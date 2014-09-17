package com.wondersgroup.fdpublicapp.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

/** 
 * 字符串操作工具包
 * @author chengshaohua
 * @created 2014-2-12
 */
public class StringUtils 
{
	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private final static Pattern telphone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	private final static Pattern password = Pattern.compile("^[a-zA-Z0-9]{5,11}$");
	private final static Pattern date = Pattern.compile("[\\d]{4}[-][\\d]{2}[-][\\d]{2}");
	
	//private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
	
	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	public static String dateToString(Date date) {
		if(date==null) return null;
		return dateFormater.get().format(date);
	}
	public static String dateToString2(Date date) {
		if(date==null) return null;
		return dateFormater2.get().format(date);
	}
	
	/**
	 * 将字符串转位日期类型
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date toDate2(String sdate) {
		try {
			return dateFormater2.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 以友好的方式显示时间
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if(time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();
		
		//判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if(curDate.equals(paramDate)){
			int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
			if(hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
			else 
				ftime = hour+"小时前";
			return ftime;
		}
		
		long lt = time.getTime()/86400000;
		long ct = cal.getTimeInMillis()/86400000;
		int days = (int)(ct - lt);		
		if(days == 0){
			int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
			if(hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
			else 
				ftime = hour+"小时前";
		}
		else if(days == 1){
			ftime = "昨天";
		}
		else if(days == 2){
			ftime = "前天";
		}
		else if(days > 2 && days <= 10){ 
			ftime = days+"天前";			
		}
		else if(days > 10){			
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}
	
	/**
	 * 判断给定字符串时间是否为今日
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate){
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if(time != null){
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if(nowDate.equals(timeDate)){
				b = true;
			}
		}
		return b;
	}
	
	/**
	 * 判断给定字符串是否空白串。
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(Object input ) {
		if ( input == null || "".equals( input ) || "null".equals(input))
			return true;
		
		if(input instanceof String) {
			String inputStr = (String) input;
			for ( int i = 0; i < inputStr.length(); i++ ) {
				char c = inputStr.charAt( i );
				if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' ) {
					return false;
				}
			}
		}
		
		return false;
	}

	public static boolean isDate(String dateString) {
		if(dateString==null || dateString.trim().length()==0) {
			return false;
		}
		return date.matcher(dateString).matches();
	}
	
	/**
	 * 判断是不是一个合法的电子邮件地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		if(email == null || email.trim().length()==0) 
			return false;
	    return emailer.matcher(email).matches();
	}
	
	/**
	 *  判断合法的手机号
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if(phone==null || phone.trim().length()==0) return false;
		return telphone.matcher(phone).matches();
	}
	
	public static boolean isPassword(String pass) {
		if(pass==null || pass.trim().length()==0) return false;
		
		return password.matcher(pass).matches();
	}
	
	/**
	 * 字符串转整数
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try{
			return Integer.parseInt(str);
		}catch(Exception e){}
		return defValue;
	}
	/**
	 * 对象转整数
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if(obj==null) return 0;
		return toInt(obj.toString(),0);
	}
	/**
	 * 对象转整数
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try{
			return Long.parseLong(obj);
		}catch(Exception e){}
		return 0;
	}
	
	public static String toString(double dou, int length) {
		BigDecimal bd = new BigDecimal(dou);
		BigDecimal result = bd.setScale(length, RoundingMode.DOWN);
		return result.toString();
	}
	
	/**
	 * 字符串转布尔值
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try{
			return Boolean.parseBoolean(b);
		}catch(Exception e){}
		return false;
	}
	
	public static String getBool(boolean b) {
		return Boolean.toString(b);
	}
	
	/**
	 *  判断JSON是否有Key元素
	 * @param object     JSON对象
	 * @param key        元素
	 * @return           布尔值
	 */
	public static boolean jsonObjectHasValue(JSONObject object, String key) {
		try {
			if (object.has(key) && !object.isNull(key)
					&& object.get(key) != null
					&& !object.getString(key).equals("null")
					&& !object.get(key).equals("")
					&& !object.getString(key).equals("[]"))
				return true;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 判断是否数值型
	 * @param numString
	 * @return
	 */
	public static boolean isNumber(String numString) {
		String regex = "^[1-9][0-9]*\\.[0-9]+$|^[1-9][0-9]*$|^0+\\.[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		char c = numString.charAt(0);
		if (c == '+' || c == '-') {
			numString = numString.substring(1);
		}
		Matcher matcher = pattern.matcher(numString);
		return matcher.matches();
	}

}
