package com.login.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.login.Service.loginService;
import com.login.VO.userInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class registerMemeberController {
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Inject
    PasswordEncoder passwordEncoder;	// 암호화 기능 추가
	
	private static final Logger logger = LoggerFactory.getLogger(registerMemeberController.class);
	/**
	 * 회원가입 팝업 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/registerForm.do", method = RequestMethod.GET)
	public String registerForm(Model model) throws Exception {
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("pwHint");
		
		model.addAttribute("pwHint", codeResult);
		return "jsp/login/registerMember";
	}
	
	/**
	 * 회원가입
	 * @param userInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/registerMember.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerAjaxFunction(@RequestBody userInfoVO userInfoVO) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		//비밀번호 암호화
		userInfoVO.setUserPw(passwordEncoder.encode(userInfoVO.getUserPw()));
		
		//벨리데이터 추가
		loginService.insertMember(userInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 가입되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * ID중복체크
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/register/checkExsitingId.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkExsitingId(@RequestBody String userId) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		// 앞, 뒤 공백 제거
		userId = userId.trim();
		
		// 같은 ID를 사용하는 데이터의 갯수 반환
		int count = loginService.selectSameId(userId);
		
		if(count == 0) {
			mReturn.put("result", "success");
			mReturn.put("message", "이 아이디를 사용하실 수 있습니다.");
			
			return mReturn;
		}
		else {
			mReturn.put("result", "fail");
			mReturn.put("message", "이 아이디가 이미 사용중입니다.");
			
			return mReturn;
		}
	}
	
	
}
