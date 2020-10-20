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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.login.VO.userInfoVO;
import com.notice.VO.boardVO;
import com.notice.Validator.boardValidator;
import com.studyManagement.Service.studyNoticeService;

@Controller
public class writeStudyNoticeController {
	@Resource(name="studyNoticeService") // 해당 서비스가 리소스임을 표시합니다.
	private studyNoticeService studyNoticeService;
	
	private static final Logger logger = LoggerFactory.getLogger(writeStudyNoticeController.class);
	
	/**
	 * 스터디 공지사항 작성 Mapping
	 */
	@RequestMapping(value = "/writeStudyNotice.do", method = RequestMethod.GET)
	public String studyNoticeForm(HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		// 관리자 권한이 없는 경우 로그인 페이지로 보냄(관리자 권한이 필요한 경우)
		if(!user.getUserIsAdmin().equals("Y")) {
			session.invalidate();
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		 
		return "jsp/studyManagement/writeStudyNotice";
	}

	/**
	 * 스터디 공지사항 작성
	 * @param boardVO
	 * @param bingdingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/makeStudyNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> makeStudyNotice(boardVO boardVO, HttpSession session, BindingResult bindingResult, MultipartHttpServletRequest mpRequest) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		boardVO.setRgstusId(user.getUserCode());

		// 관리자 권한이 없는 경우 오류 메시지 발생
		if(!user.getUserIsAdmin().equals("Y")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "권한이 없습니다.");
			
			return mReturn;
		}
				
		/** 데이터 검증(시작) **/
		boardValidator boardValidator = new boardValidator();
		boardValidator.validate(boardVO, bindingResult);
		
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
		studyNoticeService.insertStudyNotice(boardVO, mpRequest);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 생성되었습니다.");
		
		return mReturn;
	}
}

