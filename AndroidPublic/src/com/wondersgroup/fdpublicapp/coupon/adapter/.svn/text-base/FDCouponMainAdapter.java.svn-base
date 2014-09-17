package com.wondersgroup.fdpublicapp.coupon.adapter;

import java.util.ArrayList;
import java.util.List;

import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewHolder;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCoupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * @author chengshaohua
 *  商家优惠adapter
 */
public class FDCouponMainAdapter extends BaseAdapter {

	private Context activity;
	public List<FDCoupon> couponList;
	
	public FDCouponMainAdapter(Context activity,List<FDCoupon> mlist) {
		super();
		this.activity = activity;
		this.couponList = mlist;
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
		
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.fd_coupon_result_mian_listitem, null);
			ImageView couponImageView = FDViewHolder.get(convertView, R.id.main_coupon_imageview); 
			TextView restNameText = FDViewHolder.get(convertView, R.id.main_coupon_info_name);
			TextView couponDescText = FDViewHolder.get(convertView, R.id.main_hot_restaurant_money);
			if(couponList!=null) {
				coupon = couponList.get(position);
				restNameText.setText(coupon.getRestaurantNameAbbrev());
				couponDescText.setText(coupon.getTitle());
				List<FDImage> imageList = coupon.getPics();
				if(imageList!=null && imageList.size()>0) {
					FDViewUtil.showServerImage(activity, couponImageView, imageList.get(0).getFilePath());
				}
			}
		} 
		
		return convertView;
	}
	
	public void setCouponListData(List<FDCoupon> coupons) {
		this.couponList = (List<FDCoupon>) ((ArrayList<FDCoupon>) coupons).clone();
		this.notifyDataSetChanged();
	}
}
