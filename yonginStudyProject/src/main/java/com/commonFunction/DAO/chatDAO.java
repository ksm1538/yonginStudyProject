package com.commonFunction.DAO;

import com.commonFunction.VO.chatMessageVO;

public interface chatDAO {

	public void insertChat(chatMessageVO chatMessageVO) throws Exception;
}