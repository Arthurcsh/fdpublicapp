package com.wondersgroup.fdpublicapp.personal.mode;

import java.io.Serializable;
import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class FDUser implements Serializable{
    
	private static final long serialVersionUID = 1L;
	/**
	 * chengshaohua
	 */
	
	private int id;
    private String oldPassword;
    private Date expireDate = new Date();
    private int companyId;
    private String token;
	private String name;
	private String username;
	private String nickName;
	private String password;
	private String extraLoginType;        // 用户登录类型(第三方登陆)
	private String extraLoginId;          // 登陆验证ID
	private String employeeNo;
	private String cardType;
	private String cardNo;
	private String certificateOrg;
	private int sex;
	private String telephoneNo;
	private String mobilePhone;
	private String email;
	private String loginType;
	private String role = "人事部门";
	private String headImgUrl;
	private String birthday;
	private boolean isValid = true;     //有效
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String loginName) {
		this.username = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCertificateOrg() {
		return certificateOrg;
	}
	public void setCertificateOrg(String certificateOrg) {
		this.certificateOrg = certificateOrg;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getExtraLoginType() {
		return extraLoginType;
	}
	public void setExtraLoginType(String extraLoginType) {
		this.extraLoginType = extraLoginType;
	}
	public String getExtraLoginId() {
		return extraLoginId;
	}
	public void setExtraLoginId(String extraLoginId) {
		this.extraLoginId = extraLoginId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}


	public static final Parcelable.Creator<FDUser> CREATOR = new Creator<FDUser>() {  
        public FDUser createFromParcel(Parcel source) {  
        	FDUser user = new FDUser();  
        	user.id = source.readInt(); 
        	user.oldPassword = source.readString(); 
        	user.expireDate = new Date(source.readLong());
        	user.companyId = source.readInt(); 
        	user.name = source.readString();
        	user.username = source.readString();
        	user.nickName = source.readString();
        	user.password = source.readString();
        	user.extraLoginType = source.readString();
        	user.extraLoginId = source.readString();
        	user.employeeNo = source.readString();
        	user.cardType = source.readString();
        	user.cardNo = source.readString();
        	user.certificateOrg = source.readString();
        	user.sex = source.readInt();
        	user.telephoneNo = source.readString();
        	user.mobilePhone = source.readString();
        	user.email = source.readString();
        	user.loginType = source.readString();
        	user.role = source.readString();
        	user.headImgUrl = source.readString();
        	user.birthday = source.readString();
        	
            return user;  
        }  
        public FDUser[] newArray(int size) {  
            return new FDUser[size];  
        }  
    };
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(oldPassword);
		dest.writeLong(expireDate.getTime());
		dest.writeInt(companyId);
		dest.writeString(name);
		dest.writeString(username);
		dest.writeString(nickName);
		dest.writeString(password);
		dest.writeString(extraLoginType);
		dest.writeString(extraLoginId);
		dest.writeString(employeeNo);
		dest.writeString(cardType);
		dest.writeString(cardNo);
		dest.writeString(certificateOrg);
		dest.writeInt(sex);
		dest.writeString(telephoneNo);
		dest.writeString(mobilePhone);
		dest.writeString(email);
		dest.writeString(loginType);
		dest.writeString(role);
		dest.writeString(headImgUrl);
		dest.writeString(birthday);
	}
	
}
