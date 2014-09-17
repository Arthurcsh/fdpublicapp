package com.wondersgroup.fdpublicapp.personal.adapter;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCoupon;
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
 *  我收藏的优惠  adapter
 */
public class FDUserCouponFavoriteAdapter extends BaseAdapter {

	private Context activity;
	public List<FDCoupon> couponList;
	
	public FDUserCouponFavoriteAdapter(Context activity,List<FDCoupon> couponList) {
		super();
		this.activity = activity;
		this.couponList = couponList;
	}

	public int getCount() {
		return couponList==null?0:couponList.size();
	}

	public Object getItem(int position) {
		return couponList==null?null:couponList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FDCoupon coupon = null;
		HolderView couponHolderView;
		if (convertView == null) {
			couponHolderView = new HolderView();
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_usercenter_my_coupon_favorite_item, null);
			couponHolderView.restNameText = (TextView) convertView.findViewById(R.id.fd_user_coupon_favorite_name_tv);
			couponHolderView.photoImageView = (ImageView) convertView.findViewById(R.id.fd_user_coupon_favorite_photo_img);
			couponHolderView.couponDetail = (TextView) convertView.findViewById(R.id.fd_user_coupon_favorite_detail_tv);
			couponHolderView.donwloadCountView = (TextView) convertView.findViewById(R.id.fd_user_coupon_favorite_count_tv);
			couponHolderView.regionTextView = (TextView) convertView.findViewById(R.id.fd_user_coupon_favorite_region_tv);
			convertView.setTag(couponHolderView);
		} else {
			couponHolderView = (HolderView) convertView.getTag();
		}
		if(couponList!=null) {
			coupon = couponList.get(position);
			couponHolderView.restNameText.setText(coupon.getRestaurantName());
			couponHolderView.couponDetail.setText(coupon.getTitle());
			couponHolderView.regionTextView.setText(coupon.getCommercialCenterValue());
			List<FDImage> picList = coupon.getPics();
			if(picList!=null && picList.size()!=0){
				FDViewUtil.showServerImage(activity, couponHolderView.photoImageView, picList.get(0).getFilePath());
			}
			final int restaurantId = coupon.getRestaurantId();
			convertView.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(activity,FDSearchDetailsActivity.class);
					intent.putExtra(FDConst.FD_QUERY_RESTAURANT_EXTRA_KEY, restaurantId);
					activity.startActivity(intent);
				}
			});
		}
		return convertView;
	}
	
	static class HolderView {
		TextView restNameText;
		ImageView photoImageView;
		TextView couponDetail;
		TextView donwloadCountView;
		TextView regionTextView;
	}
}
