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
public class userInfoValidator implements Validator{

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
		
		/** ID 검증(시작) **/
		//ID 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "userId", "","아이디를 입력해주세요.");
		
		//ID 길이 체크
		if(userInfoVO.getUserId().length() < 5 || userInfoVO.getUserId().length() > 20) {
			error.rejectValue("userId", "", "아이디를 5~20자로 설정해주세요.");
		}
		
		//ID 영어, 숫자 확인
		boolean flag = Pattern.matches("^[a-zA-Z0-9]*$", userInfoVO.getUserId());
   	    if(flag == false) {
   	    	error.rejectValue("userId","", "ID는 영어, 숫자만 입력가능합니다.");
   	    }
   	    /** ID 검증(끝) **/
   	    
   	    
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
		
		
		/** 비밀번호 힌트 답 검증(시작) **/
        //비밀번호 힌트 답 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "userPwHintAnswer", "","비밀번호 힌트 답을 입력해주세요.");
        /** 비밀번호 힌트 답 검증(끝) **/
		
		
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
		
		
		/** 성별 검증(시작) **/
        //성별 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "userBirth", "","성별을 입력해주세요.");	
		/** 성별 검증(끝) **/
		
		
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
