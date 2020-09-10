package com.login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.DAO.loginDAO;
import com.login.VO.userInfoVO;

@Service("loginService")
public class loginServiceImpl implements loginService{
	@Autowired
	loginDAO loginDAO;
	
	@Override
	public void insertMember(userInfoVO data) throws Exception{ 
		loginDAO.insertMember(data);
	}
	
	@Override
	public userInfoVO login(userInfoVO userInfoVO) throws Exception{
		return loginDAO.login(userInfoVO);
	}
	
	@Override
	public int selectSameId(String userId) throws Exception{
		return loginDAO.selectSameId(userId);
	}
	
	@Override
	public int selectSameEmail(String userEmail) throws Exception{
		return loginDAO.selectSameEmail(userEmail);
	}
}
