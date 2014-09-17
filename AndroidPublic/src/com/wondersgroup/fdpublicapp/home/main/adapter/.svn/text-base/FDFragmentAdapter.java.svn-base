package com.wondersgroup.fdpublicapp.home.main.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 *  Fragment适配器 
 * @author chengshaohua
 *
 */
public class FDFragmentAdapter extends FragmentPagerAdapter {

	private FDBaseActivity activity;
	private FragmentManager fragmentManager;
	private ArrayList<FDSearchFragment> fragmentsList;
 
    public FDFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
    }

    public FDFragmentAdapter(FDBaseActivity context, FragmentManager fragmentManager, ArrayList<FDSearchFragment> fragments) {
        super(fragmentManager);
        this.activity = context;
        this.fragmentsList = fragments;
        this.fragmentManager = fragmentManager;
    }

    public int getCount() {
        return fragmentsList.size();
    }

    public FDSearchFragment getItem(int index) {
        return fragmentsList.get(index);
    }
 
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


    public void exchangeSearchFragment(int position)	{
    	if(fragmentsList==null) return;
    	FDSearchFragment currentFragment = fragmentsList.get(position);
		currentFragment.initRestaurantListView();
    }
}
