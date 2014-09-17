package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.search.mode.FDRestMenu;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 菜单 Adapter
 * @author chengshaohua
 *
 */
public class FDMenuDetailsAdapter extends BaseAdapter{
	private Context activity;
	private List<FDRestMenu> restaurantMenus;
	
	public FDMenuDetailsAdapter(Context activity, List<FDRestMenu> restaurantMenus) {
		super();
		this.activity = activity;
		this.restaurantMenus = restaurantMenus;
	}

	public int getCount() {
		if(restaurantMenus==null) return 0;
		return restaurantMenus.size();
	}

	public Object getItem(int position) {
		if(restaurantMenus==null) return null;
		return restaurantMenus.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDRestMenu menu = null;
		if(convertView==null){
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_restaurant_menu_details_item, null);
			ImageView menuImage = (ImageView) convertView.findViewById(R.id.menu_griditem_picture_img);
			TextView menuName = (TextView) convertView.findViewById(R.id.menu_griditem_picture_name_tv);
			TextView recommendTextView = (TextView) convertView.findViewById(R.id.menu_griditem_recommend_number_tv);
			TextView priceTextView = (TextView) convertView.findViewById(R.id.menu_griditem_money_tv);
			if(restaurantMenus!=null) {
				menu = restaurantMenus.get(position);
				recommendTextView.setText(""+menu.getRecommendedCount());
				menuName.setText(menu.getName());
				if(menu.getPicList()!=null && menu.getPicList().size()>0) {
					List<FDImage> imageList = menu.getPicList();
					FDViewUtil.showServerImage(activity, menuImage, imageList.get(0).getFilePath());
				}
			}
			convertView.setTag(menu);
		}else{
			menu = (FDRestMenu) convertView.getTag();
		}
		return convertView;
	}
	
}
