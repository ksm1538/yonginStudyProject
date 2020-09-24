package com.login.VO;

public class userInfoVO {
	
	private String userName = "";
	private String userCode = "";
	private String userId = "";
	private String userPw = "";
	private String userPwConfirm = "";
	private String userPhoneNumber ="";
	private String userPwHintCode = ""; 
	private String userPwHintAnswer = "";
	private String userGender = "";
	private String userAddress = "";
	private String useYn = "";
	private String userEmail = "";
	private String userBirth = "";
	private String userIsAdmin = "";

	/** 페이징 및 검색 조건 변수 추가 **/
	private	String	page = "";
	private int		first;
	private int		last;
	
	private String	searchUserEmail="";		//검색용 사용자 이메일
	private String	searchUserName="";		//검색용 사용자 이름
	private String	searchUserId="";		//검색용 사용자 ID
	private String	searchUserIsAdmin="";	//검색용 관리자 여부
	
	
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
	public String getSearchUserEmail() {
		return searchUserEmail;
	}
	public void setSearchUserEmail(String searchUserEmail) {
		this.searchUserEmail = searchUserEmail;
	}
	public String getSearchUserName() {
		return searchUserName;
	}
	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}
	public String getSearchUserId() {
		return searchUserId;
	}
	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}
	public String getSearchUserIsAdmin() {
		return searchUserIsAdmin;
	}
	public void setSearchUserIsAdmin(String searchUserIsAdmin) {
		this.searchUserIsAdmin = searchUserIsAdmin;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserPwHintCode() {
		return userPwHintCode;
	}
	public void setUserPwHintCode(String userPwHintCode) {
		this.userPwHintCode = userPwHintCode;
	}
	public String getUserPwHintAnswer() {
		return userPwHintAnswer;
	}
	public void setUserPwHintAnswer(String userPwHintAnswer) {
		this.userPwHintAnswer = userPwHintAnswer;
	}
	public String getUserPwConfirm() {
		return userPwConfirm;
	}
	public void setUserPwConfirm(String userPwConfirm) {
		this.userPwConfirm = userPwConfirm;
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUserIsAdmin() {
		return userIsAdmin;
	}
	public void setUserIsAdmin(String userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}
	
}
