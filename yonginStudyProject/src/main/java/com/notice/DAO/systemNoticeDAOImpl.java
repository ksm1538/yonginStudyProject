package com.notice.DAO; 

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.notice.VO.boardVO;

@Repository
public class systemNoticeDAOImpl implements systemNoticeDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<boardVO> selectSystemNoticeList(boardVO boardVO){
		return sqlSession.selectList("systemNoticeMapper.selectSystemNoticeList", boardVO);
	}
	
	@Override
	public void insertSystemNotice(boardVO data) throws Exception{
		sqlSession.insert("systemNoticeMapper.insertSystemNotice", data);
	}
	
	@Override
	public void deleteSystemNotice(String boardCode) throws Exception{
		sqlSession.delete("systemNoticeMapper.deleteSystemNotice", boardCode);
	}
	
	@Override
	public int selectSystemNoticeListToCnt(boardVO boardVO) {
		return sqlSession.selectOne("systemNoticeMapper.selectSystemNoticeListToCnt", boardVO);
	}
	
	@Override
	public boardVO selectSystemNoticeInfoDetail(String boardCode) {
		return sqlSession.selectOne("systemNoticeMapper.selectSystemNoticeInfoDetail", boardCode);
	}
	
	@Override
	public void reviseSystemNotice(boardVO data) throws Exception{
		sqlSession.update("systemNoticeMapper.reviseSystemNotice", data);
	}
	
	@Override
	public void updateSystemNoticeCnt(String boardCode) throws Exception{
		sqlSession.update("systemNoticeMapper.updateSystemNoticeCnt", boardCode);
	}
	
}