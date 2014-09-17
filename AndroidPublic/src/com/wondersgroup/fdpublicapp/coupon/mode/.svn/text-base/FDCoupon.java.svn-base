package com.wondersgroup.fdpublicapp.coupon.mode;

import java.util.List;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import android.os.Parcel;
import android.os.Parcelable;

public class FDCoupon implements Parcelable{
    private int discountId;
	private int restaurantId;
	private String restaurantName;
	private String restaurantNameAbbrev;
	private String title;
	private int commercialCenter;
	private String commercialCenterValue;
	private List<FDImage> pics;
	
	public int getDiscountId() {
		return discountId;
	}
	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantNameAbbrev() {
		return restaurantNameAbbrev;
	}
	public void setRestaurantNameAbbrev(String restaurantNameAbbrev) {
		this.restaurantNameAbbrev = restaurantNameAbbrev;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCommercialCenter() {
		return commercialCenter;
	}
	public void setCommercialCenter(int commercialCenter) {
		this.commercialCenter = commercialCenter;
	}
	public String getCommercialCenterValue() {
		return commercialCenterValue;
	}
	public void setCommercialCenterValue(String commercialCenterValue) {
		this.commercialCenterValue = commercialCenterValue;
	}
	public List<FDImage> getPics() {
		return pics;
	}
	public void setPics(List<FDImage> pics) {
		this.pics = pics;
	}
	public static final Parcelable.Creator<FDCoupon> CREATOR = new Creator<FDCoupon>() {  
        public FDCoupon createFromParcel(Parcel source) {  
        	FDCoupon coupon = new FDCoupon();  
        	coupon.discountId = source.readInt();
        	coupon.restaurantId = source.readInt(); 
        	coupon.restaurantName = source.readString();
        	coupon.restaurantNameAbbrev = source.readString();
        	coupon.title = source.readString();
        	coupon.commercialCenter = source.readInt(); 
        	coupon.commercialCenterValue = source.readString();
        	coupon.pics = source.readArrayList(FDImage.class.getClassLoader());
            return coupon;  
        }  
        
        public FDCoupon[] newArray(int size) {  
            return new FDCoupon[size];  
        }  
    };  
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(discountId);
		dest.writeInt(restaurantId);
		dest.writeString(restaurantName);
		dest.writeString(restaurantNameAbbrev);
		dest.writeString(title);
		dest.writeInt(commercialCenter);
		dest.writeString(commercialCenterValue);
		dest.writeList(pics);
	}

}
