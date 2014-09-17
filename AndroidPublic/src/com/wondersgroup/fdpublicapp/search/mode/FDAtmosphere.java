package com.wondersgroup.fdpublicapp.search.mode;


import android.os.Parcel;
import android.os.Parcelable;

public class FDAtmosphere extends FDSuperMode implements Parcelable{

	private int index;
	private String atmosphereCode;
	private String atmosphereName;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getAtmosphereCode() {
		return atmosphereCode;
	}
	public void setAtmosphereCode(String atmosphereCode) {
		this.atmosphereCode = atmosphereCode;
	}
	public String getAtmosphereName() {
		return atmosphereName;
	}
	public void setAtmosphereName(String atmosphereName) {
		this.atmosphereName = atmosphereName;
	}
	
	public static final Parcelable.Creator<FDAtmosphere> CREATOR = new Creator<FDAtmosphere>() {  
        public FDAtmosphere createFromParcel(Parcel source) {  
        	FDAtmosphere atmosphere = new FDAtmosphere();  
        	atmosphere.atmosphereCode = source.readString();
        	atmosphere.atmosphereName = source.readString();
        	
            return atmosphere;  
        }  
        
        public FDAtmosphere[] newArray(int size) {  
            return new FDAtmosphere[size];  
        }  
    };  
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(atmosphereCode);
		dest.writeString(atmosphereName);
	}
	
	
}
