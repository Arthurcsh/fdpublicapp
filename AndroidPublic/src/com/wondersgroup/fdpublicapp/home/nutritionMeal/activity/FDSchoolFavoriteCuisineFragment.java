package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDFavoriteCuisineAdapter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteCuisine;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 我收藏的菜肴
 * @author chengshaohua
 *
 */
public class FDSchoolFavoriteCuisineFragment extends FDListViewFragment {
	public List<FDFavoriteCuisine> cuisineFavoriteList = new ArrayList<FDFavoriteCuisine>();
	public FDFavoriteCuisineAdapter favoriteCuisineAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initSchoolFavoriteRestView();
		
		return fragmentView;
	}	
	
	/**
	 * 初始化收藏的菜肴
	 */
	public void initSchoolFavoriteRestView() {
		favoriteCuisineAdapter = new FDFavoriteCuisineAdapter(context,cuisineFavoriteList);         
		fdListView.setAdapter(favoriteCuisineAdapter);
		
	}
	
	/**
	 * 加载收藏菜肴列表
	 */
	public void loadFragmentPageList() {
		if(context==null) return;
		
		FDNutritionMealWrapper mealWrapper = new FDNutritionMealWrapper(context);
		mealWrapper.getStudentFavoriteCuisine(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDFavoriteCuisine> resultPage = (FDResultPage<FDFavoriteCuisine>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDFavoriteCuisine> resultCuisineList = resultPage.getResultList();
					if(resultCuisineList==null || pageIndex==1) {
						cuisineFavoriteList.clear();
						totalRecord = 0;
					}
					if(resultCuisineList!=null) {
						cuisineFavoriteList.addAll(resultCuisineList);
						totalRecord = resultPage.getTotalRecord();
					}
//					favoriteRestAdapter = new FDFavoriteRestAdapter(context,schoolFavoriteList);         
//					schoolFavoriteListView.setAdapter(favoriteRestAdapter);
					favoriteCuisineAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}

}
