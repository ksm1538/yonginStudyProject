package com.commonFunction.Service;

import java.util.List;

import com.commonFunction.VO.replyVO;

public interface replyService {
	void insertReply(replyVO replyVO) throws Exception;
	
	List<replyVO> selectReplyList(String boardCode) throws Exception;
	
	void deleteReply(String replyCode) throws Exception;
	
	void updateReply(replyVO replyVO) throws Exception;
	
	replyVO selectReplyWithId(String replyCode) throws Exception;
	
	void deleteReplyWithBoardCode(String boardCode) throws Exception;
}
