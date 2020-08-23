package com.popit.start.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.popit.start.Service.testService;
import com.popit.start.VO.testVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Resource(name="testService") // 해당 서비스가 리소스임을 표시합니다.
	private testService testService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		String aa = testService.aa();
		List<testVO> testList = testService.selectlistService();
		
		System.out.println("결과 : "+aa);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("testList", testList);
		return "home";
	}
	
}
