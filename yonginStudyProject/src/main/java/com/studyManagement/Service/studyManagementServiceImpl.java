package com.studyManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyManagement.DAO.studyManagementDAO;
import com.studyManagement.VO.studyManagementInfoVO;

@Service("studyManagementService")
public class studyManagementServiceImpl implements studyManagementService{
	@Autowired
	studyManagementDAO studyManagementDAO;
	
	@Override
	public List<studyManagementInfoVO> selectStudyMemberList(studyManagementInfoVO studyManagementInfoVO){
		return studyManagementDAO.selectStudyMemberList(studyManagementInfoVO);
	}
	
	@Override
	public int selectStudyMemeberListToCnt(studyManagementInfoVO studyManagementInfoVO)	{
		return studyManagementDAO.selectStudyMemeberListToCnt(studyManagementInfoVO);
	}

}
