package com.message.Controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.VO.userInfoVO;
import com.message.Service.messageService;
import com.message.VO.messageInfoVO;

@Controller
public class sendMessageController {

	
	@Resource(name="messageService") // 해당 서비스가 리소스임을 표시합니다.
	private messageService messageService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(messageListController.class);
	
	/**
	 * 쪽지 보내기 Mapping
	 */
	@RequestMapping(value = "/sendMessage.do", method = RequestMethod.GET)
	public String sendMessageForm(HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/message/sendMessage";
	}
	
	/** 
	 * 쪽지 보내기
	 * @param messageInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sendMessage.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendDeleteAjaxFunction(@RequestBody messageInfoVO messageInfoVO, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		messageInfoVO.setUserCodeFrom(user.getUserCode());
		messageInfoVO.getUserCodeFrom();
		
		//벨리데이터 추가
		messageService.sendMessage(messageInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "전송되었습니다.");
		
		return mReturn;
	}
}
