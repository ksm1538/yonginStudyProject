package com.login.Validator;

import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.login.VO.userInfoVO;

@Component
public class findPwUserInfoValidator implements Validator{

	@Inject
    private MessageSource msg;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return userInfoVO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		userInfoVO userInfoVO = (userInfoVO) obj;
		
		/******** 규칙 검사(시작) ********/
		
		/** 이름 검증(시작) **/
        //이름 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "userName", "","이름을 입력해주세요.");	
        /** 이름 검증(끝) **/

		/** ID 검증(시작) **/
		//ID 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "userId", "","아이디를 입력해주세요.");
		
		//ID 길이 체크
		if(userInfoVO.getUserId().length() < 5 || userInfoVO.getUserId().length() > 20) {
			error.rejectValue("userId", "", "아이디를 5~20자로 작성해주세요.");
		}
		
		//ID 영어, 숫자 확인
		boolean flag = Pattern.matches("^[a-zA-Z0-9]*$", userInfoVO.getUserId());
   	    if(flag == false) {
   	    	error.rejectValue("userId","", "ID는 영어, 숫자만 입력가능합니다.");
   	    }
   	    /** ID 검증(끝) **/
   	    
   	    /** 비밀번호 힌트 답 검증(시작) **/
        //비밀번호 힌트 답 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "userPwHintAnswer", "","비밀번호 힌트 답을 입력해주세요.");
        /** 비밀번호 힌트 답 검증(끝) **/
        
        /******** 규칙 검사(끝) ********/
	}
}
