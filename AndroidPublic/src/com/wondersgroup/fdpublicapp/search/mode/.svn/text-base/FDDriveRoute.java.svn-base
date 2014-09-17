package com.wondersgroup.fdpublicapp.search.mode;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;

import android.os.Parcel;
import android.os.Parcelable;

public class FDDriveRoute extends FDSuperRoute implements Parcelable{
	
	private DrivePath routeDrivePath;
	private String driveStrategy;
	
	
	public String getDriveStrategy() {
		return driveStrategy;
	}

	public void setDriveStrategy(String driveStrategy) {
		this.driveStrategy = driveStrategy;
	}

	public DrivePath getRouteDrivePath() {
		return routeDrivePath;
	}

	public void setRouteDrivePath(DrivePath routeDrivePath) {
		this.routeDrivePath = routeDrivePath;
	}

	public static final Parcelable.Creator<FDDriveRoute> CREATOR = new Creator<FDDriveRoute>() {  
        public FDDriveRoute createFromParcel(Parcel source) {  
        	FDDriveRoute dirveRoute = new FDDriveRoute();  
        	dirveRoute.routeID = source.readInt(); 
        	dirveRoute.distance = source.readFloat();
        	dirveRoute.startPoint = source.readParcelable(LatLonPoint.class.getClassLoader());
        	dirveRoute.targetPoint = source.readParcelable(LatLonPoint.class.getClassLoader());
        	dirveRoute.routeDrivePath = source.readParcelable(DrivePath.class.getClassLoader());
        	dirveRoute.lines = source.readArrayList(FDDriveLine.class.getClassLoader());
        	
            return dirveRoute;  
        }  
        
        public FDDriveRoute[] newArray(int size) {  
            return new FDDriveRoute[size];  
        }  
    };  
    
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(routeID);
		dest.writeFloat(distance);
		dest.writeParcelable(startPoint, flags);
		dest.writeParcelable(targetPoint, flags);
		dest.writeParcelable(routeDrivePath, flags);
		dest.writeList(lines);
	}

}
