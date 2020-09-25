package com.main.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;

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
}
