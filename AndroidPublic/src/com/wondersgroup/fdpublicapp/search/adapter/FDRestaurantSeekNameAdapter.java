package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import java.util.List;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FDRestaurantSeekNameAdapter extends BaseAdapter {
	private Context activity;
	private List<FDRestaurant> keyWordList;
	
	public FDRestaurantSeekNameAdapter(Context activity, List<FDRestaurant> keyWordList) {
		super();
		this.activity = activity;
		this.keyWordList = keyWordList;
	}

	public int getCount() {
		return keyWordList==null?0:keyWordList.size();
	}

	public Object getItem(int position) {
		return keyWordList==null?null:keyWordList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDRestaurant restaurant = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_restaurant_seek_name_keyword_listitem, null);
			TextView nameText = (TextView) convertView.findViewById(R.id.fd_rest_name_keyword_listitem_name_tv);
			TextView numberText = (TextView) convertView.findViewById(R.id.fd_rest_name_keyword_listitem_number_tv);
			if(keyWordList!=null) {
				restaurant = keyWordList.get(position);
				nameText.setText(restaurant.getName());
			}
			convertView.setTag(restaurant);	
		}else{
			restaurant = (FDRestaurant) convertView.getTag();
		}
		return convertView;
	}
	
	public void setKeyWordList(ArrayList<FDRestaurant> restaurants) {
		this.keyWordList = restaurants;
	}
}
