package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDFragmentOnClickListener;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDCustomViewPager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.personal.adapter.FDUserFragmentAdapter;

/**
 * 我的推荐
 * @author chengshaohua
 * 
 */
public class FDUserCenterMyRecommendActivity extends FDBaseActivity implements OnPageChangeListener{

	private LinearLayout backMainLayout;
	private FDCustomViewPager recommendViewPager;
	private ArrayList<Fragment> recommendFragmentList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_my_recommend);

		// 获取控件实例
		backMainLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_my_recommend_back);
		backMainLayout.setOnClickListener(backOnClickListener);
		recommendViewPager = (FDCustomViewPager) findViewById(R.id.vp_fd_usercenter_my_recommend);
		RadioGroup commentGroup = (RadioGroup) findViewById(R.id.rg_fd_usercenter_my_recommend);
		for(int i=0;i<commentGroup.getChildCount();i++) {
			View commentView = commentGroup.getChildAt(i);
			if(commentView==null) return;
			commentView.setOnClickListener(new FDFragmentOnClickListener(recommendViewPager, i));
		}
		
		recommendFragmentList = new ArrayList<Fragment>();
		recommendFragmentList.add(new FDUserCenterMyRecommendRestFragment());
		recommendFragmentList.add(new FDUserCenterMyRecommendDishFragment());
		recommendViewPager.setAdapter(new FDUserFragmentAdapter(getSupportFragmentManager(),recommendFragmentList));
		recommendViewPager.setOnPageChangeListener(this);
		recommendViewPager.setCurrentItem(0);
	}

	// 后退事件
	private OnClickListener backOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			FDUserCenterMyRecommendActivity.this.onBackPressed();
		}
	};

	public void onPageScrollStateChanged(int arg0) {
	}
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	public void onPageSelected(int position) {
		if (recommendFragmentList == null) return;

		Fragment fragment = recommendFragmentList.get(position);
		if (fragment != null) {
			FDListViewFragment listViewFragment = (FDListViewFragment) fragment;
			listViewFragment.loadFragmentPageList(1);
		}
	}

}
