package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BusinessAreaAdapter extends BaseAdapter{
	private Context activity;
	private ArrayList<FDCommerialCenter> commerialList;
	
	public BusinessAreaAdapter(Context activity, ArrayList<FDCommerialCenter> commerialList) {
		super();
		this.activity = activity;
		this.commerialList = commerialList;
	}

	public int getCount() {
		return commerialList==null?0:commerialList.size();
	}

	public Object getItem(int position) {
		return commerialList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(activity).inflate( R.layout.fd_business_area_lisitem, null);
		TextView nameText = (TextView) convertView.findViewById(R.id.fd_business_area_listitem_tv);
		FDCommerialCenter commerialCenter = commerialList.get(position);
		nameText.setText(commerialCenter.getCommerialName());
		
		return convertView;
	}
	
}
