package com.wondersgroup.fdpublicapp.common.protocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.service.FDConst;
import com.wondersgroup.fdpublicapp.common.service.ServiceManager;
import com.wondersgroup.fdpublicapp.common.util.JsonAnalyzeKeyAndValue;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.coupon.mode.FDCoupon;
import com.wondersgroup.fdpublicapp.home.main.activity.FDSearchFragment;
import com.wondersgroup.fdpublicapp.home.main.mode.FDMenu;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurant;
import com.wondersgroup.fdpublicapp.home.main.mode.FDRestaurantTable;
import com.wondersgroup.fdpublicapp.search.mode.FDAtmosphere;
import com.wondersgroup.fdpublicapp.search.mode.FDCommerialCenter;
import com.wondersgroup.fdpublicapp.search.mode.FDCredit;
import com.wondersgroup.fdpublicapp.search.mode.FDCuisine;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredientGroup;
import com.wondersgroup.fdpublicapp.search.mode.FDIngredients;
import com.wondersgroup.fdpublicapp.search.mode.FDSuperMode;

public class AbstractLoadServer {

	public OnLoadedCallbackListener onLoadedListener;
	
	// 获取餐厅列表信息   (附近餐厅查询)       121.521653, 31.07894
	public static FDRestaurantTable getRestaurant(String restaurantJson) throws JSONException {
		if (StringUtils.isEmpty(restaurantJson)) return null;
		ArrayList<FDSuperMode> averagelist = (ArrayList<FDSuperMode>) ServiceManager.get(FDConst.FD_MAIN_HASHMAP_AVERAGE_CONSUM);
		
		ArrayList<FDRestaurant> restaurantList = new ArrayList<FDRestaurant>();
		FDRestaurantTable restaurantTable = new FDRestaurantTable();
		JSONObject restaurantObject = new JSONObject(restaurantJson);
		JSONObject restaurantData = restaurantObject.optJSONObject("data");
		if(restaurantData!=null) {
			JSONArray restaurantArray = restaurantData.optJSONArray("resultList");
			restaurantTable.setCurrentPage(restaurantData.optInt("currPageNo"));
			restaurantTable.setPageCount(restaurantData.optInt("pageCount"));
			
			for (int i = 0; i < restaurantArray.length(); i++) {
				JSONObject restaurantItem = restaurantArray.getJSONObject(i);
				if (restaurantItem == null)continue;
				
				FDRestaurant restaurant = new FDRestaurant();
				restaurant.setId(restaurantItem.optInt("id"));
				restaurant.setName(restaurantItem.optString("name"));
				restaurant.setBizAddress(restaurantItem.optString("bizAddress", ""));
				restaurant.setFoodSaftyRating(restaurantItem.optInt("foodSaftyRating"));
				restaurant.setGisLongitude(restaurantItem.optDouble("gisLongitude"));
				restaurant.setGisLatitude(restaurantItem.optDouble("gisLatitude"));
				restaurant.setAverageComsumption(restaurantItem.optInt("averageComsumption"));
				if(averagelist!=null) {
					for(FDSuperMode average : averagelist) {
						if(average==null) continue;
						if(average.getCode().equals(restaurant.getAverageComsumption()+"")) {
							restaurant.setAverageComsumptionValue(average.getName());
							break;
						}
					}
				}
				
				ArrayList<FDCuisine> cuisineList = new ArrayList<FDCuisine>();
				if(restaurantItem.has("restCuisineTypeList") && !restaurantItem.get("restCuisineTypeList").equals(JSONObject.NULL)){
					JSONArray cuisineArray = restaurantItem.getJSONArray("restCuisineTypeList");
					StringBuffer cuisineValueBuffer = new StringBuffer();
					for (int k = 0; k < cuisineArray.length(); k++) {
						JSONObject cuisineObject = (JSONObject) cuisineArray.get(k);
						if(cuisineObject==null) continue;
						FDCuisine cuisine = new FDCuisine();
						cuisine.setCuisineValue(cuisineObject.optString("cuisineValue"));
						cuisine.setCuisine(cuisineObject.optString("cuisine"));
						cuisineValueBuffer.append(cuisine.getCuisineValue());
						if (k != (cuisineArray.length()-1)) {
							cuisineValueBuffer.append(",");
						}
						cuisineList.add(cuisine);		
					}
					restaurant.setCuisineValue(cuisineValueBuffer.toString());
					restaurant.setRestCuisineTypeList(cuisineList);
				}
				restaurantList.add(restaurant);
			}
			restaurantTable.setRestaurants(restaurantList);
		}
		
		return restaurantTable;
	}
	
