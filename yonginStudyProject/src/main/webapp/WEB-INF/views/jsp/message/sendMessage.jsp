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
<!--<script src="<c:url value="/resources/util/jquery.js" />"></script>-->

<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/jquery-ui/jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/ax5ui-dialog-master/dist/ax5dialog.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/ax5ui-mask-master/dist/ax5mask.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/ax5ui-toast-master/dist/ax5toast.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">



<script src="<c:url value="/resources/util/jquery-ui/jquery-1.12.4.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5core-master/dist/ax5core.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5ui-dialog-master/dist/ax5dialog.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5ui-mask-master/dist/ax5mask.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5ui-toast-master/dist/ax5toast.min.js" />"></script>
<script src="<c:url value="/resources/js/constant.js" />"></script>

<script src="<c:url value="/resources/js/sendMessage.js" />"></script>
<!-- 자기가 만든 js파일은 가장 밑으로. 순서 잘 지키고 -->
<!-- css, js 설정(끝) -->

<!DOCTYPE html>
<html>
<head>
<title>쪽지 보내기</title>
</head>
<body>

<div class="col-12 col-center mw-1200 make_study_title_wrap">
	<div class="circle_btn" onClick="self.close()"></div> 
   	<div class="tc make_study_title_con">
      <div class="make_study_title">쪽지 보내기</div>
   	</div> 
</div>

<div class="col-12 col-center mw-1200 make_study_form_wrap">
      <div class="make_study_form_con">
         
         <div class="study_name make_study_form">
            <div class="title_size type_2">받는 사람</div>
            <input type="text" id="userCodeTo" name ="userCodeTo" class="textbox_style_1">
         </div>
         
         <div class="creator_name make_study_form">
            <div class="title_size type_2">제목</div>
            <input type="text" id="messageTitle" name="messageTitle" class="textbox_style_1">
         </div>
          <div class="study_text make_study_form">
            <div class="title_size type_2">내용</div>
            <input type="text" name="messageDesc" id="messageDesc" class="textbox_style_1">
            <!--<textarea name="studyDesc" id="studyDesc" cols="20" rows="10" placeholder="스터디에 대한 설명을 입력해주세요."></textarea> -->
         </div>      
      </div>
</div>

<div class="col-12 col-center mw-1200 make_study_btn_wrap">
   <div class="btn_style_1_con">
      <input type="button" value="보내기" class="btn_style_1" onclick="sendMessage()" >
   </div> 
</div>


  
</body>
</html>