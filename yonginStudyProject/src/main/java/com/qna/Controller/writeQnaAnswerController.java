package com.qna.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.login.VO.userInfoVO;
import com.notice.VO.boardVO;
import com.notice.Validator.boardValidator;
import com.qna.Service.qnaService;

@Controller
public class writeQnaAnswerController {
	@Resource(name="qnaService") // 해당 서비스가 리소스임을 표시합니다.
	private qnaService qnaService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(writeQnaAnswerController.class);
	
	/**
	 * QnA 답글 작성
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/systemQna/writeQnaAnswer.do", method = RequestMethod.GET)
	public String writeQnaAnswer( HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/qna/writeQnaAnswer";
	}
	
	/**
	 * Qna 답글 작성
	 * @param boardVO
	 * @param bingdingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/systemQna/writeQnaAnswer", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> writeQnaAnswer(boardVO boardVO, HttpSession session, BindingResult bindingResult, MultipartHttpServletRequest mpRequest) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		// QnA 답글 작성 권한 확인 (관리자만 작성 가능)
		if(!(user.getUserIsAdmin().equals("Y"))) {
			mReturn.put("result", "fail");
			mReturn.put("message", "답글 작성 권한이 없습니다.");
			
			return mReturn;
		}
		
		// QnA 게시판 코드 확인
		if(boardVO.getHgrnkBoardCode().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "오류가 발생했습니다.");
			
			return mReturn;
		}
		
		// 답글 작성 여부 확인
		boardVO boardVO2 = qnaService.selectQnaDetail(boardVO.getHgrnkBoardCode());
		if(!(boardVO2.getQnaStatus().equals("10"))) {
			mReturn.put("result", "fail");
			mReturn.put("message", "답글을 작성할 수 없습니다.");
			
			return mReturn;
		}
		boardVO.setBoardTitle("Re:"+boardVO2.getBoardTitle());
		/** 데이터 검증(시작) **/
		boardValidator boardValidator = new boardValidator();
		boardValidator.validate(boardVO, bindingResult);
		
		// 에러 검출 시 에러 메시지와 함께 종료
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			String errorMsg = "";
		    for (FieldError error : errors ) {
		    	errorMsg += error.getDefaultMessage() + "\n";
		    }

		    mReturn.put("result", "fail");
			mReturn.put("message", errorMsg);
			
			return mReturn;
		}  
		/** 데이터 검증(끝) **/
		
		boardVO.setRgstusId(user.getUserCode());
		
		qnaService.insertQna(boardVO, mpRequest);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 생성되었습니다.");
		
		return mReturn;
	}
	
	
}
