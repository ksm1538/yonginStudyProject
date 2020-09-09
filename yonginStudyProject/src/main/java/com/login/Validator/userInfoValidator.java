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
public class userInfoValidator implements Validator{

	@Inject
    private MessageSource msg;
	
	@Override
	public boolean supports(Class<?> clazz) {
        // 타입이 안맞으면 invalid target for Validator 오류가 뜬다!
		return userInfoVO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		ValidationUtils.rejectIfEmpty(error, "userId", "","아이디를 입력해주세요.");
		ValidationUtils.rejectIfEmpty(error, "userPw", "","비밀번호를 입력해주세요");
		ValidationUtils.rejectIfEmpty(error, "userPwHintAnswer", "","비밀번호 힌트 답을 입력해주세요");
		ValidationUtils.rejectIfEmpty(error, "userName", "","이름을 입력해주세요");
		
		/**
		 * 영어,숫자 공백X
		 * 
		 */
		userInfoVO userInfoVO = (userInfoVO) obj;
		
		
		
		//ID 길이 체크
		if(userInfoVO.getUserId().length() < 5 || userInfoVO.getUserId().length() > 20) {
			error.rejectValue("userId", "", "아이디를 5~20자로 설정해주세요");
		}
		
		//비밀번호 길이 체크
		if(userInfoVO.getUserPw().length() < 8 || userInfoVO.getUserPw().length() > 20) {
			error.rejectValue("userPw", "", "비밀번호를 8~20자로 설정해주세요.");
		}
		
		//비밀번호 비밀번호확인 체크
		if(!(userInfoVO.getUserPw().equals(userInfoVO.getUserPwConfirm()))){
			error.rejectValue("userPw", "", "비밀번호와 비밀번호 확인이 다릅니다.");
		}
		
		//ID 영어숫자 확인
		boolean flag = Pattern.matches("^[a-zA-Z0-9]*$", userInfoVO.getUserId());
   	    if(flag == false) {
   	    	  error.rejectValue("userId","", "ID는 영어, 숫자만 입력가능합니다.");
   	    }
		//비밀번호 영어숫자특문확인
		
		//이메일형식확인
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
          if (!(pattern.matcher(userInfoVO.getUserEmail()).matches())) {
        	  error.rejectValue("userEmail","", "이메일을 형식에 맞게 작성해주세요.");
          }
          
		//생일확인
		
		//
		//
		
		/*
		 * //만약 이메일의 값을 검증하고싶다면 Pattern 클래스를 이용하자! Pattern pattern =
		 * Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
		 * Pattern.CASE_INSENSITIVE); if
		 * (!(pattern.matcher(memberDto.getId()).matches())) { err.rejectValue("id",
		 * "member.email.invalid"); }
		 */
	}
}
