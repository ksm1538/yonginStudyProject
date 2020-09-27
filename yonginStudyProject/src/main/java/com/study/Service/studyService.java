package com.study.Service; 

import java.util.List;

import com.main.VO.studyInfoVO;
import com.study.VO.studyApplicationFormUserVO;

public interface studyService {
	
	int selectSameStudyName(String studyName) throws Exception;
	
	void insertStudy(studyInfoVO data) throws Exception; 
	
	List<studyInfoVO> selectStudyList(studyInfoVO studyInfoVO);
	
	studyInfoVO selectStudyInfoDetail(String studyCode);
	
	void insertStudyApplicationFormUser(studyApplicationFormUserVO studyApplicationFormUserVO);
	
	int selectStudyApplicationFormCount(studyApplicationFormUserVO studyApplicationFormUserVO);
	
	void updateStudyApplicationFormUser(studyApplicationFormUserVO studyApplicationFormUserVO);
	
	studyApplicationFormUserVO selectStudyApplicationForm(studyApplicationFormUserVO studyApplicationFormUserVO);
	
	int selectUserInStudyCount(studyApplicationFormUserVO studyApplicationFormUserVO);
	
	int selectStudyListToCnt(studyInfoVO studyInfoVO);
}