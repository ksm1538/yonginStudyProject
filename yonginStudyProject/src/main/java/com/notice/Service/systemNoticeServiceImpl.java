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
			fileDAO.updateNFileWithDeleteBoard(systemNoticeCodes[i]);
		}
	}
	
	@Override
	public int selectSystemNoticeListToCnt(moreNoticeInfoVO moreNoticeInfoVO) {
		return systemNoticeDAO.selectSystemNoticeListToCnt(moreNoticeInfoVO);
	}
	
	@Override
	public moreNoticeInfoVO selectSystemNoticeInfoDetail(String systemNoticeCode) throws Exception {
		systemNoticeDAO.updateSystemNoticeCnt(systemNoticeCode);
		return systemNoticeDAO.selectSystemNoticeInfoDetail(systemNoticeCode);
	}
	
	@Override
	public void reviseSystemNotice(moreNoticeInfoVO data, MultipartHttpServletRequest mpRequest) throws Exception{
		systemNoticeDAO.reviseSystemNotice(data);
		
		String[] files = data.getFileCodeDel();
		
		List<Map<String, Object>> list = FileUtilsController.parseUpdateFileInfo(data, files, mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				fileDAO.insertFile(tempMap);
			}else {
				fileDAO.updateFile(tempMap);
			}
		}
	}
	
	
}