package com.wondersgroup.fdpublicapp.personal.activity;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.personal.views.FDUserCenterResetPwPopWindow;

/**
 * @author mengke
 */
public class FDUserCenterLostPwSecQuesFragment extends Fragment {
	private final String TAG = getClass().getSimpleName();
	private TextView submitBtnTextView;
	private Context context;
	private View lostPwSecQuesView;

	public FDUserCenterLostPwSecQuesFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		lostPwSecQuesView = inflater.inflate(
				R.layout.fd_usercenter_lost_pw_sec_ques, null);
		submitBtnTextView = (TextView) lostPwSecQuesView
				.findViewById(R.id.textv_fd_usercenter_lost_pw_sec_submit);
		submitBtnTextView.setOnClickListener(submitBtnOnClickListener);
		context = this.getActivity();
		return lostPwSecQuesView;
	}

	//提交处理事件
	private OnClickListener submitBtnOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			FDUserCenterResetPwPopWindow resetPwPopWindow = new FDUserCenterResetPwPopWindow(context);
			resetPwPopWindow.setFocusable(true);
			resetPwPopWindow.setTouchable(true);
			resetPwPopWindow.setOutsideTouchable(true);
			resetPwPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			resetPwPopWindow.setWidth(LayoutParams.MATCH_PARENT);
			//resetPwPopWindow.setHeight(LayoutParams.MATCH_PARENT);
			final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	        //int screenWidth = windowManager.getDefaultDisplay().getWidth();
	        int screenHeight = windowManager.getDefaultDisplay().getHeight();
	       /* int statusBarHeight = 0;
			Rect frame = new Rect();
			getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
			statusBarHeight = frame.top;*/
			Class c;
			try {
				c = Class.forName("com.android.internal.R$dimen");
				Object obj = c.newInstance();
				Field field = c.getField("status_bar_height");
				int x = Integer.parseInt(field.get(obj).toString());
				int y = getResources().getDimensionPixelSize(x);
				resetPwPopWindow.setHeight(screenHeight - y);
				resetPwPopWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, y);
				resetPwPopWindow.update();
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}
	};
}
