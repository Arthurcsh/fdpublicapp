package com.wondersgroup.fdpublicapp.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * AbstractService
 * @author chengshaohua
 */

public class AbstractService implements Runnable{

	protected static RestTemplate restTemplate;
	protected CallbackListener listener;
	protected String requestURL;
	protected int httpMethod;
	protected Object objectParams;
	protected Map<String, Object> objectMap;
	protected Map<String, Object> fileMap;
	protected boolean success;

    public static final int DID_START    = 00011; 
    public static final int DID_ERROR    = 00012; 
    public static final int DID_SUCCEED  = 00013; 

    public static final int HTTP_GET     = 0;
    public static final int HTTP_POST    = 1;
    public static final int HTTP_PUT     = 2;
    public static final int HTTP_DELETE  = 3;

    static {
//            restTemplate = new RestTemplate(true);
    }
    
    public static String getRestRequest(String rest) {
    	String requestURL = FDManager.getString(rest);
    	if(!requestURL.startsWith("http://")) {
    		requestURL = "http://"+requestURL;
    	}
    	return requestURL;
    }
    
    // HttpBody参数对象
    public void createHttpConnection(int mothod, String url, Object params, CallbackListener listener) {
    	this.createHttpConnection(mothod,url, listener);
    	this.objectParams = params;
    }
    
    // HttpBody无参数
    public void createHttpConnection(int mothod, String url, CallbackListener listener) {
    	this.createHttpConnection(mothod,url,null, listener);
    }
    
    // 多参数
    public void createHttpConnection(int mothod, String url,Map<String, Object> params, CallbackListener listener) {
    	this.createHttpConnection(mothod,url,params, null, listener);
    }
    
    // 多参数和多附件
    public void createHttpConnection(int mothod, String url,Map<String, Object> params, Map<String, Object> files, CallbackListener listener) {
    	this.httpMethod = mothod;
    	this.listener = listener;
    	this.requestURL = url;
    	this.objectMap = params;
    	this.fileMap = files;
    	
    	restTemplate = new RestTemplate(true);
    	restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//    	System.setProperty("http.keepAlive", "false"); 
//    	headers.set("Connection", "Close");

    	FDConnection.getInstance().push(this);
    }
    
	private static final Handler handler = new Handler() {
		public void handleMessage(Message message) {
			CallbackListener listener = (CallbackListener) message.obj;
			Object data = message.getData();
			
			switch (message.what) {
			case AbstractService.DID_START: {
				if(listener!=null) {
					listener.onStart();
				}
				break;
			}
			case AbstractService.DID_SUCCEED: {
				if (listener != null) {
					if (data != null) {
						Bundle bundle = (Bundle) data;
						String result = bundle.getString("successCallbackkey");
						listener.onSuccess(result);
					}
				}
				break;
			}

			case AbstractService.DID_ERROR: {
				if (data != null) {
					Bundle bundle = (Bundle) data;
					Throwable failure = (Throwable) bundle.get("failureCallbackkey");
					listener.onFailure(failure);
				}
				break;
			}
			}
		}
	};

	
	public void run() {
		System.out.println("URL " + requestURL);
		this.sendStartMessage();
		String restResult = getResponeString(httpMethod);
		this.sendSuccessMessage(restResult);
		
		FDConnection.getInstance().didComplete(this);
//		restTemplate = null;
	}
	

	//传递开始回调
	private void sendStartMessage() {
		Message message = Message.obtain(handler, DID_START, listener);
		Bundle data = new Bundle();
		data.putString("startCallbackkey", "");
		message.setData(data);
		handler.sendMessage(message);
	}
		
    //传递结果回调
	private void sendSuccessMessage(String result) {
		Message message = Message.obtain(handler, DID_SUCCEED, listener);
		Bundle data = new Bundle();
		data.putString("successCallbackkey", result);
		message.setData(data);
		handler.sendMessage(message);
		handler.removeCallbacks(this);
	}
	
