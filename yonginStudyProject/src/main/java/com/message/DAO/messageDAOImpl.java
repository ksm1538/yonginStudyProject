package com.message.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.message.VO.messageInfoVO;

@Repository
public class messageDAOImpl implements messageDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void deleteMessage(messageInfoVO data) throws Exception{
		sqlSession.delete("messageMapper.deleteMessage", data);
	}
	
	@Override
	public void sendMessage(messageInfoVO data) throws Exception{
		sqlSession.insert("messageMapper.sendMessage", data);
	}
}
