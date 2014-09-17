package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.Date;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 收藏的帖子
 * @author chengshaohua
 *
 */
public class FDFavoriteNote implements Parcelable {
	private int id;
	private int contentId;    // 帖子Id
	private Date createDate;
	private String createUsername;
	private String createUserNickname;       // 显示昵称
	private String commentUserName;
	private String contentTextData;
	private String contentTitle;
	private Date postCreateDate;
	private List<FDImage> pics;
	
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
	public List<FDImage> getPics() {
		return pics;
	}
	public void setPics(List<FDImage> pics) {
		this.pics = pics;
	}

	public static final Parcelable.Creator<FDFavoriteNote> CREATOR = new Creator<FDFavoriteNote>() {  
        public FDFavoriteNote createFromParcel(Parcel source) {  
        	FDFavoriteNote favoriteNote = new FDFavoriteNote();  
        	favoriteNote.id = source.readInt(); 
        	favoriteNote.contentId = source.readInt(); 
        	favoriteNote.createDate = new Date(source.readLong());
        	favoriteNote.createUsername = source.readString();
        	favoriteNote.createUserNickname = source.readString();
        	favoriteNote.commentUserName = source.readString();
        	favoriteNote.contentTextData = source.readString();
        	favoriteNote.contentTitle = source.readString();
//        	favoriteNote.postCreateDate = new Date(source.readLong());
        	favoriteNote.pics = source.readArrayList(FDImage.class.getClassLoader());
        	
            return favoriteNote;  
        }  
        public FDFavoriteNote[] newArray(int size) {  
            return new FDFavoriteNote[size];  
        }  
    };
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(contentId);
		dest.writeLong(createDate.getTime());
		dest.writeString(createUsername);
		dest.writeString(createUserNickname);
		dest.writeString(commentUserName);
		dest.writeString(contentTextData);
		dest.writeString(contentTitle);
//		dest.writeLong(postCreateDate.getTime());
		dest.writeList(pics);
	}

}
