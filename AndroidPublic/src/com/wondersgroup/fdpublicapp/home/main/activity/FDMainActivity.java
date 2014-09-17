package com.wondersgroup.fdpublicapp.home.main.activity;

import com.amap.api.location.AMapLocation;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.qrcode.FDQrCodeActivity;
import com.wondersgroup.fdpublicapp.common.impl.FDDialogListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.hotFood.views.FDIndexDelicacyView;
import com.wondersgroup.fdpublicapp.home.main.mode.FDLocation;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsActivity;
import com.wondersgroup.fdpublicapp.search.views.FDSearchView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 食安主页
 * @author chengshaohua
 *  
 */
public class FDMainActivity extends FDTabBarActivity {
	public static final String FD_MAIN_LOCATION = "fd.main.location";
	public FDIndexDelicacyView hotFoodDelicacyView;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initMainView();
		initApplicationLocation();
		onActionListener();
	}

	private BroadcastReceiver locationReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
//			context.unregisterReceiver(this);
			if (FD_MAIN_LOCATION.equals(intent.getAction())) {
				initApplicationLocation();
			}
		}
	};
	
	/**
	 * 初始化主页视图
	 */
	public void initMainView() {
		hotFoodDelicacyView = (FDIndexDelicacyView) findViewById(R.id.fd_main_index_table_view);
		IntentFilter locationFilter = new IntentFilter();
		locationFilter.addAction(FD_MAIN_LOCATION);
		context.registerReceiver(locationReceiver, locationFilter);
		
	}
	
	public void onActionListener() {
		Button delicacyButton = (Button) findViewById(R.id.main_index_delicacy);
		final FDSearchView searchView = (FDSearchView) findViewById(R.id.super_main_search_view);
		delicacyButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				searchView.excuteQueryCondition(1,null);
			}
		});
		Button todayCoupon = (Button) findViewById(R.id.main_index_coupon_today);
		todayCoupon.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				setPageIndex(2);
			}
		});
	}

	/**
	 * 二维码扫描后返回
	 */
	protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		if(resultCode==RESULT_OK) {
			String qrCode = data.getStringExtra(FDQrCodeActivity.FD_QR_CODE_RECEIVER);
			System.out.println("------------------- qr code --------------------"+qrCode);
			if(StringUtils.isNumber(qrCode)) {
				int restaurantId = StringUtils.toInt(qrCode);
				Intent intent = new Intent(context,FDSearchDetailsActivity.class);
				intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantId);
				context.startActivity(intent);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void onBackPressed() {
//		super.onBackPressed();
		onBackDialogButton(new FDDialogListener(){
			public void onPositive() {
				System.exit(0);
			}
			public void onNegative() {
			}
		});
	}

	/**
	 * 监听地理定位
	 */
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
			System.out.println("------------------  Location   ----------------------");
			hotFoodDelicacyView.setSearchLocationCondition(location);
		}else {
			locationMessage = getResources().getString(R.string.app_start_location_failure);
		}
		Toast.makeText(this, locationMessage, Toast.LENGTH_SHORT).show();
	}
	
	// 停止定位
	protected void onPause() {
		super.onPause();
		stopLocation();    
	}

	protected void onDestroy() {
		stopLocation();
		unregisterReceiver(locationReceiver);
		
		super.onDestroy();
	}
	
}
