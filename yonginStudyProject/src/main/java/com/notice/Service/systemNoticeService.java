package com.notice.Service;

import java.util.List;

import com.notice.VO.moreNoticeInfoVO;

public interface systemNoticeService {

	List<moreNoticeInfoVO> selectSystemNoticeList(moreNoticeInfoVO moreNoticeInfoVO);
	
	void insertSystemNotice(moreNoticeInfoVO data) throws Exception;
	
	void deleteSystemNotice(moreNoticeInfoVO data) throws Exception;
	
	int selectSystemNoticeListToCnt(moreNoticeInfoVO moreNoticeInfoVO);
}