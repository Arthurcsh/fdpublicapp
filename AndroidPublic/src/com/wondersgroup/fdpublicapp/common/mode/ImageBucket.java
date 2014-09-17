package com.wondersgroup.fdpublicapp.common.mode;

import java.util.List;

/**
 * 相册实例
 * @author chengshaohua
 *
 */
public class ImageBucket {
	public int count = 0;
	public String bucketName;
	public List<FDImage> imageList;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public List<FDImage> getImageList() {
		return imageList;
	}
	public void setImageList(List<FDImage> imageList) {
		this.imageList = imageList;
	}

}
