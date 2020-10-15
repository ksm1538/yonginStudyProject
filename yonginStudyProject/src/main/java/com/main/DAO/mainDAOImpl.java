package com.main.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;
import com.main.VO.studyNoticeInfoVO;
import com.notice.VO.boardVO;

@Repository
public class mainDAOImpl implements mainDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<studyInfoVO> selectStudyList(String userCode){
		return sqlSession.selectList("mainMapper.selectStudyList", userCode);
	}
	
	@Override
	public List<calendarVO> searchMyStudyCalendar(calendarVO calendarVO){
		return sqlSession.selectList("mainMapper.searchMyStudyCalendar", calendarVO);
	}
	
	@Override
	public int selectStudyNoticeListToCnt(boardVO boardVO) {
		return sqlSession.selectOne("mainMapper.selectStudyNoticeListToCnt", boardVO);
	}
	
	@Override
	public List<boardVO> selectStudyNoticeList(boardVO boardVO){
		return sqlSession.selectList("mainMapper.selectStudyNoticeList", boardVO);
	}
	
	@Override
	public studyNoticeInfoVO selectStudyNoticeInfoDetail(String studyNoticeCode) {
		return sqlSession.selectOne("mainMapper.selectStudyNoticeInfoDetail", studyNoticeCode);
	}
	
	@Override
	public void updateStudyNoticeCnt(String data){
		sqlSession.update("mainMapper.updateStudyNoticeCnt", data);
	}
}
