package com.login.Controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.Service.testService;
import com.login.VO.testVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	@Resource(name="testService") // 해당 서비스가 리소스임을 표시합니다.
	private testService testService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 로그인 화면 Controller
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String aa = testService.aa();
		List<testVO> testList = testService.selectlistService();
		
		System.out.println("TEST  : "+aa);
		
		model.addAttribute("testList", testList);
		return "jsp/login/login";
	}
	
}
