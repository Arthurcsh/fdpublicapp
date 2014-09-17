package com.wondersgroup.fdpublicapp.home.advert;

import com.wondersgroup.fdpublicapp.R;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author chengshaohua
 *
 */
public class CircleFlowIndicator extends LinearLayout {

	private ViewPager mViewPager;

	public CircleFlowIndicator(Context context) {
		super(context);
		init();
	}

	public CircleFlowIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {

	}

	public void addCircle(int count) {
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.rightMargin = 10;
		for (int i = 0; i < count; i++) {
			ImageView image = new ImageView(getContext());
			image.setTag(i);
			image.setOnClickListener(listener);
			image.setLayoutParams(params);
			if (i == 0) {
				image.setImageResource(R.drawable.main_circle_activity);
			} else {
				image.setImageResource(R.drawable.main_circle_inactivity);
			}
			addView(image);
		}
	}

	public void selectedCircle(int index) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			ImageView image = (ImageView) getChildAt(i);
			if (i == index) {
				image.setImageResource(R.drawable.main_circle_activity);
			} else {
				image.setImageResource(R.drawable.main_circle_inactivity);
			}
		}
	}

	public void setViewPager(ViewPager viewPager) {
		mViewPager = viewPager;
	}

	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			ImageView image = (ImageView) v;
			int tag = (Integer) image.getTag();
			mViewPager.setCurrentItem(tag, false);
		}
	};
}
