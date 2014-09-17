package com.wondersgroup.fdpublicapp.search.mode;


import android.os.Parcel;
import android.os.Parcelable;

public class FDDriveLine extends FDSuperLine implements Parcelable{

	public static final Parcelable.Creator<FDDriveLine> CREATOR = new Creator<FDDriveLine>() {  
        public FDDriveLine createFromParcel(Parcel source) {  
        	FDDriveLine driveLine = new FDDriveLine();  
        	driveLine.lineID = source.readInt(); 
        	driveLine.lineName = source.readString();
        	driveLine.distance = source.readFloat();
        	
            return driveLine;  
        }  
        public FDDriveLine[] newArray(int size) {  
            return new FDDriveLine[size];  
        }  
    };  
    
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(lineID);
		dest.writeString(lineName);
		dest.writeFloat(distance);
	}
	
}
