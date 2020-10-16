package com.studyManagement.Service; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.VO.calendarVO;
import com.main.VO.userInStudyVO;
import com.studyManagement.DAO.studyMainDAO;

@Service("studyMainService")
public class studyMainServiceImpl implements studyMainService{
	@Autowired
	studyMainDAO studyMainDAO;
	
	@Override
	public String selectStudyUserInfo(userInStudyVO userinfo) throws Exception{
		return studyMainDAO.selectStudyUserInfo(userinfo);
	}
	
	@Override
	public void insertCalendar(calendarVO calendarVO) {
		studyMainDAO.insertCalendar(calendarVO);
	}
	
	@Override
	public void updateCalendar(calendarVO calendarVO) {
		studyMainDAO.updateCalendar(calendarVO);
	}
	
	@Override
	public void deleteCalendar(calendarVO calendarVO) {
		studyMainDAO.deleteCalendar(calendarVO);
	}
	
	@Override
	public List<calendarVO> selectStudyCalendar(calendarVO calendarVO){
		return studyMainDAO.selectStudyCalendar(calendarVO);
	}

}
