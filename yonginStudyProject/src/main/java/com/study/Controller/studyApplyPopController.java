package com.study.Controller;

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
import com.study.Service.studyService;
import com.study.VO.studyApplicationFormUserVO;
import com.study.Validator.studyApplicationFormUserValidator;
import com.study.Validator.updateStudyApplicationFormUserValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class studyApplyPopController {
	@Resource(name="studyService") // 해당 서비스가 리소스임을 표시합니다.
	private studyService studyService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(studyApplyPopController.class);
	
	/**
	 * 스터디 신청하기 폼 
	 */
	@RequestMapping(value = "/studyApplyPop.do", method = RequestMethod.POST)
	public String studyApplyPopForm(HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		   
		return "jsp/study/studyApplyPop";
	}
	
	/** 
	 * 스터디 신청 Insert
	 * @param messageInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/study/applyStudy.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> applyStudy(@RequestBody studyApplicationFormUserVO studyApplicationFormUserVO, HttpSession session, BindingResult bindingResult) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		
		/** 데이터 검증(시작) **/
		studyApplicationFormUserValidator studyApplicationFormUserValidator = new studyApplicationFormUserValidator();
		studyApplicationFormUserValidator.validate(studyApplicationFormUserVO, bindingResult);
		
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
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		studyApplicationFormUserVO.setUserCode(user.getUserCode());
		
		int count = studyService.selectStudyApplicationFormCount(studyApplicationFormUserVO);
		
		if(count != 0) {
			mReturn.put("result", "fail");
			mReturn.put("message", "해당 스터디에 이미 신청하셨습니다.");
			
			return mReturn;
		}
		
		studyService.insertStudyApplicationFormUser(studyApplicationFormUserVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "신청이 완료되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 신청서 수정
	 * @param studyApplicationFormUserVO
	 * @param session
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/study/updateStudyApplicationForm.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStudyApplicationForm(@RequestBody studyApplicationFormUserVO studyApplicationFormUserVO, HttpSession session, BindingResult bindingResult) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		
		/** 데이터 검증(시작) **/
		updateStudyApplicationFormUserValidator updateStudyApplicationFormUserValidator = new updateStudyApplicationFormUserValidator();
		updateStudyApplicationFormUserValidator.validate(studyApplicationFormUserVO, bindingResult);
		
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
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		studyApplicationFormUserVO.setUserCode(user.getUserCode());
		
		studyService.updateStudyApplicationFormUser(studyApplicationFormUserVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "수정이 완료되었습니다.");
		
		return mReturn;
	}
	
	@RequestMapping(value="/study/selectStudyApplicationForm.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyApplicationForm(@RequestBody studyApplicationFormUserVO studyApplicationFormUserVO, HttpSession session, BindingResult bindingResult) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(studyApplicationFormUserVO.getStudyCode().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "스터디가 선택되지 않았습니다.");
			
			return mReturn;
		}
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		studyApplicationFormUserVO.setUserCode(user.getUserCode());
		
		studyApplicationFormUserVO resultVo = studyService.selectStudyApplicationForm(studyApplicationFormUserVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "수정이 완료되었습니다.");
		mReturn.put("resultVO", resultVo);
		
		return mReturn;
	}
}

