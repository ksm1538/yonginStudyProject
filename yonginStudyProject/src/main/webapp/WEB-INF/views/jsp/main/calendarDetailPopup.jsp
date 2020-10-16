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

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/main/calendarDetailPopup.js"></script>
<script>
</script>
<style>
body {
	min-width: 95%;
	max-height: 280px;
	overflow-x:hidden;
	overflow-y:hidden;
}
table {
	font-size: small;
}
p {
	margin: 0 0 5px;
}
button {
	margin-top:10px; 
	padding:7px 9px 11px 9px; 
	width: calc(50% - 1px); 
	background:none; 
	border:none;
}
</style>

<body>
<div id="typeColor" style="width:100%; height:10px;"></div>
<div style="height:240px; margin-left:5%; margin-right:5%">
  <p style="margin-top:10px;"><span id="title" style="font-size:2rem; font-weight:bold;"></span><i class="fa fa-close" style="float:right; font-size:1.7rem; cursor:pointer;" onclick="closePopup();"></i></p>
  <p><span id="calendarDt" style="font-size:1.3rem; color:grey;"></span></p>
  <p><span style="margin-right:10px;"><i class="fa fa-briefcase"></i></span><span id="calendarType" style="font-size:1.3rem;"></span></p>
  <p style="margin-top:10px;"><span style="margin-right:10px;"><i class="fa fa-group" style="font-size:1.5rem;"></i></span><span id=attendeesStudy style="font-size:1.3rem;"></span></p>
  <div style="overflow:auto; height:100px"><p><span id="calendarContent" style="font-size:1.5rem;"></span></p></div>
</div>

</body>