package com.wondersgroup.fdpublicapp.search.mode;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class FDCredit extends FDSuperMode implements Parcelable{
	private int index;
	private String creditCode;
	private String creditName;
	private Bitmap creditBitmap;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public Bitmap getCreditBitmap() {
		return creditBitmap;
	}
	public void setCreditBitmap(Bitmap creditBitmap) {
		this.creditBitmap = creditBitmap;
	}

	public static final Parcelable.Creator<FDCredit> CREATOR = new Creator<FDCredit>() {  
        public FDCredit createFromParcel(Parcel source) {  
        	FDCredit credit = new FDCredit();  
        	credit.creditCode = source.readString();
        	credit.creditName = source.readString();
        	
            return credit;  
        }  
        
        public FDCredit[] newArray(int size) {  
            return new FDCredit[size];  
        }  
    };  
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(creditCode);
		dest.writeString(creditName);
	}
	
	
}
