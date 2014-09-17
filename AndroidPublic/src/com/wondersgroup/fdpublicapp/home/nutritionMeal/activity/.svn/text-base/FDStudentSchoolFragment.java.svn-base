package com.wondersgroup.fdpublicapp.home.nutritionMeal.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter.FDStudentSchoolCategoryAdpter;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDCategory;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.service.FDNutritionMealWrapper;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.views.FDStudentRegionView;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 *  学生营养餐学校查询
 * @author chengshaohua
 *
 */
public class FDStudentSchoolFragment extends Fragment implements OnClickListener{
    private Activity context;
    private LayoutInflater inflater;
	private View studentSchoolQueryView;
	private LinearLayout backLayout;
	private LinearLayout searhLayout;	
	private RelativeLayout areaLayout,schoolTypeLayout;
	private TextView areaText, schoolTypeText;
	private EditText shoolNameEdit;
	private PopupWindow mPopupWindow;
	private FDCallback searchCallbackListener;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.context = this.getActivity();
		this.inflater = inflater;
				
		studentSchoolQueryView = inflater.inflate(R.layout.fd_student_meals_seek_main, null);
		initNutritionMealView();
		
		return studentSchoolQueryView;
	}	
	
	public FDStudentSchoolFragment(FDCallback searchListener) {
		this.searchCallbackListener = searchListener;
	}
	
	/**
	 *  初始化营养餐学校查询视图
	 */
	public void initNutritionMealView() {
		backLayout = (LinearLayout) studentSchoolQueryView.findViewById(R.id.fd_student_meals_search_back_layout);
		searhLayout = (LinearLayout) studentSchoolQueryView.findViewById(R.id.fd_student_meals_seek_botton_layout);		
		areaLayout = (RelativeLayout)studentSchoolQueryView.findViewById(R.id.fd_student_meals_seek_name_rll); 
		schoolTypeLayout = (RelativeLayout)studentSchoolQueryView.findViewById(R.id.fd_student_meals_seek_school_type_rll); 
		areaText = (TextView)studentSchoolQueryView.findViewById(R.id.fd_student_meals_seek_area_textview); 
		schoolTypeText = (TextView)studentSchoolQueryView.findViewById(R.id.fd_student_meals_seek_school_type_textview); 
		shoolNameEdit = (EditText)studentSchoolQueryView.findViewById(R.id.fd_student_meals_seek_school_name_edit); 
		
		backLayout.setOnClickListener(this);
		areaLayout.setOnClickListener(this);
		searhLayout.setOnClickListener(this);
		schoolTypeLayout.setOnClickListener(this);
		
		initSchoolTypePopWindow();
		FDNutritionMealWrapper nutritionMealWrapper = new FDNutritionMealWrapper(context);
		nutritionMealWrapper.getNutritionMealData();
	}
	
	public void onClick(View view) {	
		if(view == backLayout) {
			getActivity().finish();
		}else if (view == areaLayout){
			FDStudentRegionView commericalAreaWindow = new FDStudentRegionView(context, areaText,"行政区") {
				public void onChangedConditionCallback(int viewType, Object conditionMode) {					
					FDCommerialCenter commerialCenter = (FDCommerialCenter) conditionMode;
					if(FDConst.UNLIMITED_CONDITION_KEY.equals(commerialCenter.getCommerialCode())) {
						areaText.setText(R.string.student_meals_search_area_hint);
						this.onEventView.setTag(null);
					}else {
						areaText.setText(commerialCenter.getCommerialName());
						this.onEventView.setTag(commerialCenter);
					}
					this.dismiss();					
				}
			};
			commericalAreaWindow.showScreenView(areaLayout);
		} else if (view == searhLayout) {
			LinkedHashMap<String,String> searchCondition = new LinkedHashMap<String,String>();
			if(areaText.getTag()!=null) {
				FDCommerialCenter commerialCenter = (FDCommerialCenter) areaText.getTag();
				searchCondition.put(FDNutritionMealWrapper.FD_NUTRITION_PARAM_REGION_CODE, commerialCenter.getCommerialCode());
			}
			if(schoolTypeText.getTag()!=null) {
				FDCategory categorySchool = (FDCategory) schoolTypeText.getTag();
				searchCondition.put(FDNutritionMealWrapper.FD_NUTRITION_PARAM_SCHOOL_TYPE, categorySchool.getCode());
			}
			String schoolKeyword = shoolNameEdit.getText().toString().trim();
			if (!StringUtils.isEmpty(schoolKeyword)) {
				searchCondition.put(FDNutritionMealWrapper.FD_NUTRITION_PARAM_SCHOOL_NAME, schoolKeyword);
			}
			if(searchCallbackListener!=null) {
				searchCallbackListener.onCallback(searchCondition);
			}
		} else if (view == schoolTypeLayout) {
			selectSchoolTypeView(view);
		}
	}
	
	/**
	 * 初始化选择学校类型
	 */
	public void initSchoolTypePopWindow() {
		View popupView = inflater.inflate(R.layout.fd_student_school_type_popup_window, null);
		final RelativeLayout schoolLayout = (RelativeLayout) popupView.findViewById(R.id.layout_student_school_window);
        mPopupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        popupView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				float eveX = event.getX();
				float eveY = event.getY();
				float popX = schoolLayout.getX();
				float popY = schoolLayout.getY();
				float popW = schoolLayout.getWidth();
				float popH = schoolLayout.getHeight();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(eveX<popX || eveX>(popX+popW) || eveY<popY || eveY>(popY+popH)){
						mPopupWindow.dismiss();
					}
				}	
				return true;
			}
		});
    }
	
	/**
	 *  选择营养餐学校类型
	 * @param view
	 */
	public void selectSchoolTypeView(View view) {
		ListView schoolTypeListView = (ListView)mPopupWindow.getContentView().findViewById(R.id.school_type_select_list_view);
        schoolTypeListView.setCacheColorHint(Color.TRANSPARENT);
        schoolTypeListView.setVerticalScrollBarEnabled(false);
        final ArrayList<FDCategory> schoolListHash = (ArrayList<FDCategory>) ServiceManager.get(FDConst.FD_NUTRITION_DATA_SCHOOL);
        if(schoolListHash!=null) {
        	final ArrayList<FDCategory> schoolList = new ArrayList<FDCategory>();
        	FDCategory unlimitedSchool = new FDCategory();
        	unlimitedSchool.setCode(FDConst.UNLIMITED_CONDITION_KEY);
        	unlimitedSchool.setName("所有学校");
        	schoolList.add(unlimitedSchool);
        	schoolList.addAll(schoolListHash);
        	FDStudentSchoolCategoryAdpter adapter = new FDStudentSchoolCategoryAdpter(context, schoolList);
            schoolTypeListView.setAdapter(adapter);	
            schoolTypeListView.setOnItemClickListener(new OnItemClickListener() {
    			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    				FDCategory category = schoolList.get(position);
    				if(FDConst.UNLIMITED_CONDITION_KEY.equals(category.getCode())) {
    					schoolTypeText.setText(R.string.student_meals_search_school_type_hint);
	    				schoolTypeText.setTag(null);
    				}else {
    					schoolTypeText.setText(category.getName());
	    				schoolTypeText.setTag(category);
    				}
    				mPopupWindow.dismiss();
    			}
    		});
        }
		mPopupWindow.showAtLocation(view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
	}

	public void setSearchCallbackListener(FDCallback searchCallbackListener) {
		this.searchCallbackListener = searchCallbackListener;
	}
	
}
