package com.studyManagement.Controller;

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
import com.commonCode.VO.commonCodeVO;
import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;
import com.main.VO.userInStudyVO;
import com.studyManagement.Service.studyManagementService;

@Controller
public class studyMemberAdminChangeController {
	@Resource(name="studyManagementService") // 해당 서비스가 리소스임을 표시합니다.
	private studyManagementService studyManagementService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	/**
	 * 스터디 멤버 직위 변경 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/studyManagement/studyMemberAdminChange.do", method = RequestMethod.POST)
	public String studyMemberAdminChangeForm(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
	
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyAuthority");
		
		model.addAttribute("userInStudyVO", new userInStudyVO());
		model.addAttribute("studyAuthorityArray", codeResult);
		
		
		return "jsp/studyManagement/studyMemberAdminChange";
	}
	
	/**
	 * 멤버 직위 확인창 보기 
	 * @param studyCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/selectStudyMemberAdminView.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyMemberAdminView(@RequestBody userInStudyVO userInStudyVO) throws Exception {
		
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(userInStudyVO.getUserCode() == null || userInStudyVO.getUserCode().equals("") || userInStudyVO.getStudyCode() == null || userInStudyVO.getStudyCode().equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}

		userInStudyVO data = studyManagementService.selectStudyMemberAdminView(userInStudyVO);
		if(data == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("boardInfo", data);
		
		return mReturn;
	}
	
	/** 
	 * 스터디 멤버 직위 변경
	 * @param studyInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/studyMemberAdminChange.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> studyInfoChange(@RequestBody userInStudyVO userInStudyVO, HttpSession session) throws Exception {
 
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
	
		// 데이터 검증 할거 있으면 검증 
		
		studyManagementService.studyMemberAdminChange(userInStudyVO);
		
		
		mReturn.put("result", "success");
		mReturn.put("message", "직위가 변경되었습니다.");
		
		return mReturn;
	}

}
