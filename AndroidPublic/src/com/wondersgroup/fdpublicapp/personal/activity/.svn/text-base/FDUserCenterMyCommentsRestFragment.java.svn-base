package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.personal.adapter.FDUserCommentRestAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantComment;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 * 我对餐馆的点评
 * @author chengshaohua
 */
public class FDUserCenterMyCommentsRestFragment extends FDListViewFragment {
	private ArrayList<FDRestaurantComment> restCommentList = new ArrayList<FDRestaurantComment>();
	private FDUserCommentRestAdapter commentRestAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
		initCommentRestaurantView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化我的评价餐馆
     */
    public void initCommentRestaurantView() {
		commentRestAdapter = new FDUserCommentRestAdapter(context,restCommentList);         
		fdListView.setAdapter(commentRestAdapter);
		
		loadFragmentPageList();
    }
	
    /**
     * 更新我的评价
     */
    public void setCommentRestaurantView() {
    	
    }
    
	/**
	 * 加载我对餐厅点评的列表
	 */
	public void loadFragmentPageList() {
		FDAppContext application = (FDAppContext) context.getApplicationContext();
		String loginUserName = application.getLoginUserName();
		
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getCommentForRestaurant(loginUserName, pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
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
					commentRestAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}  
}
