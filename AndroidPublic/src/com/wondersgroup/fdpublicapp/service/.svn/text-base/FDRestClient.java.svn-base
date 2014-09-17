package com.wondersgroup.fdpublicapp.service;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * 客户端数据接口
 * @author chengshaohua
 */

public class FDRestClient extends AbstractService {

	/**
     * 所有服务请求
     * @param httpMethod          请求类型
     * @param url                 请求地址
     * @param params              参数
     * @param callbackListener    回调
     */
	public void excuteRestServer(int httpMethod, String url, CallbackListener callbackListener) {
		this.createHttpConnection(httpMethod,url,callbackListener);
	}
	
	public void excuteRestServer(int httpMethod, String url, Object params, CallbackListener callbackListener) {
		this.createHttpConnection(httpMethod,url,params,callbackListener);
	}
	
	public void excuteRestServer(int httpMethod, String url, Map<String, Object> params, CallbackListener callbackListener) {
		this.createHttpConnection(httpMethod,url,params,callbackListener);
	}
	 
	public void excuteRestServer(int httpMethod, String url, Map<String, Object> params, Map<String, Object> files, CallbackListener callbackListener) {
		if (httpMethod < 0 || url == null)
			return;

		this.createHttpConnection(httpMethod,url,params, files, callbackListener);
	}
     
     
     /**
      * 上传文件
      * @param path
      * @return CallbackListener
      */
     public void UploadFile(String path, CallbackListener callback){
             MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
             Resource resource;
             try {
                     resource = new UrlResource("file://"+path);
                     formData.add("json", resource);
             } catch (MalformedURLException e) {
                     e.printStackTrace();
             } 
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
				formData, requestHeaders);
//		ResponseEntity<String> response = restTemplate.exchange(FDConst.FD_USER_MANAGERMENT_UPLOAD,HttpMethod.POST, requestEntity, String.class);
//		
//		createHttpConnection(AbstractService.HTTP_POST, FDConst.FD_USER_MANAGERMENT_UPLOAD,null,callback);
     }

}
