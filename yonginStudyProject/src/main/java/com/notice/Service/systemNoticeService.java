package com.notice.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.notice.VO.moreNoticeInfoVO;

public interface systemNoticeService {

	List<moreNoticeInfoVO> selectSystemNoticeList(moreNoticeInfoVO moreNoticeInfoVO);
	
	void insertSystemNotice(moreNoticeInfoVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	void deleteSystemNotice(moreNoticeInfoVO data) throws Exception;
	
	int selectSystemNoticeListToCnt(moreNoticeInfoVO moreNoticeInfoVO);
	
	moreNoticeInfoVO selectSystemNoticeInfoDetail(String systemNoticeCode);
	
	void reviseSystemNotice(moreNoticeInfoVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	/*
	 * List<Map<String, Object>> selectFileList(String bCode) throws Exception;
	 * 
	 * public Map<String, Object> selectFileInfo(Map<String, Object> map) throws
	 * Exception;
	 */
    
}