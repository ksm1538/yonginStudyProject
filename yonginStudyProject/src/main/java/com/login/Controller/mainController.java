package com.login.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.Service.loginService;
import com.login.Service.testService;
import com.login.VO.testVO;
import com.login.VO.userInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class mainController {
	@Resource(name="testService") // 해당 서비스가 리소스임을 표시합니다.
	private testService testService;
	
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	/**
	 * 로그인 화면 Controller
	 */
	@RequestMapping(value = "/main/main.do", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String aa = testService.aa();
		List<testVO> testList = testService.selectlistService();
		
		System.out.println("TEST  : "+aa);
		String aaaa = testList.get(0).getColumn1();
		
		model.addAttribute("testList", aaaa);
		return "jsp/main/main";
	}
	
}
	
	
	