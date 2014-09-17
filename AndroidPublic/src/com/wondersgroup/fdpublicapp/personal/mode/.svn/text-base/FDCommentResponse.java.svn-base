package com.wondersgroup.fdpublicapp.personal.mode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 评论回应
 * @author chengshaohua
 *
 */
public class FDCommentResponse implements Parcelable {
	private String createDate;
	private String userFrom;
	private String userFromNickName;
	private String userTo;
	private String userToNickName;
	private String content;
	private int contentId;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}
	public String getUserFromNickName() {
		return userFromNickName;
	}
	public void setUserFromNickName(String userFromNickName) {
		this.userFromNickName = userFromNickName;
	}
	public String getUserTo() {
		return userTo;
	}
	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}
	public String getUserToNickName() {
		return userToNickName;
	}
	public void setUserToNickName(String userToNickName) {
		this.userToNickName = userToNickName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
	public static final Parcelable.Creator<FDCommentResponse> CREATOR = new Creator<FDCommentResponse>() {  
        public FDCommentResponse createFromParcel(Parcel source) {  
        	FDCommentResponse commentResponse = new FDCommentResponse(); 
        	commentResponse.createDate = source.readString();
        	commentResponse.userFrom = source.readString();
        	commentResponse.userFromNickName = source.readString();
        	commentResponse.userTo = source.readString();
        	commentResponse.userToNickName = source.readString();
        	commentResponse.content = source.readString();
        	commentResponse.contentId = source.readInt();
        	
            return commentResponse;  
        }  
        public FDCommentResponse[] newArray(int size) {  
            return new FDCommentResponse[size];  
        }  
    };
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(createDate);
		dest.writeString(userFrom);
		dest.writeString(userFromNickName);
		dest.writeString(userTo);
		dest.writeString(userToNickName);
		dest.writeString(content);
		dest.writeInt(contentId);
	}

}
