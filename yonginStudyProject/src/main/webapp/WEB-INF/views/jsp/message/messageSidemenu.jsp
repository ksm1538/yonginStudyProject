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
<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<jsp:include page="../common/header.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/message/messageSidemenu.js"></script>


<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
      
      <div class="col-12 col-center mw-1200 message_page_list_wrap">
         <div class="col-3 message_page_list_con" style="height:100vh">
            <div class="message_list_title">쪽지함</div>
            <ul class="message_list">
               <li><a id="listMove1" onclick="openSendMessageForm();">쪽지보내기</a></li>
               <li><a id="listMove3" onclick="openMessageForm();">받은쪽지함</a></li>
               <li><a id="listMove2" onclick="openSendMessageListForm();">보낸쪽지함</a></li>
                
            </ul>
         </div> 
      </div>
      
      
</body>
</html>