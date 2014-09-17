package com.wondersgroup.fdpublicapp.personal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDCustomDialog;
import com.wondersgroup.fdpublicapp.common.custom.FDCustomDialog.Builder;
import com.wondersgroup.fdpublicapp.common.custom.FDUpdateManager;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;
import com.wondersgroup.fdpublicapp.personal.views.FDUserCenterView;

/**
 *  个人中心设置
 * @author chengshaohua
 *
 */
public class FDUserCenterConfigActivity extends FDBaseActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		setContentView(R.layout.fd_usercenter_config_main);

		initUserCenterConfigView();
	}

	/**
	 *  初始化个人设置
	 */
	public void initUserCenterConfigView() {
		LinearLayout backLayout = (LinearLayout) findViewById(R.id.fd_usercenter_back_label);
		RelativeLayout introduceLayout = (RelativeLayout) findViewById(R.id.fd_usercenter_introduce_rll);
		RelativeLayout versionLayout = (RelativeLayout) findViewById(R.id.fd_usercenter_version_rll);
		RelativeLayout protocolLayout = (RelativeLayout) findViewById(R.id.fd_usercenter_protocol_rll);
		RelativeLayout updateLayout = (RelativeLayout) findViewById(R.id.fd_usercenter_update_rll);
		RelativeLayout logoutLayout = (RelativeLayout) findViewById(R.id.fd_usercenter_exit_rll);
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				finish();
			}
		});
		introduceLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				directionConfigurationActivity(getResources().getString(R.string.usercenter_login_config_introducion));
			}
		});
		versionLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				directionConfigurationActivity(getResources().getString(R.string.usercenter_login_config_version));
			}
		});
		protocolLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				directionConfigurationActivity(getResources().getString(R.string.usercenter_login_config_protocol));
			}
		});
		updateLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				updateVersion();
			}
		});
		
		// 清除用户登录信息(用户退出)
		logoutLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				logoutUserCenter();
			}
		});
	}
	
	/**
	 * 版本更新
	 */
	public void updateVersion() {
		FDUpdateManager.getUpdateManager().checkAppUpdate(this, true);
		
//		final FDCustomDialog.Builder builder = new Builder(this);
//		builder.setTitle("版本更新吗？");
//		builder.setMessage("当前1.0.0，最新1.2.0");
//		builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
//			public void onClick(final DialogInterface dialog, int which) {
//				dialog.dismiss();
//				
//				
//			}
//		});
//		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
//		builder.create().show();
	}
	
	/**
	 * 用户退出
	 */
	public void logoutUserCenter() {
		final FDCustomDialog.Builder builder = new Builder(this);
		builder.setMessage("你要退出当前账号吗？");
		builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, int which) {
				dialog.dismiss();
				FDPersonalWrapper personalWrapper = new FDPersonalWrapper(context);
				personalWrapper.getPersonalLogout(new FDCallback(){
					public void onCallback(Object callback) {
						if(callback==null) return;
//						int logoutStatus = (Integer) callback;
//						if(logoutStatus==0) {
//							
//						}else {
//							FDViewUtil.showToast(context, "用户退出失败..");
//						}
						if(appContext!=null) {
							appContext.cleanLoginInfo();
							appContext.setProperty(FDUserCenterView.FD_USER_CENTER_COMPLETED, false);
							sendBroadcast(new Intent(FDUserCenterView.FD_USER_CENTER_LOGOUT));
						}
						finish();
					}
				});
				
			}
		});
		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	
	/**
	 * 个人中心设置软件介绍、版本声明、许可协议
	 * @param title
	 */
	public void directionConfigurationActivity(String title) {
		Intent intent = new Intent(FDUserCenterConfigActivity.this,FDUserCenterProtocolActivity.class);
		intent.putExtra(FDUserCenterProtocolActivity.FD_USER_CENTER_TITLE_KEY, title);
		startActivity(intent);
	}
}
