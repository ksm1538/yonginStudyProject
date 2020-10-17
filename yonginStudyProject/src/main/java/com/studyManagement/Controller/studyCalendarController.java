package com.studyManagement.Controller; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commonCode.Service.commonCodeService;
import com.commonCode.VO.commonCodeVO;
import com.commonFunction.Controller.yonginFunction;
import com.login.VO.userInfoVO;
import com.main.VO.calendarVO;
import com.main.VO.userInStudyVO;
import com.studyManagement.Service.studyMainService;
import com.studyManagement.Validator.calendarValidator;

@Controller
public class studyCalendarController {
   @Resource(name="studyMainService") // 해당 서비스가 리소스임을 표시합니다.
   private studyMainService studyMainService;

   @Resource(name="commonCodeService")
   private commonCodeService commonCodeService;
   
   /**
    * 달력 일정 작성하기
    * @param session
    * @return
    * @throws Exception
    */
   @RequestMapping(value = "/studyManagement/calendarWrite.do", method = RequestMethod.POST)
   public String calendarWritePopup(HttpSession session, Model model) throws Exception {
      /** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지 로 이동(시작) **/
      userInfoVO user = (userInfoVO) session.getAttribute("user");

      if(user == null) {
         return "jsp/login/login";
      }
      /** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
      
      List<commonCodeVO> codeResult = commonCodeService.selectCommonCodeList("calendarType");
      
      model.addAttribute("calendarType", codeResult);
      
      return "jsp/studyManagement/calendarWrite";
   }
   
   /**
    * 달력 팝업 보기
    * @param session
    * @return
    * @throws Exception
    */
   @RequestMapping(value = "/studyManagement/calendarPopup.do", method = RequestMethod.POST)
   public String calendarDetailPopup(HttpSession session) throws Exception {
      /** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지 로 이동(시작) **/
      userInfoVO user = (userInfoVO) session.getAttribute("user");

      if(user == null) {
         return "jsp/login/login";
      }
      /** 세션에 유저가 정상적으로 등록되어 있지 않다면 로그인 페이지로 이동(끝) **/
      return "jsp/studyManagement/calendarPopup";
   }
   
   /**
    * 일정 저장
    * @param calendarVO
    * @param session
    * @param bindingResult
    * @return
    * @throws Exception
    */
   @RequestMapping(value="/studyManagement/saveCalendar.json", method = RequestMethod.POST)
   @ResponseBody
   public Map<String, Object> saveCalendar(@RequestBody calendarVO calendarVO, HttpSession session, BindingResult bindingResult) throws Exception {
         
      HashMap<String, Object> mReturn = new HashMap<String, Object>();
      
      userInfoVO user = (userInfoVO) session.getAttribute("user");
      
      /** 권한 체크(시작) **/
      userInStudyVO userinfo = new userInStudyVO();
      
      userinfo.setUserCode(user.getUserCode());
      userinfo.setStudyCode(calendarVO.getStudyCode());
      
      if(userinfo.getUserCode().equals("") || userinfo.getStudyCode().equals("")) {
         mReturn.put("result", "fail");
         mReturn.put("message", "오류가 발생하였습니다.");
         
         return mReturn;
      }
      
      String result = studyMainService.selectStudyUserInfo(userinfo);
      if(!(result.equals("10") || result.equals("20"))) {
         mReturn.put("result", "fail");
         mReturn.put("message", "권한이 없습니다.");
         
         return mReturn;
      }
      /** 권한 체크(끝) **/
      
      /** 데이터 검증(시작) **/
      calendarValidator calendarValidator = new calendarValidator();
      calendarValidator.validate(calendarVO, bindingResult);
      
      // 에러 검출 시 에러 메시지와 함께 종료
      if (bindingResult.hasErrors()) {
         List<FieldError> errors = bindingResult.getFieldErrors();
         String errorMsg = "";
          for (FieldError error : errors ) {
             errorMsg += error.getDefaultMessage() + "\n";
          }

          mReturn.put("result", "fail");
         mReturn.put("message", errorMsg);
         
         return mReturn;
      }  
      /** 데이터 검증(끝) **/
      
      // 시작 날짜에서 '-' 빼기
      String removeStartDt = yonginFunction.remove(calendarVO.getStartDt(), '-');   //com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
      calendarVO.setStartDt(removeStartDt);
      
      // 종료 날짜에서 '-' 빼기
      String removeEndDt = yonginFunction.remove(calendarVO.getEndDt(), '-');   //com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
      calendarVO.setEndDt(removeEndDt);
      
      // 시작 시각에서 ':' 빼기
      String removeStartHm = yonginFunction.remove(calendarVO.getStartHm(), ':');   //com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
      calendarVO.setStartHm(removeStartHm);
      
      // 종료 시각에서 ':' 빼기
      String removeEndHm = yonginFunction.remove(calendarVO.getEndHm(), ':');   //com.commonFunction.Controller에 있는 공통 함수를 이용해 문자 제거
      calendarVO.setEndHm(removeEndHm);
               
      // 시간 유효성 검사
        if(!yonginFunction.validTime(calendarVO.getStartHm()) || !yonginFunction.validTime(calendarVO.getEndHm())) {
           mReturn.put("result", "fail");
            mReturn.put("message", "유효하지 않은 값입니다.");
            
            return mReturn;
        } else if(Integer.parseInt(removeStartDt) >= Integer.parseInt(removeEndDt)) {
           String startDate = removeStartDt+removeStartHm;
           String endDate = removeEndDt+removeEndHm;
           
           if(Long.parseLong(startDate) > Long.parseLong(endDate)) {
               mReturn.put("result", "fail");
                mReturn.put("message", "올바른 시간 범위가 아닙니다.");
                
                return mReturn;
            }
        }
        
      // 신규 저장
      if(calendarVO.getCalendarCode().equals("NEW")) {
         // 스터디 코드가 존재하지 않을 경우 에러 발생 시킴
         if(calendarVO.getStudyCode().equals("")) {
            mReturn.put("result", "fail");
            mReturn.put("message", "스터디 정보가 존재하지 않습니다.");
            
            return mReturn;
         }
         
         calendarVO.setRgstusId(user.getUserCode());
         
         studyMainService.insertCalendar(calendarVO);
         
         mReturn.put("result", "success");
         mReturn.put("message", "성공적으로 등록되었습니다.");
         
         return mReturn;
      }
      // 수정
      else {
         // 캘린더 코드가 존재하지 않을 경우 에러 발생 시킴
         if(calendarVO.getCalendarCode().equals("")) {
            mReturn.put("result", "fail");
            mReturn.put("message", "캘린더 정보가 존재하지 않습니다.");
            
            return mReturn;
         }
         
         calendarVO.setUpdtusId(user.getUserCode());
         studyMainService.updateCalendar(calendarVO);
         
         mReturn.put("result", "success");
         mReturn.put("message", "성공적으로 수정되었습니다.");
         
         return mReturn;
      }
   }
   
   
   /**
    * 일정 삭제
    * @param calendarVO
    * @param session
    * @param bindingResult
    * @return
    * @throws Exception
    */
   @RequestMapping(value="/studyManagement/deleteCalendar.json", method = RequestMethod.POST)
   @ResponseBody
   public Map<String, Object> deleteCalendar(@RequestBody calendarVO calendarVO, HttpSession session, BindingResult bindingResult) throws Exception {
         
      HashMap<String, Object> mReturn = new HashMap<String, Object>();
      
      userInfoVO user = (userInfoVO) session.getAttribute("user");
      
      calendarVO.setUpdtusId(user.getUserCode());
      
      /** 권한 체크(시작) **/
      userInStudyVO userinfo = new userInStudyVO();
      
      userinfo.setUserCode(user.getUserCode());
      userinfo.setStudyCode(calendarVO.getStudyCode());
      
      if(userinfo.getUserCode().equals("") || userinfo.getStudyCode().equals("")) {
         mReturn.put("result", "fail");
         mReturn.put("message", "오류가 발생하였습니다.");
         
         return mReturn;
      }
      
      String result = studyMainService.selectStudyUserInfo(userinfo);
      if(!(result.equals("10") || result.equals("20"))) {
         mReturn.put("result", "fail");
         mReturn.put("message", "권한이 없습니다.");
         
         return mReturn;
      }
      /** 권한 체크(끝) **/
      
      studyMainService.deleteCalendar(calendarVO);
      
      mReturn.put("result", "success");
      mReturn.put("message", "성공적으로 삭제되었습니다.");
      
      return mReturn;
   }
   
}
 