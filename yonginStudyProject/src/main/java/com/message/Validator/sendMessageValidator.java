package com.message.Validator;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.message.VO.messageInfoVO;

@Component
public class sendMessageValidator implements Validator{

	@Inject
    private MessageSource msg;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return messageInfoVO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		messageInfoVO messageInfoVO = (messageInfoVO) obj;
		
		/******** 규칙 검사(시작) ********/
		
		/** 수신자 아이디 검증(시작) **/
		//쪽지 내용 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "userCodeTo", "","받는 사람 아이디를 입력해주세요.");
   	    /** 수신자 아이디 검증(끝) **/
		
		/** 쪽지 제목 검증(시작) **/
		//쪽지 제목 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "messageTitle", "","쪽지 제목을 입력해주세요.");
		
		//쪽지 제목 길이 체크
		if(messageInfoVO.getMessageTitle().length() > 30) {
			error.rejectValue("title", "", "쪽지 제목은 30글자까지 입력가능합니다.");
		}
   	    /** 쪽지 제목 검증(끝) **/
   	    
		/** 쪽지 내용 검증(시작) **/
		//쪽지 내용 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "messageDesc", "","쪽지 내용을 입력해주세요.");
   	    /** 쪽지 내용 검증(끝) **/

        /******** 규칙 검사(끝) ********/
	}
}
