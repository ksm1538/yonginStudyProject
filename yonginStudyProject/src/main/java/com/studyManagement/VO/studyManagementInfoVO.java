package com.studyManagement.VO; 

public class studyManagementInfoVO {
	private String 	userCode="";
	private String	studyCode="";
	private String 	userPosition="";
	private int	first;
	private int	last;
	private String	page = "";
	
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
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
}
