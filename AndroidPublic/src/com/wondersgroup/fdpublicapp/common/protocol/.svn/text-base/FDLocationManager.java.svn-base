package com.wondersgroup.fdpublicapp.common.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.core.GeoPoint;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Tip;
import com.amap.api.services.help.Inputtips.InputtipsListener;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.wondersgroup.fdpublicapp.search.mode.FDBusLine;
import com.wondersgroup.fdpublicapp.search.mode.FDBusRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDDriveLine;
import com.wondersgroup.fdpublicapp.search.mode.FDDriveRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperLine;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperRoute;
import com.wondersgroup.fdpublicapp.search.mode.FDWalkLine;
import com.wondersgroup.fdpublicapp.search.mode.FDWalkRoute;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.Toast;

public class FDLocationManager {

	/**
	 * 
	 * 判断GPS是否开启，若未开启，则进入GPS设置页面；设置完成后需用户手动回界面
	 * 
	 * @param currentActivity
	 * @return
	 */
	public static void openGPSSettings(Context context) {
		// 获取位置服务
		LocationManager lm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		// 若GPS未开启
		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast.makeText(context, "请开启GPS！", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			context.startActivity(intent);
		}
	}

	/**
	 * 
	 * 判断GPS是否开启，若未开启，则进入GPS设置页面；设置完成后仍回原界面
	 * 
	 * @param currentActivity
	 * @return
	 */
	public static void openGPSSettings(Activity currentActivity) {
		// 获取位置服务
		LocationManager lm = (LocationManager) currentActivity
				.getSystemService(Context.LOCATION_SERVICE);
		// 若GPS未开启
		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast.makeText(currentActivity, "请开启GPS！", Toast.LENGTH_SHORT)
					.show();
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			currentActivity.startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面
		}
	}

	/**
	 * 使用高德定位获取经纬度，包括GPS获取，网络获取;
	 * 
	 * @param context
	 *            上下文环境
	 * @param locationListener
	 *            位置监听实例
	 * @return HashMap<String,Location>
	 *         返回Location实例的HashMap，其中，GPS对应的Location实例对应的Key值为
	 *         "gps",网络为"network";
	 */
	public static Map<String, Location> getLocationObject(Context context,
			LocationListener locationListener) {
		Map<String, Location> lMap = new HashMap<String, Location>();

		LocationManagerProxy locationManager = LocationManagerProxy
				.getInstance(context);
		for (final String provider : locationManager.getProviders(true)) {

			// GPS
			if (LocationManagerProxy.GPS_PROVIDER.equals(provider)) {

				Location l = locationManager.getLastKnownLocation(provider);
				if (null != l) {
					lMap.put("gps", l);
				}
				// locationManager.requestLocationUpdates(provider, 5000,
				// 10,locationListener);
				break;
			}

			// 网络 定位服务开启
			if (LocationManagerProxy.NETWORK_PROVIDER.equals(provider)) {

				Location l = locationManager.getLastKnownLocation(provider);
				if (null != l) {
					lMap.put("network", l);
				}
				// locationManager.requestLocationUpdates(provider, 5000,
				// 10,locationListener);
				break;
			}

		}
		return lMap;
	}

	/**
	 * 使用高德地理解析，根据经纬度获取对应地址,；<br>
	 * 若使用百度地图经纬度，须经过百度API接口(BMap.Convertor.transMore(points,2,callback))的转换；
	 * 
	 * @param context
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @return 详细街道地址
	 */
	public static String getAddress(Context context, double longitude,
			double latitude) {
		String address = null;

		GeoPoint geo = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
		Geocoder mGeocoder = new Geocoder(context);

		int x = geo.getLatitudeE6();// 得到geo 纬度，单位微度(度* 1E6)
		double x1 = ((double) x) / 1000000;
		int y = geo.getLongitudeE6();// 得到geo 经度，单位微度(度* 1E6)
		double y1 = ((double) y) / 1000000;

		// 得到逆理编码，参数分别为：纬度，经度，最大结果集
		// try {
		// 高德根据政府规定，在由GPS获取经纬度显示时，使用getFromRawGpsLocation()方法;
		// List<Address> listAddress = mGeocoder.getFromRawGpsLocation(x1, y1,
		// 3);
		// if (listAddress.size() != 0) {
		// Address a = listAddress.get(0);

		/*
		 * sb.append("getAddressLine(0)"+a.getAddressLine(0)+"\n");
		 * sb.append("a.getAdminArea()"+a.getAdminArea()+"\n");
		 * sb.append("a.getCountryName()"+a.getCountryName()+"\n");
		 * 
		 * sb.append("getFeatureName()"+a.getFeatureName()+"\n");
		 * sb.append("a.getLocality()"+a.getLocality()+"\n");
		 * sb.append("a.getMaxAddressLineIndex()"
		 * +a.getMaxAddressLineIndex()+"\n");
		 * sb.append("getPhone()"+a.getPhone()+"\n");
		 * sb.append("a.getPremises()"+a.getPremises()+"\n");
		 * sb.append("a.getSubAdminArea()"+a.getSubAdminArea()+"\n");
		 * 
		 * 
		 * sb.append("a.getSubLocality()"+a.getSubLocality()+"\n"); sb.append
		 * ("getSubThoroughfare()"+a.getSubThoroughfare()+"\n");
		 * sb.append("a.getThoroughfare()"+a.getThoroughfare()+"\n");
		 * sb.append("a.getUrl()"+a.getUrl()+"\n");
		 */
		// address = a.getCountryName()
		// + a.getLocality()
		// + (a.getSubLocality() == null ? "" : a.getSubLocality())
		// + (a.getThoroughfare() == null ? "" : a
		// .getThoroughfare())
		// + (a.getSubThoroughfare() == null ? "" : a
		// .getSubThoroughfare()) + a.getFeatureName();
		// }
		// } catch (AMapException e) {
		// e.printStackTrace();
		// }

		return address;

	}

	// 兴趣点关键字查询
	public void getQueryKeyword(Context context, String keyText, String cityText) {
		Inputtips inputTips = new Inputtips(context, new InputtipsListener() {
			public void onGetInputtips(List<Tip> tipList, int rCode) {
				if (rCode == 0) { // 正确返回
					final List<String> listString = new ArrayList<String>();
					for (int i = 0; i < tipList.size(); i++) {
						listString.add(tipList.get(i).getName());
					}
					// TrafficLocationAdapter locationAdapter = new
					// TrafficLocationAdapter(context, listString);
					// settingListView.setAdapter(locationAdapter);
					// settingListView.setOnItemClickListener(new
					// OnItemClickListener(){
					// public void onItemClick(AdapterView<?> adapter, View
					// view, int position,long arg3) {
					// selectKeywordLocation(""+adapter.getItemAtPosition(position));
					// }
					// });
					// locationAdapter.notifyDataSetChanged();
				}
			}
		});
		try {
			inputTips.requestInputtips(keyText, cityText);// 第一个参数表示提示关键字，第二个参数默认代表全国，也可以为城市区号
		} catch (AMapException e) {
			e.printStackTrace();
		}
	}

	// 公交路线规划
	public static ArrayList<FDSuperRoute> getBusRoute(BusRouteResult routeResult) {
		if(routeResult==null) return null;
		
		ArrayList<FDSuperRoute> busRoutes = null;
		List<BusPath> busPaths = routeResult.getPaths();
		if(busPaths!=null) {
			busRoutes = new ArrayList<FDSuperRoute>();
			for(int i=0;i<busPaths.size();i++) {
				BusPath busPath = busPaths.get(i);
				FDBusRoute fdBusRoute = new FDBusRoute();
				fdBusRoute.setDistance(busPath.getBusDistance());
				fdBusRoute.setPrice(busPath.getCost());
				fdBusRoute.setStartPoint(routeResult.getStartPos());
				fdBusRoute.setTargetPoint(routeResult.getTargetPos());
				fdBusRoute.setRouteBusPath(busPath);
				if(busPath!=null) {
					ArrayList<FDSuperLine> busLines = new ArrayList<FDSuperLine>();
					if(busPath.getSteps()!=null) {
						for(int n=0;n<busPath.getSteps().size();n++) {
							BusStep busStep = busPath.getSteps().get(n);
							if(busStep!=null && busStep.getBusLine()!=null) {
								RouteBusLineItem busLineItem = busStep.getBusLine();
								if(busLineItem!=null) {
									FDBusLine fdBusLine = new FDBusLine();
									fdBusLine.setLineName(busLineItem.getBusLineName());
									fdBusLine.setBusDuration(busLineItem.getDuration());
									fdBusLine.setBusDistance(busLineItem.getDistance());
									if(n==0) {
										fdBusRoute.setStartWalkDistance(busStep.getWalk().getDistance());
									}
									busLines.add(fdBusLine);
								}
							}
						}
					}
					fdBusRoute.setWalkDistance(busPath.getWalkDistance());
					fdBusRoute.setLines(busLines);
					busRoutes.add(fdBusRoute);
				}
			}
		}
		return busRoutes;
	}
	
	public static ArrayList<FDSuperRoute> getDriveRoute(DriveRouteResult routeResult) {
		if(routeResult==null) return null;
		
		List<DrivePath> drivePaths = routeResult.getPaths();
		if(drivePaths==null) return null;
		ArrayList<FDSuperRoute> driveRoutes = new ArrayList<FDSuperRoute>();
		for(int i=0;i<drivePaths.size();i++) {
			DrivePath drivePath = drivePaths.get(i);
			FDDriveRoute driveRoute = new FDDriveRoute();
			if(drivePath==null) continue;
			driveRoute.setDistance(drivePath.getDistance());
			driveRoute.setDriveStrategy(drivePath.getStrategy());
			driveRoute.setStartPoint(routeResult.getStartPos());
			driveRoute.setTargetPoint(routeResult.getTargetPos());
			driveRoute.setRouteDrivePath(drivePath);
			List<DriveStep> driveSteps = drivePath.getSteps();
			if(driveSteps!=null) {
				List<FDSuperLine> driveLines = new ArrayList<FDSuperLine>();
				for(int n=0;n<driveSteps.size();n++) {
					DriveStep driveStep = driveSteps.get(n);
					if(driveStep==null) continue;
					FDDriveLine driveLine = new FDDriveLine();
					driveLine.setLineName(driveStep.getRoad());
					
					driveLines.add(driveLine);
				}
				driveRoute.setLines(driveLines);
			}
			driveRoutes.add(driveRoute);
		}
		return driveRoutes;
	}
	
	// 步行路线规划
	public static ArrayList<FDSuperRoute> getWalkRoute(WalkRouteResult routeResult) {
		if(routeResult==null) return null;
		
		List<WalkPath> walkPaths = routeResult.getPaths();
		ArrayList<FDSuperRoute> walkRoutes = new ArrayList<FDSuperRoute>();
		if(walkPaths==null) return null;
		for(int i=0;i<walkPaths.size();i++) {
			WalkPath walkPath = walkPaths.get(i);
			FDWalkRoute walkRoute = new FDWalkRoute();
			if(walkPath==null) continue;
			walkRoute.setDistance(walkPath.getDistance());
			walkRoute.setDuration(walkPath.getDuration());
			walkRoute.setStartPoint(routeResult.getStartPos());
			walkRoute.setTargetPoint(routeResult.getTargetPos());
			walkRoute.setRouteWalkPath(walkPath);
			List<WalkStep> walkSteps = walkPath.getSteps();
			if(walkSteps!=null) {
				List<FDSuperLine> walkLines = new ArrayList<FDSuperLine>();
				for(int n=0;n<walkSteps.size();n++) {
					WalkStep walkStep = walkSteps.get(n);
					if(walkStep==null) continue;
					FDWalkLine walkLine = new FDWalkLine();
					walkLine.setLineName(walkStep.getRoad());
					walkLine.setWalkDuration(walkStep.getDuration());
					walkLine.setWalkInstruction(walkStep.getInstruction());
					walkLines.add(walkLine);
				}
				walkRoute.setLines(walkLines);
			}
			walkRoutes.add(walkRoute);
		}
		return walkRoutes;
	}
	
}
