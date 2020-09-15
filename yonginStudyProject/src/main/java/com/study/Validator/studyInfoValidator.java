package com.study.Validator;

import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.main.VO.studyInfoVO;

@Component
public class studyInfoValidator implements Validator{

	@Inject
    private MessageSource msg;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return studyInfoVO.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors error) {
		studyInfoVO studyInfoVO = (studyInfoVO) obj;
		
		/******** 규칙 검사(시작) ********/
		
		/** 스터디 이름 검증(시작) **/
		//스터디 이름 빈 값 체크 
		ValidationUtils.rejectIfEmpty(error, "studyName", "","스터디 이름을 입력해주세요.");
		
		//스터디 이름 길이 체크
		if(studyInfoVO.getStudyName().length() < 5 || studyInfoVO.getStudyName().length() > 30) {
			error.rejectValue("studyName", "", "스터디 이름을 5~30자로 설정해주세요.");
		}
		
		//스터디 이름 영어, 숫자 확인
		boolean flag = Pattern.matches("^[a-zA-Z0-9]*$", studyInfoVO.getStudyName());
   	    if(flag == false) {
   	    	error.rejectValue("studyName","", "스터디 이름은 영어, 숫자만 입력가능합니다.");
   	    }
   	    /** 스터디 이름 검증(끝) **/
   	    
   	    /** 스터디 설명 검증(시작) **/
        //설명 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "studyDesc", "","스터디 설명을 입력해주세요.");	
        /** 스터디 설명 검증(끝) **/
		
		/** 스터디 주제 검증(시작) **/
        //주제 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "studyTopic", "","스터디 주제를 입력해주세요.");	
        /** 스터디 주제 검증(끝) **/
		
		/** 스터디 제한인원 검증(시작) **/
        //제한인원 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "studyLimit", "","스터디 제한인원을 입력해주세요.");	
        /** 스터디 제한인원 검증(끝) **/
		
		/** 스터디 지역 검증(시작) **/
        //지역 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "studyArea", "","스터디 지역을 입력해주세요.");	
        /** 스터디 지역 검증(끝) **/
		
		/******** 규칙 검사(끝) ********/
	
	}
}
