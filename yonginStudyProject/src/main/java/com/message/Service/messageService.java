package com.message.Service;

import com.message.VO.messageInfoVO;

public interface messageService {

	void deleteMessage(messageInfoVO data) throws Exception;
	
	void sendMessage(messageInfoVO data) throws Exception;
}
