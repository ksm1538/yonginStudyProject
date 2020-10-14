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

<script type="text/javascript" src="/resources/util/sockjs-client-master/dist/sockjs.min.js"></script>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/studyChatting.js"></script>
<style>
#chatArea {
    width: 400px; height: 100px; overflow-y: auto; border: 1px solid black;
}
</style>
<title>YonginStudy <%=request.getParameter("studyName")%> 대화방</title> 
</head>
<body>
	<!-- 사용자 id   -->
    <input type="hidden" id="userId" readonly="true" value=${user.userId } >
    <!-- 해당 스터디 정보를 가지는 form -->
	<form id="dataForm" name="dataForm">
		<input type="hidden" id="studyCode" name="studyCode" value="<%=request.getParameter("studyCode")%>">
		<input type="hidden" id="studyName" name="studyName" value="<%=request.getParameter("studyName")%>">
	</form>
    
    <h1><span id="studyNameSpan"></span></h1>
    <div id="chatArea"><div id="chatMessageArea"></div></div>
    <br/>
    <input type="text" id="message" autofocus>
    <input type="button" id="sendBtn" value="전송" onclick="send()">
    <input type="button" id="exitBtn" value="나가기" onclick="disconnect()">
</body>
</html>  
