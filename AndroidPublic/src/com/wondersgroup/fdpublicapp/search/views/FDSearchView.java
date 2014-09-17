package com.wondersgroup.fdpublicapp.search.views;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDKeyWordQueryListener;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.activity.FDTabBarActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchResultActivity;
import com.wondersgroup.fdpublicapp.search.mode.FDAtmosphere;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.search.mode.FDCondition;
import com.wondersgroup.fdpublicapp.search.mode.FDCredit;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;
import com.wondersgroup.fdpublicapp.widget.FDPickAreaWindow;
import com.wondersgroup.fdpublicapp.widget.FDPickAtmosphereWindow;
import com.wondersgroup.fdpublicapp.widget.FDPickCreditWindow;
import com.wondersgroup.fdpublicapp.widget.FDPickCuisineWindow;
import com.wondersgroup.fdpublicapp.widget.FDPopupWindow;
import com.wondersgroup.fdpublicapp.widget.FDQueryKeyNameWindow;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 餐厅搜索
 * @author chengshaohua
 *
 */

public class FDSearchView extends LinearLayout implements OnClickListener{
	public final static int FD_QUERY_REDIRECT_MAIN      = 0;
	public final static int FD_QUERY_REDIRECT_TABLE     = 1;
	private FDPopupWindow searchConditionWindow;
	private Context context;
	public RelativeLayout restaurantKeyNameLayout;
	public RelativeLayout restaurantCuisineLayout;
	public RelativeLayout restaurantCreditLayout;
	public RelativeLayout restaurantAreaLayout;
	public RelativeLayout restaurantAtmosphereLayout;
	private LinearLayout restaurantNearbyLayout;
	private ImageView restWholeImg;
	private EditText keyWordTextView;
	private LinearLayout backMainLayout;
	
	public FDSearchView(Context context) {
		super(context);
		this.context =  context;
	}

