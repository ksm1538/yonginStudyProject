package com.main.DAO;

import java.util.List;

import com.login.VO.userInfoVO;

public interface adminDAO {
	public int selectUserListToCnt(userInfoVO userInfoVO);
	
	public List<userInfoVO> selectUserList(userInfoVO userInfoVO);
	
	public void cancleAdminUser(String userCode); 
	
	public void setAdminUser(String userCode); 
	
	public void deleteStudy(String studyCode); 
	
	public void kickUser(String userCode);
}
