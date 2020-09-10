package com.message.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.VO.studyInfoVO;
import com.message.DAO.messageDAO;
import com.message.VO.messageInfoVO;

@Service("messageService")
public class messageServiceImpl implements messageService{

	@Autowired
	messageDAO messageDAO;
	
	@Override
	public void deleteMessage(messageInfoVO data) throws Exception{
		messageDAO.deleteMessage(data);
	}
	
	@Override
	public void sendMessage(messageInfoVO data) throws Exception{
		messageDAO.sendMessage(data);
	}
	
	@Override
	public List<messageInfoVO> selectMessageList(){
		return messageDAO.selectMessageList();
	}
}