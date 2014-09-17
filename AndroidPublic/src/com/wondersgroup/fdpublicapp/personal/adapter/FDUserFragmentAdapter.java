package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 *  个人中心FragmentAdpater
 * @author chengshaohua
 *
 */
public class FDUserFragmentAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> framgentList;
	
	public FDUserFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public FDUserFragmentAdapter(FragmentManager fm, ArrayList<Fragment> studentMealFramgentList) {
		super(fm);
		this.framgentList = studentMealFramgentList;
	}
	
	public Fragment getItem(int position) {
		if(framgentList!=null) {
			return framgentList.get(position);
		}
		return null;
	}

	public int getCount() {
		if(framgentList!=null) {
			return framgentList.size();
		}
		return 0;
	}

    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
