package com.study.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.study.Service.studyService;
import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class makeStudyController {
	@Resource(name="studyService") // 해당 서비스가 리소스임을 표시합니다.
	private studyService studyService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Inject
	
	private static final Logger logger = LoggerFactory.getLogger(makeStudyController.class);
	/**
	 * 스터디 생성 팝업 Mapping
	 * @throws Exception 
	 */
	@RequestMapping(value = "/makeStudy.do", method = RequestMethod.GET)
	public String registerForm(Model model) throws Exception {
		List<commonCodeVO> studyTypecodeResult = commonCodeService.selectCommonCodeList("studyType");
		List<commonCodeVO> studyAreacodeResult = commonCodeService.selectCommonCodeList("studyArea");
		List<commonCodeVO> studyLimitcodeResult= commonCodeService.selectCommonCodeList("studyLimit");
		
		model.addAttribute("studyType", studyTypecodeResult);
		model.addAttribute("studyArea", studyAreacodeResult);
		model.addAttribute("studyLimit", studyLimitcodeResult);
		return "jsp/study/makeStudy";
	}
	
	/**
	 * 스터디 생성
	 * @param studyInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/makeStudy.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerAjaxFunction(@RequestBody studyInfoVO studyInfoVO) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		
		//벨리데이터 추가
		studyService.insertStudy(studyInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 생성되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 스터디 이름 중복체크
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/make/checkExsitingStudyName.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkExsitingStudyName(@RequestBody String studyName) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		// 앞, 뒤 공백 제거
		studyName = studyName.trim();
		
		// 같은 ID를 사용하는 데이터의 갯수 반환
		int count = studyService.selectSameStudyName(studyName);
		
		if(count == 0) {
			mReturn.put("result", "success");
			mReturn.put("message", "이 스터디 이름을 사용하실 수 있습니다.");
			
			return mReturn;
		}
		else {
			mReturn.put("result", "fail");
			mReturn.put("message", "이 스터디 이름은 이미 사용중입니다.");
			
			return mReturn;
		}
	}
}
 