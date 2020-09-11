package com.login.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.email.Service.MailService;
import com.login.Service.loginService;
import com.login.VO.userInfoVO;
import com.login.Validator.findPwUserInfoValidator;

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
	
	
	private static final Logger logger = LoggerFactory.getLogger(findPwFormController.class);
	
	/**
	 * ID찾기 팝업 Mapping
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
	 * PW찾기
	 * @param userInfoVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findPw.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findPw(@ModelAttribute("userInfoVO") userInfoVO userInfoVO, BindingResult bindingResult) throws Exception {
	      
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
		
		userInfoVO resultVO = loginService.selectUserInfoWithData(userInfoVO);

		if(resultVO == null) {
			mReturn.put("result", "fail");
			mReturn.put("message", "일치하는 정보가 없습니다.");
			
			return mReturn;
		}
		else {
			// 임시비밀번호 생성
			String tempPw = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다.
			tempPw = tempPw.substring(0, 10);
			
			resultVO.setUserPw(tempPw);
			
			//임시비밀번호 암호화
			resultVO.setUserPw(passwordEncoder.encode(resultVO.getUserPw()));
			loginService.updatePw(resultVO);
			
			//발송 메일 설정
			String adminEmail = "ksm1538@gmail.com";		// 자기 gmail ID 입력하셈
			
			String mailTitle = "YonginStudy : 임시 비밀번호 발급 메일";
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<div style=\"font-size:20px\">안녕하세요.<br>");
			stringBuilder.append("YonginStudy입니다. <br>");
			stringBuilder.append("귀하의 임시 비밀번호는 <strong style=\"color:red\"> " + tempPw + "</strong> 입니다.<br>");
			stringBuilder.append("로그인 후 비밀번호를 변경해주세요.<br></div>");
			
			//메일 전송 성공하면 true, 실패하면 false 반환
			boolean result = MailService.send(mailTitle, stringBuilder.toString(), adminEmail, resultVO.getUserEmail(), null);
			
			if(result == true) {
				mReturn.put("result", "success");
				mReturn.put("message", "이메일로 임시 비밀번호를 전송했습니다.");
				
				return mReturn;
			}else {
				mReturn.put("result", "fail");
				mReturn.put("message", "이메일 전송에 실패하였습니다.");
				
				return mReturn;
			}
		}
	}
}
