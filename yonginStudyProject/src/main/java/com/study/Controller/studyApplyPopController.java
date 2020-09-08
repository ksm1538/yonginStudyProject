package com.study.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.Service.studyService;
import com.main.VO.studyInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class studyApplyPopController {
	@Resource(name="studyService") // 해당 서비스가 리소스임을 표시합니다.
	private studyService studyService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(studyApplyPopController.class);
	
	/**
	 * 스터디 더보기 Mapping
	 */
	@RequestMapping(value = "/studyApplyPop.do", method = RequestMethod.GET)
	public String studyApplyPopForm() {
		   
		return "jsp/study/studyApplyPop";
	}
}

