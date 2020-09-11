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
	
	@Override
	public String selectIdWithData(userInfoVO userInfoVO) throws Exception{
		return sqlSession.selectOne("loginMapper.selectIdWithData", userInfoVO);
	}
	
	@Override
	public userInfoVO selectUserInfoWithData(userInfoVO userInfoVO) throws Exception{
		System.out.println("이름 : "+userInfoVO.getUserName().length());
		System.out.println("아이디 : "+userInfoVO.getUserId().length());
		System.out.println("비밀번호 힌트 코드 : "+userInfoVO.getUserPwHintCode().length());
		System.out.println("비밀번호 힌트 답 : "+userInfoVO.getUserPwHintAnswer().length());
		
		return sqlSession.selectOne("loginMapper.selectUserInfoWithData", userInfoVO);
	}
	
	@Override
	public void updatePw(userInfoVO userInfoVO) throws Exception{
		sqlSession.update("loginMapper.updatePw", userInfoVO);
	}
}
