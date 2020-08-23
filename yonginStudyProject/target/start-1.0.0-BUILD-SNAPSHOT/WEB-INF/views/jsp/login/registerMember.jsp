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
<title>회원가입</title>
</head>
<body>

<div class="col-12 col-center mw-1200 search_memeber_title_wrap">

	<div class="circle_btn" onClick="self.close()"></div> 
	<div class="tc search_memeber_title_con">
		<div class="search_memeber_title">회원 가입</div>
	</div>
	
	
</div>

<div class="col-12 col-center mw-1200 search_memeber_form_wrap">
		<div class="register_member_form_con">
			
			<div class="user_name search_member_form_con">
				<div class="title_size type_2">이름</div>
				<input type="text" id="userNm" class="textbox_style_1">
			</div>
			 
			<div class="user_identity_num search_member_form_con">
				<div class="user_identity_title title_size type_2">주민번호</div>
				<input type="text" id="userNumber" class="textbox_style_1 type_2">
				<input type="text" id="userNumber" class="textbox_style_1 type_2">
			</div>
			
			<div class="user_gender search_member_form_con">
				<div class="title_size type_2">성별</div>
				<div class="radio_btn_con"><input type="radio" name="userGenderRadio" value="man" class="radio_btn"><span>남성</span></div>
				<div class="radio_btn_con"><input type="radio" name="userGenderRadio" value="woman" checked="checked" class="radio_btn"><span>여성</span></div>
			</div>
			
			<div class="user_phone search_member_form_con">
				<div class="user_phone_title title_size type_2">전화번호</div>
				<input type="text" id="userPhone" class="textbox_style_1">
			</div>
			
			
			<div class="user_id search_member_form_con">
				<div class="title_size type_2">아이디</div>
				<input type="text" id="userId" class="textbox_style_1">
				<input type="button" value="중복확인" id="checkIdBtn" class="btn_style_1">
			</div>
			
			<div class="user_pw search_member_form_con">
				<div class="title_size type_2">비밀번호</div>
				<input type="password" id="userPw" class="textbox_style_1">
			</div>
			
			<div class="user_pw_check search_member_form_con">
				<div class="title_size type_2">비밀번호 확인</div>
				<input type="password" id="userPwConfirm" class="textbox_style_1">
			</div>
			
			<div class="user_pw_hint search_member_form_con">
				<div class="title_size type_4">비밀번호 힌트</div>
				<select name="userPwHint" class="select_style_0" >
				    <option value="userPwHint1">자신의 보물 제 1호는?</option>
				    <option value="userPwHint2">태어난 곳은?</option>
				    <option value="userPwHint3">가장 좋아하는 음식은?</option>
				    <option value="userPwHint4">가장 좋아하는 색깔은?</option>
				</select>
			</div>
			
			<div class="user_pw_ans search_member_form_con">
				<div class="title_size type_2">비밀번호 힌트 답</div>
				<input type="text" id="userPwHintAwsr" class="textbox_style_1">
			</div>
			
	

	
				
		</div>
</div>

<div class="col-12 col-center mw-1200 register_memeber_btn_wrap">
	<div class="btn_style_1_con">
		<input type="submit" value="확인" class="btn_style_1">
	</div> 
</div>


  
</body>
</html>
