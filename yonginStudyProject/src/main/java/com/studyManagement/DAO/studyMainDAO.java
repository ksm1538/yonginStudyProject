package com.studyManagement.DAO;

import java.util.List;

import com.main.VO.calendarVO;
import com.main.VO.userInStudyVO;

public interface studyMainDAO {
	public String selectStudyUserInfo(userInStudyVO userinfo) throws Exception;
	
	public void insertCalendar(calendarVO calendarVO);
	
	public void updateCalendar(calendarVO calendarVO);
	
	public void deleteCalendar(calendarVO calendarVO);
	
	public List<calendarVO> selectStudyCalendar(calendarVO calendarVO);
}
