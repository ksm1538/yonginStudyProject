package com.notice.Validator; 

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.notice.VO.boardVO;

@Component
public class systemNoticeValidator implements Validator{

	@Inject
    private MessageSource msg;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return boardVO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		boardVO boardVO = (boardVO) obj;
		
		/******** 규칙 검사(시작) ********/
		
		/** 공지사항 제목 검증(시작) **/
		ValidationUtils.rejectIfEmpty(error, "boardTitle", "","공지사항 제목을 입력해주세요.");
   	    /** 공지사항 제목 검증(끝) **/
		
		/** 공지사항 내용 검증(시작) **/
		ValidationUtils.rejectIfEmpty(error, "boardDesc", "","공지사항 내용을 입력해주세요.");
   	    /** 공지사항 내용 검증(끝) **/

        /******** 규칙 검사(끝) ********/
	}
}
