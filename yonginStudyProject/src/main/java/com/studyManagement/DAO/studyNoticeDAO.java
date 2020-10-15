package com.studyManagement.DAO;

import java.util.List;

import com.notice.VO.boardVO;

public interface studyNoticeDAO {
	
	public List<boardVO> selectStudyNoticeList(boardVO boardVO);
	
	public void insertStudyNotice(boardVO data) throws Exception;
	
	public void deleteStudyNotice(String boardCode) throws Exception;
	
	public int selectStudyNoticeListToCnt(boardVO boardVO);
	
	public boardVO selectStudyNoticeInfoDetail(String boardCode);
	
	public void reviseStudyNotice(boardVO data) throws Exception;
	
	public void updateStudyNoticeCnt(String boardCode) throws Exception;
}
