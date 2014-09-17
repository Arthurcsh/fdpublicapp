package com.wondersgroup.fdpublicapp.search.mode;

import java.util.ArrayList;


import android.os.Parcel;
import android.os.Parcelable;

public class FDCommerialCenter extends FDSuperMode implements Parcelable{

	private String commerialName;
	private String commerialCode;
	private ArrayList<FDCommerialCenter> communities;
	
	public String getCommerialName() {
		return commerialName;
	}
	public void setCommerialName(String commerialName) {
		this.commerialName = commerialName;
	}
	public String getCommerialCode() {
		return commerialCode;
	}
	public void setCommerialCode(String commerialCode) {
		this.commerialCode = commerialCode;
	}
	public ArrayList<FDCommerialCenter> getCommunities() {
		return communities;
	}
	public void setCommunities(ArrayList<FDCommerialCenter> communities) {
		this.communities = communities;
	}
	
	public static final Parcelable.Creator<FDCommerialCenter> CREATOR = new Creator<FDCommerialCenter>() {  
        public FDCommerialCenter createFromParcel(Parcel source) {  
        	FDCommerialCenter commerial = new FDCommerialCenter();  
        	commerial.commerialCode = source.readString();
        	commerial.commerialName = source.readString();
        	commerial.communities = source.readArrayList(FDCommerialCenter.class.getClassLoader());
        	
            return commerial;  
        }  
        
        public FDCommerialCenter[] newArray(int size) {  
            return new FDCommerialCenter[size];  
        }  
    };  
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(commerialCode);
		dest.writeString(commerialName);
		dest.writeList(communities);
	}
	
	
}
