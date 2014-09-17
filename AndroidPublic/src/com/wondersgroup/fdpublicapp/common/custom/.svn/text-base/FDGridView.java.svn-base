package com.wondersgroup.fdpublicapp.common.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * ScrollView 和 GridView 或ExpandableListView显示冲突问题
 * 
 * @author chengshaohua
 */
public class FDGridView extends GridView {
	public FDGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FDGridView(Context context) {
		super(context);
	}

	public FDGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
