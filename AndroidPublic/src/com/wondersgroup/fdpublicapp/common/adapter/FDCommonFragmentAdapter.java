package com.wondersgroup.fdpublicapp.common.adapter;

import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 *  公用FragmentAdpater
 * @author chengshaohua
 *
 */
public class FDCommonFragmentAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> studentMealFramgentList;
	
	public FDCommonFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public FDCommonFragmentAdapter(FragmentManager fm, ArrayList<Fragment> studentMealFramgentList) {
		super(fm);
		this.studentMealFramgentList = studentMealFramgentList;
	}
	
	public Fragment getItem(int position) {
		if(studentMealFramgentList!=null) {
			return studentMealFramgentList.get(position);
		}
		return null;
	}

	public int getCount() {
		if(studentMealFramgentList!=null) {
			return studentMealFramgentList.size();
		}
		return 0;
	}

    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
