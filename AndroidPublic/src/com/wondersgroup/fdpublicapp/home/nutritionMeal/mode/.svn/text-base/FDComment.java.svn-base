package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.Date;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;


/**
 *  营养餐评论
 *  
 * @author chengshaohua
 *
 */
public class FDComment implements Parcelable{
	private int commentId;
	private String contentType;
	private String contentTitle;
	private String contentTextData;
	private String stuMealName;
	private int isRead;
	private String createDate;
	private Date lastModifiedDate = new Date();
	private int contentRootId;
	private int contentOwnerId;
	private String createUsername;
	private String commentUserNickname;
	private String commentUserName;
	private String lastModifiedUserName;
	private int delFlag;
	private List<FDImage> pics;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int contentId) {
		this.commentId = contentId;
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
	public String getStuMealName() {
		return stuMealName;
	}
	public void setStuMealName(String stuMealName) {
		this.stuMealName = stuMealName;
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
	
	public String getCommentUserNickname() {
		return commentUserNickname;
	}
	public void setCommentUserNickname(String commentUserNickname) {
		this.commentUserNickname = commentUserNickname;
	}
	public String getCommentUserName() {
		if(StringUtils.isEmpty(commentUserName)) return "匿名";
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
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
	public List<FDImage> getPics() {
		return pics;
	}
	public void setPics(List<FDImage> pics) {
		this.pics = pics;
	}
	
	public static final Parcelable.Creator<FDComment> CREATOR = new Creator<FDComment>() {  
        public FDComment createFromParcel(Parcel source) {  
        	FDComment comment = new FDComment();  
        	comment.commentId = source.readInt();
        	comment.contentType = source.readString();
        	comment.contentTitle = source.readString();
        	comment.contentTextData = source.readString();
        	comment.stuMealName = source.readString();
        	comment.isRead = source.readInt();
        	comment.createDate = source.readString();
        	comment.lastModifiedDate = new Date(source.readLong());
        	comment.contentRootId = source.readInt();
        	comment.contentOwnerId = source.readInt();
        	comment.createUsername = source.readString();
        	comment.commentUserNickname = source.readString();
        	comment.commentUserName = source.readString();
        	comment.lastModifiedUserName = source.readString();
        	comment.delFlag = source.readInt();
        	comment.pics = source.readArrayList(FDImage.class.getClassLoader());
        	
            return comment;  
        }  
        public FDComment[] newArray(int size) {  
            return new FDComment[size];  
        }  
    };
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(commentId);
		dest.writeString(contentType);
		dest.writeString(contentTitle);
		dest.writeString(contentTextData);
		dest.writeString(stuMealName);
		dest.writeInt(isRead);
		dest.writeString(createDate);
		dest.writeLong(lastModifiedDate.getTime());
		dest.writeInt(contentRootId);
		dest.writeInt(contentOwnerId);
		dest.writeString(createUsername);
		dest.writeString(commentUserNickname);
		dest.writeString(commentUserName);
		dest.writeString(lastModifiedUserName);
		dest.writeInt(delFlag);
		dest.writeList(pics);
	}
}
