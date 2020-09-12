<!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 한글 설정(끝) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>



<!DOCTYPE html>
<html>
<head>
<title>쪽지 보내기</title>

<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 필요X 생략 -->

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/message/sendmessage.js"></script>
	
</head>
<body>

<div class="col-12 send_message_wrap">

	
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
	
</div>


  
</body>
</html>