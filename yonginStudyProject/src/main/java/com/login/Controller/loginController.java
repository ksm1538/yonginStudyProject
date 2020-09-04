package com.login.Controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.login.Service.loginService;
import com.login.VO.userInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class loginController {
	@Resource(name="loginService") // 해당 서비스가 리소스임을 표시합니다.
	private loginService loginService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	/**
	 * 로그인 화면 Controller
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(userInfoVO userInfoVO, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		HttpSession session = req.getSession();
		System.out.println("아이디 : "+userInfoVO.getUserId());
		System.out.println("비번 : "+userInfoVO.getUserPw());
		userInfoVO login = loginService.login(userInfoVO);
		
		if(login == null) {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", "로그인에 실패하였습니다.");
			return "jsp/main/main";
			
		}else {
			session.setAttribute("user", login);
			return "jsp/main/main";
		}
	}
	
	/**
	 * 로그아웃 기능
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
