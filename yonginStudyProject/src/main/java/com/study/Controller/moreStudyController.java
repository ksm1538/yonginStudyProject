package com.study.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.study.Service.studyService;
import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;

@Controller
public class moreStudyController {
	@Resource(name="studyService")
	private studyService studyService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	private static final Logger logger = LoggerFactory.getLogger(moreStudyController.class);
	/**
	 * 스터디 더 보기 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/moreStudy.do", method = RequestMethod.GET)
	public String moreStudyForm(Locale locale, Model model, HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/study/moreStudy";
	}
	
	/**
	 * 스터디 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/study/selectStudyList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyList(HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		List<studyInfoVO> ltResult = studyService.selectStudyList();
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "스터디 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
}
