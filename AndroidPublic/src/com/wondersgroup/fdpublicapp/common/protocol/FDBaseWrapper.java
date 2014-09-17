package com.wondersgroup.fdpublicapp.common.protocol;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.wondersgroup.fdpublicapp.common.custom.FDCustomProgressDialog;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDCategory;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;

import android.content.Context;
import android.os.Handler;
import android.os.Message;


/**
 *  与服务通讯封装类
 * @author chengshaohua
 *
 */
public class FDBaseWrapper {
	protected Context context;
	protected FDAppContext application;
	protected AsyncHttpClient asyncHttpClient;
	protected FDCustomProgressDialog progressDialog;
	protected Handler baseHandler;
	
	public FDBaseWrapper(Context context) {
		this.context = context;
		
		initBaseWrapper();
		verifyUserLogin();
	}
	
	public FDBaseWrapper(Context context, boolean isUser) {
		this.context = context;
		
		initBaseWrapper();
	}
	
	public void initBaseWrapper() {
		this.asyncHttpClient = new AsyncHttpClient();
		this.asyncHttpClient.setTimeout(60000);
		this.progressDialog = FDCustomProgressDialog.createDialog(context);
	}
	
	/**
	 *  验证用户登录
	 */
	public void verifyUserLogin() {
		if(context==null) return;
		
		application = (FDAppContext) context.getApplicationContext();
		if(application!=null) {
			FDUser user = application.getLoginInfo();
			System.out.println("Login verify  ------------------------------- "+user.getUsername());
			if(user.getUsername()!=null) {
				asyncHttpClient.addHeader("token", user.getUsername());
				System.out.println("Login verify  ########################################### "+user.getUsername());
			}else {  
				Message message = new Message();
				message.what = 1;
				application.getUnLoginHandler().sendMessage(message);
			}
		}
	}
	
	/**
	 *  转换Mutilpart参数
	 * @param mapValue    待转换的Map
	 * @return
	 */
	public String getMutilpartObject(Map<String, Object> mapValue) {
		if(mapValue==null) return null;
		
		Charset utf8 = Charset.forName("UTF-8");
		MediaType mediaType = new MediaType("application", "json", utf8);
		HttpHeaders jsonHeaders = new HttpHeaders();
		jsonHeaders.setContentType(mediaType);
		
		MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
		for(Map.Entry<String, Object> entry:mapValue.entrySet()) {
			if(entry==null) continue;
			HttpEntity<Object> jsonEntity = new HttpEntity<Object>(entry.getValue(), jsonHeaders);
			formData.add(entry.getKey(), jsonEntity);
		}
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, new HttpHeaders());
		
		return requestEntity.toString();
	}
	
	/**
	 * 显示加载进度对话框
	 * @param msg
	 */
	public void showProgressDialog(final String msg) {
		baseHandler = new Handler();
		baseHandler.post(new Runnable(){
			public void run() {
				progressDialog.setMessage(msg);
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.show();
			}
		});
	}
	
	/**
	 * 关闭加载进度对话框
	 */
	public void dismissProgressDialog() {
		baseHandler.post(new Runnable(){
			public void run() {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
			}
		});
	}
	
	/**
	 *  解析类别(key,value)
	 * @param category
	 * @return   返回类别数组集
	 */
	public ArrayList<FDCategory> getArrayCategory(String categoryString) { 
		if(categoryString==null) return null;
		
		ArrayList<FDCategory> categoryList = new ArrayList<FDCategory>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<String, Object> categorymaps = objectMapper.readValue(categoryString, LinkedHashMap.class);
			if(categorymaps==null) return categoryList;
			
			for (Map.Entry<String, Object> entry : categorymaps.entrySet()) {
				if(entry==null) continue;
				FDCategory category = new FDCategory();
				category.setCode(entry.getKey());
				if(entry.getValue() instanceof String) {
					category.setName(""+entry.getValue());
				}else if(entry.getValue() instanceof LinkedHashMap){
					LinkedHashMap<String,String> categoryValue = (LinkedHashMap<String,String>) entry.getValue();
					if(categoryValue.containsKey("name")) {
						category.setName(categoryValue.get("name"));
					}
				}
				categoryList.add(category);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return categoryList;
	}
	
	/**
	 * 转换提交实例对象-(MutilpPart)
	 * @param parameter
	 * @param mutilpObject
	 * @return
	 */
	public LinkedHashMap<String,String> getBaseObjectMap(String parameter,Object mutilpObject) {
		if(StringUtils.isEmpty(mutilpObject)) return null;
		
		LinkedHashMap<String,String> objectMap = new LinkedHashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String userString = objectMapper.writeValueAsString(mutilpObject);
			objectMap.put("dto", userString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return objectMap;
	}
	
	/**
	 * 转换提交上传的图片集
	 * @param images
	 * @return
	 */
	public Map<String,String> getBaseImageMap(List<FDImage> images) {
		if(images==null) return null;
		
		Map<String,String> imageMap = new LinkedHashMap<String,String>();
		for(int i=0;i<images.size();i++) {
			FDImage image = images.get(i);
			if(image==null) continue;
			imageMap.put("file_"+i, image.getFilePath());
		}
		return imageMap;
	}
	
	public static Comparator<FDCategory> comparator = new Comparator<FDCategory>(){   
		public int compare(FDCategory category1, FDCategory category2) {    
			//按编号升序   
			return Integer.parseInt(category1.getCode())-Integer.parseInt(category2.getCode());    
		}
	};
	
	/** 商圈排序 **/
	public static Comparator<FDCommerialCenter> cn_commerial_comparator = new Comparator<FDCommerialCenter>(){   
		Collator cmp = Collator.getInstance(java.util.Locale.CHINA);  
		public int compare(FDCommerialCenter commerial1, FDCommerialCenter commerial2) {    
			if (cmp.compare(commerial1.getCommerialName(), commerial2.getCommerialName()) > 0) {
				return 1;
			} else if (cmp.compare(commerial1.getCommerialName(), commerial2.getCommerialName()) < 0) {
				return -1;
			}
			return 0;
		}
	};
	
	/** 菜肴类型 **/
	public static Comparator<FDSuperMode> cn_vegetable_comparator = new Comparator<FDSuperMode>(){   
		Collator cmp = Collator.getInstance(java.util.Locale.CHINA);  
		public int compare(FDSuperMode superMode1, FDSuperMode superMode2) {    
			//按编字母顺序排列   
			if (cmp.compare(superMode1.getName(), superMode2.getName()) > 0) {
				return 1;
			} else if (cmp.compare(superMode1.getName(), superMode2.getName()) < 0) {
				return -1;
			}
			return 0;
		}
	};
	
	/** 菜系类型 **/
	public static Comparator<FDCuisine> cn_cuisine_comparator = new Comparator<FDCuisine>(){   
		Collator cmp = Collator.getInstance(java.util.Locale.CHINA);  
		public int compare(FDCuisine cuisine1, FDCuisine cuisine2) {    
			//按编字母顺序排列   
			if (cmp.compare(cuisine1.getCuisineValue(), cuisine2.getCuisineValue()) > 0) {
				return 1;
			} else if (cmp.compare(cuisine1.getCuisineValue(), cuisine2.getCuisineValue()) < 0) {
				return -1;
			}
			return 0;
		}
	};
}
