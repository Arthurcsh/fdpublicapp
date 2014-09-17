package com.wondersgroup.fdpublicapp.common.service;

public class FDConst {

	public static final String FD_SERVER_HOST                               = "fd.server.host";    
	public static final String FD_SERVER_PORT                               = "fd.server.port";
	public static final String FD_SERVER_NAME                               = "fd.server.name";
	public static final String FD_SERVER_URL                                = "fd.server.url";
	public static final String FD_SERVER_LOCATION_POINT                     = "fd.server.location.point";
	public static final String FD_SERVER_VERSION_UPDATE                     = "fd.server.version.update";
	
	public static final String FD_INDEX_DATA_ALL                            = "fd.server.commercial.data.all";
	public static final String FD_INDEX_CIRCLE_TREE                         = "fd.server.commercial.circle.tree";
	public static final String FD_INDEX_CUISINE_TYPE                        = "fd.server.index.cuisine.type";
	public static final String FD_INDEX_ATMOSPHERE_LIST                     = "fd.server.index.atmosphere.list";
	public static final String FD_INDEX_CREDIT_LIST                         = "fd.server.index.credit.list";
	public static final String FD_INDEX_FOOD_TYPE                           = "fd.server.index.food.type";
	public static final String FD_INDEX_QUERY_BY_CONDITION                  = "fd.server.index.query.by.condition";
	public static final String FD_QUERY_RESTAURANT_ATTACHMENT               = "fd.server.query.restaurant.attachment";
	public static final String FD_QUERY_RESTAURANT_CUISINE                  = "fd.server.query.restaurant.cuisine";
	public static final String FD_QUERY_RESTAURANT_DETAIL                   = "fd.server.query.restaurant.detail";
	public static final String FD_QUERY_RESTAURANT_NEARBY                   = "fd.server.query.restaurant.nearby";           // 搜索附近餐厅信息
	public static final String FD_QUERY_RESTAURANT_CUISINE_PICLIST          = "fd.server.query.restaurant.cuisine.piclist";  
	public static final String FD_QUERY_RESTAURANT_CUISINE_TRACE            = "fd.server.query.restaurant.cuisine.trace";    // 食材追溯  
	public static final String FD_QUERY_RESTAURANT_CUISINE_DETAIL           = "fd.server.query.restaurant.cuisine.detail"; 
	public static final String FD_QUERY_CUISINE_COMMENT                     = "fd.server.query.cuisine.comment";
	public static final String FD_EXECUTE_DISH_RECOMMEND                    = "fd.server.execute.recommend.dish"; 
	public static final String FD_EXECUTE_DISH_FAVORITE                     = "fd.server.execute.favorite.dish"; 
	public static final String FD_EXECUTE_RESTAURANT_RECOMMEND              = "fd.server.execute.restaurant.recommend"; 
	public static final String FD_EXECUTE_RESTAURANT_FAVORITE               = "fd.server.execute.restaurant.favorite"; 
	public static final String FD_EXECUTE_RESTAURANT_COMMENT                = "fd.server.execute.restaurant.comment"; 
	public static final String FD_EXECUTE_DISH_COMMENT                      = "fd.server.execute.dish.comment";           // 菜肴点评
	public static final String FD_EXECUTE_COMMON_COMMENT_REPLY              = "fd.server.execute.common.comment.reply";   // 点评回复
	public static final String FD_EXECUTE_CREATE_MESSAGE                    = "fd.server.execute.common.create.message";   // 创建消息
	
	public static final String FD_QUERY_RESTAURANT_EXTRA_KEY                = "fd.server.query.restaurant.extra.key";
	public static final String FD_QUERY_COUPON_EXTRA_KEY                    = "fd.server.query.coupon.extra.key";
	public static final String FD_QUERY_CUISINE_EXTRA_KEY                   = "fd.server.query.cuisine.extra.key";
	
	// 餐厅搜索条件Key
	public final static String FD_QUERY_DETAIL_TYPE_ID                      = "quer.detail.type.id";                      // 搜索餐厅类型   0全城、1附近
	public final static String FD_QUERY_CONDITION_BUNDLE_KEY                = "query.condition.bundle.key";
	public final static String FD_QUERY_CONDITION_KEYNAME                   = "keyword";
	public final static String FD_QUERY_CONDITION_AREA                      = "bizDistrict";
	public final static String FD_QUERY_CONDITION_CREDIT                    = "creditLevel";
	public final static String FD_QUERY_CONDITION_CUISINE                   = "cuisineType";
	public final static String FD_QUERY_CONDITION_ATMOSPHERE                = "restAtmosphere";
	public final static String FD_QUERY_CONDITION_LONGITUDE                 = "longitude";             // 经度
	public final static String FD_QUERY_CONDITION_LATITUDE                  = "latitude";              // 纬度		
	public final static String FD_QUERY_CONDITION_DISTANCE                  = "distance";
	public final static String FD_QUERY_CONDITION_AVERAGE                   = "averageComsumption";    // 平均消费
	
