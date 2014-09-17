package com.wondersgroup.fdpublicapp.common.protocol;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import com.wondersgroup.fdpublicapp.common.service.AbstractService;
import com.wondersgroup.fdpublicapp.common.service.FDConst;

/**
 * @author chengshaohua
 *
 */
public class AppLoadServer extends AbstractLoadServer{
	
	public void loadMainData(OnLoadedCallbackListener onCallbackListener) {
		this.onLoadedListener = onCallbackListener;
		String url = AbstractService.getRestRequest(FDConst.FD_INDEX_DATA_ALL);
		
		AsyncHttpClient asynchClient = new AsyncHttpClient();
		asynchClient.setTimeout(6000);    
		
		asynchClient.get(url, null, new AsyncHttpResponseHandler() { 
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if(responseString==null) return;
				try {
					JSONObject responseFull = new JSONObject(responseString);
					String commercial = responseFull.optString("commercialCenter");
					String atmosphere = responseFull.optString("companyAtomsphere","");
					String cuisineType = responseFull.optString("cusineType");
					String creditRating = responseFull.optString("foodSafetyRating");
					String vegetablesType = responseFull.optString("companyVegetablesType","0");
					String averageConsum = responseFull.optString("companyAverageConsumption");
					
					loadBasicInformation(commercial,atmosphere,cuisineType,creditRating,vegetablesType,averageConsum);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    }
			public void onFinish() {
				if(onLoadedListener!=null) {
					onLoadedListener.onSuccessCallback();
				}
			}
		});
	}
	
	// 商圈列表
	public void loadCommerialCenter(OnLoadedCallbackListener onCallbackListener) {
		this.onLoadedListener = onCallbackListener;
		String url = AbstractService.getRestRequest(FDConst.FD_INDEX_CIRCLE_TREE);
		AsyncHttpClient asynchClient = new AsyncHttpClient();
		asynchClient.setTimeout(20000);    
		asynchClient.get(url, null, new TextHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if(responseString==null) return;
				try {
					getCommerialFromJson(responseString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    }
			
			public void onStart() {
			}
			public void onFinish() {
				if(onLoadedListener!=null) {
					onLoadedListener.onSuccessCallback();
				}
			}
			
		});
	}
	
	// 菜系类型
	public void loadCuisineType(OnLoadedCallbackListener onCallbackListener) {
		this.onLoadedListener = onCallbackListener;
		String url = AbstractService.getRestRequest(FDConst.FD_INDEX_CUISINE_TYPE);
		AsyncHttpClient asynchClient = new AsyncHttpClient();
		asynchClient.setTimeout(20000);    
		asynchClient.get(url, null, new TextHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if(responseString==null) return;
				try {
					getCuisineTypeFromJson(responseString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    }
			
			public void onStart() {
			}
			public void onFinish() {
				if(onLoadedListener!=null) {
					onLoadedListener.onSuccessCallback();
				}
			}
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
			}
			
		});
	}
	
	// 餐厅氛围
	public void loadAtmosphereList(OnLoadedCallbackListener onCallbackListener) {
		this.onLoadedListener = onCallbackListener;
		String url = AbstractService.getRestRequest(FDConst.FD_INDEX_ATMOSPHERE_LIST);
		AsyncHttpClient asynchClient = new AsyncHttpClient();
		asynchClient.setTimeout(20000);    
		 
		asynchClient.get(url, null, new TextHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if(responseString==null) return;
				try {
					getAtmosphereFromJson(responseString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    }
			
			public void onStart() {
			}
			public void onFinish() {
				if(onLoadedListener!=null) {
					onLoadedListener.onSuccessCallback();
				}
			}
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
			}
			
		});
	}
	
	// 信用等级
	public void loadCreditList(OnLoadedCallbackListener onCallbackListener) {
		this.onLoadedListener = onCallbackListener;
		String url = AbstractService.getRestRequest(FDConst.FD_INDEX_CREDIT_LIST);
		AsyncHttpClient asynchClient = new AsyncHttpClient();
		asynchClient.setTimeout(20000);    
		asynchClient.get(url, null, new TextHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if(responseString==null) return;
				try {
					getCreditFromJson(responseString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    }
			
			public void onFinish() {
				if(onLoadedListener!=null) {
					onLoadedListener.onSuccessCallback();
				}
			}
			
		});
	}
}
