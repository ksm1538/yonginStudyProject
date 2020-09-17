package com.main.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.login.VO.userInfoVO;
import com.main.Service.mainService;
import com.main.VO.studyInfoVO;
/**
 * Handles requests for the application home page.
 */
@Controller
public class mainController {
	@Resource(name="mainService") // 해당 서비스가 리소스임을 표시합니다.
	private mainService mainService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	private static final Logger logger = LoggerFactory.getLogger(mainController.class);
	
	/**
	 * 로그인 화면 Controller
	 * @throws Exception 
	 */
	@RequestMapping(value = "/main/main.do", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyTopic");
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("studyTopicArray", codeResult);
		
		return "jsp/main/main";
	}
	
	/**
	 * 스터디 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main/selectStudyList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyList(HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		List<studyInfoVO> ltResult = mainService.selectStudyList(user.getUserCode());
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "스터디 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	
	/*	일정 조회 함수 수정예정(김성목)
	@RequestMapping(value="/main/selectCalenderList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectCalenderList(HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		HttpSession session = request.getSession();
		userInfoVO userInfoVO = (userInfoVO) session.getAttribute("user");
		System.out.println("세션 : "+userInfoVO.getUserId());
		
		List<calenderVO> ltResult = mainService.selectCalenderList();
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "일정 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	*/
}
	
	
	