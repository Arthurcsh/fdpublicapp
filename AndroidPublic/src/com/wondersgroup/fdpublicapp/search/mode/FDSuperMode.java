package com.wondersgroup.fdpublicapp.search.mode;

import android.os.Parcel;
import android.os.Parcelable;

public class FDSuperMode implements Parcelable{

	private int id;
	private int type;
	private String code;
	private String name;
	private String sortBy;
	private String direction;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	
	public void setSortBy(String sort) {
		this.sortBy = sort;
	}
	public String getSortBy() {
		return sortBy;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public static final Parcelable.Creator<FDSuperMode> CREATOR = new Creator<FDSuperMode>() {  
        public FDSuperMode createFromParcel(Parcel source) {  
        	FDSuperMode superMode = new FDSuperMode();  
        	superMode.code = source.readString();
        	superMode.name = source.readString();
        	superMode.sortBy = source.readString();
        	superMode.direction = source.readString();
        	
            return superMode;  
        }  
        
        public FDSuperMode[] newArray(int size) {  
            return new FDSuperMode[size];  
        }  
    };
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(code);
		dest.writeString(name);
		dest.writeString(sortBy);
		dest.writeString(direction);
	}
}
