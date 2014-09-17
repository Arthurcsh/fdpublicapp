package com.wondersgroup.fdpublicapp.common.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 *  自定义图组件
 * @author chengshaohua
 *
 */
public class FDImageView extends ImageView {

	private int resId;
	public FDImageView(Context context) {
		super(context);
		
		initImageView();
	}
	
	public FDImageView(Context context, int id) {
		super(context);
		this.resId = id;
		
		initImageView();
	}
	
	public FDImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		initImageView();
	}

	public void initImageView() {
		setImageResource(resId);
		setScaleType(ImageView.ScaleType.FIT_XY);     //  MATRIX
	}
}
