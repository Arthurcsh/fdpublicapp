package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.search.mode.FDAtmosphere;
import com.wondersgroup.fdpublicapp.search.views.FDSearchAtmosphereView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FDPickAtmosphereWindow extends FDPopupWindow implements FDQueryConditionListener{

	// view 事件派发组件
	public FDPickAtmosphereWindow(Context context, View view) {
		super(context);
		this.onEventView = view;
		
		initAtmosphereView();
	}
	
	public FDPickAtmosphereWindow(Context context, FDQueryConditionListener fdQueryConditionListener) {
		super(context);
        this.conditionListener = fdQueryConditionListener;
		
        initAtmosphereView();
	}
	
	public void initAtmosphereView() {
		View atmosphereLayout = LayoutInflater.from(context).inflate(R.layout.fd_restaurant_seek_atmosphere, null);
		LinearLayout atmospherelistL = (LinearLayout) atmosphereLayout.findViewById(R.id.fd_restaurant_seek_atmosphere_list_ll);
		FDSearchAtmosphereView atmosphereView = new FDSearchAtmosphereView(context);
		if(conditionListener==null) {
			conditionListener = this;
		}
		atmosphereView.setQueryConditionListener(conditionListener);
		atmospherelistL.addView(atmosphereView);
		
		setContentWindowView(atmosphereLayout);
		RelativeLayout backLayout = (RelativeLayout) atmosphereLayout.findViewById(R.id.fd_restaurant_seek_atmosphere_rl);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDPickAtmosphereWindow.this.dismiss();
			}
		});
	}

	public void onChangedConditionCallback(int viewType, Object conditionMode) {
		if(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_ATMOSPHERE==viewType) {
			FDAtmosphere atmosphere = (FDAtmosphere) conditionMode;
			TextView atmosphereTextView = (TextView) onEventView.findViewById(R.id.fd_restaurant_seek_atmosphere_textview);
			if(FDConst.UNLIMITED_CONDITION_KEY.equals(atmosphere.getAtmosphereCode())) {
				atmosphereTextView.setText(context.getString(R.string.search_select_atmosphere_text));
				this.onEventView.setTag(null);
			}else {
				atmosphereTextView.setText(atmosphere.getAtmosphereName());
				this.onEventView.setTag(atmosphere);
			}
			
			this.dismiss();
		}
		
	}

}
