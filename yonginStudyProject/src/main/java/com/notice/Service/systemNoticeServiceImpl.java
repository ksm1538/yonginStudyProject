package com.notice.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commonFunction.Controller.FileUtilsController;
import com.commonFunction.DAO.fileDAO;
import com.notice.DAO.systemNoticeDAO;
import com.notice.VO.moreNoticeInfoVO;

@Service("systemNoticeService")
public class systemNoticeServiceImpl implements systemNoticeService{
	@Autowired
	systemNoticeDAO systemNoticeDAO;
	
	@Autowired
	fileDAO fileDAO;
	
	@Override
	public List<moreNoticeInfoVO> selectSystemNoticeList(moreNoticeInfoVO moreNoticeInfoVO){
		return systemNoticeDAO.selectSystemNoticeList(moreNoticeInfoVO);
	}
	
	@Override
	public void insertSystemNotice(moreNoticeInfoVO data, MultipartHttpServletRequest mpRequest) throws Exception{ 
		systemNoticeDAO.insertSystemNotice(data);
		
		List<Map<String,Object>> list = FileUtilsController.parseInsertFileInfo(data, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			fileDAO.insertFile(list.get(i)); 
		}
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
	
	@Override
	public moreNoticeInfoVO selectSystemNoticeInfoDetail(String systemNoticeCode) {
		return systemNoticeDAO.selectSystemNoticeInfoDetail(systemNoticeCode);
	}
	
	@Override
	public void reviseSystemNotice(moreNoticeInfoVO data) throws Exception{
		systemNoticeDAO.reviseSystemNotice(data);
	}
	
	/*
	 * @Override public List<Map<String, Object>> selectFileList(String bCode)
	 * throws Exception{ return systemNoticeDAO.selectFileList(bCode); }
	 * 
	 * @Override public Map<String, Object> selectFileInfo(Map<String, Object> map)
	 * throws Exception { return systemNoticeDAO.selectFileInfo(map); }
	 */
	
}