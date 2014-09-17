package com.wondersgroup.fdpublicapp.personal.mode;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 评价的菜肴 - (查询菜肴点评)
 * @author chengshaohua
 *
 */
public class FDCuisineComment implements Parcelable {
	private int commentId;
	private int contentId;
	private String commentUsername;
	private String createUsername;
	private String createUserNickname;
	private String contentTextData;
	private int cuisineId;
	private String cuisineName;
	private String restaurantName;
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
	public String getContentTextData() {
		return contentTextData;
	}
	public void setContentTextData(String contentTextData) {
		this.contentTextData = contentTextData;
	}
	public int getCuisineId() {
		return cuisineId;
	}
	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
	}
	public String getCuisineName() {
		return cuisineName;
	}
	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
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
	public List<FDCommentResponse> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<FDCommentResponse> responseList) {
		this.responseList = responseList;
	}

	public static final Parcelable.Creator<FDCuisineComment> CREATOR = new Creator<FDCuisineComment>() {  
        public FDCuisineComment createFromParcel(Parcel source) {  
        	FDCuisineComment comment = new FDCuisineComment();  
        	comment.commentId = source.readInt();
        	comment.contentId = source.readInt();
        	comment.commentUsername = source.readString();
        	comment.createUsername = source.readString();
        	comment.createUserNickname = source.readString();
        	comment.contentTextData = source.readString();
        	comment.cuisineId = source.readInt();
        	comment.cuisineName = source.readString();
        	comment.restaurantName = source.readString();
        	comment.createDate = source.readString();
        	comment.pics = source.readArrayList(FDImage.class.getClassLoader());
        	comment.responseList = source.readArrayList(FDCommentResponse.class.getClassLoader());
        	
            return comment;  
        }  
        public FDCuisineComment[] newArray(int size) {  
            return new FDCuisineComment[size];  
        }  
    };
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(commentId);
		dest.writeInt(contentId);
		dest.writeString(commentUsername);
		dest.writeString(createUsername);
		dest.writeString(createUserNickname);
		dest.writeString(contentTextData);
		dest.writeInt(cuisineId);
		dest.writeString(cuisineName);
		dest.writeString(restaurantName);
		dest.writeString(createDate);
		dest.writeList(pics);
		dest.writeList(responseList);
	}

}
