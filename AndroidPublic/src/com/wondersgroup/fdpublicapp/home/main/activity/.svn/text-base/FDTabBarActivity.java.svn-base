package com.wondersgroup.fdpublicapp.home.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.widget.FDScrollLayout;

public class FDTabBarActivity extends FDBaseActivity {
	private FDScrollLayout fdScrollLayout;
	private int            fdViewCount;
	private int            currentSelect;
	private RadioButton[]  radioButtons;
	private String[]       fdHeadTitles;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_activity_main);
		
		this.initPageScroll();
	}
	
	/**
	 * 初始化水平滚动翻页
	 */
	private void initPageScroll() {
		fdScrollLayout = (FDScrollLayout) findViewById(R.id.main_scrolllayout);
//		fdScrollLayout.setIsScroll(false);
		
		RadioGroup mainBarLayout = (RadioGroup) findViewById(R.id.fd_main_tabbar);
//		fdHeadTitles = getResources().getStringArray(R.array.head_titles);
		fdViewCount = fdScrollLayout.getChildCount();
		radioButtons = new RadioButton[fdViewCount];

		for (int i = 0; i < fdViewCount; i++) {
			radioButtons[i] = (RadioButton) mainBarLayout.getChildAt(i);
			radioButtons[i].setTag(i);
			radioButtons[i].setChecked(false);
			radioButtons[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					int posistion = (Integer) (view.getTag());
					// 点击当前项刷新
					if (currentSelect == posistion) {
						switch (posistion) {
						case 0:// 首页
							break;
						case 1:// 搜索
							initSearchView();
							break;
						case 2:// 优惠
							break;
						case 3:// 个人
							break;
						}
					}
					fdScrollLayout.snapToScreen(posistion);
				}
			});
		}

		// 设置第一显示屏
		currentSelect = 0;
		radioButtons[currentSelect].setChecked(true);
		fdScrollLayout.setOnViewChangeListener(new FDScrollLayout.OnViewChangeListener() {
					public void OnViewChange(int viewIndex) {
						// 切换列表视图-如果列表数据为空：加载数据
						switch (viewIndex) {
						case 0:// 首页
							break;
						case 1:// 搜索
							break;
						case 2:// 优惠
							break;
						case 3:// 个人
								// 判断登录
							break;
						}
						setCurrentPoint(viewIndex);
					}
				});
	}
	
	/**
	 * 设置底部栏当前焦点
	 * 
	 * @param index
	 */
	protected void setCurrentPoint(int index) {
		if (index < 0 || index > fdViewCount - 1 || currentSelect == index) return;

		radioButtons[currentSelect].setChecked(false);
		radioButtons[index].setChecked(true);
//		mHeadTitle.setText(mHeadTitles[index]);
		currentSelect = index;
		
//		mHead_search.setVisibility(View.GONE);
//		mHeadPub_post.setVisibility(View.GONE);
//		mHeadPub_tweet.setVisibility(View.GONE);
//		// 头部logo、消息、发动弹按钮显示
//		if (index == 0) {
//			mHeadLogo.setImageResource(R.drawable.frame_logo_index);
//			mHead_search.setVisibility(View.VISIBLE);
//		} else if (index == 1) {
//			mHeadLogo.setImageResource(R.drawable.frame_logo_query);
//			mHeadPub_post.setVisibility(View.VISIBLE);
//		} else if (index == 2) {
//			mHeadLogo.setImageResource(R.drawable.frame_logo_coupon);
//			mHeadPub_tweet.setVisibility(View.VISIBLE);
//		} else if (index == 3) {
//			mHeadLogo.setImageResource(R.drawable.frame_logo_personal);
//			mHeadPub_tweet.setVisibility(View.VISIBLE);
//		}
	}
	
	// 切换到..
	public void setPageIndex(int index) {
		if (index < 0 || index > fdViewCount - 1 || currentSelect == index) return;

		radioButtons[currentSelect].setChecked(false);
		radioButtons[index].setChecked(true);
		currentSelect = index;
		fdScrollLayout.snapToScreen(index);
	}
	
	public void initSearchView() {
		
	}
	
	public void setTabScroll(boolean scroll) {
		this.fdScrollLayout.setIsScroll(scroll);
	}
}
