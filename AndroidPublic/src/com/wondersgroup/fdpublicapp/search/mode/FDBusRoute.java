package com.wondersgroup.fdpublicapp.search.mode;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;

public class FDBusRoute extends FDSuperRoute implements Parcelable{

	private BusPath routeBusPath;
	private float price;
	private float walkDistance;
	private float startWalkDistance;
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getWalkDistance() {
		return walkDistance;
	}
	public void setWalkDistance(float walkDistance) {
		this.walkDistance = walkDistance;
	}
	public BusPath getRouteBusPath() {
		return routeBusPath;
	}
	public void setRouteBusPath(BusPath routeBusPath) {
		this.routeBusPath = routeBusPath;
	}
	public float getStartWalkDistance() {
		return startWalkDistance;
	}
	public void setStartWalkDistance(float startWalkDistance) {
		this.startWalkDistance = startWalkDistance;
	}

	public static final Parcelable.Creator<FDBusRoute> CREATOR = new Creator<FDBusRoute>() {  
        public FDBusRoute createFromParcel(Parcel source) {  
        	FDBusRoute busRoute = new FDBusRoute();  
        	busRoute.routeID = source.readInt(); 
        	busRoute.distance = source.readFloat();
        	busRoute.price = source.readFloat();
        	busRoute.walkDistance = source.readFloat();
        	busRoute.startWalkDistance = source.readFloat();
        	busRoute.startPoint = source.readParcelable(LatLonPoint.class.getClassLoader());
        	busRoute.targetPoint = source.readParcelable(LatLonPoint.class.getClassLoader());
        	busRoute.routeBusPath = source.readParcelable(BusPath.class.getClassLoader());
        	busRoute.lines = source.readArrayList(FDBusLine.class.getClassLoader());
        	
            return busRoute;  
        }  
        public FDBusRoute[] newArray(int size) {  
            return new FDBusRoute[size];  
        }  
    };  
    
	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(routeID);
		dest.writeFloat(distance);
		dest.writeFloat(price);
		dest.writeFloat(walkDistance);
		dest.writeFloat(startWalkDistance);
		dest.writeParcelable(startPoint, flags);
		dest.writeParcelable(targetPoint, flags);
		dest.writeParcelable(routeBusPath, flags);
		dest.writeList(lines);
	}
	
}
