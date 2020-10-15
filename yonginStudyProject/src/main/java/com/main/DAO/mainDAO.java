package com.main.DAO;

import java.util.List;

import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;
import com.main.VO.studyNoticeInfoVO;
import com.notice.VO.boardVO;

public interface mainDAO {
	public List<studyInfoVO> selectStudyList(String userCode);
	
	public List<calendarVO> searchMyStudyCalendar(calendarVO calendarVO);
	
	public int selectStudyNoticeListToCnt(boardVO boardVO);
	
	public List<boardVO> selectStudyNoticeList(boardVO boardVO);
	
	public studyNoticeInfoVO selectStudyNoticeInfoDetail(String studyNoticeCode);
	
	public void updateStudyNoticeCnt(String data);
}
