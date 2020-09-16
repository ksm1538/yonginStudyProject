<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!------ JSP 설정(끝) ------>

<!DOCTYPE html>
<html>
<head>
<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 필요X 생략 -->

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/login/findPw.js"></script>

<title>비밀번호 찾기</title>
</head>
<body>

<div class="col-12 find_pw_wrap">

   <div class="col-12 col-center mw-1200 search_memeber_title_wrap">
      <div class="circle_btn" onClick="self.close()"></div> 
      <div class="tc search_memeber_title_con">
         <div class="search_memeber_title">비밀번호 찾기</div>
      </div>
   </div>
   
   
   
   <form:form method="POST" modelAttribute="userInfoVO" name="findPwForm" id="findPwForm" action="/findPw.json">
      <div class="col-12 col-center mw-1200 search_memeber_form_wrap">
         <div class="search_password_form_con">
            <div class="search_password_form_con">
      
               <div class="user_name search_member_form_con_type_2">
                  <div class="title_size type_2">이름</div>
                  <div class="register_input_con">
                     <form:input path="userName" type="text" name="userName" id="userName" class="textbox_style_1"/>
                  </div>
               </div>
         
               <div class="user_id search_member_form_con_type_2">
                  <div class="title_size type_2"> 아이디</div>
                  <div class="register_input_con">
                     <form:input path="userId" type="text" name="userId" id="userId" class="textbox_style_1" placeholder="5~20자로 설정해주세요." maxlength="20"/>
                  </div>
               </div>
         
               <!-- 생일 선택하는 곳 width 20%안주면 너무 작게 설정됨 -->
               <div class="user_identity_num search_member_form_con_type_2">
                  <div class="title_size type_2">생일</div>
                  <div class="register_input_con">
                     <form:input path="userBirth" type="date" name="userBirth" id="userBirth" class="textbox_style_1 type_4" placeholder="yyyy-mm-dd" max="9999-12-31"  maxlength="10" data-ax5formatter="date"/>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <div class="col-12 col-center mw-1200 search_id_btn_wrap">
         <div class="btn_style_1_con">
            <input type="button" value="비밀번호 찾기" class="btn_style_1 type_2" onclick="findPw()">
         </div> 
      </div>
   </form:form>   
</div>



</body>
</html>