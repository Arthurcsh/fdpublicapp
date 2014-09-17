package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.R;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * 第三方转发选项卡(新浪微博、腾讯空间、微信朋友圈)
 * @author chengshaohua
 *
 */
public class FDForwardPopupWindows extends PopupWindow {

	public Context context;
	public View rootView;
	public FDForwardListener forwardListener;
	
	public FDForwardPopupWindows(Context mContext, View parent, FDForwardListener forward) {
		this.context = mContext;
		this.rootView = parent;
		this.forwardListener = forward;
		
		final View parentView = View.inflate(mContext, R.layout.fd_common_forward_popupwindows, null);
		parentView.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_ins));
		LinearLayout ll_popup = (LinearLayout) parentView.findViewById(R.id.layout_popup);
		ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.push_bottom_in_2));

		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		setBackgroundDrawable(new BitmapDrawable());
		setFocusable(true);
		setOutsideTouchable(true);
		setContentView(parentView);
		showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		update();

		Button sina_weibo_bt = (Button) parentView.findViewById(R.id.item_popupwindows_sina_weibo);
		Button webchat_bt = (Button) parentView.findViewById(R.id.item_popupwindows_webchat);
		Button qq_zone_bt = (Button) parentView.findViewById(R.id.item_popupwindows_qq_zone);
		Button cancel_bt = (Button) parentView.findViewById(R.id.item_popupwindows_cancel);
		sina_weibo_bt.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(forwardListener!=null) {
					forwardListener.onForwardSinaWeibo();
				}
				dismiss();
			}
		});
		webchat_bt.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(forwardListener!=null) {
					forwardListener.onForwardWebChat();
				}
				dismiss();
			}
		});
		qq_zone_bt.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				if(forwardListener!=null) {
					forwardListener.onForwardQQZone();
				}
				dismiss();
			}
		});
		cancel_bt.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				dismiss();
			}
		});
	}
	
	public interface FDForwardListener {
		public void onForwardSinaWeibo();
		public void onForwardQQZone();
		public void onForwardWebChat();
	}
}