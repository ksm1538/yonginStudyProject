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
<!--<jsp:include page="../studyManagement/studyHeader.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/studyMemberManagePopup.js"></script>

<script>
var rgstusIdCode = '${user.userCode}';		// 세션에 있는 현재 접속한 유저의 코드 값을 가져옴

</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="col-12 col-center mw-1200 study_member_detail_pop_wrap">
		<div class="study_member_detail_pop_con">
			<div class="circle_btn" onClick="closeModal()"></div> 
			<div class="tc study_member_detail_title"><span>스터디원 상세정보</span></div>
		</div>
	</div>
	
	<div class="col-12 col-center mw-1200 study_member_detail_info_wrap">
		<form:form method="POST" modelAttribute="userInfoVO" name="studyMemberManageForm" id="studyMemberManageForm" >
			<div class="study_member_info_con">
				<div class="study_member_title"><span>이름</span></div>
				 <div class="study_detail_input_con">
						<form:input path="userName" type="text" name="userName" id="userName" data-ax-path="userName" class="textbox_style_1"/>
					</div>
			</div>
			<div class="study_member_info_con">
				<div class="study_member_title"><span>ID</span></div>
				<div class="study_detail_input_con">
						<form:input path="userId" type="text" name="userId" id="userId" data-ax-path="userId" class="textbox_style_1"/>
					</div>
			</div>
			<div class="study_member_info_con">
				<div class="study_member_title"><span>주소</span></div>
				<div class="study_detail_input_con">
						<form:input path="userAddress" type="text" name="userAddress" id="userAddress" data-ax-path="userAddress" class="textbox_style_1"/>
					</div>
			</div>
			<div class="study_member_info_con">
				<div class="study_member_title"><span>이메일</span></div>
				<div class="study_detail_input_con">
						<form:input path="userEmail" type="text" name="userEmail" id="userEmail" data-ax-path="userEmail" class="textbox_style_1"/>
					</div>
			</div>
			</form:form>
	
			<div class="col-12 col-center mw-1200 study_memeber_btn_con">		
					<form id="deportStudyMemeberForm" name="deportStudyMemeberForm" action="/studyManagement/deportStudyMember.do" method="POST">
   		  		 	<input type="hidden" id="studyCode">
      				<input type="submit" value="추방하기" class="btn_style_1 return_message_btn" id="deportStudyMemeberBtn">
      				</form>
					<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
					<form id="sendMessageForm" name="sendMessageForm" action="/studyManagement/writeMessageToSelectUserForm.do" method="POST">
   		  		 	<input type="hidden" id="userId">
      				<input type="submit" value="쪽지 보내기" class="btn_style_1 return_message_btn" id="sendMessageBtn">
      				</form>			
			</div>
		
		
	</div>

</body>
</html> 