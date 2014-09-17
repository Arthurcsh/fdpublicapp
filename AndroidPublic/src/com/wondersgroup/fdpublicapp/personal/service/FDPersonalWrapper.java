package com.wondersgroup.fdpublicapp.personal.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.Header;
import android.content.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.protocol.FDAppContext;
import com.wondersgroup.fdpublicapp.common.protocol.FDBaseWrapper;
import com.wondersgroup.fdpublicapp.common.protocol.FDParseException;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.service.AbstractService;
import com.wondersgroup.fdpublicapp.common.service.FDCallbackListenerAdapter;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.FDRestClient;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.personal.mode.FDCommentNote;
import com.wondersgroup.fdpublicapp.personal.mode.FDCommentReply;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineComment;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineDetail;
import com.wondersgroup.fdpublicapp.personal.mode.FDCuisineRecommend;
import com.wondersgroup.fdpublicapp.personal.mode.FDLeaveMessage;
import com.wondersgroup.fdpublicapp.personal.mode.FDMessage;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantComment;
import com.wondersgroup.fdpublicapp.personal.mode.FDRestaurantRecommend;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.personal.mode.FDUserInfo;
import com.wondersgroup.fdpublicapp.personal.mode.FDVersion;

/**
 *  用户个人中心数据包
 * @author chengshaohua
 *
 */

public class FDPersonalWrapper extends FDBaseWrapper {

	public FDPersonalWrapper(Context context) {
		super(context);
	}

	public FDPersonalWrapper(Context context,boolean isLogin) {
		super(context, isLogin);
	}
	
