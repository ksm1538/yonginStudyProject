package com.notice.Service;

import java.util.List;

import com.notice.VO.moreNoticeInfoVO;

public interface systemNoticeService {

	List<moreNoticeInfoVO> selectSystemNoticeList();
	
	void insertSystemNotice(moreNoticeInfoVO data) throws Exception;
	
	void deleteSystemNotice(moreNoticeInfoVO data) throws Exception;
}
