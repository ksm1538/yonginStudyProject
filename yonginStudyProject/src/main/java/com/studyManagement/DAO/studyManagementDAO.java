package com.studyManagement.DAO;

import java.util.List;

import com.studyManagement.VO.studyManagementInfoVO;

public interface studyManagementDAO {

	public List<studyManagementInfoVO> selectStudyMemberList(studyManagementInfoVO studyManagementInfoVO);
	
	public int selectStudyMemeberListToCnt(studyManagementInfoVO studyManagementInfoVO);
}
