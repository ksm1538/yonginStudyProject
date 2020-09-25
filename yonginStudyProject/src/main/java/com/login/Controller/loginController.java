package com.login.Controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.login.Service.loginService;
import com.login.VO.userInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class loginController {
	@Resource(name="loginService") 
	private loginService loginService;	// 서비스 변수 추가
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	@Inject
    PasswordEncoder passwordEncoder;	// 암호화 기능 추가
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	/**
	 * 로그인 화면 Controller
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) throws Exception {
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user != null) {
			List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyTopic");
			
			//model 변수에 데이터를 담아 jsp에 전달
			model.addAttribute("studyTopicArray", codeResult);
			
			return "jsp/main/main";
		}
		return "jsp/login/login";
	}
	
	/**
	 * 로그인 기능
	 * @param userInfoVO
	 * @param req
	 * @param rttr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public String login(userInfoVO userInfoVO, HttpServletRequest req, RedirectAttributes rttr, Model model) throws Exception{
		logger.info("post login");
		
		HttpSession session = req.getSession();
		
		// ID나 PW가 비어있는 경우
		if(userInfoVO.getUserId().equals("") && userInfoVO.getUserPw().equals("")) {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", "ID와 PW를 입력해주세요.");
			return "redirect:/";
		}
		else if(userInfoVO.getUserId().equals("")) {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", "ID를  입력해주세요.");
			return "redirect:/";
		}
		else if(userInfoVO.getUserPw().equals("")) {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", "PW를  입력해주세요.");
			return "redirect:/";
		}
		
		// 입력한 ID와 PW를 이용해 select 
		userInfoVO login = loginService.login(userInfoVO);
		
		// ID와 PW가 다를 경우
		if(login == null || !(passwordEncoder.matches(userInfoVO.getUserPw(), login.getUserPw()))) {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", "ID 또는 PW를 확인해주세요.");
			return "redirect:/";
		// ID와 PW가 성공적으로 접속한 경우
		}else {
			login.setUserPw("");
			session.setAttribute("user", login);
			
			List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("studyTopic");
			List<commonCodeVO> codeResult2 = commonCodeService.selectCommonCodeList("calendarType");
			
			//model 변수에 데이터를 담아 jsp에 전달
			model.addAttribute("studyTopicArray", codeResult);
			model.addAttribute("calendarType", codeResult2);
			
			return "jsp/main/main";
		}
	}
	
	/**
	 * 로그아웃 기능
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout.json", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}	
} 
