package com.wondersgroup.fdpublicapp.search.mode;


import android.os.Parcel;
import android.os.Parcelable;

public class FDWalkLine extends FDSuperLine implements Parcelable{
 
	private float walkDuration;       //预计时间(秒)
	private String walkInstruction;   //路段指示
	
	public float getWalkDuration() {
		return walkDuration;
	}

	public void setWalkDuration(float walkDuration) {
		this.walkDuration = walkDuration;
	}

	public String getWalkInstruction() {
		return walkInstruction;
	}

	public void setWalkInstruction(String walkInstruction) {
		this.walkInstruction = walkInstruction;
	}

	public static final Parcelable.Creator<FDWalkLine> CREATOR = new Creator<FDWalkLine>() {  
        public FDWalkLine createFromParcel(Parcel source) {  
        	FDWalkLine walkLine = new FDWalkLine();  
        	walkLine.lineID = source.readInt(); 
        	walkLine.lineName = source.readString();
        	walkLine.walkDuration = source.readFloat();
        	walkLine.walkInstruction = source.readString();
        	walkLine.distance = source.readFloat();
        	
            return walkLine;  
        }  
        public FDWalkLine[] newArray(int size) {  
            return new FDWalkLine[size];  
        }  
    };  
    
	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(lineID);
		dest.writeString(lineName);
		dest.writeFloat(walkDuration);
		dest.writeString(walkInstruction);
		dest.writeFloat(distance);
	}
}
