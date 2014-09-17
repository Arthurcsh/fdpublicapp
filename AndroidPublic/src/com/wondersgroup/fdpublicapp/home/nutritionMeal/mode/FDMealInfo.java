package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.ArrayList;
import android.os.Parcel;
import android.os.Parcelable;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 *  餐次详细信息
 * @author chengshaohua
 *
 */
public class FDMealInfo implements Parcelable {

	private FDMealItem stuMealInfo;
	private ArrayList<FDImage> picList;
	private ArrayList<FDNutrition> nutritionList;
	
	public FDMealItem getStuMealInfo() {
		return stuMealInfo;
	}
	public void setStuMealInfo(FDMealItem stuMealInfo) {
		this.stuMealInfo = stuMealInfo;
	}
	public ArrayList<FDImage> getPicList() {
		return picList;
	}
	public void setPicList(ArrayList<FDImage> picList) {
		this.picList = picList;
	}
	
	public ArrayList<FDNutrition> getNutritionList() {
		return nutritionList;
	}
	public void setNutritionList(ArrayList<FDNutrition> nutritionList) {
		this.nutritionList = nutritionList;
	}

	public static final Parcelable.Creator<FDMealInfo> CREATOR = new Creator<FDMealInfo>() {  
        public FDMealInfo createFromParcel(Parcel source) {  
        	FDMealInfo mealInfo = new FDMealInfo();  
        	mealInfo.stuMealInfo = source.readParcelable(FDMealItem.class.getClassLoader()); 
        	mealInfo.picList = source.readArrayList(FDImage.class.getClassLoader());
        	mealInfo.nutritionList = source.readArrayList(FDNutrition.class.getClassLoader());
        	
            return mealInfo;  
        }  
        public FDMealInfo[] newArray(int size) {  
            return new FDMealInfo[size];  
        }  
    };
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(stuMealInfo, flags);
		dest.writeList(picList);
		dest.writeList(nutritionList);
	}
	
	
}
