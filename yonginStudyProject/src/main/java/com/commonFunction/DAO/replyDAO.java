package com.commonFunction.DAO;

import java.util.List;

import com.commonFunction.VO.replyVO;

public interface replyDAO {

	public void insertReply(replyVO replyVO) throws Exception;
	
	public List<replyVO> selectReplyList(String boardCode) throws Exception;
	
	public void deleteReply(String replyCode) throws Exception;
	
	public void updateReply(replyVO replyVO) throws Exception;
	
	public replyVO selectReplyWithId(String replyCode) throws Exception;
	
	public void deleteReplyWithBoardCode(String boardCode) throws Exception;
}