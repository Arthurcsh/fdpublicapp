package com.wondersgroup.fdpublicapp.home.main.activity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.wondersgroup.fdpublicapp.R;
import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FDMapActivity extends Activity implements LocationSource, AMapLocationListener, 
                                                       OnMarkerClickListener, OnInfoWindowClickListener, OnMapLoadedListener,
                                                       OnMarkerDragListener,OnClickListener, InfoWindowAdapter{
	private MapView LoactionMapView;  
	private AMap LocationMap; 
	private OnLocationChangedListener changedListener;
	private LocationManagerProxy fdMapLocationManager;
	private TextView searchResultText;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fd_super_map);  
		LoactionMapView = (MapView) findViewById(R.id.fd_super_map_view);  
		LoactionMapView.onCreate(savedInstanceState);    // 必须要写 

		initMapView();
		initMapMarker();
		onMapListener();
	}
	
	public void initMapMarker() {
		Bundle bundle = getIntent().getExtras();  
		if(bundle==null || !bundle.containsKey("gisLocation")) return;
		String[] gisLocation = bundle.getStringArray("gisLocation");
		if(gisLocation==null) return;
		
		MarkerOptions markerOption = new MarkerOptions();
    	markerOption.anchor(0.5f, 0.5f);
    	markerOption.draggable(true);
        for(int i=0;i<gisLocation.length;i++) {
        	String[] restaurantArray = gisLocation[i].split(",");
        	String name = restaurantArray[0];
        	int creditRating = Integer.parseInt(restaurantArray[1]);
        	double latitude = Double.parseDouble(restaurantArray[2]);
        	double longitude = Double.parseDouble(restaurantArray[3]);
        	
        	markerOption.position(new LatLng(latitude, longitude));
        	markerOption.title(name);
        	if(creditRating==21004) {
        		markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_green));
        	}else if(creditRating==21003) {
        		markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_orange));
        	}else {
        		markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_red));
        	}
        	LocationMap.addMarker(markerOption).showInfoWindow();
        }
        
        searchResultText = (TextView) this.findViewById(R.id.header_map_result_number);
        searchResultText.setText(getString(R.string.search_header_result_label)+"("+gisLocation.length+")");
//        drawMarkers();
	}
	
	/**
	 * 绘制系统默认的1种marker背景图片
	 */
	public void drawMarkers() {
		LatLng latlng = new LatLng(36.061, 103.834);
		Marker marker = LocationMap.addMarker(new MarkerOptions()
				.position(latlng)
				.title("定位餐厅")
				.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
				.draggable(true));
		marker.showInfoWindow();    // 设置默认显示一个infowinfow
	}
	
	public void onMapListener() {
		LinearLayout backButton = (LinearLayout) this.findViewById(R.id.header_map_back_Linear);
		backButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDMapActivity.this.finish();
			}
		}); 
		
		LinearLayout tableButton = (LinearLayout) this.findViewById(R.id.header_search_list_area);
		tableButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				FDMapActivity.this.finish();
			}
		});
	}
	
	public void initMapView() {
		if (LocationMap == null) {
			LocationMap = LoactionMapView.getMap();
			LatLng initLatLng = new LatLng(121.521653,31.07894);
			LocationMap.moveCamera(CameraUpdateFactory.changeLatLng(initLatLng));
			
			setUpMarkerMap();
		}
	}
	
	/**
	 * 设置一些amap的属性
	 */
	private void setUpMarkerMap() {
		LocationMap.setOnMarkerDragListener(this);                      // 设置marker可拖拽事件监听器
		LocationMap.setOnMapLoadedListener(this);                       // 设置amap加载成功事件监听器
		LocationMap.setOnMarkerClickListener(this);                     // 设置点击marker事件监听器
		LocationMap.setOnInfoWindowClickListener(this);                 // 设置点击infoWindow事件监听器
		LocationMap.setInfoWindowAdapter(this);                         // 设置自定义InfoWindow样式
		
		// 自定义系统定位小蓝点
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.search_map_location));   // 设置小蓝点的图标
		myLocationStyle.strokeColor(Color.BLACK);                       // 设置圆形的边框颜色
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));    // 设置圆形的填充颜色
		// myLocationStyle.anchor(int,int)                              // 设置小蓝点的锚点
		myLocationStyle.strokeWidth(1.0f);                              // 设置圆形的边框粗细
		LocationMap.setMyLocationStyle(myLocationStyle);
		LocationMap.setLocationSource(this);                            // 设置定位监听
		LocationMap.getUiSettings().setMyLocationButtonEnabled(true);   // 设置默认定位按钮是否显示
		LocationMap.setMyLocationEnabled(true);                         // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
	}
	
	/**
	 * 方法必须重写
	 */
	protected void onResume() {
		super.onResume();
		LoactionMapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	protected void onPause() {
		super.onPause();
		LoactionMapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		LoactionMapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	protected void onDestroy() {
		super.onDestroy();
		LoactionMapView.onDestroy();
	}

	public void onLocationChanged(Location arg0) {
		
	}

	public void onProviderDisabled(String arg0) {
		
	}

	public void onProviderEnabled(String arg0) {
		
	}

	public void onStatusChanged(String arg0, int arg1, Bundle bundle) {
		
	}

	/**
	 * 定位成功后回调函数
	 */
	public void onLocationChanged(AMapLocation mapLocation) {
		if (changedListener != null && mapLocation != null) {
			changedListener.onLocationChanged(mapLocation);   // 显示系统小蓝点
		}
	}

	/**
	 * 激活定位
	 */
	public void activate(OnLocationChangedListener changedListener) {
		this.changedListener = changedListener;
		if (fdMapLocationManager == null) {
//			fdMapLocationManager.setGpsEnable(false);      // 
			fdMapLocationManager = LocationManagerProxy.getInstance(this);
			fdMapLocationManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 5000, 10, this);
		}
	}

	public void deactivate() {
		changedListener = null;
		if (fdMapLocationManager != null) {
			fdMapLocationManager.removeUpdates(this);
			fdMapLocationManager.destory();
		}
		fdMapLocationManager = null;
	}

	public View getInfoContents(Marker marker) {
		return null;
	}

	public View getInfoWindow(Marker marker) {
		return null;
	}

	public void onClick(View view) {
		
	}

	public void onMarkerDrag(Marker marker) {
		
	}

	public void onMarkerDragEnd(Marker arg0) {
		
	}

	public void onMarkerDragStart(Marker arg0) {
		
	}

	public void onInfoWindowClick(Marker arg0) {
		
	}

	public boolean onMarkerClick(Marker arg0) {
		return false;
	}

	public void onMapLoaded() {
		
	}

}
