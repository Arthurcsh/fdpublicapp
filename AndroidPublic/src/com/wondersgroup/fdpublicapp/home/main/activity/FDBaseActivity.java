package com.wondersgroup.fdpublicapp.home.main.activity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDCustomDialog;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.custom.FDCustomDialog.Builder;
import com.wondersgroup.fdpublicapp.common.impl.FDDialogListener;
import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;
import com.wondersgroup.fdpublicapp.common.protocol.AppLoadingView;
import com.wondersgroup.fdpublicapp.common.protocol.AppManager;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;

/**
 * 应用程序Activity的基类
 * 
 * @author chengshaohua
 * @version 1.0
 * @created 2012-2-18
 */

public class FDBaseActivity extends FragmentActivity implements AMapLocationListener{
	protected Context context;
	
	private boolean allowFullScreen = true;         // 是否允许全屏
	private boolean allowDestroy = true;            // 是否允许销毁
	private View view;
	protected AppLoadingView loading_imageview;
	public LocationManagerProxy locationManager = null;
	public FDAppContext appContext;
	public int pageNo = 1;
	public int totalPage;
	public final static int LOAD_MORE_MAX_COUNT  = 8;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		allowFullScreen = true;
		context = this;
		appContext = (FDAppContext)getApplication();
		
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	public void initStartLoading() {
		loading_imageview = (AppLoadingView) findViewById(R.id.main_loading_imageview);
		initLoadingImages();
		
		runOnUiThread(new Runnable(){
			public void run() {
				loading_imageview.startAnim();
			}
		});
	}
	
	public void initLoadingImages() {
		int[] imageIds = new int[6];
		imageIds[0] = R.drawable.loading_frame_1;
		imageIds[1] = R.drawable.loading_frame_2;
		imageIds[2] = R.drawable.loading_frame_3;
		imageIds[3] = R.drawable.loading_frame_4;
		imageIds[4] = R.drawable.loading_frame_5;
		imageIds[5] = R.drawable.loading_frame_6;

		loading_imageview.setImageIds(imageIds);
	}
	
	protected void onDestroy() {
		super.onDestroy();

		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	public boolean isAllowFullScreen() {
		return allowFullScreen;
	}

	/**
	 * 设置是否可以全屏
	 * 
	 * @param allowFullScreen
	 */
	public void setAllowFullScreen(boolean allowFullScreen) {
		this.allowFullScreen = allowFullScreen;
	}

	public void setAllowDestroy(boolean allowDestroy) {
		this.allowDestroy = allowDestroy;
	}

	public void setAllowDestroy(boolean allowDestroy, View view) {
		this.allowDestroy = allowDestroy;
		this.view = view;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && view != null) {
			view.onKeyDown(keyCode, event);
			if (!allowDestroy) {
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public static void onDialogButton(Context context, String title, String message, final FDDialogListener dialogListener) {
		onDialogButton(context,title,message,"确定","取消",dialogListener);
	}
	public static void onDialogButton(Context context, String title, String message, String negativeButton, final FDDialogListener dialogListener) {
		onDialogButton(context,title,message,"确定",negativeButton,dialogListener);
	}
	public static void onDialogButton(Context context, String title, String message, String positiveButton, String negativeButton, final FDDialogListener dialogListener) {
		final FDCustomDialog.Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(positiveButton, new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialogListener.onPositive();
				dialog.dismiss();
//				finish();
			}
		});
		builder.setNegativeButton(negativeButton, new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialogListener.onNegative(); 
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	
	public void onBackDialogButton(final FDDialogListener dialogListener) {
		final FDCustomDialog.Builder builder = new Builder(this);
		builder.setMessage("你要退出食安管家吗？");
		builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialogListener.onPositive();
				dialog.dismiss();
				finish();
			}
		});
		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialogListener.onNegative();
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	// 开启地图定位
	public void initApplicationLocation() {
		locationManager = LocationManagerProxy.getInstance(this);
		locationManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 8000, 50, this);
	}
	
	public void onLocationChanged(Location location) {
		
	}
	public void onProviderDisabled(String provider) {
		
	}
	public void onProviderEnabled(String provider) {
		
	}
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
	public void onLocationChanged(AMapLocation amapLocation) {
		String locationMessage;
		if (amapLocation != null) {
			Bundle bundle = amapLocation.getExtras();
			FDLocation location = new FDLocation();
			location.setLatitude(amapLocation.getLatitude());
			location.setLongitude(amapLocation.getLongitude());
			locationMessage = ("定位成功:(" + location.getLongitude() + "," + location.getLatitude() + ")")
					+ "\n地址: "+ bundle.getString("desc");
			
			ServiceManager.putValue(FDConst.FD_SERVER_LOCATION_POINT, location);
			stopLocation();
		}else {
			locationMessage = getResources().getString(R.string.app_start_location_failure);
		}
		FDViewUtil.showToast(this, locationMessage);
	}
	
	/**
	 * 停止定位
	 */
	public void stopLocation() {
		if (locationManager != null) {
			locationManager.removeUpdates(this);
			locationManager.destory();
		}
		locationManager = null;
	}
}
