package com.study.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
public class moreStudyController {
	@Resource(name="studyService") // 해당 서비스가 리소스임을 표시합니다.
	private studyService studyService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(moreStudyController.class);
	
	/**
	 * 스터디더보기 Mapping
	 */
	@RequestMapping(value = "/moreStudy.do", method = RequestMethod.GET)
	public String MoreStudyForm() {
		 
		return "jsp/study/moreStudy";
	}
	
	/**
	 * 스터디 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/study/selectStudyList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectStudyList(HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		List<studyInfoVO> ltResult = studyService.selectStudyList();
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "스터디 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}

}