	//获取餐厅菜单
	public static ArrayList<FDMenu> getRestaurantMenuCuisineList(String responseString) throws JSONException {
		if(StringUtils.isEmpty(responseString)) return null;
		
		if (responseString == null)return null;
		JSONObject cuisineObject = new JSONObject(responseString);
		if (cuisineObject == null) return null;
		
		ArrayList<FDMenu> menuList = null;
		if (cuisineObject.has("resultList") && !cuisineObject.get("resultList").equals(null) && cuisineObject.get("resultList") != null) {
			JSONArray cuisineMenuArray = cuisineObject.getJSONArray("resultList");
			if(cuisineMenuArray==null) return null;
			
			menuList = new ArrayList<FDMenu>();
			for (int i = 0; i < cuisineMenuArray.length(); i++) {
				JSONObject resultListObject = (JSONObject) cuisineMenuArray.get(i);
				if(resultListObject==null) continue;
				
				FDMenu menu = new FDMenu();
				if (resultListObject.has("restaurantCuisine") && !resultListObject.get("restaurantCuisine").equals(null) && resultListObject.get("restaurantCuisine") != null) {
					JSONObject restaurantCuisine = (JSONObject) resultListObject.getJSONObject("restaurantCuisine");
					if(restaurantCuisine!=null) {
						menu.setId(restaurantCuisine.getInt("id"));
						menu.setName(restaurantCuisine.getString("name"));
						menu.setDescription(restaurantCuisine.getString("description"));
						
						float amountMain = restaurantCuisine.getInt("amountMain");
						String materialMain = restaurantCuisine.getString("materialMain");
						
						ArrayList<FDIngredientGroup> ingredientsGroupList = new ArrayList<FDIngredientGroup>();
						if(materialMain!=null) {
							String[] materialArray = materialMain.split(",");
							if(materialArray!=null) {
								FDIngredientGroup ingredientGroup = new FDIngredientGroup();
								ArrayList<FDIngredients> ingredientsList = new ArrayList<FDIngredients>();
								for(int n=0;n<materialArray.length;n++) {
									FDIngredients ingredients = new FDIngredients();
									ingredients.setName(materialArray[n]);
									ingredients.setWeight(amountMain);
									ingredientsList.add(ingredients);
								}
								ingredientGroup.setIngredients(ingredientsList);
								ingredientsGroupList.add(ingredientGroup);
							}
						}
						String materialAuxiliary = restaurantCuisine.getString("materialAuxiliary");
						float amountAuxiliary = restaurantCuisine.getInt("amountAuxiliary");
						if(materialAuxiliary!=null) {
							String[] auxiliaryArray = materialAuxiliary.split(",");
							FDIngredientGroup auxiliaryGroup = new FDIngredientGroup();
							ArrayList<FDIngredients> ingredientsList = new ArrayList<FDIngredients>();
							for(int m=0;m<auxiliaryArray.length;m++) {
								FDIngredients ingredients = new FDIngredients();
								ingredients.setName(auxiliaryArray[m]);
								ingredients.setWeight(amountAuxiliary);
								ingredientsList.add(ingredients);
							}
							auxiliaryGroup.setIngredients(ingredientsList);
							ingredientsGroupList.add(auxiliaryGroup);
						}
						menu.setIngredientGroupList(ingredientsGroupList);
					}
				}
				if (resultListObject.has("attachQueryResult")&& !resultListObject.get("attachQueryResult").equals(null)&& resultListObject.get("attachQueryResult") != null) {
					JSONObject attachQueryResult = (JSONObject) resultListObject.getJSONObject("attachQueryResult");
					if (attachQueryResult.has("resultList")&& !attachQueryResult.get("resultList").equals(null) && attachQueryResult.get("resultList") != null) {
						ArrayList<FDImage> imageList = new ArrayList<FDImage>();
						JSONArray resultList = attachQueryResult.getJSONArray("resultList");
						for (int j = 0; j < resultList.length(); j++) {
							FDImage image = new FDImage();
							JSONObject picObject = (JSONObject) resultList.get(j);
							if (picObject.get("id") != null) {
								image.setImageId(picObject.getString("id"));
							} 
							if (picObject.get("objId") != null) {
								image.setObjId(picObject.getString("objId"));
							} 
							if (picObject.get("filePath") != null) {
								image.setFilePath(picObject.getString("filePath"));
							} 
							if (picObject.get("uploadTime") != null) {
								image.setUploadTime(picObject.getString("uploadTime"));
							} 
							if (picObject.get("description") != null) {
								image.setDescription(picObject.getString("description"));
							} 
							if (picObject.get("name") != null) {
								image.setName(picObject.getString("name"));
							} 
							imageList.add(image);
						}
						menu.setMenuImages(imageList);
					}
				}
				menuList.add(menu);
			}
		}
		return menuList;
	}
		
