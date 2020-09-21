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
<jsp:include page="../message/messageSidemenu.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/message/messageList.js"></script>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
        <div class="col-8 mw-1200 message_list_wrap">
            <div class="content_title"><span>받은쪽지함</span></div>
            
           	보낸사람 ID <input type="text" id="userCodeFrom" name ="userCodeFrom" class="textbox_style_1" onkeyup="enterKeyEvent();">
                           제목 <input type="text" id="messageTitle" name="messageTitle" class="textbox_style_1" maxlength="30" onkeyup="enterKeyEvent();">
            <input type="button" value="검색" class="btn_style_1" onclick="searchMessageList()"  >
            	
            
            <div style="width: 80%;" >  
	  			<div data-ax5grid="messageListGrid" data-ax5grid-config="{}" style="height:610px; padding-top:10px; padding-right:10px"></div>  
			</div> 
			
			<div class="message_btn">
				<input type="button" value="쪽지 삭제하기" class="btn_style_1 type_3" onclick="deleteMessage();">
			</div>
		
        </div>
	
</body>
</html> 