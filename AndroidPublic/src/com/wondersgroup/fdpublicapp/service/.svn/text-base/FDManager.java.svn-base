package com.wondersgroup.fdpublicapp.service;

import java.util.Map;


/**
 * 资源管理
 * @author chengshaohua
 */

public class FDManager {

	 static {
//		 http://101.231.159.180   10.1.63.126
		 
		 //putServer("http://101.231.159.180:80","spjg");	
//		 putServer("http://api.365fangxinchi.com","");	
//		 putServer("http://www.365foodsafety.com","spjg");	 
		 
		 putServer("http://10.1.63.126:8080","fdAppPublic");

		 getServerHash().put(FDConst.FD_SERVER_PORT, 80);
	 }
	    
	public static void putServer(String host, String server) {
		String hostURL = host + "/" + server;
		putValue(FDConst.FD_SERVER_HOST, host);
		putValue(FDConst.FD_SERVER_NAME, server);
		putValue(FDConst.FD_SERVER_URL, hostURL);

		// 首页
		putValue(FDConst.FD_INDEX_DATA_ALL, hostURL + "/company/getPublicAppStaticData");                                      // 首页商圈、菜系、氛围和信息等级查询
		putValue(FDConst.FD_INDEX_CIRCLE_TREE, hostURL + "/company/getCommercialCenterTree");                                  // 获取商圈信息
		putValue(FDConst.FD_INDEX_CUISINE_TYPE, hostURL + "/company/getCuisineTypetList");                                     // 菜系列表
		putValue(FDConst.FD_INDEX_ATMOSPHERE_LIST, hostURL + "/company/getRestaurantCuisineSettingList");                      // 氛围列表
		putValue(FDConst.FD_INDEX_CREDIT_LIST, hostURL + "/company/getFoodSafeRatingList");                                    // 信用等级
		putValue(FDConst.FD_INDEX_QUERY_BY_CONDITION, hostURL + "/company/queryRestaurant/");                                  // 餐台综合查询
		putValue(FDConst.FD_INDEX_FOOD_TYPE, hostURL + "/company/getFoodTypeList");                                            // 菜式类型
		putValue(FDConst.FD_QUERY_RESTAURANT_ATTACHMENT, hostURL + "/company/getRestaurantAttachment/");                       // 餐厅附件查询
		putValue(FDConst.FD_QUERY_RESTAURANT_CUISINE, hostURL + "/company/queryRestaurantCuisine/");                           // 获取单位下菜肴
		putValue(FDConst.FD_QUERY_RESTAURANT_DETAIL, hostURL + "/company/getRestaurant/");                                     // 查询餐台详情
		putValue(FDConst.FD_QUERY_RESTAURANT_NEARBY, hostURL + "/company/queryCompanyByLocation/");                            // 查询附近餐台信息
		putValue(FDConst.FD_COMPANY_INFORMATION_AVERAGE_CONSUMPTION, hostURL + "/company/getAverageConsumptionList");
		putValue(FDConst.FD_QUERY_RESTAURANT_CUISINE_PICLIST, hostURL + "/company/getRestaurantCuisinesByRestaurantId/");
		


		// 个人信息
		putValue(FDConst.FD_COMPANY_INFO, hostURL + "/company/");
		putValue(FDConst.FD_COMPANY_INFO_ID, hostURL + "/company/findRequestById/");

		

	}
	

	    
	    
	public static Object get(String key) {
		return AbstractManager.get(key);
	}

	public static String getString(String key) {
		return AbstractManager.getString(key);
	}

	public static int getInt(String key) {
		return AbstractManager.getInt(key);
	}

	public static long getLong(String key) {
		return AbstractManager.getLong(key);
	}

	public static float getFloat(String key) {
		return AbstractManager.getFloat(key);
	}

	public static void putStyleValue(String key, int value) {
		AbstractManager.putStyleValue(key, new Integer(value));
	}

	public static void putStyleValue(String key, long value) {
		AbstractManager.putStyleValue(key,value);
	}

	public static void putStringValue(String key, Object value) {
		AbstractManager.putValue(key, value);
	}

	public static void putValue(String key, Object value) {
		AbstractManager.putStringValue(key, value);
	}

	private static Object get(String key, Class<?> clazz) {
		return AbstractManager.get(key, clazz);
	}

	public static Map<String, Object> getServerHash() {
		return AbstractManager.getSuperManager();
	}
	
	public static String getServerBase() {
		return getString(FDConst.FD_SERVER_URL);
	}
	
}
