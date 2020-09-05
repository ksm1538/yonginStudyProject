package com.commonCode.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commonCode.VO.commonCodeVO;

@Repository
public class commonCodeDAOImpl implements commonCodeDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<commonCodeVO> selectCommonCodeList(String code) throws Exception{
		return sqlSession.selectList("commonCode.selectCommonCodeList", code);
	}
}
