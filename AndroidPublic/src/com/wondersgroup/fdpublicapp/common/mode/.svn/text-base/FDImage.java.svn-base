package com.wondersgroup.fdpublicapp.common.mode;

import java.io.Serializable;

/**
 * 通用图片
 * @author chengshaohua
 *
 */
public class FDImage implements Cloneable,Serializable{
	
	private static final long serialVersionUID = 3628159188536977674L;
	private String imageId;
	private String objId;
	private String name;
	private String imgURL;        // 图片地址
	private String filePath;      // 图片地址
	private String uploadTime;
	private String description;
	private String thumbnailPath;
	private boolean isSelected = false;
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public FDImage  clone() {
		FDImage o = null;
		try {
			o = (FDImage) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
