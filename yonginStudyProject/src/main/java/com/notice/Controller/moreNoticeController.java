package com.notice.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.VO.userInfoVO;
import com.notice.Service.systemNoticeService;
import com.notice.VO.moreNoticeInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class moreNoticeController {
	@Resource(name="systemNoticeService") // 해당 서비스가 리소스임을 표시합니다.
	private systemNoticeService systemNoticeService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(moreNoticeController.class);
	
	/**
	 * 공지사항 더보기 Mapping
	 */
	@RequestMapping(value = "/moreNotice.do", method = RequestMethod.GET)
	public String MoreNoticeForm(HttpSession session) {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		 
		return "jsp/notice/moreNotice";
	}
	
	/**
	 * 시스템 공지사항 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/selectSystemNoticeList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectSystemNoticeList() throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		List<moreNoticeInfoVO> ltResult = systemNoticeService.selectSystemNoticeList();
		
		if(ltResult.size() < 1) {
			mReturn.put("result", "fail");
			mReturn.put("message", "쪽지 목록이 없습니다.");
		}
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회 성공하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	
	/**
	 * 시스템 공지사항 삭제
	 * @param moreNoticeInfoVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/deleteSystemNotice.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteMessageTo(@RequestBody moreNoticeInfoVO moreNoticeInfoVO) throws Exception {
	      
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		systemNoticeService.deleteSystemNotice(moreNoticeInfoVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "성공적으로 삭제되었습니다.");
		
		return mReturn;
	}

}
