package com.wondersgroup.fdpublicapp.home.main.views;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDImageView;
import com.wondersgroup.fdpublicapp.common.custom.qrcode.FDQrCodeActivity;
import com.wondersgroup.fdpublicapp.common.impl.FDKeyWordQueryListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.advert.CircleFlowIndicator;
import com.wondersgroup.fdpublicapp.home.advert.FDViewPager;
import com.wondersgroup.fdpublicapp.home.advert.FDViewPagerAdapter;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.modeSeller.activity.FDModelSellerActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDStudentMealsActivity;
import com.wondersgroup.fdpublicapp.home.ranking.activity.FDSellerRankingActivity;
import com.wondersgroup.fdpublicapp.home.safety.activity.FDFoodSafetyActivity;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsActivity;
import com.wondersgroup.fdpublicapp.widget.FDQueryKeyNameWindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 *  主视图
 * @author chengshaohua
 *
 */
public class FDIndexView extends LinearLayout implements OnClickListener{

	public Context context;
	private CircleFlowIndicator fdCircle;
	private int fdViewsIndex = 0;
	private LinearLayout keyQueryView;
	private LinearLayout qrMarkView;
	private ImageView qrMarkButton;
	private Button safetyInfoButton;
	private Button studentMealsButton;
	private Button modelSellersButton;
	private Button rankingButton;
	
	public FDIndexView(Context context) {
		super(context);
		this.context = context;

		initIndexView();
//		initMainSafety();
	}

	public FDIndexView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		
	}

	public FDIndexView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		
		initIndexView();
//		initMainSafety();
	}
	
	public void initIndexView() {
		View indexView = LayoutInflater.from(context).inflate(R.layout.fd_frame_index, null);
		
		addView(indexView);
		onIndexClickListener(indexView);
		
		IntentFilter qrFilter = new IntentFilter();
		qrFilter.addAction(FDQrCodeActivity.FD_QR_CODE_RECEIVER);
	}
	
	public void onIndexClickListener(View view) {
		keyQueryView = (LinearLayout) view.findViewById(R.id.header_search_key_words);
		qrMarkView = (LinearLayout) view.findViewById(R.id.fd_qr_code_layout);
		qrMarkButton = (ImageView) view.findViewById(R.id.fd_qr_code_button);
		studentMealsButton = (Button)view.findViewById(R.id.main_index_nutritional_lunch);
		safetyInfoButton = (Button)view.findViewById(R.id.main_index_safety_information);
		modelSellersButton = (Button)view.findViewById(R.id.main_index_model_seller);
		rankingButton = (Button)view.findViewById(R.id.main_index_ranking);
		qrMarkView.setOnClickListener(this);
		qrMarkButton.setOnClickListener(this);
		keyQueryView.setOnClickListener(this);
		studentMealsButton.setOnClickListener(this);
		safetyInfoButton.setOnClickListener(this);
		modelSellersButton.setOnClickListener(this);
		rankingButton.setOnClickListener(this);
	}
	
	public void initMainSafety() {
		List<View> arrayView = new ArrayList<View>();
		arrayView.add(new FDImageView(context,R.drawable.ad_1));
		arrayView.add(new FDImageView(context,R.drawable.ad_2));
		arrayView.add(new FDImageView(context,R.drawable.ad_3));
		arrayView.add(new FDImageView(context,R.drawable.ad_4));
		FDViewPagerAdapter viewPagerAdapter = new FDViewPagerAdapter(arrayView);
		FDViewPager viewPager = (FDViewPager) findViewById(R.id.super_main_view_pager);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setOnPageChangeListener(new HeaderViewPager());
		
		fdCircle = (CircleFlowIndicator) findViewById(R.id.super_main_circle); 
		fdCircle.addCircle(arrayView.size());
		fdCircle.setViewPager(viewPager);
	}
	
	public class HeaderViewPager implements OnPageChangeListener {
		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int position) {
			fdViewsIndex = position;
			fdCircle.selectedCircle(position);
		}
	}

	public void onClick(View view) {
		if (view == keyQueryView) {
			FDQueryKeyNameWindow searchKeyNameWindow = new FDQueryKeyNameWindow(context, new FDKeyWordQueryListener(){
				public void onKeyWordCallback(FDRestaurant restaurant) {
					if(restaurant!=null) {
						Intent intent = new Intent(context,FDSearchDetailsActivity.class);
						intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurant.getId());
						context.startActivity(intent);
					}
				}
			});
			searchKeyNameWindow.showScreenView(this);
		} 
		else if (view == studentMealsButton){
			Intent intent = new Intent(context,FDStudentMealsActivity.class);
			context.startActivity(intent);
		}else if (view == modelSellersButton){
			Intent intent = new Intent(context,FDModelSellerActivity.class);
			context.startActivity(intent);
		}else if (view == rankingButton){
			Intent intent = new Intent(context,FDSellerRankingActivity.class);
			context.startActivity(intent);
		}else if (view == safetyInfoButton) {
			Intent intent = new Intent(context,FDFoodSafetyActivity.class);
			context.startActivity(intent);
		}
		else if(view==qrMarkView || view==qrMarkButton) {
			Intent intent = new Intent(context,FDQrCodeActivity.class);
			((Activity) context).startActivityForResult(intent, 100);
		}
	}
}
