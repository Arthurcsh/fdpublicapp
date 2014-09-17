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
import com.wondersgroup.fdpublicapp.personal.adapter.FDUserCommentDishAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineComment;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 * 我对菜肴的点评
 * @author chengshaohua
 */
public class FDUserCenterMyCommentsDishFragment extends FDListViewFragment {
	private ArrayList<FDCuisineComment> dishCommentList = new ArrayList<FDCuisineComment>();
	private FDUserCommentDishAdapter commentDishAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
		initCommentRestaurantView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化我的评价菜肴
     */
    public void initCommentRestaurantView() {
    	commentDishAdapter = new FDUserCommentDishAdapter(context,dishCommentList);         
		fdListView.setAdapter(commentDishAdapter);
		
//		loadFragmentPageList();
    }
	
    /**
     * 更新我的评价
     */
    public void setCommentRestaurantView() {
    	
    }
    
	/**
	 * 加载我对菜肴点评的列表
	 */
	public void loadFragmentPageList() {
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getCommentForCuisine(-1, pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDCuisineComment> resultPage = (FDResultPage<FDCuisineComment>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDCuisineComment> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						dishCommentList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						dishCommentList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					commentDishAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}  
}
