package com.message.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.message.Service.messageService;
import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;
import com.message.VO.messageInfoVO;

@Controller
public class messageListController {

	@Resource(name="messageService") // 해당 서비스가 리소스임을 표시합니다.
	private messageService messageService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(messageListController.class);
	
	/**
	 * 쪽지함 Mapping
	 */
	@RequestMapping(value = "/message.do", method = RequestMethod.GET)
	public String messageListForm(Locale locale, HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/message/messageList";
	}
	
	/**
	 * 메시지 삭제
	 * @param messageInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteMessage.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> messageDeleteAjaxFunction(@RequestBody messageInfoVO messageInfoVO) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		
		//벨리데이터 추가
		messageService.deleteMessage(messageInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 삭제되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 쪽지 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectMessageList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectMessageList(HttpServletRequest request) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		List<messageInfoVO> ltResult = messageService.selectMessageList();
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "쪽지 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	
	
}
