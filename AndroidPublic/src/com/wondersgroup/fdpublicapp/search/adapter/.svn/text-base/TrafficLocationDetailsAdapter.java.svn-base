package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.List;

import com.wondersgroup.fdpublicapp.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TrafficLocationDetailsAdapter extends BaseAdapter {
	private Activity activity;
	private List<String> name;
	private List<String> line;	
	public TrafficLocationDetailsAdapter(Activity activity, List<String> name,
			List<String> line) {
		super();
		this.activity = activity;
		this.name = name;
		this.line = line;
	}

	@Override
	public int getCount() {
		return name.size();
	}

	@Override
	public Object getItem(int position) {
		return name.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;		
		if (convertView == null) {
			holder = new ViewHolder();
			convertView=activity.getLayoutInflater().inflate( R.layout.fd_rest_traffic_location_details_listitem, null);
			holder.nameText = (TextView) convertView.findViewById(R.id.traffic_location_details_listitem_name_tv);
			holder.lineText=(TextView) convertView.findViewById(R.id.traffic_location_details_listitem_line_tv);
			holder.nameText.setText(name.get(position));
			holder.lineText.setText(line.get(position));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}
	private class ViewHolder {
		public TextView nameText;
		public TextView lineText;
	}
}
