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
 * 我的点评
 * @author mengke
 * 
 */
public class FDUserCenterMyCommentsActivity extends FDBaseActivity implements OnPageChangeListener{

	private LinearLayout backMainLayout;
	private FDCustomViewPager commentViewPager;
	private ArrayList<Fragment> commentFragmentList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_my_comments);

		// 获取控件实例
		backMainLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_my_comments_back);
		backMainLayout.setOnClickListener(backOnClickListener);
		commentViewPager = (FDCustomViewPager) findViewById(R.id.vp_fd_usercenter_my_comments);
		RadioGroup commentGroup = (RadioGroup) findViewById(R.id.rg_fd_usercenter_my_comments);
		for(int i=0;i<commentGroup.getChildCount();i++) {
			View commentView = commentGroup.getChildAt(i);
			if(commentView==null) return;
			commentView.setOnClickListener(new FDFragmentOnClickListener(commentViewPager, i));
		}
		
		commentFragmentList = new ArrayList<Fragment>();
		commentFragmentList.add(new FDUserCenterMyCommentsRestFragment());
		commentFragmentList.add(new FDUserCenterMyCommentsDishFragment());
		commentFragmentList.add(new FDUserCenterMyCommentsNoteFragment());
		commentViewPager.setAdapter(new FDUserFragmentAdapter(getSupportFragmentManager(),commentFragmentList));
		commentViewPager.setOnPageChangeListener(this);
		commentViewPager.setCurrentItem(0);
	}

	// 后退事件
	private OnClickListener backOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			FDUserCenterMyCommentsActivity.this.onBackPressed();
		}
	};

	public void onPageScrollStateChanged(int arg0) {
	}
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	public void onPageSelected(int position) {
		if (commentFragmentList == null) return;

		Fragment fragment = commentFragmentList.get(position);
		if (fragment != null) {
			FDListViewFragment listViewFragment = (FDListViewFragment) fragment;
			listViewFragment.loadFragmentPageList(1);
		}
	}

}
