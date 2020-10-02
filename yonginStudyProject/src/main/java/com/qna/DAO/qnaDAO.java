package com.qna.DAO;

import java.util.List;

import com.notice.VO.boardVO;

public interface qnaDAO {

	public void insertQna(boardVO data) throws Exception;
	
	public List<boardVO> selectQnaList(boardVO boardVO);
	
	public int selectQnaListToCnt(boardVO boardVO);
	
	public void reviseQna(boardVO data) throws Exception;
	
	public void updateQnaStatus(String boardCode) throws Exception;
	
	public boardVO selectQnaAnswerDetail(String boardCode) throws Exception;
	
	public void updateQnaStatusTo10(String boardCode) throws Exception;
}