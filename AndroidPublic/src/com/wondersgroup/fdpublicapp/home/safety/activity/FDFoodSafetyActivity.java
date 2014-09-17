package com.wondersgroup.fdpublicapp.home.safety.activity;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDCustomViewPager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.personal.adapter.FDUserFragmentAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * @author chengshaohua
 *  食品安全信息 
 */
public class FDFoodSafetyActivity extends FDBaseActivity implements OnPageChangeListener{
	private int position;
	private ArrayList<Fragment> safetyInfoFramgentList;
	private FDCustomViewPager viewPager;
	private FDUserFragmentAdapter safetyInfoAdapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fd_safety_info_table);
		
		initSearchResultView();
	} 

	private void initSearchResultView() {
		this.viewPager = (FDCustomViewPager) this.findViewById(R.id.safety_view_pager);
		View sellerRankingView = this.findViewById(R.id.safety_info_footbar);
		View memberRankingView = this.findViewById(R.id.safety_warn_footbar);
		sellerRankingView.setOnClickListener(new SearchOnClickListener(FDSearchFragment.QUERY_RESTAURANT_ALL_AREA));
		memberRankingView.setOnClickListener(new SearchOnClickListener(FDSearchFragment.QUERY_RESTAURANT_NEAR_BY));
//		
		safetyInfoFramgentList = new ArrayList<Fragment>();
		safetyInfoFramgentList.add(new FDSafetyInfoFragment(0));
//		safetyInfoFramgentList.add(new FDSafetyInfoFragment(1));
		safetyInfoAdapter = new FDUserFragmentAdapter(getSupportFragmentManager(),safetyInfoFramgentList);
		viewPager.setAdapter(safetyInfoAdapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(position);
		setViewPagerBar(position);
		
	}
	
	
	public void setViewPagerBar(int index) {
		RadioGroup viewPagerGroup = (RadioGroup) this.findViewById(R.id.fd_safety_tabbar);
		RadioButton seleceedButton = (RadioButton) viewPagerGroup.getChildAt(index);
		seleceedButton.setChecked(true);
		viewPagerGroup.setVisibility(View.GONE);      // 需求未定，暂时隐藏
	}
	
	public class SearchOnClickListener implements View.OnClickListener {
		int clickIndex = 0;
        public SearchOnClickListener(int index) {
        	clickIndex = index;
        }

        public void onClick(View v) {
        	viewPager.setCurrentItem(clickIndex);
        }
    };
    
    // 切换不同的Page要重新加载数据。
  	public void onPageSelected(int index) {
  		if (safetyInfoFramgentList == null) return;
		Fragment fragment = safetyInfoFramgentList.get(position);
		if (fragment != null) {
			FDListViewFragment listViewFragment = (FDListViewFragment) fragment;
			listViewFragment.loadFragmentPageList(1);
		}
  	}
  	public void onPageScrollStateChanged(int arg0) {
  	}
  	public void onPageScrolled(int arg0, float arg1, int arg2) {
  	}
  	
}
