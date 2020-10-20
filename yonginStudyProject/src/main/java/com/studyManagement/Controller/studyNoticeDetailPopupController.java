package com.studyManagement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonFunction.Service.fileService;
import com.login.VO.userInfoVO;
import com.studyManagement.Service.studyNoticeService;
import com.notice.VO.boardVO;

@Controller
public class studyNoticeDetailPopupController {
	
	@Resource(name="studyNoticeService")
	private studyNoticeService studyNoticeService;
	
	@Resource(name="fileService")
	private fileService fileService;
	
	@RequestMapping(value = "/studyManagement/studyNoticeDetailPopup.do", method = RequestMethod.POST)
	public String studyInfoDetailPopup(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("boardVO", new boardVO());
		
		return "jsp/studyManagement/studyNoticeDetailPopup"; 
	}
	
	/**
	 * 스터디 공지사항 상세 보기 
	 * @param systemNoticeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/selectStudyNoticeDetail.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyNoticeDetail(@RequestBody String boardCode) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(boardCode == null || boardCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		boardVO boardVO = studyNoticeService.selectStudyNoticeInfoDetail(boardCode);
		
		if(boardVO == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		List<Map<String, Object>> fileList = fileService.selectFileList(boardCode);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("boardInfo", boardVO);
		mReturn.put("fileList", fileList);
		
		return mReturn;
	}
}

