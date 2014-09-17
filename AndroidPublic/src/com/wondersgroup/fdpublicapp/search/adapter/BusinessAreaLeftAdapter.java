package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.impl.FDQueryConditionListener;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.widget.FDBaseConditionItemView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BusinessAreaLeftAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<FDCommerialCenter> communities;	
	private FDQueryConditionListener clickChangedListener;
	
	public BusinessAreaLeftAdapter(Context context, ArrayList<FDCommerialCenter> communities, FDQueryConditionListener conditionChangedListener) {
		super();
		this.context = context;
		this.communities = communities;
		this.clickChangedListener = conditionChangedListener;
		
	}

	public int getCount() {
		return communities.size();
	}

	public Object getItem(int position) {
		return communities.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final FDCommerialCenter commerialItem = communities.get(position);
		convertView = LayoutInflater.from(context).inflate(R.layout.fd_business_area_lisitem, null);
		TextView nameText = (TextView) convertView.findViewById(R.id.fd_business_area_listitem_tv);
		nameText.setText(commerialItem.getCommerialName());
		convertView.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(clickChangedListener!=null) {
					clickChangedListener.onChangedConditionCallback(FDBaseConditionItemView.QUERY_RESTAURANT_CONDITION_AREA, commerialItem);
				}
			}
		});
		
		return convertView;
	}
	
}
