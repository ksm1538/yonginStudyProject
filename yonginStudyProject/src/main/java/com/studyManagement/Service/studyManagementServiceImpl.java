package com.studyManagement.Service; 

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commonFunction.Controller.FileUtilsController;
import com.commonFunction.DAO.fileDAO;
import com.studyManagement.DAO.studyManagementDAO;
import com.studyManagement.VO.studyManagementInfoVO;
import com.notice.VO.boardVO;

@Service("studyManagementService")
public class studyManagementServiceImpl implements studyManagementService{
	@Autowired
	studyManagementDAO studyManagementDAO;
	
	@Autowired
	fileDAO fileDAO;
	
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

}
