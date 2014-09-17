package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.search.adapter.AtmosphereAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDAtmosphere;
import com.wondersgroup.fdpublicapp.widget.FDBaseConditionItemView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FDSearchAtmosphereView extends FDBaseConditionItemView{
	private Activity context;
	private ListView atmosphereListview;
	private AtmosphereAdapter adapter;
	private ArrayList<FDAtmosphere> atmosphereList = new ArrayList<FDAtmosphere>();
	
	public FDSearchAtmosphereView(Context context) {
		super(context);
		this.context = (Activity) context;
		
		initAtmosphereView();
	}
	
	private void initAtmosphereView() {
	    View atmosphereView = LayoutInflater.from(context).inflate(R.layout.fd_serach_select_list,null);
		addView(atmosphereView);
		atmosphereListview = (ListView) atmosphereView.findViewById(R.id.fd_seek_select_listview);
		atmosphereListview.setCacheColorHint(Color.TRANSPARENT);
		atmosphereListview.setVerticalScrollBarEnabled(false);
		
		ArrayList<FDAtmosphere> atmosphereListMap = (ArrayList<FDAtmosphere>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_ATMOSPHERE_LIST);
		if(atmosphereListMap!=null) {
			FDAtmosphere unlimitedAtmosphere = new FDAtmosphere();
			unlimitedAtmosphere.setAtmosphereCode(FDConst.UNLIMITED_CONDITION_KEY);
			unlimitedAtmosphere.setAtmosphereName(FDConst.UNLIMITED_CONDITION_VALUE);
			atmosphereList.add(unlimitedAtmosphere);
			atmosphereList.addAll(atmosphereListMap);
		}
		
		if(atmosphereList!=null){
			adapter = new AtmosphereAdapter(context, atmosphereList);
			atmosphereListview.setAdapter(adapter);	
			atmosphereListview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					FDAtmosphere atmosphere = atmosphereList.get(position);
					if(conditionChangedListener!=null) {
						conditionChangedListener.onChangedConditionCallback(QUERY_RESTAURANT_CONDITION_ATMOSPHERE, atmosphere);
					}
				}
			});
		}
		
	}

}
