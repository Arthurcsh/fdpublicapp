package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNutrition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 *  营养餐成分适配器
 * @author chengshaohua
 *
 */
public class FDStudentMealNutritionAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<FDNutrition> mealNutritionList;
	
	public FDStudentMealNutritionAdapter(Context context, ArrayList<FDNutrition> mealNutritionList) {
		super();
		this.context = context;
		this.mealNutritionList = mealNutritionList;
	}
	
	public int getCount() {
		if(mealNutritionList!=null) {
			return mealNutritionList.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(mealNutritionList!=null) {
			return mealNutritionList.get(position);
		}
		return null;
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.fd_student_school_meal_nutrition_item, null);
		TextView nutritionName = (TextView) convertView.findViewById(R.id.student_meals_nutrition_component_name_textview);
		TextView nutritionValue = (TextView) convertView.findViewById(R.id.student_meals_nutrition_component_value_textview);
		if(mealNutritionList!=null) {
			FDNutrition mealNutrition = mealNutritionList.get(position);
			if(mealNutrition!=null) {
				nutritionName.setText(mealNutrition.getKey());
				nutritionValue.setText(mealNutrition.getValue()+" g");
			}
		}
		
		return convertView;
	}

}
