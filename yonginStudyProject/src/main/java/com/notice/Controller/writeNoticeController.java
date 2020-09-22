package com.notice.Controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;
import com.message.Validator.sendMessageValidator;
import com.notice.Service.systemNoticeService;
import com.notice.VO.moreNoticeInfoVO;
import com.notice.Validator.systemNoticeValidator;
import com.study.Validator.studyInfoValidator;

@Controller
public class writeNoticeController {
	@Resource(name="systemNoticeService") // 해당 서비스가 리소스임을 표시합니다.
	private systemNoticeService systemNoticeService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(writeNoticeController.class);
	
	/**
	 * 공지사항 작성 Mapping
	 */
	@RequestMapping(value = "/writeNotice.do", method = RequestMethod.GET)
	public String MoreNoticeForm(HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		 
		return "jsp/notice/writeNotice";
	}
	
	/**
	 * 공지사항 작성
	 * @param moreNoticeInfoVO
	 * @param bingdingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/makeSystemNotice.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerAjaxFunction(@RequestBody moreNoticeInfoVO moreNoticeInfoVO, HttpSession session, BindingResult bindingResult) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		moreNoticeInfoVO.setSystemNoticeRgstusId(user.getUserCode());
		
		/** 데이터 검증(시작) **/
		systemNoticeValidator systemNoticeValidator = new systemNoticeValidator();
		systemNoticeValidator.validate(moreNoticeInfoVO, bindingResult);
		
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
	
		systemNoticeService.insertSystemNotice(moreNoticeInfoVO);
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 생성되었습니다.");
		
		return mReturn;
	}
}
