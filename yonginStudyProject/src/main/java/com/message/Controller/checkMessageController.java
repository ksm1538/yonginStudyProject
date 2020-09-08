package com.message.Controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.Service.loginService;

public class checkMessageController {

	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(messageListController.class);
	
	/**
	 * 쪽지함 Mapping
	 */
	@RequestMapping(value = "/checkMessage.do", method = RequestMethod.GET)
	public String MoreStudyForm(Locale locale) {
		 
		return "jsp/message/checkMessage";
	}
	
}
