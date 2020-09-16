<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!------ JSP 설정(끝) ------> 	

<!DOCTYPE html>
<html>
<head>
<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 필요X 생략 -->

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/login/findPwSelect.js"></script>
<script>
$(window).bind('beforeunload', function() {
	$.ajax({
	     type: "GET",
	     url : "/deleteSession.json",
	     async: false,
	}); 
}
</script>
<title>비밀번호 찾기</title>
</head>
<body>

<div class="col-12 find_pw_wrap">

	<div class="col-12 col-center mw-1200 search_memeber_title_wrap">
		<div class="circle_btn" onClick="self.close()"></div> 
		<div class="tc search_memeber_title_con">
			<div class="search_memeber_title">비밀번호 찾기</div>
		</div>
	</div>
	
	
	
	<div class="col-12 col-center mw-1200 search_memeber_form_wrap">
		<div class="search_password_form_con">
			<div class="search_password_form_con">
	
				<div class="user_name search_member_form_con_type_2">
					<div class="title_size type_2">1. 이메일로 임시 비밀번호 받기</div>
					<div class="register_input_con">
						가입 시 등록하신 이메일 주소로 임시 비밀번호를 받습니다. <br>
						<font color = 'blue'>${findPwEmail}</font>
						<input type="button" value="확인" class="btn_style_1 type_2" onclick="usingEmail()">
					</div>
				</div>
				
				<div class="user_name search_member_form_con_type_2">
					<div class="title_size type_2">2. 비밀번호 힌트를 이용해 비밀번호 갱신하기</div>
					<div class="register_input_con">
						가입 시 등록하신 비밀번호 힌트를 이용해 새로운 비밀번호로 갱신합니다. <br>
						<input type="button" value="확인" class="btn_style_1 type_2" onclick="usingHint()">
					</div>
				</div>
		
			</div>
		</div>
	</div>
</div>



</body>
</html>