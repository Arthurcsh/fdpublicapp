package com.wondersgroup.fdpublicapp.home.nutritionMeal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteCuisine;
import com.wondersgroup.fdpublicapp.personal.activity.FDDishDetailActivity;
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
 * 
 *  收藏的菜肴 adapter
 */
public class FDFavoriteCuisineAdapter extends BaseAdapter {

	private Context activity;
	public List<FDFavoriteCuisine> cuisineList;
	
	public FDFavoriteCuisineAdapter(Context activity,List<FDFavoriteCuisine> cuisineList) {
		super();
		this.activity = activity;
		this.cuisineList = cuisineList;
	}

	public int getCount() {
		return cuisineList==null?0:cuisineList.size();
	}

	public Object getItem(int position) {
		return cuisineList==null?null:cuisineList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDFavoriteCuisine cuisine = null;
		CuisineHolderView  cuisineHolderView;
		if (convertView == null) {
			cuisineHolderView = new CuisineHolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_student_school_favorite_cuisine_item, null);
			cuisineHolderView.cuisineNameText = (TextView) convertView.findViewById(R.id.fd_school_favorite_cuisine_name_tv);
			cuisineHolderView.photoImg = (ImageView) convertView.findViewById(R.id.fd_school_favorite_cuisine_photo_img);
			cuisineHolderView.companyText = (TextView) convertView.findViewById(R.id.fd_school_favorite_cuisine_company_tv); 
			cuisineHolderView.addressText = (TextView) convertView.findViewById(R.id.fd_school_favorite_cuisine_address_tv);
			cuisineHolderView.dateText = (TextView) convertView.findViewById(R.id.fd_school_favorite_cuisine_date_tv);
			
			convertView.setTag(cuisineHolderView);
		} else {
			cuisineHolderView = (CuisineHolderView) convertView.getTag();
		}
		if(cuisineList!=null) {
			cuisine = cuisineList.get(position);
			cuisineHolderView.cuisineNameText.setText(cuisine.getCuisineName());
			cuisineHolderView.companyText.setText(cuisine.getRestaurantName());
			cuisineHolderView.addressText.setText(cuisine.getRestaurantAddress());
			cuisineHolderView.dateText.setText(StringUtils.dateToString2(cuisine.getCreateDate()));
			List<FDImage> picList = cuisine.getPicList();
			if(picList!=null && picList.size()!=0){
				FDViewUtil.showServerImage(activity, cuisineHolderView.photoImg, picList.get(0).getFilePath());
			}
			
			final int cuisineID = cuisine.getCuisineId();
			convertView.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity,FDDishDetailActivity.class);
					intent.putExtra("fd.dish.detail.key", cuisineID);
					activity.startActivity(intent);
				}
			});
		}
		
		return convertView;
	}
	
	static class CuisineHolderView {
		TextView cuisineNameText;
		ImageView photoImg;
		TextView companyText;
		TextView addressText;
		TextView dateText;
	}
	
}
