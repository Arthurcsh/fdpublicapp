package com.wondersgroup.fdpublicapp.search.mode;

import org.json.JSONException;
import org.json.JSONObject;

import com.wondersgroup.fdpublicapp.common.service.FDConst;

import android.os.Parcel;
import android.os.Parcelable;

public class FDCondition implements Parcelable{

	private FDSuperMode keyword;
	private FDCommerialCenter commerial;
	private FDCuisine cuisine;
	private FDCredit credit;
	private FDAtmosphere atmosphere;
	private FDSuperMode distance;
	private String otherConditions;
	
	public FDSuperMode getKeyword() {
		return keyword;
	}
	public void setKeyword(FDSuperMode keyword) {
		this.keyword = keyword;
	}
	public FDCommerialCenter getCommerial() {
		return commerial;
	}
	public void setCommerial(FDCommerialCenter commerial) {
		this.commerial = commerial;
	}
	public FDCuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(FDCuisine cuisine) {
		this.cuisine = cuisine;
	}
	public FDCredit getCredit() {
		return credit;
	}
	public void setCredit(FDCredit credit) {
		this.credit = credit;
	}
	public FDAtmosphere getAtmosphere() {
		return atmosphere;
	}
	public void setAtmosphere(FDAtmosphere atmosphere) {
		this.atmosphere = atmosphere;
	}
	public FDSuperMode getDistance() {
		return distance;
	}
	public void setDistance(FDSuperMode distance) {
		this.distance = distance;
	}
	
	public String getOtherConditions() {
		return otherConditions;
	}
	public void setOtherConditions(String otherConditions) {
		this.otherConditions = otherConditions;
	}

	// 综合条件
	public String getQueryConditions() {
		JSONObject queryConditions = new JSONObject();
		try {
			if(keyword!=null) {
				queryConditions.put(FDConst.FD_QUERY_CONDITION_KEYNAME, keyword.getName());
			}
			if(commerial!=null) {
				queryConditions.put(FDConst.FD_QUERY_CONDITION_AREA, commerial.getCommerialCode());
			}
			if(cuisine!=null) {
				queryConditions.put(FDConst.FD_QUERY_CONDITION_CUISINE, cuisine.getCuisine());
			}
			if(credit!=null) {
				queryConditions.put(FDConst.FD_QUERY_CONDITION_CREDIT, credit.getCreditCode());
			}
			if(atmosphere!=null) {
				queryConditions.put(FDConst.FD_QUERY_CONDITION_ATMOSPHERE, atmosphere.getAtmosphereCode());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return queryConditions.toString();
	}

	public static final Parcelable.Creator<FDCondition> CREATOR = new Creator<FDCondition>() {  
        public FDCondition createFromParcel(Parcel source) {  
        	FDCondition condition = new FDCondition();  
        	condition.keyword = source.readParcelable(FDSuperMode.class.getClassLoader());
        	condition.commerial = source.readParcelable(FDCommerialCenter.class.getClassLoader());
        	condition.cuisine = source.readParcelable(FDCuisine.class.getClassLoader());
        	condition.credit = source.readParcelable(FDCredit.class.getClassLoader());
        	condition.atmosphere = source.readParcelable(FDAtmosphere.class.getClassLoader());
        	condition.distance = source.readParcelable(FDSuperMode.class.getClassLoader());
        	condition.otherConditions = source.readString();
        	
            return condition;  
        }  
        
        public FDCondition[] newArray(int size) {  
            return new FDCondition[size];  
        }  
    };  
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(keyword, flags);
		dest.writeParcelable(commerial, flags);
		dest.writeParcelable(cuisine, flags);
		dest.writeParcelable(credit, flags);
		dest.writeParcelable(atmosphere, flags);
		dest.writeParcelable(distance, flags);
		dest.writeString(otherConditions);
	}
	
}
