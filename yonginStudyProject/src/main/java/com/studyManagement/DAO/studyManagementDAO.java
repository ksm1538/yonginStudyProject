package com.studyManagement.DAO; 

import java.util.List;

import com.login.VO.userInfoVO;
import com.main.VO.userInStudyVO;
import com.main.VO.studyInfoVO;
import com.message.VO.messageInfoVO;
import com.notice.VO.boardVO;
import com.study.VO.studyApplicationFormUserVO;

public interface studyManagementDAO {

	public List<userInStudyVO> selectStudyMemberList(userInStudyVO userInStudyVO);
	
	public int selectStudyMemberListToCnt(userInStudyVO userInStudyVO);
	
	public void writeStudyFreeNotice(boardVO data) throws Exception;
	
	public int selectStudyFreeNoticeListToCnt(boardVO boardVO);
	
	public List<boardVO> selectStudyFreeNoticeList(boardVO boardVO);
	
	public boardVO selectStudyFreeNoticeInfoDetail(String boardCode);
	
	public void updateStudyFreeNoticeCnt(String boardCode) throws Exception;
	
	public void reviseStudyFreeNotice(boardVO data) throws Exception;
	
	public void deleteStudyFreeNotice(String boardCode) throws Exception;
	
	public userInfoVO selectStudyMemberManage(String userCode);
	
	public void deportStudyMember(messageInfoVO data) throws Exception;
	
	public List<studyApplicationFormUserVO> selectStudyApplicationForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	public int selectStudyApplicationFormToCnt(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	public void approveStudyForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	public void rejectStudyForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	public void insertUserInStudy(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception;
	
	public studyInfoVO selectStudyInfoView(String studyCode);
	
	public void studyInfoChange(studyInfoVO studyInfoVO) throws Exception;
	
	public userInStudyVO selectStudyMemberAdminView(userInStudyVO userInStudyVO);
	
	public void studyMemberAdminChange(userInStudyVO userInStudyVO) throws Exception;
	
	public void studyMemberMasterChange(userInStudyVO userInStudyVO) throws Exception;
}
