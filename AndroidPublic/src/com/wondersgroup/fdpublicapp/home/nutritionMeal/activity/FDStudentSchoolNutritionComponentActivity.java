package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDStudentMealNutritionAdapter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealInfo;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealItem;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNutrition;

/**
 *  营养餐成分列表
 * @author chengshaohua
 *
 */
public class FDStudentSchoolNutritionComponentActivity extends FDBaseActivity {
	
	private FDMealInfo mealInfo;
	private FDStudentMealNutritionAdapter mealNutritionAdapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_student_school_meal_nutrition_component);
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle.containsKey("student.meal.nutrition.key")) {
			mealInfo =  bundle.getParcelable("student.meal.nutrition.key");
		}
		
		initMealNutritionComponentView();
	}
	
	public void initMealNutritionComponentView() {
		LinearLayout backLayout = (LinearLayout) this.findViewById(R.id.student_meals_nutrition_component_back_layout);
		ImageView mealImageView = (ImageView) this.findViewById(R.id.student_meals_nutrition_component_photo_img);
		TextView mealHeaderTextView = (TextView) this.findViewById(R.id.student_meals_nutrition_component_time_info_textview);
		TextView mealNameTextView = (TextView) this.findViewById(R.id.student_meals_menu_name_textview);
		TextView mealCompanyTextView = (TextView) this.findViewById(R.id.student_meals_company_name_textview);
		ListView mealNutritionListView = (ListView) this.findViewById(R.id.student_meals_nutrition_component_table_xml);
		
		if(mealInfo!=null) {
			FDMealItem mealItem = mealInfo.getStuMealInfo();
			ArrayList<FDImage> mealImages = mealInfo.getPicList();
			if(mealItem!=null) {
				mealHeaderTextView.setText(mealItem.getName());
				mealNameTextView.setText(mealItem.getName());
				mealCompanyTextView.setText(mealItem.getCompanyName());
			}
			if(mealImages!=null && mealImages.size()>0) {
				FDImage mealImage = mealImages.get(0);
				if(mealImage!=null) {
					FDViewUtil.showServerImage(this, mealImageView, mealImage.getFilePath());
				}
			}
			ArrayList<FDNutrition> nutritionList = mealInfo.getNutritionList();
			mealNutritionAdapter = new FDStudentMealNutritionAdapter(this,nutritionList);
			mealNutritionListView.setAdapter(mealNutritionAdapter);
		}
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDStudentSchoolNutritionComponentActivity.this.finish();
			}
		});
	}
}
