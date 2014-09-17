package com.wondersgroup.fdpublicapp.search.mode;


import android.os.Parcel;
import android.os.Parcelable;

public class FDBusLine extends FDSuperLine implements Parcelable{
 
	private String busCompany;
	private float busDuration;
	private float busDistance;
	
	public String getBusCompany() {
		return busCompany;
	}
	public void setBusCompany(String busCompany) {
		this.busCompany = busCompany;
	}
	public float getBusDuration() {
		return busDuration;
	}
	public void setBusDuration(float busDuration) {
		this.busDuration = busDuration;
	}
	public float getBusDistance() {
		return busDistance;
	}
	public void setBusDistance(float busDistance) {
		this.busDistance = busDistance;
	}

	public static final Parcelable.Creator<FDBusLine> CREATOR = new Creator<FDBusLine>() {  
        public FDBusLine createFromParcel(Parcel source) {  
        	FDBusLine busLine = new FDBusLine();  
        	busLine.lineID = source.readInt(); 
        	busLine.lineName = source.readString();
        	busLine.busCompany = source.readString();
        	busLine.busDuration = source.readFloat();
        	busLine.busDistance = source.readFloat();
        	
            return busLine;  
        }  
        public FDBusLine[] newArray(int size) {  
            return new FDBusLine[size];  
        }  
    };  
    
	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(lineID);
		dest.writeString(lineName);
		dest.writeString(busCompany);
		dest.writeFloat(busDuration);
		dest.writeFloat(busDistance);
	}
}
