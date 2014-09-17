package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.ArrayList;

import com.wondersgroup.fdpublicapp.common.mode.FDImage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  营养餐学校
 * @author chengshaohua
 *
 */
public class FDSchool implements Parcelable{

	private int id;
	private String schoolName;
	private String schoolType;
	private String schoolTypeValue;
	private String postalCode;
	private String contactPhone;
	private int companyId;
	private int supervisionOrgId;
	private String supervisionOrgValue;
	private String contactPerson;
	private long gisLatitude;
	private long gisLongitude;
	private String regionValue;
	private String contactAddress;
	private ArrayList<FDImage> attachList;
	private int isCollected;        // 1=收藏
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getSupervisionOrgId() {
		return supervisionOrgId;
	}
	public void setSupervisionOrgId(int supervisionOrgId) {
		this.supervisionOrgId = supervisionOrgId;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public long getGisLatitude() {
		return gisLatitude;
	}
	public void setGisLatitude(long gisLatitude) {
		this.gisLatitude = gisLatitude;
	}
	public long getGisLongitude() {
		return gisLongitude;
	}
	public void setGisLongitude(long gisLongitude) {
		this.gisLongitude = gisLongitude;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	
	public String getSchoolTypeValue() {
		return schoolTypeValue;
	}
	public void setSchoolTypeValue(String schoolTypeValue) {
		this.schoolTypeValue = schoolTypeValue;
	}
	public String getSupervisionOrgValue() {
		return supervisionOrgValue;
	}
	public void setSupervisionOrgValue(String supervisionOrgValue) {
		this.supervisionOrgValue = supervisionOrgValue;
	}
	public String getRegionValue() {
		return regionValue;
	}
	public void setRegionValue(String regionValue) {
		this.regionValue = regionValue;
	}
	public int getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(int isCollected) {
		this.isCollected = isCollected;
	}
	public ArrayList<FDImage> getAttachList() {
		return attachList;
	}
	public void setAttachList(ArrayList<FDImage> attachList) {
		this.attachList = attachList;
	}

	public static final Parcelable.Creator<FDSchool> CREATOR = new Creator<FDSchool>() {  
        public FDSchool createFromParcel(Parcel source) {  
        	FDSchool nutirtionSchool = new FDSchool();  
        	nutirtionSchool.id = source.readInt(); 
        	nutirtionSchool.schoolName = source.readString();
        	nutirtionSchool.schoolType = source.readString();
        	nutirtionSchool.schoolTypeValue = source.readString();
        	nutirtionSchool.isCollected = source.readInt();
        	nutirtionSchool.postalCode = source.readString();
        	nutirtionSchool.contactPhone = source.readString();
        	nutirtionSchool.companyId = source.readInt(); 
        	nutirtionSchool.supervisionOrgId = source.readInt(); 
        	nutirtionSchool.supervisionOrgValue = source.readString();
        	nutirtionSchool.contactPerson = source.readString();
        	nutirtionSchool.gisLatitude = source.readLong();
        	nutirtionSchool.gisLongitude = source.readLong();
        	nutirtionSchool.regionValue = source.readString();
        	nutirtionSchool.contactAddress = source.readString();
        	nutirtionSchool.attachList = source.readArrayList(FDImage.class.getClassLoader());
        	
            return nutirtionSchool;  
        }  
        public FDSchool[] newArray(int size) {  
            return new FDSchool[size];  
        }  
    };  
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(schoolName);
		dest.writeString(schoolType);
		dest.writeString(schoolTypeValue);
		dest.writeInt(isCollected);
		dest.writeString(postalCode);
		dest.writeString(contactPhone);
		dest.writeInt(companyId);
		dest.writeInt(supervisionOrgId);
		dest.writeString(supervisionOrgValue);
		dest.writeString(contactPerson);
		dest.writeLong(gisLatitude);
		dest.writeLong(gisLongitude);
		dest.writeString(regionValue);
		dest.writeString(contactAddress);
		dest.writeList(attachList);
	}
	
}