	public final static String FD_QUERY_CONDITION_ORDER                     = "sortby";                // 排序
	public final static String FD_QUERY_CONDITION_ORDER_DISTANCE            = "distance";              // 按距离最近
	public final static String FD_QUERY_CONDITION_ORDER_AVERAGE             = "averageComsumption";    // 按人均消费
	public final static String FD_QUERY_CONDITION_ORDER_CREDIT              = "foodSaftyRating";       // 按信用等级
	public final static String FD_QUERY_CONDITION_ORDER_DIRECTION           = "sortDirection";   
	public final static String FD_QUERY_CONDITION_ORDER_DESC                = "desc";                  // 降序
	public final static String FD_QUERY_CONDITION_ORDER_ASC                 = "asc";                   // 升序
	
	// 餐厅基本信息O
	public static final String FD_MAIN_HASHMAP_COMMERIAL_CENTER             = "fd.server.main.hashmap.commerial.center";
	public static final String FD_MAIN_HASHMAP_ATMOSPHERE_LIST              = "fd.server.main.hashmap.atmosphere.list";
	public static final String FD_MAIN_HASHMAP_CUISINE_TYPE                 = "fd.server.main.hashmap.cuisine.type";
	public static final String FD_MAIN_HASHMAP_CREDIT_LIST                  = "fd.server.main.hashmap.credit.list";
	public static final String FD_MAIN_HASHMAP_VEGETABLE_TYPE               = "fd.server.main.hashmap.vegetable.type";
	public static final String FD_MAIN_HASHMAP_AVERAGE_CONSUM               = "fd.server.main.hashmap.average.consum";
	
	public static final String FD_COMPANY_INFORMATION_UPLOAD_IMAGE          = "fd.server.company.information.upload.image";
	public static final String FD_COMPANY_INFORMATION_DELETE_IMAGE          = "fd.server.company.information.delete.image";
	public static final String FD_COMPANY_INFORMATION_LECENCE_LIST          = "fd.server.company.information.lecence.list";
	public static final String FD_COMPANY_INFORMATION_DISH_TYPE_LIST        = "fd.server.company.information.get.dish.typelist";
	public static final String FD_COMPANY_INFORMATION_FEATURE_LIST          = "fd.server.company.information.get.featurelist";
	public static final String FD_COMPANY_INFORMATION_SETTING_LIST          = "fd.server.company.information.get.settinglist";
	public static final String FD_COMPANY_INFORMATION_AVERAGE_CONSUMPTION   = "fd.server.company.information.get.averageconsumption";
	public static final String FD_COMPANY_INFORMATION_SEATS_LIST            = "fd.server.company.information.get.seats.list";
	public static final String FD_COMPANY_INFORMATION_UPDATE_ATTACHMENT     = "fd.server.company.information.update.attachment";
	public static final String FD_COMPANY_INFORMATION_COMMERCIALCENTER_LIST = "fd.server.company.information.get.shangquan.list";
	public static final String FD_COMPANY_INFORMATION_UPDATE_INFOR          = "fd.server.company.information.update.infor";	
	
	// 优惠信息
	public static final String FD_COUPON_QUERY_INFO                         = "fd.server.coupon.query.info";                   // 查询优惠信息
	public static final String FD_COUPON_QUERY_DETAIL                       = "fd.server.coupon.query.detail";                 // 查询优惠详情
	public static final String FD_COUPON_QUERY_FAVORITE                     = "fd.server.coupon.query.favorite";               // 收藏的优惠券
	public static final String FD_COUPON_FETCH_FAVORITE                     = "fd.server.coupon.fetch.favorite";               // 收藏优惠券

	// 学习营养餐
	public static final String FD_NUTRITION_DATA_CATEGORY                   = "fd.server.nutrition.data.category";             // 营养餐类型
	public static final String FD_NUTRITION_DATA_SCHOOL                     = "fd.server.nutrition.data.school";               // 营养餐学校
	public static final String FD_NUTRITION_DATA_REGION                     = "fd.server.nutrition.data.region";               // 营养餐区域
	public static final String FD_NUTRITION_DATA_INFO                       = "fd.server.nutrition.data.info";                 // 营养餐区域、类型和餐次等信息
	
	public static final String FD_NUTRITION_QUERY_SCHOOL                    = "fd.server.nutrition.query.school";              // 查询学习
	public static final String FD_NUTRITION_QUERY_BY_MONTH                  = "fd.server.nutrition.query.by.month";            // 学校月内供餐信息
	public static final String FD_NUTRITION_QUERY_STUDENT_MEAL              = "fd.server.nutrition.query.student.meal";        // 查询学生餐信息
	public static final String FD_NUTRITION_QUERY_STUDENT_MEAL_TRACE        = "fd.server.nutrition.query.student.meal.trace";  // 查询学生餐食材追溯信息
	public static final String FD_NUTRITION_RECMMEND_RESTAURANT             = "fd.server.nutrition.recommend.restaurant";      // 推荐餐饮企业
	public static final String FD_NUTRITION_RECOMMEN_CUISINE                = "fd.server.nutrition.recommend.cuisine";         // 推荐菜肴
	public static final String FD_NUTRITION_COLLECT_RESTAURANT              = "fd.server.nutrition.collect.restaurant";        // 收藏餐饮企业 
	public static final String FD_NUTRITION_COMMENT_MEAL                    = "fd.server.nutrition.comment.meal";              // 点评营养菜肴 
	public static final String FD_NUTRITION_QUERY_COMMENT_MEAL              = "fd.server.nutrition.query.comment.meal";        // 获取营养餐的点评
	public static final String FD_NUTRITION_QUERY_COMMENT_REPLY             = "fd.server.nutrition.query.comment.reply";      // 获取营养餐的点评回复
	
