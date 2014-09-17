package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BusinessCuisineAdapter extends BaseAdapter{
	private Activity activity;
	private ArrayList<FDCuisine> cuisineList;	
	public BusinessCuisineAdapter(Activity activity, ArrayList<FDCuisine> cuisineList) {
		super();
		this.activity = activity;
		this.cuisineList = cuisineList;
	}
	
	public int getCount() {
		return cuisineList==null?0:cuisineList.size();
	}

	public Object getItem(int position) {
		return cuisineList==null?null:cuisineList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDCuisine cuisine = cuisineList.get(position);
		convertView = activity.getLayoutInflater().inflate(R.layout.fd_business_area_lisitem, null);
		TextView nameText = (TextView) convertView.findViewById(R.id.fd_business_area_listitem_tv);
		nameText.setText(cuisine.getCuisineValue());
		return convertView;
	}
	
}
