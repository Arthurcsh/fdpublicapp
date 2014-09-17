package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectCuisineView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FDPickCuisineWindow extends FDPopupWindow implements FDQueryConditionListener {

	// view 事件派发组件
	public FDPickCuisineWindow(Context context, View view) {
		super(context);
		this.onEventView = view;
		
		initCuisineView();
	}

	public FDPickCuisineWindow(Context context, FDQueryConditionListener fdQueryConditionListener) {
		super(context);
        this.conditionListener = fdQueryConditionListener;
		
        initCuisineView();
	}
	
	public void initCuisineView() {
		View cuisineLayout = LayoutInflater.from(context).inflate(R.layout.fd_restaurant_seek_cuisine, null);
		LinearLayout atmospherelistL = (LinearLayout) cuisineLayout.findViewById(R.id.fd_restaurant_seek_cuisine_list_ll);
		FDSearchSelectCuisineView cuisineView = new FDSearchSelectCuisineView(context);
		if(conditionListener==null) {
			conditionListener = this;
		}
		cuisineView.setQueryConditionListener(conditionListener);
		atmospherelistL.addView(cuisineView);
		
		setContentWindowView(cuisineLayout);
		RelativeLayout backLayout = (RelativeLayout) cuisineLayout.findViewById(R.id.fd_restaurant_seek_cuisine_rl);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDPickCuisineWindow.this.dismiss();
			}
		});
	}

	public void onChangedConditionCallback(int viewType, Object conditionMode) {
		if(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_CUISINE==viewType) {
			FDCuisine cuisine = (FDCuisine) conditionMode;
			TextView cuisineTextView = (TextView) onEventView.findViewById(R.id.fd_restaurant_seek_cuisine_textview);
			if(FDConst.UNLIMITED_CONDITION_KEY.equals(cuisine.getCuisine())) {
				cuisineTextView.setText(context.getString(R.string.search_select_cuisine_text));
				this.onEventView.setTag(null);
			}else {
				cuisineTextView.setText(cuisine.getCuisineValue());
				this.onEventView.setTag(cuisine);
			}
			
			this.dismiss();
		}
	}
}
