package com.wondersgroup.fdpublicapp.home.advert;

import java.util.List;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author chengshaohua
 *  页适配器
 */
public class FDViewPagerAdapter extends PagerAdapter{
	
	private List<View> safetyViews;
	
	public FDViewPagerAdapter(List<View> views) {
		safetyViews = views;
	}
	
	public int getCount() {
		if(safetyViews==null) return 0;
		return safetyViews.size();
	}

	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	public Object instantiateItem(ViewGroup container, int position) {
		if(safetyViews==null) return null;
		
		container.addView(safetyViews.get(position));
		return safetyViews.get(position);
	}

	public void destroyItem(ViewGroup container, int position, Object object) {
		if(safetyViews==null) return;
		
		container.removeView(safetyViews.get(position));
	}

}
