package com.wondersgroup.fdpublicapp.home.nutritionMeal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.FDBaseWrapper;
import com.wondersgroup.fdpublicapp.common.protocol.FDParseException;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.service.AbstractService;
import com.wondersgroup.fdpublicapp.common.service.FDCallbackListenerAdapter;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.FDRestClient;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDCategory;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDCommentInfo;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteCuisine;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteNote;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteNoteDetail;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealInfo;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealItem;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealTrace;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDFavoriteRestaurant;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDSchool;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.search.service.FDSearchWrapper;

/**
 *  学校营养餐服务通讯包
 * @author chengshaohua
 *
 */
public class FDNutritionMealWrapper extends FDBaseWrapper {

	public final static String FD_NUTRITION_CATEGORY = "stumealType";
	public final static String FD_NUTRITION_REGION = "adminRegionTree";
	public final static String FD_NUTRITION_SCHOOL = "schoolType";
	public final static String FD_NUTRITION_PARAM_SCHOOL_TYPE = "schoolType";
	public final static String FD_NUTRITION_PARAM_REGION_CODE = "supervisionOrgId";
	public final static String FD_NUTRITION_PARAM_SCHOOL_NAME = "schoolName";
	public final static int FD_NUTRITION_SCHOOL_DETAIL = 201;
	
	public FDNutritionMealWrapper(Context context) {
		super(context);
		
	}

