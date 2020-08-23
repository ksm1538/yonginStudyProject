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

<title>비밀번호 찾기</title>
</head>
<body>  

<div class="col-12 col-center mw-1200 search_memeber_title_wrap">

	<div class="circle_btn" onClick="self.close()"></div> 
	<div class="tc search_memeber_title_con">
		<div class="search_memeber_title">비밀번호 찾기</div>
	</div>
	
	
</div>

<div class="col-12 col-center mw-1200 search_memeber_form_wrap">
	<div class="search_password_form_con">
		<div class="search_password_form_con">
		
			<div class="user_name search_member_form_con">
				<div class="user_name title_size type_3">이름</div>
				<input type="text" class="textbox_style_1">
			</div>
			<div class="user_id search_member_form_con">
				<div class="user_id title_size type_3">ID</div>
				<input type="text" class="textbox_style_1">
			</div>
			
			<div class="user_pw_hint search_member_form_con">
				<div class="user_pw_hint title_size type_4">비밀번호 찾기 힌트 </div>
					<select class="select_style_0">
							    <option value="pwHint1">자신의 보물 제 1호는?</option>
							    <option value="pwHint2">태어난 곳은?</option>
							    <option value="pwHint3">가장 좋아하는 음식은?</option>
							    <option value="pwHint4">가장 좋아하는 색깔은?</option>
					</select>
			
			</div>
			<div class="user_pw_ans search_member_form_con">
				<div class="user_password_answer title_size type_3">비밀번호 찾기 답</div>
				<input type="text" class="textbox_style_1">
			
			</div>
		</div>
	</div>
</div>

<div class="col-12 col-center mw-1200 search_id_btn_wrap">
	<div class="btn_style_1_con">
		<input type="submit" value="비밀번호 찾기" class="btn_style_1 type_2">
	</div> 
</div>

  
</body>
</html>