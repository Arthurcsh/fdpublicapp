package com.wondersgroup.fdpublicapp.home.ranking.adapter;

import java.util.ArrayList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsFragment;

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
 *  示范商家 adapter
 */
public class FDRankingSellerAdapter extends BaseAdapter {

	private Context activity;
	public ArrayList<FDRestaurant> restaurantList;
	
	public FDRankingSellerAdapter(Context activity,ArrayList<FDRestaurant> restlist) {
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
		FDRestaurant restaurant = null;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_ranking_seller_item, null);
			TextView restNameText = (TextView) convertView.findViewById(R.id.ranking_seller_name_tv);
			ImageView photoImg = (ImageView) convertView.findViewById(R.id.ranking_seller_photo_img);
			ImageView credit_img = (ImageView) convertView.findViewById(R.id.ranking_seller_credit_img); 
			TextView creditText = (TextView) convertView.findViewById(R.id.ranking_seller_credit_tv);
			TextView supervision = (TextView) convertView.findViewById(R.id.ranking_supervision_name_tv);
			TextView supervisionDate = (TextView) convertView.findViewById(R.id.ranking_supervision_date_tv);
			TextView average = (TextView) convertView.findViewById(R.id.ranking_seller_expense_tv);
			TextView cuisineType = (TextView) convertView.findViewById(R.id.ranking_seller_type_tv);
			if(restaurantList!=null) {
				restaurant = restaurantList.get(position);
				restNameText.setText(restaurant.getName());
				supervision.setText(restaurant.getSupervisionOrg());
				supervisionDate.setText(restaurant.getFoodSaftyRatingDate());
				creditText.setText(restaurant.getFoodSaftyRatingValue());
				average.setText(restaurant.getAverageComsumptionValue());
				cuisineType.setText(restaurant.getBizAddress());
				ArrayList<FDImage> picList = restaurant.getImageList();
				if(picList!=null && picList.size()!=0){
					ImageLoader imageLoader = ImageLoader.getInstance();
					imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
					DisplayImageOptions options = new DisplayImageOptions.Builder()
					.cacheInMemory(true)
					.cacheOnDisc(false)
					.showImageForEmptyUri(R.drawable.fd_gv_pic_show_failed_default)
					.showImageOnFail(R.drawable.fd_gv_pic_show_failed_default)
					.showStubImage(R.drawable.fd_gv_pic_show_failed_default)
					.build();
					imageLoader.displayImage(picList.get(0).getImgURL(), photoImg, options);	
				}
				if(restaurant.getFoodSaftyRating()==21004){
					credit_img.setImageResource(R.drawable.fd_business_credit_smile);
				}else if(restaurant.getFoodSaftyRating()==21003){
					credit_img.setImageResource(R.drawable.fd_business_credit_peace);
				}else if (restaurant.getFoodSaftyRating()==21002) {
					credit_img.setImageResource(R.drawable.fd_business_credit_unhappy);
				}else{
					credit_img.setImageResource(R.drawable.fd_business_credit_unreview);
				}
				final int restaurantID = restaurant.getId();
				convertView.setOnClickListener(new OnClickListener(){
					public void onClick(View arg0) {
						Intent intent = new Intent(activity,FDSearchDetailsFragment.class);
						intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantID);
						activity.startActivity(intent);
					}
				});
			}
			convertView.setTag(restaurant);
		} else {
			restaurant = (FDRestaurant) convertView.getTag();
		}
		return convertView;
	}
	
	public void setSearchTableData(ArrayList<FDRestaurant> restaurants) {
		this.restaurantList = restaurants;
		this.notifyDataSetChanged();
	}
}
