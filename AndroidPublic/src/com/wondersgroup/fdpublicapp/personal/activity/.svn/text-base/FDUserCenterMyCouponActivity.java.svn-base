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
 * 我的优惠
 * @author chengshaohua
 * 
 */
public class FDUserCenterMyCouponActivity extends FDBaseActivity implements OnPageChangeListener{

	private LinearLayout backMainLayout;
	private FDCustomViewPager couponViewPager;
	private ArrayList<Fragment> couponFragmentList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_my_coupon);

		// 获取控件实例
		backMainLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_my_coupon_back);
		backMainLayout.setOnClickListener(backOnClickListener);
		couponViewPager = (FDCustomViewPager) findViewById(R.id.vp_fd_usercenter_my_coupon);
		RadioGroup commentGroup = (RadioGroup) findViewById(R.id.rg_fd_usercenter_my_coupon);
		commentGroup.setVisibility(View.GONE);
		for(int index=0;index<commentGroup.getChildCount();index++) {
			View commentView = commentGroup.getChildAt(index);
			if(commentView==null) return;
			commentView.setOnClickListener(new FDFragmentOnClickListener(couponViewPager, index));
		}
		
		couponFragmentList = new ArrayList<Fragment>();
		couponFragmentList.add(new FDUserCenterMyCouponFavoriteFragment());
//		couponFragmentList.add(new FDUserCenterMyCouponFavoriteFragment());
		couponViewPager.setAdapter(new FDUserFragmentAdapter(getSupportFragmentManager(),couponFragmentList));
		couponViewPager.setOnPageChangeListener(this);
		couponViewPager.setCurrentItem(0);
	}

	// 后退事件
	private OnClickListener backOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			FDUserCenterMyCouponActivity.this.onBackPressed();
		}
	};

	public void onPageScrollStateChanged(int arg0) {
	}
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	public void onPageSelected(int position) {
		if (couponFragmentList == null) return;

		Fragment fragment = couponFragmentList.get(position);
		if (fragment != null) {
			FDListViewFragment listViewFragment = (FDListViewFragment) fragment;
			listViewFragment.loadFragmentPageList(1);
		}
	}

}
