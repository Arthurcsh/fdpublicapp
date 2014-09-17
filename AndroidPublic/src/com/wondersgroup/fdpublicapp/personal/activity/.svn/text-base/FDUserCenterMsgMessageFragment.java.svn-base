package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.personal.adapter.FDMessageReplyAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDMessage;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 * 消息管理-留言回复
 * @author chengshaohua
 */
public class FDUserCenterMsgMessageFragment extends FDListViewFragment {
	private ArrayList<FDMessage> leaveReplyList = new ArrayList<FDMessage>();
	private FDMessageReplyAdapter leaveReplyAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
		initCommentRestaurantView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化留言回复
     */
    public void initCommentRestaurantView() {
    	leaveReplyAdapter = new FDMessageReplyAdapter(context,leaveReplyList);         
		fdListView.setAdapter(leaveReplyAdapter);
		
//		loadFragmentPageList();
    }
	
    /**
     * 更新留言回复
     */
    public void setCommentRestaurantView() {
    	
    }
    
	/**
	 * 加载留言回复列表
	 */
	public void loadFragmentPageList() {
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getMessageForLeaveMessage(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDMessage> resultPage = (FDResultPage<FDMessage>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDMessage> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						leaveReplyList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						leaveReplyList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					leaveReplyAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}  
}
