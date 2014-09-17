package com.wondersgroup.fdpublicapp.start.activity;

import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDDialogListener;
import com.wondersgroup.fdpublicapp.common.protocol.AbstractLoadServer;
import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;
import com.wondersgroup.fdpublicapp.common.protocol.AppLoadServer;
import com.wondersgroup.fdpublicapp.common.protocol.AbstractLoadServer.OnLoadedCallbackListener;
import com.wondersgroup.fdpublicapp.common.util.FileUtil;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.activity.FDMainActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 应用程序启动类：显示欢迎界面并跳转到主界面
 * @author chengshaohua
 * @version 1.0
 * @created 2014-2-16
 */

public class FDStartActivity extends FDBaseActivity {

	public Timer checkTimer;
	public boolean timerFlag = false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.fd_app_start, null);
//		final View view = View.inflate(this, R.layout.fd_activity_main, null);
		setContentView(view);
		
		initStartLoading();
		initCheckServer();
		
		// 渐变展示启动屏
		AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
		animation.setDuration(2000);    // 2000
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation end) {
//				loaderOverRedirectTo();
			}
			public void onAnimationRepeat(Animation animation) {
			}
			public void onAnimationStart(Animation animation) {
				initStartContext();
//				initCheckUpVersion();
			}
		});
	}

	public void initCheckServer() {
		checkTimer = new Timer("检查服务..", true);
		TimerTask task = new TimerTask() {
			public void run() {
				FDStartActivity.this.runOnUiThread(new Runnable(){
					public void run() {
						if(timerFlag) {
							initStartContext();
						}
					}
				});
			}
		};
		checkTimer.scheduleAtFixedRate(task, 8000, 20000); 
	}
	
	public void initStartContext() {
		if(((FDAppContext)getApplication()).isNetworkConnected()) {
			if(checkTimer!=null) {
				checkTimer.cancel();
			}
			loadingOtherPropterties();
		}else {
			FDViewUtil.showToast(context, getResources().getString(R.string.app_start_disable_message), true);
		}
	}
	
	public void loadingOtherPropterties() {
		AppLoadServer loadServer = new AppLoadServer();
		loadServer.loadMainData(new OnLoadedCallbackListener() {
			public void onSuccessCallback() {
				new Thread() {
					public void run() {
						loaderAssetFile();
						loaderOverRedirectTo();
					}
				}.start();
			}
		}); 
	}

	/**
     * 跳转到...
     */
    private void loaderOverRedirectTo(){        
        Intent intent = new Intent(this, FDMainActivity.class);
        startActivity(intent);
        finish();
        
    }
    
    // 初始化基本信息
    private void loaderAssetFile() {
    	String fileDictionary = "common/";
    	String commerical = FileUtil.getFromAssets(this, fileDictionary+"commerical.txt");
    	String atmosphere = FileUtil.getFromAssets(this, fileDictionary+"atmosphere.txt");
    	String cuisine = FileUtil.getFromAssets(this, fileDictionary+"cuisine.txt");
    	String safetyRating = FileUtil.getFromAssets(this, fileDictionary+"safetyRating.txt");
    	String vegetablesType = FileUtil.getFromAssets(this, fileDictionary+"vegetablesType.txt");
    	String averageConsumption = FileUtil.getFromAssets(this, fileDictionary+"averageConsumption.txt");
    	try {
			AbstractLoadServer.loadBasicInformation(commerical, atmosphere, cuisine, safetyRating, vegetablesType, averageConsumption);
		} catch (JSONException e) {
			e.printStackTrace();
		}
    }
    
    public void initCheckUpVersion() {
    	if(appContext!=null) {
    		timerFlag = false;
    		PackageInfo packageInfo = appContext.getPackageInfo();
    		System.out.println("Version Name--"+packageInfo.versionName+"  Version Code--"+packageInfo.versionCode);
    		onDialogButton(context,"版本更新","检查到有新版本，你需要版本升级吗？", "继续", new FDDialogListener(){
				public void onPositive() {
					timerFlag = true;
				}

				public void onNegative() {
					timerFlag = true;
					initStartContext();
				}
    		});
    	}
    }
    
    protected void onDestroy() {
		super.onDestroy();
		if(checkTimer!=null) {
			timerFlag = false;
			checkTimer.cancel();
			checkTimer = null;
		}
    }
    
	protected void onStop() {
		super.onStop();
		timerFlag = false;
	}
	
	protected void onStart() {
		super.onStart();
		timerFlag = true;
	}
}
