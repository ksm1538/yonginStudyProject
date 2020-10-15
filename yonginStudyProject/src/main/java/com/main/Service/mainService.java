package com.main.Service;

import java.util.List;

import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;
import com.main.VO.studyNoticeInfoVO;
import com.notice.VO.boardVO;

public interface mainService {
	
	List<studyInfoVO> selectStudyList(String userCode);

	List<calendarVO> searchMyStudyCalendar(calendarVO calendarVO);
	
	int selectStudyNoticeListToCnt(boardVO boardVO);
	
	List<boardVO> selectStudyNoticeList(boardVO boardVO);
	
	studyNoticeInfoVO selectStudyNoticeInfoDetail(String studyNoticeCode);
	
	void updateStudyNoticeCnt(String data);
}
