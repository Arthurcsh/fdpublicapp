package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.ArrayList;
import java.util.Date;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  营养餐次(菜肴)信息
 * @author chengshaohua
 *
 */
public class FDMealItem implements Parcelable {
    private int id;
    private int type;
    private String typeValue;
    private String name;
    private String description;
    private String companyId;
    private String companyName;
    private String restaurantNameAbbrev;
    private Date date;
    private Date createDate;
//    private ArrayList<FDSchool> stuMealSchoolList;
	private ArrayList<FDOutputMat> dtoStuMealOutputMatList; 
	private ArrayList<FDImage> picList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRestaurantNameAbbrev() {
		return restaurantNameAbbrev;
	}
	public void setRestaurantNameAbbrev(String restaurantNameAbbrev) {
		this.restaurantNameAbbrev = restaurantNameAbbrev;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
//	public ArrayList<FDSchool> getStuMealSchoolList() {
//		return stuMealSchoolList;
//	}
//	public void setStuMealSchoolList(ArrayList<FDSchool> stuMealSchoolList) {
//		this.stuMealSchoolList = stuMealSchoolList;
//	}
	public ArrayList<FDOutputMat> getDtoStuMealOutputMatList() {
		return dtoStuMealOutputMatList;
	}
	public void setDtoStuMealOutputMatList(
			ArrayList<FDOutputMat> dtoStuMealOutputMatList) {
		this.dtoStuMealOutputMatList = dtoStuMealOutputMatList;
	}
	
	public ArrayList<FDImage> getPicList() {
		return picList;
	}
	public void setPicList(ArrayList<FDImage> picList) {
		this.picList = picList;
	}

	/**
	 *  主材
	 * @return
	 */
	public String getMainIngredients() {
		if(dtoStuMealOutputMatList==null) return "";
		StringBuilder builder = new StringBuilder();
		for(FDOutputMat outputMat : dtoStuMealOutputMatList) {
			if(outputMat==null) continue;
			builder.append(outputMat.getMainMat());
		}
		return builder.toString();
	}

	public String getOutputMatId() {
		if(dtoStuMealOutputMatList==null) return null;
		StringBuilder builder = new StringBuilder();
		for(FDOutputMat outputMat : dtoStuMealOutputMatList) {
			if(outputMat==null) continue;
			builder.append(outputMat.getId()+",");
		}
		
		if(StringUtils.isEmpty(builder.toString())) return null;
		
		builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}
	
	public static final Parcelable.Creator<FDMealItem> CREATOR = new Creator<FDMealItem>() {  
        public FDMealItem createFromParcel(Parcel source) {  
        	FDMealItem nutirtionMealInfo = new FDMealItem();  
        	nutirtionMealInfo.id = source.readInt(); 
        	nutirtionMealInfo.type = source.readInt();
        	nutirtionMealInfo.typeValue = source.readString();
        	nutirtionMealInfo.name = source.readString();
        	nutirtionMealInfo.description = source.readString();
        	nutirtionMealInfo.companyId = source.readString();
        	nutirtionMealInfo.companyName = source.readString();
        	nutirtionMealInfo.restaurantNameAbbrev = source.readString();
        	nutirtionMealInfo.date = new Date(source.readLong());
        	nutirtionMealInfo.createDate = new Date(source.readLong());
        	nutirtionMealInfo.dtoStuMealOutputMatList = source.readArrayList(FDOutputMat.class.getClassLoader());
        	nutirtionMealInfo.picList = source.readArrayList(FDImage.class.getClassLoader());
        	
            return nutirtionMealInfo;  
        }  
        public FDMealItem[] newArray(int size) {  
            return new FDMealItem[size];  
        }  
    };
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(type);
		dest.writeString(typeValue);
		dest.writeString(name);
		dest.writeString(description);
		dest.writeString(companyId);
		dest.writeString(companyName);
		dest.writeString(restaurantNameAbbrev);
		dest.writeLong(date.getTime());
		dest.writeLong(createDate.getTime());
		dest.writeList(dtoStuMealOutputMatList);
		dest.writeList(picList);
	}
}
