package com.wondersgroup.fdpublicapp.home.nutritionMeal.views;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectAreaView;
import com.wondersgroup.fdpublicapp.widget.FDPickAreaWindow;

/**
 *  营养餐学校区域选择
 * @author chengshaohua
 *
 */
public class FDStudentRegionView extends FDPickAreaWindow {

	private TextView headerTextView;
	private String headerTitle;
	
	public FDStudentRegionView(Context context, View view) {
		super(context, view);
		
		initCommericalCenterView();
	}

	public FDStudentRegionView(Context context, View view, String title) {
		super(context, view);
		headerTitle = title;
		
		initCommericalCenterView();
	}
	/**
	 * 重写区域选择视图
	 */
	public void initCommericalCenterView() {
		View commericalCenterLayout = LayoutInflater.from(context).inflate(R.layout.fd_restaurant_seek_business_area, null);
		LinearLayout commericalCenterlistL = (LinearLayout) commericalCenterLayout.findViewById(R.id.fd_restaurant_seek_business_area_list_ll);
		headerTextView = (TextView) commericalCenterLayout.findViewById(R.id.fd_restaurant_seek_business_area_textview);
		ArrayList<FDCommerialCenter> adminRegions = (ArrayList<FDCommerialCenter>) ServiceManager.get(FDConst.FD_NUTRITION_DATA_REGION);
		FDSearchSelectAreaView commericalCenterView = new FDSearchSelectAreaView(context, adminRegions);
		
		if(headerTitle!=null) {
			headerTextView.setText(headerTitle);
		}
		if(conditionListener==null) {
			conditionListener = this;
		}
		commericalCenterView.setQueryConditionListener(conditionListener);
		commericalCenterlistL.addView(commericalCenterView);
		
		setContentWindowView(commericalCenterLayout);
		RelativeLayout backLayout = (RelativeLayout) commericalCenterLayout.findViewById(R.id.fd_restaurant_seek_business_area_rl);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDStudentRegionView.this.dismiss();
			}
		});
	}
	
	public FDStudentRegionView(Context context, FDQueryConditionListener fdQueryConditionListener) {
		super(context, fdQueryConditionListener);
	}

}
