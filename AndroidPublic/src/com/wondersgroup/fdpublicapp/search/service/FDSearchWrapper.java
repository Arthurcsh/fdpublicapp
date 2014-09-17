package com.wondersgroup.fdpublicapp.search.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.impl.FDCallback;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.protocol.AbstractLoadServer;
import com.wondersgroup.fdpublicapp.common.protocol.FDBaseWrapper;
import com.wondersgroup.fdpublicapp.common.protocol.FDParseException;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultPage;
import com.wondersgroup.fdpublicapp.common.protocol.FDResultParser;
import com.wondersgroup.fdpublicapp.common.service.AbstractService;
import com.wondersgroup.fdpublicapp.common.service.FDCallbackListenerAdapter;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.FDRestClient;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDCommentInfo;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDInputBatch;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDInputMaterial;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDMealTrace;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDNoteComment;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDOutputMat;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.mode.FDReply;
import com.wondersgroup.fdpublicapp.personal.mode.FDMessage;
import com.wondersgroup.fdpublicapp.search.mode.FDCommentRestaurant;
import com.wondersgroup.fdpublicapp.search.mode.FDDish;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredientGroup;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredients;
import com.wondersgroup.fdpublicapp.search.mode.FDRestMenu;

/**
 *  餐厅查询
 * @author chengshaohua
 *
 */
public class FDSearchWrapper extends FDBaseWrapper {

	public FDSearchWrapper(Context context) {
		super(context);
	}

	/**
	 *  根据条件擦查询餐厅
	 * @param pageNo     页数
	 * @param pageSize   总页数
	 * @param condition  条件
	 * @param callback   返回
	 */
	public void getRestaurant(int pageNo, int pageSize, Map<String,String> condition, final FDCallback callback) {
		String url = AbstractService.getRestRequest(FDConst.FD_INDEX_QUERY_BY_CONDITION) + (pageNo++)+"/"+pageSize;
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, condition, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "餐厅查询中...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("search restaurant --------********-------"+result);
				FDResultParser resultParser = new FDResultParser(context,result);   // 注意：这里的 FD_KEY_DATA特殊
				try {
					if(resultParser!=null) {
						FDResultPage<FDRestaurant> restaurantPage = resultParser.getResultPageWithData(FDRestaurant.class);
						callback.onCallback(restaurantPage);
					}
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 餐厅详情
	 * @param restId
	 * @param callback
	 */
	public void getRestaurantDeatail(final int restId, final FDCallback callback) {
		String url = AbstractService.getRestRequest(FDConst.FD_QUERY_RESTAURANT_DETAIL)+ restId;
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "餐厅详情...");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				
				FDResultParser resultParser = new FDResultParser(context,result); 
				try {
					String bodyString = resultParser.getResultDataWithString();
					FDRestaurant restaurantDetail = AbstractLoadServer.getRestaurantDetail(bodyString);
					callback.onCallback(restaurantDetail);
				} catch (FDParseException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 餐厅菜单
	 * @param restId
	 * @param callback
	 */
	public void getRestaurantMenus(int restId, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_QUERY_RESTAURANT_CUISINE_PICLIST) + restId+"/-1/-1";
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_GET, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "餐馆菜单查询..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("------------Menu--------  "+result);
				
				try {
					FDResultParser resultParser = new FDResultParser(context,result);
					FDResultPage<FDRestMenu>  menuPage = resultParser.getResultPageWithData(FDRestMenu.class);
					callback.onCallback(menuPage);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 食材追溯查询
	 * @param restId
	 * @param callback
	 */
	public void getRestaurantMenusTrace(int cuisineId, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_QUERY_RESTAURANT_CUISINE_TRACE);
		Map<String,String> traceParams = new HashMap<String,String>();
		traceParams.put("outputMatIdListString", ""+cuisineId);
//		traceParams.put("date", ""+StringUtils.dateToString(new Date()));
		FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, traceParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "食材追溯查询..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("------------ Menu Trace --------  "+result);
				
				List<FDMealTrace> mealTraceList = getDishOutputMatTraceList(context, result);
				callback.onCallback(mealTraceList);
			}
		});
	}
	
