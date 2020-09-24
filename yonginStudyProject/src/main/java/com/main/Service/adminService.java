package com.main.Service;

import java.util.List;

import com.login.VO.userInfoVO;

public interface adminService {
	int selectUserListToCnt(userInfoVO userInfoVO);
	
	List<userInfoVO> selectUserList(userInfoVO userInfoVO);
	
	void cancleAdminUser(String userCode); 
	
	void setAdminUser(String userCode); 
	
	void deleteStudy(String studyCode); 
	
	void kickUser(String userCode);
}
