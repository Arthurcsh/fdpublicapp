package com.wondersgroup.fdpublicapp.common.mode;

import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 通用帖子
 * @author chengshaohua
 *
 */
public class FDCommonNote implements Parcelable {

	private int contentId;                   // 帖子ID
	private Date createDate;
	private String createUsername;
	private String createUserNickname;       // 显示昵称
	private String contentTitle;
	private String contentTextData;
	
	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
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

	public static final Parcelable.Creator<FDCommonNote> CREATOR = new Creator<FDCommonNote>() {  
        public FDCommonNote createFromParcel(Parcel source) {  
        	FDCommonNote commonNote = new FDCommonNote();  
        	commonNote.contentId = source.readInt(); 
        	commonNote.createDate = new Date(source.readLong()); 
        	commonNote.createUsername = source.readString();
        	commonNote.createUserNickname = source.readString();
        	commonNote.contentTitle = source.readString();
        	commonNote.contentTextData = source.readString();
        	
            return commonNote;  
        }  
        public FDCommonNote[] newArray(int size) {  
            return new FDCommonNote[size];  
        }  
    };
    
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(contentId);
		dest.writeLong(createDate!=null?createDate.getTime():0);
		dest.writeString(createUsername);
		dest.writeString(createUserNickname);
		dest.writeString(contentTitle);
		dest.writeString(contentTextData);
	}

}
