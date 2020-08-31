package com.login.DAO;

import java.util.List;

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
}
