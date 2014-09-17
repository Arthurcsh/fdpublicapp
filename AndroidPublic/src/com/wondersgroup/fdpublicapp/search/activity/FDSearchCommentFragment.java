package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDCommentReplyActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantComment;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;
import com.wondersgroup.fdpublicapp.search.adapter.FDCommentRestAdapter;

/**
 *  餐馆评论详情列表
 * @author chengshaohua
 *
 */
public class FDSearchCommentFragment extends FDListViewFragment {
	private int restaurantId;
	private FDCallback backListener;
	private ArrayList<FDRestaurantComment> restCommentList = new ArrayList<FDRestaurantComment>();
	private FDCommentRestAdapter commentRestAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		IntentFilter commentReplyFilter = new IntentFilter();
		commentReplyFilter.addAction(FDCommentReplyActivity.FD_COMMENT_REPLY_BROADCAST);
		context.registerReceiver(commentReplyReceiver, commentReplyFilter);
		
		initRestaurantCommentView();
		
		return fragmentView;
	}	
	
	public FDSearchCommentFragment() {
		super();
	}
	
	public FDSearchCommentFragment(FDCallback backListener, int restId) {
		this.backListener = backListener;
		this.restaurantId = restId;
	}
	
	private BroadcastReceiver commentReplyReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
//			context.unregisterReceiver(this);
			System.out.println("------------------restaurant---comment---reply----broadcast---------------------");
			if (FDCommentReplyActivity.FD_COMMENT_REPLY_BROADCAST.equals(intent.getAction())) {
				pageIndex = 1;
				loadFragmentPageList();
			}
		}
	};
	
	/**
	 * 初始餐厅评论详情视图
	 */
	public void initRestaurantCommentView() {
		this.setListViewTitle("餐厅评价");
		commentRestAdapter = new FDCommentRestAdapter(context,restCommentList);  
		fdListView.setAdapter(commentRestAdapter);
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(backListener!=null) {
					backListener.onCallback(null);
				}
			}
		});
		
	}
	
	/**
	 *  加载分页数据列表
	 */
	public void loadFragmentPageList() {
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getCommentForRestaurant(restaurantId, pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
                if(callback==null) return;
				
				FDResultPage<FDRestaurantComment> resultPage = (FDResultPage<FDRestaurantComment>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDRestaurantComment> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						restCommentList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						restCommentList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					
					commentRestAdapter.notifyCommentRestaurant(restCommentList);
					onListViewLoad(totalRecord);
				}
			}
		});
	}
	
	public void onDestroy() {
		super.onDestroy();
		context.unregisterReceiver(commentReplyReceiver);
	}
}
