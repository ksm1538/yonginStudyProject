package com.login.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.login.VO.userInfoVO;

@Repository
public class loginDAOImpl implements loginDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insertMember(userInfoVO data) throws Exception{
		sqlSession.insert("loginMapper.insertMember", data); 
	}
	
	@Override
	public userInfoVO login(userInfoVO userInfoVO) throws Exception{
		return sqlSession.selectOne("loginMapper.login", userInfoVO); 
	}
	
	@Override
	public int selectSameId(String userId) throws Exception{
		return sqlSession.selectOne("loginMapper.selectSameId", userId);
	}
	
	@Override
	public int selectSameEmail(String userEmail) throws Exception{
		return sqlSession.selectOne("loginMapper.selectSameEmail", userEmail);
	}
}
