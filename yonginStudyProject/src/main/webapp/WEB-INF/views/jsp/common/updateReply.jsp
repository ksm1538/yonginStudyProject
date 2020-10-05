<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>
    
<!DOCTYPE html>
<html>
<head>
<!---- css, js 설정(시작) ----> 
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/common/updateReply.js"></script>

<!---- css, js 설정(끝) ----> 

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
           
       
	<div class="col-12 reply_function_wrap">
		<div class="col-12  reply_function_con">
			<div class="tc reply_function_title"><span>댓글수정</span></div>
		</div>		
	</div>
	<div class="col-12 reply_user_info_wrap">
				<div class="reply_user_info_title">작성자ID</div>
				<div class="reply_user_textbox"><input type="text" class="textbox_style_1" value="${user.userId}" readonly="true"></div>
	</div>
	<div class="col-12 replay_textarea_wrap">
		<textarea id="replyText" class="form-control"  placeholder="댓글을 입력해주세요"></textarea>
	</div>
	<div class="col-12 reply_btn_wrap">
		<input type="button" value="완료" class="btn_style_1" onclick="updateReplyFunc()" >
		<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
	</div>
	
	
</body>
</html> 
