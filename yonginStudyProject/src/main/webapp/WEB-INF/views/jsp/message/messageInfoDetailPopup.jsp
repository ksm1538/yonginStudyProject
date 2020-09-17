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
<title></title>

<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/message/messageInfoDetailPopup.js"></script>
	
<script>
var rgstusIdCode = '${user.userCode}';		// 세션에 있는 현재 접속한 유저의 코드 값을 가져옴
</script>
</head>
<body>


	
	<div class="col-8 mw-1200 send_message_wrap">
		 <div class="tc content_title"><span>쪽지 상세 정보</span></div>
		 
		 <div class="send_message_con">
		 	<form id="messageInfoDetailForm" name="messageInfoDetailForm">
		 	  <div class="send_message_form">
    	        <div class="title_size type_2">보낸 사람</div>
    	        <input type="text" id="userCodeFrom" name ="userCodeFrom" data-ax-path="userCodeFrom" class="textbox_style_1" readonly="true">
    	        
    	        <div class="title_size type_3">받는 사람</div>
    	        <input type="text" id="userCodeTo" name ="userCodeTo" data-ax-path="userCodeTo" class="textbox_style_1"  readonly="true">
    	      </div>
    	      
    	       <div class="send_message_form">
     	       	 <div class="title_size type_2">제목</div>
     	      	 <input type="text" id="messageTitle" name="messageTitle" data-ax-path="messageTitle" class="textbox_style_1"  readonly="true">
     	      	 
     	      	 <div class="title_size type_3">보낸 시각</div>
     	      	 <input type="text" id="messageTime" name="messageTime" data-ax-path="messageTime" class="textbox_style_1"  readonly="true">
     		   </div>
     		   
     		   
     		   <div class="tc summer_note_con"><!-- 내용이라고 안넣는게 더 이쁠거같아서 뺌 -->
     		   		<div class="summer_note" style="width:100%; margin-left:0%">
     		   			<textarea name="messageDesc" id="messageDesc" data-ax-path="messageDesc" ></textarea>
     		   		</div>
     		   		
     		   </div>
    	        
    	       </form>	         	 
		 </div>

     	 
     	 <div class="btn_style_1_con">
	      	<input type="button" value="답장 쓰기" class="btn_style_1" onclick="replyMessage()" >
	      	<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
  		 </div> 
     	   
     	   
		 
	</div>
  
</body>
</html>