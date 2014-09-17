package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantRecommend;
import com.wondersgroup.fdpublicapp.search.activity.FDSearchDetailsActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * @author chengshaohua
 *  我推荐的餐馆  adapter
 */
public class FDUserRecommendRestAdapter extends BaseAdapter {

	private Context activity;
	public List<FDRestaurantRecommend> recommendList;
	
	public FDUserRecommendRestAdapter(Context activity,List<FDRestaurantRecommend> recommendList) {
		super();
		this.activity = activity;
		this.recommendList = recommendList;
	}

	public int getCount() {
		return recommendList==null?0:recommendList.size();
	}

	public Object getItem(int position) {
		return recommendList==null?null:recommendList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDRestaurantRecommend recommend = null;
		HolderView recommendHolderView;
		if (convertView == null) {
			recommendHolderView = new HolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_my_recommend_rest_item, null);
			recommendHolderView.restNameText = (TextView) convertView.findViewById(R.id.fd_user_recommend_rest_name_tv);
			recommendHolderView.photoImg = (ImageView) convertView.findViewById(R.id.fd_user_recommend_rest_photo_img);
			recommendHolderView.credit_img = (ImageView) convertView.findViewById(R.id.fd_user_recommend_rest_credit_img); 
			recommendHolderView.creditText = (TextView) convertView.findViewById(R.id.fd_user_recommend_rest_credit_tv);
			recommendHolderView.supervision = (TextView) convertView.findViewById(R.id.fd_user_recommend_rest_supervision_name_tv);
			recommendHolderView.supervisionDate = (TextView) convertView.findViewById(R.id.fd_user_recommend_rest_supervision_date_tv);
			recommendHolderView.average = (TextView) convertView.findViewById(R.id.fd_user_recommend_rest_expense_tv);
			recommendHolderView.restAddress = (TextView) convertView.findViewById(R.id.fd_user_recommend_rest_address_tv);
			recommendHolderView.cuisineType = (TextView) convertView.findViewById(R.id.fd_user_recommend_rest_cuisine_tv);
			recommendHolderView.distance = (TextView) convertView.findViewById(R.id.fd_user_recommend_rest_distance_tv);
			convertView.setTag(recommendHolderView);
		} else {
			recommendHolderView = (HolderView) convertView.getTag();
		}
		if(recommendList!=null) {
			recommend = recommendList.get(position);
			recommendHolderView.restNameText.setText(recommend.getShopSign());
			recommendHolderView.supervision.setText(recommend.getFoodSafetyOfficial());
			recommendHolderView.supervisionDate.setText(StringUtils.dateToString2(recommend.getFoodSaftyRatingDate()));
			recommendHolderView.creditText.setText(recommend.getFoodSaftyRatingValue());
			recommendHolderView.restAddress.setText(recommend.getCommercialCenterValue());
			recommendHolderView.cuisineType.setText(recommend.getRestCuisineTypeListString());
			recommendHolderView.distance.setText("256米");
			List<FDImage> picList = recommend.getPicList();
			if(picList!=null && picList.size()!=0){
				FDViewUtil.showServerImage(activity, recommendHolderView.photoImg, picList.get(0).getFilePath());
			}
			FDViewUtil.showSafetyRating(recommend.getFoodSaftyRating(), recommendHolderView.credit_img);
			final FDRestaurantRecommend restaurant = recommend;
			convertView.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity,FDSearchDetailsActivity.class);
					intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurant.getId());
					activity.startActivity(intent);
				}
			});
		}
		return convertView;
	}
	
	static class HolderView {
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
