package com.studyManagement.Service; 

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.login.VO.userInfoVO;
import com.main.VO.userInStudyVO;
import com.main.VO.studyInfoVO;
import com.message.VO.messageInfoVO;
import com.notice.VO.boardVO;
import com.study.VO.studyApplicationFormUserVO;

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
	
	void deportStudyMember(messageInfoVO data) throws Exception;
	
	List<studyApplicationFormUserVO> selectStudyApplicationForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	int selectStudyApplicationFormToCnt(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	void approveStudyForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	void rejectStudyForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	studyInfoVO selectStudyInfoView(String studyCode) throws Exception;
	
	void studyInfoChange(studyInfoVO studyInfoVO) throws Exception;
}
