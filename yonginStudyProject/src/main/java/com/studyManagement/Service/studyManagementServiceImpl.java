package com.studyManagement.Service; 

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commonFunction.Controller.FileUtilsController;
import com.commonFunction.DAO.fileDAO;
import com.commonFunction.DAO.replyDAO;
import com.studyManagement.DAO.studyManagementDAO;
import com.studyManagement.VO.studyManagementInfoVO;
import com.notice.VO.boardVO;

@Service("studyManagementService")
public class studyManagementServiceImpl implements studyManagementService{
	@Autowired
	studyManagementDAO studyManagementDAO;
	
	@Autowired
	fileDAO fileDAO;
	
	@Autowired
	replyDAO replyDAO;
	
	@Override
	public List<studyManagementInfoVO> selectStudyMemberList(studyManagementInfoVO studyManagementInfoVO){
		return studyManagementDAO.selectStudyMemberList(studyManagementInfoVO);
	}
	
	@Override
	public int selectStudyMemeberListToCnt(studyManagementInfoVO studyManagementInfoVO)	{
		return studyManagementDAO.selectStudyMemeberListToCnt(studyManagementInfoVO);
	}
	
	@Override
	public void writeStudyFreeNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception{ 
		studyManagementDAO.writeStudyFreeNotice(data);
		
		List<Map<String,Object>> list = FileUtilsController.parseInsertFileInfo(data, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			fileDAO.insertFile(list.get(i)); 
		}
	}
	
	@Override
	public int selectStudyFreeNoticeListToCnt(boardVO boardVO) {
		return studyManagementDAO.selectStudyFreeNoticeListToCnt(boardVO);
	}
	
	@Override
	public List<boardVO> selectStudyFreeNoticeList(boardVO boardVO){
		return studyManagementDAO.selectStudyFreeNoticeList(boardVO);
	}
	
	@Override
	public boardVO selectStudyFreeNoticeInfoDetail(String boardCode) throws Exception {
		studyManagementDAO.updateStudyFreeNoticeCnt(boardCode);
		return studyManagementDAO.selectStudyFreeNoticeInfoDetail(boardCode);
	}
	
	@Override
	public void reviseStudyFreeNotice(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception{
		studyManagementDAO.reviseStudyFreeNotice(data);
		
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
	
	@Override
	public void deleteStudyFreeNotice(boardVO boardVO) throws Exception{
			String boardCode = boardVO.getBoardCode();
			studyManagementDAO.deleteStudyFreeNotice(boardCode);
			fileDAO.updateNFileWithDeleteBoard(boardCode);
			replyDAO.deleteReplyWithBoardCode(boardCode);
		
	}

}
