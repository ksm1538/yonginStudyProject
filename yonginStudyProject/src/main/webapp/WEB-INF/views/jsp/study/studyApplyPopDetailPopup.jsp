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
<script type="text/javascript" src="/resources/js/study/studyApplyPopDetailPopup.js"></script>

<!---- css, js 설정(끝) ----> 

<meta charset="UTF-8">
<title>스터디 신청서</title>
</head>
<body>

 
 <div class="study_apply_wrap">
	<div class="circle_btn" onClick="closeModal()"></div> 

	<div class="study_apply_con tc">
		<form id="applicationFormDetailPopupForm" name="applicationFormDetailPopupForm">
		<div class="study_apply_main_title content_title"><span>스터디 신청서 </span></div>
		<div class="study_apply_title_con">
			<div class="study_apply_sub_title "><span>신청서 제목 </span></div>
			<input type="text" id="title" name="title" data-ax-path="title" class="textbox_style_1" placeholder="신청서 제목을 입력해주세요." maxlength="30">
		</div>
		
			
		<div class="study_apply_desc"><span>신청서 내용 </span></div>
		
		<div class="study_apply_desc_summer tc">
			<textarea id="applicationFormDesc" data-ax-path="applicationFormDesc"></textarea>
		</div>
		</form>
	<div id="readonlyDiv" class="btn_style_1_con">
		<input type="button" value="수정하기" class="btn_style_1 study_apply_finsh_btn" id="updateAFBtn" onclick="updateStudyApplicationForm()" >
		<input type="button" value="닫기" class="btn_style_1 study_apply_close_btn" onclick="closeModal()" >
	</div> 
	<div id="adminDiv" class="btn_style_1_con">
		<input type="button" value="승인" class="btn_style_1 study_apply_finsh_btn" id="approveBtn" onclick="approveStudyForm()" >
		<input type="button" value="거부" class="btn_style_1 study_apply_finsh_btn" id="rejectBtn" onclick="rejectStudyForm()" >
		<input type="button" value="닫기" class="btn_style_1 study_apply_close_btn" onclick="closeModal()" >
	</div> 
		
		
	</div>
</div>

</body>
</html>
