package com.wondersgroup.fdpublicapp.coupon.activity;

import java.util.Date;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView;
import com.wondersgroup.fdpublicapp.common.custom.xListView.LJListView.IXListViewListener;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.coupon.adapter.FDCouponMainAdapter;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCoupon;
import com.wondersgroup.fdpublicapp.coupon.service.FDCouponWrapper;
import com.wondersgroup.fdpublicapp.home.main.activity.FDBaseActivity;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;

/**
 * 餐厅优惠信息 -- 餐厅详情-优惠信息
 * @author chengshaohua
 *
 */
public class FDCouponActivity extends FDBaseActivity implements IXListViewListener {
	
	private FDRestaurant restDetail;
	private LinearLayout backLayout;
	public LJListView couponListView;
	public FDCouponMainAdapter couponAdapter;
	public List<FDCoupon> couponList;
	public Handler mHandler;
	public int pageNo = 1;
	public int totalPage;
	public final static int LOAD_MORE_MAX_COUNT  = 8;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fd_frame_coupon);
		
		Bundle bundle = getIntent().getExtras();
		if (bundle == null || !bundle.containsKey(FDConst.FD_QUERY_COUPON_EXTRA_KEY)) return;
		restDetail = bundle.getParcelable(FDConst.FD_QUERY_COUPON_EXTRA_KEY);
		
		initCouponView();
		loadCouponData();
	}
	
	/**
	 * 初始化餐厅优惠信息
	 */
	public void initCouponView() {
		backLayout = (LinearLayout) findViewById(R.id.fd_restaurant_coupon_back_label);
		couponListView = (LJListView) findViewById(R.id.main_coupon_listView);     
		couponListView.setPullLoadEnable(true, "..");
		couponListView.setPullRefreshEnable(true);
		couponListView.setIsAnimation(true);
		couponListView.setXListViewListener(this);
		couponAdapter = new FDCouponMainAdapter(context,couponList);             
		couponListView.setAdapter(couponAdapter);    
		couponListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context,FDCouponDetailsActivity.class);
				if(couponList!=null && position<=couponList.size()) {
					FDCoupon coupon = couponList.get(position-1);
					if(coupon!=null) {
						intent.putExtra(FDConst.FD_QUERY_COUPON_EXTRA_KEY, coupon);
					}
				}
				context.startActivity(intent);
			}
		});
		
		backLayout.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				finish();
			}
		});
		
	}
	
	public void loadCouponData() {
		if(restDetail==null) return;
		
		FDCouponWrapper couponWrapper = new FDCouponWrapper(context);
		couponWrapper.getCoupons(restDetail.getId(), pageNo++, LOAD_MORE_MAX_COUNT, new FDCallback(){
			public void onCallback(Object callback) {
				if(callback==null) return;
				
				FDResultPage<FDCoupon> couponPage = (FDResultPage<FDCoupon>) callback;
				totalPage = (int) (1+couponPage.getTotalRecord()/LOAD_MORE_MAX_COUNT);
				couponList = couponPage.getResultList();
				
				couponAdapter.setCouponListData(couponList);
				onCommentLoad(couponPage.getTotalRecord());
			}
		});
	}

	public void onRefresh() {
		mHandler = new Handler();

		pageNo = 1;
		onLoadMore();
	}

	public void onLoadMore() {
		if (pageNo > totalPage) {
			onCommentLoad(0);
			return;
		}

		if(mHandler==null) {
			mHandler = new Handler();
		}
		mHandler.post(new Runnable() {
			public void run() {
				loadCouponData();
			}
		});
	}
	
	/**
	 *  更新列表状态
	 * @param count
	 */
	public void onCommentLoad(long count) {
		couponListView.setCount(""+count);
		couponListView.stopRefresh();
		couponListView.stopLoadMore(pageNo>totalPage);
		couponListView.setRefreshTime(StringUtils.dateToString(new Date()));
	}
}
