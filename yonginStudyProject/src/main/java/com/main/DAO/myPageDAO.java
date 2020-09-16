package com.main.DAO;

import com.login.VO.userInfoVO;

public interface myPageDAO {
	public userInfoVO selectUserInfoData(String userCode);
	
	public void updateUserInfo(userInfoVO userInfoVO);
}
