package com.login.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonFunction.Controller.yonginFunction;
import com.email.Service.MailService;
import com.login.Service.loginService;
import com.login.VO.userInfoVO;
import com.login.Validator.findIdUserInfoValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class findIdFormController {
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	@Resource(name="MailService") // 해당 서비스가 리소스임을 표시합니다.
	private MailService MailService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Autowired
	findIdUserInfoValidator findIdUserInfoValidator;// validator 변수 불러옴
	
	
	private static final Logger logger = LoggerFactory.getLogger(findIdFormController.class);
	
	/**
	 * ID찾기 팝업 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findIdForm.do", method = RequestMethod.GET)
	public String findIdForm(Model model) throws Exception {
		
		model.addAttribute("userInfoVO", new userInfoVO());
		return "jsp/login/findId";
	}
	
	/**
	 * ID찾기
	 * @param userInfoVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findId.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findId(@ModelAttribute("userInfoVO") userInfoVO userInfoVO, BindingResult bindingResult) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		/** 데이터 검증(시작) **/
		findIdUserInfoValidator findIdUserInfoValidator = new findIdUserInfoValidator();
		findIdUserInfoValidator.validate(userInfoVO, bindingResult);
		
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
		
		// 생일에서 '-' 빼기
		String removeBirth = yonginFunction.remove(userInfoVO.getUserBirth(), '-');	//com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
		userInfoVO.setUserBirth(removeBirth);
		
		String Id = loginService.selectIdWithData(userInfoVO);
		
		if(Id != null && !Id.equals("")) {
			mReturn.put("result", "success");
			mReturn.put("message", "성공적으로 찾았습니다.");
			mReturn.put("resultData", Id);
			
			return mReturn;
		}else {
			mReturn.put("result", "fail");
			mReturn.put("message", "일치하는 정보가 없습니다.");
			
			return mReturn;
		}
	}
	
}
