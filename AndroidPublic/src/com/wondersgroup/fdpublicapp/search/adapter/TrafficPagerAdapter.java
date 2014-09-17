package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * ViewPager适配器
 */
public class TrafficPagerAdapter extends PagerAdapter {
	public List<View> mListViews;

	public TrafficPagerAdapter(List<View> mListViews) {
		this.mListViews = mListViews;
	}

	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(mListViews.get(arg1));
	}

	public void finishUpdate(View arg0) {
	}

	public int getCount() {
		return mListViews==null?0:mListViews.size();
	}

	public Object instantiateItem(View arg0, int arg1) {
		((ViewPager) arg0).addView(mListViews.get(arg1), 0);
		return mListViews.get(arg1);
	}

	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	public void restoreState(Parcelable arg0, ClassLoader arg1) {
	}

	public Parcelable saveState() {
		return null;
	}

	public void startUpdate(View arg0) {
	}
}