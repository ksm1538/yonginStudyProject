package com.message.DAO;

import java.util.List;

import com.main.VO.studyInfoVO;
import com.message.VO.messageInfoVO;

public interface messageDAO {
	
	public void deleteMessage(messageInfoVO data) throws Exception;
	
	public void deleteSendMessage(messageInfoVO data) throws Exception;
	
	public void sendMessage(messageInfoVO data) throws Exception;
	
	public List<messageInfoVO> selectMessageList();
	
	public List<messageInfoVO> selectSendMessageList();

}
