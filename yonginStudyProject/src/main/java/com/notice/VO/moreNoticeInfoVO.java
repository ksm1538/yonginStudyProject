package com.notice.VO; 

public class moreNoticeInfoVO {

	private String systemNoticeCode	= "";
	private String systemNoticeTitle = "";
	private String systemNoticeDesc = "";
	private String systemNoticeRgstusId = "";
	private String useYn = "";
	private String systemNoticeTime = "";
	private String systemNoticeUpdateTime = "";
	private String[] systemNoticeCodes;
	private int boardcount;
	private int	first;
	private int	last;
	private String	page = "";
	
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
	public int getBoardCount() {
		return boardcount;
	}
	public void setBoardCount(int boardcount)	{
		this.boardcount = boardcount;
	}
	public void setSystemNoticeCodes(String[] systemNoticeCodes) {
		this.systemNoticeCodes = systemNoticeCodes;
	}
	public String[] getSystemNoticeCodes() {
		return systemNoticeCodes;
	}
	public String getSystemNoticeUpdateTime() {
		return systemNoticeUpdateTime;
	}
	public void setSystemNoticeUpdateTime(String systemNoticeUpdateTime) {
		this.systemNoticeUpdateTime = systemNoticeUpdateTime;
	}
	public String getSystemNoticeTime() {
		return systemNoticeTime;
	}
	public void setSystemNoticeTime(String systemNoticeTime) {
		this.systemNoticeTime = systemNoticeTime;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSystemNoticeRgstusId() {
		return systemNoticeRgstusId;
	}
	public void setSystemNoticeRgstusId(String systemNoticeRgstusId) {
		this.systemNoticeRgstusId = systemNoticeRgstusId;
	}
	public String getSystemNoticeDesc() {
		return systemNoticeDesc;
	}
	public void setSystemNoticeDesc(String systemNoticeDesc) {
		this.systemNoticeDesc = systemNoticeDesc;
	}
	public String getSystemNoticeTitle() {
		return systemNoticeTitle;
	}
	public void setSystemNoticeTitle(String systemNoticeTitle) {
		this.systemNoticeTitle = systemNoticeTitle;
	}
	public String getSystemNoticeCode() {
		return systemNoticeCode;
	}
	public void setSystemNoticeCode(String systemNoticeCode) {
		this.systemNoticeCode = systemNoticeCode;
	}
	
}