	// 获取餐厅列表详情
	public static FDRestaurant getRestaurantDetail(String restaurantJson) throws JSONException {
		if (StringUtils.isEmpty(restaurantJson)) return null;
		
		ArrayList<FDImage> imageList=new ArrayList<FDImage>();
		FDRestaurant restaurantDeatil = new FDRestaurant();
		JSONObject restaurantObject = new JSONObject(restaurantJson);
		if(restaurantObject==null) return null;
		
		if(restaurantObject.has("RestaurantImages")&&!restaurantObject.get("RestaurantImages").equals(null)&&restaurantObject.get("RestaurantImages")!=null){
			JSONObject restaurantImages=restaurantObject.getJSONObject("RestaurantImages");
			int number = restaurantImages.getInt("totalRecord");
			JSONArray array = restaurantImages.getJSONArray("resultList");
			if(array!=null && array.length()>0){				
				for (int i = 0; i < number; i++) {
					FDImage image = new FDImage();
					JSONObject picObject = (JSONObject) array.get(i);
					if (picObject.get("name") != null) {
						image.setName(picObject.getString("name"));
					} 
					if (picObject.get("id") != null) {
						image.setImageId(picObject.getString("id"));
					} 
					if (picObject.get("objId") != null) {
						image.setObjId(picObject.getString("objId"));
					} 
					if (picObject.get("filePath") != null) {
						image.setFilePath(picObject.getString("filePath"));
					} 
					if (picObject.get("uploadTime") != null) {
						image.setUploadTime(picObject.getString("uploadTime"));
					} 
					if (picObject.get("description") != null) {
						image.setDescription(picObject.getString("description"));
					} 
					imageList.add(image);
					restaurantDeatil.setImageList(imageList);
				}
			}
		}
		restaurantDeatil.setIsRecommended(restaurantObject.optInt("isRecommended"));
		restaurantDeatil.setIsCollected(restaurantObject.optInt("isCollected"));
		restaurantDeatil.setCountOfCollect(restaurantObject.optInt("countOfCollect"));	
		restaurantDeatil.setCountOfRecommended(restaurantObject.optInt("countOfRecommended"));	
		restaurantDeatil.setRecommendedCuisines(restaurantObject.optString("recommendedCuisines"));
		restaurantDeatil.setCouponInfoTitle(restaurantObject.optString("discountTitle"));
		restaurantDeatil.setCouponInfoData(restaurantObject.optString("discountDesc"));
		
		JSONObject restaurantData = restaurantObject.getJSONObject("data");
		System.out.println("restaurantData-----"+restaurantData.toString());
		if(restaurantData!=null) {
			restaurantDeatil.setId(restaurantData.getInt("id"));
			restaurantDeatil.setType(restaurantData.getInt("type"));
			restaurantDeatil.setTypeValue(restaurantData.getString("typeValue"));
			restaurantDeatil.setName(restaurantData.getString("name"));
			restaurantDeatil.setShopSign(restaurantData.optString("shopSign"));
			restaurantDeatil.setRestaurantNameAbbrev(restaurantData.optString("restaurantNameAbbrev"));
			restaurantDeatil.setBizRegNumber(restaurantData.getString("bizRegNumber"));
			restaurantDeatil.setCateringCert(restaurantData.getString("cateringCert"));
			restaurantDeatil.setLegalPerson(restaurantData.getString("legalPerson"));
			restaurantDeatil.setFoodSafetyOffical(restaurantData.getString("foodSafetyOfficial"));
			restaurantDeatil.setRegisteredAddress(restaurantData.getString("registeredAddress"));
			restaurantDeatil.setPostalCode(restaurantData.getString("postalCode"));
			restaurantDeatil.setBizScope(restaurantData.getString("bizScope"));
			restaurantDeatil.setStatus(restaurantData.optInt("status", 0));
			restaurantDeatil.setStatusValue(restaurantData.optString("statusValue",""));
			restaurantDeatil.setCommercialCenter(restaurantData.optInt("commercialCenter",0));
			restaurantDeatil.setCommercialCenterValue(restaurantData.getString("commercialCenterValue"));
			restaurantDeatil.setBizAddress(restaurantData.getString("bizAddress"));
			restaurantDeatil.setTransportation(restaurantData.getString("transportation"));
			restaurantDeatil.setGisLatitude(restaurantData.optDouble("gisLatitude",FDSearchFragment.latitude));
			restaurantDeatil.setGisLongitude(restaurantData.optDouble("gisLongitude",FDSearchFragment.longitude));
			restaurantDeatil.setSeats(restaurantData.optInt("seats",0));
			restaurantDeatil.setSeatsValue(restaurantData.optString("seatsValue",""));
			restaurantDeatil.setAtmosphere(restaurantData.optString("atmosphere",""));
			restaurantDeatil.setFeatures(restaurantData.optString("features",""));
			restaurantDeatil.setAverageComsumption(restaurantData.optInt("averageComsumption"));
			restaurantDeatil.setAverageComsumptionValue(restaurantData.getString("averageComsumptionValue"));
			restaurantDeatil.setTelephone(restaurantData.getString("telephone"));
			restaurantDeatil.setComplaintPhone(restaurantData.getString("complaintPhone"));
			restaurantDeatil.setQrCode(restaurantData.getString("qrCode"));
			restaurantDeatil.setAccountStatus(restaurantData.optInt("accountStatus"));
			restaurantDeatil.setFoodSaftyRating(restaurantData.optInt("foodSaftyRating"));
			restaurantDeatil.setFoodSaftyRatingValue(restaurantData.getString("foodSaftyRatingValue"));
			restaurantDeatil.setIsManagedInSys(restaurantData.optInt("isManagedInSys",0));
			restaurantDeatil.setRegisteredAreaId(restaurantData.optInt("registeredAreaId"));
			restaurantDeatil.setSupervisionOrgId(restaurantData.optInt("supervisionOrgId"));
			restaurantDeatil.setParkingSpaces(restaurantData.optInt("parkingSpaces",0));
			restaurantDeatil.setParkingSpacesValue(restaurantData.getString("parkingSpacesValue"));
			restaurantDeatil.setCateringType(restaurantData.optInt("cateringType"));
			restaurantDeatil.setCateringTypeValue(restaurantData.getString("cateringTypeValue"));
			restaurantDeatil.setHonorQualification(restaurantData.getString("honorQualification"));
			restaurantDeatil.setFoodSaftyRatingDate(restaurantData.getString("foodSaftyRatingDate"));	
			//			restaurantDeatil...restaurantData.
		}
		return restaurantDeatil;
	}
	
