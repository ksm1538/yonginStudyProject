package com.qna.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.notice.VO.boardVO;

public interface qnaService {

	void insertQna(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	List<boardVO> selectQnaList(boardVO boardVO);
	
	int selectQnaListToCnt(boardVO boardVO);
	
	boardVO selectQnaDetail(String boardCode) throws Exception;
    
	void deleteQna(String boardCode) throws Exception;
	
	void reviseQna(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	boardVO selectQnaAnswerDetail(String boardCode) throws Exception;
	
	void deleteQnaAnswer(boardVO boardVO) throws Exception;
}