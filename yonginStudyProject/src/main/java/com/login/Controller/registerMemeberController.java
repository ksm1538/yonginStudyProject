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
public class registerMemeberController {
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(registerMemeberController.class);
	
	/**
	 * 회원가입 팝업 Mapping
	 */
	@RequestMapping(value = "/login/registerForm.do", method = RequestMethod.GET)
	public String registerForm() {
		
		return "jsp/login/registerMember";
	}
	
	/**
	 * 회원가입
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/registerMember/registerAjax.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerAjaxFunction(@RequestBody userInfoVO data) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		loginService.insertMember(data);
		
		mReturn.put("result", "success");
		mReturn.put("message", "저장에 성공했습니다.");
		
		return mReturn;
	}
}
