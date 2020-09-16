package com.study.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.Service.studyService;
import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class studyApplyPopController {
	@Resource(name="studyService") // 해당 서비스가 리소스임을 표시합니다.
	private studyService studyService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(studyApplyPopController.class);
	
	/**
	 * 스터디 더보기 Mapping
	 */
	@RequestMapping(value = "/studyApplyPop.do", method = RequestMethod.GET)
	public String studyApplyPopForm(HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		   
		return "jsp/study/studyApplyPop";
	}
}

