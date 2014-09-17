package com.wondersgroup.fdpublicapp.widget;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDPhotoBucketListener;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class FDPopupWindow extends PopupWindow {

	protected Context context;
	protected int screenWidth;
	protected int screenHeight;
	protected final int[] mLocation = new int[2];
	protected final Rect mRect = new Rect();
	protected View contentLayoutView;
	protected View onEventView;;
	protected FDQueryConditionListener conditionListener;
	protected FDPhotoBucketListener callbackListener;
	
	public FDPopupWindow(Context context) {
		super(context);
		this.context = context;
		
		initializeDefault();
	}

	public void initializeDefault() {
		setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.MATCH_PARENT);

        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        screenHeight = windowManager.getDefaultDisplay().getHeight();
        
        setContentWindowView(contentLayoutView);
	}
	
	public void setContentWindowView(View layoutView) {
		this.contentLayoutView = layoutView;
        setContentView(layoutView);
    }
	
	// 该方法取消
	public void showScreenView(View anchor) {
		final View contentView = getContentView();
		setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		showAtLocation(anchor, Gravity.NO_GRAVITY, 0, 0);
		
		contentView.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dismiss();
					return true;
				}
				return false;
			}
		});
	}
	
	public void show(View anchor) {
		final View contentView = getContentView();
        if (contentView == null) {
            throw new IllegalStateException("You need to set the content view using the setContentView method");
        }

        //设置触摸事件 - 修复触摸弹窗以外的地方无法隐藏弹窗
        contentView.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
	            final int x = (int) event.getX();
	            final int y = (int) event.getY();
	            
	            if ((event.getAction() == MotionEvent.ACTION_DOWN) && ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight()))) {
	                dismiss();
	                return true;
	            } else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
	                dismiss();
	                return true;
	            } else {
	            	dismiss();
	                return contentView.onTouchEvent(event);
	            }
			}
		});
        
        contentView.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dismiss();
					return true;
				}
				return false;
			}
		});

        //修复点击背景空白
//        setBackgroundDrawable(null);

        final int[] location = mLocation;
        anchor.getLocationOnScreen(location);
        mRect.set(location[0], location[1], location[0] + anchor.getWidth(), location[1] + anchor.getHeight());

//        if (mIsDirty) {
//            clearQuickActions();
//            populateQuickActions(mQuickActions);
//        }
//        onMeasureAndLayout(mRect, contentView);

        prepareAnimationStyle();
        showAtLocation(anchor, Gravity.NO_GRAVITY, 0, 0);
	}
	
	private void prepareAnimationStyle() {
        final int width = screenWidth;
        final boolean onTop = true;
        final int arrowPointX = mRect.centerX();

        if (arrowPointX <= width / 4) {
            setAnimationStyle(onTop ? R.style.QueryRestaurant_Animation_PopUp_Left
                    : R.style.QueryRestaurant_Animation_PopDown_Left);
        } else if (arrowPointX >= 3 * width / 4) {
            setAnimationStyle(onTop ? R.style.QueryRestaurant_Animation_PopUp_Right
                    : R.style.QueryRestaurant_Animation_PopDown_Right);
        } else {
            setAnimationStyle(onTop ? R.style.QueryRestaurant_Animation_PopUp_Center
                    : R.style.QueryRestaurant_Animation_PopDown_Center);
        }
    }
}
