package com.study.Service;

import com.main.VO.studyInfoVO;

public interface studyService {
	
	int selectSameStudyName(String studyName) throws Exception;
	
	void insertStudy(studyInfoVO data) throws Exception; 
}
