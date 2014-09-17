package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.Map;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDCommonFragmentAdapter;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDCustomViewPager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDSchool;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;

/**
 *  学生营养餐主页面
 * @author chengshaohua
 *
 */
public class FDStudentMealsActivity extends FDBaseActivity {
	private FDCustomViewPager nutritionViewPager;
	private FDCommonFragmentAdapter nutritionMealAdapter;
	private FDStudentSchoolFragment studentSchoolFragment;
	private FDStudentSchoolListFragment studentSchoolListFragment;
	private Map<String,String> mealMap;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_student_meals_content);		
		
		initNutritionMealView();
	}
	
	public void initNutritionMealView() {
		this.nutritionViewPager = (FDCustomViewPager) this.findViewById(R.id.student_meal_view_pager);
		Button collectSchoolButton = (Button) findViewById(R.id.student_meal_collect_school);
		Button calculatorButton = (Button) findViewById(R.id.student_meal_nutrition_calculator);
		ArrayList<Fragment> studentMealFramgentList = new ArrayList<Fragment>();
		studentSchoolFragment = new FDStudentSchoolFragment(searchSchoolListener);
		studentSchoolListFragment = new FDStudentSchoolListFragment(backSchoolListener);
		studentMealFramgentList.add(studentSchoolFragment);
		studentMealFramgentList.add(studentSchoolListFragment);
		this.nutritionMealAdapter = new FDCommonFragmentAdapter(getSupportFragmentManager(), studentMealFramgentList);
		nutritionViewPager.setAdapter(nutritionMealAdapter);
//		nutritionViewPager.setOnPageChangeListener(this);
		nutritionViewPager.setCurrentItem(0);
		
		collectSchoolButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				Intent intent = new Intent(FDStudentMealsActivity.this,FDStudentSchoolFavoriteActivity.class);
				intent.putExtra("fd.student.meal.favorite.index", 3);     // 查询收藏的学校
				startActivity(intent);
			}
		});
	}
	
	
	/**
	 *  营养餐学校查询
	 */
	FDCallback searchSchoolListener = new FDCallback() {
		public void onCallback(Object callback) {
			if(callback!=null) {
				mealMap = (Map<String, String>) callback;
			}
			nutritionViewPager.setCurrentItem(1);
			studentSchoolListFragment.loadStudentSchools(mealMap);
		}
	};
	
	/**
	 *  学校列表后退,选择学校
	 */
	FDCallback backSchoolListener = new FDCallback() {
		public void onCallback(Object callback) {
			if(callback==null) {
				nutritionViewPager.setCurrentItem(0);
			}else {
				FDSchool school = (FDSchool) callback;
				Intent intent = new Intent(context,FDStudentSchoolDetailActivity.class);
				intent.putExtra("student.school.key", school);
				startActivityForResult(intent, 100);
			}
		}
	};
	
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if (resultCode != FDNutritionMealWrapper.FD_NUTRITION_SCHOOL_DETAIL) return;

		/** 重新设置条件查询学校 **/
		nutritionViewPager.setCurrentItem(1);
		studentSchoolListFragment.loadStudentSchools(mealMap);

	}
}
