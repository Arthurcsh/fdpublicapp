package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.Date;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 评论回复(帖子、留言) DTO-(DTOPublicContent)
 * @author chengshaohua
 *
 */
public class FDReply implements Parcelable {
	private String contentType;
	private String contentTitle;
	private String contentTextData;
	private int isRead;
	private String createDate;
	private Date lastModifiedDate;
	private int contentRootId;
	private int contentOwnerId;
	private String createUsername;
	private String createUserNickname;
	private String lastModifiedUserName;
	private int delFlag;
	private String contentTo;
	private String contentFrom;
	private int collectCount;
	private List<FDImage> picList;
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getContentTextData() {
		return contentTextData;
	}
	public void setContentTextData(String contentTextData) {
		this.contentTextData = contentTextData;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public int getContentRootId() {
		return contentRootId;
	}
	public void setContentRootId(int contentRootId) {
		this.contentRootId = contentRootId;
	}
	public int getContentOwnerId() {
		return contentOwnerId;
	}
	public void setContentOwnerId(int contentOwnerId) {
		this.contentOwnerId = contentOwnerId;
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
	public String getLastModifiedUserName() {
		return lastModifiedUserName;
	}
	public void setLastModifiedUserName(String lastModifiedUserName) {
		this.lastModifiedUserName = lastModifiedUserName;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public String getContentTo() {
		return contentTo;
	}
	public void setContentTo(String contentTo) {
		this.contentTo = contentTo;
	}
	public String getContentFrom() {
		return contentFrom;
	}
	public void setContentFrom(String contentFrom) {
		this.contentFrom = contentFrom;
	}
	public int getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}
	public List<FDImage> getPicList() {
		return picList;
	}
	public void setPicList(List<FDImage> picList) {
		this.picList = picList;
	}
	
	public static final Parcelable.Creator<FDReply> CREATOR = new Creator<FDReply>() {  
        public FDReply createFromParcel(Parcel source) {  
        	FDReply safetyInfo = new FDReply();  
        	safetyInfo.contentType = source.readString();
        	safetyInfo.contentTitle = source.readString();
        	safetyInfo.contentTextData = source.readString();
        	safetyInfo.isRead = source.readInt(); 
        	safetyInfo.createDate = source.readString();
        	safetyInfo.contentRootId = source.readInt(); 
        	safetyInfo.contentOwnerId = source.readInt(); 
        	safetyInfo.createUsername = source.readString();
        	safetyInfo.createUserNickname = source.readString();
        	safetyInfo.lastModifiedUserName = source.readString();
        	safetyInfo.delFlag = source.readInt(); 
        	safetyInfo.contentTo = source.readString();
        	safetyInfo.contentFrom = source.readString();
        	safetyInfo.collectCount = source.readInt(); 
        	safetyInfo.picList = source.readArrayList(FDImage.class.getClassLoader());
        	
            return safetyInfo;  
        }  
        public FDReply[] newArray(int size) {  
            return new FDReply[size];  
        }  
    }; 
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(contentType);
		dest.writeString(contentTitle);
		dest.writeString(contentTextData);
		dest.writeInt(isRead);
		dest.writeString(createDate);
		dest.writeInt(contentRootId);
		dest.writeInt(contentOwnerId);
		dest.writeString(createUsername);
		dest.writeString(createUserNickname);
		dest.writeString(lastModifiedUserName);
		dest.writeInt(delFlag);
		dest.writeString(contentTo);
		dest.writeString(contentFrom);
		dest.writeInt(collectCount);
		dest.writeList(picList);
	}

}
