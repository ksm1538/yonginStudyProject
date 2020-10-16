package com.studyManagement.Validator;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.main.VO.calendarVO;

@Component
public class calendarValidator implements Validator{

	@Inject
    private MessageSource msg;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return calendarVO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		calendarVO calendarVO = (calendarVO) obj;
		
		/******** 규칙 검사(시작) ********/
		
		//일정 제목 빈 값 체크
		ValidationUtils.rejectIfEmpty(error, "title", "","일정 제목을 입력해주세요.");
		
		//일정 제목 길이 체크
		if(calendarVO.getTitle().length() > 30) {
			error.rejectValue("title", "", "일정 제목은 30자까지 설정 가능합니다.");
		}
		
		//일정 제목 길이 체크
		if(calendarVO.getContent().length() > 100) {
			error.rejectValue("content", "", "일정 내용은 100자까지 설정 가능합니다.");
		}
		
		ValidationUtils.rejectIfEmpty(error, "startDt", "","시작 날짜를 입력해주세요.");
		
		ValidationUtils.rejectIfEmpty(error, "startHm", "","시작 시각을 입력해주세요.");
		
		ValidationUtils.rejectIfEmpty(error, "endDt", "","종료 날짜를 입력해주세요.");
		
		ValidationUtils.rejectIfEmpty(error, "endHm", "","종료 시각을 입력해주세요.");
		
        
        
        /******** 규칙 검사(끝) ********/
	}
}
