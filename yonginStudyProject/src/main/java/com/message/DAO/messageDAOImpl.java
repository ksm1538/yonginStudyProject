package com.message.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.main.VO.studyInfoVO;
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
	public void deleteSendMessage(messageInfoVO data) throws Exception{
		sqlSession.delete("messageMapper.deleteSendMessage", data);
	}
	
	
	@Override
	public void sendMessage(messageInfoVO data) throws Exception{
		sqlSession.insert("messageMapper.sendMessage", data);
	}
	
	@Override
	public List<messageInfoVO> selectMessageList(){
		return sqlSession.selectList("messageMapper.selectMessageList");
	}
	
	@Override
	public List<messageInfoVO> selectSendMessageList(){
		return sqlSession.selectList("messageMapper.selectSendMessageList");
	}
}
