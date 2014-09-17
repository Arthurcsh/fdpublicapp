package com.wondersgroup.fdpublicapp.home.safety.mode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author chengshaohua
 *  评论
 */
public class FDSafetyComment implements Parcelable{

	private int id;
	private String commenterName;
	private String title;
	private String content;
	private String commentDate;
	private String demo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getCommenterName() {
		return commenterName;
	}
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public static final Parcelable.Creator<FDSafetyComment> CREATOR = new Creator<FDSafetyComment>() {  
        public FDSafetyComment createFromParcel(Parcel source) {  
        	FDSafetyComment safetyInfo = new FDSafetyComment();  
        	safetyInfo.id = source.readInt(); 
        	safetyInfo.commenterName = source.readString();
        	safetyInfo.title = source.readString();
        	safetyInfo.content = source.readString();
        	safetyInfo.commentDate = source.readString();
        	
            return safetyInfo;  
        }  
        public FDSafetyComment[] newArray(int size) {  
            return new FDSafetyComment[size];  
        }  
    }; 
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(commenterName);
		dest.writeString(title);
		dest.writeString(content);
		dest.writeString(commentDate);
	}
	
	
}
