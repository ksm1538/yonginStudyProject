package com.studyManagement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class studyMainController {

	/**
	 * 스터디 페이지 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/studyManagement/studyMain.do", method = RequestMethod.GET)
	public String studyMainForm() throws Exception {
		
		
		return "jsp/studyManagement/studyMain";
	}
}
