package com.login.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

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
import com.login.Validator.userInfoValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class registerMemeberController {
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	@Resource(name="MailService") // 해당 서비스가 리소스임을 표시합니다.
	private MailService MailService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Inject
    PasswordEncoder passwordEncoder;	// 암호화 기능 추가
	
	@Autowired
	userInfoValidator userInfoValidator;// validator 변수 불러옴
	
	
	private static final Logger logger = LoggerFactory.getLogger(registerMemeberController.class);
	
	/**
	 * 회원가입 팝업 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/registerForm.do", method = RequestMethod.GET)
	public String registerForm(Model model) throws Exception {
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("pwHint");
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("pwHint", codeResult);
		model.addAttribute("userInfoVO", new userInfoVO());
		return "jsp/login/registerMember";
	}
	
	/**
	 * 회원가입
	 * @param userInfoVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/registerMember.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerAjaxFunction(@ModelAttribute("userInfoVO") userInfoVO userInfoVO, BindingResult bindingResult) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		/** 데이터 검증(시작) **/
		userInfoValidator userInfoValidator = new userInfoValidator();
		userInfoValidator.validate(userInfoVO, bindingResult);
		
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
		
		//비밀번호 암호화
		userInfoVO.setUserPw(passwordEncoder.encode(userInfoVO.getUserPw()));
		
		// 날짜에서 '-' 빼기
		String removeBirth = yonginFunction.remove(userInfoVO.getUserBirth(), '-');	//com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
		userInfoVO.setUserBirth(removeBirth);
		
		// 전화번호에서 '-' 빼기
		String removePhoneNumber = yonginFunction.remove(userInfoVO.getUserPhoneNumber(), '-');	//com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
		userInfoVO.setUserPhoneNumber(removePhoneNumber);
		
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
		
		// id 입력 확인
		if(userId == null || userId.equals("")){
			mReturn.put("result", "fail");
			mReturn.put("message", "아이디를 입력해주세요.");
			
			return mReturn;
		}
		
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
	
	/**
	 * 이메일 전송
	 * @param userEmail
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/register/sendEmailAuthCode.json", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendEmailAuthCode(@RequestBody String userEmail, HttpSession session) throws Exception{
	
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(userEmail).matches())) {
        	mReturn.put("result", "fail");
			mReturn.put("message", "이메일 형식에 맞게 입력해주세요.");
			
			return mReturn;
        }
        
        // 사용중인 이메일이 있는지 확인
        int count = loginService.selectSameEmail(userEmail);
        if(count != 0) {
        	mReturn.put("result", "fail");
			mReturn.put("message", "해당 이메일은 이미 사용중입니다.");
			
			return mReturn;
        }
        
        //난수 발생
		int randomNumber = new Random().nextInt(900000) + 100000;
		String authCode = String.valueOf(randomNumber);
		
		//발송 메일 설정
		String adminEmail = "ID@gmail.com";		// 자기 gmail ID 입력하셈
		
		String mailTitle = "YonginStudy : 메일 인증 코드";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<div style=\"font-size:20px\">안녕하세요.<br>");
		stringBuilder.append("YonginStudy를 사용해주셔서 감사합니다. <br>");
		stringBuilder.append("귀하의 인증 코드는 <strong style=\"color:red\"> " + authCode + "</strong> 입니다.<br>");
		stringBuilder.append("계속 진행하시려면 인증코드를 입력해주세요.<br></div>");
		
		session.setAttribute("authCode", authCode);		//세션에 인증번호 저장
		
		//메일 전송 성공하면 true, 실패하면 false 반환
		boolean result = MailService.send(mailTitle, stringBuilder.toString(), adminEmail, userEmail, null);
		
		if(result == true) {
			mReturn.put("result", "success");
			mReturn.put("message", "인증번호가 전송되었습니다.");
			
			return mReturn;
		}
		else {
			session.removeAttribute("authCode");	//세션에서 인증코드 삭제
			mReturn.put("result", "fail");
			mReturn.put("message", "인증번호 전송에 실패했습니다.");
			
			return mReturn;
		}
	}

	/**
	 * 이메일 인증 코드 확인
	 * @param userAuthCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/register/checkEmailAuthCode.json", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkEmailAuthCode(@RequestBody String userAuthCode, HttpSession session){
	
		HashMap<String, Object> mReturn = new HashMap<String, Object>();

		String authCode = (String) session.getAttribute("authCode");	//세션에서 인증번호 불러옴
		
		// 인증번호가 맞을 경우
		if(authCode.equals(userAuthCode)) {
			mReturn.put("result", "success");
			mReturn.put("message", "이메일 인증에 성공하였습니다.");
			
			return mReturn;
		}else {
			mReturn.put("result", "fail");
			mReturn.put("message", "이메일 인증에 실패하였습니다.");
			
			return mReturn;
		}
	}
	
	/**
	 * email 초기화
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/register/resetEmail.json", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetEmail(HttpSession session){
	
		HashMap<String, Object> mReturn = new HashMap<String, Object>();

		session.removeAttribute("authCode");	//세션에서 인증코드 삭제

		mReturn.put("result", "success");
		mReturn.put("message", "이메일을 입력해주세요.");
		
		return mReturn;
	}
}
