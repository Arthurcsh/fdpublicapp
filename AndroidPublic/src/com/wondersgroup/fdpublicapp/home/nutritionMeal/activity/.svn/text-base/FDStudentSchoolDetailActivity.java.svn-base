package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealItem;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDSchool;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.views.FDStudentCalendarView;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.views.FDStudentCalendarView.NutritionMealCalendarInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 *  营养餐学校餐次详情
 * @author chengshaohua
 *
 */
public class FDStudentSchoolDetailActivity  extends FDBaseActivity implements OnClickListener {
	private LinearLayout backLayout;
	private LinearLayout collectLayout;
	private TextView schoolNameHeaderView;
	private TextView schoolNameDetailView;
	private TextView schoolRegionView;
	private TextView schoolTypeView;
	private ImageView schoolImageView;
	private ImageView schoolCollectView;
	private FDStudentCalendarView nutritionCalendarView;
	private FDSchool nutritionSchool;
	private LinkedHashMap<String, ArrayList<FDMealItem>> nutritionMealList;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_student_school_detail);
		
		Bundle bundle = getIntent().getExtras();  
		if(bundle.containsKey("student.school.key")) {
			nutritionSchool =  bundle.getParcelable("student.school.key");
		}
		
		loadSchoolDetailData(StringUtils.dateToString2(new Date()));
		initSchoolDetailView();
	}
	
	/**
	 *  初始化营养餐学校信息
	 */
	public void initSchoolDetailView() {
		backLayout = (LinearLayout) findViewById(R.id.fd_student_meals_school_detail_back_layout);
		collectLayout = (LinearLayout) findViewById(R.id.fd_student_meals_school_detail_collect_layout);
		schoolNameHeaderView = (TextView) findViewById(R.id.fd_student_meals_school_detail_name_text);
		schoolNameDetailView = (TextView) findViewById(R.id.fd_student_meals_school_detail_name_label);
		schoolRegionView = (TextView) findViewById(R.id.fd_student_meals_school_detail_erea_text);
		schoolTypeView = (TextView) findViewById(R.id.fd_student_meals_school_detail_type_text);
		schoolImageView = (ImageView) findViewById(R.id.fd_student_meals_school_detail_picture_image);
		schoolCollectView = (ImageView) findViewById(R.id.fd_student_meals_school_detail_collect_enable);
		nutritionCalendarView = (FDStudentCalendarView) findViewById(R.id.fd_student_meals_school_detail_calendar_view);
		nutritionCalendarView.setNutritionMealCalendarListener(new NutritionMealCalendarInterface() {
			public void onChangeMonthMeal(Calendar calendar) {
				if(calendar==null) return;
				loadSchoolDetailData(StringUtils.dateToString2(calendar.getTime()));
			}
			public void onCalendarSelected(String onSelected, Calendar calendar) {
				if(nutritionMealList!=null) {
					ArrayList<FDMealItem> mealItemList = nutritionMealList.get(onSelected);
					Intent intent = new Intent(FDStudentSchoolDetailActivity.this, FDStudentSchoolMealActivity.class);
					intent.putExtra("student.meals.date.key", onSelected);
					intent.putExtra("student.school.key", nutritionSchool);
					intent.putParcelableArrayListExtra("student.meals.item.key", mealItemList);
					FDStudentSchoolDetailActivity.this.startActivity(intent);
				}
			}
		});
		backLayout.setOnClickListener(this);
		collectLayout.setOnClickListener(this);
		
		setStudentSchoolView();
	}

	public void setStudentSchoolView() {
		if(nutritionSchool!=null) {
			schoolNameHeaderView.setText(nutritionSchool.getSchoolName());
			schoolNameDetailView.setText(nutritionSchool.getSchoolName());
			schoolRegionView.setText(nutritionSchool.getRegionValue());
			schoolTypeView.setText(nutritionSchool.getSchoolTypeValue());
			if(nutritionSchool.getIsCollected()==0) {
				schoolCollectView.setImageResource(R.drawable.fd_restaurant_collection_enable);
			}else {
				schoolCollectView.setImageResource(R.drawable.fd_restaurant_collection_disable);
			}
			ArrayList<FDImage> schoolImages = nutritionSchool.getAttachList();
			if(schoolImages!=null && schoolImages.size()>0) {
				FDImage schoolImage = schoolImages.get(0);
				FDViewUtil.showServerImage(context, schoolImageView, schoolImage.getFilePath());
			}
		}
	}
	
	/**
	 *  预加载当月学校营养餐信息
	 */
	public void loadSchoolDetailData(String mealDate) {
		if (nutritionSchool == null) return;
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(this);
		nutritionMealWrapper.getSchoolNutritionMealInfoByMonth(""+nutritionSchool.getCompanyId(), mealDate, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				nutritionMealList = (LinkedHashMap<String, ArrayList<FDMealItem>>) callback;
				setNutritionMealItem();
			}
		});
	}
	
	/**
	 *  根据获取的营养餐日设置日历
	 */
	public void setNutritionMealItem() {
		if(nutritionMealList==null) return;
		
		ArrayList<String> mealDays = new ArrayList<String>();
		for(Map.Entry<String, ArrayList<FDMealItem>> entry : nutritionMealList.entrySet()) {
			if(entry==null) continue;
			mealDays.add(entry.getKey());
		}
		nutritionCalendarView.setMealDays(mealDays);
	}
	
	public void onClick(View view) {
		if (view == backLayout) {
			Intent intent = new Intent();
			setResult(FDNutritionMealWrapper.FD_NUTRITION_SCHOOL_DETAIL, intent);
			finish();
		}else if(view == collectLayout) {
			performSchoolCollection();
		}
	}
	
	/**
	 *  收藏学校
	 */
	public void performSchoolCollection() {
		if(nutritionSchool==null) return;
		int enable = nutritionSchool.getIsCollected()==0?1:0;
				
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(this);
		nutritionMealWrapper.collectNutritionMealSchool(nutritionSchool.getCompanyId(), enable, new FDCallback(){
			public void onCallback(Object callback) {
				int collectStatus = (Integer) callback;
				ImageView collectView = new ImageView(context);
				collectView.setImageResource(R.drawable.fd_restaurant_favorites);
				if(collectStatus==0) {
					nutritionSchool.setIsCollected(nutritionSchool.getIsCollected()==0?1:0);
					
					if(nutritionSchool.getIsCollected()==0) {
						schoolCollectView.setImageResource(R.drawable.fd_restaurant_collection_enable);
						FDViewUtil.showToast(context, collectView, "取消收藏");
					}else {
						schoolCollectView.setImageResource(R.drawable.fd_restaurant_collection_disable);
						FDViewUtil.showToast(context, collectView, "已收藏");
					}
					
				}
			}
		});
		
	}
}
