package com.main.DAO;

import java.util.List;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;
import com.main.VO.userInStudyVO;
import com.study.VO.studyApplicationFormUserVO;

public interface myPageDAO {
	public userInfoVO selectUserInfoData(String userCode);
	
	public void updateUserInfo(userInfoVO userInfoVO);
	
	public List<studyInfoVO> selectStudyMadeByMeList(String userCode);
	
	public List<studyInfoVO> selectParticipateStudyList(String userCode);
	
	public List<studyApplicationFormUserVO> selectMyStudyApplicationFormList(String userCode);
	
	public void exitStudy(userInStudyVO userInStudyVO) throws Exception;
	
	public void cancelStudyForm(studyApplicationFormUserVO vo) throws Exception;
	
	public void deleteStudyForm(studyApplicationFormUserVO vo) throws Exception;
}
