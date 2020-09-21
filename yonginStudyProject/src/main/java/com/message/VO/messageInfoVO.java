package com.message.VO;

public class messageInfoVO {

	private String	userCodeFrom = "";
	private String 	userCodeTo = "";
	private String 	messageCode = "";
	private String 	messageTitle = "";
	private String 	messageDesc = "";
	private String	messageTime = "";
	private int totalCount;
	private int messageCodeSize;
	private String[] messageCodes;
	private	String	page = "";
	private int		first;
	private int		last;
	private String	searchUserCodeFrom="";
	private String	searchUserCodeTo="";
	private	String	searchMessageTitle="";
	
	
	public String getSearchUserCodeFrom() {
		return searchUserCodeFrom;
	}
	public void setSearchUserCodeFrom(String searchUserCodeFrom) {
		this.searchUserCodeFrom = searchUserCodeFrom;
	}
	public String getSearchUserCodeTo() {
		return searchUserCodeTo;
	}
	public void setSearchUserCodeTo(String searchUserCodeTo) {
		this.searchUserCodeTo = searchUserCodeTo;
	}
	public String getSearchMessageTitle() {
		return searchMessageTitle;
	}
	public void setSearchMessageTitle(String searchMessageTitle) {
		this.searchMessageTitle = searchMessageTitle;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public String[] getMessageCodes() {
		return messageCodes;
	}
	public void setMessageCodes(String[] messageCodes) {
		this.messageCodes = messageCodes;
	}
	public int getMessageCodeSize() {
		return messageCodeSize;
	}
	public void setMessageCodeSize(int messageCodeSize) {
		this.messageCodeSize = messageCodeSize;
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
