package com.wondersgroup.fdpublicapp.coupon.service;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDBaseWrapper;
import com.wondersgroup.fdpublicapp.common.protocol.FDParseException;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.service.AbstractService;
import com.wondersgroup.fdpublicapp.common.service.FDCallbackListenerAdapter;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.FDRestClient;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCoupon;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCouponDetail;

/**
 * 优惠信息包
 * @author chengshaohua
 *
 */
public class FDCouponWrapper extends FDBaseWrapper {

	public FDCouponWrapper(Context context) {
		super(context);
	}

	/**
	 * 查询优惠信息
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getCoupons(int restId, int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_COUPON_QUERY_INFO)+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
    	Map<String,String> couponParam = new HashMap<String,String>();
		if (restId > 0) {
			couponParam.put("companyId", ""+restId);
		}
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, couponParam, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "加载优惠信息.."); 
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("coupon info -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDResultPage<FDCoupon> couponPage = resultParser.getResultPageWithData(FDCoupon.class);
					callback.onCallback(couponPage);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 优惠信息详情
	 * @param coponId
	 * @param callback
	 */
	public void getCouponDetail(int coponId, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_COUPON_QUERY_DETAIL)+coponId;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "加载优惠详情..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("coupon detail -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDCouponDetail couponDetail = resultParser.getResultData(FDCouponDetail.class);
					callback.onCallback(couponDetail);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 收藏的优惠券
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getFavoriteCoupons(int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_COUPON_QUERY_FAVORITE)+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "加载收藏的优惠券..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("coupon favorite -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDResultPage<FDCoupon> couponPage = resultParser.getResultPageWithData(FDCoupon.class);
					callback.onCallback(couponPage);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 收藏优惠
	 * @param couponId
	 * @param enable
	 * @param callback
	 */
	public void setFavoriteCoupon(int couponId, final int enable, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_COUPON_FETCH_FAVORITE);
		Map<String, String> couponParams = new HashMap<String, String>();
		couponParams.put("discountId", ""+couponId);
		couponParams.put("enable", ""+enable);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, couponParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "收藏优惠券..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("coupon favorite -------------------------------- "+result+" enable---"+enable);
				FDResultParser resultParser = new FDResultParser(context,result);
				int status = resultParser.getBaseResult().getStatus();
				callback.onCallback(status);
			}
		});
	}
}
