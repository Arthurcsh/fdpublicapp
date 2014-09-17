package com.wondersgroup.fdpublicapp.common.protocol;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author chengshaohua
 * 返回结果构造
 */
public class FDBaseResult {
	
	public static final int FD_STATUS_SUCCESS = 0;
	public static final int FD_STATUS_FAILURE = 1;
	private int status;
	private String messageCode;
	private String message;
	
	public FDBaseResult(JSONObject jsonObject) {
		if(jsonObject==null) return;
		
		try {
			initBaseResult(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 初始化返回信息
	 * @param jsonObject
	 * @throws JSONException
	 */
	public void initBaseResult(JSONObject jsonObject) throws JSONException {
		status = jsonObject.optInt("status");
		messageCode = jsonObject.optString("messageCode");
		message = jsonObject.optString("message");
	}
	
}
