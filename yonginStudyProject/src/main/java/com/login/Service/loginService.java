package com.login.Service;

import com.login.VO.userInfoVO;

public interface loginService {
	
	void insertMember(userInfoVO data) throws Exception; 

	userInfoVO login(userInfoVO userInfoVO) throws Exception;
	
	int selectSameId(String userId) throws Exception;
	
	int selectSameEmail(String userEmail) throws Exception;
	
	String selectIdWithData(userInfoVO userInfoVO) throws Exception;
	
	userInfoVO selectUserInfoWithData(userInfoVO userInfoVO) throws Exception;
	
	void updatePw(userInfoVO userInfoVO) throws Exception;
	
	int selectUserPwHint(userInfoVO userInfoVO) throws Exception;
}
