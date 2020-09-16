package com.main.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.login.VO.userInfoVO;

@Repository
public class myPageDAOImpl implements myPageDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public userInfoVO selectUserInfoData(String userCode) {
		return sqlSession.selectOne("myPageSql.selectUserInfoData", userCode);
	}
	
	@Override
	public void updateUserInfo(userInfoVO userInfoVO) {
		sqlSession.update("myPageSql.updateUserInfo", userInfoVO);
	}
}
