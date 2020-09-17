package com.main.Service;

import java.util.List;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;

public interface myPageService {
	userInfoVO selectUserInfoData(String userCode);
	
	void updateUserInfo(userInfoVO userInfoVO);
	
	List<studyInfoVO> selectStudyMadeByMeList(String userCode);
	
	List<studyInfoVO> selectParticipateStudyList(String userCode);
}
