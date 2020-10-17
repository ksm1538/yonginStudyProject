package com.main.Service;

import java.util.List;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;
import com.main.VO.userInStudyVO;
import com.study.VO.studyApplicationFormUserVO;

public interface myPageService {
	userInfoVO selectUserInfoData(String userCode);
	
	void updateUserInfo(userInfoVO userInfoVO);
	
	List<studyInfoVO> selectStudyMadeByMeList(String userCode);
	
	List<studyInfoVO> selectParticipateStudyList(String userCode);
	
	List<studyApplicationFormUserVO> selectMyStudyApplicationFormList(String userCode);
	
	void exitStudy(userInStudyVO userInStudyVO) throws Exception;
	
	void cancelStudyForm(studyApplicationFormUserVO vo) throws Exception;
	
	void deleteStudyForm(studyApplicationFormUserVO vo) throws Exception;
}
