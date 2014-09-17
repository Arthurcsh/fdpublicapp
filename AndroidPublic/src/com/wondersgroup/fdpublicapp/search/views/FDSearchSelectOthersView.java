package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.home.main.adapter.FDSuperAdapter;
import com.wondersgroup.fdpublicapp.search.adapter.AtmosphereAdapter;
import com.wondersgroup.fdpublicapp.search.adapter.AverageAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDAtmosphere;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;
import com.wondersgroup.fdpublicapp.widget.FDBaseConditionItemView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;

public class FDSearchSelectOthersView extends FDBaseConditionItemView implements OnClickListener{
	private Activity context;
	private RelativeLayout atmosphereLayout;
	private RelativeLayout personLayout;
	private RelativeLayout orderLayout;
	public TextView atmosphereTextView;
	private TextView personTextView;
	private TextView orderTextView;
	private LayoutInflater flater;
	private View othersPublicView1;
	private View othersView ;
	private View mainOthersView;
	private LinearLayout subOthersView;
	private ListView listView;
	private Button backBtn;
	private LinearLayout conditionQueryImageView;
	private ArrayList<FDAtmosphere> atmosphereList = new ArrayList<FDAtmosphere>();
	private ArrayList<FDSuperMode> averagelist = new ArrayList<FDSuperMode>();
	private ArrayList<FDSuperMode> orderlist = new ArrayList<FDSuperMode>();
	
	public FDSearchSelectOthersView(Context context) {
		super(context);
		this.context = (Activity) context;
		
		initOthersView();
	}

