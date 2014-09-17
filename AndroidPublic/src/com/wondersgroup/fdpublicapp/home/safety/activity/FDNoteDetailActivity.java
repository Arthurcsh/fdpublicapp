package com.wondersgroup.fdpublicapp.home.safety.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.sina.weibo.SinaWeibo.ShareParams;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDHorizontalListViewAdapter;
import com.wondersgroup.fdpublicapp.common.custom.FDHorizontalListView;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonComment;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonNote;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDCommonCommentNoteActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDNoteCommentAdapter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteNoteDetail;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import com.wondersgroup.fdpublicapp.widget.FDForwardPopupWindows;
import com.wondersgroup.fdpublicapp.widget.FDForwardPopupWindows.FDForwardListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author chengshaohua
 *  帖子详情
 */
public class FDNoteDetailActivity extends FDBaseActivity implements OnClickListener, IXListViewListener{
	public static final String FD_NOTE_DETAIL_KEY = "fd.note.detail.key";
	private FDCommonNote favoriteNote;
	private FDFavoriteNoteDetail noteDetail;
	private LinearLayout backLayout;
	private RelativeLayout favoriteLayout;
	private ImageView noteDetaiFavoriteView;
	private TextView noteDetailTitleView;
	private TextView noteDetailContentView;
	private TextView noteDetailNameView;
	private TextView noteDetailDateView;
	private TextView noteForwardCountView;
	private TextView noteFavoriteCountView;
	private TextView noteCommentCountView;
	private Button noteForwardButton;
	private Button noteCommentButton;
	private FDHorizontalListView noteDetailImageView;
	private LJListView noteCommentListView;
	private List<FDNoteComment> commentsList = new ArrayList<FDNoteComment>();
	private FDNoteCommentAdapter noteCommentAdapter;
	private View forwardContenView;
	private FDForwardPopupWindows forwardPopupWindow;
	private Handler mHandler;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fd_common_note_detail);
		ShareSDK.initSDK(this);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null && bundle.containsKey(FD_NOTE_DETAIL_KEY)) {
			favoriteNote = bundle.getParcelable(FD_NOTE_DETAIL_KEY);
		}
		
		initFavoriteNoteDetailView();
	} 

	/**
	 *  初始化帖子详情
	 */
	private void initFavoriteNoteDetailView() {
		forwardContenView = View.inflate(context,R.layout.fd_common_forward_popupwindows, null);
		backLayout = (LinearLayout) this.findViewById(R.id.fd_favorite_note_detail_header_back_label);
		favoriteLayout = (RelativeLayout) this.findViewById(R.id.fd_favorite_note_detail_collect_layout);
		noteDetaiFavoriteView = (ImageView) this.findViewById(R.id.fd_favorite_note_detail_level_img);
		noteDetailTitleView = (TextView) this.findViewById(R.id.fd_favorite_note_detail_title_textview);
		noteDetailContentView = (TextView) this.findViewById(R.id.fd_favorite_note_detail_content_textview);
		noteDetailNameView = (TextView) this.findViewById(R.id.fd_favorite_note_supervision_detail_name_tv);
		noteDetailDateView = (TextView) this.findViewById(R.id.fd_favorite_note_supervision_detail_date_tv);
		noteForwardCountView = (TextView) this.findViewById(R.id.fd_favorite_note_detail_forward_tv);
		noteFavoriteCountView = (TextView) this.findViewById(R.id.fd_favorite_note_detail_favorites_tv);
		noteCommentCountView = (TextView) this.findViewById(R.id.fd_favorite_note_detail_comment_textview);
		noteDetailImageView = (FDHorizontalListView) this.findViewById(R.id.fd_school_favorite_note_detail_listView);
		noteCommentListView = (LJListView) this.findViewById(R.id.student_favorite_note_detail_comment_xml);
		noteCommentListView.setPullLoadEnable(true, "");
		noteCommentListView.setPullRefreshEnable(true);
		noteCommentListView.setIsAnimation(true);
		noteCommentListView.setXListViewListener(this);
		noteForwardButton = (Button) this.findViewById(R.id.student_meal_favorite_note_forward);
		noteCommentButton = (Button) this.findViewById(R.id.student_meal_favorite_note_comment);
		backLayout.setOnClickListener(this);
		favoriteLayout.setOnClickListener(this);
		noteForwardButton.setOnClickListener(this);
		noteCommentButton.setOnClickListener(this);
		
		loadFavoriteNoteDetail();
		loadFavoriteNoteDetailComments();
		
		noteCommentAdapter = new FDNoteCommentAdapter(this, commentsList);
		noteCommentListView.setAdapter(noteCommentAdapter);
		noteCommentListView.setOnItemClickListener(null);
		
		setFavoriteNoteDetailView();
	}

	/**
	 * 设置帖子详情
	 */
	private void setFavoriteNoteDetailView() {
		if(noteDetail==null) return;
		
		noteDetailTitleView.setText(noteDetail.getContentTitle());
		noteDetailContentView.setText(noteDetail.getContentTextData());
		noteDetailNameView.setText(noteDetail.getCreateUsername());
		noteDetailDateView.setText(noteDetail.getCreateDate());
		
		if(noteDetail.getIsCollected()==0) {
			noteDetaiFavoriteView.setImageResource(R.drawable.fd_restaurant_collection_disable);
		}else if(noteDetail.getIsCollected()==1) {
			noteDetaiFavoriteView.setImageResource(R.drawable.fd_restaurant_collection_enable);
		}
		
//		noteForwardCountView.setText("转发("+noteDetail.getCollectCount()+")");
		noteFavoriteCountView.setText("收藏("+noteDetail.getCollectCount()+")");
		List<FDImage> noteDetailImages = noteDetail.getPicList();
		if(noteDetailImages!=null && noteDetailImages.size()>0) {
			noteDetailImageView.setVisibility(View.VISIBLE);
			FDHorizontalListViewAdapter photoAdapter = new FDHorizontalListViewAdapter(this, noteDetailImages, false);
			noteDetailImageView.setAdapter(photoAdapter);
		}else {
			noteDetailImageView.setVisibility(View.GONE);
		}
		
	}
	
	/**
	 * 加载收藏的帖子详情 
	 */
	private void loadFavoriteNoteDetail() {
		if(favoriteNote==null) return;
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(this);
		nutritionMealWrapper.getStudentFavoriteNoteDetail(favoriteNote.getContentId(), new FDCallback(){
			public void onCallback(Object callback) {
				noteDetail = (FDFavoriteNoteDetail) callback;
				setFavoriteNoteDetailView();
			}
		});
	}
	
	/**
	 * 加载帖子详情评论
	 */
	private void loadFavoriteNoteDetailComments() {
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(this);
		nutritionMealWrapper.getStudentFavoriteNoteComments(favoriteNote.getContentId(), pageNo, LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				long totalRecord = 0;
				FDResultPage<FDNoteComment> noteCommentPage = (FDResultPage<FDNoteComment>) callback;
				totalPage = noteCommentPage.getPageCount();
				List<FDNoteComment> noteCommentsList = noteCommentPage.getResultList();
				if(noteCommentsList==null || pageNo==1) {
					commentsList.clear();
					totalRecord = 0;
				}
				if(noteCommentsList!=null) {
					commentsList.addAll(noteCommentsList);
					totalRecord = noteCommentPage.getTotalRecord();
				}
				
				noteCommentAdapter.setSearchTableData(commentsList);
				noteCommentCountView.setText("共"+totalRecord+"条");
				onCommentLoad(totalRecord);
			}
		});
	}
	
	public void onClick(View view) {
		if(view == backLayout) {
			this.finish();
		}else if(view == noteForwardButton) {
			forwardNoteView();
		}else if(view == noteCommentButton) {
			commentNoteView();
		}else if(view == favoriteLayout) {
			if(noteDetail==null) return;
			FDNutritionMealWrapper studentWrapper = new FDNutritionMealWrapper(context);
			int favoriteEanble = noteDetail.getIsCollected()==0?1:0;
			studentWrapper.setStudentFavoriteNote(noteDetail.getContentId(), favoriteEanble, new FDCallback(){
				public void onCallback(Object callback) {
					if(callback==null) return;
					int status = (Integer) callback;
					if(status==0) {
						FDViewUtil.showTaggleToast(context, noteDetail.getIsCollected(), "收藏");
						loadFavoriteNoteDetail();
					}
				}
			});
		}
	}
	
	/**
	 * 转发帖子
	 */
	public void forwardNoteView() {
		mHandler = new Handler();
		forwardPopupWindow = new FDForwardPopupWindows(context, forwardContenView, new FDForwardListener(){
			public void onForwardSinaWeibo() {
				if(noteDetail==null) return;
				
				System.out.println("----------------- Sina Weibo ----------------------");
				ShareParams sp = new ShareParams();
				sp.setTitle(noteDetail.getContentTitle());
				sp.setText(noteDetail.getContentTextData());
				sp.setShareType(Platform.SHARE_TEXT);
				List<FDImage> noteImages = noteDetail.getPicList();
//				if(noteImages!=null && noteImages.size()>0) {
//					String imageUri = noteImages.get(0).getFilePath();
//					sp.setImagePath(FDViewUtil.getAbsoluteImageUrl(imageUri));
//				}
//				sp.setImageUrl("/storage/sdcard0/MyFavorite/u=3562923886");

				Platform weibo = ShareSDK.getPlatform(context, SinaWeibo.NAME);
				weibo.setPlatformActionListener(platformActionListener);        // 设置分享事件回调
				weibo.share(sp);                                                // 执行图文分享
			}

			public void onForwardQQZone() {
				System.out.println("----------------- QQ Zone ----------------------");
				ShareParams sp = new ShareParams();
				sp.setTitle(noteDetail.getContentTitle());
				sp.setText(noteDetail.getContentTextData());
				sp.setShareType(Platform.SHARE_TEXT);
				List<FDImage> noteImages = noteDetail.getPicList();

				Platform qqZone = ShareSDK.getPlatform(context, QZone.NAME);
				qqZone.setPlatformActionListener(platformActionListener);        // 设置分享事件回调
				qqZone.share(sp);                                  
			}

			public void onForwardWebChat() {
				System.out.println("----------------- WeChat ----------------------");
				Platform wechatPlat = ShareSDK.getPlatform(context, WechatMoments.NAME);
				System.out.println("-------   wechat plat  -------"+wechatPlat);
				wechatPlat.setPlatformActionListener(platformActionListener);
				WechatMoments.ShareParams sp = getWechatMomentsShareParams();
				wechatPlat.share(sp);
			}
		});
	}
	
	/**
	 * 第三方转发回调 
	 */
	PlatformActionListener platformActionListener = new PlatformActionListener() {
		public void onCancel(Platform platform, int action) {
			mHandler.post(new Runnable(){
				public void run() {
					FDViewUtil.showToast(context, "转发取消..", true);
				}
			});
		}

		public void onComplete(Platform platform, int action, HashMap<String, Object> callbackMap) {
			System.out.println("----- share callback -----"+callbackMap);
			mHandler.post(new Runnable(){
				public void run() {
					FDViewUtil.showToast(context, "转发成功..", true);
					ShareSDK.removeCookieOnAuthorize(true);
				}
			});
		}

		public void onError(Platform platform, int action, final Throwable throwable) {
			mHandler.post(new Runnable(){
				public void run() {
					FDViewUtil.showToast(context, "转发失败.."+throwable, true);
				}
			});
			
		}
	};
	
	/**
	 * 评论帖子
	 */
	public void commentNoteView() {
		if(noteDetail==null) return;
		FDCommonComment noteComment = new FDCommonComment();
		noteComment.setContentId(noteDetail.getContentId());
		noteComment.setContentTitle(noteDetail.getContentTitle());
		Intent intent = new Intent(this, FDCommonCommentNoteActivity.class);
		intent.putExtra("fd.common.comment.key", noteComment);
		startActivityForResult(intent, 100);
	}

	/**
	 * 帖子点评返回刷新
	 */
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if(resultCode==204) {
			loadFavoriteNoteDetailComments();
		}
        System.out.println("-----Code-----------"+resultCode+"------Data-------"+data);
        super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void onRefresh() {
		mHandler = new Handler();
		this.pageNo = 1;
		mHandler.post(new Runnable(){
			public void run() {
				loadFavoriteNoteDetailComments();
			}
		});
	}

	public void onLoadMore() {
		if (favoriteNote == null) return;
		if(mHandler==null) {
			mHandler = new Handler();
		}

		pageNo++;
		if(pageNo>totalPage) {
			onCommentLoad(0);
			return;
		}
		mHandler.post(new Runnable(){
			public void run() {
				loadFavoriteNoteDetailComments();
			}
		});
	}
  	
	/**
	 *  更新列表状态
	 * @param count
	 */
	private void onCommentLoad(long count) {
		noteCommentListView.setCount(""+count);
		noteCommentListView.stopRefresh();
		noteCommentListView.stopLoadMore(pageNo>totalPage);
		noteCommentListView.setRefreshTime(StringUtils.dateToString(new Date()));
	}
	
	/**微信朋友圈分享参数
	 * 
	 * WechatMoment share params
	 * */
	private WechatMoments.ShareParams getWechatMomentsShareParams() {
		if(noteDetail==null) return null;
		WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
		//任何分享类型都需要title和text
		sp.title = noteDetail.getContentTitle();
		sp.text = noteDetail.getContentTextData();
		sp.shareType = Platform.SHARE_TEXT;
		
		return sp;
	}
	
	protected void onDestroy() {
		super.onDestroy();
		ShareSDK.stopSDK(this);
	}
}
