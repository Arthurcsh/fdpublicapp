package com.wondersgroup.fdpublicapp.common.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;

/**
 * @author chengshaohua  解析返回消息
 *
 */
public class FDResultParser extends FDBaseParser {
	
    private String dataKey;           // 服务接口格式与一期不同
	private String resultStr;
	private JSONObject rootObject;
	private ObjectMapper objectMapper;
	private FDBaseResult baseResult;
	
	public FDResultParser(Context context, String result) {
		this(context,result,FD_KEY_BODY);
	}
	
	public FDResultParser(Context context, String result,String key) {
		this.context = context;
		this.resultStr = result;
		this.dataKey = key;
		this.objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		initBaseResult();
	}
	
	/**
	 * 初始化返回信息
	 */
	public void initBaseResult() {
		if(baseResult==null) {
			try {
				this.rootObject = new JSONObject(resultStr);
				this.baseResult = new FDBaseResult(rootObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *   返回数据类型结果集
	 * @param clazz   封装解析的类型
	 * @return  返回数据类型
	 */
	public <E> E getResultData(Class<E> clazz) throws FDParseException { 
		return getResultData(rootObject, clazz);
	}
	
	/**
	 *   解析数据类型结果集
	 * @param jsonObject   待解析的JSON信息
	 * @param clazz        解析类型
	 * @return             返回数据对象
	 * @throws FDParseException
	 */
	public <E> E getResultData(JSONObject jsonObject, Class<E> clazz) throws FDParseException { 
		if(jsonObject==null) return null;
		
		E entity = null;
		try {
			if (jsonObject.has(dataKey)) {
				JSONObject jsonBody = jsonObject.optJSONObject(dataKey);
				if (!StringUtils.isEmpty(jsonBody)) {
					entity = clazz.newInstance();
					objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
					entity = (E) objectMapper.readValue(jsonBody.toString(),clazz);
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new FDParseException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new FDParseException(e.getMessage());
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new FDParseException(e.getMessage());
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new FDParseException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new FDParseException(e.getMessage());
		}
		
		return entity;
	}
	
	/**
	 *  解析分页信息
	 * @return
	 * @throws FDParseException
	 */
	public <E> FDResultPage<E> getBaseResultPage() throws FDParseException {
		if (rootObject == null) return null;
		
		FDResultPage<E> resultPage = new FDResultPage<E>();
		try {
			JSONObject bodyObject = rootObject.optJSONObject(dataKey);
			if(bodyObject==null) return null;
			if(!StringUtils.jsonObjectHasValue(bodyObject, FD_KEY_PAGE_TOTAL)
					|| !StringUtils.jsonObjectHasValue(bodyObject, FD_KEY_PAGE_SIZE)
					|| !StringUtils.jsonObjectHasValue(bodyObject, FD_KEY_PAGE_COUNT)
					|| !StringUtils.jsonObjectHasValue(bodyObject, FD_KEY_PAGE_INDEX)) return null;
			resultPage.setTotalRecord(bodyObject.getLong(FD_KEY_PAGE_TOTAL));
			resultPage.setCurrPageNo(bodyObject.getInt(FD_KEY_PAGE_INDEX));
			resultPage.setPageCount(bodyObject.getInt(FD_KEY_PAGE_COUNT));
			resultPage.setPageSize(bodyObject.getInt(FD_KEY_PAGE_SIZE));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultPage;
	}
	
	/**
	 *   返回分页结果集
	 * @param clazz          对象类型
	 * @return
	 * @throws FDParseException    异常信息
	 */
	public <E> FDResultPage<E> getResultPageWithData(Class<E> clazz) throws FDParseException {
		if(clazz==null) return null;
		
		FDResultPage<E> baseResultPage = getBaseResultPage();
		if(baseResultPage==null) {
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		}
		if(rootObject==null) return null;
		try {
			JSONObject bodyObject = rootObject.getJSONObject(dataKey);
			if(bodyObject!=null && StringUtils.jsonObjectHasValue(bodyObject, FD_KEY_RESULT_SET)) {
				JSONArray bodyList = bodyObject.getJSONArray(FD_KEY_RESULT_SET);
				if(bodyList!=null) {
					List<E> resultList = new ArrayList<E>();
					for(int i=0;i<bodyList.length();i++) {
						Object bodyItem = bodyList.get(i);
						if(bodyItem==null) continue;
						E entity = clazz.newInstance();
						entity = objectMapper.readValue(""+bodyItem, clazz);
						if(entity!=null) {
							resultList.add(entity);
						}
					}
					baseResultPage.setResultList(resultList);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			showParseExceptionToast();
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		} catch (InstantiationException e) {
			e.printStackTrace();
			showParseExceptionToast();
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			showParseExceptionToast();
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		} catch (JsonParseException e) {
			e.printStackTrace();
			showParseExceptionToast();
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			showParseExceptionToast();
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			showParseExceptionToast();
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		}
		return baseResultPage;
	}
	
	/**
	 *  返回Map结果集  (未完成)
	 * @param clazz
	 * @return
	 * @throws FDParseException
	 */
	public <E> LinkedHashMap<String,?> getResultMapData(Class<E> clazz) throws FDParseException { 
		if(clazz==null) return null;
		
		LinkedHashMap<String, ?> entity = null;
		try {
			JSONObject bodyObject = rootObject.getJSONObject(dataKey);
			if(bodyObject!=null && StringUtils.jsonObjectHasValue(bodyObject, FD_KEY_RESULT_SET)) {
				objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				entity = objectMapper.readValue(bodyObject.toString(), LinkedHashMap.class);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	/**
	 * 返回全部根信息
	 * @return
	 * @throws FDJSONParseException
	 */
	public JSONObject getRootJSONObject() throws FDParseException {
		if (rootObject == null) {
			showParseExceptionToast();
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		}
		return rootObject;
	}
	
	/**
	 *  获取内容Body信息
	 * @return
	 * @throws FDParseException
	 */
	public String getResultDataWithString() throws FDParseException {
		if(rootObject==null) return null;
		try {
			return rootObject.getString(dataKey);
		} catch (JSONException e) {
			e.printStackTrace();
			showParseExceptionToast();
			throw new FDParseException(FD_KEY_PARSER_ERROR);
		}
	}
	
	public FDBaseResult getBaseResult() {
		return baseResult;
	}
}
