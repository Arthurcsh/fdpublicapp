package com.wondersgroup.fdpublicapp.home.safety.mode;

import java.util.ArrayList;

import com.wondersgroup.fdpublicapp.common.mode.FDImage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 安全信息
 * @author chengshaohua
 *
 */
public class FDSafetyInfo implements Parcelable{

	private int id;
	private String name;
	private String title;
	private String content;
	private ArrayList<FDImage> images;
	private String supervision;
	private String supervisionDate;
	private String demo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public ArrayList<FDImage> getImages() {
		return images;
	}
	public void setImages(ArrayList<FDImage> images) {
		this.images = images;
	}
	public String getSupervision() {
		return supervision;
	}
	public void setSupervision(String supervision) {
		this.supervision = supervision;
	}
	public String getSupervisionDate() {
		return supervisionDate;
	}
	public void setSupervisionDate(String supervisionDate) {
		this.supervisionDate = supervisionDate;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	
	public static final Parcelable.Creator<FDSafetyInfo> CREATOR = new Creator<FDSafetyInfo>() {  
        public FDSafetyInfo createFromParcel(Parcel source) {  
        	FDSafetyInfo safetyInfo = new FDSafetyInfo();  
        	safetyInfo.id = source.readInt(); 
        	safetyInfo.name = source.readString();
        	safetyInfo.title = source.readString();
        	safetyInfo.content = source.readString();
        	safetyInfo.images = source.readArrayList(FDImage.class.getClassLoader());
        	
            return safetyInfo;  
        }  
        public FDSafetyInfo[] newArray(int size) {  
            return new FDSafetyInfo[size];  
        }  
    }; 
    
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(title);
		dest.writeString(content);
		dest.writeList(images);
	}
	
	
}
