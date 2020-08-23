<!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 한글 설정(끝) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>

<!-- css, js 설정(시작) -->
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/test.js" />"></script>
<!-- css, js 설정(끝) -->

<!DOCTYPE html>
<html>
<body> 
<div class="col-12 point_bg0 header_wrap">
	<div class="col-12 col-center mw-1200 header_con">
			<div class="header_menu_con">
				<ul class="header_menu">
					<li><a>HOME</a></li>
					<li><a>스터디목록</a></li>
					<li><a>공지사항</a></li>
					<li><a>마이페이지</a></li>
				</ul>
			</div>
			
			<div class="header_member_con">
				<div class="header_user"><span class="user_id">용인대학교</span>님 환영합니다.</div>
				<div class="logout">로그아웃</div>
			</div>
	</div>
</div>

</body>
</html>