package com.studyManagement.Service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.VO.userInStudyVO;
import com.studyManagement.DAO.studyMainDAO;

@Service("studyMainService")
public class studyMainServiceImpl implements studyMainService{
	@Autowired
	studyMainDAO studyMainDAO;
	
	@Override
	public String selectStudyUserInfo(userInStudyVO userinfo) throws Exception{
		return studyMainDAO.selectStudyUserInfo(userinfo);
	}

}
