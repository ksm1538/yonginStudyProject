package com.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.VO.userInfoVO;
import com.main.DAO.myPageDAO;
import com.main.VO.studyInfoVO;
import com.main.VO.userInStudyVO;
import com.study.VO.studyApplicationFormUserVO;

@Service("myPageService")
public class myPageServiceImpl implements myPageService{
	@Autowired
	myPageDAO myPageDAO;
	
	@Override
	public userInfoVO selectUserInfoData(String userCode) {
		return myPageDAO.selectUserInfoData(userCode);
	}
	
	@Override
	public void updateUserInfo(userInfoVO userInfoVO) {
		myPageDAO.updateUserInfo(userInfoVO);
	}
	
	@Override
	public List<studyInfoVO> selectStudyMadeByMeList(String userCode){
		return myPageDAO.selectStudyMadeByMeList(userCode);
	}
	
	@Override
	public List<studyInfoVO> selectParticipateStudyList(String userCode){
		return myPageDAO.selectParticipateStudyList(userCode);
	}
	
	@Override
	public List<studyApplicationFormUserVO> selectMyStudyApplicationFormList(String userCode){
		return myPageDAO.selectMyStudyApplicationFormList(userCode);
	}
	
	@Override
	public void exitStudy(userInStudyVO userInStudyVO) throws Exception{
		myPageDAO.exitStudy(userInStudyVO);
	}
	
	@Override
	public void cancelStudyForm(studyApplicationFormUserVO vo) throws Exception{
		myPageDAO.cancelStudyForm(vo);
	}
	
	@Override
	public void deleteStudyForm(studyApplicationFormUserVO vo) throws Exception{
		myPageDAO.deleteStudyForm(vo);
	}
}
