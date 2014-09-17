package com.wondersgroup.fdpublicapp.home.safety.service;

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
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;

/**
 * 食品安全信息通信包
 * @author chengshaohua
 *
 */
public class FDSafetWrapper extends FDBaseWrapper {

	public FDSafetWrapper(Context context) {
		super(context);
	}

	/**
	 * 查询食品安全信息
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getFoodSafetyInfo(int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_SAFETY_QUERY_INFO)+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "食品安全信息..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("safety info -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDResultPage<FDNoteComment> foodSafetyPage = resultParser.getResultPageWithData(FDNoteComment.class);
					callback.onCallback(foodSafetyPage);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 食品安全信息详情
	 * @param safetyInfoId
	 * @param callback
	 */
	public void getFoodSafetyDetail(int safetyInfoId, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_SAFETY_QUERY_DETAIL)+safetyInfoId;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "食品安全信息详情..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("safety detail -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDResultPage<FDReply> couponPage = resultParser.getResultPageWithData(FDReply.class);
					callback.onCallback(couponPage);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
