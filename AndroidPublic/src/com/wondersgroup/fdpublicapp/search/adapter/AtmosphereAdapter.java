package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.mode.FDAtmosphere;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AtmosphereAdapter extends BaseAdapter{
	private Activity activity;
	private ArrayList<FDAtmosphere> atmosphereList;
	
	public AtmosphereAdapter(Activity activity, ArrayList<FDAtmosphere> atmosphereList) {
		super();
		this.activity = activity;
		this.atmosphereList = atmosphereList;
	}
	
	public int getCount() {
		return atmosphereList==null?0:atmosphereList.size();
	}
	
	public Object getItem(int position) {
		return atmosphereList==null?null:atmosphereList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		FDAtmosphere atmosphere = null;
		if(convertView==null){
			convertView = activity.getLayoutInflater().inflate(R.layout.fd_business_area_lisitem, null);
			TextView nameText = (TextView) convertView.findViewById(R.id.fd_business_area_listitem_tv);
			if(atmosphereList!=null) {
				atmosphere = atmosphereList.get(position);
				nameText.setText(atmosphere.getAtmosphereName());
			}
			convertView.setTag(atmosphere);
		}else{
			atmosphere=(FDAtmosphere) convertView.getTag();
		}
		return convertView;
	}
}
