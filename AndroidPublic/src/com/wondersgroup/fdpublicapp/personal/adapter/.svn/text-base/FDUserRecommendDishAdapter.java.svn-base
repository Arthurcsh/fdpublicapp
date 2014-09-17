package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.personal.activity.FDDishDetailActivity;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineRecommend;
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
 *  我推荐的菜肴  adapter
 */
public class FDUserRecommendDishAdapter extends BaseAdapter {

	private Context activity;
	public List<FDCuisineRecommend> recommendList;
	
	public FDUserRecommendDishAdapter(Context activity,List<FDCuisineRecommend> recommendList) {
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
		FDCuisineRecommend recommend = null;
		CuisineHolderView recommendHolderView;
		if (convertView == null) {
			recommendHolderView = new CuisineHolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_my_recommend_cuisine_item, null);
			recommendHolderView.cuisineNameText = (TextView) convertView.findViewById(R.id.fd_user_recommend_cuisine_name_tv);
			recommendHolderView.photoImg = (ImageView) convertView.findViewById(R.id.fd_user_recommend_cuisine_photo_img);
			recommendHolderView.companyText = (TextView) convertView.findViewById(R.id.fd_user_recommend_cuisine_company_tv); 
			recommendHolderView.addressText = (TextView) convertView.findViewById(R.id.fd_user_recommend_cuisine_address_tv);
			recommendHolderView.dateText = (TextView) convertView.findViewById(R.id.fd_user_recommend_cuisine_date_tv);
			convertView.setTag(recommendHolderView);
		} else {
			recommendHolderView = (CuisineHolderView) convertView.getTag();
		}
		if(recommendList!=null) {
			recommend = recommendList.get(position);
			recommendHolderView.cuisineNameText.setText(recommend.getName());
			recommendHolderView.companyText.setText(recommend.getRestaurantName());
			recommendHolderView.addressText.setText(recommend.getRestaurantAddress());
			recommendHolderView.dateText.setText(StringUtils.dateToString2(recommend.getRecommendDate()));
			List<FDImage> picList = recommend.getPicList();
			if(picList!=null && picList.size()!=0){
				FDViewUtil.showServerImage(activity, recommendHolderView.photoImg, picList.get(0).getFilePath());
			}
			
			final int cuisineID = recommend.getCuisineId();
			convertView.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity, FDDishDetailActivity.class);
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
