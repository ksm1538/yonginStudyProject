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
<script type="text/javascript" src="/resources/js/common/replyOnReply.js"></script>

<!---- css, js 설정(끝) ----> 

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
       <div class="col-12 col-center mw-1200 moreStudy_wrap">
       		<div class="circle_btn" onClick="closeModal()"></div> 
        	<div class="col-12 moreStudy_list_wrap content_wrap">
         	   <div class="content_title"><span>댓글 쓰기</span></div>
         	   
	         	   작성자 ID <input type="text" class="textbox_style_1" value="${user.userId}">
	             <textarea id="replyText" class="form-control" style="width:100%; height:200px;" placeholder="댓글을 입력해주세요"></textarea>

				<div class="btn_style_1_con">
					<input type="button" value="완료" class="btn_style_1" onclick="replyOnReplyFunc()" >
					<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
				</div> 			   	
   		 	</div>
       </div>
</body>
</html> 
