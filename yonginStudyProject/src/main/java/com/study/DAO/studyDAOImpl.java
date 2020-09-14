package com.study.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.main.VO.studyInfoVO;

@Repository
public class studyDAOImpl implements studyDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int selectSameStudyName(String studyName) throws Exception{
		return sqlSession.selectOne("studyMapper.selectSameStudyName", studyName);
	}
	
	@Override
	public void insertStudy(studyInfoVO data) throws Exception{
		sqlSession.insert("studyMapper.insertStudy", data); 
	}
	
	@Override
	public List<studyInfoVO> selectStudyList(){
		return sqlSession.selectList("studyMapper.selectStudyList");
	}
}
 