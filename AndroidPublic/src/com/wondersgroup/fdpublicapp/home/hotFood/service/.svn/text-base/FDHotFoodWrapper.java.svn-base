package com.wondersgroup.fdpublicapp.home.hotFood.service;

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
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;

/**
 * 热点美食--猜你想吃
 * @author chengshaohua
 *
 */
public class FDHotFoodWrapper extends FDBaseWrapper {

	public FDHotFoodWrapper(Context context) {
		super(context);
	}

	/**
	 * 查询主页热点美食
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getHotFoods(int distance, double latitude, double longitude, int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_INDEX_QUERY_BY_CONDITION)+pageNo+"/"+pageSize;
		Map<String, String> hotFoodParam = new HashMap<String, String>();
		hotFoodParam.put("distance", ""+distance);
		hotFoodParam.put("longitude", ""+longitude);
		hotFoodParam.put("latitude", ""+latitude);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, hotFoodParam, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "加载热点美食..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("--hot food page -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					if(resultParser!=null) {
						FDResultPage<FDRestaurant> restaurantPage = resultParser.getResultPageWithData(FDRestaurant.class);
						callback.onCallback(restaurantPage);
					}
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
