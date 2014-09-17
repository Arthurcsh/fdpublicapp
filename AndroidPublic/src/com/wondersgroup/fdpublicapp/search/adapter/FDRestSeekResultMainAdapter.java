package com.wondersgroup.fdpublicapp.search.adapter;

import java.util.ArrayList;
import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
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
 *  全城、附近餐厅 adapter
 */
public class FDRestSeekResultMainAdapter extends BaseAdapter {

	private Context activity;
	public List<FDRestaurant> restaurantList;
	
	public FDRestSeekResultMainAdapter(Context activity,List<FDRestaurant> mlist) {
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
		FDRestaurant restaurant;
		View view = convertView;
		RestHolderView restHolderView;
		if (convertView == null) {
			restHolderView = new RestHolderView();
			view = LayoutInflater.from(activity).inflate(R.layout.fd_rest_seek_result_mian_listitem, null);
			restHolderView.restNameText = (TextView) view.findViewById(R.id.restaurant_name);
			restHolderView.creditImg = (ImageView) view.findViewById(R.id.restaurant_credit);
			restHolderView.cuisineText = (TextView) view.findViewById(R.id.restaurant_cuisine_name);
			restHolderView.restAddressText = (TextView) view.findViewById(R.id.restaurant_address);
			restHolderView.restDistanceText = (TextView) view.findViewById(R.id.restaurant_distance);
			restHolderView.restDistanceUnitText = (TextView) view.findViewById(R.id.restaurant_distance_unit);
			restHolderView.restMoneyText = (TextView) view.findViewById(R.id.restaurant_money);
			view.setTag(restHolderView);
		} else {
			restHolderView = (RestHolderView) view.getTag();
		}
		if(restaurantList!=null) {
			restaurant = restaurantList.get(position);
			restHolderView.restNameText.setText(restaurant.getShopSign());
			FDViewUtil.showSafetyRating(restaurant.getFoodSaftyRating(), restHolderView.creditImg);
			if(!StringUtils.isEmpty(restaurant.getCuisineValue())){
				restHolderView.cuisineText.setText("["+restaurant.getCuisineValue()+"]");
			}
			restHolderView.restAddressText.setText(restaurant.getBizAddress());
			restHolderView.restMoneyText.setText(restaurant.getAverageComsumptionValue());
			
			double distance = restaurant.getDistance();
			if(restaurant.getDistance()>1000) {
				distance/=1000;
				restHolderView.restDistanceUnitText.setText("km");
			}else {
				restHolderView.restDistanceUnitText.setText("m");
			}
			restHolderView.restDistanceText.setText(StringUtils.toString(distance, 2));
		}
		return view;
	}
	
	/**
	 *  刷新ListView
	 * @param restaurants
	 */
	public void setSearchTableData(List<FDRestaurant> restaurants) {
		if(restaurants!=null) {
			this.restaurantList = (List<FDRestaurant>) ((ArrayList)restaurants).clone();
			this.notifyDataSetChanged();
		}
	}
	
	static class RestHolderView {
		TextView restNameText;
		ImageView creditImg;
		TextView cuisineText;
		TextView restAddressText;
		TextView restDistanceText;
		TextView restDistanceUnitText;
		TextView restMoneyText;
	}
}
