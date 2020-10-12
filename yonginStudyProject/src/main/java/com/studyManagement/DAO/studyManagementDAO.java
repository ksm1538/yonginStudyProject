package com.studyManagement.DAO; 

import java.util.List;

import com.notice.VO.boardVO;
import com.main.VO.userInStudyVO;

public interface studyManagementDAO {

	public List<userInStudyVO> selectStudyMemberList(userInStudyVO userInStudyVO);
	
	public int selectStudyMemberListToCnt(userInStudyVO userInStudyVO);
	
	public void writeStudyFreeNotice(boardVO data) throws Exception;
	
	public int selectStudyFreeNoticeListToCnt(boardVO boardVO);
	
	public List<boardVO> selectStudyFreeNoticeList(boardVO boardVO);
	
	public boardVO selectStudyFreeNoticeInfoDetail(String boardCode);
	
	public void updateStudyFreeNoticeCnt(String boardCode) throws Exception;
	
	public void reviseStudyFreeNotice(boardVO data) throws Exception;
	
	public void deleteStudyFreeNotice(String boardCode) throws Exception;
}
