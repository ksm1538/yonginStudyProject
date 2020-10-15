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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.commonFunction.Controller.yonginFunction;
import com.login.VO.userInfoVO;
import com.main.Service.mainService;
import com.main.VO.calendarVO;
import com.main.VO.studyInfoVO;
import com.notice.VO.boardVO;
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
		
		
		List<commonCodeVO> codeResult1 = commonCodeService.selectCommonCodeList("studyTopic");
		List<commonCodeVO> codeResult2 = commonCodeService.selectCommonCodeList("calendarType");

		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("studyTopicArray", codeResult1);
		model.addAttribute("calendarType", codeResult2);
		
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
	
	/**
	 * 스터디 공지사항 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main/selectStudyNoticeList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyNoticeList(@RequestBody boardVO boardVO, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		boardVO.setCurrentUserCode(user.getUserCode());
		
		/*** 페이징(시작) ***/
		int dataPerPage = 10; //그리드 한 페이지에 표시할 데이터 수
    	int page = Integer.parseInt(boardVO.getPage()); //페이지별 변경
    	
    	int first = page * dataPerPage + 1; //변경없이 추가
    	int last = first + dataPerPage - 1; //변경없이 추가
    	
    	boardVO.setFirst(first); //변경없이 추가
    	boardVO.setLast(last);   //변경없이 추가
    	
    	int total = mainService.selectStudyNoticeListToCnt(boardVO); // 총 몇 페이지인지 확인
    	int totalPages = (int)Math.ceil(total / (double)dataPerPage); // 변경없이 추가
		
		/*** 페이징(끝) ***/
    	
    	List<boardVO> ltResult = mainService.selectStudyNoticeList(boardVO);
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "공지사항 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("total", total);
    	mReturn.put("totalPages", totalPages);
    	mReturn.put("dataPerPage", dataPerPage);
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	
	/**
	 * 자기가 가입한 스터디의 일정 조회
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/main/searchMyStudyCalendar.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchMyStudyCalendar(@RequestBody calendarVO calendarVO, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		calendarVO.setUserCode(user.getUserCode());
		
		List<calendarVO> ltResult = mainService.searchMyStudyCalendar(calendarVO);
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "일정 목록이 없습니다.");
		}
		
		// 시간 형식으로 변환
		for(int i=0;i<ltResult.size();i++) {
			calendarVO vo = ltResult.get(i);
			
			// '-' 추가
    		String startDt = yonginFunction.nullConvert(vo.getStartDt());
    		vo.setStartDt(yonginFunction.addMinusChar(startDt));
    		String endDt = yonginFunction.nullConvert(vo.getEndDt());
    		vo.setEndDt(yonginFunction.addMinusChar(endDt));
    		// ':' 추가
            String startHm = vo.getStartHm();
            vo.setStartHm(yonginFunction.addColonChar(startHm));
            String endHm = vo.getEndHm();
            vo.setEndHm(yonginFunction.addColonChar(endHm));
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	
	/**
	 * 달력 팝업 상세보기
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/main/calendarDetailPopup.do", method = RequestMethod.POST)
	public String calendarDetailPopup(HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지 로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		return "jsp/main/calendarDetailPopup";
	}
	
	
	
}
	
	
	