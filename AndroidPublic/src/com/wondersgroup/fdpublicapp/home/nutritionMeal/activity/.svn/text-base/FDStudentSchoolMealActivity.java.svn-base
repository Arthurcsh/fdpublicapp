package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDCommonFragmentAdapter;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDCustomViewPager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealItem;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDSchool;

/**
 *  学校营养餐日餐次详情  (早餐、午餐、晚餐)
 * @author chengshaohua
 *
 */
public class FDStudentSchoolMealActivity extends FDBaseActivity {
	private final static String FD_MEAL_ITEM_01 = "37001";
	private final static String FD_MEAL_ITEM_02 = "37002";
	private final static String FD_MEAL_ITEM_03 = "37003";
	private final static String FD_MEAL_ITEM_04 = "37004";
	private final static String FD_MEAL_ITEM_05 = "37005";
	private TextView titileTextView;
	private TextView dateTextView;
	private TextView typeTextView;
	private String mealDate;
	private FDCustomViewPager dayNutritionViewPager;                                // 餐次标签页
	private FDCommonFragmentAdapter nutritionMealAdapter;                      // 营养餐餐次Adapter
	private LinkedHashMap<String, ArrayList<FDMealItem>> mealCategoryMap;           // 营养餐餐次信息
	private FDSchool nutritionSchool;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_student_school_meals_fragment);
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle.containsKey("student.school.key")) {
			this.nutritionSchool = bundle.getParcelable("student.school.key");
		}
		if(bundle.containsKey("student.meals.date.key")) {
			this.mealDate = bundle.getString("student.meals.date.key");
		}
		preSchoolMealView();
//		initSchoolMealView();
		
		if(bundle.containsKey("student.meals.item.key")) {
			ArrayList<FDMealItem> schoollMealItemList =  bundle.getParcelableArrayList("student.meals.item.key");
			if(schoollMealItemList!=null) {
				mealCategoryMap = new LinkedHashMap<String, ArrayList<FDMealItem>>();
				
				ArrayList<FDMealItem> categoryList_01 = new ArrayList<FDMealItem>();
				ArrayList<FDMealItem> categoryList_02 = new ArrayList<FDMealItem>();
				ArrayList<FDMealItem> categoryList_03 = new ArrayList<FDMealItem>();
				ArrayList<FDMealItem> categoryList_04 = new ArrayList<FDMealItem>();
				ArrayList<FDMealItem> categoryList_05 = new ArrayList<FDMealItem>();
				for(FDMealItem mealItem : schoollMealItemList) {
					if(mealItem==null) continue;
					if(mealItem.getType()==37001) {
						categoryList_01.add(mealItem);
					}else if(mealItem.getType()==37002) {
						categoryList_02.add(mealItem);
					}else if(mealItem.getType()==37003) {
						categoryList_03.add(mealItem);
					}else if(mealItem.getType()==37004) {
						categoryList_04.add(mealItem);
					}else if(mealItem.getType()==37005) {
						categoryList_05.add(mealItem);
					}
				}
				mealCategoryMap.put(FD_MEAL_ITEM_01, categoryList_01);
				mealCategoryMap.put(FD_MEAL_ITEM_02, categoryList_02);
				mealCategoryMap.put(FD_MEAL_ITEM_03, categoryList_03);
				mealCategoryMap.put(FD_MEAL_ITEM_04, categoryList_04);
				mealCategoryMap.put(FD_MEAL_ITEM_05, categoryList_05);
				
				initSchoolMealView();
			}
		}
	}
	
	/**
	 *  预初始化餐次视图
	 */
	public void preSchoolMealView() {
		titileTextView = (TextView) this.findViewById(R.id.fd_student_meals_table_title_textview);
		dateTextView = (TextView) this.findViewById(R.id.fd_student_meals_time_info_textview);
		typeTextView = (TextView) this.findViewById(R.id.fd_student_meals_type_textview);
		LinearLayout backLayout = (LinearLayout) this.findViewById(R.id.fd_student_meals_table_back_layout);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDStudentSchoolMealActivity.this.finish();
			}
		});
		if(nutritionSchool!=null) {
			titileTextView.setText(nutritionSchool.getSchoolName());
		}
		dateTextView.setText(mealDate);
	}
	
	/**
	 *  初始化营养餐详情餐次列表
	 *   37001 早餐     37004 上午餐
	 *   37002 午餐     37005 下午餐
	 *   37003 晚餐
	 */
	public void initSchoolMealView() {
		this.dayNutritionViewPager = (FDCustomViewPager) this.findViewById(R.id.student_meal_detail_table_view_pager);
		RadioGroup mealsGroup = (RadioGroup) this.findViewById(R.id.fd_student_meal_table_tabbar);
		
		if(mealCategoryMap==null) return;
		ArrayList<Fragment> studentMealFragemntList = new ArrayList<Fragment>();
		for(int i=0;i<mealsGroup.getChildCount();i++) {
			RadioButton nutritionMeal = (RadioButton) mealsGroup.getChildAt(i);
			nutritionMeal.setOnClickListener(new StudentMealOnClickListener(i));
			FDStudentSchoolMealFragment studentMealFragment = null;
			switch(i) {
			case 0:
				ArrayList<FDMealItem> mealItemList01 = mealCategoryMap.get(FD_MEAL_ITEM_01);
				studentMealFragment = new FDStudentSchoolMealFragment(mealItemList01);
				break;
			case 1:
				ArrayList<FDMealItem> mealItemList05 = mealCategoryMap.get(FD_MEAL_ITEM_05);
				studentMealFragment = new FDStudentSchoolMealFragment(mealItemList05);
				break;
			case 2:
				ArrayList<FDMealItem> mealItemList02 = mealCategoryMap.get(FD_MEAL_ITEM_02);
				studentMealFragment = new FDStudentSchoolMealFragment(mealItemList02);
				break;
			case 3:
				ArrayList<FDMealItem> mealItemList04 = mealCategoryMap.get(FD_MEAL_ITEM_04);
				studentMealFragment = new FDStudentSchoolMealFragment(mealItemList04);
				break;
			case 4:
				ArrayList<FDMealItem> mealItemList03 = mealCategoryMap.get(FD_MEAL_ITEM_03);
				studentMealFragment = new FDStudentSchoolMealFragment(mealItemList03);
				break;
			}
			studentMealFragemntList.add(studentMealFragment);
		}
		
		this.nutritionMealAdapter = new FDCommonFragmentAdapter(getSupportFragmentManager(), studentMealFragemntList);
		dayNutritionViewPager.setAdapter(nutritionMealAdapter);
//		dayNutritionViewPager.setCurrentItem(2);

	}
	
	
	/**
	 *  学生营养餐详情：早餐、午餐和晚餐切换监听器
	 */
	public class StudentMealOnClickListener implements View.OnClickListener {
		int clickIndex = 0;
        public StudentMealOnClickListener(int index) {
        	clickIndex = index;
        }

        public void onClick(View v) {
        	dayNutritionViewPager.setCurrentItem(clickIndex);
        	switch(clickIndex) {
			case 0:
				typeTextView.setText(R.string.student_meals_school_table_breakfast);
				break;
			case 1:
				typeTextView.setText(R.string.student_meals_school_table_moring_meal);
				break;
			case 2:
				typeTextView.setText(R.string.student_meals_school_table_lunch);
				break;
			case 3:
				typeTextView.setText(R.string.student_meals_school_table_afternoon_meal);
				break;
			case 4:
				typeTextView.setText(R.string.student_meals_school_table_dinner);
				break;
        	}
        }
    };
}
