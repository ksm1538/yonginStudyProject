package com.commonFunction.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonFunction.Service.replyService;
import com.commonFunction.VO.replyVO;
import com.login.VO.userInfoVO;

@Controller
public class replyController {
	
	@Resource(name="replyService")
	private replyService replyService;
	 
	/**
	 * 댓글 저장
	 * @param replyVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply/insertReply.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertReply(@RequestBody replyVO replyVO, HttpSession session) throws Exception {
		
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(replyVO.getBoardCode() == null || replyVO.getBoardCode().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "Board 코드가 없습니다.");
			
			return mReturn;
		}
		if(replyVO.getReplyText().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "댓글 내용을 입력해주세요.");
			
			return mReturn;
		}
		
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		replyVO.setRgstusId(user.getUserCode());
		
		replyService.insertReply(replyVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "댓글을 작성하였습니다.");
		
		return mReturn;
	}
	
	/**
	 * 댓글 조회
	 * @param boardCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply/selectReplyList.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectReplyList(@RequestBody String boardCode) throws Exception {
		
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(boardCode == null || boardCode.equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "Board 코드가 없습니다.");
			
			return mReturn;
		}
		
		List<replyVO> ltResult = replyService.selectReplyList(boardCode);
		
		mReturn.put("result", "success");
		mReturn.put("message", "댓글을 조회하였습니다.");
		mReturn.put("resultList", ltResult);
		
		return mReturn;
	}
	
	/**
	 * 댓글 삭제
	 * @param replyCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply/deleteReply.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteReply(@RequestBody String replyCode, HttpSession session) throws Exception {
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(replyCode == null || replyCode.equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "댓글 코드가 없습니다.");
			
			return mReturn;
		}
		
		/** 댓글 작성자와 현재 접속자 비교(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		replyVO data = replyService.selectReplyWithId(replyCode);
		
		if(!(data.getRgstusCode().equals(user.getUserCode()))) {
			mReturn.put("result", "fail");
			mReturn.put("message", "권한이 없습니다.");
			
			return mReturn;
		}
		/** 댓글 작성자와 현재 접속자 비교(끝) **/
		
		replyService.deleteReply(replyCode);
		
		mReturn.put("result", "success");
		mReturn.put("message", "댓글을 성공적으로 삭제했습니다.");
		
		return mReturn;
	}
	
	/**
	 * 댓글 수정
	 * @param replyVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply/updateReply.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateReply(@RequestBody replyVO replyVO, HttpSession session) throws Exception {
		
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(replyVO.getReplyText().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "댓글 내용을 입력해주세요.");
			
			return mReturn;
		}
		if(replyVO.getReplyCode().equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "댓글 코드가 없습니다.");
			
			return mReturn;
		}
		
		/** 댓글 작성자와 현재 접속자 비교(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		replyVO data = replyService.selectReplyWithId(replyVO.getReplyCode());
		
		if(!(data.getRgstusCode().equals(user.getUserCode()))) {
			mReturn.put("result", "fail");
			mReturn.put("message", "권한이 없습니다.");
			
			return mReturn;
		}
		/** 댓글 작성자와 현재 접속자 비교(끝) **/
		
		replyService.updateReply(replyVO);
		
		mReturn.put("result", "success");
		mReturn.put("message", "수정이 완료되었습니다.");
		
		return mReturn;
	}
	
	/**
	 * 댓글 상세 조회
	 * @param replyCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply/selectReplyWithId.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectReplyWithId(@RequestBody String replyCode, HttpSession session) throws Exception {
		
		HashMap<String, Object> mReturn = new HashMap<String, Object>();
		
		if(replyCode.equals("")) {
			mReturn.put("result", "fail");
			mReturn.put("message", "댓글 코드가 없습니다.");
			
			return mReturn;
		}
		replyVO data = replyService.selectReplyWithId(replyCode);
		
		/** 댓글 작성자와 현재 접속자 비교(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");
		
		if(!(data.getRgstusCode().equals(user.getUserCode()))) {
			mReturn.put("result", "fail");
			mReturn.put("message", "권한이 없습니다.");
			
			return mReturn;
		}
		/** 댓글 작성자와 현재 접속자 비교(끝) **/
		
		mReturn.put("result", "success");
		mReturn.put("message", "조회에 성공하였습니다.");
		mReturn.put("replyVO", data);
		
		return mReturn;
	}
	
	
	/**
	 * 댓글 달기 팝업창
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply/replyOnReplyForm.do", method = RequestMethod.POST)
	public String replyOnReplyForm(HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/common/replyOnReply";
	}
	
	/**
	 * 댓글 수정 팝업창
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply/updateReplyForm.do", method = RequestMethod.POST)
	public String updateReplyForm(HttpSession session) throws Exception {
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(시작) **/
		userInfoVO user = (userInfoVO) session.getAttribute("user");

		if(user == null) {
			return "jsp/login/login";
		}
		/** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
		
		return "jsp/common/updateReply";
	}
	
}