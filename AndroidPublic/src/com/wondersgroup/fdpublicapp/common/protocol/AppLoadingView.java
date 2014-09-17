package com.wondersgroup.fdpublicapp.common.protocol;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author chengshaohua
 * 
 */
public class AppLoadingView extends ImageView implements Runnable {
	private boolean isStop = false;
	private int[] imageIds;
	private int index = 0;
	private int length = 1;

	public AppLoadingView(Context context) {
		this(context, null);
	}

	public AppLoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setImageIds(int[] imageId) {
		this.imageIds = imageId;
		if (imageIds != null && imageIds.length > 0) {
			length = imageIds.length;
		}
	}

	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		isStop = true;
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (imageIds != null && imageIds.length > 0) {
			this.setImageResource(imageIds[index]);
		}
	}

	public void run() {
		while (!isStop) {
			index = ++index % length;
			postInvalidate();
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void startAnim() {
		new Thread(this).start();
	}

}