	// 获取优惠信息详情
	public static FDCoupon getCouponDetail(String couponJson) throws JSONException {
		
		return null;
	}
	
	// 解析商圈信息
	public static ArrayList<FDCommerialCenter> getCommerialFromJson(String json) throws JSONException{
		if(StringUtils.isEmpty(json)) return null;
		if(!(JSONValue.parse(json) instanceof Map)) return null;
		
		Map<String,Object> jsonMap = (Map<String,Object>) JSONValue.parse(json);
		if(jsonMap==null) return null;
		
		ArrayList<FDCommerialCenter> commerialList = new ArrayList<FDCommerialCenter>();
		for(Map.Entry<String, Object> entry:jsonMap.entrySet()){    
		     if(entry==null) continue;
		     if(entry.getValue()!=null) {
		    	 JSONObject commerialValue = new JSONObject(entry.getValue().toString());
		    	 JSONObject communities = commerialValue.getJSONObject("childs");
//		    	 if(communities==null) continue;
		    	 
		    	 if(communities!=null && communities.length()>0) {
		    		 FDCommerialCenter commerialCenter = new FDCommerialCenter();
			    	 commerialCenter.setCommerialCode(entry.getKey());
			    	 commerialCenter.setCommerialName(commerialValue.getString("name"));
			    	 
			    	 ArrayList<FDCommerialCenter> someCommerialCenter = new ArrayList<FDCommerialCenter>();
			    	 if(communities.length()>0) {
			    		 Map<String,Object> secondMap = (Map<String,Object>) JSONValue.parse(communities.toString());
			    		 if(secondMap!=null) {
			    			 for(Map.Entry<String, Object> secondEntry:secondMap.entrySet()){    
			    			     if(secondEntry==null) continue;
			    			     if(secondEntry.getValue()!=null) {
			    			    	 JSONObject secondValue = new JSONObject(secondEntry.getValue().toString());
			    			    	 JSONObject secondCommunities = secondValue.getJSONObject("childs");
			    			    	 
			    			    	 if(secondCommunities!=null) {
			    			    		 FDCommerialCenter childCommerial = new FDCommerialCenter();
			    			    		 childCommerial.setCommerialCode(secondEntry.getKey());
			    			    		 childCommerial.setCommerialName(secondValue.getString("name"));
			    			    		 someCommerialCenter.add(childCommerial);
			    			    	 }
			    			     }
			    			 }
			    			 Collections.sort(someCommerialCenter, FDBaseWrapper.cn_commerial_comparator); 
			    			 commerialCenter.setCommunities(someCommerialCenter);
			    		 }
			    	 }
			    	 commerialList.add(commerialCenter);
		    	 }
		     }
		}
		Collections.sort(commerialList, FDBaseWrapper.cn_commerial_comparator);    // 按中文字母顺序排列
		ServiceManager.putStringValue(FDConst.FD_MAIN_HASHMAP_COMMERIAL_CENTER, commerialList);
		
		return commerialList;   
	}
	
