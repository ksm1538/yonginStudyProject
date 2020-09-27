package com.main.VO;

public class studyNoticeInfoVO {
	
	private String studyNoticeCode	= "";
	private String studyNoticeStudyName = "";
	private String studyNoticeTitle = "";
	private String studyNoticeDesc = "";
	private int studyNoticeCount;
	private String studyNoticeTime = "";
	private String studyNoticeUpdateTime = "";
	private String studyNoticeRgstusId = "";
	private int	first;
	private int	last;
	private String	page = "";
	private String userCode = "";
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	public String getStudyNoticeRgstusId() {
		return studyNoticeRgstusId;
	}
	public void setStudyNoticeRgstusId(String studyNoticeRgstusId) {
		this.studyNoticeRgstusId = studyNoticeRgstusId;
	}
	public String getStudyNoticeUpdateTime() {
		return studyNoticeUpdateTime;
	}
	public void setStudyNoticeUpdateTime(String studyNoticeUpdateTime) {
		this.studyNoticeUpdateTime = studyNoticeUpdateTime;
	}
	public String getStudyNoticeTime() {
		return studyNoticeTime;
	}
	public void setStudyNoticeTime(String studyNoticeTime) {
		this.studyNoticeTime = studyNoticeTime;
	}
	public int getStudyNoticeCount() {
		return studyNoticeCount;
	}
	public void setStudyNoticeCount(int studyNoticeCount)	{
		this.studyNoticeCount = studyNoticeCount;
	}
	public String getStudyNoticeDesc() {
		return studyNoticeDesc;
	}
	public void setStudyNoticeDesc(String studyNoticeDesc) {
		this.studyNoticeDesc = studyNoticeDesc;
	}
	public String getStudyNoticeTitle() {
		return studyNoticeTitle;
	}
	public void setStudyNoticeTitle(String studyNoticeTitle) {
		this.studyNoticeTitle = studyNoticeTitle;
	}
	public String getStudyNoticeStudyName() {
		return studyNoticeStudyName;
	}
	public void setStudyNoticeStudyName(String studyNoticeStudyName) {
		this.studyNoticeStudyName = studyNoticeStudyName;
	}
	public String getStudyNoticeCode() {
		return studyNoticeCode;
	}
	public void setStudyNoticeCode(String studyNoticeCode) {
		this.studyNoticeCode = studyNoticeCode;
	}

}
