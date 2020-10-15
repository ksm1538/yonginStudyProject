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
    
    <div class="col-12 study_chatting_wrap">
    	<div class="col-12 tc study_chatting_title_con">
    		<div class="tc study_chatting_title"><span class="study_chatting_name" id="studyNameSpan"></span></div>
    	</div>
    	
    	<div class="col-12 chatting_user_area_con" id="userArea">
    		<div class="tc chatting_user_area_title"><span id="userListSpan">대화 참여 인원</span></div>
    		<div class="chatting_user_list_area" id="userListArea"></div>
    	</div>
    	<div class="col-12 col-center chatting_area_con" id="chatArea">
    		<div class="chatting_message_area" id="chatMessageArea"></div>
    	</div> 
    	
        
    		  
    </div>
    
    
  	<div class="col-12 chatting_write_wrap">
  		<div class="chatting_wirte_text_box_con">
  			<input type="text" class="chatting_textbox" id="message" maxlength="7000" autofocus>
  			 <input type="button" class="chatting_btn" id="sendBtn" value="전송" onclick="send()">
  		</div>
  	</div>
  	
  	<div class="col-12 tc chatting_exit_btn_wrap">
  		<input type="button" class="chatting_exit_btn"id="exitBtn" value="나가기" onclick="disconnect()">
  	</div>
    
    
    
   
    

</body>
</html>  
