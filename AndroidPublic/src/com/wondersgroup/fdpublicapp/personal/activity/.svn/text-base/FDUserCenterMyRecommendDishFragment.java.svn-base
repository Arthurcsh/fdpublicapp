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
import com.wondersgroup.fdpublicapp.personal.adapter.FDUserRecommendDishAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineRecommend;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 * 我推荐的菜肴
 * @author chengshaohua
 */
public class FDUserCenterMyRecommendDishFragment extends FDListViewFragment {
	private ArrayList<FDCuisineRecommend> dishRecommendList = new ArrayList<FDCuisineRecommend>();
	private FDUserRecommendDishAdapter recommendDishAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
		initCommentRestaurantView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化我的推荐菜肴
     */
    public void initCommentRestaurantView() {
    	recommendDishAdapter = new FDUserRecommendDishAdapter(context,dishRecommendList);         
		fdListView.setAdapter(recommendDishAdapter);
		
//		loadFragmentPageList();
    }
	
    /**
     * 更新我的推荐
     */
    public void setCommentRestaurantView() {
    	
    }
    
	/**
	 * 加载我对菜肴推荐的列表
	 */
	public void loadFragmentPageList() {
		if(context==null) return;
		
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getRecommendForCuisine(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDCuisineRecommend> resultPage = (FDResultPage<FDCuisineRecommend>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDCuisineRecommend> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						dishRecommendList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						dishRecommendList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					recommendDishAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}  
}
