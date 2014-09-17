package com.wondersgroup.fdpublicapp.search.mode;


import android.os.Parcel;
import android.os.Parcelable;

public class FDCuisine extends FDSuperMode implements Parcelable{

	private int index;
	private String cuisine;
	private String cuisineValue;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getCuisineValue() {
		return cuisineValue;
	}
	public void setCuisineValue(String cuisineValue) {
		this.cuisineValue = cuisineValue;
	}


	public static final Parcelable.Creator<FDCuisine> CREATOR = new Creator<FDCuisine>() {  
        public FDCuisine createFromParcel(Parcel source) {  
        	FDCuisine cuisine = new FDCuisine();  
        	cuisine.cuisine = source.readString();
        	cuisine.cuisineValue = source.readString();
        	
            return cuisine;  
        }  
        
        public FDCuisine[] newArray(int size) {  
            return new FDCuisine[size];  
        }  
    };  
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(cuisine);
		dest.writeString(cuisineValue);
	}
	
	
}
