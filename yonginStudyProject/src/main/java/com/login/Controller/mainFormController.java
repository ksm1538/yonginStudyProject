package com.login.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.Service.loginService;
import com.login.VO.userInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class mainFormController{
	@Resource(name="loginService") // �ش� ���񽺰� ���ҽ����� ǥ���մϴ�.
	private loginService loginService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(mainFormController.class);
	
	/**
	 * ���͵� ���� Mapping
	 */
	@RequestMapping(value = "/mainopen.do", method = RequestMethod.GET)
	public String studyManagementForm() {
		
		return "jsp/main/main";
	}

}