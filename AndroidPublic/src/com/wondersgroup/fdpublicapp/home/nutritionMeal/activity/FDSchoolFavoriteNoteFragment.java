package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDFavoriteNoteAdpter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteNote;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 我收藏的帖子
 * @author chengshaohua
 *
 */
public class FDSchoolFavoriteNoteFragment extends FDListViewFragment {
	public List<FDFavoriteNote> noteFavoriteList = new ArrayList<FDFavoriteNote>();
	public FDFavoriteNoteAdpter favoriteNoteAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initSchoolFavoriteNoteView();
		
		return fragmentView;
	}	
	
	/**
	 * 初始化收藏的帖子
	 */
	public void initSchoolFavoriteNoteView() {
		favoriteNoteAdapter = new FDFavoriteNoteAdpter(context,noteFavoriteList);         
		fdListView.setAdapter(favoriteNoteAdapter);
	}
	
	/**
	 * 加载收藏帖子列表
	 */
	public void loadFragmentPageList() {
		FDNutritionMealWrapper mealWrapper = new FDNutritionMealWrapper(context);
		mealWrapper.getStudentFavoriteNote(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDFavoriteNote> resultPage = (FDResultPage<FDFavoriteNote>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDFavoriteNote> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						noteFavoriteList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						noteFavoriteList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					favoriteNoteAdapter = new FDFavoriteNoteAdpter(context,noteFavoriteList);         
					fdListView.setAdapter(favoriteNoteAdapter);
					onListViewLoad(totalRecord);
				}
			}
		});
	}

	
}
