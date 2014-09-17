package com.wondersgroup.fdpublicapp.personal.mode;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 菜肴
 * @author chengshaohua
 *
 */
public class FDCuisineDetail implements Parcelable {
	private int id;
	private String name;
	private int cusineType;
	private String cusineTypeValue;
	private int cuisineCustomTypeId;
	private String cuisineCustomTypeName;
	private String description;
	private String mainMat;
	private String subMat;
	private int isRecommended;
	private int isCollected;
	private int countOfRecommended;
	private int countOfCollected;
	private int restaurantId;
	private String restaurantName;
	private String restaurantNameAbbrev;     // 店招
	private List<FDImage> picList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCusineType() {
		return cusineType;
	}
	public void setCusineType(int cusineType) {
		this.cusineType = cusineType;
	}
	public String getCusineTypeValue() {
		return cusineTypeValue;
	}
	public void setCusineTypeValue(String cusineTypeValue) {
		this.cusineTypeValue = cusineTypeValue;
	}
	public int getCuisineCustomTypeId() {
		return cuisineCustomTypeId;
	}
	public void setCuisineCustomTypeId(int cuisineCustomTypeId) {
		this.cuisineCustomTypeId = cuisineCustomTypeId;
	}
	public String getCuisineCustomTypeName() {
		return cuisineCustomTypeName;
	}
	public void setCuisineCustomTypeName(String cuisineCustomTypeName) {
		this.cuisineCustomTypeName = cuisineCustomTypeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMainMat() {
		return mainMat;
	}
	public void setMainMat(String mainMat) {
		this.mainMat = mainMat;
	}
	public String getSubMat() {
		return subMat;
	}
	public void setSubMat(String subMat) {
		this.subMat = subMat;
	}
	public int getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(int isRecommended) {
		this.isRecommended = isRecommended;
	}
	public int getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(int isCollected) {
		this.isCollected = isCollected;
	}
	public int getCountOfRecommended() {
		return countOfRecommended;
	}
	public void setCountOfRecommended(int countOfRecommended) {
		this.countOfRecommended = countOfRecommended;
	}
	public int getCountOfCollected() {
		return countOfCollected;
	}
	public void setCountOfCollected(int countOfCollected) {
		this.countOfCollected = countOfCollected;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantNameAbbrev() {
		return restaurantNameAbbrev;
	}
	public void setRestaurantNameAbbrev(String restaurantNameAbbrev) {
		this.restaurantNameAbbrev = restaurantNameAbbrev;
	}
	public List<FDImage> getPicList() {
		return picList;
	}
	public void setPicList(List<FDImage> picList) {
		this.picList = picList;
	}
	
	public static final Parcelable.Creator<FDCuisineDetail> CREATOR = new Creator<FDCuisineDetail>() {  
        public FDCuisineDetail createFromParcel(Parcel source) {  
        	FDCuisineDetail cuisineDetail = new FDCuisineDetail();  
        	cuisineDetail.id = source.readInt();
        	cuisineDetail.name = source.readString();
        	cuisineDetail.cusineType = source.readInt();
        	cuisineDetail.cusineTypeValue = source.readString();
        	cuisineDetail.cuisineCustomTypeId = source.readInt();
        	cuisineDetail.cuisineCustomTypeName = source.readString();
        	cuisineDetail.description = source.readString();
        	cuisineDetail.mainMat = source.readString();
        	cuisineDetail.subMat = source.readString();
        	cuisineDetail.isRecommended = source.readInt();
        	cuisineDetail.isCollected = source.readInt();
        	cuisineDetail.countOfRecommended = source.readInt();
        	cuisineDetail.countOfCollected = source.readInt();
        	cuisineDetail.restaurantId = source.readInt();
        	cuisineDetail.restaurantName = source.readString();
        	cuisineDetail.restaurantNameAbbrev = source.readString();
        	cuisineDetail.picList = source.readArrayList(FDImage.class.getClassLoader());
        	
            return cuisineDetail;  
        }  
        
        public FDCuisineDetail[] newArray(int size) {  
            return new FDCuisineDetail[size];  
        }  
    };  
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeInt(cusineType);
		dest.writeString(cusineTypeValue);
		dest.writeInt(cuisineCustomTypeId);
		dest.writeString(cuisineCustomTypeName);
		dest.writeString(description);
		dest.writeString(mainMat);
		dest.writeString(subMat);
		dest.writeInt(isRecommended);
		dest.writeInt(isCollected);
		dest.writeInt(countOfRecommended);
		dest.writeInt(countOfCollected);
		dest.writeInt(restaurantId);
		dest.writeString(restaurantName);
		dest.writeString(restaurantNameAbbrev);
		dest.writeList(picList);
	}
	
}
