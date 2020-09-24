package com.main.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.login.VO.userInfoVO;

@Repository
public class adminDAOImpl implements adminDAO{
	@Inject
	private SqlSession sqlSession;

	public int selectUserListToCnt(userInfoVO userInfoVO) {
		return sqlSession.selectOne("adminPageSql.selectUserListToCnt", userInfoVO);
	}
	
	public List<userInfoVO> selectUserList(userInfoVO userInfoVO) {
		return sqlSession.selectList("adminPageSql.selectUserList", userInfoVO);
	}
	
	public void kickUser(String userCode) {
		sqlSession.update("adminPageSql.kickUser", userCode);
	}
	
	public void cancleAdminUser(String userCode) {
		sqlSession.update("adminPageSql.cancleAdminUser", userCode);
	}
	
	public void setAdminUser(String userCode) {
		sqlSession.update("adminPageSql.setAdminUser", userCode);
	}
	
	public void deleteStudy(String studyCode) {
		sqlSession.update("adminPageSql.deleteStudy", studyCode);
	}
}
