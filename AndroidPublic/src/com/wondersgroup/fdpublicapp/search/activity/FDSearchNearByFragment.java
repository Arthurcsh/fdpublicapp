package com.wondersgroup.fdpublicapp.search.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.impl.FDMultiConditionListener;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;
import com.wondersgroup.fdpublicapp.widget.FDQueryNearView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author chengshaohua
 * @desc   搜索附近餐厅
 */
public class FDSearchNearByFragment extends FDSearchFragment implements FDMultiConditionListener, IXListViewListener {
	
	private FDQueryNearView searchNearByView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		initRestaurantListView(); 
		setSearchLocationCondition();
		
		return searchNearByView;
	}
	
	// 初始化附近餐厅列表 (该方法必须实现)
	public void initRestaurantListView() {
		// .....
		searchNearByView = new FDQueryNearView(context);
		searchNearByView.setQueryConditionListener(this);
		
		searchHeaderProgressBar = (ProgressBar) searchNearByView.findViewById(R.id.header_location_progress);
		searchHeaderCountText = (TextView) searchNearByView.findViewById(R.id.header_search_result_number);
		LinearLayout nearbyTableView = (LinearLayout) searchNearByView.findViewById(R.id.fd_business_trends_xml);
		LinearLayout backButton = (LinearLayout) searchNearByView.findViewById(R.id.header_search_back_Linear);
		nearbyTableView.addView(searchResultTableView);
		xlistView.setXListViewListener(this);
		
		initMapViewListener(searchNearByView);
		backButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDSearchNearByFragment.this.getActivity().finish();
			}
		});
	}
	
	public void setNearByRestaurant(List<FDRestaurant> searchAllList) {
		if(searchAllList==null) return;
		
		if(this.searchCallbackListener!=null) {
			searchCallbackListener.searchCallback(searchAllList);
		}
		// ......
		updateRestaurantTable(searchAllList);
	}
	
	// 设置附近定位餐厅
	public void setSearchLocationCondition() {
		JSONObject nearByObject = new JSONObject();
		try {
			nearByObject.put(FDConst.FD_QUERY_CONDITION_LONGITUDE, longitude);
			nearByObject.put(FDConst.FD_QUERY_CONDITION_LATITUDE, latitude);
			nearByObject.put(FDConst.FD_QUERY_CONDITION_DISTANCE, FD_DEFAULT_DISTANCE);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		getRequestParams(nearByObject.toString());
		loadSearcNearByPageList();
	}
	
	// 搜索附近餐厅列表信息      "121.521653/31.07894/500/1/20"   
	public void loadSearcNearByPageList() {
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
					setNearByRestaurant(restaurantList);
					onListViewLoad(totalRecord);
				}
			}
		});
	}

	public void onChangedConditionCallback(String conditionCallback) {
//		if(conditionCallback==null) return;
		getRequestParams(conditionCallback);
		loadSearcNearByPageList();
	}

	public void onRefresh() {
		this.pageIndex = 1;
		pageHandler.post(new Runnable(){
			public void run() {
				loadSearcNearByPageList();
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
				loadSearcNearByPageList();
			}
		});
	}

}
