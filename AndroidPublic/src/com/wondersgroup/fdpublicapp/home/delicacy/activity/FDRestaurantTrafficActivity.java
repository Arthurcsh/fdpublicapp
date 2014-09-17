package com.wondersgroup.fdpublicapp.home.delicacy.activity;

import java.util.ArrayList;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.FDAMapUtil;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.adapter.TrafficPagerAdapter;
import com.wondersgroup.fdpublicapp.search.views.FDSearchTrafficMainView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FDRestaurantTrafficActivity extends FDBaseActivity implements OnClickListener, AMapLocationListener,
                                                                OnMarkerClickListener, OnInfoWindowClickListener,
                                                                OnMapLoadedListener, InfoWindowAdapter, LocationSource {

	private LinearLayout backLayout;
	private ViewPager trafficViewPager;
	private FDRestaurant restaurantDetail;
	private ArrayList<View> listViews;
	public LocationManagerProxy locationManager = null;
	public AMapLocation aMapLocation;     // 用于判断定位超时
	public MapView routerMapView;
	public AMap routerMap; 
	public OnLocationChangedListener changedListener;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_restaurant_traffic_xml);
		routerMapView = (MapView) findViewById(R.id.fd_traffic_router_map_view);  
		routerMapView.onCreate(savedInstanceState);    // 必须要写 
		
		startLocationManager();
		initViewPager();
		initTrafficView();
		initMapView();
		initRouterMapMarker();
	}
	
	private void initTrafficView() {
		backLayout = (LinearLayout) findViewById(R.id.traffic_img_ll);
		backLayout.setOnClickListener(this);
		
		restaurantDetail = getIntent().getParcelableExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY);
		if(restaurantDetail!=null) {
			for(int i=0;i<listViews.size();i++) {
				FDSearchTrafficMainView trafficView = (FDSearchTrafficMainView) listViews.get(i);
				trafficView.setTrafficRestaurant(restaurantDetail);
				TextView addressTextView = (TextView) trafficView.findViewById(R.id.traffic_end_point_name_tv);
				addressTextView.setText(restaurantDetail.getBizAddress());
			}
		}
	}
	
	
	/**
	 * 初始化ViewPager
	 */
    private void initViewPager() {
    	trafficViewPager = (ViewPager) findViewById(R.id.search_traffic_pager);
    	RadioGroup trafficGroups = (RadioGroup) findViewById(R.id.fd_traffic_tabbar);
    	listViews = new ArrayList<View>();
        for(int i=0;i<trafficGroups.getChildCount();i++) {
        	View trafficItem = trafficGroups.getChildAt(i);
        	FDSearchTrafficMainView trafficView = new FDSearchTrafficMainView(this);
        	trafficView.setTrafficRouteType(i);
        	trafficItem.setOnClickListener(new TrafficOnClickListener(i));
        	listViews.add(trafficView);
        }
        trafficViewPager.setAdapter(new TrafficPagerAdapter(listViews));
        trafficViewPager.setCurrentItem(0);
    }
    
    public class TrafficOnClickListener implements View.OnClickListener {
		int clickIndex = 0;
        public TrafficOnClickListener(int index) {
        	clickIndex = index;
        }

        public void onClick(View v) {
        	trafficViewPager.setCurrentItem(clickIndex);
        	
        	for(int i=0;i<listViews.size();i++) {
				FDSearchTrafficMainView trafficLocationView = (FDSearchTrafficMainView) listViews.get(i);
				trafficLocationView.setSettingViewVisible(false);
				
				if(i==clickIndex) {
					trafficLocationView.locationRoute();
				}
			}
        }
    };
    
    
    public void onClick(View view) {
		if(view==backLayout){
			finish();
		}
	}
    

	public void onLocationChanged(Location location) {

	}

	public void onProviderDisabled(String provider) {
		
	}

	public void onProviderEnabled(String provider) {
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

	/**
	 * GPS定位回调方法
	 */
	public void onLocationChanged(AMapLocation location) {
		if (changedListener != null && location != null) {
			changedListener.onLocationChanged(location);   // 显示系统小蓝点
		}
		if (location != null) {
			Bundle bundle = location.getExtras();
			Double geoLat = location.getLatitude();
			Double geoLng = location.getLongitude();
			String str = ("定位成功:(" + geoLng + "," + geoLat + ")"
					+ "\n精    度    :" + location.getAccuracy() + "米"
					+ "\n定位方式: " + location.getProvider() + "\n定位时间:" + FDAMapUtil.convertToTime(location.getTime()))
					+ "\n地址: "+ bundle.getString("desc");
//			trafficLocationText.setText(str);
//			Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
			
			FDLocation fdLocation = new FDLocation();
			fdLocation.setLatitude(location.getLatitude());
			fdLocation.setLongitude(location.getLongitude());
			fdLocation.setAccuracy(location.getAltitude());
			fdLocation.setProviderType(location.getProvider());
			fdLocation.setLocationTime(FDAMapUtil.convertToTime(location.getTime()));
			fdLocation.setCityCode(bundle.getString("citycode"));
			fdLocation.setAddressCode(bundle.getString("adcode"));
			fdLocation.setAddressDesc(bundle.getString("desc"));
			
			for(int i=0;i<listViews.size();i++) {
				FDSearchTrafficMainView trafficLocationView = (FDSearchTrafficMainView) listViews.get(i);
				trafficLocationView.setTrafficLocation(fdLocation);
				if(i==0) {
					trafficLocationView.locationRoute();
				}
			}
			stopLocation();
		}
	}
	
	protected void onPause() {
		super.onPause();
		routerMapView.onResume();
		
		stopLocation();    // 停止定位
	}

	protected void onDestroy() {
		stopLocation();
		routerMapView.onResume();
		
		super.onDestroy();
	}
	
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		routerMapView.onSaveInstanceState(outState);
	}
	
	protected void onResume() {
		super.onResume();
		routerMapView.onResume();
	}
	
	public void initMapView() {
		if (routerMap == null) {
			routerMap = routerMapView.getMap();
			LatLng initLatLng = new LatLng(121.521653,31.07894);
			routerMap.moveCamera(CameraUpdateFactory.changeLatLng(initLatLng));
			
			setUpMarkerMap();
		}
	}
	
	public void initRouterMapMarker() {
		MarkerOptions markerOption = new MarkerOptions();
    	markerOption.anchor(0.5f, 0.5f);
    	markerOption.draggable(true);
    	
    	if(restaurantDetail==null) return;
    	String name = restaurantDetail.getName();
    	int creditRating = restaurantDetail.getFoodSaftyRating();
    	double latitude = restaurantDetail.getGisLatitude();
    	double longitude = restaurantDetail.getGisLongitude();
    	
    	markerOption.position(new LatLng(latitude, longitude));
    	markerOption.title(name);
    	if(creditRating==21004) {
    		markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_green));
    	}else if(creditRating==21003) {
    		markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_orange));
    	}else if (creditRating==21002){
    		markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_red));
    	}else {
    		markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_gray));
    	}
    	routerMap.addMarker(markerOption).showInfoWindow();
	}
	
	private void setUpMarkerMap() {
		routerMap.setOnMapLoadedListener(this);                     
		routerMap.setOnMarkerClickListener(this);                  
		routerMap.setOnInfoWindowClickListener(this);                 
		routerMap.setInfoWindowAdapter(this);                         
		
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_location)); 
		myLocationStyle.strokeColor(Color.BLACK);                       // 设置圆形的边框颜色
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));    // 设置圆形的填充颜色
		// myLocationStyle.anchor(int,int)                              // 设置小蓝点的锚点
		myLocationStyle.strokeWidth(1.0f);                              // 设置圆形的边框粗细
		routerMap.setMyLocationStyle(myLocationStyle);
		routerMap.setLocationSource(this);                            // 设置定位监听
		routerMap.getUiSettings().setMyLocationButtonEnabled(true);   // 设置默认定位按钮是否显示
		routerMap.setMyLocationEnabled(true);                         // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
	}
	
	public void startLocationManager() {
//		openGPSSettings(this);
		locationManager = LocationManagerProxy.getInstance(this);
		// API定位采用GPS定位方式，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
		locationManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 8000, 10, this);
	}
	
	/**
	 * 销毁定位
	 */
	public void stopLocation() {
		System.out.println("stop location--------------------------------------");
		if (locationManager != null) {
			locationManager.removeUpdates(this);
			locationManager.destory();
		}
		locationManager = null;
	}
	
	public static void openGPSSettings(Context context) {
		// 获取位置服务
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		// 若GPS未开启
		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast.makeText(context, "请开启GPS！", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			context.startActivity(intent);
		}
	}

	public void onInfoWindowClick(Marker arg0) {
		
	}

	public boolean onMarkerClick(Marker arg0) {
		return false;
	}

	public void onMapLoaded() {
		
	}

	public View getInfoContents(Marker arg0) {
		return null;
	}

	public View getInfoWindow(Marker arg0) {
		return null;
	}

	public void activate(OnLocationChangedListener changedListener) {
		this.changedListener = changedListener;
	}

	public void deactivate() {
		changedListener = null;
	}
}
