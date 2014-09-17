package com.wondersgroup.fdpublicapp.home.main.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FDSpinnerAdapter extends BaseAdapter{
	private Activity mContext;
	private int selectedItem = -1;
	private List<FDSuperMode> DataList;
	
	public FDSpinnerAdapter(Activity context, List<FDSuperMode> list){	
		if(list!=null && list.size()>0){
			DataList = list;
		}
		this.mContext = context;
	}

	public int getCount() {
		return DataList==null?0:DataList.size();
//		return DataList.size();
	}

	public void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Object getItem(int pos) {
		return DataList==null?null:DataList.get(pos);
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder=null;
		FDSuperMode infor=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView = mContext.getLayoutInflater().inflate(R.layout.fd_rest_list_spinner_item, null);
			holder.nameText = (TextView)convertView.findViewById(R.id.tv_list_item);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		if(position == selectedItem) {
			holder.nameText.setBackgroundColor(mContext.getResources().getColor(R.color.sky_blue));
			holder.nameText.setTextColor(mContext.getResources().getColor(R.color.dark_blue));
        }
		if(DataList!=null){
			infor=DataList.get(position);
			holder.nameText.setText(infor.getName());
		}
		return convertView;
	}
	private class ViewHolder {
		public TextView nameText;
	}	
}