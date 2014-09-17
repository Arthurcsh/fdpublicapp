package com.wondersgroup.fdpublicapp.personal.views;

import java.lang.reflect.Field;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDBadgeView;
import com.wondersgroup.fdpublicapp.common.custom.FDUserAvatarView;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;
import com.wondersgroup.fdpublicapp.home.main.activity.FDTabBarActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDStudentSchoolFavoriteActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterCompleteActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterConfigActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterLoginActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterMsgManagerActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterMyCouponActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterMyMessageActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterMyCommentsActivity;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterMyRecommendActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.personal.mode.FDUserInfo;
import com.wondersgroup.fdpublicapp.personal.service.FDPersonalWrapper;

/**
 *  个人设置中心
 * @author mengke
 *
 */
public class FDUserCenterView extends LinearLayout implements OnClickListener{
	public final static int FD_QUERY_REDIRECT_MAIN         = 0;
	public final static int FD_QUERY_REDIRECT_TABLE        = 1;
	public final static String FD_USER_CENTER_COMPLETED    = "user.completed";
	public final static String FD_USER_CENTER_LOGIN        = "user.login.update";
	public final static String FD_USER_CENTER_LOGOUT       = "user.logout.update";
	private Activity context;
	private FDUserAvatarView userAvatarView;
	private LinearLayout backMainLayout;
	public TextView completeUserInfoBtn;
	public RelativeLayout myCommentsLayout;
	public RelativeLayout myCouponLayout;
	public RelativeLayout myRecommendationLayout;
	public RelativeLayout myFavoriteLayout;
	public RelativeLayout myMessageLayout;
	public RelativeLayout mySettingsLayout;
	public LinearLayout myManagerMsgLayout;
	public TextView usernameTextView;              // 用户名
	public TextView commentTextView;               // 我的点评
	public TextView couponTextView;                // 我的优惠
	public TextView recommendTextView;             // 我的推荐
	public TextView collectTextView;               // 我的收藏
	public TextView messageTextView;               // 我的留言
	public FDBadgeView messageBadge;
	public FDAppContext appContext;
	public FDUser loginUser;
	
	
	public FDUserCenterView(Context context) {
		super(context);
		this.context = (Activity) context;
	}

	public FDUserCenterView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FDUserCenterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = (Activity) context;
		
