package com.message.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.message.VO.messageInfoVO;

@Repository
public class messageDAOImpl implements messageDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void deleteMessage(String messageCode) throws Exception{
		sqlSession.delete("messageMapper.deleteMessage", messageCode);
	}
	
	@Override
	public void deleteSendMessage(String messageCode) throws Exception{
		sqlSession.delete("messageMapper.deleteSendMessage", messageCode);
	}
	
	
	@Override
	public void sendMessage(messageInfoVO data) throws Exception{
		sqlSession.insert("messageMapper.sendMessage", data);
	}
	
	@Override
	public List<messageInfoVO> selectMessageList(messageInfoVO messageInfoVO){
		return sqlSession.selectList("messageMapper.selectMessageList", messageInfoVO);
	}
	
	@Override
	public List<messageInfoVO> selectSendMessageList(messageInfoVO messageInfoVO){
		return sqlSession.selectList("messageMapper.selectSendMessageList", messageInfoVO);
	}
	
	@Override
	public messageInfoVO selectMessageInfoDetail(String messageCode) {
		return sqlSession.selectOne("messageMapper.selectMessageInfoDetail", messageCode);
	}
	
	@Override
	public int selectUserExistCount(String userId) {
		return sqlSession.selectOne("messageMapper.selectUserExistCount", userId);
	}
	
	@Override
	public int selectSendMessageListToCnt(messageInfoVO messageInfoVO) {
		return sqlSession.selectOne("messageMapper.selectSendMessageListToCnt", messageInfoVO);
	}
	
	@Override
	public int selectMessageListToCnt(messageInfoVO messageInfoVO) {
		return sqlSession.selectOne("messageMapper.selectMessageListToCnt", messageInfoVO);
	}
}
