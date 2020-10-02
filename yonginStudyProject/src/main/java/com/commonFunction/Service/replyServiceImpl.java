package com.commonFunction.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commonFunction.DAO.replyDAO;
import com.commonFunction.VO.replyVO;

@Service("replyService")
public class replyServiceImpl implements replyService{
	@Autowired
	replyDAO replyDAO;

	@Override
	public void insertReply(replyVO replyVO) throws Exception{
		replyDAO.insertReply(replyVO);
	}
	
	@Override
	public List<replyVO> selectReplyList(String boardCode) throws Exception{
		return replyDAO.selectReplyList(boardCode);
	}
	
	@Override
	public void deleteReply(String replyCode) throws Exception{
		replyDAO.deleteReply(replyCode);
	}
	
	@Override
	public void updateReply(replyVO replyVO) throws Exception{
		replyDAO.updateReply(replyVO);
	}
	
	@Override
	public replyVO selectReplyWithId(String replyCode) throws Exception{
		return replyDAO.selectReplyWithId(replyCode);
	}
	
	@Override
	public void deleteReplyWithBoardCode(String boardCode) throws Exception{
		replyDAO.deleteReplyWithBoardCode(boardCode);
	}
}
