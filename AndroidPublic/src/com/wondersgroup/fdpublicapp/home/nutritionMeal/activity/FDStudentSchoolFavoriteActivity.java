package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDCommonFragmentAdapter;
import com.wondersgroup.fdpublicapp.common.adapter.FDFragmentOnClickListener;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDCustomViewPager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 *   营养餐我的学校收藏夹
 * @author chengshaohua
 *
 */
public class FDStudentSchoolFavoriteActivity extends FDBaseActivity implements OnClickListener, OnPageChangeListener {
	private int favoriteIndex;
	private LinearLayout backLayout;
	private ArrayList<Fragment> favoriteFramgentList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_student_school_favorite);
		Bundle bundle = getIntent().getExtras();  
		if(bundle!=null && bundle.containsKey("fd.student.meal.favorite.index")) {
			favoriteIndex = bundle.getInt("fd.student.meal.favorite.index");
		}
		initStudentSchoolView();
	}

	/**
	 *  初始化我的收藏
	 */
	public void initStudentSchoolView() {
		backLayout = (LinearLayout) findViewById(R.id.fd_student_meals_search_school_favorite_back_layout);
		backLayout.setOnClickListener(this);
		FDCustomViewPager  favoriteViewPager = (FDCustomViewPager) findViewById(R.id.fd_student_school_favorite_pageview);
		RadioGroup favoriteGroup = (RadioGroup) this.findViewById(R.id.fd_student_school_favorite_group);
		for(int i=0;i<favoriteGroup.getChildCount();i++) {
			View favoriteView = favoriteGroup.getChildAt(i);
			favoriteView.setOnClickListener(new FDFragmentOnClickListener(favoriteViewPager, i));
		}
		
		favoriteFramgentList = new ArrayList<Fragment>();
		FDSchoolFavoriteRestaurantFragment favoriteRestFragment = new FDSchoolFavoriteRestaurantFragment();
		FDSchoolFavoriteCuisineFragment favoriteCuisineFragment = new FDSchoolFavoriteCuisineFragment();
		FDSchoolFavoriteNoteFragment favoriteNoteFragment = new FDSchoolFavoriteNoteFragment();
		FDSchoolFavoriteSchoolFragment favoriteSchoolFragment = new FDSchoolFavoriteSchoolFragment();
		
		favoriteFramgentList.add(favoriteRestFragment);
		favoriteFramgentList.add(favoriteCuisineFragment);
		favoriteFramgentList.add(favoriteNoteFragment);
		favoriteFramgentList.add(favoriteSchoolFragment);
		FDCommonFragmentAdapter nutritionFavoriteAdapter = new FDCommonFragmentAdapter(getSupportFragmentManager(), favoriteFramgentList);
		favoriteViewPager.setAdapter(nutritionFavoriteAdapter);
		favoriteViewPager.setOnPageChangeListener(this);
		favoriteViewPager.setCurrentItem(favoriteIndex);
		setViewPagerBar(favoriteIndex, favoriteGroup);
	}
	
	public void setViewPagerBar(int index, RadioGroup radioGroup) {
		if(radioGroup==null) return;
		RadioButton seleceedButton = (RadioButton) radioGroup.getChildAt(index);
		seleceedButton.setChecked(true);
	}
	
	public void onClick(View view) {
		if (view == backLayout) {
			finish();
		}
	}

	/**
	 *  切换各个收藏更新数据 (只加载一次)
	 */
	public void onPageSelected(int position) {
		if(favoriteFramgentList==null) return;
		
		Fragment fragment = favoriteFramgentList.get(position);
		if(fragment!=null) {
			FDListViewFragment listViewFragment = (FDListViewFragment) fragment;
			listViewFragment.loadFragmentPageList(1);
		}
	}
	
	public void onPageScrollStateChanged(int position) {
	}
	public void onPageScrolled(int position, float arg1, int arg2) {
	}

}
