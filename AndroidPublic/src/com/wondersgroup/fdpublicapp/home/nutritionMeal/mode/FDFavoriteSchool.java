package com.wondersgroup.fdpublicapp.home.nutritionMeal.mode;

import java.util.Date;
import java.util.List;
import com.wondersgroup.fdpublicapp.common.mode.FDImage;

/**
 * 收藏的学校
 * @author chengshaohua
 *
 */
public class FDFavoriteSchool {
	private int id;
	private String companyName;
    private String schoolTypeValue;
    private String supervisionOrgValue;
	private Date createDate;
	private String createUsername;
	private String createUserNickname;
	private List<FDImage> attachList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public List<FDImage> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<FDImage> attachList) {
		this.attachList = attachList;
	}
	
}
