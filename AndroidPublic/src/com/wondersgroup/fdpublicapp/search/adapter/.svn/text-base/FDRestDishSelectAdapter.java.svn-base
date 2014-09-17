package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewHolder;
import com.wondersgroup.fdpublicapp.search.mode.FDDish;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author chengshaohua
 *  菜肴选择 Adapter
 */
public class FDRestDishSelectAdapter extends BaseAdapter {
	private List<FDDish> dishList = new ArrayList<FDDish>();
	private Context context;
	
	public FDRestDishSelectAdapter( Context context, List<FDDish> picList) {
		this.dishList = picList;
		this.context = context;
		
	}

	public int getCount() {
		if(dishList!=null) {
			return dishList.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		if(dishList!=null) {
			return dishList.get(position);
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = View.inflate(context,R.layout.fd_rest_seek_cuisine_gridview_item, null);
			TextView dishView = FDViewHolder.get(convertView, R.id.fd_rest_select_cuisine_item_textview);
			final LinearLayout selectedLayout = FDViewHolder.get(convertView, R.id.fd_rest_cuisine_item_selected);
			if(dishList!=null) {
				final FDDish dish = dishList.get(position);
				dishView.setText(dish.getCuisineName());
				if(dish.isSelected()) {
					selectedLayout.setVisibility(View.VISIBLE);
				}else {
					selectedLayout.setVisibility(View.GONE);
				}
				convertView.setOnClickListener(new OnClickListener(){
					public void onClick(View view) {
						dish.setSelected(!dish.isSelected());
						if(dish.isSelected()) {
							selectedLayout.setVisibility(View.VISIBLE);
						}else {
							selectedLayout.setVisibility(View.GONE);
						}
					}
				});
			}
		}
		return convertView; 
	}
}
