package com.studyManagement.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.notice.VO.boardVO;

public interface studyNoticeService {
	
	List<boardVO> selectStudyNoticeList(boardVO boardVO);
	
	void insertStudyNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	void deleteStudyNotice(boardVO boardVO) throws Exception;
	
	int selectStudyNoticeListToCnt(boardVO boardVO);
	
	boardVO selectStudyNoticeInfoDetail(String boardCode) throws Exception;
	
	void reviseStudyNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;

}
