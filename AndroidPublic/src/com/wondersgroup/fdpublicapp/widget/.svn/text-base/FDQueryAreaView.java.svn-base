package com.wondersgroup.fdpublicapp.widget;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.search.mode.FDAtmosphere;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.search.mode.FDCondition;
import com.wondersgroup.fdpublicapp.search.mode.FDCredit;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class FDQueryAreaView extends FDQueryBaseCondition implements FDQueryConditionListener{

	public FDQueryAreaView(Context context) {
		super(context);
		
		initAreaConditionView();
	}
	
	public FDQueryAreaView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

    public void initAreaConditionView() {
    	distanceButton.setVisibility(View.GONE);
    	distanceDevideImage.setVisibility(View.GONE);
    	
    	searchAreaView.setQueryConditionListener(this);
		searchCuisineView.setQueryConditionListener(this);
		searchCreditView.setQueryConditionListener(this);
		searchOthersView.setQueryConditionListener(this);
    }

	// 监听餐厅查询条件选择
	public void onChangedConditionCallback(int viewType, Object conditionMode) {
		if(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_AREA==viewType) {
			FDCommerialCenter commerialCenter = (FDCommerialCenter) conditionMode;
			if(FDConst.UNLIMITED_CONDITION_KEY.equals(commerialCenter.getCommerialCode())) {
				areaButton.setText(context.getString(R.string.search_condition_area_bar));
			}else {
				areaButton.setText(commerialCenter.getCommerialName());
			}
			areaButton.setTag(commerialCenter);
		}else if(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_CUISINE==viewType) {
			FDCuisine cuisine = (FDCuisine) conditionMode;
			if(FDConst.UNLIMITED_CONDITION_KEY.equals(cuisine.getCuisine())) {
				cuisineButton.setText(context.getString(R.string.search_condition_cuisine_bar));
			}else {
				cuisineButton.setText(cuisine.getCuisineValue());
			}
			cuisineButton.setTag(cuisine);
		}else if(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_CREDIT==viewType) {
			FDCredit credit = (FDCredit) conditionMode;
			if(FDConst.UNLIMITED_CONDITION_KEY.equals(credit.getCreditCode())) {
				creditButton.setText(context.getString(R.string.search_condition_credit_bar));
			}else {
				creditButton.setText(credit.getCreditName());
			}
			creditButton.setTag(credit);
		}else if(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_OTHERS==viewType) {
			othersButton.setTag(conditionMode);
		}
		if(queryConditionListener!=null) {
			JSONObject conditionObject = new JSONObject();
			try {
				if(areaButton.getTag()!=null) {
					conditionObject.put(FDConst.FD_QUERY_CONDITION_AREA, ((FDCommerialCenter)areaButton.getTag()).getCommerialCode());
				}
				if(cuisineButton.getTag()!=null) {
					conditionObject.put(FDConst.FD_QUERY_CONDITION_CUISINE, ((FDCuisine)cuisineButton.getTag()).getCuisine());
				}
				if(creditButton.getTag()!=null) {
					conditionObject.put(FDConst.FD_QUERY_CONDITION_CREDIT, ((FDCredit)creditButton.getTag()).getCreditCode());
				}
				if(othersButton.getTag()!=null) {
					Map<String,Object> otherCondition = (Map<String,Object>) othersButton.getTag();
					if(otherCondition.size()>0) {
						for(Map.Entry<String, Object> entry:otherCondition.entrySet()){
							if(entry==null) continue;
							if(FDConst.FD_QUERY_CONDITION_ATMOSPHERE.equals(entry.getKey())) {
								FDAtmosphere atmosphere = (FDAtmosphere) entry.getValue();
								conditionObject.put(FDConst.FD_QUERY_CONDITION_ATMOSPHERE, atmosphere.getAtmosphereCode());
							}else if(FDConst.FD_QUERY_CONDITION_AVERAGE.equals(entry.getKey())) {
								FDSuperMode average = (FDSuperMode) entry.getValue();
								conditionObject.put(FDConst.FD_QUERY_CONDITION_AVERAGE, average.getCode());
							}else if(FDConst.FD_QUERY_CONDITION_ORDER.equals(entry.getKey())) {
								FDSuperMode order = (FDSuperMode) entry.getValue();
								if(order.getCode().equals(FDConst.UNLIMITED_CONDITION_SORT_KEY)) {
									conditionObject.put(FDConst.FD_QUERY_CONDITION_ORDER, order.getSortBy());
									if(order.getDirection()!=null) {
										conditionObject.put(FDConst.FD_QUERY_CONDITION_ORDER_DIRECTION, order.getDirection());
									}
								}else if(order.getCode().equals(FDConst.UNLIMITED_CONDITION_KEY)){
									conditionObject.put(FDConst.FD_QUERY_CONDITION_ORDER, order.getCode());
								}
							}
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			selectConditionWindow.dismiss();
			queryConditionListener.onChangedConditionCallback(conditionObject.toString());
//			System.out.println("Condition Query..."+conditionObject);
		}
	}
	
	// 设置条件查询的BarItem
	public void initConditionQuery(FDCondition condition) {
		if(condition==null) return;
		if(condition.getCommerial()!=null) {
			areaButton.setText(condition.getCommerial().getCommerialName());
		}
		if(condition.getCuisine()!=null) {
			cuisineButton.setText(condition.getCuisine().getCuisineValue());
		}
		if(condition.getCredit()!=null) {
			creditButton.setText(condition.getCredit().getCreditName());
		}
		if(condition.getAtmosphere()!=null) {
			searchOthersView.atmosphereTextView.setText(condition.getAtmosphere().getAtmosphereName());
		}
	}
}
