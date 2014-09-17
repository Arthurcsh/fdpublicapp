package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.Date;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 收藏的帖子详情
 * 
 * @author chengshaohua
 * 
 */
public class FDFavoriteNoteDetail implements Parcelable {
	private int id;
	private int contentId;           // 帖子Id
	private String createDate;
	private String createUsername;
	private String createUserNickname;
	private String commentUserName;
	private String contentTextData;
	private String contentTitle;
	private Date postCreateDate;
	private List<FDImage> picList;
	private int collectCount;
	private int isCollected;         // 是否收藏
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public String getCommentUserName() {
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	public String getContentTextData() {
		return contentTextData;
	}
	public void setContentTextData(String contentTextData) {
		this.contentTextData = contentTextData;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public Date getPostCreateDate() {
		return postCreateDate;
	}
	public void setPostCreateDate(Date postCreateDate) {
		this.postCreateDate = postCreateDate;
	}
	public List<FDImage> getPicList() {
		return picList;
	}
	public void setPicList(List<FDImage> picList) {
		this.picList = picList;
	}
	public int getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}
	public int getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(int isCollected) {
		this.isCollected = isCollected;
	}
	
	public static final Parcelable.Creator<FDFavoriteNoteDetail> CREATOR = new Creator<FDFavoriteNoteDetail>() {  
        public FDFavoriteNoteDetail createFromParcel(Parcel source) {  
        	FDFavoriteNoteDetail favoriteNote = new FDFavoriteNoteDetail();  
        	favoriteNote.id = source.readInt(); 
        	favoriteNote.contentId = source.readInt(); 
        	favoriteNote.createDate = source.readString();
        	favoriteNote.createUsername = source.readString();
        	favoriteNote.createUserNickname = source.readString();
        	favoriteNote.commentUserName = source.readString();
        	favoriteNote.contentTextData = source.readString();
        	favoriteNote.contentTitle = source.readString();
        	favoriteNote.picList = source.readArrayList(FDImage.class.getClassLoader());
        	favoriteNote.collectCount = source.readInt();
        	favoriteNote.isCollected = source.readInt();
        	
            return favoriteNote;  
        }  
        public FDFavoriteNoteDetail[] newArray(int size) {  
            return new FDFavoriteNoteDetail[size];  
        }  
    };
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(contentId);
		dest.writeString(createDate);
		dest.writeString(createUsername);
		dest.writeString(createUserNickname);
		dest.writeString(commentUserName);
		dest.writeString(contentTextData);
		dest.writeString(contentTitle);
		dest.writeList(picList);
		dest.writeInt(collectCount);
		dest.writeInt(isCollected);
	}

}
