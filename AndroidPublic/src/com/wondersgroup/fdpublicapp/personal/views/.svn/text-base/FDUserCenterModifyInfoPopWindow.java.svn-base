package com.wondersgroup.fdpublicapp.personal.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wondersgroup.fdpublicapp.R;

public class FDUserCenterModifyInfoPopWindow extends PopupWindow {
	private LinearLayout backMainLayout;
	private TextView okBtnTextView;
	private Context context;

	public FDUserCenterModifyInfoPopWindow(Context context) {
		super(context);
		this.context = context;
		initResetPwView();
	}

	public void initResetPwView() {
		View resetPwView = LayoutInflater.from(context).inflate(
				R.layout.fd_usercenter_modify_info, null);

		setContentView(resetPwView);

		// 获取控件实例
		backMainLayout = (LinearLayout) resetPwView
				.findViewById(R.id.llo_fd_usercenter_modify_info_back);
		okBtnTextView = (TextView) resetPwView
				.findViewById(R.id.tv_fd_usercenter_modify_info_okbtn);

		// 给控件绑定事件
		backMainLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				FDUserCenterModifyInfoPopWindow.this.dismiss();
			}
		});
		okBtnTextView.setOnClickListener(okBtnOnClickListener);
	}

	// 提交 事件
	private OnClickListener okBtnOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			FDUserCenterModifyInfoPopWindow.this.dismiss();
		}
	};
}
