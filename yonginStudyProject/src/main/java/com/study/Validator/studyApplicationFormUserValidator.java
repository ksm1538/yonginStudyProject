package com.study.Validator;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.VO.studyApplicationFormUserVO;

@Component
public class studyApplicationFormUserValidator implements Validator{

	@Inject
    private MessageSource msg;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return studyApplicationFormUserVO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		studyApplicationFormUserVO studyApplicationFormUserVO = (studyApplicationFormUserVO) obj;
		
		/******** 규칙 검사(시작) ********/
		
		/** 신청할 스터디 코드 검증(시작) **/
		//신청서 내용 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "studyCode", "","신청할 스터디의 ID가 없습니다.");
		
   	    /** 신청할 스터디 코드 검증(끝) **/
		
		/** 신청서 제목 검증(시작) **/
		//신청서 제목 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "title", "","신청서 제목을 입력해주세요.");
		
		//신청서 제목 길이 체크
		if(studyApplicationFormUserVO.getTitle().length() > 30) {
			error.rejectValue("title", "", "신청서 제목은 30글자까지 입력가능합니다.");
		}
   	    /** 신청서 제목 검증(끝) **/
   	    
		/** 신청서 내용 검증(시작) **/
		//신청서 내용 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "applicationFormDesc", "","신청서 내용을 입력해주세요.");
		
   	    /** 신청서 내용 검증(끝) **/

        /******** 규칙 검사(끝) ********/
	}
}
