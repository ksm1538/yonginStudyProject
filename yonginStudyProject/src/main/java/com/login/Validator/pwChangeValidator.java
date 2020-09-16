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
public class pwChangeValidator implements Validator{

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
		
   	    /** PW 검증(시작) **/
   	    //비밀번호 빈 값 체크 
   	    ValidationUtils.rejectIfEmpty(error, "userPw", "","비밀번호를 입력해주세요.");
   	    //비밀번호 확인 빈 값 체크
   	    ValidationUtils.rejectIfEmpty(error, "userPwConfirm", "","비밀번호 확인을 입력해주세요.");
   	    
   	    //비밀번호 길이 체크
		if(userInfoVO.getUserPw().length() < 10 || userInfoVO.getUserPw().length() > 20) {
			error.rejectValue("userPw", "", "비밀번호를 10~20자로 설정해주세요.");
		}
   			
		//비밀번호, 비밀번호확인 일치 여부 체크
		if(!(userInfoVO.getUserPw().equals(userInfoVO.getUserPwConfirm()))){
			error.rejectValue("userPw", "", "비밀번호와 비밀번호 확인이 다릅니다.");
		}
		
		//비밀번호 영어,숫자 확인
		boolean flag2 = Pattern.matches("^[a-zA-Z0-9]*$", userInfoVO.getUserPw());
   	    if(flag2 == false) {
   	    	error.rejectValue("userPw","", "비밀번호는 영어, 숫자만 입력가능합니다.");
   	    }
   	    /** PW 검증(끝) **/
		
        /******** 규칙 검사(끝) ********/
	}
}
