package com.main.Service;

import com.login.VO.userInfoVO;

public interface myPageService {
	userInfoVO selectUserInfoData(String userCode);
	
	void updateUserInfo(userInfoVO userInfoVO);
}
