package com.study.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.main.VO.studyInfoVO;
import com.study.Service.studyService;

@Controller
public class studyInfoDetailPopupController {
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Resource(name="studyService") // 해당 서비스가 리소스임을 표시합니다.
	private studyService studyService;
	
	@RequestMapping(value = "/study/studyInfoDetailPopup.do", method = RequestMethod.POST)
	public String login(Model model) throws Exception {

		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyTopic");
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("studyTopicArray", codeResult);
		model.addAttribute("studyInfoVO", new studyInfoVO());
		
		return "jsp/study/studyInfoDetailPopup"; 
	}
	
	
	@RequestMapping(value="/study/selectStudyInfoDetail.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyInfoDetail(@RequestBody String studyCode) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(studyCode == null || studyCode.equals("")) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		studyInfoVO studyInfo = studyService.selectStudyInfoDetail(studyCode);
		
		if(studyInfo == null) {
			mReturn.put("result","fail");
			mReturn.put("message", "오류가 발생하였습니다.");
			
			return mReturn;
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 조회하였습니다.");
		mReturn.put("studyInfo", studyInfo);
		
		return mReturn;
	}
}
