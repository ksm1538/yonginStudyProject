package com.main.Service;

import java.util.List;

import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;
import com.main.VO.studyNoticeInfoVO;

public interface mainService {
	
	List<studyInfoVO> selectStudyList(String userCode);

	List<calendarVO> searchMyStudyCalendar(calendarVO calendarVO);
	
	int selectStudyNoticeListToCnt(studyNoticeInfoVO studyNoticeInfoVO);
	
	List<studyNoticeInfoVO> selectStudyNoticeList(studyNoticeInfoVO studyNoticeInfoVO);
	
	studyNoticeInfoVO selectStudyNoticeInfoDetail(String studyNoticeCode);
	
	void updateStudyNoticeCnt(String data);
}
