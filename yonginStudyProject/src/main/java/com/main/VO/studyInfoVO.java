package com.main.VO;

public class studyInfoVO {
	String	studyCode = "";
	String 	studyName = "";
	String 	studyDesc = "";
	String 	studyTopic = "";
	String 	studyLimit = "";
	String	studyRgstusId = "";
	String 	userName="";
	String	studyArea="";
	int 	totalCount;
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getStudyArea() {
		return studyArea;
	}
	public void setStudyArea(String studyArea) {
		this.studyArea = studyArea;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	public String getStudyTopic() {
		return studyTopic;
	}
	public void setStudyTopic(String studyTopic) {
		this.studyTopic = studyTopic;
	}
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
	public String getStudyDesc() {
		return studyDesc;
	}
	public void setStudyDesc(String studyDesc) {
		this.studyDesc = studyDesc;
	}
	public String getStudyLimit() {
		return studyLimit;
	}
	public void setStudyLimit(String studyLimit) {
		this.studyLimit = studyLimit;
	}
	public String getStudyRgstusId() {
		return studyRgstusId;
	}
	public void setStudyRgstusId(String studyRgstusId) {
		this.studyRgstusId = studyRgstusId;
	} 	

	
}