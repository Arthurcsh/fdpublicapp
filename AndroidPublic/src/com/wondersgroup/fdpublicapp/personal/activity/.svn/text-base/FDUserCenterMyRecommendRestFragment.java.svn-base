package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.personal.adapter.FDUserRecommendRestAdapter;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantRecommend;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 * 我推荐的餐馆
 * @author chengshaohua
 */
public class FDUserCenterMyRecommendRestFragment extends FDListViewFragment {
	private ArrayList<FDRestaurantRecommend> restRecommendList = new ArrayList<FDRestaurantRecommend>();
	private FDUserRecommendRestAdapter recommendRestAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
		initCommentRestaurantView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化我的推荐餐馆
     */
    public void initCommentRestaurantView() {
    	recommendRestAdapter = new FDUserRecommendRestAdapter(context,restRecommendList);         
		fdListView.setAdapter(recommendRestAdapter);
		
		loadFragmentPageList();
    }
	
    /**
     * 更新我的推荐
     */
    public void setCommentRestaurantView() {
    	
    }
    
	/**
	 * 加载我对餐厅推荐的列表
	 */
	public void loadFragmentPageList() {
		FDLocation location = (FDLocation) ServiceManager.get(FDConst.FD_SERVER_LOCATION_POINT);
		
		FDPersonalWrapper commentWrapper = new FDPersonalWrapper(context);
		commentWrapper.getRecommendForRestaurant(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDRestaurantRecommend> resultPage = (FDResultPage<FDRestaurantRecommend>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDRestaurantRecommend> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						restRecommendList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						restRecommendList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					recommendRestAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}  
}
