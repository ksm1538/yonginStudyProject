package com.message.DAO;

import com.message.VO.messageInfoVO;

public interface messageDAO {
	
	public void deleteMessage(messageInfoVO data) throws Exception;
	
	public void sendMessage(messageInfoVO data) throws Exception;

}
