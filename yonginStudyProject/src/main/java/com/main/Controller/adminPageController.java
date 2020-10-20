package com.main.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.login.VO.userInfoVO;
import com.main.Service.adminService;
import com.main.VO.studyInfoVO;
import com.study.Service.studyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class adminPageController {
	@Resource(name="adminService") // 해당 서비스가 리소스임을 표시합니다.
	private adminService adminService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Resource(name="studyService")
	private studyService studyService;
	
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
		
		// 관리자 권한이 없는 경우 로그인 페이지로 보냄(관리자 권한이 필요한 경우)
		if(!user.getUserIsAdmin().equals("Y")) {
			session.invalidate();
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyTopic");
		
		model.addAttribute("studyTopicArray", codeResult);
		return "jsp/main/adminPage";
	}
	
	/**
	 * 사용자 리스트 조회
	 * @param userInfoVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminPage/selectUserList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectUserList(@RequestBody userInfoVO userInfoVO, HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		/*** 페이징(시작) ***/
		int dataPerPage = 6; //그리드 한 페이지에 표시할 데이터 수
    	int page = Integer.parseInt(userInfoVO.getPage()); //페이지별 변경
    	
    	int first = page * dataPerPage + 1; //변경없이 추가
    	int last = first + dataPerPage - 1; //변경없이 추가
    	
    	userInfoVO.setFirst(first); //변경없이 추가
    	userInfoVO.setLast(last);   //변경없이 추가
    	
    	int total = adminService.selectUserListToCnt(userInfoVO); // 총 몇 페이지인지 확인
    	int totalPages = (int)Math.ceil(total / (double)dataPerPage); // 변경없이 추가
		
		/*** 페이징(끝) ***/
    	
		List<userInfoVO> ltResult = adminService.selectUserList(userInfoVO);
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "사용자 목록이 없습니다.");
			
			return mReturn;
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
	 * 관리자 페이지에서 스터디 목록 조회
	 * @param studyInfoVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminPage/selectStudyList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyList(@RequestBody studyInfoVO studyInfoVO, HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		/*** 페이징(시작) ***/
		int dataPerPage = 6; //그리드 한 페이지에 표시할 데이터 수
    	int page = Integer.parseInt(studyInfoVO.getPage()); //페이지별 변경
    	
    	int first = page * dataPerPage + 1; //변경없이 추가
    	int last = first + dataPerPage - 1; //변경없이 추가
    	
    	studyInfoVO.setFirst(first); //변경없이 추가
    	studyInfoVO.setLast(last);   //변경없이 추가
    	
    	int total = studyService.selectStudyListToCnt(studyInfoVO); // 총 몇 페이지인지 확인
    	int totalPages = (int)Math.ceil(total / (double)dataPerPage); // 변경없이 추가
		
		/*** 페이징(끝) ***/
    	
		List<studyInfoVO> ltResult = studyService.selectStudyList(studyInfoVO);
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "스터디 목록이 없습니다.");
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
	 * 사용자 추방
	 * @param userCode
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminPage/kickUser.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> kickUser(@RequestBody String userCode, HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		if(userCode == null || userCode.equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "사용자가 선택되지 않았습니다.");
		}
		
		adminService.kickUser(userCode);
		
		mReturn.put("result", "success");
		mReturn.put("message", "변경 사항이 저장되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 관리자 권한 취소
	 * @param userCode
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminPage/cancleAdminUser.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancleAdminUser(@RequestBody String userCode, HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		if(userCode == null || userCode.equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "사용자가 선택되지 않았습니다.");
		}
		
		adminService.cancleAdminUser(userCode);
		
		mReturn.put("result", "success");
		mReturn.put("message", "변경 사항이 저장되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 관리자 권한 임명
	 * @param userCode
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminPage/setAdminUser.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setAdminUser(@RequestBody String userCode, HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		if(userCode == null || userCode.equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "사용자가 선택되지 않았습니다.");
		}
		
		adminService.setAdminUser(userCode);
		
		mReturn.put("result", "success");
		mReturn.put("message", "변경 사항이 저장되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 스터디 삭제
	 * @param studyCode
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminPage/deleteStudy.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteStudy(@RequestBody String studyCode, HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		if(studyCode == null || studyCode.equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "스터디가 선택되지 않았습니다.");
		}
		
		adminService.deleteStudy(studyCode);
		
		mReturn.put("result", "success");
		mReturn.put("message", "변경 사항이 저장되었습니다.");
		
		return mReturn;
	}
}
