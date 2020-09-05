package com.login.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.login.VO.userInfoVO;

@Component
public class userInfoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
        // 타입이 안맞으면 invalid target for Validator 오류가 뜬다!
		return userInfoVO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		ValidationUtils.rejectIfEmpty(error, "userId", "userInfoVO.userId.empty","아이디를 입력해주세요.");
		ValidationUtils.rejectIfEmpty(error, "userPw", "userInfoVO.userPw.empty","비밀번호를 입력해주세요");
       
		userInfoVO userInfoVO = (userInfoVO) obj;
		
		/*
		 * //만약 이메일의 값을 검증하고싶다면 Pattern 클래스를 이용하자! Pattern pattern =
		 * Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
		 * Pattern.CASE_INSENSITIVE); if
		 * (!(pattern.matcher(memberDto.getId()).matches())) { err.rejectValue("id",
		 * "member.email.invalid"); }
		 */
	}
}
