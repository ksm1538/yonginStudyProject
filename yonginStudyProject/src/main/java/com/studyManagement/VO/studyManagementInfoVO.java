package com.studyManagement.VO;

public class studyManagementInfoVO {
	private String 	userCode="";
	private String	studyCode="";
	private String 	userPosition="";
	
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
