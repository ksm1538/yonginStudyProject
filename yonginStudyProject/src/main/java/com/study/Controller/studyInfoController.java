package com.study.Controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.Service.studyService;
import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;

@Controller
public class studyInfoController {
	@Resource(name="studyService") // 해당 서비스가 리소스임을 표시합니다.
	private studyService studyService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(studyInfoController.class);
	
	/**
	 * 스터디정보 팝업 Mapping
	 */
	@RequestMapping(value = "/studyInfo.do", method = RequestMethod.GET)
	public String studyInfoForm(Locale locale, Model model, HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/

		return "jsp/study/studyInfo"; 
	}
}
