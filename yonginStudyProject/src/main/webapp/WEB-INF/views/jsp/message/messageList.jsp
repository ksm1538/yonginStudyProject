<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>
    
<!DOCTYPE html>
<html>
<head>
<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<jsp:include page="../common/header.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/message/messageList.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div class="col-12 col-center mw-1200 moreStudy_wrap">
        <div class="col-12 message_list_wrap content_wrap">
            <div class="content_title"><span>쪽지</span></div>
            
            <div style="width: 100%;" >  
	  			<div data-ax5grid="messageListGrid" data-ax5grid-config="{}" style="height:600px; padding-top:10px; padding-right:10px"></div>  
			</div> 
			
			<div class="message_btn">
				<input type="button" value="쪽지  보내기" class="btn_style_1 type_3" onclick="openSendMessageForm();">
				<input type="button" value="쪽지 삭제하기" class="btn_style_1 type_3" onclick="deleteMessage();">
			</div>
		
        </div>
       </div>
	
</body>
</html> 