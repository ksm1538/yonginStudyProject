package com.popit.start.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.popit.start.VO.testVO;

@Repository
public class testDAOImpl implements testDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
    public List<testVO> selectlist(){
        return sqlSession.selectList("testMapper.selectlist");
    }
}
