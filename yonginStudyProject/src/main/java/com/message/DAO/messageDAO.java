package com.message.DAO;

import java.util.List;

import com.message.VO.messageInfoVO;

public interface messageDAO {
	
	public void deleteMessage(String messageCode) throws Exception;
	
	public void deleteSendMessage(String messageCode) throws Exception;
	
	public void sendMessage(messageInfoVO data) throws Exception;
	
	public List<messageInfoVO> selectMessageList(messageInfoVO messageInfoVO);
	
	public List<messageInfoVO> selectSendMessageList(messageInfoVO messageInfoVO);

	public messageInfoVO selectMessageInfoDetail(String messageCode);
	
	public int selectUserExistCount(String userId);
	
	public int selectSendMessageListToCnt(messageInfoVO messageInfoVO);
	
	public int selectMessageListToCnt(messageInfoVO messageInfoVO);
}
