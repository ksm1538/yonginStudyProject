package com.study.DAO;

import java.util.List;

import com.main.VO.studyInfoVO;
import com.study.VO.studyApplicationFormUserVO;

public interface studyDAO {

	public int selectSameStudyName(String studyName) throws Exception;
	
	public void insertStudy(studyInfoVO data) throws Exception;
	
	public List<studyInfoVO> selectStudyList();
	
	public studyInfoVO selectStudyInfoDetail(String studyCode);
	
	public void insertStudyApplicationFormUser(studyApplicationFormUserVO studyApplicationFormUserVO);
	
	public int selectStudyApplicationFormCount(studyApplicationFormUserVO studyApplicationFormUserVO);
	
	public void updateStudyApplicationFormUser(studyApplicationFormUserVO studyApplicationFormUserVO);
	
	public studyApplicationFormUserVO selectStudyApplicationForm(studyApplicationFormUserVO studyApplicationFormUserVO);
}
 