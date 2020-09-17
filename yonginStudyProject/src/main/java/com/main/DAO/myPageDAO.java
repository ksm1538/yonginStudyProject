package com.main.DAO;

import java.util.List;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;

public interface myPageDAO {
	public userInfoVO selectUserInfoData(String userCode);
	
	public void updateUserInfo(userInfoVO userInfoVO);
	
	public List<studyInfoVO> selectStudyMadeByMeList(String userCode);
	
	public List<studyInfoVO> selectParticipateStudyList(String userCode);
}
