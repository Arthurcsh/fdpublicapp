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
import com.wondersgroup.fdpublicapp.personal.adapter.FDUserCommentNoteAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDCommentNote;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 * 我对帖子的点评
 * @author chengshaohua
 */
public class FDUserCenterMyCommentsNoteFragment extends FDListViewFragment {
	private ArrayList<FDCommentNote> noteCommentList = new ArrayList<FDCommentNote>();
	private FDUserCommentNoteAdapter commentNoteAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
		initCommentRestaurantView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化我的评价帖子
     */
    public void initCommentRestaurantView() {
    	commentNoteAdapter = new FDUserCommentNoteAdapter(context,noteCommentList);         
		fdListView.setAdapter(commentNoteAdapter);
    }
	
    /**
     * 更新我的评价
     */
    public void setCommentRestaurantView() {
    	
    }
    
	/**
	 * 加载我对帖子点评的列表
	 */
	public void loadFragmentPageList() {
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getCommentForNote(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDCommentNote> resultPage = (FDResultPage<FDCommentNote>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDCommentNote> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						noteCommentList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						noteCommentList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					commentNoteAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}  
}
