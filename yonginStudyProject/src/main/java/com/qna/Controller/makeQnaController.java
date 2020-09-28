package com.qna.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;
import com.study.Service.studyService;

@Controller
public class makeQnaController {
	
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
	private static final Logger logger = LoggerFactory.getLogger(makeQnaController.class);
	/**
	 * 스터디 더 보기 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/makeQna.do", method = RequestMethod.GET)
	public String makeQnaForm(Locale locale, Model model, HttpSession session) throws Exception {
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
		
		return "jsp/qna/makeQna";
	}
}