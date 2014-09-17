package com.wondersgroup.fdpublicapp.common.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

/**
 *  ViewPager+Fragment
 *  
 * @author chengshaohua
 *
 */
public class FDFragmentOnClickListener implements OnClickListener {

	private ViewPager fragmentViewPager;
	private int clickIndex = 0;
	
	public FDFragmentOnClickListener(ViewPager viewPager, int index) {
		this.fragmentViewPager = viewPager;
		this.clickIndex = index;
	}
	
	public void onClick(View view) {
		if(fragmentViewPager!=null) {
			fragmentViewPager.setCurrentItem(clickIndex);
		}
	}

}
