package com.main.DAO;

import java.util.List;

import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;
import com.main.VO.studyNoticeInfoVO;

public interface mainDAO {
	public List<studyInfoVO> selectStudyList(String userCode);
	
	public List<calendarVO> searchMyStudyCalendar(calendarVO calendarVO);
	
	public int selectStudyNoticeListToCnt(studyNoticeInfoVO studyNoticeInfoVO);
	
	public List<studyNoticeInfoVO> selectStudyNoticeList(studyNoticeInfoVO studyNoticeInfoVO);
	
	public studyNoticeInfoVO selectStudyNoticeInfoDetail(String studyNoticeCode);
	
	public void updateStudyNoticeCnt(String data);
}
