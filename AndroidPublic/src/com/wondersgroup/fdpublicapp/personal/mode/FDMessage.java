package com.wondersgroup.fdpublicapp.personal.mode;

import java.util.Date;

/**
 * 消息
 * @author chengshaohua
 *
 */
public class FDMessage {
	private int id;
	private String messageType;
	private int messageStatus;
	private int companyFrom;
	private int companyTo;
	private String commTitle;
	private String commContent;
	private String createDate;
	private Date lastModifiedDate;
	private String fromUsername;
	private int contentId;
	private String toUsername;
	private String userToNickname;
	private String userFromNickname;
	private String companyFromName;
	private String companyToName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public int getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(int messageStatus) {
		this.messageStatus = messageStatus;
	}
	public int getCompanyFrom() {
		return companyFrom;
	}
	public void setCompanyFrom(int companyFrom) {
		this.companyFrom = companyFrom;
	}
	public int getCompanyTo() {
		return companyTo;
	}
	public void setCompanyTo(int companyTo) {
		this.companyTo = companyTo;
	}
	public String getCommTitle() {
		return commTitle;
	}
	public void setCommTitle(String commTitle) {
		this.commTitle = commTitle;
	}
	public String getCommContent() {
		return commContent;
	}
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getFromUsername() {
		return fromUsername;
	}
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getToUsername() {
		return toUsername;
	}
	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}
	public String getUserToNickname() {
		return userToNickname;
	}
	public void setUserToNickname(String userToNickname) {
		this.userToNickname = userToNickname;
	}
	public String getUserFromNickname() {
		return userFromNickname;
	}
	public void setUserFromNickname(String userFromNickname) {
		this.userFromNickname = userFromNickname;
	}
	public String getCompanyFromName() {
		return companyFromName;
	}
	public void setCompanyFromName(String companyFromName) {
		this.companyFromName = companyFromName;
	}
	public String getCompanyToName() {
		return companyToName;
	}
	public void setCompanyToName(String companyToName) {
		this.companyToName = companyToName;
	}

}
