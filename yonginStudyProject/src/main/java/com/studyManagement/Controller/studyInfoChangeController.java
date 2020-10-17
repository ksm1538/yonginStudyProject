package com.studyManagement.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.VO.userInfoVO;

@Controller
public class studyInfoChangeController {

	/**
	 * 스터디 정보 수정 Mapping
	 */
	@RequestMapping(value = "/studyManagemet/studyInfoChange.do", method = RequestMethod.GET)
	public String studyInfoChange(HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/studyManagement/studyInfoChange"; 
	}
}
