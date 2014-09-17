package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.impl.FDMultiConditionListener;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.mode.FDCondition;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;
import com.wondersgroup.fdpublicapp.widget.FDQueryAreaView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 *  全城查询
 * @author chengshaohua
 *
 */
public class FDSearchAreaFragment extends FDSearchFragment implements FDMultiConditionListener, IXListViewListener {
	private FDQueryAreaView searchAreaView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initRestaurantListView();
		setSearchLocationCondition();
		
		return searchAreaView;
	}	
	
	// 初始化全城餐厅列表 (该方法必须实现)
	public void initRestaurantListView() {
		// .....
		this.searchAreaView = new FDQueryAreaView(context);
		searchAreaView.setQueryConditionListener(this);
		
		searchHeaderProgressBar = (ProgressBar) searchAreaView.findViewById(R.id.header_location_progress);
		searchHeaderCountText = (TextView) searchAreaView.findViewById(R.id.header_search_result_number);
		LinearLayout searchAreaLayout = (LinearLayout) searchAreaView.findViewById(R.id.fd_business_trends_xml);
		LinearLayout backButton = (LinearLayout) searchAreaView.findViewById(R.id.header_search_back_Linear);
		searchAreaLayout.addView(searchResultTableView);
		xlistView.setXListViewListener(this);
		
		initMapViewListener(searchAreaView);
		backButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDSearchAreaFragment.this.getActivity().finish();
			}
		});
	}
	
	public void setAreaRestaurant(List<FDRestaurant> searchAllList) {
		if(searchAllList==null) return;
		
		if(this.searchCallbackListener!=null) {
			searchCallbackListener.searchCallback(searchAllList);
		}
		// ......
		updateRestaurantTable(searchAllList);
	}

	// 初始化餐厅查询条件
	public void setSearchLocationCondition() {
		Bundle bundle = getActivity().getIntent().getExtras();
		String queryCondition = null;
		if(bundle.containsKey(FDConst.FD_QUERY_CONDITION_BUNDLE_KEY)) {
			FDCondition bundleCondition = bundle.getParcelable(FDConst.FD_QUERY_CONDITION_BUNDLE_KEY);
			searchAreaView.initConditionQuery(bundleCondition);
			// 初始化条件BarItem
			if(bundleCondition!=null) {
				queryCondition = bundleCondition.getQueryConditions();
			}
		}
		
		getRequestParams(queryCondition);
		loadSearchAreaPageList();
	}
	
	// 餐厅列表信息
	public void loadSearchAreaPageList() {
		searchHeaderProgressBar.setVisibility(View.VISIBLE);
		FDSearchWrapper searchWrapper = new FDSearchWrapper(context);
		searchWrapper.getRestaurant(pageIndex, QUERY_RESTAURANT_MAX_COUNT, searchParamsMap, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				searchHeaderProgressBar.setVisibility(View.GONE);
				FDResultPage<FDRestaurant> restaurantPage = (FDResultPage<FDRestaurant>) callback;
				if(restaurantPage!=null) {
					long totalRecord = 0;
					pageCount = restaurantPage.getPageCount();
					ArrayList<FDRestaurant> restaurants = (ArrayList<FDRestaurant>) restaurantPage.getResultList();
					if(restaurants==null || pageIndex==1) {
						restaurantList.clear();
						totalRecord = 0;
					}
					if(restaurants!=null) {
						restaurantList.addAll(restaurants);
						totalRecord = restaurantPage.getTotalRecord();
					}
					setAreaRestaurant(restaurantList);
					onListViewLoad(totalRecord);
				}
			}
		});
	}

	/**
	 * 设置查询条件
	 */
	public void onChangedConditionCallback(String conditionCallback) {
		getRequestParams(conditionCallback);
		System.out.println("condition ------------------"+searchParamsMap);
		this.pageIndex = 1;
		loadSearchAreaPageList();
	}

	public void onRefresh() {
		this.pageIndex = 1;
		pageHandler.post(new Runnable(){
			public void run() {
				loadSearchAreaPageList();
			}
		});
	}

	public void onLoadMore() {
		pageIndex++;
		if(pageIndex>pageCount) {
			onListViewLoad(0);
			return;
		}
		pageHandler.post(new Runnable(){
			public void run() {
				loadSearchAreaPageList();
			}
		});
	}

}
