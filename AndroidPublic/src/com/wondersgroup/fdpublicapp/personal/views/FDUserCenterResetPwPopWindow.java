package com.wondersgroup.fdpublicapp.personal.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.personal.activity.FDUserCenterLoginActivity;

public class FDUserCenterResetPwPopWindow extends PopupWindow {
	private LinearLayout backMainLayout;
	private TextView submitBtnTextView;
	private Context context;

	public FDUserCenterResetPwPopWindow(Context context) {
		super(context);
		this.context = context;
		initResetPwView();
	}

	public void initResetPwView() {
		View resetPwView = LayoutInflater.from(context).inflate(
				R.layout.fd_usercenter_lost_pw_reset, null);

		setContentView(resetPwView);

		// 获取控件实例
		backMainLayout = (LinearLayout) resetPwView
				.findViewById(R.id.llo_fd_usercenter_lost_pw_reset_back);
		submitBtnTextView = (TextView) resetPwView
				.findViewById(R.id.textv_fd_usercenter_lost_pw_reset_submit);

		// 给控件绑定事件
		backMainLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				FDUserCenterResetPwPopWindow.this.dismiss();
			}
		});
		submitBtnTextView.setOnClickListener(submitBtnOnClickListener);
	}

	// 提交 事件
	private OnClickListener submitBtnOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(context, FDUserCenterLoginActivity.class);
			context.startActivity(intent);
		}
	};
}
