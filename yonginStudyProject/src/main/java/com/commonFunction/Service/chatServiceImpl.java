package com.commonFunction.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commonFunction.DAO.chatDAO;
import com.commonFunction.VO.chatMessageVO;

@Service("chatService")
public class chatServiceImpl implements chatService{
	@Autowired
	chatDAO chatDAO;

	@Override
	public void insertChat(chatMessageVO chatMessageVO) throws Exception{
		chatDAO.insertChat(chatMessageVO);
	}
	
}
