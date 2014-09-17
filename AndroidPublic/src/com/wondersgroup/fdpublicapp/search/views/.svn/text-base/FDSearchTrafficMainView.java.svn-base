package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import java.util.List;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import com.amap.api.services.route.WalkRouteResult;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.protocol.FDLocationManager;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchTrafficMapActivity;
import com.wondersgroup.fdpublicapp.search.adapter.TrafficListAdapter;
import com.wondersgroup.fdpublicapp.search.adapter.TrafficLocationAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDBusRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDDriveRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDWalkRoute;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TextView;

public class FDSearchTrafficMainView extends LinearLayout implements View.OnClickListener, OnEditorActionListener
                                                                    , OnPoiSearchListener, OnRouteSearchListener{
	
	public final static String FD_TRAFFIC_QUERY_LOCATION_SHANGHAI = "上海";
	
	private Activity context;
	public LayoutInflater flater;
	private ListView listView;
	private ListView settingListView;
	private TrafficListAdapter adapter;
	private TextView addressText;
	private View trafficSettingView;
	private View trafficLocationView;
	private EditText keywordEditView;
	private TextView startLocationView;
	private TextView currentLocationView;
	private TrafficLocationAdapter locationAdapter;
	private PoiSearch.Query startSearchQuery;
	private RouteSearch routeSearch;
	private int busMode = RouteSearch.BusDefault;           // 公交默认模式
	private int drivingMode = RouteSearch.DrivingDefault;   // 驾车默认模式
	private int walkMode = RouteSearch.WalkDefault;         // 步行默认模式
	private BusRouteResult busRouteResult;                  // 公交模式查询结果
	private DriveRouteResult driveRouteResult;              // 驾车模式查询结果
	private WalkRouteResult walkRouteResult;                // 步行模式查询结果
	private int routeType;                                  // 路径规划类型
	private FDRestaurant restaurant;                        // 当前餐厅信息

	public FDSearchTrafficMainView(Context context) {
		super(context);
		this.context = (Activity) context;
		
		initTrafficMainView();
		initLocationGeocode();
	}

	private void initTrafficMainView() {
		flater = LayoutInflater.from(context);
		View trafficView = flater.inflate(R.layout.fd_restaurant_traffic_main,null);
		addView(trafficView);
		
		trafficSettingView = trafficView.findViewById(R.id.traffic_setting_location_view);
		trafficLocationView = trafficView.findViewById(R.id.traffic_route_location_view);
		startLocationView = (TextView) trafficLocationView.findViewById(R.id.traffic_origin_location_success);
		currentLocationView = (TextView) trafficSettingView.findViewById(R.id.traffic_origin_location_textview);
		keywordEditView = (EditText) trafficView.findViewById(R.id.fd_traffic_route_setting_edit);
		keywordEditView.setOnEditorActionListener(this);
		currentLocationView.setOnClickListener(this);
		
		listView = (ListView) trafficView.findViewById(R.id.traffic_listview);
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent intent = new Intent(context,FDSearchTrafficMapActivity.class);
				if(adapter!=null) {
					if(routeType==0) {
						intent.putExtra(FDSearchTrafficMapActivity.FD_TRAFFIC_ROUTE_PATH_KEY, (FDBusRoute)adapter.getItem(position));
					}else if(routeType==1) {
						intent.putExtra(FDSearchTrafficMapActivity.FD_TRAFFIC_ROUTE_PATH_KEY, (FDDriveRoute)adapter.getItem(position));
					}else if(routeType==2) {
						intent.putExtra(FDSearchTrafficMapActivity.FD_TRAFFIC_ROUTE_PATH_KEY, (FDWalkRoute)adapter.getItem(position));
					}
				}
				context.startActivity(intent);
				context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			}
		});
		addressText = (TextView) trafficView.findViewById(R.id.traffic_origin_name_tv);
		addressText.setOnClickListener(this);
		
		settingListView = (ListView) trafficView.findViewById(R.id.fd_traffic_route_list);
	}

	public void initLocationGeocode() {
		routeSearch = new RouteSearch(context);
		routeSearch.setRouteSearchListener(this);
	}
	
	public void onClick(View view) {
		if (view == addressText) {
			setSettingViewVisible(true);
		}else if(view == currentLocationView) {
			locationRoute();
		}
	}
	
	// 定位规划线路
	public void locationRoute() {
		if(currentLocationView.getTag()!=null) {
			FDLocation location = (FDLocation) currentLocationView.getTag();
			selectKeywordLocation(location);
		}
	}
	
	/**
	 * 开始搜索路径规划方案
	 */
	public void searchRouteResult(LatLonPoint startPoint, LatLonPoint endPoint) {
		final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint, endPoint);
		if (routeType == 0) {           // 公交路径规划
			BusRouteQuery query = new BusRouteQuery(fromAndTo, busMode, FD_TRAFFIC_QUERY_LOCATION_SHANGHAI, 0);  // 第一个参数表示路径规划的起点和终点，第二个参数表示公交查询模式，第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算
			routeSearch.calculateBusRouteAsyn(query);                                                            // 异步路径规划公交模式查询
		} else if (routeType == 1) {    // 驾车路径规划
			DriveRouteQuery query = new DriveRouteQuery(fromAndTo, drivingMode, null, null, "");                 // 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
			routeSearch.calculateDriveRouteAsyn(query);                                                          // 异步路径规划驾车模式查询
		} else if (routeType == 2) {    // 步行路径规划
			WalkRouteQuery query = new WalkRouteQuery(fromAndTo, walkMode);
			routeSearch.calculateWalkRouteAsyn(query);                                                           // 异步路径规划步行模式查询
		}
	}
	
	// 设置兴趣点关键字位置
	public void selectKeywordLocation(final FDLocation addressPoiItem) {
		trafficLocationView.setVisibility(View.VISIBLE);
		startLocationView.setText(addressPoiItem.getAddressDesc());
		startLocationView.setTag(addressPoiItem);
		setSettingViewVisible(false);
		
		if(restaurant!=null && addressPoiItem!=null) {
			LatLonPoint startLatLon = new LatLonPoint(addressPoiItem.getLatitude(),addressPoiItem.getLongitude());
			LatLonPoint endLatLon = new LatLonPoint(restaurant.getGisLatitude(),restaurant.getGisLongitude());
			searchRouteResult(startLatLon,endLatLon);
		}
	}
	
	public void setSettingViewVisible(boolean visible) {
		if(visible) {
			trafficSettingView.setVisibility(View.VISIBLE);
			trafficSettingView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_right_in)); 
		}else {
			if(trafficSettingView.isShown()) {
				if(locationAdapter!=null) {
					locationAdapter.clearTrafficLocation();
				}
				trafficSettingView.setVisibility(View.GONE);
				trafficSettingView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_right_out));
			}
		}
	}

	// 设置定位地址
	public void setTrafficLocation(FDLocation location) {
		if(location==null) return;
		
		startLocationView.setText("定位成功");
		currentLocationView.setText(location.getAddressDesc());
		currentLocationView.setTag(location);
	}
	

	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		switch(actionId){  
        case EditorInfo.IME_NULL:  
            break;  
        case EditorInfo.IME_ACTION_SEND:  
            break;  
        case EditorInfo.IME_ACTION_DONE:  
        	queryKeywordResutl(view.getText().toString().trim());
        }
		return false;
	}
	
	public void queryKeywordResutl(String addressName) {
		startSearchQuery = new PoiSearch.Query(addressName, "", FD_TRAFFIC_QUERY_LOCATION_SHANGHAI);   // 第一个参数表示查询关键字，第二参数表示poi搜索类型，第三个参数表示城市区号或者城市名
		startSearchQuery.setPageNum(0);                                                                // 设置查询第几页，第一页从0开始
		startSearchQuery.setPageSize(20);                                                              // 设置每页返回多少条数据
		PoiSearch poiSearch = new PoiSearch(context, startSearchQuery);
		poiSearch.setOnPoiSearchListener(this);
		poiSearch.searchPOIAsyn();                                                                     // 异步poi查询
	}
	
	public void onPoiItemDetailSearched(PoiItemDetail arg0, int arg1) {
		
	}

	/**
	 * POI搜索结果回调 (兴趣点地址)
	 */
	public void onPoiSearched(PoiResult result, int rCode) {
		if (rCode == 0) {      // 返回成功
			if (result != null && result.getQuery() != null && result.getPois() != null && result.getPois().size() > 0) {
				if (result.getQuery().equals(startSearchQuery)) {
					List<PoiItem> poiItems = result.getPois();              // 取得poiitem数据
					locationAdapter = new TrafficLocationAdapter(context, poiItems);
					settingListView.setAdapter(locationAdapter);
					settingListView.setOnItemClickListener(new OnItemClickListener(){
						public void onItemClick(AdapterView<?> adapter, View view, int position,long arg3) {
							PoiItem poiItem = (PoiItem) adapter.getItemAtPosition(position);
							FDLocation location = new FDLocation();
							location.setAddressDesc(poiItem.getTitle());
							location.setCityCode(poiItem.getCityCode());
							location.setLatitude(poiItem.getLatLonPoint().getLatitude());
							location.setLongitude(poiItem.getLatLonPoint().getLongitude());
							selectKeywordLocation(location);
						}
					});
					locationAdapter.notifyDataSetChanged();
				}
			}
		}else {
			Toast.makeText(context, "查询失败..", Toast.LENGTH_LONG).show();
		}
	}

	// 公交线路查询
	public void onBusRouteSearched(BusRouteResult result, int rCode) {
		if (rCode == 0) {
			if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
				busRouteResult = result;
//				Toast.makeText(context, "打的需要 "+busRouteResult.getTaxiCost()+"元", Toast.LENGTH_LONG).show();
				ArrayList<FDSuperRoute> busRoutes = FDLocationManager.getBusRoute(result);
				adapter = new TrafficListAdapter(context, busRoutes);
				listView.setAdapter(adapter);
				this.adapter.notifyDataSetChanged();
			} else {
				Toast.makeText(context, "公交规划失败..", Toast.LENGTH_LONG).show();
			}
		}else {
			Toast.makeText(context, "公交规划失败..", Toast.LENGTH_LONG).show();
		}
	}

	// 驾车线路查询
	public void onDriveRouteSearched(DriveRouteResult result, int rCode) {
		if (rCode == 0) {
			if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
				driveRouteResult = result;
				ArrayList<FDSuperRoute> driveRoutes = FDLocationManager.getDriveRoute(result);
				adapter = new TrafficListAdapter(context, driveRoutes);
				listView.setAdapter(adapter);
				this.adapter.notifyDataSetChanged();
			} else {
				Toast.makeText(context, "驾车规划失败..", Toast.LENGTH_LONG).show();
			}
		}else {
			Toast.makeText(context, "驾车规划失败..", Toast.LENGTH_LONG).show();
		}
	}

	// 步行线路查询
	public void onWalkRouteSearched(WalkRouteResult result, int rCode) {
		if (rCode == 0) {
			if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
				walkRouteResult = result;
				ArrayList<FDSuperRoute> walkRoutes = FDLocationManager.getWalkRoute(result);
				adapter = new TrafficListAdapter(context, walkRoutes);
				listView.setAdapter(adapter);
				this.adapter.notifyDataSetChanged();
			} else {
				Toast.makeText(context, "步行规划失败..", Toast.LENGTH_LONG).show();
			}
		}else {
			Toast.makeText(context, "步行规划失败..", Toast.LENGTH_LONG).show();
		}
	}
	
	public void setTrafficRestaurant(FDRestaurant trafficRestaurant) {
		this.restaurant = trafficRestaurant;
	}
	public void setTrafficRouteType(int type) {
		this.routeType = type;
	}
}
