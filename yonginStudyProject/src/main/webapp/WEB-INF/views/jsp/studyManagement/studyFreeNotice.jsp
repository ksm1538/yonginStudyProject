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

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<jsp:include page="../studyManagement/studyHeader.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/studyFreeNotice.js"></script>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-12 study_free_notice_wrap">
		<div class="col-12 col-center mw-1200 study_free_notice_con">
   			<div class="study_free_notice_title_con">
				<div class="study_free_notice_title"><span>자유게시판</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri type_2"></div>
						<div class="question_desc type_2">자유게시판 입니다. 자유롭게 질문하세요</div>
					</div>
				</div>
			</div>
			
		<div class="col-12 col-center mw-1200 study_free_notice_grid_con "style="width: 100%;" >  
	  		<div data-ax5grid="studyFreeNoticeListGrid" data-ax5grid-config="{}" class="color_grid" style="height:300px; padding-top:10px; padding-right:10px"></div>  
		</div>
		
		<div class="tc study_free_notice_btn_con">
			<input type="button" value="작성하기" class="btn_style_1">
		</div>
			
			
			
		</div>
	</div>
	
	
</body>
</html>