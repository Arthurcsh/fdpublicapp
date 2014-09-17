package com.wondersgroup.fdpublicapp.home.advert;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDImageView;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.home.advert.CircleFlowIndicator;
import com.wondersgroup.fdpublicapp.home.advert.FDViewPager;
import com.wondersgroup.fdpublicapp.home.advert.service.FDAdvertWrapper;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;
import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 *  食品安全广告页
 * @author chengshaohua
 *
 */
public class FDAdvertView extends LinearLayout implements OnClickListener {

	public Context context;
	private List<FDReply> safetyPage;
	private List<View> safetyPageView;
	private View advertPageView;
	private FDViewPager advertViewPager;
	private CircleFlowIndicator fdPageCircle;
	private int fdViewsIndex = 0;
	
	public FDAdvertView(Context context) {
		super(context);
		this.context = context;

		initIndexAdvertView();
//		loadMainSafetyAdvertPages();
	}

	public FDAdvertView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		
	}

	public FDAdvertView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		
		initIndexAdvertView();
	}
	
	/**
	 * 初始化广告页
	 */
	public void initIndexAdvertView() {
		advertPageView = LayoutInflater.from(context).inflate(R.layout.fd_view_flow, null);
		addView(advertPageView);
		advertViewPager = (FDViewPager) advertPageView.findViewById(R.id.super_main_view_pager);
		fdPageCircle = (CircleFlowIndicator) advertPageView.findViewById(R.id.super_main_circle); 
		
		onIndexClickListener(advertPageView);
		loadMainSafetyAdvertPages();
	}
	
	public void onIndexClickListener(View view) {
		
	}
	
	/**
	 * 食品安全信息广告页
	 */
	public void loadMainSafetyAdvertPages() {
		FDAdvertWrapper advertPageWrapper = new FDAdvertWrapper(context);
		advertPageWrapper.getAdvertPage(-1, -1, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				safetyPage = (List<FDReply>) callback;
				setSafetyAdvertPageView();
			}
		});
	}
	
	/**
	 * 设置广告页图片
	 */
	public void setSafetyAdvertPageView() {
		if(safetyPage==null) return;
		
		safetyPageView = new ArrayList<View>();
		for(FDReply page:safetyPage) {
			if(page==null) continue;
			List<FDImage> pageImages = page.getPicList();
			if(pageImages!=null && pageImages.size()>0) {
				FDImage pageImage = pageImages.get(0);
				FDImageView pageImageView = new FDImageView(context);
				FDViewUtil.showServerImage(context, pageImageView, pageImage.getFilePath());
				safetyPageView.add(pageImageView);
			}
		}
		
		FDViewPagerAdapter viewPagerAdapter = new FDViewPagerAdapter(safetyPageView);
		advertViewPager.setAdapter(viewPagerAdapter);
		advertViewPager.setOnPageChangeListener(new HeaderViewPager());
		fdPageCircle.addCircle(safetyPageView.size());
		fdPageCircle.setViewPager(advertViewPager);
	}
	
	public class HeaderViewPager implements OnPageChangeListener {
		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int position) {
			fdViewsIndex = position;
			fdPageCircle.selectedCircle(position);
		}
	}

	public void onClick(View view) {
		
	}
}
