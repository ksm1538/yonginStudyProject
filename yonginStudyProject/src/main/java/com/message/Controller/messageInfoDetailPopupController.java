package com.message.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.VO.userInfoVO;
import com.message.Service.messageService;
import com.message.VO.messageInfoVO;

@Controller
public class messageInfoDetailPopupController {
	
	@Resource(name="messageService") // 해당 서비스가 리소스임을 표시합니다.
	private messageService messageService;
	
	/**
	 * 쪽지 상세 팝업 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/message/messageInfoDetailPopup.do", method = RequestMethod.POST)
	public String messageInfoDetailPopup(HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/message/messageInfoDetailPopup"; 
	}
	
	
	/**
	 * 쪽지 상세 팝업
	 * @param messageCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/message/selectMessageInfoDetail.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectMessageInfoDetail(@RequestBody String messageCode) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(messageCode == null || messageCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		messageInfoVO messageInfo = messageService.selectMessageInfoDetail(messageCode);
		
		if(messageInfo == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("messageInfo", messageInfo);
		
		return mReturn;
	}
}
