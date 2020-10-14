package com.studyManagement.Controller;


import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.VO.userInfoVO;

@Controller
public class studyNoticeController {
	
	/*
	 서비스는 아직 안만들었으므로 선언할 필요없음
	 @Resource(name="studyService") private studyService studyService;
	 */
	
	/*
	공통코드 필요시 사용
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	*/
	
	/*컨트롤러 이름이랑 같게*/
	private static final Logger logger = LoggerFactory.getLogger(studyNoticeController.class);
	
	/**
	 * 스터디 전용 공지사항 페이지
	 * @throws Exception 
	 */
	@RequestMapping(value = "/studynotice.do", method = RequestMethod.POST)
	public String studyNoticeForm(Locale locale, Model model, HttpSession session) throws Exception {
		/*건들필요없음 무조건 들어가는거*/
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		/*스터디토픽에 관련된 데이터를  db에서 가져오고 그걸 coderesult에 넣음*/
		/*List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyTopic");*/
		
		//model 변수에 데이터를 담아 jsp에 전달
		/*coderesult를 moodel에 넣어둠*/
		/*model.addAttribute("studyTopicArray", codeResult);*/
		
		return "jsp/studyManagement/studyNotice";
	}
	
	
	/**
	 * 공지사항 작성팝업
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/studyManagement/writeStudyNotice.do", method = RequestMethod.GET)
	public String writeStudyNoticePopup(HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지 로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		return "jsp/studyManagement/writeStudyNotice";
	}
	
	
	
}