package com.notice.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.notice.VO.boardVO;

public interface systemNoticeService {

	List<boardVO> selectSystemNoticeList(boardVO boardVO);
	
	void insertSystemNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	void deleteSystemNotice(boardVO boardVO) throws Exception;
	
	int selectSystemNoticeListToCnt(boardVO boardVO);
	
	boardVO selectSystemNoticeInfoDetail(String boardCode) throws Exception;
	
	void reviseSystemNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	

    
}