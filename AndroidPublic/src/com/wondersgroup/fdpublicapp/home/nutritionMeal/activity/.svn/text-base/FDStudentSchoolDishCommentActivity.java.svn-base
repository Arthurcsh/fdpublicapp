package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.Date;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDHorizontalListViewAdapter;
import com.wondersgroup.fdpublicapp.common.custom.FDHorizontalListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDCommonReply;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDCommentReplyAdapter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;

/**
 *  学生营养餐菜肴评价详情(回复)
 *  
 * @author chengshaohua
 *
 */
public class FDStudentSchoolDishCommentActivity extends FDBaseActivity implements IXListViewListener {

	private TextView nameHeaderTextView;
	private TextView nameTextView;
	private TextView dateTextView;
	private FDHorizontalListView commentImageListView;
	private LinearLayout replyLayout;
	private LJListView commentListView;
	private TextView commentContentTextView;
	private FDCommentReplyAdapter commentAdapter;
	private FDComment dishComment;
	private List<FDReply> replyList;
	private Handler mHandler;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_student_school_comment_details_xml);
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey("fd.meal.comment.key")) {
			dishComment = bundle.getParcelable("fd.meal.comment.key");
		}
		IntentFilter commentReplyFilter = new IntentFilter();
		commentReplyFilter.addAction(FDCommentReplyActivity.FD_COMMENT_REPLY_BROADCAST);
		registerReceiver(commentReplyReceiver, commentReplyFilter);
		
		initDishCommentDetailView();
		loadCommentDetailReplay();
	}
	
	private BroadcastReceiver commentReplyReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
//			context.unregisterReceiver(this);
			if (FDCommentReplyActivity.FD_COMMENT_REPLY_BROADCAST.equals(intent.getAction())) {
				pageNo = 1;
				loadCommentDetailReplay();
			}
		}
	};
	
	/**
	 * 初始化菜肴评论详情视图
	 */
	public void initDishCommentDetailView() {
		LinearLayout backLayout = (LinearLayout) this.findViewById(R.id.student_school_comment_details_back_img_layout);
		nameHeaderTextView = (TextView) this.findViewById(R.id.student_school_comment_details_dish_title);
		commentContentTextView = (TextView) this.findViewById(R.id.student_school_comment_detail_content_textview);
		nameTextView = (TextView) this.findViewById(R.id.student_school_comment_dish_name_textview);
		dateTextView = (TextView) this.findViewById(R.id.fd_usercenter_comments_date_textview);
		replyLayout = (LinearLayout) this.findViewById(R.id.fd_usercenter_comments_reply_layout);
		commentImageListView = (FDHorizontalListView) this.findViewById(R.id.student_school_comment_detail_imagelistView);
		commentListView = (LJListView) this.findViewById(R.id.student_school_comment_detail_listview);
		commentListView.setPullLoadEnable(true, "..");
		commentListView.setPullRefreshEnable(true);
		commentListView.setIsAnimation(true);
		commentListView.setXListViewListener(this);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDStudentSchoolDishCommentActivity.this.finish();
			}
		});
		replyLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDCommonReply commentReply = new FDCommonReply();
				commentReply.setCommentName(dishComment.getCommentUserName());
				commentReply.setContentId(dishComment.getCommentId());
				commentReply.setContentOwnerId(dishComment.getCommentId());
				commentReply.setCommentData(dishComment.getContentTextData());
				commentReply.setCommentDate(dishComment.getCreateDate());
				Intent intent = new Intent(context, FDCommentReplyActivity.class);
				intent.putExtra(FDCommentReplyActivity.FD_COMMENT_REPLY_KEY, commentReply);
				startActivity(intent);
			}
		});
		
		setCommentDetailView();
	}
	
	/**
	 *  点评成功后返回更新
	 */
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if (resultCode != 20) return;
		
		pageNo = 1;
		loadCommentDetailReplay();

		super.onActivityResult(requestCode, resultCode, data);  
	}
	
	/**
	 * 设置菜肴评价详情
	 */
	public void setCommentDetailView() {
		if(dishComment==null) return;
		
		nameHeaderTextView.setText(dishComment.getStuMealName());
		nameTextView.setText(dishComment.getCommentUserNickname());
		commentContentTextView.setText(dishComment.getContentTextData());
		dateTextView.setText(StringUtils.dateToString(dishComment.getLastModifiedDate()));
		List<FDImage> commentImages = dishComment.getPics();
		if(StringUtils.isEmpty(commentImages)||commentImages.size()<1) {
			((LinearLayout)commentImageListView.getParent()).setVisibility(View.GONE);
		}else {
			FDHorizontalListViewAdapter photoAdapter = new FDHorizontalListViewAdapter(this, commentImages, false);
			commentImageListView.setAdapter(photoAdapter);
		}
	}
	
	
	/**
	 * 点评回复信息
	 */
	public void loadCommentDetailReplay() {
		if(dishComment==null) return;
		
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(this);
		nutritionMealWrapper.getCommentDetailReplay(pageNo++,LOAD_MORE_MAX_COUNT,dishComment.getCommentId(), new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDReply> resultPage = (FDResultPage<FDReply>) callback;
				initCommentReplyView(resultPage);
			}
		});
	}
	
	public void initCommentReplyView(FDResultPage<FDReply> resultPage) {
		if(resultPage==null) return;
		totalPage = (int) (1+resultPage.getTotalRecord()/LOAD_MORE_MAX_COUNT);
		
		replyList = resultPage.getResultList();
		commentAdapter = new FDCommentReplyAdapter(this,replyList);
		commentListView.setAdapter(commentAdapter);
		
		onCommentLoad(resultPage.getTotalRecord());
	}
	
	public void onRefresh() {
		mHandler = new Handler();
		
		pageNo = 1;
		onLoadMore();
	}

	public void onLoadMore() {
		if(dishComment==null) return;
		
		if(pageNo>totalPage) {
			onCommentLoad(0);
			return;
		}
		
		mHandler.post(new Runnable(){
			public void run() {
				loadCommentDetailReplay();
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
	
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(commentReplyReceiver);
	}

}