	//服务请求异常回调
	private void sendFailureMessage(Throwable failure) {
		Message message = Message.obtain(handler, DID_ERROR, listener);
		Bundle data = new Bundle();
		data.putSerializable("failureCallbackkey", failure);
		message.setData(data);
		handler.sendMessage(message);
	}

	public String getResponeString(int httpMethod) {
		if(restTemplate==null) return null;
		
		MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
		List<Charset> charsetList = new ArrayList<Charset>();
		charsetList.add(Charset.forName("UTF-8"));
//		Map<String, String> charsetMap = new HashMap<String, String>();
//		charsetMap.put("charset", "utf-8");

		Charset utf8 = Charset.forName("UTF-8");
		MediaType mediaType = new MediaType("application", "json", utf8);
		
		HttpHeaders jsonHeaders = null;
		if(objectParams!=null || objectMap!=null) {
			jsonHeaders = new HttpHeaders();
//			jsonHeaders.setAcceptCharset(charsetList);
			jsonHeaders.setContentType(mediaType);
		}
		
		if(objectMap!=null) {
			for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
				HttpEntity<Object> jsonEntity = new HttpEntity<Object>(entry.getValue(), jsonHeaders);
				formData.add(entry.getKey(), jsonEntity);
			}
		}
		
		if(fileMap!=null) {
			HttpHeaders pictureHeaders = new HttpHeaders();
			pictureHeaders.setContentType(MediaType.IMAGE_JPEG);
			
			Iterator<Map.Entry<String, Object>> fileItor = fileMap.entrySet().iterator();
			while (fileItor.hasNext()) {
				Map.Entry<String, Object> entry = fileItor.next();
				Resource res = getFileResource((String) entry.getValue());
				if(res==null) continue;
				
	    		HttpEntity<Resource> imageEntity = new HttpEntity<Resource>(res, pictureHeaders);
				formData.add(entry.getKey(), imageEntity);
			}
		}
		
		ResponseEntity<String> responseEntity = null; 
		String responseString = null;
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			HttpEntity<?> requestEntity = null;
			if(objectParams!=null) {
				requestEntity = new HttpEntity<Object>(objectParams,requestHeaders);
			}else if(objectMap!=null) {
				requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
			}
			if(requestEntity!=null) {
				System.out.println("Parameters: "+objectMap+" HttpBody: "+requestEntity.getBody());
			}
			switch(httpMethod){
			case HTTP_GET:
				responseEntity = restTemplate.exchange(requestURL,HttpMethod.GET, requestEntity, String.class);
				break;
			case HTTP_POST:
				responseEntity = restTemplate.exchange(requestURL,HttpMethod.POST, requestEntity, String.class);
				break;
			case HTTP_PUT:
				responseEntity = restTemplate.exchange(requestURL,HttpMethod.PUT, requestEntity, String.class);
				break;
			case HTTP_DELETE:
				responseEntity = restTemplate.exchange(requestURL,HttpMethod.DELETE, requestEntity, String.class);
				break;
			default:
				break;
			}
			responseString = responseEntity.getBody();
			int result = responseEntity.getStatusCode().value();
		    success = result >= 200 && result < 300;
		}catch(RestClientException ce) {
			success = false;
			sendFailureMessage(ce);
			ce.printStackTrace();
		}
		
		return responseString;
	}
	 
	public Resource getFileResource(String local) {
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
    	bitmapOptions.inSampleSize = 4;
    	Bitmap bitmap = BitmapFactory.decodeFile(local, bitmapOptions);
    	
		if(bitmap==null) return null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, bos);
		byte[] data = bos.toByteArray();
		
		final File file = new File(local);
		if(!file.exists()){
			System.out.println("File is not exist!");
			return null;
		}
		Resource res = new ByteArrayResource(data) {
            public String getFilename() throws IllegalStateException {
            	System.out.println("Resource: "+file.getName());
            	return file.getName();
            }
		};
		if(bitmap != null) {
			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
				bitmap = null;
			}
		}
		
		return res;
	}
	
	public interface CallbackListener {
		public void onStart();
		public void onLoading();
		public void onFailure(Throwable failure);
		public void onSuccess(String result);
	}
}
