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
		
		/******** ±ÔÄ¢ °Ë»ç(½ÃÀÛ) ********/
		
		/** ½ºÅÍµğ ÀÌ¸§ °ËÁõ(½ÃÀÛ) **/
		//½ºÅÍµğ ÀÌ¸§ ºó °ª Ã¼Å© 
		ValidationUtils.rejectIfEmpty(error, "studyName", "","½ºÅÍµğ ÀÌ¸§À» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		
		//½ºÅÍµğ ÀÌ¸§ ±æÀÌ Ã¼Å©
		if(studyInfoVO.getStudyName().length() < 5 || studyInfoVO.getStudyName().length() > 30) {
			error.rejectValue("studyName", "", "½ºÅÍµğ ÀÌ¸§À» 5~30ÀÚ·Î ¼³Á¤ÇØÁÖ¼¼¿ä.");
		}
		
		//½ºÅÍµğ ÀÌ¸§ ¿µ¾î, ¼ıÀÚ, ÇÑ±Û È®ÀÎ
		boolean flag = Pattern.matches("^[a-zA-Z0-9°¡-ÆR\s]*$", studyInfoVO.getStudyName());
   	    if(flag == false) {
   	    	error.rejectValue("studyName","", "½ºÅÍµğ ÀÌ¸§Àº ¿µ¾î, ¼ıÀÚ, ÇÑ±Û¸¸ ÀÔ·Â°¡´ÉÇÕ´Ï´Ù.");
   	    }
   	    /** ½ºÅÍµğ ÀÌ¸§ °ËÁõ(³¡) **/
   	    
   	    /** ½ºÅÍµğ ¼³¸í °ËÁõ(½ÃÀÛ) **/
        //¼³¸í ºó °ª Ã¼Å©
		ValidationUtils.rejectIfEmpty(error, "studyDesc", "","½ºÅÍµğ ¼³¸íÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");	
        /** ½ºÅÍµğ ¼³¸í °ËÁõ(³¡) **/
		
		/** ½ºÅÍµğ ÁÖÁ¦ °ËÁõ(½ÃÀÛ) **/
        //ÁÖÁ¦ ºó °ª Ã¼Å©
		ValidationUtils.rejectIfEmpty(error, "studyTopic", "","½ºÅÍµğ ÁÖÁ¦¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");	
        /** ½ºÅÍµğ ÁÖÁ¦ °ËÁõ(³¡) **/
		
		/** ½ºÅÍµğ Á¦ÇÑÀÎ¿ø °ËÁõ(½ÃÀÛ) **/
        //Á¦ÇÑÀÎ¿ø ºó °ª Ã¼Å©
		ValidationUtils.rejectIfEmpty(error, "studyLimit", "","½ºÅÍµğ Á¦ÇÑÀÎ¿øÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");	
        /** ½ºÅÍµğ Á¦ÇÑÀÎ¿ø °ËÁõ(³¡) **/
		
		/** ½ºÅÍµğ Áö¿ª °ËÁõ(½ÃÀÛ) **/
        //Áö¿ª ºó °ª Ã¼Å©
		ValidationUtils.rejectIfEmpty(error, "studyArea", "","½ºÅÍµğ Áö¿ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");	
        /** ½ºÅÍµğ Áö¿ª °ËÁõ(³¡) **/
		
		/******** ±ÔÄ¢ °Ë»ç(³¡) ********/
	
	}
}
