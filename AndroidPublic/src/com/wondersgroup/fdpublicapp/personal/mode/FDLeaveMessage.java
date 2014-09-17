package com.wondersgroup.fdpublicapp.personal.mode;

/**
 * 我的留言
 * @author chengshaohua
 *
 */
public class FDLeaveMessage {
	private String companyName;     //商家名称
	 private int companyId;         //商家id
	 private String replayContent;  //商家回复内容
	 private String msgContent;     // 留言内容
	 private String msgCreateDate;    //留言时间
	 
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getReplayContent() {
		return replayContent;
	}
	public void setReplayContent(String replayContent) {
		this.replayContent = replayContent;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgCreateDate() {
		return msgCreateDate;
	}
	public void setMsgCreateDate(String msgCreateDate) {
		this.msgCreateDate = msgCreateDate;
	}
	 
}
