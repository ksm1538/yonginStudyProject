package com.study.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.main.VO.studyInfoVO;
import com.study.VO.studyApplicationFormUserVO;

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
	
	@Override
	public studyInfoVO selectStudyInfoDetail(String studyCode) {
		return sqlSession.selectOne("studyMapper.selectStudyInfoDetail", studyCode);
	}
	
	@Override
	public void insertStudyApplicationFormUser(studyApplicationFormUserVO studyApplicationFormUserVO) {
		sqlSession.insert("studyMapper.insertStudyApplicationFormUser", studyApplicationFormUserVO);
	}
	
	@Override
	public int selectStudyApplicationFormCount(studyApplicationFormUserVO studyApplicationFormUserVO) {
		return sqlSession.selectOne("studyMapper.selectStudyApplicationFormCount", studyApplicationFormUserVO);
	}
	
	@Override
	public void updateStudyApplicationFormUser(studyApplicationFormUserVO studyApplicationFormUserVO) {
		sqlSession.update("studyMapper.updateStudyApplicationFormUser", studyApplicationFormUserVO);
	}
	
	@Override
	public studyApplicationFormUserVO selectStudyApplicationForm(studyApplicationFormUserVO studyApplicationFormUserVO) {
		return sqlSession.selectOne("studyMapper.selectStudyApplicationForm", studyApplicationFormUserVO);
	}
}
 