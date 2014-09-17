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
 * 消息管理(系统消息、推送消息..)
 * @author chengshaohua
 * 
 */
public class FDUserCenterMsgManagerActivity extends FDBaseActivity implements OnPageChangeListener{

	private LinearLayout backMainLayout;
	private FDCustomViewPager messageViewPager;
	private ArrayList<Fragment> messageFragmentList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_usercenter_message_manager);

		// 获取控件实例
		backMainLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_msg_manager_back);
		backMainLayout.setOnClickListener(backOnClickListener);
		messageViewPager = (FDCustomViewPager) findViewById(R.id.vp_fd_usercenter_msg_manager);
		RadioGroup commentGroup = (RadioGroup) findViewById(R.id.rg_fd_usercenter_msg_manager);
		for(int i=0;i<commentGroup.getChildCount();i++) {
			View commentView = commentGroup.getChildAt(i);
			if(commentView==null) return;
			commentView.setOnClickListener(new FDFragmentOnClickListener(messageViewPager, i));
		}
		
		messageFragmentList = new ArrayList<Fragment>();
		messageFragmentList.add(new FDUserCenterMsgReplyFragment());
		messageFragmentList.add(new FDUserCenterMsgSystemFragment());
		messageFragmentList.add(new FDUserCenterMsgMessageFragment());
		messageViewPager.setAdapter(new FDUserFragmentAdapter(getSupportFragmentManager(),messageFragmentList));
		messageViewPager.setOnPageChangeListener(this);
		messageViewPager.setCurrentItem(0);
	}

	// 后退事件
	private OnClickListener backOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			FDUserCenterMsgManagerActivity.this.onBackPressed();
		}
	};

	public void onPageScrollStateChanged(int arg0) {
	}
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	public void onPageSelected(int position) {
		if (messageFragmentList == null) return;

		Fragment fragment = messageFragmentList.get(position);
		if (fragment != null) {
			FDListViewFragment listViewFragment = (FDListViewFragment) fragment;
			listViewFragment.loadFragmentPageList(1);
		}
	}

}
