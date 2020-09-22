package com.main.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class adminDAOImpl implements adminDAO{
	@Inject
	private SqlSession sqlSession;
	
}
