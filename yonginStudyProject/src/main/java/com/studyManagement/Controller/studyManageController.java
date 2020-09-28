package com.studyManagement.Controller; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class studyManageController {

	/**
	 * 스터디 페이지 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/studyManagement/studyManage.do", method = RequestMethod.GET)
	public String studyManageForm() throws Exception {
		
		
		return "jsp/studyManagement/studyManage";
	}
} 
