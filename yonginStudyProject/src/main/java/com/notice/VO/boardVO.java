package com.notice.VO;

public class boardVO {
	private String boardCode = "";
	private String boardTitle = "";
	private String boardDesc = "";
	private String rgstusId = "";
	private String useYn = "";
	private String rgdtDt = "";
	private String updtDt = "";
	private String hgrnkBoardCode = "";
	private String qnaStatus = "";
	private String[] boardCodes;
	private int boardCount;
	private int	first;
	private int	last;
	private String	page = "";
	private String searchBoardRgstusId="";
	private String searchBoardTitle="";
	private String	fileCode="";
	private String[]	fileCodeDel;
	private String[]	fileNameDel;
	private String	rgstusCode="";
	
	
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode; 
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardDesc() {
		return boardDesc;
	}
	public void setBoardDesc(String boardDesc) {
		this.boardDesc = boardDesc;
	}
	public String getRgstusId() {
		return rgstusId;
	}
	public void setRgstusId(String rgstusId) {
		this.rgstusId = rgstusId;
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
	public String[] getBoardCodes() {
		return boardCodes;
	}
	public void setBoardCodes(String[] boardCodes) {
		this.boardCodes = boardCodes;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
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
	public String getSearchBoardRgstusId() {
		return searchBoardRgstusId;
	}
	public void setSearchBoardRgstusId(String searchBoardRgstusId) {
		this.searchBoardRgstusId = searchBoardRgstusId;
	}
	public String getSearchBoardTitle() {
		return searchBoardTitle;
	}
	public void setSearchBoardTitle(String searchBoardTitle) {
		this.searchBoardTitle = searchBoardTitle;
	}
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	public String[] getFileCodeDel() {
		return fileCodeDel;
	}
	public void setFileCodeDel(String[] fileCodeDel) {
		this.fileCodeDel = fileCodeDel;
	}
	public String[] getFileNameDel() {
		return fileNameDel;
	}
	public void setFileNameDel(String[] fileNameDel) {
		this.fileNameDel = fileNameDel;
	}
	public String getHgrnkBoardCode() {
		return hgrnkBoardCode;
	}
	public void setHgrnkBoardCode(String hgrnkBoardCode) {
		this.hgrnkBoardCode = hgrnkBoardCode;
	}
	public String getQnaStatus() {
		return qnaStatus;
	}
	public void setQnaStatus(String qnaStatus) {
		this.qnaStatus = qnaStatus;
	}
	public String getRgstusCode() {
		return rgstusCode;
	}
	public void setRgstusCode(String rgstusCode) {
		this.rgstusCode = rgstusCode;
	}
	
}
