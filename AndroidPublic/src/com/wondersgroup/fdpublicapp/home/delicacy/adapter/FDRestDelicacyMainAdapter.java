package com.wondersgroup.fdpublicapp.home.delicacy.adapter;

import java.util.ArrayList;
import java.util.List;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author chengshaohua
 *  猜你想吃餐厅 adapter
 */
public class FDRestDelicacyMainAdapter extends BaseAdapter {

	private Context activity;
	public List<FDRestaurant> restaurantList;
	
	public FDRestDelicacyMainAdapter(Context activity,List<FDRestaurant> mlist) {
		super();
		this.activity = activity;
		this.restaurantList = mlist;
	}

	public int getCount() {
		return restaurantList==null?0:restaurantList.size();
	}

	public Object getItem(int position) {
		return restaurantList==null?null:restaurantList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDRestaurant restaurant = null;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_main_delicacy_listitem, null);
			TextView restNameText = (TextView) convertView.findViewById(R.id.main_hot_restaurant_name);
			ImageView creditImg = (ImageView) convertView.findViewById(R.id.main_hot_restaurant_credit);
			TextView cuisineText = (TextView) convertView.findViewById(R.id.main_hot_restaurant_cuisine_name);
			TextView restAddressText = (TextView) convertView.findViewById(R.id.main_hot_restaurant_address);
			TextView restMoneyText = (TextView) convertView.findViewById(R.id.main_hot_restaurant_money);
			if(restaurantList!=null) {
				restaurant = restaurantList.get(position);
				restNameText.setText(restaurant.getName());
				if(restaurant.getFoodSaftyRating()==21004){
					creditImg.setImageResource(R.drawable.fd_business_credit_smile);
				}else if(restaurant.getFoodSaftyRating()==21003){
					creditImg.setImageResource(R.drawable.fd_business_credit_peace);
				}else if (restaurant.getFoodSaftyRating()==21002) {
					creditImg.setImageResource(R.drawable.fd_business_credit_unhappy);
				}else{
					creditImg.setImageResource(R.drawable.fd_business_credit_unreview);
				}
				cuisineText.setText("["+restaurant.getCuisineValue()+"]");
				restAddressText.setText(restaurant.getBizAddress());
				restMoneyText.setText(restaurant.getAverageComsumptionValue());
			}
			convertView.setTag(restaurant);
		} else {
			restaurant = (FDRestaurant) convertView.getTag();
		}
		return convertView;
	}
	
	public void setSearchTableData(List<FDRestaurant> restaurants) {
		this.restaurantList = restaurants;
		this.notifyDataSetChanged();
	}
}
