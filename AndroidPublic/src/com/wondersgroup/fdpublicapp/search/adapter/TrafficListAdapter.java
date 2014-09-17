package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperLine;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperRoute;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TrafficListAdapter extends BaseAdapter {
	private Activity activity;
	private ArrayList<FDSuperRoute> transferRoutes;

	public TrafficListAdapter(Activity activity, ArrayList<FDSuperRoute> busRoutes) {
		super();
		this.activity = activity;
		this.transferRoutes = busRoutes;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = activity.getLayoutInflater().inflate(R.layout.fd_restaurant_traffic_listitem, null);
		
		if(transferRoutes!=null) {
			FDSuperRoute superRoute = transferRoutes.get(position);
			String lineString = getRouteLine(superRoute);
			
			TextView sortText = (TextView) convertView.findViewById(R.id.traffic_listitem_number_tv);
			TextView routeText = (TextView) convertView.findViewById(R.id.traffic_listitem_route_textview);
			sortText.setText(""+(position+1));
			routeText.setText(lineString);
		}
		
		return convertView;
	}

	public int getCount() {
		return transferRoutes.size();
	}

	public Object getItem(int position) {
		return transferRoutes.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public static String getRouteLine(FDSuperRoute superRoute) {
		StringBuilder lineString = new StringBuilder();
		if(superRoute!=null && superRoute.getLines()!=null) {
			List<FDSuperLine> transLines = superRoute.getLines();
			for(int i=0;i<transLines.size();i++) {
				FDSuperLine line = transLines.get(i);
				String lineName = line.getLineName();
				if(i<transLines.size()-1) {
					lineString.append(lineName+"--");
				}else {
					lineString.append(lineName);
				}
			}
		}
		return lineString.toString();
	}
	
}
