package com.wondersgroup.fdpublicapp.search.mode;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 餐厅菜单
 * @author chengshaohua
 *
 */
public class FDRestMenu implements Parcelable {
	private int id;
	private int cusineType;
	private String name;
	private String cusineTypeValue;
	private int cuisineCustomTypeId;
	private String cuisineCustomTypeName;
	private String description;
	private String mainMat;
	private String subMat;
	private int recommendedCount;
	private List<FDImage> picList;
	private String outputDate;               // 台账记录时间
	private String outputMatId;              // 菜单产出品id
	private int menuType;                    // 菜单类型   1=营养餐，0=餐厅菜肴

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCusineType() {
		return cusineType;
	}
	public void setCusineType(int cusineType) {
		this.cusineType = cusineType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getRecommendedCount() {
		return recommendedCount;
	}
	public void setRecommendedCount(int recommendedCount) {
		this.recommendedCount = recommendedCount;
	}
	public List<FDImage> getPicList() {
		return picList;
	}
	public void setPicList(List<FDImage> picList) {
		this.picList = picList;
	}
	public String getOutputDate() {
		return outputDate;
	}
	public void setOutputDate(String outputDate) {
		this.outputDate = outputDate;
	}
	public String getOutputMatId() {
		return outputMatId;
	}
	public void setOutputMatId(String outputMatId) {
		this.outputMatId = outputMatId;
	}
	public int getMenuType() {
		return menuType;
	}
	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}
	
	public static final Parcelable.Creator<FDRestMenu> CREATOR = new Creator<FDRestMenu>() {  
        public FDRestMenu createFromParcel(Parcel source) {  
        	FDRestMenu menu = new FDRestMenu();  
        	menu.id = source.readInt(); 
        	menu.cusineType = source.readInt(); 
        	menu.name = source.readString();
        	menu.cusineTypeValue = source.readString();
        	menu.cuisineCustomTypeId = source.readInt();
        	menu.cuisineCustomTypeName = source.readString();
        	menu.description = source.readString();
        	menu.mainMat = source.readString();
        	menu.subMat = source.readString();
        	menu.recommendedCount = source.readInt();
        	menu.picList = source.readArrayList(FDImage.class.getClassLoader());
        	menu.outputDate = source.readString();
        	menu.outputMatId = source.readString();
        	menu.menuType = source.readInt();
        	
            return menu;  
        }  
        public FDRestMenu[] newArray(int size) {  
            return new FDRestMenu[size];  
        }  
    }; 
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(cusineType);
		dest.writeString(name);
		dest.writeString(cusineTypeValue);
		dest.writeInt(cuisineCustomTypeId);
		dest.writeString(cuisineCustomTypeName);
		dest.writeString(description);
		dest.writeString(mainMat);
		dest.writeString(subMat);
		dest.writeInt(recommendedCount);
		dest.writeList(picList);
		dest.writeString(outputDate);
		dest.writeString(outputMatId);
		dest.writeInt(menuType);
	}
}
