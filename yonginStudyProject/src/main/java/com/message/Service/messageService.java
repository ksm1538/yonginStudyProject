package com.message.Service;

import java.util.List;

import com.main.VO.studyInfoVO;
import com.message.VO.messageInfoVO;

public interface messageService {

	void deleteMessage(messageInfoVO data) throws Exception;
	
	void sendMessage(messageInfoVO data) throws Exception;
	
	List<messageInfoVO> selectMessageList();
	
}
