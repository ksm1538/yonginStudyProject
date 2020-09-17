package com.message.Service;

import java.util.List;

import com.message.VO.messageInfoVO;

public interface messageService {

	void deleteMessage(messageInfoVO data) throws Exception;
	
	void deleteSendMessage(messageInfoVO data) throws Exception;
	
	void sendMessage(messageInfoVO data) throws Exception;
	
	List<messageInfoVO> selectMessageList(String userCode);
	
	List<messageInfoVO> selectSendMessageList(String userCode);
	
	messageInfoVO selectMessageInfoDetail(String messageCode);
	
}
