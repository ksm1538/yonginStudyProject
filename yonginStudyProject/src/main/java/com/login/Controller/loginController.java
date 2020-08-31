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
public class loginController {
	@Resource(name="testService") // 해당 서비스가 리소스임을 표시합니다.
	private testService testService;
	
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	/**
	 * 로그인 화면 Controller
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String aa = testService.aa();
		List<testVO> testList = testService.selectlistService();
		
		System.out.println("TEST  : "+aa);
		String aaaa = testList.get(0).getColumn1();
		
		model.addAttribute("testList", aaaa);
		return "jsp/login/login";
	}
	
	/**
	 * 회원가입 팝업 Mapping
	 */
	@RequestMapping(value = "/login/registerForm.do", method = RequestMethod.GET)
	public String registerForm() {
		
		return "jsp/login/registerMember";
	}
	
	@RequestMapping(value="/login/testAjax.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> testAjaxFunction(@RequestBody String data) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		// 위에 RequestBody 부분으로 JS에서 들어오는 데이터를 표현 근데 js에서 받은 데이터가 1이라는게 정확히 어떤상황에서 jsㅇ가 값을 넘겨준다는거임 ?ㅇㅇ
		// js에서 받은 데이터가 "1"일 때 DB에서 값 가져옴
		if(data.equals("1")) {
			List<testVO> testList = testService.selectlistService();
			
			mReturn.put("resultList", testList);
			mReturn.put("message", "성공");
		    mReturn.put("result", "success");   
		}
		else {
			mReturn.put("resultList", null);
			mReturn.put("message", "실패");
		    mReturn.put("result", "fail");   
		}
	    
	    return mReturn;
	}
	
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
