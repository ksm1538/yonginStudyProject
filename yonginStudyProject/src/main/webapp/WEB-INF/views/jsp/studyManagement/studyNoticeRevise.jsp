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

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/studyNoticeRevise.js"></script>

<script>
var rgstusIdCode = '${user.userCode}';		// 세션에 있는 현재 접속한 유저의 코드 값을 가져옴

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div class="col-12 notice_revise_wrap">
		<div class="col-12 notice_revise_con">
			<div class="circle_btn" onClick="closeModal()"></div> 
			<div class="tc notice_revise_title"><span>공지사항 수정</span></div>
		</div>
	</div>
	<div class="col-12 notice_detail_pop_content_con">
		<form id="readForm" name="readForm" method="post" role="form">
		 	<input type="hidden" id="FILE_CODE" name="FILE_CODE" value=""> 
		 </form> 
		 
		<form name="reviseNoticeForm" id="reviseNoticeForm" method="POST" enctype="multipart/form-data">
		 <input type="hidden" id="fileCodeDel" name="fileCodeDel" value=""> 
		 <input type="hidden" id="boardCode" name="boardCode" value=""> 
		 
		 <div class="study_detail_pop_wrap_con" id="detailDiv">
				<div class="free_notice_revise_wrap">
				<div class="study_detail_con detail_name">
					<div class="name_title_size_1">제목</div>
					<div class="study_detail_input_con">
						<input type="text" name="boardTitle" id="boardTitle" data-ax-path="boardTitle" class="name_textbox_style_0"/>
					</div>
				</div>
				</div>				
				
				<div class="detail_desc">
					<div class="study_detail_input_con">
						<textarea id="boardDesc" name="boardDesc" data-ax-path="boardDesc"></textarea>
					</div> 
				</div>
				
				
				<div class="file_desc">
					<div class="title_size type_2">첨부 파일</div>
					<div id="fileListDiv">
					</div>
					
				</div>
				
				<div class="index_desc">					
			        <input type="button" value="파일 추가" class="btn_style_1" onclick="fn_addFile()">
					<div id="fileIndex"></div>
				</div>
				
				
				
			</div>
				<div class="btn_style_1_con">
					<input type="button" value="수정완료" class="btn_style_1" onclick="reviseStudyNoticeFunc()" >
					<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
				</div> 
		 </form>
	</div> 
</body>
</html>