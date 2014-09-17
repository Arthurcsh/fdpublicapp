package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectAreaView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FDPickAreaWindow extends FDPopupWindow implements FDQueryConditionListener {

	// view 事件派发组件
	public FDPickAreaWindow(Context context, View view) {
		super(context);
        this.onEventView = view;
		
		initCommericalCenterView();
	}

	public FDPickAreaWindow(Context context, FDQueryConditionListener fdQueryConditionListener) {
		super(context);
        this.conditionListener = fdQueryConditionListener;
		
		initCommericalCenterView();
	}

	public void initCommericalCenterView() {
		View commericalCenterLayout = LayoutInflater.from(context).inflate(R.layout.fd_restaurant_seek_business_area, null);
		LinearLayout commericalCenterlistL = (LinearLayout) commericalCenterLayout.findViewById(R.id.fd_restaurant_seek_business_area_list_ll);
		FDSearchSelectAreaView commericalCenterView = new FDSearchSelectAreaView(context);
		if(conditionListener==null) {
			conditionListener = this;
		}
		commericalCenterView.setQueryConditionListener(conditionListener);
		commericalCenterlistL.addView(commericalCenterView);
		
		setContentWindowView(commericalCenterLayout);
		RelativeLayout backLayout = (RelativeLayout) commericalCenterLayout.findViewById(R.id.fd_restaurant_seek_business_area_rl);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDPickAreaWindow.this.dismiss();
			}
		});
	}
	
	public void onChangedConditionCallback(int viewType, Object conditionMode) {
		if(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_AREA==viewType) {
			FDCommerialCenter commerialCenter = (FDCommerialCenter) conditionMode;
			TextView commerialCenterTextView = (TextView) onEventView.findViewById(R.id.fd_restaurant_seek_center_textview);
			if(FDConst.UNLIMITED_CONDITION_KEY.equals(commerialCenter.getCommerialCode())) {
				commerialCenterTextView.setText(context.getString(R.string.search_select_commerial_center_text));
				this.onEventView.setTag(null);
			}else {
				commerialCenterTextView.setText(commerialCenter.getCommerialName());
				this.onEventView.setTag(commerialCenter);
			}
			
			this.dismiss();
		}
	}

}
