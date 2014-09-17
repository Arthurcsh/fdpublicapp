package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.List;

import com.amap.api.services.core.PoiItem;
import com.wondersgroup.fdpublicapp.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TrafficLocationAdapter extends BaseAdapter {
	private Activity activity;
	private List<PoiItem> poiItem;

	public TrafficLocationAdapter(Activity activity, List<PoiItem> poi) {
		super();
		this.activity = activity;
		this.poiItem = poi;
	}

	public int getCount() {
		return poiItem==null?0:poiItem.size();
	}

	public Object getItem(int position) {
		return poiItem==null?null:poiItem.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = activity.getLayoutInflater().inflate(R.layout.fd_rest_traffic_location_listitem, null);
			holder.nameText = (TextView) convertView.findViewById(R.id.traffic_location_listitem_name_tv);
			holder.nameText.setText(poiItem.get(position).getTitle());
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	public void setTrafficLocationList(List<PoiItem> locations) {
		this.poiItem = locations;
	}
	
	private class ViewHolder {
		public TextView nameText;
	}
	
	public void clearTrafficLocation() {
		if(poiItem!=null) {
			poiItem.clear();
		}
	}
}
