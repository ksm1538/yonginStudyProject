package com.login.Service;

import com.login.VO.userInfoVO;

public interface loginService {
	
	void insertMember(userInfoVO data) throws Exception; 

	userInfoVO login(userInfoVO userInfoVO) throws Exception;
	
	int selectSameId(String userId) throws Exception;
	
	int selectSameEmail(String userEmail) throws Exception;
	
}
