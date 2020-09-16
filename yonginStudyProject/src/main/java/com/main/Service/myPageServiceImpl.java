package com.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.VO.userInfoVO;
import com.main.DAO.myPageDAO;

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
}
