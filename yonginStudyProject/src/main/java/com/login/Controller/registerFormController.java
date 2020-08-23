package com.login.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class registerFormController {
	
	private static final Logger logger = LoggerFactory.getLogger(registerFormController.class);
	
	/**
	 * 회원가입 팝업 Mapping
	 */
	@RequestMapping(value = "/login/registerForm.do", method = RequestMethod.GET)
	public String registerForm() {
		
		return "jsp/login/registerMember";
	}
	
}
