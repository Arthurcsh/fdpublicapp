package com.wondersgroup.fdpublicapp.search.views;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.search.adapter.BusinessCuisineAdapter;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class FDSearchNearByCuisineView extends LinearLayout {
	private Activity context;
	private ListView listView;
	private BusinessCuisineAdapter adapter;
	public LayoutInflater flater;

	public FDSearchNearByCuisineView(Context context) {
		super(context);
		this.context = (Activity) context;
		initCuisineView();
	}

	private void initCuisineView() {
		flater = LayoutInflater.from(context);
	    View cuisineView = flater.inflate(R.layout.fd_rest_seek_result_ll,null);
		addView(cuisineView);
		listView=(ListView) cuisineView.findViewById(R.id.fd_seek_nearby_public_listview);
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setVerticalScrollBarEnabled(false);
		ArrayList<FDCuisine> cuisineList = (ArrayList<FDCuisine>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_CUISINE_TYPE);
		
		if(cuisineList!=null){
			adapter = new BusinessCuisineAdapter(context, cuisineList);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					removeAllViews();
					FDSearchTableListView view1=new FDSearchTableListView(context);
					addView(view1);
				}
			});	
		}		
		cuisineView.findViewById(R.id.fd_seek_nearby_public_img).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				removeAllViews();
				FDSearchTableListView view=new FDSearchTableListView(context);
				addView(view);
			}
		});
	}

}
