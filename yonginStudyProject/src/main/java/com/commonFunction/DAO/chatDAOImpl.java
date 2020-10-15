package com.commonFunction.DAO; 

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commonFunction.VO.chatMessageVO;

@Repository
public class chatDAOImpl implements chatDAO{
	@Inject
	private SqlSession sqlSession;

	@Override
	public void insertChat(chatMessageVO chatMessageVO) throws Exception{
		sqlSession.insert("chatSQL.insertChat", chatMessageVO);
	}
}