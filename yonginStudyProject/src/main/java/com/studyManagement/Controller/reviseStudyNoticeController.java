package com.studyManagement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commonFunction.Service.fileService;
import com.login.VO.userInfoVO;
import com.studyManagement.Service.studyManagementService;
import com.notice.VO.boardVO;
import com.notice.Validator.boardValidator;

@Controller
public class reviseStudyNoticeController {
	
	@Resource(name="studyManagementService")
	private studyManagementService studyManagementService;
	
	@Resource(name="fileService")
	private fileService fileService;
	
	@RequestMapping(value = "/studyManagement/reviseStudyNotice.do", method = RequestMethod.GET)
	public String reviseStudyNotice(Model model, HttpSession session) throws Exception {
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
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("boardVO", new boardVO());
		
		return "jsp/studyManagement/reviseStudyNotice"; 
	}
	
	/**
	 * 해당 내용 조회
	 * @param systemNoticeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/selectReviseStudyNotice.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyInfoDetail(@RequestBody String boardCode) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(boardCode == null || boardCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		boardVO boardInfo = studyManagementService.selectStudyNoticeInfoDetail(boardCode);
		
		if(boardInfo == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		List<Map<String, Object>> fileList = fileService.selectFileList(boardCode);
		
		mReturn.put("fileList", fileList);
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("boardInfo", boardInfo);
		
		return mReturn;
	}
	
	/**
	 * 공지사항 수정
	 * @param boardVO
	 * @param bingdingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/selectReviseStudyNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerAjaxFunction(boardVO boardVO, HttpSession session, BindingResult bindingResult, MultipartHttpServletRequest mpRequest) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		boardVO.setRgstusId(user.getUserCode());
		
		// 관리자 권한이 없는 경우 오류 메시지 발생
		if(!user.getUserIsAdmin().equals("Y")) {
			mReturn.put("result", "fail");
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
		studyManagementService.reviseStudyNotice(boardVO, mpRequest);
		mReturn.put("result", "success");
		mReturn.put("message", "수정이 완료되었습니다.");
		
		return mReturn;
	}

}

