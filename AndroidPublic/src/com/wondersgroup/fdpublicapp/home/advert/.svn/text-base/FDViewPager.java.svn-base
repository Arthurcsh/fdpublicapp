package com.wondersgroup.fdpublicapp.home.advert;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * @author chengshaohua
 *
 */
public class FDViewPager extends ViewPager {
	private ViewPager viewPager;

	public FDViewPager(Context paramContext) {
		this(paramContext, null);
	}

	public FDViewPager(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
		if (this.viewPager != null)
			this.viewPager.requestDisallowInterceptTouchEvent(true);
		return super.dispatchKeyEvent(paramKeyEvent);
	}

	public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
		if (this.viewPager != null)
			this.viewPager.requestDisallowInterceptTouchEvent(true);
		return super.onInterceptTouchEvent(paramMotionEvent);
	}

	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		if (this.viewPager != null)
			this.viewPager.requestDisallowInterceptTouchEvent(true);
		return super.onTouchEvent(paramMotionEvent);
	}

	public void setPagerView(ViewPager paramViewPager) {
		this.viewPager = paramViewPager;
	}
}