	/**
	 * 获取静态信息(行政区域，学校类型， 餐次类型列表)
	 */
	public void getNutritionMealData() {
		String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_DATA_INFO);
		asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers,String responseString) {
				if(responseString==null) return;
				
				try {
					FDResultParser resultParser = new FDResultParser(context,responseString);
					String nutritionMealString = resultParser.getResultDataWithString();
					setNutritionMealInfoFromString(nutritionMealString);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}

			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "加载中...");
			}

			public void onFinish() {
				FDViewUtil.dismissProgressDialog(progressDialog);
			}
		});
	}
	
	/**
	 *  解析营养餐类型、行政区域和餐次类型
	 * @param nutritionMeal   待解析的信息内容
	 */
	public void setNutritionMealInfoFromString(String nutritionMeal) {
		if(StringUtils.isEmpty(nutritionMeal)) return;
		
		try {
			JSONObject nutritionMealObject = new JSONObject(nutritionMeal);
			String nutritionCategory = nutritionMealObject.getString(FD_NUTRITION_CATEGORY);
			String nutritionSchool = nutritionMealObject.getString(FD_NUTRITION_SCHOOL);
			String nutritionRegion = nutritionMealObject.getString(FD_NUTRITION_REGION);
			
			ArrayList<FDCategory> categoryList = getArrayCategory(nutritionCategory);
			ArrayList<FDCategory> schoolList = getArrayCategory(nutritionSchool);
			ArrayList<FDCommerialCenter> adminRegions = getNutritionRegionFromString(nutritionRegion);
			Collections.sort(schoolList,comparator);
			ServiceManager.putStringValue(FDConst.FD_NUTRITION_DATA_CATEGORY, categoryList);
			ServiceManager.putStringValue(FDConst.FD_NUTRITION_DATA_SCHOOL, schoolList);
			ServiceManager.putStringValue(FDConst.FD_NUTRITION_DATA_REGION, adminRegions);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *   解析区域街道
	 * @param nutritionRegion  区域街道参数
	 * @return
	 */
	public ArrayList<FDCommerialCenter> getNutritionRegionFromString(String nutritionRegion) {
		final String FD_SPLIT_CHAR = "/t";
		if(nutritionRegion==null) return null;
		ArrayList<FDCommerialCenter> reginList = new ArrayList<FDCommerialCenter>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<String, ArrayList<String>> regionMaps = objectMapper.readValue(nutritionRegion, LinkedHashMap.class);
			if(regionMaps==null) return null;
			for (Map.Entry<String, ArrayList<String>> entry : regionMaps.entrySet()) {
				if(entry==null) continue;
				FDCommerialCenter region = new FDCommerialCenter();
				String key = entry.getKey();
				String[] regionKey = key.split(FD_SPLIT_CHAR);
				region.setCommerialCode(regionKey[0]);
				region.setCommerialName(regionKey[1]);
				ArrayList<String> regionArray = regionMaps.get(key);
				if(regionArray!=null) {
					ArrayList<FDCommerialCenter> reginChildList = new ArrayList<FDCommerialCenter>();
					for(String regionChildren : regionArray) {
						if(regionChildren==null) continue;
						FDCommerialCenter childRegion = new FDCommerialCenter();
						String[] regionChildArray = regionChildren.split(FD_SPLIT_CHAR);
						childRegion.setCommerialCode(regionChildArray[0]);
						childRegion.setCommerialName(regionChildArray[1]);
						reginChildList.add(childRegion);
//						System.out.println("Region Name: "+region.getCommerialName()+"  Region Child Name: "+childRegion.getCommerialName());
					}
					region.setCommunities(reginChildList);
				}
				reginList.add(region);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return reginList;
	}
	
	/**
	 *    查询营养餐学校
	 * @param regionId      学校区域
	 * @param schoolType    学校编号
	 * @param SchoolName    学校名称
	 * @param pageNo        页码
	 * @param pageSize      当前页记录数
	 * @param callback      回调数据集
	 */
	public void getNutritionMealSchool(int pageNo, int pageSize, String regionId, String schoolType, String SchoolName, final FDCallback callback) {
		if(callback==null) return;
		
		String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_QUERY_SCHOOL)+pageNo+"/"+pageSize;
		RequestParams schoolParams = new RequestParams();
		if(regionId!=null) {
			schoolParams.put(FD_NUTRITION_PARAM_REGION_CODE, regionId);
		}
		if(schoolType!=null) {
			schoolParams.put(FD_NUTRITION_PARAM_SCHOOL_TYPE, schoolType);
		}
		if(SchoolName!=null) {
			schoolParams.put(FD_NUTRITION_PARAM_SCHOOL_NAME, SchoolName);
		}
		
		asyncHttpClient.post(url, schoolParams, new AsyncHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers,String responseString) {
				if(responseString==null) return;
				
				try {
					FDResultParser resultParser = new FDResultParser(context,responseString);
					FDResultPage<FDSchool> nutritionSchools = resultParser.getResultPageWithData(FDSchool.class);
					callback.onCallback(nutritionSchools);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}

			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "正在加载学校信息...");
			}
			public void onFinish() {
				FDViewUtil.dismissProgressDialog(progressDialog);
			}
		});
	}
	
	/**
	 *  收藏学校
	 * @param schoolId   学校编号
	 * @param callback
	 */
	public void collectNutritionMealSchool(int schoolId, int enable, final FDCallback callback) {
		String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_COLLECT_SCHOOL);
		Map<String,String> collectSchoolParams = new HashMap<String,String>();
		collectSchoolParams.put("companyId", ""+schoolId);
		collectSchoolParams.put("enable", ""+enable);   
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, collectSchoolParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "收藏该学校...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser.getBaseResult().getStatus());
			}
		});
		
	}
	
	/**
	 *  学校对应的某个月内的所有供餐信息
	 * @param schoolId   学校
	 * @param date       日期
	 * @param callback   返回
	 */
	public void getSchoolNutritionMealInfoByMonth(String schoolId, String date, final FDCallback callback) {
		if(schoolId==null || date==null || callback==null) return;
		String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_QUERY_BY_MONTH);
		RequestParams nutritionMealParams = new RequestParams();
		nutritionMealParams.put("schoolId", schoolId);
		nutritionMealParams.put("dateString", date);   
		asyncHttpClient.post(url, nutritionMealParams, new AsyncHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers,String responseString) {
				if(responseString==null) return;

				try {
					FDResultParser resultParser = new FDResultParser(context,responseString);
					String nutritionMealString = resultParser.getResultDataWithString();
//					System.out.println("Meal: "+nutritionMealString);
					LinkedHashMap<String, ArrayList<FDMealItem>> nutritionMealMap = getNutritionMealMapByMonth(nutritionMealString, FDMealItem.class);
					if(nutritionMealMap!=null) {
						callback.onCallback(nutritionMealMap);
					}
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}

			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询当月营养餐信息...");
			}
			public void onFinish() {
				FDViewUtil.dismissProgressDialog(progressDialog);
			}
		});
	}
	
	/**
	 * 
	 * @param nutritionMealString  待解析的餐次信息
	 * @param clazz
	 * @return    返回当月的餐次集
	 */
	public <E> LinkedHashMap<String, ArrayList<E>> getNutritionMealMapByMonth(String nutritionMealString, Class<E> clazz) {
		if(nutritionMealString==null) return null;
		
		LinkedHashMap<String, ArrayList<E>> entity = new LinkedHashMap<String, ArrayList<E>>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);     // 忽略无法解析的属性
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); // 键支持 不带双引号
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);     // 需要忽略 默认值
		try {
			LinkedHashMap<String, Object> entityMap = objectMapper.readValue(nutritionMealString, LinkedHashMap.class);
			if(entityMap==null) return null;
			for(Map.Entry<String, Object> entry : entityMap.entrySet()) {
				if(entry==null || entry.getValue()==null || "".equals(entry.getValue())) continue;
				
				Object value = entry.getValue();
				ArrayList<E> mealItemList = objectMapper.convertValue(value, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
				if(mealItemList!=null) {
					entity.put(entry.getKey(), mealItemList);
				}
			}
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
	 *  学生营养餐详情信息 (早、午、晚)
	 * @param mealId   
	 * @param callback
	 */
	public void getSchoolNutritionMealDetailInfo(final int mealId, final FDCallback callback) {
		if(mealId<0 || callback==null) return;
		String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_QUERY_STUDENT_MEAL);
		RequestParams nutritionMealParams = new RequestParams();
		nutritionMealParams.put("stuMealId", ""+mealId);
		asyncHttpClient.post(url, nutritionMealParams, new AsyncHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers,String responseString) {
				if(responseString==null) {
					FDViewUtil.showToast(context, "查无餐次信息..");
					return;
				}
//				System.out.println("###############################: MealDetail  "+responseString);
				try {
					FDResultParser resultParser = new FDResultParser(context,responseString);
					FDMealInfo mealInfo = resultParser.getResultData(FDMealInfo.class);
					if(mealInfo!=null) {
						callback.onCallback(mealInfo);
					}else {
						FDViewUtil.showToast(context, "查无餐次信息..");
					}
				} catch (FDParseException e) {
					e.printStackTrace();
				} 
			}

			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询当月营养餐信息...");
			}
			public void onFinish() {
				FDViewUtil.dismissProgressDialog(progressDialog);
			}
		});
	}
	
	/**
	 *  查询营养餐食材追溯信息
	 * @param outputMat  产出品ID "1243,1244,1245"
	 * @param date       营养餐次时间
	 * @param callback
	 */
	public void getSchoolNutritionMealTraceInfo(String outputMat, String date, final FDCallback callback) {
		if(date==null || outputMat==null) return;
		
		String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_QUERY_STUDENT_MEAL_TRACE);
		Map<String,String> mealTraceMap = new LinkedHashMap<String,String>();
		mealTraceMap.put("relatedOutputMatIdListString", outputMat);   // relatedOutputMatIdListstuMealId
		mealTraceMap.put("date", date);
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, mealTraceMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询当前食材追溯信息...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("MealTraceInfo -------------------------------- "+result);
				List<FDMealTrace>mealTraceList = FDSearchWrapper.getMealOutputMatTraceList(context, result);
				callback.onCallback(mealTraceList);
			}
		});
	}
	
	/**
	 * 对营养餐点评
	 * @param comment  营养餐对象
	 * @param images   点评的图片
	 * @param callback 返回
	 */
	public void setNutritionMealComment(FDCommentInfo commentInfo, ArrayList<FDImage> images, final FDCallback callback) {
		if(commentInfo==null) return;
		
		String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_COMMENT_MEAL);
		LinkedHashMap<String,String> commentMap = new LinkedHashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String userString = objectMapper.writeValueAsString(commentInfo);
			commentMap.put("dto", userString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Map<String, String> imageMap = new HashMap<String,String>();
		if(images!=null) {
			for(int i=0;i<images.size();i++) {
				FDImage image = images.get(i);
				if(image==null) continue;
				imageMap.put("file_"+i, image.getFilePath());
			}
		}
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, commentMap, imageMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "正在添加营养餐评价...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("result++++++++++++++++++++++++++++++++++++++++++++++++"+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser);
			}
		});
	}
	
	/**
	 *  查询所有的营养餐点评
	 * @param pageNo        当前页
	 * @param pageSize      最大记录数
	 * @param cuisineId     菜肴编号
	 * @param callback      回调
	 */
    public void getNutritionMealComments(int pageNo, int pageSize, int cuisineId, final FDCallback callback) {
		if(cuisineId<0) return;
		
		final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_QUERY_COMMENT_MEAL)+pageNo+"/"+pageSize;
		RequestParams nutritionCommentParams = new RequestParams();
		nutritionCommentParams.put("stuMealId", ""+cuisineId);
		asyncHttpClient.post(url, nutritionCommentParams,
				new AsyncHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,String responseString) {
						FDViewUtil.dismissProgressDialog(progressDialog);
						if (responseString == null) {
							FDViewUtil.showToast(context, "查无营养餐评价..");
							return;
						}
						System.out.println("Comment-----------------"+responseString);
						try {
							FDResultParser resultParser = new FDResultParser(context,responseString);
							if(resultParser!=null) {
								FDResultPage<FDComment> resultPage = resultParser.getResultPageWithData(FDComment.class);
								callback.onCallback(resultPage);
							}
						} catch (FDParseException e) {
							e.printStackTrace();
						}
					}
					public void onStart() {
						FDViewUtil.showProgressDialog(context, progressDialog,"查询营养餐评价...");
					}
					public void onFinish() {
						FDViewUtil.dismissProgressDialog(progressDialog);
					}
				});
	}
    
    /**
     * 查询全部评论的回复
     * @param pageNo
     * @param pageSize
     * @param cuisineId
     * @param callback
     */
    public void getCommentDetailReplay(int pageNo, int pageSize, int commentId, final FDCallback callback) {
    	final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_QUERY_COMMENT_REPLY)+pageNo+"/"+pageSize;
		RequestParams commentReplayParams = new RequestParams();
		commentReplayParams.put("contentOwnerId", ""+commentId);
		commentReplayParams.put("contentType", "COMMENT_RLY");
		asyncHttpClient.post(url, commentReplayParams,
				new AsyncHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,String responseString) {
						FDViewUtil.dismissProgressDialog(progressDialog);
						if (responseString == null) {
							FDViewUtil.showToast(context, "查无评价的回复..");
							return;
						}
						System.out.println("Replay-----------------"+responseString);
						try {
							FDResultParser resultParser = new FDResultParser(context,responseString);
							if(resultParser!=null) {
								FDResultPage<FDReply> resultPage = resultParser.getResultPageWithData(FDReply.class);
								callback.onCallback(resultPage);
							}
						} catch (FDParseException e) {
							e.printStackTrace();
						}
					}
					public void onStart() {
						FDViewUtil.showProgressDialog(context, progressDialog,"查询营养餐评价回复...");
					}
					public void onFinish() {
						FDViewUtil.dismissProgressDialog(progressDialog);
					}
				});
    }
    
    
    /**
     *  我收藏的学校-餐馆
     * @param callback
     */
    public void getStudentFavoriteRestaurant(int pageNo, int pageSize,final FDCallback callback) {
    	final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_FAVORITE_RESTAURANT)+pageNo+"/"+pageSize;
		asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,String responseString) {
						FDViewUtil.dismissProgressDialog(progressDialog);
						if (responseString == null) {
							FDViewUtil.showToast(context, "查无收藏的餐馆..");
							return;
						}
						System.out.println("Favorite -----------------"+responseString);
						try {
							FDResultParser resultParser = new FDResultParser(context,responseString);
							if(resultParser!=null) {
								FDResultPage<FDFavoriteRestaurant> resultPage = resultParser.getResultPageWithData(FDFavoriteRestaurant.class);
								callback.onCallback(resultPage);
							}
						} catch (FDParseException e) {
							e.printStackTrace();
						}
					}
					public void onStart() {
						FDViewUtil.showProgressDialog(context, progressDialog,"加载收藏的餐馆...");
					}
					public void onFinish() {
						FDViewUtil.dismissProgressDialog(progressDialog);
					}
				});
    }
    
    /**
     *  我收藏的学校
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void getStudentFavoriteSchool(int pageNo, int pageSize,final FDCallback callback) {
    	final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_FAVORITE_SCHOOL)+pageNo+"/"+pageSize;
    	
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "查询收藏的学校...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				
				FDResultParser resultParser = new FDResultParser(context,result);
				FDResultPage<FDSchool> schoolPage = null;
				try {
					schoolPage = resultParser.getResultPageWithData(FDSchool.class);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
				callback.onCallback(schoolPage);
			}
		});
		
    }
    
    /**
     *  查询收藏的菜肴
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void getStudentFavoriteCuisine(int pageNo, int pageSize,final FDCallback callback) {
    	final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_FAVORITE_CUISINE)+pageNo+"/"+pageSize;
		asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,String responseString) {
						FDViewUtil.dismissProgressDialog(progressDialog);
						if (responseString == null) {
							FDViewUtil.showToast(context, "查无收藏的菜肴..");
							return;
						}
//						System.out.println("result++++++++++++++++++++++++++++++++++++++++++++++++"+responseString);
						try {
							FDResultParser resultParser = new FDResultParser(context,responseString);
							if(resultParser!=null) {
								FDResultPage<FDFavoriteCuisine> resultPage = resultParser.getResultPageWithData(FDFavoriteCuisine.class);
								callback.onCallback(resultPage);
							}
						} catch (FDParseException e) {
							e.printStackTrace();
						}
					}
					public void onStart() {
						FDViewUtil.showProgressDialog(context, progressDialog,"正在收藏的菜肴...");
					}
					public void onFinish() {
						FDViewUtil.dismissProgressDialog(progressDialog);
					}
				});
    }
    
    /**
     *  查询收藏的帖子
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void getStudentFavoriteNote(int pageNo, int pageSize,final FDCallback callback) {
    	final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_FAVORITE_NOTE)+pageNo+"/"+pageSize;
		asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,String responseString) {
						FDViewUtil.dismissProgressDialog(progressDialog);
						if (responseString == null) {
							FDViewUtil.showToast(context, "查无收藏的帖子..");
							return;
						}
//						System.out.println("-------Favorite  note -----"+responseString);
						try {
							FDResultParser resultParser = new FDResultParser(context,responseString);
							if(resultParser!=null) {
								FDResultPage<FDFavoriteNote> resultPage = resultParser.getResultPageWithData(FDFavoriteNote.class);
								callback.onCallback(resultPage);
							}
						} catch (FDParseException e) {
							e.printStackTrace();
						}
					}
					public void onStart() {
						FDViewUtil.showProgressDialog(context, progressDialog,"正在收藏的帖子...");
					}
					public void onFinish() {
						FDViewUtil.dismissProgressDialog(progressDialog);
					}
				});
    }
    
    /**
     * 我收藏的帖子详情
     * @param noteId
     * @param callback
     */
    public void getStudentFavoriteNoteDetail(int noteId, final FDCallback callback) {
    	final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_FAVORITE_NOTE_DETAIL)+noteId;
		asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,String responseString) {
						FDViewUtil.dismissProgressDialog(progressDialog);
						if (responseString == null) {
							FDViewUtil.showToast(context, "查无收藏的帖子..");
							return;
						}
						System.out.println("-------Nutrition  note detail-----"+responseString);
						try {
							FDResultParser resultParser = new FDResultParser(context,responseString);
							if(resultParser!=null) {
								FDFavoriteNoteDetail noteDetail = resultParser.getResultData(FDFavoriteNoteDetail.class);
								callback.onCallback(noteDetail);
							}
						} catch (FDParseException e) {
							e.printStackTrace();
						}
					}
					public void onStart() {
						FDViewUtil.showProgressDialog(context, progressDialog,"正在加载帖子详情..");
					}
					public void onFinish() {
						FDViewUtil.dismissProgressDialog(progressDialog);
					}
				});
    }
    
    /**
     * 查询收藏的帖子评论
     * @param noteId
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void getStudentFavoriteNoteComments(int noteId, int pageNo, int pageSize, final FDCallback callback) {
    	final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_FAVORITE_NOTE_COMMENT)+pageNo+"/"+pageSize;
    	RequestParams noteCommentParams = new RequestParams();
    	noteCommentParams.put("contentId", ""+noteId);
		asyncHttpClient.post(url, noteCommentParams, new AsyncHttpResponseHandler() {
					public void onSuccess(int statusCode, Header[] headers,String responseString) {
						FDViewUtil.dismissProgressDialog(progressDialog);
						if (responseString == null) {
							FDViewUtil.showToast(context, "查无帖子评论..");
							return;
						}
//						System.out.println("----------note comment------"+responseString);
						try {
							FDResultParser resultParser = new FDResultParser(context,responseString);
							if(resultParser!=null) {
								FDResultPage<FDNoteComment> resultPage = resultParser.getResultPageWithData(FDNoteComment.class);
								callback.onCallback(resultPage);
							}
						} catch (FDParseException e) {
							e.printStackTrace();
						}
					}
					public void onStart() {
						FDViewUtil.showProgressDialog(context, progressDialog,"正在加载帖子评论..");
					}
					public void onFinish() {
						FDViewUtil.dismissProgressDialog(progressDialog);
					}
				});
    }
    
    /**
     * 收藏帖子
     * @param noteId
     * @param enable
     * @param callback
     */
    public void setStudentFavoriteNote(int noteId, int enable, final FDCallback callback) {
    	final String url = AbstractService.getRestRequest(FDConst.FD_NUTRITION_COLLECT_NOTE);
    	Map<String, String> noteParams = new HashMap<String,String>();
    	noteParams.put("contentId", ""+noteId);
    	noteParams.put("enable", ""+enable);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, noteParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "收藏帖子..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				
				FDResultParser resultParser = new FDResultParser(context,result);
				int collectStatus = resultParser.getBaseResult().getStatus();
				callback.onCallback(collectStatus);
			}
		});
		
    }
    
    /**
     * 对帖子的点评
     * @param commentReply
     * @param callback
     */
    public void setFavoriteNoteComment(FDReply commentReply, final FDCallback callback) {
        if(commentReply==null) return;
		
		Map<String,String> commentReplyMap = new LinkedHashMap<String,String>();
		Map<String,String> imageMap = new LinkedHashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		commentReplyMap.put("contentType", "COMMENT");
		try {
			String commentReplyString = objectMapper.writeValueAsString(commentReply);
			commentReplyMap.put("dto", commentReplyString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		List<FDImage> replyImages = commentReply.getPicList();
		if(replyImages!=null) {
			for(int i=0;i<replyImages.size();i++) {
				FDImage image = replyImages.get(i);
				if(image==null) continue;
				imageMap.put("file_"+i, image.getFilePath());
			}
		}
		
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_COMMON_COMMENT_REPLY);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, commentReplyMap, imageMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "提交点评中..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println(" comment note -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				int commentStatus = resultParser.getBaseResult().getStatus();
				callback.onCallback(commentStatus);
			}
		});
    }
}
