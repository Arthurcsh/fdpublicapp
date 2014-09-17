package com.wondersgroup.fdpublicapp.common.protocol;

import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;

import android.content.Context;

/**
 * @author chengshaohua
 *
 */
public class FDBaseParser {

	public static final String FD_KEY_BODY                 = "body";
	public static final String FD_KEY_DATA                 = "data";
	public static final String FD_KEY_RESULT_SET           = "resultList";
	public static final String FD_KEY_PARSER_ERROR         = "parser data error";
	public static final String FD_KEY_PAGE_TOTAL           = "totalRecord";
	public static final String FD_KEY_PAGE_SIZE            = "pageSize";
	public static final String FD_KEY_PAGE_INDEX           = "currPageNo";
	public static final String FD_KEY_PAGE_COUNT           = "pageCount";
	
	public Context context;
	
	/**
	 * 显示数据解析出错的Toast
	 */
	protected void showParseExceptionToast() {
		FDViewUtil.showToast(context, "数据解析出错");
	}
}
