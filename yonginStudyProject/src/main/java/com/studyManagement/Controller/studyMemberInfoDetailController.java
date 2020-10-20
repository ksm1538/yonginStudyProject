package com.studyManagement.Controller;

import java.util.HashMap;
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
import com.commonFunction.Controller.yonginFunction;
import com.login.VO.userInfoVO;
import com.studyManagement.Service.studyManagementService;

@Controller
public class studyMemberInfoDetailController {
	@Resource(name="studyManagementService") // 해당 서비스가 리소스임을 표시합니다.
	private studyManagementService studyManagementService;

	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	/**
	 * 스터디 멤버 상세보기 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/studyManagement/studyMemberInfoDetail.do", method = RequestMethod.POST)
	public String studyMemberInfoDetail(Model model, HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		model.addAttribute("userInfoVO", new userInfoVO());
		
		return "jsp/studyManagement/studyMemberInfoDetail";
	}
	
	/**
	 * 스터디 상세 보기 
	 * @param systemNoticeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studyManagement/selectStudyMemberInfoDetail.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyInfoDetail(@RequestBody String userCode) throws Exception {
		
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(userCode == null || userCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		userInfoVO userInfoVO = studyManagementService.selectStudyMemberManage(userCode);
		
		if(userInfoVO == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		String birth = yonginFunction.nullConvert(userInfoVO.getUserBirth());
		userInfoVO.setUserBirth(yonginFunction.addMinusChar(birth));
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("boardInfo", userInfoVO);
		
		return mReturn;
	}
}
