package com.commonFunction.VO;

public class chatMessageVO {
	private String 	studyCode;
	private String 	writer;
	private String	message;
	private MessageType type;
	
	public enum MessageType {
	    ENTER,CHAT,LEAVE
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
}
