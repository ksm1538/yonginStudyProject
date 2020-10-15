package com.studyManagement.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.notice.VO.boardVO;

@Repository
public class studyNoticeDAOImpl implements studyNoticeDAO {
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<boardVO> selectStudyNoticeList(boardVO boardVO){
		return sqlSession.selectList("studyNoticeMapper.selectStudyNoticeList", boardVO);
	}
	
	@Override
	public void insertStudyNotice(boardVO data) throws Exception{
		sqlSession.insert("studyNoticeMapper.insertStudyNotice", data);
	}
	
	@Override
	public void deleteStudyNotice(String boardCode) throws Exception{
		sqlSession.delete("studyNoticeMapper.deleteStudyNotice", boardCode);
	}
	
	@Override
	public int selectStudyNoticeListToCnt(boardVO boardVO) {
		return sqlSession.selectOne("studyNoticeMapper.selectStudyNoticeListToCnt", boardVO);
	}
	
	@Override
	public boardVO selectStudyNoticeInfoDetail(String boardCode) {
		return sqlSession.selectOne("studyNoticeMapper.selectStudyNoticeInfoDetail", boardCode);
	}
	
	@Override
	public void reviseStudyNotice(boardVO data) throws Exception{
		sqlSession.update("studyNoticeMapper.reviseStudyNotice", data);
	}
	
	@Override
	public void updateStudyNoticeCnt(String boardCode) throws Exception{
		sqlSession.update("studyNoticeMapper.updateStudyNoticeCnt", boardCode);
	}

}
