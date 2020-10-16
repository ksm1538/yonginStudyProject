package com.studyManagement.Service;

import java.util.List;

import com.main.VO.calendarVO;
import com.main.VO.userInStudyVO;

public interface studyMainService {
	String selectStudyUserInfo(userInStudyVO userinfo) throws Exception;
	
	void insertCalendar(calendarVO calendarVO);
	
	void updateCalendar(calendarVO calendarVO);
	
	void deleteCalendar(calendarVO calendarVO);
	
	List<calendarVO> selectStudyCalendar(calendarVO calendarVO);
}
