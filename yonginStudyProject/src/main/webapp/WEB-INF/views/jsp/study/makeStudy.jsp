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

<div class="col-12 col-center mw-1200 make_study_title_wrap">

   <div class="circle_btn" onClick="self.close()"></div> 
   <div class="tc make_study_title_con">
      <div class="make_study_title">스터디 생성</div>
   </div> 
   
   
</div>

<div class="col-12 col-center mw-1200 make_study_form_wrap">
      <div class="make_study_form_con">
         
         <div class="study_name make_study_form">
            <div class="title_size type_2">스터디 이름</div>
            <input type="text" id="studyName" class="textbox_style_1">
         </div>
         
         <div class="creator_name make_study_form">
            <div class="title_size type_2">스터디장 이름</div>
            <input type="text" id="creatorName" class="textbox_style_1">
         </div>
         
         <div class="study_type make_study_form">
            <div class="title_size type_4">스터디 종류</div>
            <select name="studyType" class="select_style_0" >
                <option value="studyType1">토익</option>
                <option value="studyType2">토플</option>
                <option value="studyType3">기사 시험</option>
                <option value="studyType4">기능사 시험</option>
            </select>
         </div>
         
          <div class="study_area make_study_form">
            <div class="title_size type_4">스터디 지역</div>
            <select name="studyArea" class="select_style_0" >
                <option value="studyArea1">서울</option>
                <option value="studyArea2">경기</option>
                <option value="studyArea3">인천</option>
                <option value="studyArea4">강원</option>
            </select>
         </div>
         
         <div class="study_people make_study_form">
            <div class="title_size type_4">모집 인원</div>
            <select name="studyPeople" class="select_style_0" >
                <option value="studyPeople1">5</option>
                <option value="studyPeople2">10</option>
                <option value="studyPeople3">15</option>
                <option value="studyPeople4">20</option>
            </select>
         </div>
         
         <div class="study_text make_study_form">
            <div class="title_size type_2">설명</div>
            <!-- <input type="text" id="studyText" class="textbox_style_1"> -->
            <textarea name="studyText" cols="20" rows="10" placeholder="스터디에 대한 설명을 입력해주세요."></textarea>
         </div>      
      </div>
</div>

<div class="col-12 col-center mw-1200 make_study_btn_wrap">
   <div class="btn_style_1_con">
      <input type="button" value="완료" class="btn_style_1" onclick="registerMemberFunc()" >
   </div> 
</div>


  
</body>
</html>