package com.main.Validator;

import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.login.VO.userInfoVO;

@Component
public class changeUserInfoValidator implements Validator{

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
		
		/** 이메일 검증(시작) **/
		//이메일 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "userEmail", "","이메일을 입력해주세요.");
		
		//이메일형식확인
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(userInfoVO.getUserEmail()).matches())) {
        	error.rejectValue("userEmail","", "이메일을 형식에 맞게 작성해주세요.");
        }
		/** 이메일 검증(끝) **/
		
        
        /** 전화번호 검증(시작) **/
        //전화번호 빈 값 체크
        ValidationUtils.rejectIfEmpty(error, "userPhoneNumber", "","휴대폰 번호를 입력해주세요.");
        //전화번호 형식 체크
        boolean flag3 = Pattern.matches("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$", userInfoVO.getUserPhoneNumber());
   	    if(flag3 == false) {
   	    	error.rejectValue("userPhoneNumber","", "휴대폰 번호를 형식에 맞게 입력해주세요.");
   	    }
        /** 전화번호 검증(끝) **/
        
        
        /** 주소 검증(시작) **/
        //주소 빈 값 체크
        ValidationUtils.rejectIfEmpty(error, "userAddress", "","주소를 입력해주세요.");
        /** 주소 검증(끝) **/
        
        
        /******** 규칙 검사(끝) ********/
	}
}
