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
<title>YonginStudy</title>

<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<jsp:include page="../message/messageSidemenu.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/message/sendMessage.js"></script>
	
</head>
<body>

	<div class="col-12 send_message_wrap" id="send_message_top">
		<div class="col-12 col-center mw-1200 send_message_con">
			<div class="send_message_title_con">
				<div class="send_message_title"><span>쪽지 보내기</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri"></div>
						<div class="question_desc">받는사람의 아이디를 입력하고 하고싶은 말을 쪽지로 보내세요. 쪽지 제목의 최대는 30글자 입니다.</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-12 col-center mw-1200 send_message_content_con">
			<div class="send_message_form_con">
				 <div class="title_size type_2">받는 사람 ID</div>
    	        <input type="text" id="userCodeTo" name ="userCodeTo" class="textbox_style_1">
			</div>
			
			<div class="send_message_form_con">
     	       	 <div class="title_size type_2">제목</div>
     	      	 <input type="text" id="messageTitle" name="messageTitle" class="textbox_style_1" maxlength="30">
     		</div>		
		</div>
		
		<div class="col-12 col-center mw-1200 send_message_summernote_con">
			<div class="summer_note col-12">
     		   	<textarea name="messageDesc" id="messageDesc"></textarea>
     		</div>
		</div>
		
		<div class="col-12 btn_style_1_con">
	      	<input type="button" value="보내기" class="btn_style_1" onclick="sendMessage()" >
  		</div> 
		
	</div>


  
</body>
</html>