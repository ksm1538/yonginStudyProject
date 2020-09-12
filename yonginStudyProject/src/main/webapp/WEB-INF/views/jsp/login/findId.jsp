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
<script type="text/javascript" src="/resources/js/login/findId.js"></script>

<title>아이디 찾기</title>
</head>
<body>



<div class="col-12 find_id_wrap">

	
<div class="col-12 col-center mw-1200 search_memeber_title_wrap">

	<div class="circle_btn" onClick="self.close()"></div> 
	<div class="tc search_memeber_title_con">
		<div class="search_memeber_title">아이디 찾기</div>
	</div>

</div>

<form:form method="POST" modelAttribute="userInfoVO" name="findIdForm" id="findIdForm" action="/findId.json">
		<div class="col-12 col-center mw-1200 search_memeber_form_wrap">
			<div class="search_id_form_con">
				<div class="user_name search_id_form_con">
			
			
					<div class="user_name search_member_form_con_type_2">
						<div class="title_size type_2">이름</div>
						<div class="register_input_con">
							<input type="text" name="userName" id="userName" class="textbox_style_1"/>
						</div>
					</div>
			
					<div class="user_identity_num search_member_form_con_type_2">
						<div class="title_size type_2">생일</div>
						<div class="register_input_con">
							<input type="date" name="userBirth" id="userBirth" class="textbox_style_1 type_4" />
						</div>
					</div>
			
					<div class="user_name search_member_form_con_type_2">
						<div class="title_size type_2">이메일</div>
						<div class="register_input_con">
							<input type="text" name="userEmail" id="userEmail" class="textbox_style_1" placeholder="도메인까지 입력해주세요."/>
						</div>
					</div>
			
				</div>
			</div>
		</div>

		<div class="col-12 col-center mw-1200 search_id_btn_wrap">
			<div class="btn_style_1_con">
				<input type="button" value="확인" class="btn_style_1" onclick="findId()">
			</div> 
		</div>
</form:form>
  
</div>

</body>
</html>
