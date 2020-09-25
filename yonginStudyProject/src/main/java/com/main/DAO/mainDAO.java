package com.main.DAO;

import java.util.List;

import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;

public interface mainDAO {
	public List<studyInfoVO> selectStudyList(String userCode);
	
	public List<calendarVO> searchMyStudyCalendar(calendarVO calendarVO);
}
