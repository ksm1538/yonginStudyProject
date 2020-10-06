package com.studyManagement.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.notice.VO.boardVO;
import com.studyManagement.VO.studyManagementInfoVO;

public interface studyManagementService {
	
	List<studyManagementInfoVO> selectStudyMemberList(studyManagementInfoVO studyManagementInfoVO);
	
	int selectStudyMemeberListToCnt(studyManagementInfoVO studyManagementInfoVO);
	
	void writeStudyFreeNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	int selectStudyFreeNoticeListToCnt(boardVO boardVO);
	
	List<boardVO> selectStudyFreeNoticeList(boardVO boardVO);
	
	boardVO selectStudyFreeNoticeInfoDetail(String boardCode) throws Exception;
	
	void reviseStudyFreeNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;

}
