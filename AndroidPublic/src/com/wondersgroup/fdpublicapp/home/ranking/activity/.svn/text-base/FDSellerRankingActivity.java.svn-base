package com.wondersgroup.fdpublicapp.home.ranking.activity;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDCustomViewPager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;
import com.wondersgroup.fdpublicapp.home.main.adapter.FDFragmentAdapter;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * @author chengshaohua
 *  商家排行榜
 */
public class FDSellerRankingActivity extends FDBaseActivity implements OnPageChangeListener{
	private int position;
	private FDCustomViewPager viewPager;
	private FDFragmentAdapter rankingAdapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fd_ranking_table);
		
		initSearchResultView();
	} 

	private void initSearchResultView() {
		this.viewPager = (FDCustomViewPager) this.findViewById(R.id.ranking_view_pager);
		View sellerRankingView = this.findViewById(R.id.ranking_seller_footbar);
		View memberRankingView = this.findViewById(R.id.ranking_member_footbar);
		sellerRankingView.setOnClickListener(new SearchOnClickListener(FDSearchFragment.QUERY_RESTAURANT_ALL_AREA));
		memberRankingView.setOnClickListener(new SearchOnClickListener(FDSearchFragment.QUERY_RESTAURANT_NEAR_BY));
//		
		ArrayList<FDSearchFragment> rankingFramgentList = new ArrayList<FDSearchFragment>();
		rankingFramgentList.add(new FDSellerRankingFragment());
		rankingFramgentList.add(new FDMemberRankingFragment());
		this.rankingAdapter = new FDFragmentAdapter(this,getSupportFragmentManager(), rankingFramgentList);
		viewPager.setAdapter(rankingAdapter);
		viewPager.setOnPageChangeListener(this);
		
//		this.position =  getIntent().getIntExtra(FDConst.FD_QUERY_DETAIL_TYPE_ID, 0);
		this.viewPager.setCurrentItem(position);
		setViewPagerBar(position);
		
	}
	
	
	public void setViewPagerBar(int index) {
		RadioGroup viewPagerGroup = (RadioGroup) this.findViewById(R.id.fd_ranking_tabbar);
		RadioButton seleceedButton = (RadioButton) viewPagerGroup.getChildAt(index);
		seleceedButton.setChecked(true);
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
  		if (index == FDSearchFragment.QUERY_RESTAURANT_NEAR_BY) {        
  			
  		}else if(index == FDSearchFragment.QUERY_RESTAURANT_ALL_AREA) {   
 		}
  	}
  	
  	public void onPageScrollStateChanged(int arg0) {
  		
  	}
  	public void onPageScrolled(int arg0, float arg1, int arg2) {
  		
  	}
  	
  	
}
