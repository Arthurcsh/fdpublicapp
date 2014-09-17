package com.wondersgroup.fdpublicapp.coupon.activity;

import java.util.List;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCoupon;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCouponDetail;
import com.wondersgroup.fdpublicapp.coupon.service.FDCouponWrapper;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 优惠详情
 * @author chengshaohua
 *
 */
public class FDCouponDetailsActivity extends FDBaseActivity implements OnClickListener {
	private FDCoupon coupon;
	private FDCouponDetail couponDetail;
	private LinearLayout backImgLayout;
	private TextView couponNameText;
	private TextView couponDetailContentText;
	private TextView couponFromDateText;
	private TextView couponToDateText;
	private ImageView collectImageView;
	private ImageView couponImageView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_coupon_details_xml);
		
		Bundle bundle = getIntent().getExtras();
		if (bundle == null || !bundle.containsKey(FDConst.FD_QUERY_COUPON_EXTRA_KEY))
			return;
		
		coupon = bundle.getParcelable(FDConst.FD_QUERY_COUPON_EXTRA_KEY);
		
		initCouponDetailView();
		loadCouponDetailData();
	}
	
	/**
	 * 初始化优惠详情
	 */
	private void initCouponDetailView() {
		backImgLayout = (LinearLayout) findViewById(R.id.coupon_details_back_img_layout);          // 返回按钮
		couponNameText = (TextView) findViewById(R.id.coupon_details_name_textview);               // 对应公司，店招
		couponDetailContentText = (TextView) findViewById(R.id.coupon_details_content_textview);   // 优惠详情内容
		couponFromDateText = (TextView) findViewById(R.id.coupon_details_date_from);               // 日期
		couponToDateText = (TextView) findViewById(R.id.coupon_details_date_to);      
		couponImageView = (ImageView) findViewById(R.id.main_coupon_details_photo_img);
		
		collectImageView = (ImageView) findViewById(R.id.coupon_details_star_level_img);           // 收藏
		backImgLayout.setOnClickListener(this);
		collectImageView.setOnClickListener(this);
	}
	
	/**
	 * 设置优惠详情信息
	 * @param coupon
	 */
	public void setCouponDetailView(final FDCouponDetail coupon) {
		if (coupon == null) return;
		
		List<FDImage> couponImage = coupon.getPics();
		if(couponImage!=null && couponImage.size()>0){
			FDViewUtil.showServerImage(context, couponImageView, couponImage.get(0).getFilePath());
		}
		couponNameText.setText(coupon.getRestaurantNameAbbrev());
		couponDetailContentText.setText(coupon.getTitle());
		couponFromDateText.setText(coupon.getStartFrom());
		couponToDateText.setText(coupon.getEndTo());
		if(coupon.getIsCollected()==0) {
			collectImageView.setImageResource(R.drawable.fd_restaurant_collection_disable);
		}else if(coupon.getIsCollected()==1) {
			collectImageView.setImageResource(R.drawable.fd_restaurant_collection_enable);
		}
	}

	/**
	 * 加载优惠详情信息
	 */
	public void loadCouponDetailData() {
		if(coupon==null) return;
		
		FDCouponWrapper couponWrapper = new FDCouponWrapper(context);
		couponWrapper.getCouponDetail(coupon.getDiscountId(), new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				couponDetail = (FDCouponDetail) callback;
				setCouponDetailView(couponDetail);
			}
		});
	}

	public void onClick(View view) {
		if (view == backImgLayout) {
			finish();
		}else if(view == collectImageView) {
			FDCouponWrapper couponWrapper = new FDCouponWrapper(context);
			int favoriteEanble = couponDetail.getIsCollected()==0?1:0;
			couponWrapper.setFavoriteCoupon(coupon.getDiscountId(), favoriteEanble, new FDCallback(){
				public void onCallback(Object callback) {
					if(callback==null) return;
					int status = (Integer) callback;
					if(status==0) {
						FDViewUtil.showTaggleToast(context, couponDetail.getIsCollected(), "收藏");
						loadCouponDetailData();
					}
				}
			});
		}
		
	}
}
