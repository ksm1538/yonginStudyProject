package com.login.DAO;

import com.login.VO.userInfoVO;

public interface loginDAO {
	public void insertMember(userInfoVO data) throws Exception; 
	
	public userInfoVO login(userInfoVO userInfoVO) throws Exception;
	
	public int selectSameId(String userId) throws Exception;
	
	public int selectSameEmail(String userEmail) throws Exception;
}
