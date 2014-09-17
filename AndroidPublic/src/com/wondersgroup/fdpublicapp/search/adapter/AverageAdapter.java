package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AverageAdapter extends BaseAdapter{
	private Activity activity;
	public ArrayList<FDSuperMode> averagelist;	
	public AverageAdapter(Activity activity, ArrayList<FDSuperMode> averagelist) {
		super();
		this.activity = activity;
		this.averagelist = averagelist;
	}
	
	public int getCount() {
		return averagelist==null?0:averagelist.size();
	}

	public Object getItem(int position) {
		return averagelist==null?null:averagelist.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		FDSuperMode infor=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView = activity.getLayoutInflater().inflate(R.layout.fd_business_area_lisitem, null);
			holder.averageNameText = (TextView) convertView.findViewById(R.id.fd_business_area_listitem_tv);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		if(averagelist!=null){
			infor=averagelist.get(position);
			holder.averageNameText.setText(infor.getName());
		}
		return convertView;
	}
	private class ViewHolder {
		public TextView averageNameText;
	}
}
