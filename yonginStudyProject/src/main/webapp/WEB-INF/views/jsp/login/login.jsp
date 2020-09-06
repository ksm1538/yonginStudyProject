<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>

<!DOCTYPE html>
<html>
<head>
<!-- 자원 불러오기(공통) -->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 -->
<script src="<c:url value="/resources/js/login.js" />"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> 
<div class="col-12 log_in_wrap">
	<div class="col-12 log_in_con">
		<div class="col-12 log_in_img"></div>
		<div class="col-12 log_in_dim"></div>
	
		<div class="col-12 col-center mw-1200 tc log_in_title_content">
			<div class="col-12 log_in_title">Studying Matching</div>
			<div class="col-12 log_in_subtitle"> If you want to go far, go together.</div>
			<hr class="col-center log_in_line">
		</div>
		
		<form name="loginForm" method="post" action="/login.json">
			<div class="col-3 mw-1200 col-center tc log_in_form_content">
				<font color="red">${msg}</font>
				<div class="id_form"><input type="text" name="userId" class="textbox_style_0" placeholder="아이디를 입력하세요"></div>
				<div class="pw_form"><input type="password" name="userPw" class="textbox_style_0" placeholder="비밀번호를 입력하세요"></div>
				<div class="log_in_btn"><input type="submit" value="로그인" class="bd btn_style_0" ></div>
				<div class="col-12">
					<ul class="member_function">
						<li><a class="a_tag" onclick="openRegisterForm();"><span>회원가입</span></a></li>
						<li><a class="a_tag" onclick="a1()"><span>아이디 찾기</span></a></li>
						<li><a class="a_tag" onclick="a2()"><span>비밀번호 찾기</span></a></li>
					</ul>
				</div> 
			</div>
		</form>
	</div>
</div>

</body>