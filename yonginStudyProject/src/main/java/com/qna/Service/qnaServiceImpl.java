package com.qna.Service;

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
import com.qna.DAO.qnaDAO;

@Service("qnaService")
public class qnaServiceImpl implements qnaService{
	@Autowired
	qnaDAO qnaDAO;
	
	@Autowired
	systemNoticeDAO systemNoticeDAO;
	
	@Autowired
	fileDAO fileDAO;
	
	@Autowired
	replyDAO replyDAO;
	
	@Override
	public void insertQna(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception{
		qnaDAO.insertQna(data);
		
		List<Map<String,Object>> list = FileUtilsController.parseInsertFileInfo(data, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			fileDAO.insertFile(list.get(i)); 
		}
		
		if(!(data.getHgrnkBoardCode().equals(""))) {
			qnaDAO.updateQnaStatus(data.getHgrnkBoardCode());
		}
	}
	
	@Override
	public List<boardVO> selectQnaList(boardVO boardVO){
		return qnaDAO.selectQnaList(boardVO);
	}
	
	@Override
	public int selectQnaListToCnt(boardVO boardVO) {
		return qnaDAO.selectQnaListToCnt(boardVO);
	}
	
	@Override
	public boardVO selectQnaDetail(String boardCode) throws Exception {
		systemNoticeDAO.updateSystemNoticeCnt(boardCode);
		return systemNoticeDAO.selectSystemNoticeInfoDetail(boardCode);
	}
	
	@Override
	public void deleteQna(String boardCode) throws Exception{
		systemNoticeDAO.deleteSystemNotice(boardCode);
		fileDAO.updateNFileWithDeleteBoard(boardCode);
		replyDAO.deleteReplyWithBoardCode(boardCode);
	}
	
	@Override
	public void reviseQna(boardVO data, MultipartHttpServletRequest mpRequest) throws Exception{
		qnaDAO.reviseQna(data);
		
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
	public boardVO selectQnaAnswerDetail(String boardCode) throws Exception{
		systemNoticeDAO.updateSystemNoticeCnt(boardCode);
		return qnaDAO.selectQnaAnswerDetail(boardCode);
	}
	
	@Override
	public void deleteQnaAnswer(boardVO boardVO) throws Exception{
		systemNoticeDAO.deleteSystemNotice(boardVO.getBoardCode());
		qnaDAO.updateQnaStatusTo10(boardVO.getHgrnkBoardCode());
		fileDAO.updateNFileWithDeleteBoard(boardVO.getBoardCode());
		replyDAO.deleteReplyWithBoardCode(boardVO.getBoardCode());
	}
	
}