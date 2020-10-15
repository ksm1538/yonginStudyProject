package com.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.DAO.mainDAO;
import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;
import com.main.VO.studyNoticeInfoVO;
import com.notice.VO.boardVO;

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
	
	@Override
	public int selectStudyNoticeListToCnt(boardVO boardVO) {
		return mainDAO.selectStudyNoticeListToCnt(boardVO);
	}
	
	@Override
	public List<boardVO> selectStudyNoticeList(boardVO boardVO){
		return mainDAO.selectStudyNoticeList(boardVO);
	}
	
	@Override
	public studyNoticeInfoVO selectStudyNoticeInfoDetail(String studyNoticeCode) {
		return mainDAO.selectStudyNoticeInfoDetail(studyNoticeCode);
	}
	
	@Override
	public void updateStudyNoticeCnt(String data){
		mainDAO.updateStudyNoticeCnt(data);
	}
}
