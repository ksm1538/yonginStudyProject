package com.study.Controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.Controller.registerMemeberController;
import com.login.Service.loginService;

@Controller
public class makeStudyController {
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService; 
	
	
	private static final Logger logger = LoggerFactory.getLogger(makeStudyController.class);
	
	/**
	 * 스터디 생성 Mapping
	 */
	@RequestMapping(value = "/makeStudy.do", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		return "jsp/study/makeStudy";
	}
}
