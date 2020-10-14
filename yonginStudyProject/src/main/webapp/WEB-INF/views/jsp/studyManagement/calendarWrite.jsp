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
<%-- <jsp:include page="../common/header.jsp"></jsp:include> --%>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/calendarWrite.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
 	<div class="col-12 col-center mw-1200 write_schedule_wrap">
 		<div class="col-12 col-center mw-1200 write_schedule_con">
 			<div class="circle_btn" onClick="closeWriteModal()"></div> 
			<div class="tc write_schedule_title"><span>스터디 일정 작성</span></div>
 		</div>
 	</div>
 	
 	<div class="col-12 col-center mw-1200 write_schedule_form_wrap">
 		<div class="col-12 col-center mw-1200 write_schedule_form_con">
 		
 			<div class="write_schedule_content">
 			   <div class="wirte_schedule_content_box">
 			   		<div class="write_schedule_content_title start_yym">일정제목</div>
 					<input type="text" id="" name ="" class="textbox_style_1" onkeyup="" >
 			   </div>
 			   <div class="wirte_schedule_content_box">
 			   		<div class="write_schedule_content_title start_yym">일정분류</div>
 					<select name="studyTopic" id="studyTopic" class="select_style_0" >
            			<option value="noSelect" ><c:out value='선택 안함'/>
						<c:forEach var="result" items="${studyTopicArray}" varStatus="status">
							<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
						</c:forEach>
           			</select>
 			   </div>
 			</div>
 			
 			<!--스터디 / 작성자는 작성버튼 누르면 자동으로 스터디 이름이랑 로그인 계정 이름 채워지게 부탁-->
 			<div class="write_schedule_content">
 			   <div class="wirte_schedule_content_box">
 			   		<div class="write_schedule_content_title start_yym">스터디명</div>
 					<input type="text" id="" name ="" class="textbox_style_1" onkeyup="" >
 			   </div>
 			   <div class="wirte_schedule_content_box">
 			   		<div class="write_schedule_content_title start_yym">작성자명</div>
 					<input type="text" id="" name ="" class="textbox_style_1" onkeyup="" >
 			   </div>
 			</div>
 			
 			
 			<div class="write_schedule_content">
 			   <div class="wirte_schedule_content_box">
 			   		<div class="write_schedule_content_title start_yym">시작년월</div>
 					<input type="text" id="" name ="" class="textbox_style_1" onkeyup="" placeholder="ex) 20201014">
 			   </div>
 			   <div class="wirte_schedule_content_box">
 			   		<div class="write_schedule_content_title start_yym">시작시간</div>
 					<input type="text" id="" name ="" class="textbox_style_1" onkeyup="" placeholder="ex) 0100">
 			   </div>
 			</div>
 			
 			<div class="write_schedule_content">
 			   <div class="wirte_schedule_content_box">
 			   		<div class="write_schedule_content_title start_yym">종료년월</div>
 					<input type="text" id="" name ="" class="textbox_style_1" onkeyup="" placeholder="ex) 20201015">
 			   </div>
 			   <div class="wirte_schedule_content_box">
 			   		<div class="write_schedule_content_title start_yym">종료시간</div>
 					<input type="text" id="" name ="" class="textbox_style_1" onkeyup="" placeholder="ex) 0300">
 			   </div>
 			</div>
 		</div>
 	</div>
 	
 	
 	<div class="col-12 col-center mw-1200 write_schedule_summernote_wrap">
 		<div class="col-center mw-1200 write_schedule_summernote_con">
 			<textarea name="scheduleWriteDesc" id="scheduleWriteDesc"></textarea>
 		</div>
 	</div>
 	
 	<div class="col-12 col-center mw-1200 write_schedule_btn_wrap">
 		<div class="col-12 col-center mw-1200 write_schedule_btn_con">
 			<input type="button" value="작성하기" class="btn_style_1" onclick="" >
 			<input type="button" value="닫기" class="btn_style_1" onclick="closeWriteModal()" >
 		</div>
 	</div>
 
</body>
</html>