package com.main.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;
import com.main.VO.studyNoticeInfoVO;

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
	public int selectStudyNoticeListToCnt(studyNoticeInfoVO studyNoticeInfoVO) {
		return sqlSession.selectOne("mainMapper.selectStudyNoticeListToCnt", studyNoticeInfoVO);
	}
	
	@Override
	public List<studyNoticeInfoVO> selectStudyNoticeList(studyNoticeInfoVO studyNoticeInfoVO){
		return sqlSession.selectList("mainMapper.selectStudyNoticeList", studyNoticeInfoVO);
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
