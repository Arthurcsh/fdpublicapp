package com.wondersgroup.fdpublicapp.common.service;

import java.util.Map;

import android.content.Context;

import com.wondersgroup.fdpublicapp.common.impl.FDCallbackListener;

/**
 * 客户端数据接口
 * @author chengshaohua
 */

public class FDRestClient extends AbstractService {

	public FDRestClient(Context context) {
		this.context = context;
	}
	
	/**
     * 所有服务请求
     * @param httpMethod          请求类型
     * @param url                 请求地址
     * @param params              参数
     * @param callbackListener    回调
     */
	public void excuteRestServer(int httpMethod, String url, FDCallbackListener callbackListener) {
		this.createHttpConnection(httpMethod,url,callbackListener);
	}
	
	// 该方法取消
//	public void excuteRestServer(int httpMethod, String url, Object params, CallbackListener callbackListener) {
//		this.createHttpConnection(httpMethod,url,params,callbackListener);
//	}
	
	// JSON格式字符串参数
	public void excuteRestServer(int httpMethod, String url, String jsonString, FDCallbackListener callbackListener) {
		this.createHttpConnection(httpMethod,url,jsonString,callbackListener);
	}
	
	public void excuteRestServer(int httpMethod, String url, Map<String, String> params, FDCallbackListener callbackListener) {
		this.createHttpConnection(httpMethod,url,params,callbackListener);
	}
	 
	public void excuteRestServer(int httpMethod, String url, Map<String, String> params, Map<String, String> files, FDCallbackListener callbackListener) {
		if (httpMethod < 0 || url == null)
			return;

		this.createHttpConnection(httpMethod,url,params, files, callbackListener);
	}
}
