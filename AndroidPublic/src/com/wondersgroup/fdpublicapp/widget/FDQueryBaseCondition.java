package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDMultiConditionListener;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectAreaView;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectCreditView;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectCuisineView;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectDistanceView;
import com.wondersgroup.fdpublicapp.search.views.FDSearchSelectOthersView;
import com.wondersgroup.fdpublicapp.search.views.FDSelectConditionWindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RadioGroup;

public abstract class FDQueryBaseCondition extends LinearLayout implements OnClickListener {

	public Context context;
	public ImageView areaDevideImage;
	public ImageView distanceDevideImage;
	public CheckedTextView areaButton;
	public CheckedTextView cuisineButton;
	public CheckedTextView creditButton;
	public CheckedTextView distanceButton;
	public CheckedTextView othersButton;
	public FDSearchSelectAreaView searchAreaView;
	public FDSearchSelectCuisineView searchCuisineView;
	public FDSearchSelectCreditView searchCreditView;
	public FDSearchSelectDistanceView searchDistanceView;
	public FDSearchSelectOthersView searchOthersView;
	public LinearLayout selectViewLayout;
	public RadioGroup selectViewRadioGroup;
	protected FDMultiConditionListener queryConditionListener;
	protected FDSelectConditionWindow selectConditionWindow;
	
	public FDQueryBaseCondition(Context context) {
		super(context);
		this.context = context;
		
		initConditionView();
	}
	
	public FDQueryBaseCondition(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		
	}
	
	public FDQueryBaseCondition(Context context, AttributeSet attrs,int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		
//		initConditionView();
	}
	
	public void initConditionView() {
		View queryConditionView = LayoutInflater.from(context).inflate(R.layout.fd_search_condiction, null);
		areaDevideImage = (ImageView) queryConditionView.findViewById(R.id.fd_query_condition_area_devide);
		distanceDevideImage = (ImageView) queryConditionView.findViewById(R.id.fd_query_condition_distance_devide);
		areaButton = (CheckedTextView) queryConditionView.findViewById(R.id.fd_query_condition_area);
		cuisineButton = (CheckedTextView) queryConditionView.findViewById(R.id.fd_query_condition_cuisine);
		creditButton = (CheckedTextView) queryConditionView.findViewById(R.id.fd_query_condition_credit);
		distanceButton = (CheckedTextView) queryConditionView.findViewById(R.id.fd_query_condition_distance);
		othersButton = (CheckedTextView) queryConditionView.findViewById(R.id.fd_query_condition_others);
		
		selectViewLayout = (LinearLayout) queryConditionView.findViewById(R.id.fd_query_condition_layout);
		selectViewRadioGroup = (RadioGroup) queryConditionView.findViewById(R.id.fd_query_condition_tabbar);
		
		searchAreaView = new FDSearchSelectAreaView(context);
		searchCuisineView = new FDSearchSelectCuisineView(context);
		searchCreditView = new FDSearchSelectCreditView(context);
		searchDistanceView = new FDSearchSelectDistanceView(context);
		searchOthersView = new FDSearchSelectOthersView(context);
		
		areaButton.setOnClickListener(this);
		cuisineButton.setOnClickListener(this);
		creditButton.setOnClickListener(this);
		distanceButton.setOnClickListener(this);    // 设置距离
		othersButton.setOnClickListener(this);
		
		addView(queryConditionView);
	}

	public void onClick(View view) {
		if(view==areaButton) {
			areaButton.setChecked(!areaButton.isChecked());
			if(areaButton.isChecked()) {
				selectConditionWindow = new FDSelectConditionWindow(context, searchAreaView);
				selectConditionWindow.showConditionView(areaButton);
			}
		}else if(view==cuisineButton) {
			cuisineButton.setChecked(!cuisineButton.isChecked());
			if(cuisineButton.isChecked()) {
				selectConditionWindow = new FDSelectConditionWindow(context, searchCuisineView);
				selectConditionWindow.showConditionView(cuisineButton);
			}
		}else if(view==creditButton) {
			creditButton.setChecked(!creditButton.isChecked());
			if (creditButton.isChecked()) {
				selectConditionWindow = new FDSelectConditionWindow(context, searchCreditView);
				selectConditionWindow.showConditionView(creditButton);
			}
		}else if(view==distanceButton) {
			distanceButton.setChecked(!distanceButton.isChecked());
			if (distanceButton.isChecked()) {
				selectConditionWindow = new FDSelectConditionWindow(context, searchDistanceView);
				selectConditionWindow.showConditionView(distanceButton);
			}
		}else if(view==othersButton) {
			othersButton.setChecked(!othersButton.isChecked());
			if (othersButton.isChecked()) {
				selectConditionWindow = new FDSelectConditionWindow(context, searchOthersView);
				selectConditionWindow.showConditionView(othersButton);
			}
		}
		initConditionEventListener();
	}

	public void initConditionEventListener() {
		if(selectConditionWindow==null) return;
		selectConditionWindow.setOnDismissListener(new OnDismissListener(){
			public void onDismiss() {
				setSelcetConditionDefault();
			}
		});
	}
	
	// 设置默认的选择状态
	public void setSelcetConditionDefault() {
		if(selectViewRadioGroup==null) return;
		for(int n=0;n<selectViewRadioGroup.getChildCount();n++) {
			if(selectViewRadioGroup.getChildAt(n).getClass().equals(CheckedTextView.class)) {
				CheckedTextView checkView = (CheckedTextView) selectViewRadioGroup.getChildAt(n);
//				if(checkView==view) continue;
				checkView.setChecked(false);
			}
		}
	}
	
	public void setQueryConditionListener(FDMultiConditionListener queryConditionListener) {
		this.queryConditionListener = queryConditionListener;
	}
}
