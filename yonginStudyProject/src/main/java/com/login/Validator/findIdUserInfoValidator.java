package com.login.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.commonFunction.Controller.yonginFunction;
import com.login.VO.userInfoVO;

@Component
public class findIdUserInfoValidator implements Validator{

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
		
		
		/** 생일 검증(시작) **/
        //생일 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "userBirth", "","생일을 입력해주세요.");	
		
		//생일 날짜 검사
		if(!userInfoVO.getUserBirth().equals("")) {
			SimpleDateFormat timeFormat = new SimpleDateFormat ("yyyyMMdd");
			Date time = new Date();
			String today = timeFormat.format(time);
			
			String removeBirth = yonginFunction.remove(userInfoVO.getUserBirth(), '-');	//com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
					
			if(Integer.parseInt(removeBirth) > Integer.parseInt(today)) {
				error.rejectValue("userBirth","", "생일은 오늘 날짜보다 작거나 같아야합니다.");
			}
		}
        /** 생일 검증(끝) **/
		
		/** 이메일 검증(시작) **/
		//이메일 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "userEmail", "","이메일을 입력해주세요.");
		
		//이메일형식확인
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(userInfoVO.getUserEmail()).matches())) {
        	error.rejectValue("userEmail","", "이메일을 형식에 맞게 작성해주세요.");
        }
		/** 이메일 검증(끝) **/
        
        /******** 규칙 검사(끝) ********/
	}
}
