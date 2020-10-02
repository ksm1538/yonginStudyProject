package com.commonFunction.DAO; 

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commonFunction.VO.replyVO;

@Repository
public class replyDAOImpl implements replyDAO{
	@Inject
	private SqlSession sqlSession;

	@Override
	public void insertReply(replyVO replyVO) throws Exception{
		sqlSession.insert("replySQL.insertReply", replyVO);
	}
	
	@Override
	public List<replyVO> selectReplyList(String boardCode) throws Exception{
		return sqlSession.selectList("replySQL.selectReplyList", boardCode);
	}
	
	@Override
	public void deleteReply(String replyCode) throws Exception{
		sqlSession.update("replySQL.updateReplyUseYnToN", replyCode);
	}
	
	@Override
	public void updateReply(replyVO replyVO) throws Exception{
		sqlSession.update("replySQL.updateReply", replyVO);
	}
	
	@Override 
	public replyVO selectReplyWithId(String replyCode) throws Exception{
		return sqlSession.selectOne("replySQL.selectReplyWithId",replyCode);
	}
	
	@Override
	public void deleteReplyWithBoardCode(String boardCode) throws Exception{
		sqlSession.update("replySQL.updateReplyWithBoardCodeUseYnToN",boardCode);
	}
}