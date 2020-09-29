package com.notice.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.notice.VO.moreNoticeInfoVO;

public interface systemNoticeService {

	List<moreNoticeInfoVO> selectSystemNoticeList(moreNoticeInfoVO moreNoticeInfoVO);
	
	void insertSystemNotice(moreNoticeInfoVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	
	void deleteSystemNotice(moreNoticeInfoVO data) throws Exception;
	
	int selectSystemNoticeListToCnt(moreNoticeInfoVO moreNoticeInfoVO);
	
	moreNoticeInfoVO selectSystemNoticeInfoDetail(String systemNoticeCode) throws Exception;
	
	void reviseSystemNotice(moreNoticeInfoVO data, MultipartHttpServletRequest mpRequest) throws Exception;
	

    
}