package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDStudentSchoolMealAdapter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealItem;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 *  营养餐餐次菜肴信息
 * @author chengshaohua
 *
 */
public class FDStudentSchoolMealFragment extends Fragment {

	public Context context;
	public View studentMealTableView;
	public ListView mealListView;
	public ArrayList<FDMealItem> mealItemList;
	public FDStudentSchoolMealAdapter studentMealAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.context = this.getActivity();
				
		studentMealTableView = inflater.inflate(R.layout.fd_student_school_meals_table, null);
		initNutritionMealView();
		
		return studentMealTableView;
	}	
	
	public FDStudentSchoolMealFragment() {
		super();
	}
	
	public FDStudentSchoolMealFragment(ArrayList<FDMealItem> mealItemList) {
		super();
		this.mealItemList = mealItemList;
	}
	
	/**
	 *  初始化营养餐餐次详情列表
	 */
	public void initNutritionMealView() {
		TextView messageTextView = (TextView) studentMealTableView.findViewById(R.id.fd_school_meal_table_message_textview);
		mealListView = (ListView) studentMealTableView.findViewById(R.id.student_school_meal_table_listView);
		mealListView.setCacheColorHint(Color.TRANSPARENT);
		
		if(mealItemList!=null) {
			messageTextView.setVisibility(View.GONE);
			studentMealAdapter = new FDStudentSchoolMealAdapter(context, mealItemList);
			mealListView.setAdapter(studentMealAdapter);
			mealListView.setOnItemClickListener(new OnItemClickListener(){
				public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
					FDMealItem mealItem = mealItemList.get(position);
					if(mealItem==null) return;
					Intent intent = new Intent(context, FDStudentSchoolMealDetailActivity.class);
					intent.putExtra("student.meal.key", mealItem.getId());
					context.startActivity(intent);
				}
			});
		}else {
			messageTextView.setVisibility(View.VISIBLE);
		}
	}
}
