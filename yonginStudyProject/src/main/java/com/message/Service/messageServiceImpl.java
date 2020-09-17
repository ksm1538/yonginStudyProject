package com.message.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.DAO.messageDAO;
import com.message.VO.messageInfoVO;

@Service("messageService")
public class messageServiceImpl implements messageService{

	@Autowired
	messageDAO messageDAO;
	
	@Override
	public void deleteMessage(messageInfoVO data) throws Exception{
		String[] messageCodes = data.getMessageCodes();
		for(int i=0;i<data.getMessageCodes().length;i++) {
			messageDAO.deleteMessage(messageCodes[i]);
		}
	}
	
	@Override
	public void deleteSendMessage(messageInfoVO data) throws Exception{
		
		String[] messageCodes = data.getMessageCodes();
		for(int i=0;i<data.getMessageCodes().length;i++) {
			messageDAO.deleteSendMessage(messageCodes[i]);
		}
	}
	
	@Override
	public void sendMessage(messageInfoVO data) throws Exception{
		messageDAO.sendMessage(data);
	}
	
	@Override
	public List<messageInfoVO> selectMessageList(String userCode){
		return messageDAO.selectMessageList(userCode);
	}
	
	@Override
	public List<messageInfoVO> selectSendMessageList(String userCode){
		return messageDAO.selectSendMessageList(userCode);
	}
	
	@Override
	public messageInfoVO selectMessageInfoDetail(String messageCode) {
		return messageDAO.selectMessageInfoDetail(messageCode);
	}
	
	@Override
	public int selectUserExistCount(String userId) {
		return messageDAO.selectUserExistCount(userId);
	}
	
}
