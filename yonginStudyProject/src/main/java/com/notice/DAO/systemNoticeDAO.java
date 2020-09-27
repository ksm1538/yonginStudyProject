package com.notice.DAO;

import java.util.List;

import com.notice.VO.moreNoticeInfoVO;

public interface systemNoticeDAO {

	public List<moreNoticeInfoVO> selectSystemNoticeList(moreNoticeInfoVO moreNoticeInfoVO);
	
	public void insertSystemNotice(moreNoticeInfoVO data) throws Exception;
	
	public void deleteSystemNotice(String messageCode) throws Exception;
	
	public int selectSystemNoticeListToCnt(moreNoticeInfoVO moreNoticeInfoVO);
	
	public moreNoticeInfoVO selectSystemNoticeInfoDetail(String systemNoticeCode);
	
	public void reviseSystemNotice(moreNoticeInfoVO data) throws Exception;
	
	/*
	 * public void insertFile(Map<String, Object> map) throws Exception;
	 * 
	 * public List<Map<String, Object>> selectFileList(String bCode) throws
	 * Exception;
	 * 
	 * public Map<String, Object> selectFileInfo(Map<String, Object> map) throws
	 * Exception;
	 */

}