		initUserCenterView();
	}
	
	// 初始化页面
	public void initUserCenterView() {
		appContext = (FDAppContext)context.getApplication();
		View usercenterView = LayoutInflater.from(context).inflate(R.layout.fd_usercenter_view, null);	
		addView(usercenterView);
		backMainLayout = (LinearLayout) usercenterView.findViewById(R.id.llo_fd_usercenter_back);
		userAvatarView = (FDUserAvatarView) usercenterView.findViewById(R.id.img_fd_usercenter_user_info_headerimg);
		completeUserInfoBtn = (TextView) usercenterView.findViewById(R.id.tv_fd_usercenter_complete_user_info_btn);
		myCommentsLayout = (RelativeLayout) usercenterView.findViewById(R.id.rlo_fd_usercenter_my_comments);
		myCouponLayout = (RelativeLayout) usercenterView.findViewById(R.id.rlo_fd_usercenter_my_coupon);
		myRecommendationLayout = (RelativeLayout) usercenterView.findViewById(R.id.rlo_fd_usercenter_my_recommendation);
		myFavoriteLayout = (RelativeLayout) usercenterView.findViewById(R.id.rlo_fd_usercenter_my_favorite);
		myMessageLayout = (RelativeLayout) usercenterView.findViewById(R.id.rlo_fd_usercenter_my_message);
		mySettingsLayout = (RelativeLayout) usercenterView.findViewById(R.id.rlo_fd_usercenter_my_settings);
		myManagerMsgLayout = (LinearLayout) usercenterView.findViewById(R.id.llo_fd_usercenter_msg);
		
		usernameTextView = (TextView) usercenterView.findViewById(R.id.tv_fd_usercenter_user_info_username);
		commentTextView = (TextView) usercenterView.findViewById(R.id.tv_fd_usercenter_my_comments_number);
		couponTextView = (TextView) usercenterView.findViewById(R.id.tv_fd_usercenter_my_coupon_number);
		recommendTextView = (TextView) usercenterView.findViewById(R.id.tv_fd_usercenter_my_recommendation_number);
		collectTextView = (TextView) usercenterView.findViewById(R.id.tv_fd_usercenter_my_favorite_number);
		messageTextView = (TextView) usercenterView.findViewById(R.id.tv_fd_usercenter_my_message_number);
		
		View target = findViewById(R.id.imgv_fd_usercenter_msg);
		messageBadge = new FDBadgeView(getContext(), target);
		messageBadge.show();
		
		onClickListener();
		initUserCenterInformation();
	}
	
	private BroadcastReceiver loginUpdateReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			context.unregisterReceiver(this);
			if (FD_USER_CENTER_LOGIN.equals(intent.getAction())) {
				loginUser = appContext.getLoginInfo();
				
				initUserCenterInformation();
			}
		}
	};
	
	private BroadcastReceiver logoutUpdateReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			context.unregisterReceiver(this);
			if (FD_USER_CENTER_LOGOUT.equals(intent.getAction())) {
				setUserCenterInformation(null);
			}
		}
	};
	
	/**
	 * 更新用户信息
	 */
	private void initUserCenterInformation() {
		loginUser = appContext.getLoginInfo();
		if(loginUser!=null) {
			FDViewUtil.showServerImage(context, userAvatarView, loginUser.getHeadImgUrl());
		}
		
		IntentFilter loginFilter = new IntentFilter();
		loginFilter.addAction(FD_USER_CENTER_LOGIN);
		context.registerReceiver(loginUpdateReceiver, loginFilter);
		
		IntentFilter logoutFilter = new IntentFilter();
		logoutFilter.addAction(FD_USER_CENTER_LOGOUT);
		context.registerReceiver(logoutUpdateReceiver, logoutFilter);
		
		FDPersonalWrapper personalWrapper = new FDPersonalWrapper(context);
		personalWrapper.getPersonalInformation(new FDCallback(){
			public void onCallback(Object callback) {
				System.out.println("personal information isCompleted**********"+callback);
				FDUserInfo userInfo = (FDUserInfo) callback;
				setUserCenterInformation(userInfo);
			}
		});
	}
	
	/**
	 *  更新个人中心信息 
	 * @param userInfo  用户信息
	 */
	private void setUserCenterInformation(FDUserInfo userInfo) {
		FDAppContext appContext = (FDAppContext) context.getApplication();
		loginUser = appContext.getLoginInfo();
		if(userInfo!=null) {
			if(appContext!=null && loginUser!=null) {
				usernameTextView.setText(loginUser.getNickName());
				FDViewUtil.showServerImage(context, userAvatarView, loginUser.getHeadImgUrl());
			}
			commentTextView.setText(""+userInfo.getCountOfComments());
			couponTextView.setText(""+userInfo.getCountOfDiscount());
			recommendTextView.setText(""+userInfo.getCountOfRecommends());
			collectTextView.setText(""+userInfo.getCountOfCollect());
			messageTextView.setText(""+userInfo.getCountOfEvent());
			messageBadge.setText(""+userInfo.getCountOfMsg());
		}else {
			usernameTextView.setText("匿名用户");
			commentTextView.setText("0");
			couponTextView.setText("0");
			recommendTextView.setText("0");
			collectTextView.setText("0");
			messageTextView.setText("0");
			messageBadge.setText("0");
			FDViewUtil.showServerImage(context, userAvatarView, "");
		}
		boolean isCompleted = appContext.getBooleanProperty(FD_USER_CENTER_COMPLETED);
		System.out.println("isCompleted--------"+isCompleted);
		String completeUserButton = "完善个人资料";
		if(isCompleted) {
			completeUserButton = "修改个人资料";
		}
		completeUserInfoBtn.setText(completeUserButton);
	}
	
	private void onClickListener() {
		backMainLayout.setOnClickListener(this);
		completeUserInfoBtn.setOnClickListener(this);
		myCommentsLayout.setOnClickListener(this);
		myCouponLayout.setOnClickListener(this);
		myRecommendationLayout.setOnClickListener(this);
		myFavoriteLayout.setOnClickListener(this);
		myMessageLayout.setOnClickListener(this);
		mySettingsLayout.setOnClickListener(this);
		myManagerMsgLayout.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		appContext = (FDAppContext)context.getApplication();
		if (view == backMainLayout) {
			((FDTabBarActivity)context).setPageIndex(0);
		}else if (view == completeUserInfoBtn) {          //完善个人资料，修改个人资料
			if(!appContext.isLogin()) {
				FDViewUtil.showToast(context, "请先用户登录..", true);
				return;
			}
			boolean isCompleted = appContext.getBooleanProperty(FD_USER_CENTER_COMPLETED);
			if(isCompleted) {
				showModifyInfoPopWindow();
			}else {
				Intent intent = new Intent(context,FDUserCenterCompleteActivity.class);
				context.startActivity(intent);
			}
		}else if (view == myManagerMsgLayout) {
			Intent intent = new Intent(context,FDUserCenterMsgManagerActivity.class);
			context.startActivity(intent);
		}else if (view == myCommentsLayout) {
			Intent intent = new Intent(context,FDUserCenterMyCommentsActivity.class);
			context.startActivity(intent);
		}else if (view == myCouponLayout) {
			Intent intent = new Intent(context,FDUserCenterMyCouponActivity.class);
			context.startActivity(intent);
		}else if(view == myRecommendationLayout){
			Intent intent = new Intent(context,FDUserCenterMyRecommendActivity.class);
			context.startActivity(intent);
		}else if (view == myFavoriteLayout) {
			Intent intent = new Intent(context,FDStudentSchoolFavoriteActivity.class);
			context.startActivity(intent);
		}else if (view == myMessageLayout) {
			Intent intent = new Intent(context,FDUserCenterMyMessageActivity.class);
			context.startActivity(intent);
		}else if (view == mySettingsLayout) {
			if(appContext.isLogin()) {
				Intent intent = new Intent(context,FDUserCenterConfigActivity.class);
				context.startActivity(intent);
			}else {
				showLoginActivity();
			}
		}
	}
	
	//跳转到登录页面
	private void showLoginActivity(){
		Intent intent = new Intent(context,FDUserCenterLoginActivity.class);
		/*intent.putExtra(FDConst.FD_QUERY_DETAIL_TYPE_ID, type);
		if(condition!=null) {
			intent.putExtra(FDConst.FD_QUERY_CONDITION_BUNDLE_KEY, condition);
		}*/
		context.startActivity(intent);
	}
	
	//显示修改个人资料页面
	private void showModifyInfoPopWindow(){
		FDUserCenterModifyInfoPopWindow modifyInfoPopWindow = new FDUserCenterModifyInfoPopWindow(context);
		modifyInfoPopWindow.setFocusable(true);
		modifyInfoPopWindow.setTouchable(true);
		modifyInfoPopWindow.setOutsideTouchable(true);
		modifyInfoPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		modifyInfoPopWindow.setWidth(LayoutParams.MATCH_PARENT);
		//resetPwPopWindow.setHeight(LayoutParams.MATCH_PARENT);
		final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
		//resetPwPopWindow.showScreenView(lostPwPhoneView);
		/*Rect frame = new Rect();
		final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(outMetrics)
		int statusBarHeight = frame.top;*/
		Class c;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			int y = getResources().getDimensionPixelSize(x);
			modifyInfoPopWindow.setHeight(screenHeight - y);
			modifyInfoPopWindow.showAtLocation(this, Gravity.NO_GRAVITY, 0, y);
			modifyInfoPopWindow.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
