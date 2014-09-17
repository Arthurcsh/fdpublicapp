package com.wondersgroup.fdpublicapp.home.main.adapter;

import java.util.ArrayList;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author chengshaohua
 * @desc   设置距离
 */
public class FDSuperAdapter extends BaseAdapter{

	private Activity context;
	private ArrayList<FDSuperMode> modeList;
	
	public FDSuperAdapter(Activity context, ArrayList<FDSuperMode> mode) {
		super();
		this.context = context;
		this.modeList = mode;
	}
	
	public int getCount() {
		return modeList==null?0:modeList.size();
	}

	public Object getItem(int arg0) {
		return modeList==null?null:modeList.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public View getView(int position, View convertView, ViewGroup arg2) {
		FDSuperMode superMode = null;
		if(convertView==null){
			convertView = context.getLayoutInflater().inflate(R.layout.fd_business_area_lisitem, null);
			TextView nameText = (TextView) convertView.findViewById(R.id.fd_business_area_listitem_tv);
			if(modeList!=null) {
				superMode = modeList.get(position);
				nameText.setText(superMode.getName());
			}
			convertView.setTag(superMode);
		} else {
			superMode = (FDSuperMode) convertView.getTag();
		}
		
		return convertView;
	}

}
