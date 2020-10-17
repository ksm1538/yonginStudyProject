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
import com.studyManagement.Service.studyManagementService;

@Controller
public class studyInfoChangeController {
	@Resource(name="studyManagementService") // 해당 서비스가 리소스임을 표시합니다.
	private studyManagementService studyManagementService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;

	/**
	 * 스터디 정보 수정 Mapping
	 */
	@RequestMapping(value = "/studyManagemet/studyInfoChange.do", method = RequestMethod.POST)
	public String studyInfoChange(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyTopic");
		
		model.addAttribute("studyInfoVO", new studyInfoVO());
		model.addAttribute("studyTopicArray", codeResult);
		
		return "jsp/studyManagement/studyInfoChange"; 
	}
	
	/**
	 * 스터디 정보창 보기 
	 * @param studyCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/selectStudyInfoView.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyInfoView(@RequestBody String studyCode) throws Exception {
		
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(studyCode == null || studyCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		studyInfoVO studyInfoVO = studyManagementService.selectStudyInfoView(studyCode);
		
		if(studyInfoVO == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("boardInfo", studyInfoVO);
		
		return mReturn;
	}
	
	/** 
	 * 스터디 정보 변경
	 * @param studyInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/studyInfoChange.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> studyInfoChange(@RequestBody studyInfoVO studyInfoVO) throws Exception {
 
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	
		// 데이터 검증 할거 있으면 검증 
		
		studyManagementService.studyInfoChange(studyInfoVO);
		
		System.out.println("End");
		
		mReturn.put("result", "success");
		mReturn.put("message", "변경되었습니다.");
		
		return mReturn;
	}
}
