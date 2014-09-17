package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDFavoriteSchoolAdpter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteSchool;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDSchool;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 我收藏的学校
 * @author chengshaohua
 *
 */
public class FDSchoolFavoriteSchoolFragment extends FDListViewFragment {
	public List<FDSchool> schoolFavoriteList = new ArrayList<FDSchool>();
	public FDFavoriteSchoolAdpter favoriteSchoolAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initSchoolFavoriteRestView();
		
		return fragmentView;
	}	
	
	/**
	 * 初始化收藏的学校
	 */
	public void initSchoolFavoriteRestView() {
		favoriteSchoolAdapter = new FDFavoriteSchoolAdpter(context,schoolFavoriteList);         
		fdListView.setAdapter(favoriteSchoolAdapter);
		fdListView.getListView().setSelector(R.color.grays);
		fdListView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> adapter, View view, int position,long arg3) {
				if (position == 0) return;
				if (schoolFavoriteList != null && position <= schoolFavoriteList.size()) {
					Intent intent = new Intent(context,FDStudentSchoolDetailActivity.class);
					FDSchool favoriteSchool = schoolFavoriteList.get(position - 1);
					if (favoriteSchool != null) {
						intent.putExtra("student.school.key", favoriteSchool);
					}
					startActivity(intent);
				}
			}
		});
		
		loadFragmentPageList();      // 这里默认加载数据欠妥
	}
	
	/**
	 * 加载收藏学校列表
	 */
	public void loadFragmentPageList() {
		if(context==null) return;
		
		FDNutritionMealWrapper mealWrapper = new FDNutritionMealWrapper(context);
		mealWrapper.getStudentFavoriteSchool(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDSchool> resultPage = (FDResultPage<FDSchool>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDSchool> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						schoolFavoriteList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						schoolFavoriteList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					favoriteSchoolAdapter = new FDFavoriteSchoolAdpter(context,schoolFavoriteList);         
					fdListView.setAdapter(favoriteSchoolAdapter);
					onListViewLoad(totalRecord);
				}
			}
		});
	}

}
