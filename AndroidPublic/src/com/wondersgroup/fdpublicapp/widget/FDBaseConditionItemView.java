package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Chengshaohua
 *
 */
public class FDBaseConditionItemView extends LinearLayout {

	public final static int QUERY_RESTAURANT_CONDITION_AREA       = 0x0001;
	public final static int QUERY_RESTAURANT_CONDITION_CUISINE    = 0x1002;
	public final static int QUERY_RESTAURANT_CONDITION_CREDIT     = 0x0003;
	public final static int QUERY_RESTAURANT_CONDITION_ATMOSPHERE = 0x0004;
	public final static int QUERY_RESTAURANT_CONDITION_DISTANCE   = 0x0005;
	public final static int QUERY_RESTAURANT_CONDITION_OTHERS     = 0x0006;
	
	protected FDQueryConditionListener conditionChangedListener;
	
	public FDBaseConditionItemView(Context context) {
		super(context);
		
	}
	
	public FDBaseConditionItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public FDBaseConditionItemView(Context context, AttributeSet attrs,int defStyle) {
		super(context, attrs, defStyle);
		
	}
	
	public void setQueryConditionListener(FDQueryConditionListener conditionChangedListener) {
		this.conditionChangedListener = conditionChangedListener;
	}

	public void setViewVisible(int visible) {
		this.setVisibility(visible);
	}
	
	public void dissmissView() {
		this.setVisibility(View.GONE);
	}
}
