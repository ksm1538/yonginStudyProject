package com.studyManagement.DAO; 

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.main.VO.userInStudyVO;

@Repository
public class studyMainDAOImpl implements studyMainDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public String selectStudyUserInfo(userInStudyVO userinfo) throws Exception{
		return sqlSession.selectOne("studyManagementMainMapper.selectStudyUserInfo", userinfo);
	}
}
