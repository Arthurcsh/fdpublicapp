package com.wondersgroup.fdpublicapp.common.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 嵌套ListView组件
 * 
 * @author chengshaohua
 * 
 */
public class FDNestListView extends ListView {

	public FDNestListView(Context context) {
		super(context);

	}

	public FDNestListView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public FDNestListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
