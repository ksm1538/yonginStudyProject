package com.studyManagement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.VO.userInfoVO;
import com.message.Controller.messageListController;
import com.message.Service.messageService;
import com.message.VO.messageInfoVO;
import com.main.VO.userInStudyVO;
import com.message.Validator.sendMessageValidator;
import com.studyManagement.Service.studyManagementService;

@Controller
public class deportStudyMemberController {
	
	@Resource(name="messageService") // 해당 서비스가 리소스임을 표시합니다.
	private messageService messageService;
	
	@Resource(name="studyManagementService")
	private studyManagementService studyManagementService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(messageListController.class);
	
	/**
	 * 쪽지 보내기 Mapping
	 */
	@RequestMapping(value = "/studyManagement/deportStudyMember.do", method = RequestMethod.POST)
	public String sendMessageForm(HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/studyManagement/deportStudyMember";
	}
	
	/** 
	 * 쪽지 보내고 추방
	 * @param messageInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/deportStudyMemberFunc.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendDeleteAjaxFunction(@RequestBody messageInfoVO messageInfoVO, HttpSession session, BindingResult bindingResult) throws Exception {
	     
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		messageInfoVO.setUserCodeFrom(user.getUserCode());
		messageInfoVO.getUserCodeFrom();
		
		/** 데이터 검증(시작) **/
		sendMessageValidator sendMessageValidator = new sendMessageValidator();
		sendMessageValidator.validate(messageInfoVO, bindingResult);
		
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
		
		int count = messageService.selectUserExistCount(messageInfoVO.getUserCodeTo());
		
		if(count != 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "받는 사람 ID가 존재하지 않습니다.\n다시 입력해주세요.");
			
			return mReturn;
		}
		studyManagementService.deportStudyMember(messageInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "추방되었습니다.");
		
		return mReturn;
	}
}
