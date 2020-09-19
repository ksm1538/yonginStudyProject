package com.notice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notice.DAO.systemNoticeDAO;
import com.notice.VO.moreNoticeInfoVO;

@Service("systemNoticeService")
public class systemNoticeServiceImple implements systemNoticeService{
	@Autowired
	systemNoticeDAO systemNoticeDAO;
	
	@Override
	public List<moreNoticeInfoVO> selectSystemNoticeList(){
		return systemNoticeDAO.selectSystemNoticeList();
	}
	
	@Override
	public void insertSystemNotice(moreNoticeInfoVO data) throws Exception{ 
		systemNoticeDAO.insertSystemNotice(data);
	}
}
