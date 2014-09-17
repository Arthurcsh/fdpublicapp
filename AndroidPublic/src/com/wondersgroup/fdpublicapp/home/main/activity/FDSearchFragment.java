package com.wondersgroup.fdpublicapp.home.main.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.impl.FDRestaurantImpl;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.activity.FDBaseFragment;
import com.wondersgroup.fdpublicapp.search.views.FDSearchTableListView;

/**
 *  餐厅查询 Fragment
 *  
 * @author chengshaohua
 *
 */
public abstract class FDSearchFragment extends FDBaseFragment implements FDRestaurantImpl {
	
	public final static int QUERY_RESTAURANT_ALL_AREA   = 0;
	public final static int QUERY_RESTAURANT_NEAR_BY    = 1;
	public final static int QUERY_RESTAURANT_MAX_COUNT  = 8;
	public static double latitude = 31.20271640025101;             // 经度
	public static double longitude = 121.41061436139555;           // 维度
	
	protected Context context;
	protected FDSearchTableListView searchResultTableView;
	protected LJListView xlistView;
	protected TextView searchHeaderCountText;
	public ProgressBar searchHeaderProgressBar;
	public FragmentSearchCallback searchCallbackListener;
	public List<FDRestaurant> currentRestaurant = new ArrayList<FDRestaurant>();
	protected Map<String,String> searchParamsMap = new HashMap<String,String>();
	protected List<FDRestaurant> restaurantList = new ArrayList<FDRestaurant>();
	protected Handler pageHandler;
	protected int pageIndex = 1;                               // 当前页
	protected int pageCount;                                   // 总页数
	protected final static int FD_DEFAULT_DISTANCE = 6000;     // 默认距离
	
	// 初始化餐厅列表
	public abstract void initRestaurantListView();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		context = this.getActivity();
		pageHandler = new Handler();
		
		initSearchTableView();
		return container;
	}
	
	public void initSearchTableView() {
		FDLocation location = (FDLocation) ServiceManager.get(FDConst.FD_SERVER_LOCATION_POINT);
		if(location!=null) {
			this.latitude = location.getLatitude();
			this.longitude = location.getLongitude();
		}
		
		this.searchResultTableView = new FDSearchTableListView(context);
		this.xlistView = (LJListView) searchResultTableView.getLJListView();
	}
	
	// 设置Hearder餐厅数回调
	public void setSearchListener(FragmentSearchCallback callbackListener) {
		this.searchCallbackListener = callbackListener;
	}
	
	public void updateRestaurantTable(List<FDRestaurant> searchAllList) {
		if(searchAllList==null) return;
		this.currentRestaurant = searchAllList;
		
		if(context==null) return;   // context=getActivity()可能为null
		Handler pullHandler = new Handler();
		pullHandler.post(new Runnable() {
			public void run() {
				searchHeaderCountText.setText(context.getResources().getString(R.string.search_header_result_label)+"("+getRestaurants().size()+")");
				searchResultTableView.setAllList(currentRestaurant);
//				xlistView.stopLoadMore();    // 重置更多加载
			}
		});
	}
	
	// 添加地图事件
  	public void initMapViewListener(View view) {
  		LinearLayout mapLocation = (LinearLayout) view.findViewById(R.id.header_map_location_area);
		mapLocation.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				String[] gisLocation = null;
				if(getRestaurants()!=null) {
					int count = getRestaurants().size();
					gisLocation = new String[count];
					for(int i=0;i<count;i++) {
						FDRestaurant restaurant = getRestaurants().get(i);
						gisLocation[i] = restaurant.getName()+","+restaurant.getFoodSaftyRating()
								+","+restaurant.getGisLatitude()+","+restaurant.getGisLongitude();
					}
				}
				Intent intent = new Intent(FDSearchFragment.this.getActivity(),FDMapActivity.class);
				if(gisLocation!=null) {
					Bundle bundle = new Bundle();
					bundle.putStringArray("gisLocation", gisLocation);
					intent.putExtras(bundle);
				}
				startActivity(intent);
			}
		});
  	}
  	
    // 餐厅查询参数
  	public Map<String,String> getRequestParams(String jsonParams) {
		if(jsonParams!=null) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			try {
				LinkedHashMap<String, Object> entityMap = objectMapper.readValue(jsonParams, LinkedHashMap.class);
				if(entityMap!=null) {
					for(Map.Entry<String, Object> entry : entityMap.entrySet()) {
						if(entry==null) continue;
						if(FDConst.UNLIMITED_CONDITION_KEY.equals(entry.getValue().toString())){
							searchParamsMap.remove(entry.getKey());
						}else {
							searchParamsMap.put(entry.getKey(), entry.getValue().toString());
						}
					}
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		searchParamsMap.put(FDConst.FD_QUERY_CONDITION_LONGITUDE, ""+longitude);
		searchParamsMap.put(FDConst.FD_QUERY_CONDITION_LATITUDE, ""+latitude);
		
		return searchParamsMap;
  	}
  	
  	public List<FDRestaurant> getRestaurants() {
		return this.currentRestaurant;
	}

  	public interface FragmentSearchCallback {
		public void searchCallback(List<FDRestaurant> restaurants);
	}
  	
  	/**
	 *  更新列表状态
	 * @param count  更新条数
	 */
	protected void onListViewLoad(long count) {
		xlistView.setCount(""+count);
		xlistView.stopRefresh();
		xlistView.stopLoadMore(pageIndex>pageCount);
		xlistView.setRefreshTime(StringUtils.dateToString(new Date()));
	}
	
	public void loadFragmentPageList() {
		
	}
}
