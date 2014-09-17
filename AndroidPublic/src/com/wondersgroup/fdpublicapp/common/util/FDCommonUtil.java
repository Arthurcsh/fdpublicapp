package com.wondersgroup.fdpublicapp.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chengshaohua
 *
 */
public class FDCommonUtil {

	private static SimpleDateFormat pullDateFormat = new SimpleDateFormat("MM-dd HH:mm");
	
	public static String formatDateTime(long time) {
		if (0 == time) {
			return "";
		}
		return pullDateFormat.format(new Date(time));
	}
	
}
