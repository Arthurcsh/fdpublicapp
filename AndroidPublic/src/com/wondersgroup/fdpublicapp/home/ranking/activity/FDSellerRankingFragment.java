package com.wondersgroup.fdpublicapp.home.ranking.activity;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.ranking.adapter.FDRankingSellerAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDCondition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 商户排行榜
 * @author chengshaohua
 *
 */
public class FDSellerRankingFragment extends FDSearchFragment {

	public View rankingConditionView;
	public ArrayList<FDRestaurant> sellersRankingList = new ArrayList<FDRestaurant>();
	public FDRankingSellerAdapter sellerRankingAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initSellerRankingData();
		initSellerRankingView();
		setSearchRankingCondition();
		
		return rankingConditionView;
	}	
	
	public void initSellerRankingData() {
		for(int i=0;i<9;i++) {
			FDRestaurant restaurant = new FDRestaurant();
			restaurant.setId(5);
			restaurant.setName("龙谷料理");
			restaurant.setSupervisionOrg("上海安食办");
			restaurant.setFoodSaftyRating(21004);
			restaurant.setFoodSaftyRatingValue("优秀");
			restaurant.setFoodSaftyRatingDate("2014-02-15");
			restaurant.setAverageComsumptionValue("120");
			restaurant.setBizAddress("西式餐厅 多商区");
			restaurant.setTelephone("021-63590688");
			sellersRankingList.add(restaurant);
		}
	}
	
	public void initSellerRankingView() {
		rankingConditionView = LayoutInflater.from(context).inflate(R.layout.fd_ranking_seller_condiction, null);
		LinearLayout backLayout = (LinearLayout) rankingConditionView.findViewById(R.id.fd_ranking_header_back_label);
		LinearLayout rankingLayout = (LinearLayout) rankingConditionView.findViewById(R.id.fd_sellers_ranking_xml);
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDSellerRankingFragment.this.getActivity().finish();
			}
		});
		rankingLayout.addView(searchResultTableView);
		sellerRankingAdapter = new FDRankingSellerAdapter(this.getActivity(),sellersRankingList);         
		xlistView.setAdapter(sellerRankingAdapter);
		sellerRankingAdapter.notifyDataSetChanged();
	}
	
	public void setSearchRankingCondition() {
		Bundle bundle = getActivity().getIntent().getExtras();
		String queryCondition = null;
		if(bundle!=null && bundle.containsKey(FDConst.FD_QUERY_CONDITION_BUNDLE_KEY)) {
			FDCondition bundleCondition = bundle.getParcelable(FDConst.FD_QUERY_CONDITION_BUNDLE_KEY);
//			searchAreaView.initConditionQuery(bundleCondition);
			// 初始化条件BarItem
			if(bundleCondition!=null) {
				queryCondition = bundleCondition.getQueryConditions();
			}
		}
		getRequestParams(queryCondition);
//		loadSearchDetailList(null);
	}

	public void setSearchLocationCondition() {
		
	}

	public void initRestaurantListView() {
		
	}
}
