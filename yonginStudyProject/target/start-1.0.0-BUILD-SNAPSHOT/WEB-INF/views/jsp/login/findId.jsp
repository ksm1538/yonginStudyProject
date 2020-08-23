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
<head>

<title>아이디 찾기</title>
</head>
<body>  

<div class="col-12 col-center mw-1200 search_memeber_title_wrap">

	<div class="circle_btn" onClick="self.close()"></div> 
	<div class="tc search_memeber_title_con">
		<div class="search_memeber_title">아이디 찾기</div>
	</div>
	
	
</div>

<div class="col-12 col-center mw-1200 search_memeber_form_wrap">
	<div class="search_id_form_con">
		<div class="user_name search_id_form_con">
			<div class="search_member_form_con">
				<div class="user_name title_size type_2">이름</div>
				<input type="text" class="textbox_style_1">
			</div>
			<div class="user_phone search_member_form_con">
				<div class="user_name title_size type_2">전화번호</div>
				<input type="text" class="textbox_style_1">
			
			</div>
			
			
			<div class="user_identity_num search_member_form_con">
				<div class="user_identity_title title_size type_2">주민번호</div>
				<input type="text" id="userNumber" class="textbox_style_1 type_2">
				<input type="text" id="userNumber" class="textbox_style_1 type_2">
			</div>
			
		</div>
	</div>
</div>

<div class="col-12 col-center mw-1200 search_id_btn_wrap">
	<div class="btn_style_1_con">
		<input type="submit" value="확인" class="btn_style_1">
	</div> 
</div>

  
</body>
</html>
