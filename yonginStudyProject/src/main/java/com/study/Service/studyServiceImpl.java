package com.study.Service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.VO.studyInfoVO;
import com.study.DAO.studyDAO;
import com.study.VO.studyApplicationFormUserVO;

@Service("studyService")
public class studyServiceImpl implements studyService{
	@Autowired
	studyDAO studyDAO;
	
	@Override
	public int selectSameStudyName(String studyName) throws Exception{
		return studyDAO.selectSameStudyName(studyName);
	}
	
	@Override
	public void insertStudy(studyInfoVO data) throws Exception{ 
		//스터디 생성하고
		studyDAO.insertStudy(data);
		
		//스터디에 가입시켜줌
		studyDAO.insertUserInStudy(data);
	}
	
	@Override
	public List<studyInfoVO> selectStudyList(studyInfoVO studyInfoVO){
		return studyDAO.selectStudyList(studyInfoVO);
	}
	
	@Override
	public studyInfoVO selectStudyInfoDetail(String studyCode) {
		return studyDAO.selectStudyInfoDetail(studyCode);
	}
	
	@Override
	public void insertStudyApplicationFormUser(studyApplicationFormUserVO studyApplicationFormUserVO) {
		studyDAO.insertStudyApplicationFormUser(studyApplicationFormUserVO);
	}
	
	@Override
	public int selectStudyApplicationFormCount(studyApplicationFormUserVO studyApplicationFormUserVO) {
		return studyDAO.selectStudyApplicationFormCount(studyApplicationFormUserVO);
	}
	
	@Override
	public void updateStudyApplicationFormUser(studyApplicationFormUserVO studyApplicationFormUserVO) {
		studyDAO.updateStudyApplicationFormUser(studyApplicationFormUserVO);
	}
	
	@Override
	public studyApplicationFormUserVO selectStudyApplicationForm(studyApplicationFormUserVO studyApplicationFormUserVO) {
		return studyDAO.selectStudyApplicationForm(studyApplicationFormUserVO);
	}
	
	@Override
	public int selectUserInStudyCount(studyApplicationFormUserVO studyApplicationFormUserVO) {
		return studyDAO.selectUserInStudyCount(studyApplicationFormUserVO);
	}
	
	@Override
	public int selectStudyListToCnt(studyInfoVO studyInfoVO) {
		return studyDAO.selectStudyListToCnt(studyInfoVO);
	}
}
 