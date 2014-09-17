package com.wondersgroup.fdpublicapp.personal.activity;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCoupon;
import com.wondersgroup.fdpublicapp.coupon.service.FDCouponWrapper;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.activity.FDListViewFragment;
import com.wondersgroup.fdpublicapp.personal.adapter.FDUserCouponFavoriteAdapter;

/**
 * 我收藏的优惠
 * @author chengshaohua
 */
public class FDUserCenterMyCouponFavoriteFragment extends FDListViewFragment {
	private ArrayList<FDCoupon> favoriteCouponList = new ArrayList<FDCoupon>();
	private FDUserCouponFavoriteAdapter couponAdapter;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
		
    	initFavoriteCouponView();
        
        return fragmentView;
    }
	
    
    /**
     *  初始化我的收藏的优惠
     */
    public void initFavoriteCouponView() {
    	couponAdapter = new FDUserCouponFavoriteAdapter(context,favoriteCouponList);         
		fdListView.setAdapter(couponAdapter);
		
		loadFragmentPageList();
    }
	
    
	/**
	 * 加载我收藏的优惠
	 */
	public void loadFragmentPageList() {
		FDCouponWrapper couponWrapper = new FDCouponWrapper(context);
		couponWrapper.getFavoriteCoupons(pageIndex, FDBaseActivity.LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				FDResultPage<FDCoupon> resultPage = (FDResultPage<FDCoupon>) callback;
				if(resultPage!=null) {
					long totalRecord = 0;
					pageCount = resultPage.getPageCount();
					List<FDCoupon> resultRestList = resultPage.getResultList();
					if(resultRestList==null || pageIndex==1) {
						favoriteCouponList.clear();
						totalRecord = 0;
					}
					if(resultRestList!=null) {
						favoriteCouponList.addAll(resultRestList);
						totalRecord = resultPage.getTotalRecord();
					}
					couponAdapter.notifyDataSetChanged();
					onListViewLoad(totalRecord);
				}
			}
		});
	}  
}
