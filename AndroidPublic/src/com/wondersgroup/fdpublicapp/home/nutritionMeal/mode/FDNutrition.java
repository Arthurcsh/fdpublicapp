package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import android.os.Parcel;
import android.os.Parcelable;


/**
 *  营养成分
 * @author chengshaohua
 *
 */
public class FDNutrition implements Parcelable{

	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public static final Parcelable.Creator<FDNutrition> CREATOR = new Creator<FDNutrition>() {  
        public FDNutrition createFromParcel(Parcel source) {  
        	FDNutrition mealNutrition = new FDNutrition();  
        	mealNutrition.key = source.readString();
        	mealNutrition.value = source.readString();
        	
            return mealNutrition;  
        }  
        public FDNutrition[] newArray(int size) {  
            return new FDNutrition[size];  
        }  
    };
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(key);
		dest.writeString(value);
	}
	
}
