package com.wondersgroup.fdpublicapp.common.mode;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 点评提交实例 (FDTakePhotoView、FDCommonCommentNoteActivity) 
 * 
 * @author chengshaohua
 *
 */
public class FDCommonComment implements Parcelable {

	private int id;
	private int contentId;
	private String contentTitle;
	private String commentData;
	private List<FDImage> commentImages;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommentData() {
		return commentData;
	}
	public void setCommentData(String commentData) {
		this.commentData = commentData;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public List<FDImage> getCommentImages() {
		return commentImages;
	}
	public void setCommentImages(List<FDImage> commentImages) {
		this.commentImages = commentImages;
	}
	
	public static final Parcelable.Creator<FDCommonComment> CREATOR = new Creator<FDCommonComment>() {  
        public FDCommonComment createFromParcel(Parcel source) {  
        	FDCommonComment commonComment = new FDCommonComment();  
        	commonComment.id = source.readInt(); 
        	commonComment.contentId = source.readInt(); 
        	commonComment.contentTitle = source.readString();
        	commonComment.commentData = source.readString();
        	commonComment.commentImages = source.readArrayList(FDImage.class.getClassLoader());
        	
            return commonComment;  
        }  
        public FDCommonComment[] newArray(int size) {  
            return new FDCommonComment[size];  
        }  
    };
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(contentId);
		dest.writeString(contentTitle);
		dest.writeString(commentData);
		dest.writeList(commentImages);
	}
	
	
}
