package com.wondersgroup.fdpublicapp.common.mode;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 公共点评回复
 * @author chengshaohua
 *
 */
public class FDCommonReply implements Parcelable{

	private int contentId;
	private int contentOwnerId;
	private String commentName;
	private String commentData;
	private String commentDate;
	private List<FDImage> commentImageList;
	
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getContentOwnerId() {
		return contentOwnerId;
	}
	public void setContentOwnerId(int contentOwnerId) {
		this.contentOwnerId = contentOwnerId;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public String getCommentData() {
		return commentData;
	}
	public void setCommentData(String commentData) {
		this.commentData = commentData;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public List<FDImage> getCommentImageList() {
		return commentImageList;
	}
	public void setCommentImageList(List<FDImage> commentImageList) {
		this.commentImageList = commentImageList;
	}

	public static final Parcelable.Creator<FDCommonReply> CREATOR = new Creator<FDCommonReply>() {  
        public FDCommonReply createFromParcel(Parcel source) {  
        	FDCommonReply commentReply = new FDCommonReply();  
        	commentReply.contentId = source.readInt();
        	commentReply.contentOwnerId = source.readInt();
        	commentReply.commentName = source.readString();
        	commentReply.commentData = source.readString();
        	commentReply.commentDate = source.readString();
        	commentReply.commentImageList = source.readArrayList(FDImage.class.getClassLoader());
        	
            return commentReply;  
        }  
        public FDCommonReply[] newArray(int size) {  
            return new FDCommonReply[size];  
        }  
    };
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(contentId);
		dest.writeInt(contentOwnerId);
		dest.writeString(commentName);
		dest.writeString(commentData);
		dest.writeString(commentDate);
		dest.writeList(commentImageList);
	}
	
}