	/**
	 * 推荐餐厅
	 * @param restId
	 * @param enable
	 * @param callback
	 */
	public void setRecommondRestaurant(int restId, int enable, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_RESTAURANT_RECOMMEND);
		Map<String,String> dishParams = new HashMap<String,String>();
		dishParams.put("restaurantId", ""+restId);
		dishParams.put("enable", ""+enable);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, dishParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "推荐餐馆..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("restaurant recommend detail -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser);
			}
		});
	}
	
	/**
	 * 收藏餐厅
	 * @param restId  餐厅编号
	 * @param enable
	 * @param callback
	 */
	public void setForaviteRestaurant(int restId, int enable, final FDCallback callback) {
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_RESTAURANT_FAVORITE);
		Map<String,String> dishParams = new HashMap<String,String>();
		dishParams.put("companyId", ""+restId);
		dishParams.put("enable", ""+enable);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, dishParams, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "收藏餐馆..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("restaurant favorite detail -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				callback.onCallback(resultParser);
			}
		});
	}
	
	/**
	 * 餐厅点评
	 * @param commentRest  点评的餐厅信息
	 * @param callback
	 */
	public void setCommentRestaurant(FDCommentRestaurant commentRest, final FDCallback callback) {
		if(commentRest==null) return;
		
		Map<String,String> commentMap = new LinkedHashMap<String,String>();
		Map<String,String> imageMap = new LinkedHashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String commentRestaurantString = objectMapper.writeValueAsString(commentRest);
			commentMap.put("dto", commentRestaurantString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		FDNoteComment noteComment = commentRest.getContent();
		if(noteComment!=null) {
			List<FDImage> commentImages = noteComment.getPicList();
			if(commentImages!=null) {
				for(int i=0;i<commentImages.size();i++) {
					FDImage image = commentImages.get(i);
					if(image==null) continue;
					imageMap.put("file_"+i, image.getFilePath());
				}
			}
		}
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_RESTAURANT_COMMENT);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, commentMap, imageMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "点评餐馆..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("comment restaurant -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				int status = resultParser.getBaseResult().getStatus();
				if(status==1) {
					FDViewUtil.showToast(context, "餐厅点评失败..", true);
				}else if(status==0) {
					FDViewUtil.showToast(context, "餐厅点评成功..", true);
				}
				callback.onCallback(status);
			}
		});
	}
	
	/**
	 * 查询餐厅下的菜肴
	 * @param restId
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 */
	public void getDishWithRestaurant(int restId, int pageNo, int pageSize, final FDCallback callback) {
		if(restId<0) return;
		
		final String url = AbstractService.getRestRequest(FDConst.FD_QUERY_RESTAURANT_CUISINE)+restId+"/"+pageNo+"/"+pageSize;
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "获取餐馆菜肴..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("dish restaurant -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				try {
					FDResultPage<FDDish> restaurantPage = resultParser.getResultPageWithData(FDDish.class);
					callback.onCallback(restaurantPage);
				} catch (FDParseException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 点评菜肴
	 * @param dishId
	 * @param callback
	 */
	public void setDishComment(FDCommentInfo dishComment, final FDCallback callback) {
		if(dishComment==null) return;
		
		Map<String,String> commentMap = new LinkedHashMap<String,String>();
		Map<String,String> imageMap = new LinkedHashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String commentDishString = objectMapper.writeValueAsString(dishComment);
			commentMap.put("dto", commentDishString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if(dishComment.getContent()!=null) {
			List<FDImage> dishCommentImages = dishComment.getContent().getPics();
			imageMap = this.getBaseImageMap(dishCommentImages);
		}
		
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_DISH_COMMENT);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, commentMap, imageMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "点评菜肴..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("dish restaurant -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				int status = resultParser.getBaseResult().getStatus();
				callback.onCallback(status);
			}
		});
	}
	
	/**
	 * 对评论的回复
	 * @param reply
	 * @param callback
	 */
	public void setCommonCommentReply(FDReply commentReply,  final FDCallback callback) {
		if(commentReply==null) return;
		
		Map<String,String> commentReplyMap = new LinkedHashMap<String,String>();
		Map<String,String> imageMap = new LinkedHashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String commentReplyString = objectMapper.writeValueAsString(commentReply);
			commentReplyMap.put("dto", commentReplyString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		List<FDImage> replyImages = commentReply.getPicList();
		if(replyImages!=null) {
			imageMap = this.getBaseImageMap(replyImages);
		}
		
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_COMMON_COMMENT_REPLY);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, commentReplyMap, imageMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "提交点评回复中..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
				System.out.println("reply comment  -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				int replyStatus = resultParser.getBaseResult().getStatus();
				callback.onCallback(replyStatus);
			}
		});
	}
	
	/**
	 * 商家留言
	 * @param commentReply
	 * @param callback
	 */
	public void setCreateLeaveMessageToCompany(FDMessage message,  final FDCallback callback) {
		if(message==null) return;
		
		Map<String,String> commentMessageMap = getBaseObjectMap("dto", message);
		final String url = AbstractService.getRestRequest(FDConst.FD_EXECUTE_CREATE_MESSAGE);
    	FDRestClient restClient = new FDRestClient(context);
		restClient.excuteRestServer(AbstractService.HTTP_POST, url, commentMessageMap, new FDCallbackListenerAdapter(){
			public void onStart() {
				FDViewUtil.showProgressDialog(context, progressDialog, "商家留言..");
			}
			public void onSuccess(String result) {
				FDViewUtil.dismissProgressDialog(progressDialog);
				if(result==null) return;
//				System.out.println("company leave message  -------------------------------- "+result);
				FDResultParser resultParser = new FDResultParser(context,result);
				int replyStatus = resultParser.getBaseResult().getStatus();
				callback.onCallback(replyStatus);
			}
		});
	}
	
	/**
	 * 营养餐食材追溯
	 * @param context
	 * @param outputMatTraceString
	 * @return
	 */
	public static List<FDMealTrace> getMealOutputMatTraceList(Context context, String outputMatTraceString) {
		if(outputMatTraceString==null) return null;
		
		List<FDMealTrace> mealTraceList = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);    
			FDResultParser resultParser = new FDResultParser(context,outputMatTraceString);
			String mealTraceInfo = resultParser.getResultDataWithString();  
			JSONArray mealTraceArray = new JSONArray(mealTraceInfo);
			if(mealTraceArray!=null && mealTraceArray.length()>0) {
				mealTraceList = new ArrayList<FDMealTrace>();
				for(int i=0;i<mealTraceArray.length();i++) {
					JSONObject mealTraceObject = mealTraceArray.getJSONObject(i);
					if(mealTraceObject==null) continue;
					
					FDMealTrace mealTrace = new FDMealTrace();
					LinkedHashMap<String, Object> entityMap = objectMapper.readValue(mealTraceObject.toString(), LinkedHashMap.class);
					Object outputMatObject = entityMap.get("info");
					Object outputTraceObject = entityMap.get("traceList");
					FDOutputMat mealOutputMat = objectMapper.convertValue(outputMatObject, FDOutputMat.class);
					ArrayList<FDInputBatch> traceList = objectMapper.convertValue(outputTraceObject, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, FDInputBatch.class));
					mealTrace.setInfo(mealOutputMat);
					mealTrace.setTraceList(traceList);
					mealTraceList.add(mealTrace);
				}
			}
		} catch (FDParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mealTraceList;
	}
	
	/**
	 * 菜肴食材追溯
	 * @param context
	 * @param outputMatTraceString
	 * @return
	 */
	public static List<FDMealTrace> getDishOutputMatTraceList(Context context, String outputMatTraceString) {
		if(outputMatTraceString==null) return null;
		
		List<FDMealTrace> dishTraceList = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);    
			FDResultParser resultParser = new FDResultParser(context,outputMatTraceString);
			String dishTraceInfo = resultParser.getResultDataWithString();  
			JSONArray dishTraceArray = new JSONArray(dishTraceInfo);
			if(dishTraceArray!=null && dishTraceArray.length()>0) {
				dishTraceList = new ArrayList<FDMealTrace>();
				for(int i=0;i<dishTraceArray.length();i++) {
					JSONArray dishTraceObject = dishTraceArray.getJSONArray(i);
					if(dishTraceObject==null) continue;
					
					FDMealTrace mealTrace = new FDMealTrace();
					ArrayList<FDInputBatch> entityList = objectMapper.readValue(dishTraceObject.toString(), TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, FDInputBatch.class));
					mealTrace.setTraceList(entityList);
					dishTraceList.add(mealTrace);
				}
			}
		} catch (FDParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dishTraceList;
	}
	
	/**
     *  食材追溯信息
     * @param mealTraceList
     * @return
     */
    public static ArrayList<FDIngredientGroup> getMenuIngredientList(List<FDMealTrace> mealTraceList) {
    	if(mealTraceList==null) return null;
    	System.out.println("------------   trace   ------------"+mealTraceList);
    	
    	ArrayList<FDIngredientGroup> ingredientGroupList = new ArrayList<FDIngredientGroup>();
		for(FDMealTrace mealTrace:mealTraceList) {
			if(mealTrace==null) continue;
			FDOutputMat outputMat = mealTrace.getInfo();
			ArrayList<FDInputBatch> inputBatchList = mealTrace.getTraceList();
			if(inputBatchList!=null) {
				FDIngredientGroup ingredientGroup = new FDIngredientGroup();
				ArrayList<FDIngredients> ingredientList = new ArrayList<FDIngredients>();
				for(FDInputBatch inputBatch:inputBatchList) {
					if(inputBatch==null) continue;
					FDInputMaterial inputMaterial = inputBatch.getInputMatDto();
					if(inputMaterial!=null) {
						FDIngredients ingredient = new FDIngredients();
						ingredient.setId(inputBatch.getId());
						ingredient.setName(inputMaterial.getName());
						ingredient.setType(inputMaterial.getTypeGeneral());
						ingredient.setSpecifications(inputMaterial.getSpec());
						ingredient.setManufacturer(inputMaterial.getManufacture());
						ingredient.setWeight(inputBatch.getQuantity());
						ingredient.setPurchaseDate(StringUtils.dateToString2(inputBatch.getProductionDate()));
						ingredient.setGuaranteeDate(StringUtils.dateToString2(inputBatch.getExpireDate()));
						if(outputMat!=null && outputMat.getPicList()!=null && outputMat.getPicList().size()>0) {
							ingredient.setImageFile(outputMat.getPicList().get(0).getFilePath());
						}
						ingredientList.add(ingredient);
					}
				}
				ingredientGroup.setIngredients(ingredientList);
				if(outputMat!=null) {
					ingredientGroup.setName(outputMat.getName());
				}
				ingredientGroupList.add(ingredientGroup);
			}
		}
		return ingredientGroupList;
    }
}
