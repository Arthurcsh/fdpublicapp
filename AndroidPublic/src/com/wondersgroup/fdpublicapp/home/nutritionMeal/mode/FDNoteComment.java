package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.Date;
import java.util.List;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 帖子评论
 * @author chengshaohua
 *
 */
public class FDNoteComment {
	private int contentId;
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
	 private List<FDImage> picList;
	 private int collectCount;
	 
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
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
	 
	 
}
