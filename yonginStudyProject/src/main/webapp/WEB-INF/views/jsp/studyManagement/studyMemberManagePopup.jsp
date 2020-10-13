<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>

<!DOCTYPE html>
<html>
<head>
<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<%-- <!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<jsp:include page="../studyManagement/studyHeader.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/studyMain.js"></script> --%>


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
			<div class="study_member_info_con">
				<div class="study_member_title"><span>이름</span></div>
				 <input type="text" name="boardTitle" id="boardTitle" class="textbox_style_1">
			</div>
			<div class="study_member_info_con">
				<div class="study_member_title"><span>아이디</span></div>
				<input type="text" name="boardTitle" id="boardTitle" class="textbox_style_1">
			</div>
			<div class="study_member_info_con">
				<div class="study_member_title"><span>이메일</span></div>
				<input type="text" name="boardTitle" id="boardTitle" class="textbox_style_1">
			</div>
			<div class="study_member_info_con">
				<div class="study_member_title"><span>권한</span></div>
				<input type="text" name="boardTitle" id="boardTitle" class="textbox_style_1">				
			</div>
			<div class="study_member_info_con type_2">
				<div class="study_member_title type_2"><span>권한변경</span></div>
				<input type="button" value="운영진변경" class="btn_style_1 change_admin" onclick="" >
				<input type="button" value="스터디원변경" class="btn_style_1 change_study" onclick="" >			
			</div>
	</div>
	
	<div class="col-12 col-center mw-1200 study_memeber_btn_con">		
			<input type="button" value="추방하기 " class="btn_style_1" onclick="" >
			<input type="button" value="닫기" class="btn_style_1" onclick="" >				
	</div>

	
	<!-- <div class="col-12 col-center mw-1200 study_memeber_detail_pop_wrap">
		<div class="study_memeber_detail_pop_con">
			<div class="circle_btn" onClick="closeModal()"></div> 
			<div class="tc study_memeber_detail_title"><span>스터디원 상세정보</span></div>
		</div>
		
		<div class="study_member_detail_info_con">
			<div class="study_member_info_box">
				<div class="study_member_title"><span>이름</span></div>
				 <input type="text" name="boardTitle" id="boardTitle" class="textbox_style_1">
			</div>
			<div class="study_member_info_box">
				<div class="study_member_title"><span>아이디</span></div>
				<input type="text" name="boardTitle" id="boardTitle" class="textbox_style_1">
			</div>
			<div class="study_member_info_box">
				<div class="study_member_title"><span>이메일</span></div>
				<input type="text" name="boardTitle" id="boardTitle" class="textbox_style_1">
			</div>
			<div class="study_member_info_box">
				<div class="study_member_title"><span>권한</span></div>
				<input type="text" name="boardTitle" id="boardTitle" class="textbox_style_1">				
			</div>
			<div class="study_member_info_box">
				<div class="study_member_title"><span>권한변경</span></div>
				<input type="button" value="운영진변경" class="btn_style_1" onclick="" >
				<input type="button" value="스터디원변경" class="btn_style_1" onclick="" >			
			</div>
		</div>
		
		<div class="study_memeber_btn_con">
		
			<input type="button" value="추방하기 " class="btn_style_1" onclick="" >
			<input type="button" value="닫기" class="btn_style_1" onclick="" >				
		</div>
	</div> -->
	



</body>
</html> 