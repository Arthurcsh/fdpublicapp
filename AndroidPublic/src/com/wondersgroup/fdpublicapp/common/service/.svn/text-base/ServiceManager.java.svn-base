package com.wondersgroup.fdpublicapp.common.service;

import java.util.Map;


/**
 * 资源管理
 * @author chengshaohua
 */

public class ServiceManager {

	 static {
//		 http://101.231.159.180   10.1.63.126:8080
		 
		 putServer("http://101.231.159.181:80","fdAppPublic");    // 公网测试
//		 putServer("http://10.1.63.126:8080","fdAppPublic");	
//		 putServer("http://www.365foodsafety.com","spjg");	 
//		 putServer("http://101.231.159.180:80","fdAppPublic");	
		 
		 getServerHash().put(FDConst.FD_SERVER_PORT, 80);
//		 putServer("http://10.10.2.232:4000","fdAppPublic");	
	 }
	    
	public static void putServer(String host, String server) {
		String hostURL = host + "/" + server;
		putValue(FDConst.FD_SERVER_HOST, host);
		putValue(FDConst.FD_SERVER_NAME, server);
		putValue(FDConst.FD_SERVER_URL, hostURL);
		putValue(FDConst.FD_SERVER_VERSION_UPDATE, hostURL+"/version");

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
		putValue(FDConst.FD_QUERY_RESTAURANT_CUISINE_PICLIST, hostURL + "/company/queryRestaurantCuisineAllInfo/");            // 餐馆企业菜单
		putValue(FDConst.FD_QUERY_RESTAURANT_CUISINE_TRACE, hostURL + "/stumeal/getTracedOutputMaterial");                     // 餐馆企业菜单食材追溯
		putValue(FDConst.FD_QUERY_RESTAURANT_CUISINE_DETAIL, hostURL + "/company/getCuisineId/");                              // 查询菜肴详情
		putValue(FDConst.FD_QUERY_CUISINE_COMMENT, hostURL + "/social/queryCuisineComments/");                                 // 查询菜肴点评
		putValue(FDConst.FD_EXECUTE_DISH_RECOMMEND, hostURL + "/social/recommendCuisine");                                     // 菜肴推荐
		putValue(FDConst.FD_EXECUTE_DISH_FAVORITE, hostURL + "/social/collectCuisine");                                        // 菜肴收藏
		putValue(FDConst.FD_EXECUTE_RESTAURANT_RECOMMEND, hostURL + "/social/recommendRestaurant");                            // 餐厅推荐
		putValue(FDConst.FD_EXECUTE_RESTAURANT_FAVORITE, hostURL + "/social/collectCompany");                                  // 餐厅收藏
		putValue(FDConst.FD_EXECUTE_RESTAURANT_COMMENT, hostURL + "/social/postCommentForRestaurant");                         // 餐厅点评
		putValue(FDConst.FD_EXECUTE_DISH_COMMENT, hostURL + "/social/postCommentForCuisine");                                  // 菜肴点评
		putValue(FDConst.FD_EXECUTE_COMMON_COMMENT_REPLY, hostURL + "/social/postContent");                                    // 点评回复
		putValue(FDConst.FD_EXECUTE_CREATE_MESSAGE, hostURL + "/social/createCommunication");                                  // 商家留言(创建消息)
		
		// 优惠信息
		putValue(FDConst.FD_COUPON_QUERY_INFO, hostURL + "/discount/queryPublicDiscount/");                                    // 优惠信息
		putValue(FDConst.FD_COUPON_QUERY_DETAIL, hostURL + "/discount/getPublicDiscount/");                                    // 优惠详情
		putValue(FDConst.FD_COUPON_QUERY_FAVORITE, hostURL + "/social/queryCollectDiscount/");                                 // 收藏的优惠券
		putValue(FDConst.FD_COUPON_FETCH_FAVORITE, hostURL + "/social/collectPublicDiscount");                                 // 收藏优惠券
		
        // 学习营养餐
		putValue(FDConst.FD_NUTRITION_DATA_INFO, hostURL + "/base/getPublicAppStaticData");                                    // 营养餐相关信息
		putValue(FDConst.FD_NUTRITION_QUERY_SCHOOL, hostURL + "/stumeal/searchSchool/");                                       // 查询营养餐学校
        putValue(FDConst.FD_NUTRITION_QUERY_BY_MONTH, hostURL + "/stumeal/getSchoolStuMealInfosByMonth");
        putValue(FDConst.FD_NUTRITION_QUERY_STUDENT_MEAL, hostURL + "/stumeal/getSchoolStuMealInfo");                          // 查询学生餐信息
        putValue(FDConst.FD_NUTRITION_QUERY_STUDENT_MEAL_TRACE, hostURL + "/stumeal/getStumealTracedOutputMaterial");          // 营养餐食材追溯
        putValue(FDConst.FD_NUTRITION_COMMENT_MEAL, hostURL + "/social/postCommentForStumeal");                                // 评价营养餐
        putValue(FDConst.FD_NUTRITION_QUERY_COMMENT_MEAL, hostURL + "/social/queryStumealComments/");                          // 评价营养餐信息
        putValue(FDConst.FD_NUTRITION_QUERY_COMMENT_REPLY, hostURL + "/social/queryPost/");                                    // 营养餐评论回复
        
        putValue(FDConst.FD_NUTRITION_COLLECT_SCHOOL, hostURL + "/social/collectCompany");                                     // 收藏学校
        // 收藏的学校
        putValue(FDConst.FD_NUTRITION_FAVORITE_RESTAURANT, hostURL + "/social/queryCollectCompany/");                          // 我收藏的学校-餐馆
        putValue(FDConst.FD_NUTRITION_FAVORITE_CUISINE, hostURL + "/social/queryCollectCuisine/");                             // 我收藏的学校-菜肴
        putValue(FDConst.FD_NUTRITION_FAVORITE_NOTE, hostURL + "/social/getCollectPost/");                                     // 我收藏的学校-帖子
        putValue(FDConst.FD_NUTRITION_FAVORITE_NOTE_DETAIL, hostURL + "/social/getCollectPostById/");                          // 我收藏的学校-帖子详情
        putValue(FDConst.FD_NUTRITION_FAVORITE_SCHOOL, hostURL + "/social/queryCollectSchool/");                               // 我收藏的学校-学校
        putValue(FDConst.FD_NUTRITION_COLLECT_NOTE, hostURL + "/social/collectPost");                                          // 收藏帖子
        
        putValue(FDConst.FD_NUTRITION_FAVORITE_NOTE_COMMENT, hostURL + "/social/queryPublicContentComment/");                  // 查询帖子点评
		
        // 食品安全信息 
        putValue(FDConst.FD_HOME_SAFETY_ADVERT_PAGE, hostURL + "/social/queryHomeAd/");                                        // 食品安全广告页
        
        putValue(FDConst.FD_SAFETY_QUERY_INFO, hostURL + "/social/queryFoodSafety/");                                          // 查询食品安全信息 
        putValue(FDConst.FD_SAFETY_QUERY_DETAIL, hostURL + "/social/queryFoodSafetyDetail/");                                  // 查询食品安全信息详情
        
		// 个人信息
		putValue(FDConst.FD_COMPANY_INFO, hostURL + "/company/");
		putValue(FDConst.FD_COMPANY_INFO_ID, hostURL + "/company/findRequestById/");
        putValue(FDConst.FD_PERSONAL_USER_LOGIN, hostURL + "/user/login");
        putValue(FDConst.FD_PERSONAL_USER_REGISTER, hostURL + "/user/register");                                               // 用户注册
        putValue(FDConst.FD_PERSONAL_USER_COMPLETE, hostURL + "/user/edit");                                                   // 编辑用户
        putValue(FDConst.FD_PERSONAL_USER_MODIFY, hostURL + "/user/update");                                                   // 更新用户
        putValue(FDConst.FD_PERSONAL_USER_LOGOUT, hostURL + "/user/logout");                                                   // 用户注销

        putValue(FDConst.FD_PERSONAL_USER_INFORMATION, hostURL + "/social/getUserCenterInfo");                                 // 用户信息
        // 全部消息
        putValue(FDConst.FD_PERSONAL_MESSAGE_COMMENT_REPLY, hostURL + "/social/queryPublicContentCommentReply/");              // 评论回复、帖子点评
        putValue(FDConst.FD_PERSONAL_MESSAGE_SYSTEM_MESSAGE, hostURL + "/social/querySysMsg/");                                // 系统消息
        putValue(FDConst.FD_PERSONAL_MESSAGE_LEAVE_REPLY, hostURL + "/social/querySysMsg/");                                   // 留言回复
        
        // 我的点评
        putValue(FDConst.FD_PERSONAL_MY_COMMENT_RESTAURANT, hostURL + "/social/queryRestComments/");                           // 我点评的餐厅(企业、学校)
        putValue(FDConst.FD_PERSONAL_MY_COMMENT_CUISINE, hostURL + "/social/queryCuisineComments/");                           // 我点评的菜肴
        putValue(FDConst.FD_PERSONAL_MY_COMMENT_NOTE, hostURL + "/social/queryMyCommentedPostWithComment/");                   // 我点评的帖子
        // 我的推荐
        putValue(FDConst.FD_PERSONAL_MY_RECOMMEND_RESTAURANT, hostURL + "/social/queryRestRecommends/");                       // 我推荐的餐厅
        putValue(FDConst.FD_PERSONAL_MY_RECOMMEND_CUISINE, hostURL + "/social/queryCuisineRecommends/");                       // 我推荐的菜肴
        // 我的留言
        putValue(FDConst.FD_PERSONAL_MY_LEAVE_MESSAGE, hostURL + "/social/queryMyComMsgWithReply/");   
        
        putValue(FDConst.FD_PERSONAL_VERSION_UPGRADE, hostURL + "/system/upgrade");                                            // 版本更新
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
	
//	public static final String HOSTURL_PICTURE = "http://10.1.63.118:80/fdWebFile";
	public static final String HOSTURL_PICTURE = "http://101.231.159.181:80/fdWebFile";
}
