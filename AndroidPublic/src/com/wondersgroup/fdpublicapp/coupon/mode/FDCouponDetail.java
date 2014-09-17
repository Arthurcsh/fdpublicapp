package com.wondersgroup.fdpublicapp.coupon.mode;

import java.util.List;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 优惠详情
 * @author chengshaohua
 *
 */
public class FDCouponDetail {
	private int discountId;
	private int restaurantId;
	private String restaurantName;
	private String restaurantNameAbbrev;
	private String title;
	private String startFrom;
	private String endTo;
	private int commercialCenter;
	private String commercialCenterValue;
	private int isCollected;
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
	public String getStartFrom() {
		return startFrom;
	}
	public void setStartFrom(String startFrom) {
		this.startFrom = startFrom;
	}
	public String getEndTo() {
		return endTo;
	}
	public void setEndTo(String endTo) {
		this.endTo = endTo;
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
	public int getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(int isCollected) {
		this.isCollected = isCollected;
	}
	public List<FDImage> getPics() {
		return pics;
	}
	public void setPics(List<FDImage> pics) {
		this.pics = pics;
	}
	
	
}
