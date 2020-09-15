package com.study.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;

public class studyInfoDetailPopupController {
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@RequestMapping(value = "/study/studyInfoDetailPopup.do", method = RequestMethod.POST)
	public String login(Model model) throws Exception {

		List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyTopic");
		
		//model 변수에 데이터를 담아 jsp에 전달
		model.addAttribute("studyTopicArray", codeResult);
		System.out.println("으흠");
		return "jsp/study/studyInfoDetailPopup"; 
	}
}
