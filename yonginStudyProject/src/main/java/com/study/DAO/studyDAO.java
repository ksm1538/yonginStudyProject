package com.study.DAO;

import java.util.List;

import com.main.VO.studyInfoVO;

public interface studyDAO {

	public int selectSameStudyName(String studyName) throws Exception;
	
	public void insertStudy(studyInfoVO data) throws Exception;
	
	public List<studyInfoVO> selectStudyList();
}
 