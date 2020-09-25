package com.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.DAO.mainDAO;
import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;

@Service("mainService")
public class mainServiceImpl implements mainService{
	@Autowired
	mainDAO mainDAO;
	
	@Override
	public List<studyInfoVO> selectStudyList(String userCode){
		return mainDAO.selectStudyList(userCode);
	}
	
	@Override
	public List<calendarVO> searchMyStudyCalendar(calendarVO calendarVO){
		return mainDAO.searchMyStudyCalendar(calendarVO);
	}
}
