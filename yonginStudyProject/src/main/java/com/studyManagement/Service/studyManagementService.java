package com.studyManagement.Service;

import java.util.List;

import com.studyManagement.VO.studyManagementInfoVO;

public interface studyManagementService {
	
	List<studyManagementInfoVO> selectStudyMemberList(studyManagementInfoVO studyManagementInfoVO);
	
	int selectStudyMemeberListToCnt(studyManagementInfoVO studyManagementInfoVO);

}
