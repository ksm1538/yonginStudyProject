package com.login.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.commonFunction.Controller.yonginFunction;
import com.email.Service.MailService;
import com.login.Service.loginService;
import com.login.VO.userInfoVO;
import com.login.Validator.findPwUserInfoValidator;
import com.login.Validator.pwChangeValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class findPwFormController {
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	@Resource(name="MailService") // 해당 서비스가 리소스임을 표시합니다.
	private MailService MailService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Inject
    PasswordEncoder passwordEncoder;	// 암호화 기능 추가
	
	@Autowired
	findPwUserInfoValidator findPwUserInfoValidator;// validator 변수 불러옴
	
	@Autowired
	pwChangeValidator pwChangeValidator;// validator 변수 불러옴
	
	private static final Logger logger = LoggerFactory.getLogger(findPwFormController.class);
	
	/**
	 * Pw찾기 팝업 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findPwForm.do", method = RequestMethod.GET)
	public String findIdForm(Model model) throws Exception {
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("pwHint");
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("pwHint", codeResult);
		model.addAttribute("userInfoVO", new userInfoVO());
		return "jsp/login/findPw";
	}
	
	/**
	 * PW찾기 아이디 이메일 정보 확인
	 * @param userInfoVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findPw.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findPw(@ModelAttribute("userInfoVO") userInfoVO userInfoVO, BindingResult bindingResult, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		/** 데이터 검증(시작) **/
		findPwUserInfoValidator findPwUserInfoValidator = new findPwUserInfoValidator();
		findPwUserInfoValidator.validate(userInfoVO, bindingResult);
		
		// 에러 검출 시 에러 메시지와 함께 종료
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			String errorMsg = "";
		    for (FieldError error : errors ) {
		    	errorMsg += error.getDefaultMessage() + "\n";
		    }

		    mReturn.put("result", "fail");
			mReturn.put("message", errorMsg);
			
			return mReturn;
		}  
		/** 데이터 검증(끝) **/
		
		// 날짜에서 '-' 빼기
		String removeBirth = yonginFunction.remove(userInfoVO.getUserBirth(), '-');	//com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
		userInfoVO.setUserBirth(removeBirth);
		
		userInfoVO resultVO = loginService.selectUserInfoWithData(userInfoVO);

		if(resultVO == null) {
			mReturn.put("result", "fail");
			mReturn.put("message", "일치하는 정보가 없습니다.");
			
			return mReturn;
		}
		else {
			// 일치하는 정보 있는 경우 세션에 저장
			session.setAttribute("findPw", resultVO);
			
			mReturn.put("result", "success");
			mReturn.put("message", "정보가 일치하는 아이디를 찾았습니다.");
			mReturn.put("url", "/findPwSelectForm.do");
			
			return mReturn;
		}
	}
	
	/**
	 * PW찾기 방법 선택 팝업
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findPwSelectForm.do")
	public String findPwSelectForm(Model model, HttpSession session) throws Exception {
		
		userInfoVO user = (userInfoVO) session.getAttribute("findPw");
		
		if(user == null) {
			return "jsp/login/login";
		}
		
		model.addAttribute("findPwEmail", user.getUserEmail());
		
		return "jsp/login/findPwSelect";
	}
	
	/**
	 * 이메일을 이용해 임시비밀번호 발급받기
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findPwUsingEmail.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findPwUsingEmail(HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();

		// 비밀번호 찾기에서 세션에 넣은 사용자 정보 가져옴
		userInfoVO user = (userInfoVO) session.getAttribute("findPw");

		if(user == null) {
			mReturn.put("result", "fail");
			mReturn.put("message", "잘못된 접근입니다.");
			
			return mReturn;
		}
		
		// 임시비밀번호 생성
		String tempPw = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다.
		tempPw = tempPw.substring(0, 10);
		
		user.setUserPw(tempPw);
		
		// 임시비밀번호 암호화
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		
		// 비밀번호 업데이트
		loginService.updatePw(user);
		
		//발송 메일 설정
		String adminEmail = "ksm1538@gmail.com";		// 자신 gmail ID
		
		String mailTitle = "YonginStudy : 임시 비밀번호 발급 메일";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<div style=\"font-size:20px\">안녕하세요.<br>");
		stringBuilder.append("YonginStudy입니다. <br>");
		stringBuilder.append("귀하의 임시 비밀번호는 <strong style=\"color:red\"> " + tempPw + "</strong> 입니다.<br>");
		stringBuilder.append("로그인 후 비밀번호를 변경해주세요.<br></div>");
		
		//메일 전송 성공하면 true, 실패하면 false 반환
		boolean result = MailService.send(mailTitle, stringBuilder.toString(), adminEmail, user.getUserEmail(), null);
		
		if(result == true) {
			mReturn.put("result", "success");
			mReturn.put("message", "이메일로 임시 비밀번호를 전송했습니다.");
			
			session.invalidate();	// 세션 삭제
			
			return mReturn;
		}else {
			mReturn.put("result", "fail");
			mReturn.put("message", "이메일 전송에 실패하였습니다.");
			
			return mReturn;
		}
	}
	
	/**
	 * PW찾기 임시비밀번호 이용한 팝업 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findPwUsingHintForm.do")
	public String findPwUsingHintForm(Model model, HttpSession session) throws Exception {
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("pwHint");
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("pwHint", codeResult);
		
		return "jsp/login/findPwUsingHint";
	}
	
	/**
	 * 비밀번호 힌트 확인
	 * @param userInfoVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findPwUsingHint.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findPwUsingHint(@RequestBody userInfoVO userInfoVO, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();

		if(userInfoVO.getUserPwHintCode().equals("") || userInfoVO.getUserPwHintAnswer().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "비밀번호 힌트와 답을 입력해주세요.");
			
			return mReturn;
		}
		// 비밀번호 찾기에서 세션에 넣은 사용자 정보 가져옴
		userInfoVO user = (userInfoVO) session.getAttribute("findPw");
		
		if(user == null) {
			mReturn.put("result", "fail");
			mReturn.put("message", "잘못된 접근입니다.");
			
			return mReturn;
		}
		
		userInfoVO.setUserCode(user.getUserCode());

		//비밀번호 힌트와 답이 일치하면 1 반환
		int result = loginService.selectUserPwHint(userInfoVO);
		
		
		if(result == 1) {
			mReturn.put("result", "success");
			mReturn.put("message", "비밀번호 힌트와 답이 일치합니다.\n비밀번호를 변경해주십시오.");
			mReturn.put("url", "/findPwChangePw.do");
			
			return mReturn;
		}else {
			mReturn.put("result", "fail");
			mReturn.put("message", "비밀번호 힌트와 답이 일치하지 않습니다.");
			
			return mReturn;
		}
	}
	
	/**
	 * 비밀번호 변경 페이지
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPwChangePw.do")
	public String findPwChangePw(Model model) throws Exception {
		
		model.addAttribute("userInfoVO", new userInfoVO());
		return "jsp/login/findPwChangePw";
	}
	
	/**
	 * 비밀번호 변경
	 * @param userInfoVO
	 * @param session
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findPwChangePw.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findPwChangePw(@ModelAttribute("userInfoVO") userInfoVO userInfoVO, HttpSession session, BindingResult bindingResult) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();

		/** 데이터 검증(시작) **/
		pwChangeValidator pwChangeValidator = new pwChangeValidator();
		pwChangeValidator.validate(userInfoVO, bindingResult);
		
		// 에러 검출 시 에러 메시지와 함께 종료
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			String errorMsg = "";
		    for (FieldError error : errors ) {
		    	errorMsg += error.getDefaultMessage() + "\n";
		    }

		    mReturn.put("result", "fail");
			mReturn.put("message", errorMsg);
			
			return mReturn;
		}  
		/** 데이터 검증(끝) **/
		
		// 비밀번호 찾기에서 세션에 넣은 사용자 정보 가져옴
		userInfoVO user = (userInfoVO) session.getAttribute("findPw");
		
		if(user == null) {
			mReturn.put("result", "fail");
			mReturn.put("message", "잘못된 접근입니다.");
			
			return mReturn;
		}
		
		userInfoVO.setUserCode(user.getUserCode());

		// 비밀번호 암호화
		userInfoVO.setUserPw(passwordEncoder.encode(userInfoVO.getUserPw()));
				
		// 비밀번호 업데이트
		loginService.updatePw(userInfoVO);
		
		session.invalidate();
		
		mReturn.put("result", "success");
		mReturn.put("message", "비밀번호가 변경되었습니다.");
		
		return mReturn;
		
	}
}
