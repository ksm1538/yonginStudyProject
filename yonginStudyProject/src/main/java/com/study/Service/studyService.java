package com.study.Service;

import java.util.List;

import com.main.VO.studyInfoVO;

public interface studyService {
	
	int selectSameStudyName(String studyName) throws Exception;
	
	void insertStudy(studyInfoVO data) throws Exception; 
	
	List<studyInfoVO> selectStudyList();
	
	studyInfoVO selectStudyInfoDetail(String studyCode);
}
 