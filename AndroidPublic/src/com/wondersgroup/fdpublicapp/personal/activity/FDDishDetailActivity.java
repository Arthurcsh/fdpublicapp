package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.Date;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.delicacy.activity.FDRestMenuDetailsTraceListActivity;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.personal.adapter.FDDishCommentAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineComment;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineDetail;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;
import com.wondersgroup.fdpublicapp.search.activity.FDDishCommentActivity;
import com.wondersgroup.fdpublicapp.search.mode.FDRestMenu;

/**
 *  菜肴详情
 * @author chengshaohua
 *
 */
public class FDDishDetailActivity extends FDBaseActivity implements IXListViewListener{
	private int cuisineId;
	private FDCuisineDetail cuisineInfo;                    // 菜肴详情
	private ImageView mealImageView;
	private TextView titleTextView;
	private TextView nameTextView;
	private TextView descTextView;
	private TextView recommendCountTextView;
	private TextView favoriteCountTextView;
	private TextView mainMatTextView;
	private TextView subMatTextView;
	private ImageView commentImageView;
	private ImageView recommendImageView;
	private ImageView favoriteImageView;
	private LJListView commentListView;
	private TextView commentCountTextView;
	private FDDishCommentAdapter commentAdapter;
	private List<FDCuisineComment> commentList;
	private Handler mHandler;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_dish_details_xml);
		
		initDishDetailView();
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey("fd.dish.detail.key")) {
			cuisineId = bundle.getInt("fd.dish.detail.key");
			loadDishDeatilData();
		}
	}
	
	/**
	 * 初始化菜肴详情视图
	 */
	public void initDishDetailView() {
		LinearLayout backLayout = (LinearLayout) this.findViewById(R.id.fd_dish_details_back_img_layout);
		RelativeLayout recommendLayout = (RelativeLayout) this.findViewById(R.id.fd_dish_details_recommend_img_layout);
		RelativeLayout favoriteLayout = (RelativeLayout) this.findViewById(R.id.fd_dish_details_favorite_img_layout);
		RelativeLayout retrospectLayout = (RelativeLayout) this.findViewById(R.id.fd_dish_details_retrospect_layout);
		recommendImageView = (ImageView) this.findViewById(R.id.fd_dish_details_recommend_img);
		favoriteImageView = (ImageView) this.findViewById(R.id.fd_dish_details_favorite_img);
		mealImageView = (ImageView) this.findViewById(R.id.fd_dish_details_photo_img);
		titleTextView = (TextView) this.findViewById(R.id.fd_dish_details_name_header_title);
		commentCountTextView = (TextView) this.findViewById(R.id.fd_dish_detail_comment_count_textview);
		nameTextView = (TextView) this.findViewById(R.id.fd_dish_details_name_textview);
		descTextView = (TextView) this.findViewById(R.id.fd_dish_details_desc_textview);
		recommendCountTextView = (TextView) this.findViewById(R.id.fd_dish_recommend_count_textview);
		favoriteCountTextView = (TextView) this.findViewById(R.id.fd_dish_favorite_count_textview);
		mainMatTextView = (TextView) this.findViewById(R.id.fd_dish_details_mainmat_textview);
		subMatTextView = (TextView) this.findViewById(R.id.fd_dish_details_submat_textview);
		commentImageView = (ImageView) this.findViewById(R.id.fd_dish_detail_comment_imageview);
		commentListView = (LJListView) this.findViewById(R.id.fd_dish_detail_comment_xml);
		commentListView.setPullLoadEnable(true, "..");
		commentListView.setPullRefreshEnable(true);
		commentListView.setIsAnimation(true);
		commentListView.setXListViewListener(this);
		commentListView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
				if(commentList==null || position==0) return;
				
				FDCuisineComment cuisineComment = commentList.get(position-1);      // 评论
				Intent intent = new Intent(context,FDDishCommentDetailActivity.class);
				intent.putExtra("fd.cuisine.comment.key", cuisineComment);
				startActivity(intent);
			}
		});
		
		retrospectLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				laodIngredientTraceData();
			}
		});
		commentImageView.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(cuisineInfo==null ) return;
				
				Intent intent = new Intent(context,FDDishCommentActivity.class);
				intent.putExtra(FDConst.FD_QUERY_CUISINE_EXTRA_KEY, cuisineInfo);
				startActivityForResult(intent,100);
			}
		});
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDDishDetailActivity.this.finish();
			}
		});
		recommendLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(cuisineInfo==null) return;
				FDPersonalWrapper personalWrapper = new FDPersonalWrapper(context);
				int recommendEanble = cuisineInfo.getIsRecommended()==0?1:0;
				personalWrapper.setRecommondDish(cuisineInfo.getId(), recommendEanble, new FDCallback(){
					public void onCallback(Object callback) {
						if(callback==null) return;
						FDResultParser dishResultParser = (FDResultParser) callback;
						int status = dishResultParser.getBaseResult().getStatus();
						if(status==0) {
							FDViewUtil.showTaggleToast(appContext, cuisineInfo.getIsRecommended(), "推荐");
							loadDishDeatilData();
						}
					}
				});
			}
		});
		favoriteLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDPersonalWrapper personalWrapper = new FDPersonalWrapper(context);
				int favoriteEanble = cuisineInfo.getIsCollected()==0?1:0;
				personalWrapper.setForaviteDish(cuisineInfo.getId(), favoriteEanble, new FDCallback(){
					public void onCallback(Object callback) {
						if(callback==null) return;
						FDResultParser dishResultParser = (FDResultParser) callback;
						int status = dishResultParser.getBaseResult().getStatus();
						if(status==0) {
							FDViewUtil.showTaggleToast(appContext, cuisineInfo.getIsCollected(), "收藏");
							loadDishDeatilData();
						}
					}
				});
			}
		});
	}
	
	/**
	 *  点评成功后返回更新
	 */
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if (resultCode != 20) return;
		
		pageNo = 1;
		loadDishDetailComments();

		super.onActivityResult(requestCode, resultCode, data);  
	}
	
	public void setDishDetailView() {
		if(cuisineInfo==null) return;
		
		titleTextView.setText(cuisineInfo.getRestaurantNameAbbrev());
		nameTextView.setText(cuisineInfo.getName());
		descTextView.setText(cuisineInfo.getDescription());
		recommendCountTextView.setText(cuisineInfo.getCountOfRecommended()+"人推荐");
		favoriteCountTextView.setText(cuisineInfo.getCountOfCollected()+"人收藏");
		if(cuisineInfo.getIsRecommended()==0) {
			recommendImageView.setImageResource(R.drawable.fd_restaurant_details_support_disable);
		}else if(cuisineInfo.getIsRecommended()==1) {
			recommendImageView.setImageResource(R.drawable.fd_restaurant_details_support_enable);
		}
		if(cuisineInfo.getIsCollected()==0) {
			favoriteImageView.setImageResource(R.drawable.fd_restaurant_collection_disable);
		}else if(cuisineInfo.getIsCollected()==1) {
			favoriteImageView.setImageResource(R.drawable.fd_restaurant_collection_enable);
		}
		mainMatTextView.setText(cuisineInfo.getMainMat());
		subMatTextView.setText(cuisineInfo.getSubMat());
		List<FDImage> mealImages = cuisineInfo.getPicList();
		if(mealImages!=null && mealImages.size()>0) {
			FDImage mealImage = mealImages.get(0);
			FDViewUtil.showServerImage(this, mealImageView, mealImage.getFilePath());
		}
	}
	
	/**
	 *  加载食材台账信息
	 */
	public void laodIngredientTraceData() {
		if(cuisineInfo==null) return;
//		if(StringUtils.isEmpty(cuisineInfo.getOutputMatId())){
//			FDViewUtil.showToast(context, "没有查到相关食材台账记录..",true);
//			return;
//		}
		
		FDRestMenu dishMenu = new FDRestMenu();
		dishMenu.setId(cuisineInfo.getId());
		dishMenu.setMenuType(0);
		dishMenu.setCusineType(cuisineInfo.getCusineType());
		dishMenu.setCusineTypeValue(cuisineInfo.getCusineTypeValue());
		dishMenu.setCuisineCustomTypeId(cuisineInfo.getCuisineCustomTypeId());
		dishMenu.setCuisineCustomTypeName(cuisineInfo.getCuisineCustomTypeName());
		dishMenu.setDescription(cuisineInfo.getDescription());
		dishMenu.setMainMat(cuisineInfo.getMainMat());
		dishMenu.setSubMat(cuisineInfo.getSubMat());
		dishMenu.setPicList(cuisineInfo.getPicList());
		
		Intent intent = new Intent(context,FDRestMenuDetailsTraceListActivity.class);
		intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, dishMenu);
		startActivity(intent);
	}
	
	/**
	 *  加载菜肴详情信息
	 *  dishId  餐次编号
	 */
	public void loadDishDeatilData() {
		if(cuisineId<0) return;
		
		FDPersonalWrapper personalWrapper = new FDPersonalWrapper(this);
		personalWrapper.getDishDetail(cuisineId, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				cuisineInfo = (FDCuisineDetail) callback;
				mHandler = new Handler();
				mHandler.post(new Runnable(){
					public void run() {
						pageNo = 1;
						setDishDetailView();
						loadDishDetailComments();
					}
				});
			}
		});
	}

	/**
	 * 点评信息
	 */
	public void loadDishDetailComments() {
		if(cuisineInfo==null) return;
		
		FDPersonalWrapper personalWrapper = new FDPersonalWrapper(this);
		personalWrapper.getDishComments(pageNo++,LOAD_MORE_MAX_COUNT,cuisineInfo.getId(), new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDCuisineComment> resultPage = (FDResultPage<FDCuisineComment>) callback;
				initDishCommentView(resultPage);
			}
		});
	}
	
	public void initDishCommentView(FDResultPage<FDCuisineComment> resultPage) {
		if(resultPage==null) return;
		totalPage = (int) (1+resultPage.getTotalRecord()/LOAD_MORE_MAX_COUNT);
		
		commentCountTextView.setText("(共"+resultPage.getTotalRecord()+"条)");
		commentList = resultPage.getResultList();
		commentAdapter = new FDDishCommentAdapter(this,commentList);
		commentListView.setAdapter(commentAdapter);
		
		onCommentLoad(resultPage.getTotalRecord());
	}
	
	public void onRefresh() {
		mHandler = new Handler();
		
		pageNo = 1;
		onLoadMore();
	}

	public void onLoadMore() {
		if(cuisineInfo==null) return;
		
		if(pageNo>totalPage) {
			onCommentLoad(0);
			return;
		}
		
		mHandler.post(new Runnable(){
			public void run() {
				loadDishDetailComments();
			}
		});
	}
	
	/**
	 *  更新列表状态
	 * @param count
	 */
	private void onCommentLoad(long count) {
		commentListView.setCount(""+count);
		commentListView.stopRefresh();
		commentListView.stopLoadMore(pageNo>totalPage);
		commentListView.setRefreshTime(StringUtils.dateToString(new Date()));
	}
}
