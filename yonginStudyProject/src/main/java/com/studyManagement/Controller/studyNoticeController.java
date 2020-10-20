package com.studyManagement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.VO.userInfoVO;
import com.notice.VO.boardVO;
import com.studyManagement.Service.studyNoticeService;

@Controller
public class studyNoticeController {
	 @Resource(name="studyNoticeService") 
	  private studyNoticeService studyNoticeService;
	
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
	public String studyNoticeForm(HttpSession session) /*throws Exception*/ {
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
	 * 스터디 공지사항 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/selectStudyNoticeList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyNoticeList(@RequestBody boardVO boardVO) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		/*** 페이징(시작) ***/
		int dataPerPage = 12; //그리드 한 페이지에 표시할 데이터 수
    	int page = Integer.parseInt(boardVO.getPage()); //페이지별 변경
    	
    	int first = page * dataPerPage + 1; //변경없이 추가
    	int last = first + dataPerPage - 1; //변경없이 추가
    	
    	boardVO.setFirst(first); //변경없이 추가
    	boardVO.setLast(last);   //변경없이 추가
    	
    	int total = studyNoticeService.selectStudyNoticeListToCnt(boardVO); // 총 몇 페이지인지 확인
    	int totalPages = (int)Math.ceil(total / (double)dataPerPage); // 변경없이 추가
		
		/*** 페이징(끝) ***/
    	
    	List<boardVO> ltResult = studyNoticeService.selectStudyNoticeList(boardVO);
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "success");
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
	 * 스터디 공지사항 삭제
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/deleteStudyNotice.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteMessageTo(@RequestBody boardVO boardVO, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		// 관리자 권한이 없는 경우 오류 메시지 발생
		if(!user.getUserIsAdmin().equals("Y")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "권한이 없습니다.");
			
			return mReturn;
		}
				
		studyNoticeService.deleteStudyNotice(boardVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 삭제되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 공지사항 작성팝업
	 * @param session
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/studyManagement/writeStudyNotice.do", method = RequestMethod.GET)
	public String writeStudyNoticePopup(HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지 로 이동(시작) **/
		/*userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		/*return "jsp/studyManagement/writeStudyNotice";
	}*/
	
	
}