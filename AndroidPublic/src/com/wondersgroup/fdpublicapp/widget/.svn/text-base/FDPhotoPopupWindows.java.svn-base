package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDPhotoBucketListener;

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
 * 图片选项卡
 * @author chengshaohua
 *
 */
public class FDPhotoPopupWindows extends PopupWindow {

	public Context context;
	public View rootView;
	public FDPhotoBucketListener photoBucketListener;
	
	public FDPhotoPopupWindows(Context mContext, View parent, final FDPhotoBucketListener photoBucketListener) {
		this.context = mContext;
		this.rootView = parent;
		this.photoBucketListener = photoBucketListener;
		
		final View photoView = View.inflate(mContext, R.layout.fd_common_photo_item_popupwindows, null);
		photoView.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_ins));
		LinearLayout ll_popup = (LinearLayout) photoView.findViewById(R.id.ll_popup);
		ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.push_bottom_in_2));

		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		setBackgroundDrawable(new BitmapDrawable());
		setFocusable(true);
		setOutsideTouchable(true);
		setContentView(photoView);
		showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		update();

		Button camera_bt = (Button) photoView.findViewById(R.id.item_popupwindows_camera);
		Button photo_bt = (Button) photoView.findViewById(R.id.item_popupwindows_Photo);
		Button cancel_bt = (Button) photoView.findViewById(R.id.item_popupwindows_cancel);
		camera_bt.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				photoBucketListener.onCameraPhoto();
			}
		});
		photo_bt.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				FDPhotoPickWindow photoPickWindow = new FDPhotoPickWindow(context,photoBucketListener);
				photoPickWindow.showScreenView(rootView);
				dismiss();
			}
		});
		cancel_bt.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				dismiss();
			}
		});
	}
	
}