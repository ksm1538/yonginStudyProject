package com.message.Service;

import java.util.List;

import com.message.VO.messageInfoVO;

public interface messageService {

	void deleteMessage(messageInfoVO data) throws Exception;
	
	void deleteSendMessage(messageInfoVO data) throws Exception;
	
	void sendMessage(messageInfoVO data) throws Exception;
	
	List<messageInfoVO> selectMessageList(messageInfoVO messageInfoVO);
	
	List<messageInfoVO> selectSendMessageList(messageInfoVO messageInfoVO);
	
	messageInfoVO selectMessageInfoDetail(String messageCode);
	
	int selectUserExistCount(String userId);
	
	int selectSendMessageListToCnt(messageInfoVO messageInfoVO);
	
	int selectMessageListToCnt(messageInfoVO messageInfoVO);
}
