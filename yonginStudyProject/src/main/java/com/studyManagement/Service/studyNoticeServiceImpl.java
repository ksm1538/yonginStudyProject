package com.studyManagement.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commonFunction.Controller.FileUtilsController;
import com.commonFunction.DAO.fileDAO;
import com.commonFunction.DAO.replyDAO;
import com.studyManagement.DAO.studyNoticeDAO;
import com.notice.VO.boardVO;

@Service("studyNoticeService")
public class studyNoticeServiceImpl implements studyNoticeService {
	@Autowired
	studyNoticeDAO studyNoticeDAO;
	
	@Autowired
	fileDAO fileDAO;
	
	@Autowired
	replyDAO replyDAO;
	
	@Override
	public List<boardVO> selectStudyNoticeList(boardVO boardVO){
		return studyNoticeDAO.selectStudyNoticeList(boardVO);
	}
	
	@Override
	public void insertStudyNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception{ 
		studyNoticeDAO.insertStudyNotice(data);
		
		List<Map<String,Object>> list = FileUtilsController.parseInsertFileInfo(data, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			fileDAO.insertFile(list.get(i)); 
		}
	}
	
	@Override
	public void deleteStudyNotice(boardVO boardVO) throws Exception{
		String[] boardCodes = boardVO.getBoardCodes();
		for(int i=0;i<boardCodes.length;i++) {
			studyNoticeDAO.deleteStudyNotice(boardCodes[i]);
			fileDAO.updateNFileWithDeleteBoard(boardCodes[i]);
			replyDAO.deleteReplyWithBoardCode(boardCodes[i]);
		}
		
	}
	
	@Override
	public int selectStudyNoticeListToCnt(boardVO boardVO) {
		return studyNoticeDAO.selectStudyNoticeListToCnt(boardVO);
	}
	
	@Override
	public boardVO selectStudyNoticeInfoDetail(String boardCode) throws Exception {
		studyNoticeDAO.updateStudyNoticeCnt(boardCode);
		return studyNoticeDAO.selectStudyNoticeInfoDetail(boardCode);
	}
	
	@Override
	public void reviseStudyNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception{
		studyNoticeDAO.reviseStudyNotice(data);
		
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
