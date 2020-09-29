package com.notice.DAO;

import java.util.List;

import com.notice.VO.boardVO;

public interface systemNoticeDAO {

	public List<boardVO> selectSystemNoticeList(boardVO boardVO);
	
	public void insertSystemNotice(boardVO data) throws Exception;
	
	public void deleteSystemNotice(String boardCode) throws Exception;
	
	public int selectSystemNoticeListToCnt(boardVO boardVO);
	
	public boardVO selectSystemNoticeInfoDetail(String boardCode);
	
	public void reviseSystemNotice(boardVO data) throws Exception;
	
	public void updateSystemNoticeCnt(String boardCode) throws Exception;

}