	protected static void getCuisineTypeFromJson(String cuisineJson) throws JSONException {
		if(StringUtils.isEmpty(cuisineJson)) return;
		ArrayList<FDCuisine> cuisineList = new ArrayList<FDCuisine>();
		if(JSONValue.parse(cuisineJson) instanceof Map) {
			Map<String,Object> jsonMap = (Map<String,Object>)JSONValue.parse(cuisineJson);
			if(jsonMap!=null) {
				for(Map.Entry<String, Object> entry:jsonMap.entrySet()){
					if(entry==null) continue;
					FDCuisine cuisine = new FDCuisine();
					cuisine.setCuisine(entry.getKey());
					cuisine.setCuisineValue(entry.getValue().toString());
					cuisineList.add(cuisine);
				}
			}
		}
		Collections.sort(cuisineList, FDBaseWrapper.cn_cuisine_comparator);    // 按中文字母顺序排列
		ServiceManager.putStringValue(FDConst.FD_MAIN_HASHMAP_CUISINE_TYPE, cuisineList);
	}

	// 餐厅氛围
	protected static void getAtmosphereFromJson(String atmosphereJson) throws JSONException {
		if(StringUtils.isEmpty(atmosphereJson)) return;
		
		ArrayList<FDAtmosphere> atmosphereList = new ArrayList<FDAtmosphere>();
		if(JSONValue.parse(atmosphereJson) instanceof Map) {
			Map<String,Object> jsonMap = (Map<String,Object>)JSONValue.parse(atmosphereJson);
			if(jsonMap!=null) {
				for(Map.Entry<String, Object> entry:jsonMap.entrySet()){
					if(entry==null) continue;
					FDAtmosphere atmosphere = new FDAtmosphere();
					atmosphere.setAtmosphereCode(entry.getKey());
					atmosphere.setAtmosphereName(entry.getValue().toString());
					atmosphereList.add(atmosphere);
				}
			}
		}
		ServiceManager.putStringValue(FDConst.FD_MAIN_HASHMAP_ATMOSPHERE_LIST, atmosphereList);
	}

