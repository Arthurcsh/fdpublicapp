package com.wondersgroup.fdpublicapp.search.activity;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.search.adapter.TrafficLocationDetailsAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author chengshaohua
 *  交通定位详情
 */
public class FDTrafficLocationDetailsActivity extends FDBaseActivity implements OnClickListener{
	private List<String> name;
	private List<String> line;
	private ListView listView;
	private TrafficLocationDetailsAdapter adapter;
	private LinearLayout backLl;
	private TextView headlineText;
	private TextView numberText;
	private TextView nameText;
	private TextView gpsText;
	private String acceptValue;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_rest_traffic_location_details_list);
	}
	
	private void initData() {
		backLl=(LinearLayout) findViewById(R.id.traffic_location_details_img_ll);
		backLl.setOnClickListener(this);
		headlineText=(TextView) findViewById(R.id.traffic_location_details_name_tv);
		numberText=(TextView) findViewById(R.id.traffic_location_details_number_tv);
		nameText=(TextView) findViewById(R.id.traffic_location_details_number_name_tv);
		gpsText=(TextView) findViewById(R.id.traffic_location_details_my_location_tv);
		listView=(ListView) findViewById(R.id.traffic_location_details_list);
		adapter=new TrafficLocationDetailsAdapter(this, name, line);
		listView.setAdapter(adapter);
	}
	
	public void onClick(View v) {
		if(v==backLl){
			finish();
		}
	}
}