	public static final String FD_NUTRITION_COLLECT_SCHOOL                  = "fd.server.nutrition.collect.school";            // 收藏学校
	public static final String FD_NUTRITION_FAVORITE_RESTAURANT             = "fd.server.nutrition.favorite.restaurant";       // 收藏的餐馆
	public static final String FD_NUTRITION_FAVORITE_CUISINE                = "fd.server.nutrition.favorite.cuisine";          // 收藏的菜肴
	public static final String FD_NUTRITION_FAVORITE_NOTE                   = "fd.server.nutrition.favorite.note";             // 收藏的帖子
	public static final String FD_NUTRITION_FAVORITE_NOTE_DETAIL            = "fd.server.nutrition.favorite.note.detail";      // 收藏的帖子详情
	public static final String FD_NUTRITION_FAVORITE_SCHOOL                 = "fd.server.nutrition.favorite.school";           // 收藏的学校
	public static final String FD_NUTRITION_COLLECT_NOTE                    = "fd.server.nutrition.collect.note";              // 收藏帖子
	
	public static final String FD_NUTRITION_FAVORITE_NOTE_COMMENT           = "fd.server.nutrition.favorite.note.comment";     // 收藏的帖子评论
	
	// 食品安全信息
	public static final String FD_HOME_SAFETY_ADVERT_PAGE                   = "fd.server.home.safety.advert.page";             // 食品安全广告页
	 
    public static final String FD_SAFETY_QUERY_INFO                         = "fd.server.safety.query.info";                   // 食品安全
    public static final String FD_SAFETY_QUERY_DETAIL                       = "fd.server.safety.query.detail";                 // 食品安全详情
		
	// 个人中心
	public static final String FD_PERSONAL_USER_LOGIN                       = "fd.server.personal.user.login";                 // 用户登录
	public static final String FD_PERSONAL_USER_REGISTER                    = "fd.server.personal.user.register";              // 用户注册
	public static final String FD_PERSONAL_USER_COMPLETE                    = "fd.server.personal.user.complete";              // 用户完善
	public static final String FD_PERSONAL_USER_MODIFY                      = "fd.server.personal.user.modify";                // 用户修改
	public static final String FD_PERSONAL_USER_LOGOUT                      = "fd.server.personal.user.logout";                // 用户退出
	
	public static final String FD_PERSONAL_USER_INFORMATION                 = "fd.server.personal.user.information";           // 用户综合信息
	// 我的消息
	public static final String FD_PERSONAL_MESSAGE_COMMENT_REPLY            = "fd.server.personal.message.comment.reply";      // 评论回复
	public static final String FD_PERSONAL_MESSAGE_SYSTEM_MESSAGE           = "fd.server.personal.message.system.message";     // 系统消息
	public static final String FD_PERSONAL_MESSAGE_LEAVE_REPLY              = "fd.server.personal.message.leave.reply";        // 留言回复
	// 我的点评
	public static final String FD_PERSONAL_MY_COMMENT_RESTAURANT            = "fd.server.personal.my.comment.restaurant";      // 我评价的餐馆
	public static final String FD_PERSONAL_MY_COMMENT_CUISINE               = "fd.server.personal.my.comment.cuisine";         // 我评价的菜肴
	public static final String FD_PERSONAL_MY_COMMENT_NOTE                  = "fd.server.personal.my.comment.note";            // 我评价的帖子
	// 我的推荐
	public static final String FD_PERSONAL_MY_RECOMMEND_RESTAURANT          = "fd.server.personal.my.recommend.restaurant";    // 我推荐的餐馆
	public static final String FD_PERSONAL_MY_RECOMMEND_CUISINE             = "fd.server.personal.my.recommend.cuisine";       // 我推荐的菜肴
	// 我的留言
	public static final String FD_PERSONAL_MY_LEAVE_MESSAGE                 = "fd.server.personal.my.leave.message";           // 我的留言
	
	public static final String FD_PERSONAL_VERSION_UPGRADE                  = "fd.server.personal.version.upgrade";            // 版本更新
	
	//企业
	public static final String FD_COMPANY_INFO                              = "fd.server.company.info";
	public static final String FD_COMPANY_INFO_ID                           = "fd.server.company.info.id";
	
	
	public static final String UNLIMITED_CONDITION_KEY                      = "unlimited.condition.key";
	public static final String UNLIMITED_CONDITION_VALUE                    = "不限";
	public static final String UNLIMITED_CONDITION_SORT_KEY                 = "unlimited.condition.sort.key";

}
