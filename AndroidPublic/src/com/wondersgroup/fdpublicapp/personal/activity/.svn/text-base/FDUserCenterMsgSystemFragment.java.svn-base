package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.personal.adapter.FDMessageSystemAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDMessage;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;
import com.wondersgroup.fdpublicapp.personal.views.FDUserCenterView;

/**
 * 消息管理-系统消息
 * @author chengshaohua
 */
public class FDUserCenterMsgSystemFragment extends FDListViewFragment {
	private ArrayList<FDMessage> systemMsgList = new ArrayList<FDMessage>();
	private FDMessageSystemAdapter systemMsgAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
		initCommentRestaurantView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化系统消息
     */
    public void initCommentRestaurantView() {
    	systemMsgAdapter = new FDMessageSystemAdapter(context,systemMsgList);         
		fdListView.setAdapter(systemMsgAdapter);
		
//		loadFragmentPageList();
    }
	
    /**
     * 更新系统消息
     */
    public void setCommentRestaurantView() {
    	
    }
    
	/**
	 * 加载系统消息列表
	 */
	public void loadFragmentPageList() {
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getMessageForSystemMessage(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDMessage> resultPage = (FDResultPage<FDMessage>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDMessage> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						systemMsgList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						systemMsgList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					systemMsgAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
					
					updateUserCenterView();
				}
			}
		});
	}  
	
	/**
	 * 刷新系统未读消息
	 */
	public void updateUserCenterView() {
		context.sendBroadcast(new Intent(FDUserCenterView.FD_USER_CENTER_LOGIN));
	}
}
