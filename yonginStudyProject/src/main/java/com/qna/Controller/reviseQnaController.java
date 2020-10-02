package com.qna.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commonFunction.Service.fileService;
import com.login.VO.userInfoVO;
import com.notice.VO.boardVO;
import com.notice.Validator.boardValidator;
import com.qna.Service.qnaService;

@Controller
public class reviseQnaController {
	
	@Resource(name="qnaService")
	private qnaService qnaService;
	
	@Resource(name="fileService")
	private fileService fileService;
	
	@RequestMapping(value = "/systemQna/reviseQna.do", method = RequestMethod.GET)
	public String reviseQna(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("boardVO", new boardVO());
		
		return "jsp/qna/reviseQna"; 
	}
	
	/**
	 * QnA 수정
	 * @param boardVO
	 * @param session
	 * @param bindingResult
	 * @param mpRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/systemQna/reviseQna", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> reviseQna(boardVO boardVO, HttpSession session, BindingResult bindingResult, MultipartHttpServletRequest mpRequest) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		boardVO.setRgstusId(user.getUserCode());
		
		boardVO boardVO2 = qnaService.selectQnaDetail(boardVO.getBoardCode());
		
		if(boardVO2 == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		if(!(boardVO2.getRgstusCode().equals(user.getUserCode()))) {
			mReturn.put("result","fail");
			mReturn.put("message", "권한이 없습니다.");
			
			return mReturn;
		}
		
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
		qnaService.reviseQna(boardVO, mpRequest);
		mReturn.put("result", "success");
		mReturn.put("message", "수정이 완료되었습니다.");
		
		return mReturn;
	}
	
}
