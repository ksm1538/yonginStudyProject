<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>
    
<!DOCTYPE html> 
<html>
<head>
<!---- css, js 설정(시작) ----> 
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<%-- <jsp:include page="../common/header.jsp"></jsp:include> --%>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/study/studyApplyPop.js"></script>

<!---- css, js 설정(끝) ----> 

<meta charset="UTF-8">
<title>스터디 신청서</title>
</head>
<body>

<div class="circle_btn" onClick="self.close()"></div> 

<div class="study_apply_wrap">
	
	<div class="study_apply_con tc">
		<div class="study_apply_main_title content_title"><span>스터디 신청서 </span></div>
		
		<div class="study_apply_sub_title "><span>신청 사유 </span></div>
		
	<div class="textarea_style_con tc">
		<textarea class="textarea_style" cols="100" rows="10"></textarea>
	</div>
		
	<div class="btn_style_1_con">
		<input type="button" value="확인" class="btn_style_1" onclick="()" >
	</div> 
		
		
	</div>
</div>
 
</body>
</html>
