package com.studyManagement.DAO; 

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class studyMainDAOImpl implements studyMainDAO{
	@Inject
	private SqlSession sqlSession;
}
