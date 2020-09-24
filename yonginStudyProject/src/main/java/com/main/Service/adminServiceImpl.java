package com.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.VO.userInfoVO;
import com.main.DAO.adminDAO;

@Service("adminService")
public class adminServiceImpl implements adminService{
	@Autowired
	adminDAO adminDAO;
	
	@Override
	public int selectUserListToCnt(userInfoVO userInfoVO) {
		return adminDAO.selectUserListToCnt(userInfoVO);
	}

	@Override
	public List<userInfoVO> selectUserList(userInfoVO userInfoVO) {
		return adminDAO.selectUserList(userInfoVO);
	}
	
	@Override
	public void kickUser(String userCode) {
		adminDAO.kickUser(userCode);
	}
	
	@Override
	public void cancleAdminUser(String userCode) {
		 adminDAO.cancleAdminUser(userCode);
	}
	
	@Override
	public void setAdminUser(String userCode) {
		 adminDAO.setAdminUser(userCode);
	}
	
	@Override
	public void deleteStudy(String studyCode) {
		 adminDAO.deleteStudy(studyCode);
	}
}