	public FDSearchView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FDSearchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		
		initSearchView();
	}
	
	// 初始化餐厅搜索
	public void initSearchView() {
		View searchView = LayoutInflater.from(context).inflate(R.layout.fd_restaurant_seek_main, null);		
		addView(searchView);
		
		backMainLayout = (LinearLayout) searchView.findViewById(R.id.fd_restaurant_search_back_label);
		restaurantKeyNameLayout = (RelativeLayout) searchView.findViewById(R.id.fd_restaurant_seek_name_rll);
		restaurantCuisineLayout = (RelativeLayout) searchView.findViewById(R.id.fd_restaurant_seek_cuisine_rll);
		restaurantCreditLayout = (RelativeLayout) searchView.findViewById(R.id.fd_restaurant_seek_credit_rll);
		restaurantAreaLayout = (RelativeLayout) searchView.findViewById(R.id.fd_restaurant_seek_shangquan_rll);
		restaurantAtmosphereLayout = (RelativeLayout) searchView.findViewById(R.id.fd_restaurant_seek_atmosphere_rll);
		restaurantNearbyLayout = (LinearLayout) searchView.findViewById(R.id.fd_restaurant_seek_up_ll);
		restWholeImg = (ImageView) searchView.findViewById(R.id.fd_restaurant_seek_search_img);
		keyWordTextView = (EditText) restaurantKeyNameLayout.findViewById(R.id.fd_restaurant_seek_keyName_textview);
		
		onClickListener(searchView);
	}
	
	private void onClickListener(View view) {
		restWholeImg.setOnClickListener(this);
		backMainLayout.setOnClickListener(this);
		restaurantCuisineLayout.setOnClickListener(this);
		restaurantCreditLayout.setOnClickListener(this);
		restaurantAreaLayout.setOnClickListener(this);
		restaurantAtmosphereLayout.setOnClickListener(this);
		restaurantNearbyLayout.setOnClickListener(this);
	}
	
	// 选中关键字
	public void excuteKeyWordPickWindow(final int excuteType) {
		searchConditionWindow = new FDQueryKeyNameWindow(context, new FDKeyWordQueryListener(){
			public void onKeyWordCallback(FDRestaurant restaurant) {
				if(restaurant!=null) {
					keyWordTextView.setText(restaurant.getName());
				}
			}
		});
		searchConditionWindow.showScreenView(this);
	}
	
	// 选中商圈
	public void excuteAreaPickWindow(final int excuteType) {
		searchConditionWindow = new FDPickAreaWindow(context, new FDQueryConditionListener(){
			public void onChangedConditionCallback(int viewType, Object conditionMode) {
				FDCommerialCenter commerialCenter = (FDCommerialCenter) conditionMode;
				if(excuteType==FD_QUERY_REDIRECT_TABLE) {
					if(FDConst.UNLIMITED_CONDITION_KEY.equals(commerialCenter.getCommerialCode())){
						excuteQueryCondition(0, null);
					}else {
						FDCondition commerialCondition = new FDCondition();
						commerialCondition.setCommerial(commerialCenter);
						excuteQueryCondition(0, commerialCondition);
					}
				}
				searchConditionWindow.dismiss();
			}
		});
		searchConditionWindow.showScreenView(this);
	}
	
	// 选中信用
	public void excuteCreditPickWindow(final int excuteType) {
		searchConditionWindow = new FDPickCreditWindow(context, new FDQueryConditionListener(){
			public void onChangedConditionCallback(int viewType, Object conditionMode) {
				FDCredit credit = (FDCredit) conditionMode;
				if(excuteType==FD_QUERY_REDIRECT_TABLE) {
					if(FDConst.UNLIMITED_CONDITION_KEY.equals(credit.getCreditCode())){
						excuteQueryCondition(0, null);
					}else {
						FDCondition creditCondition = new FDCondition();
						creditCondition.setCredit(credit);
						excuteQueryCondition(0, creditCondition);
					}
				}
				searchConditionWindow.dismiss();
			}
		});
		searchConditionWindow.showScreenView(this);
	}

	// 选中菜系
	public void excuteCuisinePickWindow(final int excuteType) {
		searchConditionWindow = new FDPickCuisineWindow(context, new FDQueryConditionListener(){
			public void onChangedConditionCallback(int viewType, Object conditionMode) {
				FDCuisine cuisine = (FDCuisine) conditionMode;
				if(excuteType==FD_QUERY_REDIRECT_TABLE) {
					if(FDConst.UNLIMITED_CONDITION_KEY.equals(cuisine.getCuisine())){
						excuteQueryCondition(0, null);
					}else {
						FDCondition cuisineCondition = new FDCondition();
						cuisineCondition.setCuisine(cuisine);
						excuteQueryCondition(0, cuisineCondition);
					}
				}
				searchConditionWindow.dismiss();
			}
		});
		searchConditionWindow.showScreenView(this);
	}

	// 选中氛围
	public void excuteAtmospherePickWindow(final int excuteType) {
		searchConditionWindow = new FDPickAtmosphereWindow(context, new FDQueryConditionListener(){
			public void onChangedConditionCallback(int viewType, Object conditionMode) {
				FDAtmosphere atmosphere = (FDAtmosphere) conditionMode;
				if(excuteType==FD_QUERY_REDIRECT_TABLE) {
					if(FDConst.UNLIMITED_CONDITION_KEY.equals(atmosphere.getAtmosphereCode())){
						excuteQueryCondition(0, null);
					}else {
						FDCondition atmosphereCondition = new FDCondition();
						atmosphereCondition.setAtmosphere(atmosphere);
						excuteQueryCondition(0,atmosphereCondition);
					}
				}
				searchConditionWindow.dismiss();
			}
		});
		searchConditionWindow.showScreenView(this);
	}
	
	// type 0全城、 1附近
	public void excuteQueryCondition(int type, FDCondition condition) {
		if(type>1) return;
		Intent intent = new Intent(context,FDSearchResultActivity.class);
		if(type>-1) {
			intent.putExtra(FDConst.FD_QUERY_DETAIL_TYPE_ID, type);
		}
		if(condition!=null) {
			intent.putExtra(FDConst.FD_QUERY_CONDITION_BUNDLE_KEY, condition);
		}
		context.startActivity(intent);	
	}
	
	public void onClick(View view) {
		if (view == backMainLayout) {
			((FDTabBarActivity)context).setPageIndex(0);
		}else if (view == restaurantCuisineLayout) {
			FDPickCuisineWindow cuisineWindow = new FDPickCuisineWindow(context, view);
			cuisineWindow.showScreenView(this);
		}else if (view == restaurantCreditLayout) {
			FDPickCreditWindow creditWindow = new FDPickCreditWindow(context, view);
			creditWindow.showScreenView(this);
		}else if (view == restaurantAreaLayout) {
			FDPickAreaWindow commericalAreaWindow = new FDPickAreaWindow(context, view);
			commericalAreaWindow.showScreenView(this);
		}else if (view == restaurantAtmosphereLayout) {
			FDPickAtmosphereWindow atmosphereWindow = new FDPickAtmosphereWindow(context, view);
			atmosphereWindow.showScreenView(this);
		}else if(view == restWholeImg){
			excuteQueryCondition(0, getQueryCondition());
		}else if (view == restaurantNearbyLayout) {
			excuteQueryCondition(1, null);
		}
	}

	// 餐厅搜索条件
	public FDCondition getQueryCondition()  {
		FDCondition conditionObject = new FDCondition();
		if(!"".equals(keyWordTextView.getText())) {
			FDSuperMode keyWord = new FDSuperMode();
			keyWord.setCode(FDConst.FD_QUERY_CONDITION_KEYNAME);
			keyWord.setName(keyWordTextView.getText().toString().trim());
			conditionObject.setKeyword(keyWord);
		}
		if(restaurantKeyNameLayout.getTag()!=null) {
			FDSuperMode keyWord = (FDSuperMode) restaurantKeyNameLayout.getTag();
			conditionObject.setKeyword(keyWord);
		}
		if(restaurantCuisineLayout.getTag()!=null) {
			FDCuisine cuisineCondition = (FDCuisine) restaurantCuisineLayout.getTag();
			conditionObject.setCuisine(cuisineCondition);
		}
		if(restaurantCreditLayout.getTag()!=null) {
			FDCredit creditCondition = (FDCredit) restaurantCreditLayout.getTag();
			conditionObject.setCredit(creditCondition);
		}
		if(restaurantAreaLayout.getTag()!=null) {
			FDCommerialCenter commericalCenterCondition = (FDCommerialCenter) restaurantAreaLayout.getTag();
			conditionObject.setCommerial(commericalCenterCondition);
		}
		if(restaurantAtmosphereLayout.getTag()!=null) {
			FDAtmosphere atmosphereCondition = (FDAtmosphere) restaurantAtmosphereLayout.getTag();
			conditionObject.setAtmosphere(atmosphereCondition);
		}
		return conditionObject;
	}
}
