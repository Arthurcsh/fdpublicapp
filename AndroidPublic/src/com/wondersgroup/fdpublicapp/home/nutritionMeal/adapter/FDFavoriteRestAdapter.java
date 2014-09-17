package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteRestaurant;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * @author chengshaohua
 *  收藏的餐馆 adapter
 */
public class FDFavoriteRestAdapter extends BaseAdapter {

	private Context activity;
	public List<FDFavoriteRestaurant> restaurantList;
	
	public FDFavoriteRestAdapter(Context activity,List<FDFavoriteRestaurant> restlist) {
		super();
		this.activity = activity;
		this.restaurantList = restlist;
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
		FDFavoriteRestaurant restaurant = null;
		RestHolderView  restHolderView;
		if (convertView == null) {
			restHolderView = new RestHolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_student_school_favorite_rest_item, null);
			restHolderView.restNameText = (TextView) convertView.findViewById(R.id.school_favorite_rest_name_tv);
			restHolderView.photoImg = (ImageView) convertView.findViewById(R.id.school_favorite_rest_photo_img);
			restHolderView.credit_img = (ImageView) convertView.findViewById(R.id.school_favorite_rest_credit_img); 
			restHolderView.creditText = (TextView) convertView.findViewById(R.id.school_favorite_rest_credit_tv);
			restHolderView.supervision = (TextView) convertView.findViewById(R.id.school_favorite_rest_supervision_name_tv);
			restHolderView.supervisionDate = (TextView) convertView.findViewById(R.id.school_favorite_rest_supervision_date_tv);
			restHolderView.average = (TextView) convertView.findViewById(R.id.school_favorite_rest_expense_tv);
			restHolderView.restAddress = (TextView) convertView.findViewById(R.id.school_favorite_rest_address_tv);
			restHolderView.cuisineType = (TextView) convertView.findViewById(R.id.school_favorite_rest_cuisine_tv);
			restHolderView.distance = (TextView) convertView.findViewById(R.id.school_favorite_rest_distance_tv);
			
			convertView.setTag(restHolderView);
		} else {
			restHolderView = (RestHolderView) convertView.getTag();
		}
		if(restaurantList!=null) {
			restaurant = restaurantList.get(position);
			restHolderView.restNameText.setText(restaurant.getRestaurantNameAbbrev());
			restHolderView.supervision.setText(restaurant.getFoodSafetyOfficial());
			restHolderView.supervisionDate.setText(StringUtils.dateToString2(restaurant.getFoodSaftyRatingDate()));
			restHolderView.creditText.setText(restaurant.getFoodSaftyRatingValue());
//			restHolderView.average.setText(restaurant.get);
			restHolderView.restAddress.setText(restaurant.getCommercialCenterValue());
			restHolderView.cuisineType.setText(restaurant.getRestCuisineTypeListString());
//			restHolderView.distance.setText(restaurant.getBizAddress());
			List<FDImage> picList = restaurant.getAttachList();
			if(picList!=null && picList.size()!=0){
				FDViewUtil.showServerImage(activity, restHolderView.photoImg, picList.get(0).getFilePath());
			}
			FDViewUtil.showSafetyRating(restaurant.getFoodSaftyRating(), restHolderView.credit_img);
			
			final int restaurantID = restaurant.getCompanyId();
			convertView.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity,FDSearchDetailsActivity.class);
					intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantID);
					activity.startActivity(intent);
				}
			});
		}
		
		return convertView;
	}
	
	static class RestHolderView {
		TextView restNameText;
		ImageView photoImg;
		ImageView credit_img;
		TextView creditText;
		TextView supervision;
		TextView supervisionDate;
		TextView average;
		TextView restAddress;
		TextView cuisineType;
		TextView distance;
	}
	
}
