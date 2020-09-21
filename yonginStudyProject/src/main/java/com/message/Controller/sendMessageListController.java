package com.message.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.login.VO.userInfoVO;
import com.message.Service.messageService;
import com.message.VO.messageInfoVO;

@Controller
public class sendMessageListController {

	@Resource(name="messageService") // 해당 서비스가 리소스임을 표시합니다.
	private messageService messageService;
	
	@Resource(name="commonCodeService")
	private commonCodeService commonCodeService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(messageListController.class);
	
	/**
	 * 쪽지함 Mapping
	 */
	@RequestMapping(value = "/sendMessageList.do", method = RequestMethod.GET)
	public String sendMessageListForm(Locale locale, HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/message/sendMessageList";
	}
	
	/**
	 * 메시지 삭제
	 * @param messageInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSendMessage.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> messageDeleteAjaxFunction(@RequestBody messageInfoVO messageInfoVO) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		//벨리데이터 추가
		messageService.deleteSendMessage(messageInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 삭제되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 보낸 쪽지 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectSendMessageList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectSendMessageList(@RequestBody messageInfoVO messageInfoVO, HttpSession session) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
	      
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		String userCode = user.getUserCode();
		messageInfoVO.setUserCodeFrom(userCode);
		
		/*** 페이징(시작) ***/
		int dataPerPage = 12; //그리드 한 페이지에 표시할 데이터 수
    	int page = Integer.parseInt(messageInfoVO.getPage()); //페이지별 변경
    	
    	int first = page * dataPerPage + 1; //변경없이 추가
    	int last = first + dataPerPage - 1; //변경없이 추가
    	
    	messageInfoVO.setFirst(first); //변경없이 추가
    	messageInfoVO.setLast(last);   //변경없이 추가
    	
    	int total = messageService.selectSendMessageListToCnt(messageInfoVO); // 총 몇 페이지인지 확인
    	int totalPages = (int)Math.ceil(total / (double)dataPerPage); // 변경없이 추가
		
		/*** 페이징(끝) ***/
		
		List<messageInfoVO> ltResult = messageService.selectSendMessageList(messageInfoVO);
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "쪽지 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("total", total);
    	mReturn.put("totalPages", totalPages);
    	mReturn.put("dataPerPage", dataPerPage);
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
}
