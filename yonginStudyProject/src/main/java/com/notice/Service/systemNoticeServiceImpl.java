package com.notice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notice.DAO.systemNoticeDAO;
import com.notice.VO.moreNoticeInfoVO;

@Service("systemNoticeService")
public class systemNoticeServiceImpl implements systemNoticeService{
	@Autowired
	systemNoticeDAO systemNoticeDAO;
	
	@Override
	public List<moreNoticeInfoVO> selectSystemNoticeList(moreNoticeInfoVO moreNoticeInfoVO){
		return systemNoticeDAO.selectSystemNoticeList(moreNoticeInfoVO);
	}
	
	@Override
	public void insertSystemNotice(moreNoticeInfoVO data) throws Exception{ 
		systemNoticeDAO.insertSystemNotice(data);
	}
	
	@Override
	public void deleteSystemNotice(moreNoticeInfoVO data) throws Exception{
		String[] systemNoticeCodes = data.getSystemNoticeCodes();
		for(int i=0;i<data.getSystemNoticeCodes().length;i++) {
			systemNoticeDAO.deleteSystemNotice(systemNoticeCodes[i]);
		}
	}
	
	@Override
	public int selectSystemNoticeListToCnt(moreNoticeInfoVO moreNoticeInfoVO) {
		return systemNoticeDAO.selectSystemNoticeListToCnt(moreNoticeInfoVO);
	}
}