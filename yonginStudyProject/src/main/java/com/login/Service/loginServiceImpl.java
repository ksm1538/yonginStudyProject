package com.login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.DAO.loginDAO;
import com.login.VO.userInfoVO;

@Service("loginService")
public class loginServiceImpl implements loginService{
	@Autowired
	loginDAO loginDAO;
	
	public void insertMember(userInfoVO data) throws Exception{ 
		loginDAO.insertMember(data);
	}
}
