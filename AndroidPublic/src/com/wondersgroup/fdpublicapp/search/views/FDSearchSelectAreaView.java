package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.search.adapter.BusinessAreaAdapter;
import com.wondersgroup.fdpublicapp.search.adapter.BusinessAreaLeftAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.widget.FDBaseConditionItemView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 *  区域选择视图
 * @author chengshaohua
 *
 */
public class FDSearchSelectAreaView extends FDBaseConditionItemView {
	private Context context;
	private ListView areaListviewLeft;
	private ListView areaListviewRight;
	private ArrayList<FDCommerialCenter> commerialListMap = (ArrayList<FDCommerialCenter>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_COMMERIAL_CENTER);;
	private ArrayList<FDCommerialCenter> commerialList = new ArrayList<FDCommerialCenter>();

	public FDSearchSelectAreaView(Context context) {
		super(context);
		this.context = (Activity) context;
		initSearchView();
	}

	public FDSearchSelectAreaView(Context context, ArrayList<FDCommerialCenter> sourceCommerialList) {
		super(context);
		this.context = (Activity) context;
		this.commerialListMap = sourceCommerialList;
		
		initSearchView();
	}
	
	public FDSearchSelectAreaView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FDSearchSelectAreaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = (Activity) context;
		
		initSearchView();
	}
	
	private void initSearchView() {
		final View areaView = LayoutInflater.from(context).inflate(R.layout.fd_business_area_list,null);
		addView(areaView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		areaListviewLeft = (ListView) areaView.findViewById(R.id.fd_business_area_list_lfet);
		areaListviewLeft.setCacheColorHint(Color.TRANSPARENT);
		areaListviewLeft.setSelector(R.color.white);
		areaListviewLeft.setVerticalScrollBarEnabled(false);
		if(commerialListMap!=null){
			FDCommerialCenter unlimitedCenter = new FDCommerialCenter();
			unlimitedCenter.setCommerialCode(FDConst.UNLIMITED_CONDITION_KEY);
			unlimitedCenter.setCommerialName(FDConst.UNLIMITED_CONDITION_VALUE);
			this.commerialList.add(unlimitedCenter);
			this.commerialList.addAll(commerialListMap);
		}
		
		BusinessAreaAdapter adapter = new BusinessAreaAdapter(context,commerialList);
		areaListviewLeft.setAdapter(adapter);
		areaListviewLeft.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position>0) {
					ArrayList<FDCommerialCenter> communities = commerialList.get(position).getCommunities();
					BusinessAreaLeftAdapter adapter = new BusinessAreaLeftAdapter(context, communities, conditionChangedListener);
					areaListviewRight = (ListView) areaView.findViewById(R.id.fd_business_area_list_right);
					areaListviewRight.setCacheColorHint(Color.TRANSPARENT);
					areaListviewRight.setAdapter(adapter);
				}else {
					FDCommerialCenter commerialCenter = commerialList.get(position);
					conditionChangedListener.onChangedConditionCallback(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_AREA, commerialCenter);
				}
			}
		});
	}

	public void setCommerialListMap(ArrayList<FDCommerialCenter> commerialListMap) {
		this.commerialListMap = commerialListMap;
		initSearchView();
	}
}
