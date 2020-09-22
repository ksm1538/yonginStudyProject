package com.main.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.commonCode.Service.commonCodeService;
import com.login.VO.userInfoVO;
import com.main.Service.adminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class adminPageController {
	@Resource(name="adminService") // 해당 서비스가 리소스임을 표시합니다.
	private adminService adminService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	private static final Logger logger = LoggerFactory.getLogger(adminPageController.class);
	
	/**
	 * 마이페이지 팝업 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/openAdminPage.do", method = RequestMethod.GET)
	public String openAdminPage(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		
		// 관리자 권한이 없는 경우 로그인 페이지로 보냄(여기 페이지만 추가)
		if(!user.getUserIsAdmin().equals("Y")) {
			session.invalidate();
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/main/adminPage";
	}
}
