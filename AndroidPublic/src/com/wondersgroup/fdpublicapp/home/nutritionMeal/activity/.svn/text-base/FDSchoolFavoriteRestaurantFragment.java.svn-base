package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDFavoriteRestAdapter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteRestaurant;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 我收藏的餐馆
 * @author chengshaohua
 *
 */
public class FDSchoolFavoriteRestaurantFragment extends FDListViewFragment {
	public List<FDFavoriteRestaurant> schoolFavoriteList = new ArrayList<FDFavoriteRestaurant>();
	public FDFavoriteRestAdapter favoriteRestAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initSchoolFavoriteRestView();
		
		return fragmentView;
	}	
	
	/**
	 * 初始化收藏的学校
	 */
	public void initSchoolFavoriteRestView() {
		favoriteRestAdapter = new FDFavoriteRestAdapter(context,schoolFavoriteList);         
		fdListView.setAdapter(favoriteRestAdapter);
		
		loadFragmentPageList();
	}
	
	/**
	 *  加载分页数据列表
	 */
	public void loadFragmentPageList() {
		FDLocation location = (FDLocation) ServiceManager.get(FDConst.FD_SERVER_LOCATION_POINT);
		
		FDNutritionMealWrapper mealWrapper = new FDNutritionMealWrapper(context);
		mealWrapper.getStudentFavoriteRestaurant(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDFavoriteRestaurant> resultPage = (FDResultPage<FDFavoriteRestaurant>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDFavoriteRestaurant> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						schoolFavoriteList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						schoolFavoriteList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					favoriteRestAdapter = new FDFavoriteRestAdapter(context,schoolFavoriteList);         
					fdListView.setAdapter(favoriteRestAdapter);
					onListViewLoad(totalRecord);
				}
			}
		});
	}

}
