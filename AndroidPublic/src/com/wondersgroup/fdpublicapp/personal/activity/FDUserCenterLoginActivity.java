package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.HashMap;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDClearEditText;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDBaseResult;
import com.wondersgroup.fdpublicapp.common.protocol.FDParseException;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.activity.FDMainActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;
import com.wondersgroup.fdpublicapp.personal.views.FDUserCenterView;

/**
 *  用户登录
 * @author mengke
 * 
 */
public class FDUserCenterLoginActivity extends FDBaseActivity implements Callback, PlatformActionListener{

	private static final int FD_MSG_USERID_FOUND = 1;
	private static final int FD_MSG_LOGIN = 2;
	private static final int FD_MSG_AUTH_CANCEL = 3;
	private static final int FD_MSG_AUTH_ERROR= 4;
	private static final int FD_MSG_AUTH_COMPLETE = 5;
	private static final String FD_MSG_LOGIN_FINISHED = "user.finished";
	
	private LinearLayout backMainLayout;
	private FDClearEditText loginNameText;
	private FDClearEditText passwordText;
	private CheckBox rememberPwCheckBox;
	private TextView lostPwTextView;
	private Button loginBtn;
	private TextView registerTextView;
	private LinearLayout registerLayout;
	private LinearLayout weiboImageView;
	private LinearLayout qqImageView;
	private LinearLayout weixinImageView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		
		preUserCentern();
		
