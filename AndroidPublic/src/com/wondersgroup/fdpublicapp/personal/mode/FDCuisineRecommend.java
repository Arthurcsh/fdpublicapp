package com.wondersgroup.fdpublicapp.personal.mode;

import java.util.Date;
import java.util.List;

import com.wondersgroup.fdpublicapp.common.mode.FDImage;


/**
 * 推荐的菜肴
 * @author chengshaohua
 *
 */
public class FDCuisineRecommend {

	private int id;
	private String name;
	private int cuisineId;
	private String restaurantName;
	private String restaurantAddress;
	private Date recommendDate;
	private List<FDImage> picList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCuisineId() {
		return cuisineId;
	}
	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public Date getRecommendDate() {
		return recommendDate;
	}
	public void setRecommendDate(Date recommendDate) {
		this.recommendDate = recommendDate;
	}
	public List<FDImage> getPicList() {
		return picList;
	}
	public void setPicList(List<FDImage> picList) {
		this.picList = picList;
	}
	
}
