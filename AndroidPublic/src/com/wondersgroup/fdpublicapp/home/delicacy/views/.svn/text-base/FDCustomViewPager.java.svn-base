package com.wondersgroup.fdpublicapp.home.delicacy.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class FDCustomViewPager extends ViewPager {
	private boolean isPagingEnabled;

	public FDCustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.isPagingEnabled = false;
	}

	public void setCurrentItem(int item) {
		super.setCurrentItem(item);
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (this.isPagingEnabled) {
			return super.onTouchEvent(event);
		}
		return false;
	}
	
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (this.isPagingEnabled) {
			return super.onInterceptTouchEvent(event);
		}
		return false;
	}

	public void setPagingEnabled(boolean b) {
		this.isPagingEnabled = b;
	}
}