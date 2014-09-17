package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;

/**
 * @author mengke
 * 
 */
public class FDUserCenterLostPwActivity extends FDBaseActivity {

	private LinearLayout backMainLayout;
	private FragmentManager fragmentManager;
	private RadioGroup radioGroup;
	private RadioButton secRadioButton;
	private RadioButton phoneRadioButton;
	private ViewPager viewPager;
	// ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
	private Fragment fragmentTab1;
	private Fragment fragmentTab2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_lost_pw);

		// 获取控件实例
		backMainLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_login_back);
		fragmentManager = getSupportFragmentManager();
		radioGroup = (RadioGroup) findViewById(R.id.rg_fd_usercenter_lost_pw);
		secRadioButton = (RadioButton) findViewById(R.id.rb_fd_usercenter_lost_pw_sec);
		phoneRadioButton = (RadioButton) findViewById(R.id.rb_fd_usercenter_lost_pw_phone);

		// 给控件绑定事件
		backMainLayout.setOnClickListener(backOnClickListener);
		secRadioButton.setOnClickListener(secRbOnClickListener);
		phoneRadioButton.setOnClickListener(phoneRbOnClickListener);

		viewPager = (ViewPager) findViewById(R.id.vp_fd_usercenter_lost_pw);
		// fragmentList.add(new
		// FDUserCenterLostPwSecAccountFragment(viewPager));
		// fragmentList.add(new FDUserCenterLostPwPhoneFragment());
		viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
				null));
		viewPager.setOnPageChangeListener(viewPagerOnPageChangeListener);
	}

	private OnPageChangeListener viewPagerOnPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// radioGroup.clearCheck();
			radioGroup.check(arg0 > 0 ? R.id.rb_fd_usercenter_lost_pw_phone
					: R.id.rb_fd_usercenter_lost_pw_sec);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};

	// 安全问题找回RadioButton
	private OnClickListener secRbOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			radioGroup.check(R.id.rb_fd_usercenter_lost_pw_sec);
			viewPager.setCurrentItem(0);
			/*
			 * myPagerAdapter adapter = new
			 * myPagerAdapter(getSupportFragmentManager(), null);
			 * viewPager.setAdapter(adapter); adapter.notifyDataSetChanged();
			 */
		}
	};
	// 手机号找回RadioButton
	private OnClickListener phoneRbOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			radioGroup.check(R.id.rb_fd_usercenter_lost_pw_phone);
			viewPager.setCurrentItem(1);
		}
	};

	// 后退事件
	private OnClickListener backOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			FDUserCenterLostPwActivity.this.onBackPressed();
		}
	};

	/**
	 * adapter
	 * 
	 * @author
	 */
	class myPagerAdapter extends FragmentPagerAdapter {

		// private List<Fragment> fragmentList;

		public myPagerAdapter(FragmentManager fm, List<String> titleList) {
			super(fm);
			// this.fragmentList = fragmentList;
		}

		@Override
		public Fragment getItem(int position) {
			/*
			 * return (fragmentList == null || fragmentList.size() == 0) ? null
			 * : fragmentList.get(arg0);
			 */
			if (position == 0) {
				if (fragmentTab1 == null) {
					fragmentTab1 = new FDUserCenterLostPwSecAccountFragment(
							new OnClickListener() {
								@Override
								public void onClick(View arg0) {
									fragmentManager.beginTransaction()
											.remove(fragmentTab1).commit();
									fragmentTab1 = new FDUserCenterLostPwSecQuesFragment();
									notifyDataSetChanged();
								}
							});
				}
				return fragmentTab1;
			} else
				fragmentTab2 = new FDUserCenterLostPwPhoneFragment();
			return fragmentTab2;
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
			/*
			 * if (object instanceof FDUserCenterLostPwSecAccountFragment &&
			 * fragmentTab1 instanceof FDUserCenterLostPwSecQuesFragment) return
			 * POSITION_NONE; return POSITION_UNCHANGED;
			 */
		}
	}
}
