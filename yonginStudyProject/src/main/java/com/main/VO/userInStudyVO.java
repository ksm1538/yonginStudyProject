package com.main.VO;

public class userInStudyVO {
	private String	userCode = "";
	private String 	studyCode = "";
	private String 	userId = "";
	private String 	userName = "";
	private String 	userAddress = "";
	private String	studyAuthority = "";
	private String 	useYn = "";
	private String	rgdtDt = "";
	private String	updtDt = "";
	private int	first;
	private int	last;
	private String	page = "";
	private String searchStudyMemberId="";
	
	public String getSearchStudyMemberId() {
		return searchStudyMemberId;
	}
	public void setSearchStudyMemberId(String searchStudyMemberId) {
		this.searchStudyMemberId = searchStudyMemberId;
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
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
	public String getStudyAuthority() {
		return studyAuthority;
	}
	public void setStudyAuthority(String studyAuthority) {
		this.studyAuthority = studyAuthority;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRgdtDt() {
		return rgdtDt;
	}
	public void setRgdtDt(String rgdtDt) {
		this.rgdtDt = rgdtDt;
	}
	public String getUpdtDt() {
		return updtDt;
	}
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	
	
}	
