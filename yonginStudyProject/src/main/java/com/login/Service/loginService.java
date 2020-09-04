package com.login.Service;

import com.login.VO.userInfoVO;

public interface loginService {
	
	void insertMember(userInfoVO data) throws Exception; 

	userInfoVO login(userInfoVO userInfoVO) throws Exception;
}
