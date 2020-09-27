package com.main.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.VO.userInfoVO;
import com.main.Service.mainService;
import com.main.VO.studyNoticeInfoVO;

@Controller
public class studyNoticeInfoDetailPopupController {

	@Resource(name="mainService")
	private mainService mainService;
	
	@RequestMapping(value = "/main/studyNoticeInfoDetailPopup.do", method = RequestMethod.POST)
	public String studyNoticeInfoDetailPopup(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("studyNoticeInfoVO", new studyNoticeInfoVO());
		
		return "jsp/main/studyNoticeInfoDetailPopup"; 
	}
	
	/**
	 * 공지사항 상세 보기 
	 * @param systemNoticeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main/selectStudyNoticeInfoDetail.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyNoticeInfoDetail(@RequestBody String studyNoticeCode) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(studyNoticeCode == null || studyNoticeCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		mainService.updateStudyNoticeCnt(studyNoticeCode);
		studyNoticeInfoVO studyNoticeInfo = mainService.selectStudyNoticeInfoDetail(studyNoticeCode);
		
		if(studyNoticeInfo == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("studyNoticeInfo", studyNoticeInfo);
		
		return mReturn;
	}
}
