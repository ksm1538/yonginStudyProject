package com.main.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;

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
	
	@Override
	public List<studyInfoVO> selectStudyMadeByMeList(String userCode){
		return sqlSession.selectList("myPageSql.selectStudyMadeByMeList", userCode);
	}
	
	@Override
	public List<studyInfoVO> selectParticipateStudyList(String userCode){
		return sqlSession.selectList("myPageSql.selectParticipateStudyList", userCode);
	}
}
