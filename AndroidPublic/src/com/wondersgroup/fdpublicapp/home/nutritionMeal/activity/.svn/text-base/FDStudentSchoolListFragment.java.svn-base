package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.List;
import java.util.Map;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDStudentSchoolAdpter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDSchool;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 *  学生营养餐学校列表
 * @author chengshaohua
 *
 */
public class FDStudentSchoolListFragment extends Fragment {
    private Activity context;
    private LayoutInflater inflater;
	private View studentSchoolListView;
	private LinearLayout backLayout;
	private ListView schoolListView;
	private List<FDSchool> nutritionSchoolsList;
	private FDCallback searchCallbackListener;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.context = this.getActivity();
		this.inflater = inflater;
		
		studentSchoolListView = inflater.inflate(R.layout.fd_student_school_list, null);
		initStudentSchoolView();
		
		return studentSchoolListView;
	}	
	
	public FDStudentSchoolListFragment(FDCallback searchListener) {
		this.searchCallbackListener = searchListener;
	}
	
	/**
	 *  初始化学校列表
	 */
	public void initStudentSchoolView() {
		backLayout = (LinearLayout) studentSchoolListView.findViewById(R.id.fd_student_meals_search_school_list_back_layout);
		schoolListView = (ListView) studentSchoolListView.findViewById(R.id.fd_student_school_list_view);
		schoolListView.setCacheColorHint(Color.TRANSPARENT);
		backLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if(searchCallbackListener!=null) {
					if(nutritionSchoolsList!=null) {
						nutritionSchoolsList.clear();
					}
					searchCallbackListener.onCallback(null);
				}
			}
		});
	}
	
	public void setSchoolList() {
		if(nutritionSchoolsList!=null) {
			FDStudentSchoolAdpter adapter = new FDStudentSchoolAdpter(context,nutritionSchoolsList);
			schoolListView.setAdapter(adapter);
			schoolListView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					FDSchool school = nutritionSchoolsList.get(position);
//					Intent intent = new Intent(context,FDStudentSchoolDetailActivity.class);
//					intent.putExtra("student.school.key", school);
//					startActivity(intent);
					if(searchCallbackListener!=null) {
						searchCallbackListener.onCallback(school);
					}
				}
			});
		}
	}
	
	/**
	 * 根据条件查询营养餐学校
	 */
	public void loadStudentSchools(Map<String,String> conditionMap) {
		if(conditionMap==null) return;
		
		String schoolRegion = null;
		String schoolType = null;
		String schoolName = null;
		if(conditionMap.containsKey(FDNutritionMealWrapper.FD_NUTRITION_PARAM_REGION_CODE)) {
			schoolRegion = conditionMap.get(FDNutritionMealWrapper.FD_NUTRITION_PARAM_REGION_CODE);
		}
		if(conditionMap.containsKey(FDNutritionMealWrapper.FD_NUTRITION_PARAM_SCHOOL_TYPE)) {
			schoolType = conditionMap.get(FDNutritionMealWrapper.FD_NUTRITION_PARAM_SCHOOL_TYPE);
		}
		if(conditionMap.containsKey(FDNutritionMealWrapper.FD_NUTRITION_PARAM_SCHOOL_NAME)) {
			schoolName = conditionMap.get(FDNutritionMealWrapper.FD_NUTRITION_PARAM_SCHOOL_NAME);
		}
		
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(context);
		nutritionMealWrapper.getNutritionMealSchool(-1, -1, schoolRegion, schoolType, schoolName, new FDCallback() {
			public void onCallback(Object callback) {
				FDResultPage<FDSchool> nutritionSchoolsPage = (FDResultPage<FDSchool>) callback;
				if(nutritionSchoolsPage!=null) {
					nutritionSchoolsList = nutritionSchoolsPage.getResultList();
				}
				setSchoolList();
			}
		});
	}
}
