package com.qna.DAO; 

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.notice.VO.boardVO;

@Repository
public class qnaDAOImpl implements qnaDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insertQna(boardVO data) throws Exception{
		sqlSession.insert("systemQnaSQL.insertQna", data);
	}
	
	@Override
	public List<boardVO> selectQnaList(boardVO boardVO){
		return sqlSession.selectList("systemQnaSQL.selectQnaList", boardVO);
	}
	
	@Override
	public int selectQnaListToCnt(boardVO boardVO) {
		return sqlSession.selectOne("systemQnaSQL.selectQnaListToCnt", boardVO);
	}
	
	@Override
	public void reviseQna(boardVO data) throws Exception{
		sqlSession.update("systemQnaSQL.reviseQna", data);
	}
	
	@Override
	public void updateQnaStatus(String boardCode) throws Exception{
		sqlSession.update("systemQnaSQL.updateQnaStatus", boardCode);
	}
	
	@Override
	public boardVO selectQnaAnswerDetail(String boardCode) throws Exception{
		return sqlSession.selectOne("systemQnaSQL.selectQnaAnswerDetail", boardCode);
	}
	
	@Override
	public void updateQnaStatusTo10(String boardCode) throws Exception{
		sqlSession.update("systemQnaSQL.updateQnaStatusTo10", boardCode);
	}
}