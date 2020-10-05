package com.studyManagement.DAO; 

import java.util.List;

import com.notice.VO.boardVO;
import com.studyManagement.VO.studyManagementInfoVO;

public interface studyManagementDAO {

	public List<studyManagementInfoVO> selectStudyMemberList(studyManagementInfoVO studyManagementInfoVO);
	
	public int selectStudyMemeberListToCnt(studyManagementInfoVO studyManagementInfoVO);
	
	public void writeStudyFreeNotice(boardVO data) throws Exception;
	
	public int selectStudyFreeNoticeListToCnt(boardVO boardVO);
	
	public List<boardVO> selectStudyFreeNoticeList(boardVO boardVO);
}
