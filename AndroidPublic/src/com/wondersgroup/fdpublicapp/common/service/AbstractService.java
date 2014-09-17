package com.wondersgroup.fdpublicapp.common.service;

import static org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.wondersgroup.fdpublicapp.common.impl.FDCallbackListener;
import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;
import com.wondersgroup.fdpublicapp.common.protocol.AppException;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * AbstractService
 * 
 * @author chengshaohua
 */

public class AbstractService implements Runnable{
	
    protected Context context;
    public static final String UTF_8 = "UTF-8";
    private final static int TIMEOUT_CONNECTION = 20000;
	private final static int TIMEOUT_SOCKET = 20000;
	private final static int RETRY_TIME = 3;
	protected static RestTemplate restTemplate;
	protected static AssetManager assetManager; 
	public static void setAssetManager(AssetManager assetManager) {
		AbstractService.assetManager = assetManager;
	}
	/*protected static Context context; 
	public static void setContext(Context context) {
		AbstractService.context = context;
	}*/
	protected static Activity activity; 
	public static void setActivity(Activity activity) {
		AbstractService.activity = activity;
	}
	protected FDCallbackListener listener;      // 响应回调
	protected String requestURL;                // 请求地址
	protected int httpMethod;                   // HTTP协议
	protected String objectParams;              // Json参数
	protected Map<String, String> objectMap;    // 键值参数
	protected Map<String, String> fileMap;      // 文件参数
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
    	return ServiceManager.getString(rest);
    }
    
    /**
     * @param mothod json参数
     * @param url 请求路径
     * @param params  JSON参数  ,JSON格式字符串：{"key":"value"}
     * @param listener  回调
     */
    public void createHttpConnection(int mothod, String url, String params, FDCallbackListener listener) {
    	this.createHttpConnection(mothod,url, listener);
    	this.objectParams = params;
    }
    
    /**
     * @param mothod  无参数
     * @param url 服务请求路径
     * @param listener  回调
     */
    public void createHttpConnection(int mothod, String url, FDCallbackListener listener) {
    	this.createHttpConnection(mothod,url,null, null, listener);
    }
    
    // 多参数
    /**
     * @param mothod   Map键值对
     * @param url      服务请求路径
     * @param params   请求参数
     * @param listener 回调
     */
    public void createHttpConnection(int mothod, String url,Map<String, String> params, FDCallbackListener listener) {
    	this.createHttpConnection(mothod,url,params, null, listener);
    }
    
    /**
     * @param mothod  多参数和多附件
     * @param url     服务请求路径
     * @param params  Map键值对参数
     * @param files   其他参数(图片、文件)路径
     * @param listener 回调
     */
    public void createHttpConnection(int mothod, String url,Map<String, String> params, Map<String, String> files, FDCallbackListener listener) {
    	this.httpMethod = mothod;
    	this.listener = listener;
    	this.requestURL = url;
    	this.objectMap = params;
    	this.fileMap = files;
    	
    	restTemplate = new RestTemplate(true);
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(60000);              // 设置超时
//		initHttps(requestFactory);                            //if ssl init failed, only http will work
    	restTemplate.setRequestFactory(requestFactory);
    	
    	//add interceptor to add cookie into http header
    	List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    	interceptors.add(new FDClientHttpRequestInterceptor(context));
    	restTemplate.setInterceptors(interceptors);

    	ConnectionManager.getInstance().push(this);
    }
    
    /**
     *  初始化Https协议
     * @param requestFactory
     */
    private void initHttps(HttpComponentsClientHttpRequestFactory requestFactory){
    	DefaultHttpClient httpClient = (DefaultHttpClient) requestFactory.getHttpClient();
		try {
			KeyStore keyStore  = KeyStore.getInstance(KeyStore.getDefaultType());
			//keyStore.load(null,null);
			InputStream ins = assetManager.open("truststore.bks");
//			keyStore.load(ins, Constant.TRUST_STORE_PASSWORD.toCharArray());
			keyStore.aliases();
			SSLSocketFactory sf = new SSLSocketFactory(keyStore);
			sf.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);
			ins.close();
			
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sf, 443));
		} catch (Exception e) {
			Log.e(getClass().getSimpleName(), "initHttps failed.");
			Log.e(getClass().getSimpleName(), e.getMessage());
		}
    }
    
	private static final Handler handler = new Handler() {
		public void handleMessage(Message message) {
			FDCallbackListener listener = (FDCallbackListener) message.obj;
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
		String restResult = getHttpResponeString(httpMethod);
		this.sendSuccessMessage(restResult);
		
		ConnectionManager.getInstance().didComplete(this);
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
		//根据自定义规则过滤返回值, 1 time out, 2 app version not supported
//		if(!SecurityUtils.filterResponse(result, activity)){
			Message message = Message.obtain(handler, DID_SUCCEED, listener);
			Bundle data = new Bundle();
			data.putString("successCallbackkey", result);
			message.setData(data);
			handler.sendMessage(message);
//		}
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

	/**
	 *  返回请求
	 * @param httpMethod   请求协议
	 * @return
	 */
	public String getHttpResponeString(int httpMethod) {
		if(restTemplate==null) return null;
		
		Charset utf8 = Charset.forName("UTF-8");
		MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
		List<Charset> charsetList = new ArrayList<Charset>();
		charsetList.add(utf8);

		MediaType mediaType = new MediaType("application", "json", utf8);
		HttpHeaders jsonHeaders = null;
		if(objectParams!=null || objectMap!=null) {
			jsonHeaders = new HttpHeaders();
//			jsonHeaders.setAcceptCharset(charsetList);
			jsonHeaders.setContentType(mediaType);
		}
		if (!StringUtils.isEmpty(objectParams)) {
			try {
				JSONObject jsonObject = new JSONObject(objectParams);
				if (jsonObject != null) {
					for (Iterator itor = jsonObject.keys(); itor.hasNext();) {
						String key = (String) itor.next();
						String value = jsonObject.getString(key);
						HttpEntity<Object> jsonEntity = new HttpEntity<Object>(value, jsonHeaders);
						formData.add(key, jsonEntity);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtils.isEmpty(objectMap)) {
			for (Map.Entry<String, String> entry : objectMap.entrySet()) {
				HttpEntity<Object> jsonEntity = new HttpEntity<Object>(entry.getValue(), jsonHeaders);
				formData.add(entry.getKey(), jsonEntity);
			}
		}
		
		if(!StringUtils.isEmpty(fileMap)) {
			HttpHeaders pictureHeaders = new HttpHeaders();
			pictureHeaders.setContentType(MediaType.IMAGE_JPEG);
		//	MediaType mediaType = new MediaType("text","html",Charset.forName("UTF-8")); 
			
			Iterator<Map.Entry<String, String>> fileItor = fileMap.entrySet().iterator();
			while (fileItor.hasNext()) {
				Map.Entry<String, String> entry = fileItor.next();
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
			if(objectParams!=null && requestURL.contains("/security/")){
				requestEntity = new HttpEntity<Object>(objectParams,requestHeaders);
			}
			else if(objectParams!=null || objectMap!=null) {
				requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
			}
			if(requestEntity!=null) {
				System.out.println("Parameters: "+objectParams+" HttpBody: "+requestEntity.getBody());
			}
			HttpMethod restHttpMethod = HttpMethod.GET;
			switch(httpMethod){
			case HTTP_GET:
				restHttpMethod = HttpMethod.GET;
				break;
			case HTTP_POST:
				restHttpMethod = HttpMethod.POST;
				break;
			case HTTP_PUT:
				restHttpMethod = HttpMethod.PUT;
				break;
			case HTTP_DELETE:
				restHttpMethod = HttpMethod.DELETE;
				break;
			}
			responseEntity = restTemplate.exchange(requestURL,restHttpMethod, requestEntity, String.class);
			responseString = responseEntity.getBody();
			int result = responseEntity.getStatusCode().value();
		    success = result >= 200 && result < 300;
		}catch(ResourceAccessException e){
			Log.e("AbstractService", "", e);
		}catch(Exception ce) {    //必须catch所有的exception
			success = false;
			sendFailureMessage(ce);
			ce.printStackTrace();
		}
		
		return responseString;
	}
	 
	/**
	 *   获取文件资源
	 * @param local  文件路径
	 * @return       返回资源
	 */
	public Resource getFileResource(String localResource) {
		if(StringUtils.isEmpty(localResource)) return null;
		
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
    	bitmapOptions.inSampleSize = 4;
    	Bitmap bitmap = BitmapFactory.decodeFile(localResource, bitmapOptions);
    	
		if(bitmap==null) return null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, bos);
		byte[] data = bos.toByteArray();
		
		final File file = new File(localResource);
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
	
	
	/**
	 * get请求URL
	 * @param url
	 * @throws AppException 
	 */
	public static InputStream http_get(FDAppContext appContext, String url) throws AppException {	
		//System.out.println("get_url==> "+url);
//		String cookie = getCookie(appContext);
//		String userAgent = getUserAgent(appContext);
		
		HttpClient httpClient = null;
		GetMethod httpGet = null;

		String responseBody = "";
		int time = 0;
		do{
			try 
			{
				httpClient = getHttpClient();
				httpGet = getHttpGet(url, null, null);			
				int statusCode = httpClient.executeMethod(httpGet);
				if (statusCode != HttpStatus.SC_OK) {
					throw AppException.http(statusCode);
				}
				responseBody = httpGet.getResponseBodyAsString();
				//System.out.println("XMLDATA=====>"+responseBody);
				break;				
			} catch (HttpException e) {
				time++;
				if(time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {} 
					continue;
				}
				// 发生致命的异常，可能是协议不对或者返回的内容有问题
				e.printStackTrace();
				throw AppException.http(e);
			} catch (IOException e) {
				time++;
				if(time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {} 
					continue;
				}
				// 发生网络异常
				e.printStackTrace();
				throw AppException.network(e);
			} finally {
				// 释放连接
				httpGet.releaseConnection();
				httpClient = null;
			}
		}while(time < RETRY_TIME);
		
		responseBody = responseBody.replaceAll("\\p{Cntrl}", "");
		if(responseBody.contains("result") && responseBody.contains("errorCode") && appContext.containsProperty("user.uid")){
			try {
//				Result res = Result.parse(new ByteArrayInputStream(responseBody.getBytes()));	
//				if(res.getErrorCode() == 0){
//					appContext.Logout();
//					appContext.getUnLoginHandler().sendEmptyMessage(1);
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return new ByteArrayInputStream(responseBody.getBytes());
	}
	
	/**
	 * 公用post方法
	 * @param url
	 * @param params
	 * @param files
	 * @throws AppException
	 */
	public static InputStream _post(FDAppContext appContext, String url, Map<String, Object> params, Map<String,File> files) throws AppException {
		//System.out.println("post_url==> "+url);
//		String cookie = getCookie(appContext);
//		String userAgent = getUserAgent(appContext);
		
		HttpClient httpClient = null;
		PostMethod httpPost = null;
		
		//post表单参数处理
		int length = (params == null ? 0 : params.size()) + (files == null ? 0 : files.size());
		Part[] parts = new Part[length];
		int i = 0;
        if(params != null)
        for(String name : params.keySet()){
        	parts[i++] = new StringPart(name, String.valueOf(params.get(name)), UTF_8);
        	//System.out.println("post_key==> "+name+"    value==>"+String.valueOf(params.get(name)));
        }
        if(files != null)
        for(String file : files.keySet()){
        	try {
				parts[i++] = new FilePart(file, files.get(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        	//System.out.println("post_key_file==> "+file);
        }
		
		String responseBody = "";
		int time = 0;
		do{
			try 
			{
				httpClient = getHttpClient();
				httpPost = getHttpPost(url, null, null);	        
		        httpPost.setRequestEntity(new MultipartRequestEntity(parts,httpPost.getParams()));		        
		        int statusCode = httpClient.executeMethod(httpPost);
		        if(statusCode != HttpStatus.SC_OK) 
		        {
		        	throw AppException.http(statusCode);
		        }
		        else if(statusCode == HttpStatus.SC_OK) 
		        {
		            Cookie[] cookies = httpClient.getState().getCookies();
		            String tmpcookies = "";
		            for (Cookie ck : cookies) {
		                tmpcookies += ck.toString()+";";
		            }
		            //保存cookie   
	        		if(appContext != null && tmpcookies != ""){
	        			appContext.setProperty("cookie", tmpcookies);
//	        			appCookie = tmpcookies;
	        		}
		        }
		     	responseBody = httpPost.getResponseBodyAsString();
		        //System.out.println("XMLDATA=====>"+responseBody);
		     	break;	     	
			} catch (HttpException e) {
				time++;
				if(time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {} 
					continue;
				}
				// 发生致命的异常，可能是协议不对或者返回的内容有问题
				e.printStackTrace();
				throw AppException.http(e);
			} catch (IOException e) {
				time++;
				if(time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {} 
					continue;
				}
				// 发生网络异常
				e.printStackTrace();
				throw AppException.network(e);
			} finally {
				// 释放连接
				httpPost.releaseConnection();
				httpClient = null;
			}
		}while(time < RETRY_TIME);
        
        responseBody = responseBody.replaceAll("\\p{Cntrl}", "");
		if(responseBody.contains("result") && responseBody.contains("errorCode") && appContext.containsProperty("user.uid")){
			try {
//				Result res = Result.parse(new ByteArrayInputStream(responseBody.getBytes()));	
//				if(res.getErrorCode() == 0){
//					appContext.Logout();
//					appContext.getUnLoginHandler().sendEmptyMessage(1);
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
        return new ByteArrayInputStream(responseBody.getBytes());
	}
	
	/**
	 * post请求URL
	 * @param url
	 * @param params
	 * @param files
	 * @throws AppException 
	 * @throws IOException 
	 * @throws  
	 */
//	private static Result http_post(AppContext appContext, String url, Map<String, Object> params, Map<String,File> files) throws AppException, IOException {
//        return Result.parse(_post(appContext, url, params, files));  
//	}	
	
	private static HttpClient getHttpClient() {        
        HttpClient httpClient = new HttpClient();
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        // 设置 默认的超时重试处理策略
		httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// 设置 连接超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT_CONNECTION);
		// 设置 读数据超时时间 
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT_SOCKET);
		// 设置 字符集
		httpClient.getParams().setContentCharset(UTF_8);
		return httpClient;
	}	
	
	private static GetMethod getHttpGet(String url, String cookie, String userAgent) {
		GetMethod httpGet = new GetMethod(url);
		// 设置 请求超时时间
		httpGet.getParams().setSoTimeout(TIMEOUT_SOCKET);
		httpGet.setRequestHeader("Host", AbstractService.getRestRequest(FDConst.FD_SERVER_HOST));
		httpGet.setRequestHeader("Connection","Keep-Alive");
		if(cookie!=null) {
			httpGet.setRequestHeader("Cookie", cookie);
		}
		if(userAgent!=null) {
			httpGet.setRequestHeader("User-Agent", userAgent);
		}
		return httpGet;
	}
	
	private static PostMethod getHttpPost(String url, String cookie, String userAgent) {
		PostMethod httpPost = new PostMethod(url);
		// 设置 请求超时时间
		httpPost.getParams().setSoTimeout(TIMEOUT_SOCKET);
		httpPost.setRequestHeader("Host", AbstractService.getRestRequest(FDConst.FD_SERVER_HOST));
		httpPost.setRequestHeader("Connection","Keep-Alive");
		if(cookie!=null) {
			httpPost.setRequestHeader("Cookie", cookie);
		}
		if(userAgent!=null) {
			httpPost.setRequestHeader("User-Agent", userAgent);
		}
		
		return httpPost;
	}
}
