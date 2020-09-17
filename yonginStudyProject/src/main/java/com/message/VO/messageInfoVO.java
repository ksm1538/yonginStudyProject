package com.message.VO;

public class messageInfoVO {

	String	userCodeFrom = "";
	String 	userCodeTo = "";
	String 	messageCode = "";
	String 	messageTitle = "";
	String 	messageDesc = "";
	String	messageTime = "";
	int totalCount;
	int messageCodeSize;
	String[] data = new String[messageCodeSize];
	
	public int getMessageCodeSize() {
		return messageCodeSize;
	}
	public void setMessageCodeSize(int messageCodeSize) {
		this.messageCodeSize = messageCodeSize;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data)
	{
		this.data = data;
	}
	public String getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getUserCodeFrom() {
		return userCodeFrom;
	}
	public void setUserCodeFrom(String userCodeFrom) {
		this.userCodeFrom = userCodeFrom;
	}
	public String getUserCodeTo() {
		return userCodeTo;
	}
	public void setUserCodeTo(String userCodeTo) {
		this.userCodeTo = userCodeTo;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getMessageDesc() {
		return messageDesc;
	}
	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}
}