	// 信用等级
	protected static void getCreditFromJson(String creditJson) throws JSONException {
		if(StringUtils.isEmpty(creditJson)) return;
		ArrayList<FDCredit> creditList = new ArrayList<FDCredit>();
		if(JSONValue.parse(creditJson) instanceof Map) {
			Map<String,Object> jsonMap = (Map<String,Object>)JSONValue.parse(creditJson);
			if(jsonMap!=null) {
				for(Map.Entry<String, Object> entry:jsonMap.entrySet()){
					if(entry==null) continue;
					FDCredit credit = new FDCredit();
					credit.setCreditCode(entry.getKey());
					credit.setCreditName(entry.getValue().toString());
					creditList.add(credit);
				}
			}
		}
		ServiceManager.putStringValue(FDConst.FD_MAIN_HASHMAP_CREDIT_LIST, creditList);
	}
	
	// 获取菜肴类型
	public static void getVegetableTpey(String vegetableJson) throws JSONException {
		if(StringUtils.isEmpty(vegetableJson)) return;
		
		List<FDSuperMode> vegetableList = new ArrayList<FDSuperMode>();
		Object vegetableObject = JSONValue.parse(vegetableJson);
		if(vegetableObject instanceof Map) {
			Map<String,Object> jsonMap = (Map<String,Object>)JSONValue.parse(vegetableJson);
			if(jsonMap!=null) {
				for(Map.Entry<String, Object> entry:jsonMap.entrySet()){
					if(entry==null) continue;
					FDSuperMode vegetable = new FDSuperMode();
					vegetable.setCode(entry.getKey());
					vegetable.setName(entry.getValue().toString());
					vegetableList.add(vegetable);
				}
			}
		}
		Collections.sort(vegetableList, FDBaseWrapper.cn_vegetable_comparator);
		ServiceManager.putStringValue(FDConst.FD_MAIN_HASHMAP_VEGETABLE_TYPE, vegetableList);
	}
	
	public static void getAverageConsumption(String averageConsumJson) throws JSONException {
		if(StringUtils.isEmpty(averageConsumJson)) return;
		
		ArrayList<FDSuperMode> list = new ArrayList<FDSuperMode>();
		String[] keyArray = JsonAnalyzeKeyAndValue.analyzeJsonToArray(averageConsumJson, "key");
		String[] valueArray = JsonAnalyzeKeyAndValue.analyzeJsonToArray(averageConsumJson, "value");
		if(keyArray!=null) {
			for (int i = 0; i < keyArray.length; i++) {
				FDSuperMode averageConsum=new FDSuperMode();
				averageConsum.setCode(keyArray[i]);
				averageConsum.setName(valueArray[i]);
				list.add(averageConsum);
			}
		}
		ServiceManager.putStringValue(FDConst.FD_MAIN_HASHMAP_AVERAGE_CONSUM, list);
	}
		
	public static void loadBasicInformation(String commercial, String atmosphere, String cuisine, String creditRating, String vegetable, String averageConsum) throws JSONException {
		getCommerialFromJson(commercial);
		getAtmosphereFromJson(atmosphere);
		getCuisineTypeFromJson(cuisine);
		getCreditFromJson(creditRating);
		getVegetableTpey(vegetable);
		getAverageConsumption(averageConsum);
	}
	
	public interface OnLoadedCallbackListener {
		public void onSuccessCallback();
	}
}
