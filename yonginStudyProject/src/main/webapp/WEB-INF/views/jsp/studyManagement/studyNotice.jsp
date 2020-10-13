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
<script type="text/javascript" src="/resources/js/studyManagement/studyNotice.js"></script>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-12 study_notice_wrap">
		<div class="col-12 col-center mw-1200 study_notice_con">
   			<div class="study_notice_title_con">
				<div class="study_notice_title"><span>공지 사항</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri type_2"></div>
						<div class="question_desc type_2">공지사항 입니다. 수시로 확인하여 차질이 없도록 하세요.</div>
					</div>
				</div>
			</div>
			
			<div class="col-12 col-center mw-1200 study_notice_search_con">
				<div class="study_notice_search_box get_person_color">
					<div class="study_notice_box_title"> ID</div>
					<input type="text" id="userCodeTo" name ="userCodeTo" class="textbox_style_1 color_bg" onkeyup="enterKeyEvent();">
				</div>
				
				<div class="study_notice_search_box get_search_btn_color">
					  <input type="button" value="검색" class="btn_style_1" onclick="searchMessageList()" >
				</div>
		    </div>
			
			
			<div class="col-12 col-center mw-1200 study_notice_grid_con "style="width: 100%;" >  
	  			<div data-ax5grid="studyNoticeListGrid" data-ax5grid-config="{}" class="color_grid" style="height:300px; padding-top:10px; padding-right:10px"></div>  
			</div>
			
			
			
		</div>
	</div>
	
	
</body>
</html>