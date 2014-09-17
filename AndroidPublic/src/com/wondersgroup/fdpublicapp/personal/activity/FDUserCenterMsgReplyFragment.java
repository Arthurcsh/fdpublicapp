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
import com.wondersgroup.fdpublicapp.personal.adapter.FDMessageAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDCommentReply;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 * 消息-评论回复
 * @author chengshaohua
 */
public class FDUserCenterMsgReplyFragment extends FDListViewFragment {
	private ArrayList<FDCommentReply> commengReplyList = new ArrayList<FDCommentReply>();
	private FDMessageAdapter commengReplyAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
		initCommentRestaurantView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化消息回复
     */
    public void initCommentRestaurantView() {
    	commengReplyAdapter = new FDMessageAdapter(context,commengReplyList);         
		fdListView.setAdapter(commengReplyAdapter);
		
		loadFragmentPageList();
    }
	
    /**
     * 更新消息回复
     */
    public void setCommentRestaurantView() {
    	
    }
    
	/**
	 * 加载消息回复的列表
	 */
	public void loadFragmentPageList() {
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getMessageForComment(-1, pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDCommentReply> resultPage = (FDResultPage<FDCommentReply>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDCommentReply> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						commengReplyList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						commengReplyList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					commengReplyAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}  
}
