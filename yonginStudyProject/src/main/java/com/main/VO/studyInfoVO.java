package com.main.VO;

public class studyInfoVO {
	private String	studyCode = "";
	private String 	studyName = "";
	private String 	studyDesc = "";
	private String 	studyTopic = "";
	private String 	studyLimit = "";
	private String	studyRgstusId = "";
	private String 	userName = "";
	private String	studyArea = "";
	private String	studyRgstusCode = "";
	private int 	totalCount;
	private	String	page = "";
	private int		first;
	private int		last;
	private String	searchStudyTopic="";	//검색용 스터디 코드
	private String	searchStudyName="";		//검색용 스터디 이름
	private String	searchStudyArea="";		//검색용 스터디 지역
	
	
	
	public String getSearchStudyTopic() {
		return searchStudyTopic;
	}
	public void setSearchStudyTopic(String searchStudyTopic) {
		this.searchStudyTopic = searchStudyTopic;
	}
	public String getSearchStudyName() {
		return searchStudyName;
	}
	public void setSearchStudyName(String searchStudyName) {
		this.searchStudyName = searchStudyName;
	}
	public String getSearchStudyArea() {
		return searchStudyArea;
	}
	public void setSearchStudyArea(String searchStudyArea) {
		this.searchStudyArea = searchStudyArea;
	}
	public String getStudyRgstusCode() {
		return studyRgstusCode;
	}
	public void setStudyRgstusCode(String studyRgstusCode) {
		this.studyRgstusCode = studyRgstusCode;
	}
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

	
}
