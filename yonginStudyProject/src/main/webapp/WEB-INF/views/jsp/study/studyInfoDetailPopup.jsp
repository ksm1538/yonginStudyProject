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

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<%-- <jsp:include page="../common/header.jsp"></jsp:include> --%>

<!-- 주소 API javascript 호출(주소 사용하는 곳만 추가) -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/study/studyInfoDetailPopup.js"></script>

<script>
var rgstusIdCode = '${user.userCode}';		// 세션에 있는 현재 접속한 유저의 코드 값을 가져옴

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" modelAttribute="studyInfoVO" name="studyInfoDetailForm" id="studyInfoDetailForm" >
	<div class="col-12 col-center mw-1200 search_memeber_form_wrap">
			<div class="register_member_form_con" id="detailDiv">
			
				<form:input path="studyCode" type="hidden" name="studyCode" id="studyCode" data-ax-path="studyCode" class="textbox_style_1"/>
					
				<div class="user_id search_member_form_con_type_2">
					<div class="title_size type_2">스터디 이름</div>
					<div class="register_input_con">
						<form:input path="studyName" type="text" name="studyName" id="studyName" data-ax-path="studyName" class="textbox_style_1"/>
					</div>
				</div>
				
				<div class="user_pw search_member_form_con_type_2">
					<div class="title_size type_2">스터디 주제</div>
					<div class="register_input_con">
						<form:select path="studyTopic" name="studyTopic" id="studyTopic" data-ax-path="studyTopic" class="select_style_0" >
						    <c:forEach var="result" items="${studyTopicArray}" varStatus="status">
					          	<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
					         </c:forEach>
						</form:select>
					</div>
				</div>
				
				<div class="user_pw_check search_member_form_con_type_2">
					<div class="title_size type_2">스터디 내용</div>
					<div class="register_input_con">
						<form:textarea path="studyDesc" id="studyDesc" name="studyDesc" data-ax-path="studyDesc" style="height:200px; display: inline-block; z-index:0"/>
					</div>
				</div>
				
				<div class="user_pw_hint search_member_form_con_type_2">
					<div class="title_size type_2">스터디 방장</div>
					<div class="register_input_con">
						<form:input path="studyRgstusId" type="text" name="studyRgstusId" id="studyRgstusId" data-ax-path="studyRgstusId" class="textbox_style_1"/>
					</div>
				</div>
				
				<div class="user_pw_ans search_member_form_con_type_2">
					<div class="title_size type_2">스터디 지역</div>
					<div class="register_input_con">
						<form:input path="studyArea" type="text" name="studyArea" id="studyArea" data-ax-path="studyArea" class="textbox_style_1"/>
						<input type="button" value="주소 검색" class="btn_style_1" onclick="addressPopup()" >
					</div>
				</div>
				
			</div>
	</div>
	</form:form>
	<div class="col-12 col-center mw-1200 register_memeber_btn_wrap">
		<div class="btn_style_1_con">
			<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
		</div> 
	</div>
</body>
</html>