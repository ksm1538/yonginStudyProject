package com.studyManagement.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.VO.userInfoVO;
import com.studyManagement.Service.studyManagementService;

@Controller
public class writeStudyNoticeController {
	@Resource(name="studyManagementService") // 해당 서비스가 리소스임을 표시합니다.
	private studyManagementService studyManagementService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(writeStudyNoticeController.class);
	
	/**
	 * 스터디 공지사항 작성 Mapping
	 */
	@RequestMapping(value = "/writeStudyNotice.do", method = RequestMethod.GET)
	public String openWriteStudyNotice(HttpSession session) {
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

}
