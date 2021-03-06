package com.main.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.commonFunction.Controller.yonginFunction;
import com.login.Service.loginService;
import com.login.VO.userInfoVO;
import com.login.Validator.pwChangeValidator;
import com.main.Service.myPageService;
import com.main.VO.studyInfoVO;
import com.main.VO.userInStudyVO;
import com.main.Validator.changeUserInfoValidator;
import com.study.VO.studyApplicationFormUserVO;
import com.studyManagement.Service.studyMainService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class myPageFormController {
	@Resource(name="myPageService") // 해당 서비스가 리소스임을 표시합니다.
	private myPageService myPageService;
	
	@Autowired
	changeUserInfoValidator changeUserInfoValidator;// validator 변수 불러옴
	
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Resource(name="studyMainService")
	private studyMainService studyMainService;
	
	@Inject
    PasswordEncoder passwordEncoder;	// 암호화 기능 추가
	
	private static final Logger logger = LoggerFactory.getLogger(myPageFormController.class);
	
	/**
	 * 마이페이지 팝업 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public String myPageForm(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		
		
		//model 변수에 데이터를 담아 jsp에 전달
		String userCode = user.getUserCode();
		userInfoVO userInfoVO = myPageService.selectUserInfoData(userCode);
		List<commonCodeVO> codeResult1 = commonCodeService.selectCommonCodeList("SAFStatus");
		List<commonCodeVO> codeResult2 = commonCodeService.selectCommonCodeList("studyTopic");
		
		model.addAttribute("userInfoVO", new userInfoVO());
		model.addAttribute("currentUser", userInfoVO);
		model.addAttribute("applicationFormStatusArray", codeResult1);
		model.addAttribute("studyTopicArray", codeResult2);
		return "jsp/main/mypage";
	}

	/**
	 * 사용자 정보 변경
	 * @param userInfoVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/changeUserInfo.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changeUserInfo(@ModelAttribute("userInfoVO") userInfoVO userInfoVO, BindingResult bindingResult, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		/** 데이터 검증(시작) **/
		changeUserInfoValidator changeUserInfoValidator = new changeUserInfoValidator();
		changeUserInfoValidator.validate(userInfoVO, bindingResult);
		
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
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		userInfoVO.setUserCode(user.getUserCode());
		
		// 전화번호에서 '-' 빼기
		String removePhoneNumber = yonginFunction.remove(userInfoVO.getUserPhoneNumber(), '-');	//com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
		userInfoVO.setUserPhoneNumber(removePhoneNumber);
		
		myPageService.updateUserInfo(userInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 수정되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 마이페이지 비밀번호 변경 페이지
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/myPageChangePwForm.do", method = RequestMethod.GET)
	public String myPageChangePwForm(Model model, HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		model.addAttribute("userInfoVO", new userInfoVO());
		
		return "jsp/main/myPageChangePw";
	}
	
	/**
	 * 비밀번호 변경
	 * @param userInfoVO
	 * @param session
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myPageChangePw.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> myPageChangePw(@ModelAttribute("userInfoVO") userInfoVO userInfoVO, HttpSession session, BindingResult bindingResult) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();

		/** 데이터 검증(시작) **/
		pwChangeValidator pwChangeValidator = new pwChangeValidator();
		pwChangeValidator.validate(userInfoVO, bindingResult);
		
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
		
		// 비밀번호 찾기에서 세션에 넣은 사용자 정보 가져옴
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		if(user == null) {
			mReturn.put("result", "fail");
			mReturn.put("message", "잘못된 접근입니다.");
			return mReturn;
		}
		
		userInfoVO.setUserCode(user.getUserCode());

		// 비밀번호 암호화
		userInfoVO.setUserPw(passwordEncoder.encode(userInfoVO.getUserPw()));
				
		// 비밀번호 업데이트
		loginService.updatePw(userInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "비밀번호가 변경되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 내가 만든스터디 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myPage/selectStudyMadeByMeList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyMadeByMeList(HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		String userCode = user.getUserCode();
		
	      
		List<studyInfoVO> ltResult = myPageService.selectStudyMadeByMeList(userCode);
		
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
	 * 내가 가입된 스터디 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myPage/selectParticipateStudyList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectParticipateStudyList(HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		String userCode = user.getUserCode();
		
	      
		List<studyInfoVO> ltResult = myPageService.selectParticipateStudyList(userCode);
		
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
	 * 나의 신청서 내역 보기
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myPage/selectMyStudyApplicationFormList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectMyStudyApplicationFormList(HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		String userCode = user.getUserCode();
		
	      
		List<studyApplicationFormUserVO> ltResult = myPageService.selectMyStudyApplicationFormList(userCode);
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "신청 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	
	/**
	 * 스터디 탈퇴하기
	 * @param userInStudyVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myPage/exitStudy.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> exitStudy(@RequestBody userInStudyVO userInStudyVO, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		String userCode = user.getUserCode();
		
		userInStudyVO.setUserCode(userCode);
		
		String authority = studyMainService.selectStudyUserInfo(userInStudyVO);
		
		// 사용자가 스터디 장인 경우
		if(authority.equals("10")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "스터디 장을 위임한 후에 탈퇴해주세요.");
			
			return mReturn;
		}
		
		myPageService.exitStudy(userInStudyVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "탈퇴 되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 스터디 신청 취소하기
	 * @param vo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myPage/cancelStudyForm.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancelStudyForm(@RequestBody studyApplicationFormUserVO vo, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		String userCode = user.getUserCode();
		
		vo.setUserCode(userCode);
		
		if(vo.getUserCode().equals("") || vo.getApplicationFormCode().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "필요한 정보를 불러오는데 실패하였습니다.");
			
			return mReturn;
		}
		
		myPageService.cancelStudyForm(vo);
		
		mReturn.put("result", "success");
		mReturn.put("message", "취소가 완료되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 스터디 신청서 삭제하기
	 * @param vo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myPage/deleteStudyForm.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteStudyForm(@RequestBody studyApplicationFormUserVO vo, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		String userCode = user.getUserCode();
		
		vo.setUserCode(userCode);
		
		if(vo.getUserCode().equals("") || vo.getApplicationFormCode().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "필요한 정보를 불러오는데 실패하였습니다.");
			
			return mReturn;
		}
		
		myPageService.deleteStudyForm(vo);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 삭제되었습니다.");
		
		return mReturn;
	}
	
	
	
	
	
	
	
	

	
}