	public FDSearchSelectOthersView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		
	}

	public ArrayList<FDSuperMode> getOthersOrder() {
		ArrayList<FDSuperMode> orderListMap = new ArrayList<FDSuperMode>();
		FDSuperMode minDistance = new FDSuperMode();
		FDSuperMode descendingCredit = new FDSuperMode();
		FDSuperMode ascendingCredit = new FDSuperMode();
		FDSuperMode descendingConsumption= new FDSuperMode();
		FDSuperMode ascendingConsumption = new FDSuperMode();
		minDistance.setCode(FDConst.UNLIMITED_CONDITION_SORT_KEY);
		minDistance.setSortBy(FDConst.FD_QUERY_CONDITION_ORDER_DISTANCE);
		minDistance.setName("距离最近");
		
		descendingCredit.setCode(FDConst.UNLIMITED_CONDITION_SORT_KEY);
		descendingCredit.setSortBy(FDConst.FD_QUERY_CONDITION_ORDER_CREDIT);
		descendingCredit.setDirection(FDConst.FD_QUERY_CONDITION_ORDER_DESC);
		descendingCredit.setName("信用从高到低");
		
		ascendingCredit.setCode(FDConst.UNLIMITED_CONDITION_SORT_KEY);
		ascendingCredit.setSortBy(FDConst.FD_QUERY_CONDITION_ORDER_CREDIT);
		ascendingCredit.setDirection(FDConst.FD_QUERY_CONDITION_ORDER_ASC);
		ascendingCredit.setName("信用从低到高");
		
		descendingConsumption.setCode(FDConst.UNLIMITED_CONDITION_SORT_KEY);
		descendingConsumption.setSortBy(FDConst.FD_QUERY_CONDITION_ORDER_AVERAGE);
		descendingConsumption.setDirection(FDConst.FD_QUERY_CONDITION_ORDER_DESC);
		descendingConsumption.setName("费用从高到低");
		
		ascendingConsumption.setCode(FDConst.UNLIMITED_CONDITION_SORT_KEY);
		ascendingConsumption.setSortBy(FDConst.FD_QUERY_CONDITION_ORDER_AVERAGE);
		ascendingConsumption.setDirection(FDConst.FD_QUERY_CONDITION_ORDER_ASC);
		ascendingConsumption.setName("费用从低到高");
		
		orderListMap.add(minDistance);
		orderListMap.add(descendingCredit);
		orderListMap.add(ascendingCredit);
		orderListMap.add(descendingConsumption);
		orderListMap.add(ascendingConsumption);
		
		return orderListMap;
	}
	
	public FDSearchSelectOthersView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = (Activity) context;
		
		initOthersView();
	}
	
	public void initOthersView() {
		removeAllViews();
		flater = LayoutInflater.from(context);
		othersView = flater.inflate(R.layout.fd_business_rests, null);
		mainOthersView = othersView.findViewById(R.id.fd_other_main_xml);
		subOthersView = (LinearLayout) othersView.findViewById(R.id.fd_other_subItem_xml);
		addView(othersView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		initOthersDefault();
	}

	public void initOthersDefault() {
		othersPublicView1 = flater.inflate(R.layout.fd_business_atmosphere_list, null);
		atmosphereLayout = (RelativeLayout) othersView.findViewById(R.id.fd_business_others_atmosphere_ll);
		personLayout = (RelativeLayout) othersView.findViewById(R.id.fd_business_others_percapita_ll);
		orderLayout = (RelativeLayout) othersView.findViewById(R.id.fd_business_others_order_ll);
		atmosphereTextView = (TextView) othersView.findViewById(R.id.fd_table_others_atmosphere_text);
		personTextView = (TextView) othersView.findViewById(R.id.fd_table_others_person_text);
		orderTextView = (TextView) othersView.findViewById(R.id.fd_table_others_order_text);
		conditionQueryImageView = (LinearLayout) othersView.findViewById(R.id.fd_business_choose_btn);
		atmosphereTextView.setText(context.getResources().getString(R.string.search_others_atmophere));
		personTextView.setText(context.getResources().getString(R.string.search_others_average));
		orderTextView.setText(context.getResources().getString(R.string.search_others_order));
		
		conditionQueryImageView.setOnClickListener(this);
		personLayout.setOnClickListener(this);
		atmosphereLayout.setOnClickListener(this);
		orderLayout.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		if (view == atmosphereLayout) {
			listView = (ListView) othersPublicView1.findViewById(R.id.fd_business_atmosphere_list_id);
			backBtn = (Button) othersPublicView1.findViewById(R.id.fd_business_atmosphere_back_btn);
			backBtn.setOnClickListener(this);
			listView.setCacheColorHint(Color.TRANSPARENT);
			listView.setVerticalScrollBarEnabled(false);
			ArrayList<FDAtmosphere> atmosphereListMap = (ArrayList<FDAtmosphere>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_ATMOSPHERE_LIST);
			if(atmosphereListMap!=null) {
				atmosphereList.clear();
				FDAtmosphere unlimitedAtmosphere = new FDAtmosphere();
				unlimitedAtmosphere.setAtmosphereCode(FDConst.UNLIMITED_CONDITION_KEY);
				unlimitedAtmosphere.setAtmosphereName(FDConst.UNLIMITED_CONDITION_VALUE);
				atmosphereList.add(unlimitedAtmosphere);
				atmosphereList.addAll(atmosphereListMap);
			}
			if (atmosphereList != null) {
				final AtmosphereAdapter adapter = new AtmosphereAdapter(context,atmosphereList);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
						FDAtmosphere atmosphere = (FDAtmosphere) adapter.getItem(position);
						if(atmosphere.getAtmosphereCode().equals(FDConst.UNLIMITED_CONDITION_KEY)) {
							atmosphereTextView.setText(context.getResources().getString(R.string.search_others_atmophere));
						}else {
							atmosphereTextView.setText(atmosphere.getAtmosphereName());
						}
						atmosphereTextView.setTag(atmosphere);
						subOthersView.removeAllViews();
						setSubOtherViewVisible(false);
					}
				});
			}
			subOthersView.removeAllViews();
			subOthersView.addView(othersPublicView1);
			setSubOtherViewVisible(true);
		}else if(view==personLayout){
			listView = (ListView) othersPublicView1.findViewById(R.id.fd_business_atmosphere_list_id);
			backBtn = (Button) othersPublicView1.findViewById(R.id.fd_business_atmosphere_back_btn);
			backBtn.setOnClickListener(this);
			listView.setCacheColorHint(Color.TRANSPARENT);
			listView.setVerticalScrollBarEnabled(false);
			ArrayList<FDSuperMode> averagelistMap = (ArrayList<FDSuperMode>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_AVERAGE_CONSUM);
			if(averagelistMap!=null) {
				averagelist.clear();
				FDSuperMode unlimitedAverage = new FDSuperMode();
				unlimitedAverage.setCode(FDConst.UNLIMITED_CONDITION_KEY);
				unlimitedAverage.setName(FDConst.UNLIMITED_CONDITION_VALUE);
				averagelist.add(unlimitedAverage);
				averagelist.addAll(averagelistMap);
			}
			if (averagelist != null) {
				final AverageAdapter adapter = new AverageAdapter(context,averagelist);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
						FDSuperMode personalAverage = (FDSuperMode) adapter.getItem(position);
						if(personalAverage.getCode().equals(FDConst.UNLIMITED_CONDITION_KEY)) {
							personTextView.setText(context.getResources().getString(R.string.search_others_average));
						}else {
							personTextView.setText(personalAverage.getName());
						}
						personTextView.setTag(personalAverage);
						subOthersView.removeAllViews();
						setSubOtherViewVisible(false);
					}
				});
			}
			
			subOthersView.removeAllViews();
			subOthersView.addView(othersPublicView1);
			setSubOtherViewVisible(true);
		}else if(view==orderLayout){
			listView = (ListView) othersPublicView1.findViewById(R.id.fd_business_atmosphere_list_id);
			backBtn = (Button) othersPublicView1.findViewById(R.id.fd_business_atmosphere_back_btn);
			backBtn.setOnClickListener(this);
			listView.setCacheColorHint(Color.TRANSPARENT);
			listView.setVerticalScrollBarEnabled(false);
			if(orderlist!=null) {
				orderlist.clear();
				FDSuperMode unlimitedAverage = new FDSuperMode();
				unlimitedAverage.setCode(FDConst.UNLIMITED_CONDITION_KEY);
				unlimitedAverage.setName(FDConst.UNLIMITED_CONDITION_VALUE);
				orderlist.add(unlimitedAverage);
				orderlist.addAll(getOthersOrder());
			}
			final FDSuperAdapter adapter = new FDSuperAdapter(context,orderlist);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					FDSuperMode orderItem = (FDSuperMode) adapter.getItem(position);
					if(orderItem.getCode().equals(FDConst.UNLIMITED_CONDITION_KEY)) {
						orderTextView.setText(context.getResources().getString(R.string.search_others_order));
					}else {
						orderTextView.setText(orderItem.getName());
					}
					orderTextView.setTag(orderItem);
					subOthersView.removeAllViews();
					setSubOtherViewVisible(false);
				}
			});
			subOthersView.removeAllViews();
			subOthersView.addView(othersPublicView1);
			setSubOtherViewVisible(true);
		}else if(view==backBtn){
			subOthersView.removeAllViews();
			setSubOtherViewVisible(false);
		}else if(view==conditionQueryImageView) {
			Map<String,Object> otherCondition = new HashMap<String,Object>();
			if(atmosphereTextView.getTag()!=null) {
				FDAtmosphere atmosphere = (FDAtmosphere) atmosphereTextView.getTag();
				otherCondition.put(FDConst.FD_QUERY_CONDITION_ATMOSPHERE, atmosphere);
			}
			if(personTextView.getTag()!=null) {
				FDSuperMode average = (FDSuperMode) personTextView.getTag();
				otherCondition.put(FDConst.FD_QUERY_CONDITION_AVERAGE, average);
			}
			if(orderTextView.getTag()!=null) {
				FDSuperMode order = (FDSuperMode) orderTextView.getTag();
				otherCondition.put(FDConst.FD_QUERY_CONDITION_ORDER, order);
			}
			conditionChangedListener.onChangedConditionCallback(QUERY_RESTAURANT_CONDITION_OTHERS, otherCondition);
		}
	}

	public void setSubOtherViewVisible(boolean visible) {
		if(visible) {
			subOthersView.setVisibility(View.VISIBLE);
			subOthersView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_right_in)); 
		}else {
			if(subOthersView.isShown()) {
				subOthersView.setVisibility(View.GONE);
				subOthersView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_right_out));
			}
		}
	}
}
