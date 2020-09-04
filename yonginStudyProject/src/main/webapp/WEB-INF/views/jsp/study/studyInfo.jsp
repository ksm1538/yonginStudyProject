<!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 한글 설정(끝) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>

<!-- css, js 설정(시작) -->
<script src="<c:url value="/resources/util/jquery.js" />"></script>

<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/registerMember.js" />"></script>
<!-- css, js 설정(끝) -->

<!DOCTYPE html>
<html>
<head>
<title>스터디 생성</title>
</head>
<body>

<div class="col-12 col-center mw-1200 study_info_title_wrap">

   <div class="circle_btn" onClick="self.close()"></div> 
   <div class="tc study_info_title_con">
      <div class="study_info_title">스터디 생성</div>
   </div> 
   
   
</div>

<div class="col-12 col-center mw-1200 study_info_form_wrap">
      <div class="make_study_form_con">
         
         <div class="study_name_info study_info_form">
            <div class="title_size type_2">스터디 이름</div>
         </div>
         
         <div class="creator_name_info study_info_form">
            <div class="title_size type_2">스터디장 이름</div>
         </div>
         
         <div class="study_type_info study_info_form">
            <div class="title_size type_4">스터디 종류</div>
         </div>
         
          <div class="study_area_info study_info_form">
            <div class="title_size type_4">스터디 지역</div>
         </div>
         
         <div class="study_people_info study_info_form">
            <div class="title_size type_4">모집 인원</div>
         </div>
         
         <div class="study_text_info make_study_form">
            <div class="title_size type_2">설명</div>
         </div>      
      </div>
</div>

<div class="col-12 col-center mw-1200 make_study_btn_wrap">
   <div class="btn_style_1_con">
      <input type="button" value="목록으로" class="btn_style_1" onclick="registerMemberFunc()" >
   </div> 
</div>


  
</body>
</html>