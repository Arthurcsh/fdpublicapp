package com.wondersgroup.fdpublicapp.search.activity;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * 输入对话框
 * @author chengshaohua
 *
 */
public class FDInputPopupWindows extends PopupWindow {

	public Context context;
	public View rootView;
	public FDCallback inputCallbackListener;
	
	public FDInputPopupWindows(Context mContext, View parent, final FDCallback inputListener) {
		this.context = mContext;
		this.rootView = parent;
		this.inputCallbackListener = inputListener;
		
		final View inputView = View.inflate(mContext, R.layout.fd_common_input_popupwindows, null);
		LinearLayout confirmLayout = (LinearLayout) inputView.findViewById(R.id.item_popupwindows_input_confirm);
		inputView.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_ins));
		RelativeLayout ll_popup = (RelativeLayout) inputView.findViewById(R.id.layout_input_popup);
		ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.push_bottom_in_2));

		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		setBackgroundDrawable(new BitmapDrawable());
		setFocusable(true);
		setOutsideTouchable(true);
		setContentView(inputView);
		showAsDropDown(parent);
//		showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		update();

		final EditText inputEditView = (EditText) inputView.findViewById(R.id.item_popupwindows_input_editview);
		inputEditView.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
			}
		});
		confirmLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(inputCallbackListener==null) return;
				String inputString = inputEditView.getText().toString().trim();
				if(StringUtils.isEmpty(inputString)) {
					FDViewUtil.showToast(context, "请输入平均消费值.", true);
					return;
				}
				dismiss();
				inputCallbackListener.onCallback(inputString);
			}
		});
	}
	
}