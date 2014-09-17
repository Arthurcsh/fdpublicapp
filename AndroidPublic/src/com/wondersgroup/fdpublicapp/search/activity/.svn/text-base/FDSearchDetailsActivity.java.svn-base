package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.adapter.FDCommonFragmentAdapter;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.delicacy.views.FDCustomViewPager;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;

/**
 *  餐厅查询详情
 * @author chengshaohua
 *
 */
public class FDSearchDetailsActivity extends FDBaseActivity {
	private int restaurantID;
	private FDRestaurant restaurantDetail;
	private FDCustomViewPager searchDetailViewPager;
	private FDCommonFragmentAdapter searchDetailAdapter;
	private FDSearchDetailsFragment searchDetailFragment;
	private FDSearchCommentFragment searchCommentFragment;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_search_detail_content);		
		
		initSearchDetailView();
	}
	
	/**
	 * 初始化餐厅详情
	 */
	public void initSearchDetailView() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null && bundle.containsKey(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY)) {
			restaurantID = bundle.getInt(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		}
		
		ImageView commentImageView = (ImageView) findViewById(R.id.search_detail_comment_imageview);
		this.searchDetailViewPager = (FDCustomViewPager) this.findViewById(R.id.search_detail_view_pager);
		ArrayList<Fragment> searchDetailFramgentList = new ArrayList<Fragment>();
		searchDetailFragment = new FDSearchDetailsFragment(backAndCommentListener,restaurantID);
		searchCommentFragment = new FDSearchCommentFragment(commentBackListener, restaurantID);
		searchDetailFramgentList.add(searchDetailFragment);
		searchDetailFramgentList.add(searchCommentFragment);
		this.searchDetailAdapter = new FDCommonFragmentAdapter(getSupportFragmentManager(), searchDetailFramgentList);
		searchDetailViewPager.setAdapter(searchDetailAdapter);
//		nutritionViewPager.setOnPageChangeListener(this);
		searchDetailViewPager.setCurrentItem(0);
		
		commentImageView.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				Intent intent = new Intent(context,FDRestCommentActivity.class);
				if(restaurantDetail!=null) {
					intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantDetail);
				}
				startActivity(intent);
			}
		});
	}
	
	/**
	 *  餐厅详情返回/
	 */
	FDCallback backAndCommentListener = new FDCallback() {
		public void onCallback(Object callback) {
			if(callback==null) {
				FDSearchDetailsActivity.this.finish();
			}else if(callback instanceof FDRestaurant){
				restaurantDetail = (FDRestaurant) callback;
			}else {
				searchDetailViewPager.setCurrentItem(1);
				searchCommentFragment.loadFragmentPageList();
			}
		}
	};
	
	/**
	 * 餐厅点评返回
	 */
	FDCallback commentBackListener = new FDCallback() {
		public void onCallback(Object callback) {
			searchDetailViewPager.setCurrentItem(0);
		}
	};
	
}