	/**
	 *  用户登录
	 * @param name        用户名
	 * @param password    密码
	 * @param callback    返回
	 */
	public void getPersonalLogin(FDUser user, final FDCallback callback) {
		if(user==null) return;
		
		String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_USER_LOGIN);
		LinkedHashMap<String,String> loginMap = new LinkedHashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String userString = objectMapper.writeValueAsString(user);
			loginMap.put("dto", userString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, loginMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "用户登录中...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("login ++++++++++++++++++++++++++++++++++++++++++++++++"+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser);
			}
		});
	}
	
	/**
	 *  用户退出
	 * @param callback
	 */
	public void getPersonalLogout(final FDCallback callback) {
		if(callback==null) return;
		
		String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_USER_LOGOUT);
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "用户注销中...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("logout ----------------------------------------------"+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser.getBaseResult().getStatus());
			}
		});
	}
	
	/**
	 *   用户注册
	 * @param user     用户对象
	 * @param callback
	 */
	public void getPersonalRegister(FDUser user, final FDCallback callback) {
		if(user==null) return;
		
		String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_USER_REGISTER);
		LinkedHashMap<String,String> registerMap = new LinkedHashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String userString = objectMapper.writeValueAsString(user);
			registerMap.put("dto", userString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, registerMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "新用户注册中...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser);
			}
		});
	}
	
	/**
	 *  个人中初始化点评、推荐、收藏和留言等数目
	 * @param callback
	 */
	public void getPersonalInformation(final FDCallback callback) {
		if(callback==null) return;
		
		String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_USER_INFORMATION);
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "用户信息...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDUserInfo userInfo = resultParser.getResultData(FDUserInfo.class);
					callback.onCallback(userInfo);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 完善用户资料
	 * @param user
	 * @param callback
	 */
	public void setPersonalInformation(FDUser user, final FDCallback callback) {
		if(user==null) return;
		String imageUrl = application.getLoginInfo().getHeadImgUrl();
		String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_USER_COMPLETE);
		LinkedHashMap<String,String> updateUserMap = getBaseObjectMap("dto", user);
		LinkedHashMap<String,String> headImageUrlMap = new LinkedHashMap<String,String>();
		if(user.getHeadImgUrl()!=null && imageUrl!=user.getHeadImgUrl()) {
			headImageUrlMap.put("file", user.getHeadImgUrl());
		}
		
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, updateUserMap, headImageUrlMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "完善用户信息...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("Completed --------"+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDUser loginUser = resultParser.getResultData(FDUser.class);
					callback.onCallback(loginUser);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 查询消息-评论回复
	 * @param restId
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getMessageForComment(int commentId, int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MESSAGE_COMMENT_REPLY)+pageNo+"/"+pageSize;
		Map<String,String> restParams = new HashMap<String,String>();
		restParams.put("contentOwnerId", ""+commentId);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, restParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询全部评论回复...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("Message ------------------"+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDCommentReply> resuaurantPage = null;
				try {
					resuaurantPage = resultParser.getResultPageWithData(FDCommentReply.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(resuaurantPage);
			}
		});
	}
	
	/**
	 * 查询全部系统消息
	 * @param restId
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getMessageForSystemMessage(int pageNo, int pageSize, final FDCallback callback) {
		FDAppContext application = (FDAppContext) context.getApplicationContext();
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MESSAGE_SYSTEM_MESSAGE)+pageNo+"/"+pageSize;
		Map<String,String> restParams = new HashMap<String,String>();
		restParams.put("messageStatus", "SYS_MSG");
		restParams.put("toUsername", application.getLoginUserName());
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, restParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询系统消息...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("Message ------------------"+result);
				
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDMessage> resuaurantPage = null;
				try {
					resuaurantPage = resultParser.getResultPageWithData(FDMessage.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(resuaurantPage);
			}
		});
	}
	
	/**
	 * 查询全部留言回复 
	 * @param restId
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getMessageForLeaveMessage(int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MESSAGE_LEAVE_REPLY)+pageNo+"/"+pageSize;
		Map<String,String> restParams = new HashMap<String,String>();
		restParams.put("messageStatus", "COM_MSG_REPLY");
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, restParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询全部留言回复...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("Message ------------------"+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDMessage> resuaurantPage = null;
				try {
					resuaurantPage = resultParser.getResultPageWithData(FDMessage.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(resuaurantPage);
			}
		});
	}
	
	/**
	 * 查询我所评价的学校、企业、餐馆
	 * @param restId      餐馆ID
	 * @param pageNo      页码
	 * @param pageSize    最大记录数
	 * @param callback
	 */
	public void getCommentForRestaurant(int restId, String userName, int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MY_COMMENT_RESTAURANT)+pageNo+"/"+pageSize;
		Map<String,String> restParams = new HashMap<String,String>();
		restParams.put("restaurantId", ""+restId);
		if(!StringUtils.isEmpty(userName)) {
			restParams.put("username", userName);
		}
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, restParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询我点评的餐馆、企业...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("comment restaurant -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDRestaurantComment> resuaurantPage = null;
				try {
					resuaurantPage = resultParser.getResultPageWithData(FDRestaurantComment.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(resuaurantPage);
			}
		});
	}
	public void getCommentForRestaurant(int restId, int pageNo, int pageSize, final FDCallback callback) {
		getCommentForRestaurant(restId,null,pageNo,pageSize,callback);
	}
	
	public void getCommentForRestaurant(String userName, int pageNo, int pageSize, final FDCallback callback) {
		getCommentForRestaurant(-1,userName,pageNo,pageSize,callback);
	}
	
	/**
	 * 查询我所评价的菜肴
	 * @param cuisineId
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getCommentForCuisine(int cuisineId, int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MY_COMMENT_CUISINE)+pageNo+"/"+pageSize;
		Map<String,String> cuisineParams = new HashMap<String,String>();
		cuisineParams.put("cuisineId", ""+cuisineId);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, cuisineParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询我点评的菜肴..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("comment dish -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDCuisineComment> cuisinePage = null;
				try {
					cuisinePage = resultParser.getResultPageWithData(FDCuisineComment.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(cuisinePage);
			}
		});
	}
	
	/**
	 * 我对帖子的评论
	 * @param noteId
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getCommentForNote(int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MY_COMMENT_NOTE)+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询我点评的帖子..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("comment note -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDCommentNote> notePage = null;
				try {
					notePage = resultParser.getResultPageWithData(FDCommentNote.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(notePage);
			}
		});
	}
	
	/**
	 * 点评的帖子详情
	 * @param noteId
	 * @param callback
	 */
	public void getCommentNoteDetail(int noteId, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_FAVORITE_NOTE_DETAIL)+noteId;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "帖子详情..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("note detail -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDNoteComment noteDetailData = resultParser.getResultData(FDNoteComment.class);
					callback.onCallback(noteDetailData);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 菜肴详情
	 * @param dishId
	 * @param callback
	 */
	public void getDishDetail(int dishId, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_QUERY_RESTAURANT_CUISINE_DETAIL)+dishId;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询菜肴..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("dish detail -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDCuisineDetail cuisineData = null;
				try {
					cuisineData = resultParser.getResultData(FDCuisineDetail.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(cuisineData);
			}
		});
	}
	
	/**
	 * 查询菜肴点评
	 * @param pageNo
	 * @param pageSize
	 * @param cuisineId
	 * @param callback
	 */
	public void getDishComments(int pageNo, int pageSize, int cuisineId, final FDCallback callback) {
		if(cuisineId<0) return;
		
		final String url = AbstractService.getRestRequest(FDConst.FD_QUERY_CUISINE_COMMENT)+pageNo+"/"+pageSize;
		RequestParams nutritionCommentParams = new RequestParams();
		nutritionCommentParams.put("cuisineId", ""+cuisineId);
		asyncHttpClient.post(url, nutritionCommentParams,
				new AsyncHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,String responseString) {
						FDViewUtil.dismissProgressDialog(progressDialog);
						if (responseString == null) {
							FDViewUtil.showToast(context, "查无菜肴评价..");
							return;
						}
//						System.out.println("Comment-----------------"+responseString);
						try {
							FDResultParser resultParser = new FDResultParser(context,responseString);
							if(resultParser!=null) {
								FDResultPage<FDCuisineComment> resultPage = resultParser.getResultPageWithData(FDCuisineComment.class);
								callback.onCallback(resultPage);
							}
						} catch (FDParseException e) {
							e.printStackTrace();
						}
					}
					public void onStart() {
						FDViewUtil.showProgressDialog(context, progressDialog,"查询菜肴评价...");
					}
					public void onFinish() {
						FDViewUtil.dismissProgressDialog(progressDialog);
					}
				});
	}
	
	/**
	 * 推荐菜肴
	 * @param dishId
	 * @param callback
	 */
	public void setRecommondDish(int dishId, int enable, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_DISH_RECOMMEND);
		Map<String,String> dishParams = new HashMap<String,String>();
		dishParams.put("cuisineId", ""+dishId);
		dishParams.put("enable", ""+enable);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, dishParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "推荐菜肴..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("dish recommend detail -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser);
			}
		});
	}
	
	/**
	 * 收藏菜肴
	 * @param dishId  菜肴编后
	 * @param enable  1-收藏，0-取消收藏
	 * @param callback
	 */
	public void setForaviteDish(int dishId, int enable, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_DISH_FAVORITE);
		Map<String,String> dishParams = new HashMap<String,String>();
		dishParams.put("cuisineId", ""+dishId);
		dishParams.put("enable", ""+enable);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, dishParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "收藏菜肴..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("dish favorite detail -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser);
			}
		});
	}
	
	/**
	 * 我推荐的餐馆
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getRecommendForRestaurant(int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MY_RECOMMEND_RESTAURANT)+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询我推荐的餐馆..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("recommend ++++++++++++++++++++++++++++++++++++++++++++++++"+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDRestaurantRecommend> recommendPage = null;
				try {
					recommendPage = resultParser.getResultPageWithData(FDRestaurantRecommend.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(recommendPage);
			}
		});
	}
	
	/**
	 * 我推荐的菜肴
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getRecommendForCuisine(int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MY_RECOMMEND_CUISINE)+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询我推荐的菜肴..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("recommend cuisine ++++++++++++++++++++++++++++++++++++++++++++++++"+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDCuisineRecommend> cuisinePage = null;
				try {
					cuisinePage = resultParser.getResultPageWithData(FDCuisineRecommend.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(cuisinePage);
			}
		});
	}
	
	/**
	 * 我的留言
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getMyLeaveMessages(int pageNo, int pageSize, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_MY_LEAVE_MESSAGE)+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询我的留言..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("messagePage --------------------------------------------  "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDLeaveMessage> messagePage = null;
				try {
					messagePage = resultParser.getResultPageWithData(FDLeaveMessage.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(messagePage);
			}
		});
	}
	
	/**
	 * 最新版本信息
	 * @param callback
	 */
	public void getAppVersionInfo(final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_PERSONAL_VERSION_UPGRADE);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
//				FDViewUtil.showProgressDialog(context, progressDialog, "版本更新..");
			}
			public void onSuccess(String result) {
//				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("--- Version upgrade --------------------------------------------  "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				FDVersion appVersion = null;
				try {
					appVersion = resultParser.getResultData(FDVersion.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(appVersion);
			}
		});
	}
}
