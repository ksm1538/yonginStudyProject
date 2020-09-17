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


	<div class="col-12 mw-1200 col-center message_detail_wrap">
		<div class="circle_btn type_2" onClick="closeModal()"></div> 
		<div class="tc content_title"><span>쪽지 상세 정보</span></div>
			
				<div class="message_detail_con">
					<form id="messageInfoDetailForm" name="messageInfoDetailForm">
					
					
					<div class="message_desc_con">
						<div class="message_title_con">
							<div class="title_size">제목</div>
     	      				<input type="text" id="messageTitle" name="messageTitle" data-ax-path="messageTitle" class="textbox_style_1"  readonly="true">
						</div>
						<div class="message_time_con">
							 <div class="title_size">보낸 시각</div>
     	      				 <input type="text" id="messageTime" name="messageTime" data-ax-path="messageTime" class="textbox_style_1"  readonly="true">
						</div>
					</div>
					<div class="message_user_con">
						<div class="send_user_con">
						 	<div class="title_size">보낸 사람</div>
						 	<input type="text" id="userCodeFrom" name ="userCodeFrom" data-ax-path="userCodeFrom" class="textbox_style_1" readonly="true">
						</div>
						<div class="get_user_con">
							 <div class="title_size">받는 사람</div>
							 <input type="text" id="userCodeTo" name ="userCodeTo" data-ax-path="userCodeTo" class="textbox_style_1"  readonly="true">
						</div>
					</div>
					
					<div class="tc summer_note_con">
     		   			<div class="summer_note" style="width:100%; margin-left:0%">
     		   				<textarea name="messageDesc" id="messageDesc" data-ax-path="messageDesc" ></textarea>
     		   			</div>
     		  		</div>	
     		  		</form>
     		  		 <div class="message_detail_btn_con">
     		  		 	<form id="replyMessageForm" name="replyMessageForm" action="/message/replyMessageForm.do" method="POST">
     		  		 	<input type="hidden" id="userCodeFrom">
	      				<input type="submit" value="답장 쓰기" class="btn_style_1 return_message_btn" id="replyMessageBtn">
	      				</form>
	      				<input type="button" value="닫기" class="btn_style_1 close_message_btn" onclick="closeModal()">
  					 </div> 
     		  		
     		  							
				</div>
			
	</div>
</body>
</html>