package com.studyManagement.DAO; 

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.main.VO.calendarVO;
import com.main.VO.userInStudyVO;

@Repository
public class studyMainDAOImpl implements studyMainDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public String selectStudyUserInfo(userInStudyVO userinfo) throws Exception{
		return sqlSession.selectOne("studyManagementMainMapper.selectStudyUserInfo", userinfo);
	}
	
	@Override
	public void insertCalendar(calendarVO calendarVO) {
		sqlSession.insert("studyManagementMainMapper.insertCalendar", calendarVO);
	}
	
	@Override
	public void updateCalendar(calendarVO calendarVO) {
		sqlSession.update("studyManagementMainMapper.updateCalendar", calendarVO);
	}
	
	@Override
	public void deleteCalendar(calendarVO calendarVO) {
		sqlSession.update("studyManagementMainMapper.updateCalendarToN", calendarVO);
	}
	
	@Override
	public List<calendarVO> selectStudyCalendar(calendarVO calendarVO){
		return sqlSession.selectList("studyManagementMainMapper.selectStudyCalendar", calendarVO);
	}
}
