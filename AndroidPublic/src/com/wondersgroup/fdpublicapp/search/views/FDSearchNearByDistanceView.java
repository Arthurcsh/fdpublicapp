package com.wondersgroup.fdpublicapp.search.views;

import com.wondersgroup.fdpublicapp.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class FDSearchNearByDistanceView extends LinearLayout{
	private Activity context;
	private ListView listView;
	private String[] items = { "不限", "0-50", "50-60", "70-100", "100-200","200-300","300以上"};
	private ArrayAdapter adapter;
	public LayoutInflater flater;
	public FDSearchNearByDistanceView(Context context) {
		super(context);
		this.context=(Activity) context;
		initCreditView();
	}
	private void initCreditView() {
		flater = LayoutInflater.from(context);
	    View distanceView = flater.inflate(R.layout.fd_rest_seek_result_ll,null);
		addView(distanceView);
		listView=(ListView) distanceView.findViewById(R.id.fd_seek_nearby_public_listview);
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setVerticalScrollBarEnabled(false);
		adapter = new ArrayAdapter(context, R.layout.fd_business_area_lisitem,	R.id.fd_business_area_listitem_tv, items);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				removeAllViews();
				FDSearchTableListView view1=new FDSearchTableListView(context);
				addView(view1);	
			}
		});
		distanceView.findViewById(R.id.fd_seek_nearby_public_img).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				removeAllViews();
				FDSearchTableListView view=new FDSearchTableListView(context);
				addView(view);
				
			}
		});
	}

}
