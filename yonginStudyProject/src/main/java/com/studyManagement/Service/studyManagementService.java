package com.studyManagement.Service; 

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.notice.VO.boardVO;
import com.main.VO.userInStudyVO;
import com.login.VO.userInfoVO;

public interface studyManagementService {
	
	List<userInStudyVO> selectStudyMemberList(userInStudyVO userInStudyVO);
	
	int selectStudyMemberListToCnt(userInStudyVO userInStudyVO);
	
	void writeStudyFreeNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	int selectStudyFreeNoticeListToCnt(boardVO boardVO);
	
	List<boardVO> selectStudyFreeNoticeList(boardVO boardVO);
	
	boardVO selectStudyFreeNoticeInfoDetail(String boardCode) throws Exception;
	
	void reviseStudyFreeNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception;

	void deleteStudyFreeNotice(boardVO boardVO) throws Exception;
	
	userInfoVO selectStudyMemberManage(String userCode) throws Exception;
}
