package com.wondersgroup.fdpublicapp.search.views;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class FDSelectConditionWindow extends PopupWindow {

	public Context context;
	protected int screenWidth;
	protected int screenHeight;
	protected final int[] mLocation = new int[2];
	protected final Rect mRect = new Rect();
	protected View contentLayoutView;
	protected FDQueryConditionListener conditionChangedListener;
	
	public FDSelectConditionWindow(Context context) {
		this(context, null);
	}
	
	public FDSelectConditionWindow(Context context, View contentView) {
		super(context);
		this.context = context;
		this.contentLayoutView = contentView;
		
		initializeDefault();
	}
	
	public void initializeDefault() {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		screenWidth  = dm.widthPixels;		// 屏幕宽（像素，如：480px）
	    screenHeight = dm.heightPixels;		// 屏幕高（像素，如：800px）

		setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setAnimationStyle(R.style.PopupWindowAnimation);
        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(screenHeight-230);

        setContentWindowView(contentLayoutView);
	}
	
	public void setContentWindowView(View layoutView) {
		this.contentLayoutView = layoutView;
		
        setContentView(layoutView);
    }
	
	public void showConditionView(View anchor) {
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

        final int[] location = mLocation;
        anchor.getLocationOnScreen(location);
        mRect.set(location[0], location[1], location[0] + anchor.getWidth(), location[1] + anchor.getHeight());
        //修复点击背景空白
        setBackgroundDrawable(null);
//        showAtLocation(anchor, Gravity.NO_GRAVITY, 0, location[1]-getHeight());
        showAsDropDown(anchor);
	}
	
	public void setQueryConditionListener(FDQueryConditionListener conditionChangedListener) {
		this.conditionChangedListener = conditionChangedListener;
	}
}
