package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.search.adapter.BusinessCuisineAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;
import com.wondersgroup.fdpublicapp.widget.FDBaseConditionItemView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class FDSearchSelectCuisineView extends FDBaseConditionItemView {
	private Activity context;
	private ListView listView;
	private BusinessCuisineAdapter adapter;
	public LayoutInflater flater;
	public ArrayList<FDCuisine> cuisineList = new ArrayList<FDCuisine>();

	public FDSearchSelectCuisineView(Context context) {
		super(context);
		this.context = (Activity) context;
		
		initCuisineView();
	}

	public FDSearchSelectCuisineView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		
	}

	public FDSearchSelectCuisineView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = (Activity) context;
		
		initCuisineView();
	}
	
	private void initCuisineView() {
		flater = LayoutInflater.from(context);
	    View cuisineView = flater.inflate(R.layout.fd_serach_select_list,null);
		listView = (ListView) cuisineView.findViewById(R.id.fd_seek_select_listview);
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setVerticalScrollBarEnabled(false);
		addView(cuisineView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		ArrayList<FDCuisine> cuisineListMap = (ArrayList<FDCuisine>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_CUISINE_TYPE);
		if(cuisineListMap!=null) {
			FDCuisine unlimitedCuisine = new FDCuisine();
			unlimitedCuisine.setCuisine(FDConst.UNLIMITED_CONDITION_KEY);
			unlimitedCuisine.setCuisineValue(FDConst.UNLIMITED_CONDITION_VALUE);
			this.cuisineList.add(unlimitedCuisine);
			this.cuisineList.addAll(cuisineListMap);
		}
		
		if(cuisineList!=null){
			adapter = new BusinessCuisineAdapter(context, cuisineList);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					FDCuisine cuisine = cuisineList.get(position);
					if(conditionChangedListener!=null) {
						conditionChangedListener.onChangedConditionCallback(QUERY_RESTAURANT_CONDITION_CUISINE, cuisine);
					}
				}
			});
		}	
	}

}
