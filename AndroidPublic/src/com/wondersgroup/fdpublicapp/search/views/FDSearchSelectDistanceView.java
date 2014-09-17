package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.adapter.FDSuperAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;
import com.wondersgroup.fdpublicapp.widget.FDBaseConditionItemView;

public class FDSearchSelectDistanceView extends FDBaseConditionItemView {

	private Activity context;
	private ArrayList<FDSuperMode> distanceList;
	
	public FDSearchSelectDistanceView(Context context) {
		super(context);
		this.context = (Activity) context;
		
		initDistanceView();
	}
	
	public FDSearchSelectDistanceView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public FDSearchSelectDistanceView(Context context, AttributeSet attrs,int defStyle) {
		super(context, attrs, defStyle);
		
		this.context = (Activity) context;
		initDistanceView();
	}
	
	public void initDistanceView() {
		if(distanceList==null) {
			distanceList = new ArrayList<FDSuperMode>();
			FDSuperMode unlimitedDistance = new FDSuperMode();
			unlimitedDistance.setCode(FDConst.UNLIMITED_CONDITION_KEY);
			unlimitedDistance.setName(FDConst.UNLIMITED_CONDITION_VALUE);
			distanceList.add(unlimitedDistance);
			
			FDSuperMode mode50 = new FDSuperMode();
			FDSuperMode mode100 = new FDSuperMode();
			FDSuperMode mode300 = new FDSuperMode();
			FDSuperMode mode500 = new FDSuperMode();
			FDSuperMode mode1000 = new FDSuperMode();
			FDSuperMode mode2000 = new FDSuperMode();
			FDSuperMode mode10000 = new FDSuperMode();
			FDSuperMode mode16000 = new FDSuperMode();
			mode50.setCode("50");
			mode50.setName("50米");
			mode100.setCode("100");
			mode100.setName("100米");
			mode300.setCode("300");
			mode300.setName("300米");
			mode500.setCode("500");
			mode500.setName("500米");
			mode1000.setCode("1000");
			mode1000.setName("1千米");
			mode2000.setCode("2000");
			mode2000.setName("2千米");
			mode10000.setCode("10000");
			mode10000.setName("10公里");
			mode16000.setCode("16000");
			mode16000.setName("16公里");
			distanceList.add(mode50);
			distanceList.add(mode100);
			distanceList.add(mode300);
			distanceList.add(mode500);
			distanceList.add(mode1000);
			distanceList.add(mode2000);
			distanceList.add(mode10000);
			distanceList.add(mode16000);
		}
		View distanceView = LayoutInflater.from(context).inflate(R.layout.fd_serach_select_list, null);
		ListView distanceListView = (ListView) distanceView.findViewById(R.id.fd_seek_select_listview);
		distanceListView.setCacheColorHint(Color.TRANSPARENT);
		distanceListView.setVerticalScrollBarEnabled(false);
		addView(distanceView);
		
		if(distanceList!=null){
			FDSuperAdapter adapter = new FDSuperAdapter(context, distanceList);
			distanceListView.setAdapter(adapter);
			distanceListView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					FDSuperMode distanceMode = distanceList.get(position);
					if(conditionChangedListener!=null) {
						conditionChangedListener.onChangedConditionCallback(QUERY_RESTAURANT_CONDITION_DISTANCE, distanceMode);
					}
				}
			});
		}	
	}
	
}
