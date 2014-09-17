package com.wondersgroup.fdpublicapp.personal.mode;

import java.util.Date;
import java.util.List;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 我点评的餐馆
 * @author chengshaohua
 *
 */
public class FDRestaurantComment {
	private int commentId;              //点评ID
	private int contentId;              //点评内容ID
	private String commentUsername;
	private String createUsername;
	private String createUserNickname;
	private String restaurantName;      //餐馆名称
	private String restaurantNameAbbrev;//餐馆店招
	private int restaurantId;           //餐馆Id
	private int foodSaftyRating;        //餐厅信用等级
	private Date foodSaftyRatingDate;   //评级时间
	private String orgName;             //监管部门名称
	private int star;
	private int averageComsumption;
	private String contentTextData;
	private String createDate;
	private List<FDImage> pics;
	private List<FDCommentResponse> responseList;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getCommentUsername() {
		return commentUsername;
	}
	public void setCommentUsername(String commentUsername) {
		this.commentUsername = commentUsername;
	}
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public String getCreateUserNickname() {
		return createUserNickname;
	}
	public void setCreateUserNickname(String createUserNickname) {
		this.createUserNickname = createUserNickname;
	}
	public List<FDCommentResponse> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<FDCommentResponse> responseList) {
		this.responseList = responseList;
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
	public int getFoodSaftyRating() {
		return foodSaftyRating;
	}
	public void setFoodSaftyRating(int foodSaftyRating) {
		this.foodSaftyRating = foodSaftyRating;
	}
	public Date getFoodSaftyRatingDate() {
		return foodSaftyRatingDate;
	}
	public void setFoodSaftyRatingDate(Date foodSaftyRatingDate) {
		this.foodSaftyRatingDate = foodSaftyRatingDate;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getAverageComsumption() {
		return averageComsumption;
	}
	public void setAverageComsumption(int averageComsumption) {
		this.averageComsumption = averageComsumption;
	}
	public String getContentTextData() {
		return contentTextData;
	}
	public void setContentTextData(String contentTextData) {
		this.contentTextData = contentTextData;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<FDImage> getPics() {
		return pics;
	}
	public void setPics(List<FDImage> pics) {
		this.pics = pics;
	}
	
}
