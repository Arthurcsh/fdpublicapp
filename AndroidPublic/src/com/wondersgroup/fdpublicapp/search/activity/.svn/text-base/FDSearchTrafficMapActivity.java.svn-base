package com.wondersgroup.fdpublicapp.search.activity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.overlay.BusRouteOverlay;
import com.amap.api.maps.overlay.DrivingRouteOverlay;
import com.amap.api.maps.overlay.RouteOverlay;
import com.amap.api.maps.overlay.WalkRouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.WalkPath;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.adapter.TrafficListAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDBusRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDDriveRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDWalkRoute;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author chengshaohua
 *  交通地图
 */
public class FDSearchTrafficMapActivity extends Activity implements OnMarkerClickListener,
                                                OnMapClickListener, OnInfoWindowClickListener, 
                                                InfoWindowAdapter, OnMapLoadedListener, OnClickListener{
	
	public final static String FD_TRAFFIC_ROUTE_PATH_KEY = "search.traffic.route.path.key";
	private LinearLayout backLayout;
	private MapView routeMapView;  
	private AMap routeMap; 
	private FDSuperRoute mapRoute;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_restaurant_traffic_map_details);
		routeMapView = (MapView) findViewById(R.id.fd_traffic_route_map_view);
		routeMapView.onCreate(savedInstanceState); 
		
		initTrafficMapView();
	}
	
	private void initTrafficMapView() {
		backLayout = (LinearLayout) findViewById(R.id.traffic_map_img_ll);
		backLayout.setOnClickListener(this);

		Bundle bundle = getIntent().getExtras();  
		FDSuperRoute transRoute =  bundle.getParcelable(FD_TRAFFIC_ROUTE_PATH_KEY);  
        initTrafficRouteMap(transRoute);
	}
	
	public void initTrafficRouteMap(FDSuperRoute superRoute) {
		this.mapRoute = superRoute;
		
		if (routeMap == null) {
			routeMap = routeMapView.getMap();
			registerListener();
			
			LatLng initLatLng = new LatLng(121.521653,31.07894);
			routeMap.moveCamera(CameraUpdateFactory.changeLatLng(initLatLng));
		}
		routeMap.clear();      // 清理地图上的所有覆盖物
	}
	
	/**
	 * 注册监听
	 */
	private void registerListener() {
		routeMap.setOnMapClickListener(this);
		routeMap.setOnMarkerClickListener(this);
		routeMap.setOnInfoWindowClickListener(this);
		routeMap.setInfoWindowAdapter(this);
		routeMap.setOnMapLoadedListener(this);
	}
	
	public void onClick(View view) {
		if(view==backLayout){
			finish();
		}
	}

	
	protected void onResume() {
		super.onResume();
		routeMapView.onResume();
	}

	
	protected void onPause() {
		super.onPause();
		routeMapView.onPause();
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		routeMapView.onSaveInstanceState(outState);
	}

	protected void onDestroy() {
		super.onDestroy();
		routeMapView.onDestroy();
	}
	
	public void onMapload() {
		
	}
	
	public void onMapClick(LatLng latLng) {
		
	}

	public boolean onMarkerClick(Marker marker) {
		return false;
	}

	public View getInfoContents(Marker arg0) {
		return null;
	}

	public View getInfoWindow(Marker arg0) {
		return null;
	}

	public void onInfoWindowClick(Marker arg0) {
		
	}

	public void onMapLoaded() {
		if(mapRoute!=null) {
			TextView startWalkText = (TextView) this.findViewById(R.id.traffic_map_start_walk_tv);
			TextView targetWalkText = (TextView) this.findViewById(R.id.traffic_map_walk_last_tv);
			TextView busTextView = (TextView) this.findViewById(R.id.traffic_map_bus_tv);
			
			LatLonPoint startLatLonPoint = mapRoute.getStartPoint();
			LatLonPoint targetLatLonPoint = mapRoute.getTargetPoint();
			
			RouteOverlay routeOverlay = null;
			if(mapRoute instanceof FDBusRoute) {
				FDBusRoute fdBusRoute = (FDBusRoute) mapRoute;
				BusPath busPath = fdBusRoute.getRouteBusPath();
				routeOverlay = new BusRouteOverlay(this, routeMap, busPath, startLatLonPoint, targetLatLonPoint);
				routeOverlay.removeFromMap();
				((BusRouteOverlay) routeOverlay).addToMap();
				
				startWalkText.setText("步行"+fdBusRoute.getStartWalkDistance()+"米");
				// 2014-08-04目的地步行距离
				targetWalkText.setText("步行"+(busPath.getWalkDistance()-fdBusRoute.getStartWalkDistance())+"米");
			}else if(mapRoute instanceof FDDriveRoute) {
				DrivePath drivePath = ((FDDriveRoute)mapRoute).getRouteDrivePath();
				routeOverlay = new DrivingRouteOverlay(this, routeMap, drivePath, startLatLonPoint, targetLatLonPoint);
				routeOverlay.removeFromMap();
				((DrivingRouteOverlay) routeOverlay).addToMap();
			}else if(mapRoute instanceof FDWalkRoute) {
				WalkPath walkPath = ((FDWalkRoute)mapRoute).getRouteWalkPath();
				routeOverlay = new WalkRouteOverlay(this, routeMap, walkPath, startLatLonPoint, targetLatLonPoint);
				routeOverlay.removeFromMap();
				((WalkRouteOverlay) routeOverlay).addToMap();
				startWalkText.setText("步行"+walkPath.getDistance()+"米");
			}
			routeOverlay.zoomToSpan();
			
			if(mapRoute!=null && mapRoute.getLines()!=null) {
				String lineString = TrafficListAdapter.getRouteLine(mapRoute);
				if(mapRoute instanceof FDWalkRoute) {
					targetWalkText.setText(lineString);
				}else {
					busTextView.setText(lineString);
				}
			}
			
		}
		
	}
}