		setContentView(R.layout.fd_usercenter_login);
		initUserCenterView();
	}

	public void preUserCentern() {
		ShareSDK.initSDK(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(FD_MSG_LOGIN_FINISHED);
		registerReceiver(mFinishReceiver, filter);
	}
	
	private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
//			unregisterReceiver(this);
			if (FD_MSG_LOGIN_FINISHED.equals(intent.getAction())) {
				finish();
			}
		}
	};

	/**
	 *  初始化用户登录
	 */
	private void initUserCenterView() {
		// 获取控件实例
		backMainLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_login_back);
		lostPwTextView = (TextView) findViewById(R.id.textv_fd_usercenter_login_lost_pw);
		registerLayout = (LinearLayout) findViewById(R.id.llo_fd_usercenter_login_register);
		registerTextView = (TextView) findViewById(R.id.textv_fd_usercenter_login_register);
		loginNameText = (FDClearEditText) findViewById(R.id.editt_fd_usercenter_login_loginName);
		passwordText = (FDClearEditText) findViewById(R.id.editt_fd_usercenter_login_pw);
		loginBtn = (Button) findViewById(R.id.imgv_fd_usercenter_login_btn);
		weiboImageView = (LinearLayout) findViewById(R.id.layout_fd_usercenter_login_thirdparty_weibo);
		qqImageView = (LinearLayout) findViewById(R.id.layout_fd_usercenter_login_thirdparty_qq);
		weixinImageView = (LinearLayout) findViewById(R.id.layout_fd_usercenter_login_thirdparty_weixin);

		// 给控件绑定事件
		backMainLayout.setOnClickListener(backOnClickListener);
		registerLayout.setOnClickListener(registerOnClickListener);
		lostPwTextView.setOnClickListener(lostPwOnClickListener);
		loginBtn.setOnClickListener(userLoginClickListener);
		weiboImageView.setOnClickListener(weiboLoginClickListener);
		qqImageView.setOnClickListener(qqLoginClickListener);
		weixinImageView.setOnClickListener(weixinLoginClickListener);

		// 给文字加下划线
		SpannableString lostPwContent = new SpannableString(lostPwTextView.getText());
		lostPwContent.setSpan(new UnderlineSpan(), 0, lostPwContent.length(), 0);
		lostPwTextView.setText(lostPwContent);
		SpannableString registerContent = new SpannableString(registerTextView.getText());
		registerContent.setSpan(new UnderlineSpan(), 0,registerContent.length(), 0);
		registerTextView.setText(registerContent);
	}
	
	private OnClickListener userLoginClickListener = new OnClickListener() {
		public void onClick(View view) {
			String loginName = loginNameText.getText().toString().trim();
			String loginPass = passwordText.getText().toString().trim();
			
			if(FDViewUtil.verifyTextview(loginNameText, "用户名") &&
					FDViewUtil.verifyTextview(passwordText, "密码")){
				
				FDUser loginUser = new FDUser();
				loginUser.setUsername(loginName);
				loginUser.setPassword(loginPass);
				loginUser.setId(1);
				userCenterLogin(loginUser);
			}
		}
	};
	
	/**
	 *  用户登录
	 * @param user 用户对象
	 */
	private void userCenterLogin(final FDUser user) {
		if(user==null) return;
		
		FDPersonalWrapper personalWrapper = new FDPersonalWrapper(this, true);
		personalWrapper.getPersonalLogin(user, new FDCallback(){
			public void onCallback(Object callback) {
				final FDResultParser resultParser = (FDResultParser) callback;
				if(resultParser==null) return;
				
				FDBaseResult baseResult = resultParser.getBaseResult();
//				FDViewUtil.showToast(FDUserCenterLoginActivity.this, baseResult.getMessage());
				FDUser loginUser = null;
				try {
					loginUser = resultParser.getResultData(FDUser.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				if(baseResult.getStatus()==0) {
					if(!StringUtils.isEmpty(loginUser)) {
						user.setId(1);
						user.setUsername(loginUser.getUsername());
						user.setNickName(loginUser.getNickName());
						user.setEmail(loginUser.getEmail());
						user.setMobilePhone(loginUser.getMobilePhone());
						user.setSex(loginUser.getSex());
						user.setBirthday(loginUser.getBirthday());
						user.setHeadImgUrl(loginUser.getHeadImgUrl());
						
						appContext.saveLoginInfo(user);
						new Handler().post(new Runnable() {
							public void run() {
								ShareSDK.stopSDK();
								sendBroadcast(new Intent(FD_MSG_LOGIN_FINISHED));
								sendBroadcast(new Intent(FDUserCenterView.FD_USER_CENTER_LOGIN));    // 更新用户信息
								sendBroadcast(new Intent(FDMainActivity.FD_MAIN_LOCATION));          // 更新主页
								FDViewUtil.showToast(context, user.getUsername()+"登陆成功", true);
								FDUserCenterLoginActivity.this.finish();
							}
						});
					}
				}else {
					FDViewUtil.showToast(context, "用户名或密码错误，请重新登陆..", true);
					loginNameText.clearText();
					passwordText.clearText();
					
					/** 第三方登陆需要注册跳转到用户注册 **/
					if(user.getExtraLoginType()!=null) {
						new Handler().post(new Runnable() {
							public void run() {
								Intent intent = new Intent(context,FDUserCenterRegisterNewUserActivity.class);
								intent.putExtra("fd.login.user", user);
								startActivity(intent);
							}
						});
					}
				}
			}
		});
	}
	
	// 找回密码
	private OnClickListener lostPwOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(context,FDUserCenterLostPwActivity.class);
			FDUserCenterLoginActivity.this.startActivity(intent);
		}
	};
	
	// 注册新用户
	private OnClickListener registerOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(context,FDUserCenterRegisterNewUserActivity.class);
			FDUserCenterLoginActivity.this.startActivity(intent);
		}
	};

	private OnClickListener weiboLoginClickListener = new OnClickListener() {
		public void onClick(View view) {
			authorize(new SinaWeibo(context));
		}
	};
	private OnClickListener qqLoginClickListener = new OnClickListener() {
		public void onClick(View view) {
			authorize(new QZone(context));
		}
	};
	private OnClickListener weixinLoginClickListener = new OnClickListener() {
		public void onClick(View view) {
			
		}
	};
	
	// 后退事件
	private OnClickListener backOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			FDUserCenterLoginActivity.this.finish();
		}
	};

	/**
	 * 第三方登陆验证
	 * @param platform
	 */
	private void authorize(Platform platform) {
		if (platform == null) return;
		
		if(platform.isValid()) {
			platform.removeAccount();
			String userId = platform.getDb().getUserId();
			if (!TextUtils.isEmpty(userId)) {
				UIHandler.sendEmptyMessage(FD_MSG_USERID_FOUND, this);
				thirdPartyLogin(platform.getName(), userId, null);
				return;
			}
		}
		platform.setPlatformActionListener(this);
		platform.SSOSetting(true);
		platform.showUser(null);
	}
	
	private void thirdPartyLogin(String plat, String userId, HashMap<String, Object> userInfo) {
		Message msg = new Message();
		msg.what = FD_MSG_LOGIN;
		msg.obj = plat;
		UIHandler.sendMessage(msg, this);
		
		FDUser user = new FDUser();
		user.setExtraLoginType(plat);
		user.setExtraLoginId(userId);
		userCenterLogin(user);
	}
	
	public void onCancel(Platform platform, int action) {
		if (action == Platform.ACTION_USER_INFOR) {
			UIHandler.sendEmptyMessage(FD_MSG_AUTH_CANCEL, this);
		}
	}

	public void onComplete(final Platform platform, int action,final HashMap<String, Object> res) {
		if (action == Platform.ACTION_USER_INFOR) {
			UIHandler.sendEmptyMessage(FD_MSG_AUTH_COMPLETE, this);
			runOnUiThread(new Runnable(){
				public void run() {
					thirdPartyLogin(platform.getName(), platform.getDb().getUserId(), res);
				}
			});
		}
	}

	public void onError(Platform platform, int action, Throwable throwable) {
		if (action == Platform.ACTION_USER_INFOR) {
			UIHandler.sendEmptyMessage(FD_MSG_AUTH_ERROR, this);
		}
		throwable.printStackTrace();
	}

	public boolean handleMessage(Message msg) {
		switch(msg.what) {
		case FD_MSG_USERID_FOUND: {
			Toast.makeText(this, "用户信息已存在，正在跳转登录操作…", Toast.LENGTH_SHORT).show();
			break;
		}
		case FD_MSG_LOGIN: {
			String text = getString(R.string.usercenter_login_thirdparty_logining, msg.obj);
			Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
			
//			Builder builder = new Builder(this);
//			builder.setTitle(R.string.if_register_needed);
//			builder.setMessage(R.string.after_auth);
//			builder.setPositiveButton(R.string.ok, null);
//			builder.create().show();
			break;
		}
		case FD_MSG_AUTH_CANCEL: {
			Toast.makeText(this, R.string.usercenter_login_thirdparty_auth_cancel, Toast.LENGTH_SHORT).show();
			break;
		}
		case FD_MSG_AUTH_ERROR: {
			Toast.makeText(this, R.string.usercenter_login_thirdparty_auth_error, Toast.LENGTH_SHORT).show();
			break;
		}
		case FD_MSG_AUTH_COMPLETE: {
			Toast.makeText(this, R.string.usercenter_login_thirdparty_auth_complete, Toast.LENGTH_SHORT).show();
			break;
		}
	}
	return false;
	}
	
	protected void onDestroy() { 
	    super.onDestroy(); 
	    if(mFinishReceiver != null) { 
	        unregisterReceiver(mFinishReceiver); 
	    } 
	} 
}
