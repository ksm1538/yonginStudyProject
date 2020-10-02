package com.qna.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.notice.VO.boardVO;
import com.qna.Service.qnaService;

@Controller
public class systemQnaController {

	@Resource(name="qnaService") 
	private qnaService qnaService;

	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	/*컨트롤러 이름이랑 같게*/
	private static final Logger logger = LoggerFactory.getLogger(systemQnaController.class);
	
	/**
	 * 스터디 더 보기 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/systemQna.do", method = RequestMethod.GET)
	public String systemQnaForm(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("qnaStatus");
		

		model.addAttribute("qnaStatusArray", codeResult);
		
		return "jsp/qna/systemQna";
	}

	/**
	 * QnA조회
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/systemQna/selectQnaList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectSystemNoticeList(@RequestBody boardVO boardVO) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		/*** 페이징(시작) ***/
		int dataPerPage = 12; //그리드 한 페이지에 표시할 데이터 수
    	int page = Integer.parseInt(boardVO.getPage()); //페이지별 변경
    	
    	int first = page * dataPerPage + 1; //변경없이 추가
    	int last = first + dataPerPage - 1; //변경없이 추가
    	
    	boardVO.setFirst(first); //변경없이 추가
    	boardVO.setLast(last);   //변경없이 추가
    	
    	int total = qnaService.selectQnaListToCnt(boardVO); // 총 몇 페이지인지 확인
    	int totalPages = (int)Math.ceil(total / (double)dataPerPage); // 변경없이 추가
		
		/*** 페이징(끝) ***/
    	
    	List<boardVO> ltResult = qnaService.selectQnaList(boardVO);
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "success");
			mReturn.put("message", "Qna 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("total", total);
    	mReturn.put("totalPages", totalPages);
    	mReturn.put("dataPerPage", dataPerPage);
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	
}