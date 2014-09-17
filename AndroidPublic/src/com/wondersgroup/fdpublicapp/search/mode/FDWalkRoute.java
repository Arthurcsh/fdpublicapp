package com.wondersgroup.fdpublicapp.search.mode;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.WalkPath;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * @author Administrator
 *
 */
public class FDWalkRoute extends FDSuperRoute implements Parcelable{

	private long duration;
	private WalkPath routeWalkPath;
	
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public WalkPath getRouteWalkPath() {
		return routeWalkPath;
	}

	public void setRouteWalkPath(WalkPath routeWalkPath) {
		this.routeWalkPath = routeWalkPath;
	}

	public static final Parcelable.Creator<FDWalkRoute> CREATOR = new Creator<FDWalkRoute>() {  
        public FDWalkRoute createFromParcel(Parcel source) {  
        	FDWalkRoute walkRoute = new FDWalkRoute();  
        	walkRoute.routeID = source.readInt(); 
        	walkRoute.distance = source.readFloat();
        	walkRoute.duration = source.readLong();
        	walkRoute.startPoint = source.readParcelable(LatLonPoint.class.getClassLoader());
        	walkRoute.targetPoint = source.readParcelable(LatLonPoint.class.getClassLoader());
        	walkRoute.routeWalkPath = source.readParcelable(WalkPath.class.getClassLoader());
        	walkRoute.lines = source.readArrayList(FDWalkLine.class.getClassLoader());
        	
            return walkRoute;  
        }  
        public FDWalkRoute[] newArray(int size) {  
            return new FDWalkRoute[size];  
        }  
    }; 
    
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(routeID);
		dest.writeFloat(distance);
		dest.writeLong(duration);
		dest.writeParcelable(startPoint, flags);
		dest.writeParcelable(targetPoint, flags);
		dest.writeParcelable(routeWalkPath, flags);
		dest.writeList(lines);
	}

}
