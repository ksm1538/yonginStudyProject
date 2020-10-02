package com.qna.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonFunction.Service.fileService;
import com.login.VO.userInfoVO;
import com.notice.VO.boardVO;
import com.qna.Service.qnaService;

@Controller
public class qnaAnswerDetailPopupController {

	@Resource(name="qnaService")
	private qnaService qnaService;
	
	@Resource(name="fileService")
	private fileService fileService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	/**
	 * QnA 답글 보기 팝업 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/systemQna/qnaAnswerDetailPopup.do", method = RequestMethod.GET)
	public String qnaAnswerDetailPopup(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("boardVO", new boardVO());
		return "jsp/qna/qnaAnswerDetailPopup"; 
	}
	
	/**
	 * QnA 답글 상세 조회
	 * @param boardCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/systemQna/selectQnaAnswerDetail.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectQnaAnswerDetail(@RequestBody String boardCode) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(boardCode == null || boardCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		boardVO boardVO = qnaService.selectQnaAnswerDetail(boardCode);
		
		if(boardVO == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		List<Map<String, Object>> fileList = fileService.selectFileList(boardVO.getBoardCode());
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("boardInfo", boardVO);
		mReturn.put("fileList", fileList);
		
		return mReturn;
	}
	
	
	@RequestMapping(value="/systemQna/deleteQnaAnswer.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteQna(@RequestBody boardVO boardVO, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(boardVO.getHgrnkBoardCode().equals("") || boardVO.getBoardCode().equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		boardVO boardVO2 = qnaService.selectQnaDetail(boardVO.getBoardCode());
		
		if(boardVO2 == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		if(!(boardVO2.getRgstusCode().equals(user.getUserCode()))) {
			mReturn.put("result","fail");
			mReturn.put("message", "권한이 없습니다.");
			
			return mReturn;
		}
		
		qnaService.deleteQnaAnswer(boardVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 삭제하였습니다.");
		
		return mReturn;
	}
}