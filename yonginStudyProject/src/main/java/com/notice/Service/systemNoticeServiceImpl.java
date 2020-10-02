package com.notice.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commonFunction.Controller.FileUtilsController;
import com.commonFunction.DAO.fileDAO;
import com.commonFunction.DAO.replyDAO;
import com.notice.DAO.systemNoticeDAO;
import com.notice.VO.boardVO;

@Service("systemNoticeService")
public class systemNoticeServiceImpl implements systemNoticeService{
	@Autowired
	systemNoticeDAO systemNoticeDAO;
	
	@Autowired
	fileDAO fileDAO;
	
	@Autowired
	replyDAO replyDAO;
	
	@Override
	public List<boardVO> selectSystemNoticeList(boardVO boardVO){
		return systemNoticeDAO.selectSystemNoticeList(boardVO);
	}
	
	@Override
	public void insertSystemNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception{ 
		systemNoticeDAO.insertSystemNotice(data);
		
		List<Map<String,Object>> list = FileUtilsController.parseInsertFileInfo(data, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			fileDAO.insertFile(list.get(i)); 
		}
	}
	
	@Override
	public void deleteSystemNotice(boardVO boardVO) throws Exception{
		String[] boardCodes = boardVO.getBoardCodes();
		for(int i=0;i<boardCodes.length;i++) {
			systemNoticeDAO.deleteSystemNotice(boardCodes[i]);
			fileDAO.updateNFileWithDeleteBoard(boardCodes[i]);
			replyDAO.deleteReplyWithBoardCode(boardCodes[i]);
		}
		
	}
	
	@Override
	public int selectSystemNoticeListToCnt(boardVO boardVO) {
		return systemNoticeDAO.selectSystemNoticeListToCnt(boardVO);
	}
	
	@Override
	public boardVO selectSystemNoticeInfoDetail(String boardCode) throws Exception {
		systemNoticeDAO.updateSystemNoticeCnt(boardCode);
		return systemNoticeDAO.selectSystemNoticeInfoDetail(boardCode);
	}
	
	@Override
	public void reviseSystemNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception{
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