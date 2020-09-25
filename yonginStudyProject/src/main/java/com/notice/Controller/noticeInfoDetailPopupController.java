package com.notice.Controller;

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

import com.login.VO.userInfoVO;
import com.notice.VO.moreNoticeInfoVO;
import com.notice.Service.systemNoticeService;

@Controller
public class noticeInfoDetailPopupController {

	@Resource(name="systemNoticeService")
	private systemNoticeService systemNoticeService;
	
	@RequestMapping(value = "/notice/noticeInfoDetailPopup.do", method = RequestMethod.POST)
	public String studyInfoDetailPopup(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("moreNoticeInfoVO", new moreNoticeInfoVO());
		
		return "jsp/notice/noticeInfoDetailPopup"; 
	}
	
	@RequestMapping(value="/notice/selectNoticeInfoDetail.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyInfoDetail(@RequestBody String systemNoticeCode) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(systemNoticeCode == null || systemNoticeCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		moreNoticeInfoVO systemNoticeInfo = systemNoticeService.selectSystemNoticeInfoDetail(systemNoticeCode);
		
		if(systemNoticeInfo == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("systemNoticeInfo", systemNoticeInfo);
		
		return mReturn;
	}
}