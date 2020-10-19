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
<title>YonginStudy</title>
</head>
<body>


	<div class="col-12 study_detail_pop_wrap">
		<div class="circle_btn" onClick="closeModal()"></div>
		<div class="col-12 col-center mw-1200 study_detail_pop_con">
			<div class="tc study_detail_pop_title"><span>스터디 상세정보</span></div>
		</div>
	</div>
	
	<div class="col-12 study_detail_pop_content_wrap">
		<div class="col-12 col-center mw-1200 study_detail_pop_content_con">
			 <form:form method="POST" modelAttribute="studyInfoVO" name="studyInfoDetailForm" id="studyInfoDetailForm" >
		 <div class="study_detail_pop_wrap_con" id="detailDiv">
			
				<form:input path="studyCode" type="hidden" name="studyCode" id="studyCode" data-ax-path="studyCode" class="textbox_style_1"/>
				
				<div class="study_detail_wrap">
					<div class="study_detail_con detail_name">
						<div class="name_title_size">스터디 이름</div>
						<div class="study_detail_input_con">
							<form:input path="studyName" type="text" name="studyName" id="studyName" data-ax-path="studyName" class="name_textbox_style_0"/>
						</div>
					</div>				
					<div class="study_detail_con detail_subject">
						<div class="name_title_size">스터디 과목</div>
						<div class="study_detail_input_con">
							<form:select path="studyTopic" name="studyTopic" id="studyTopic" data-ax-path="studyTopic" class="select_style_0" >
							    <c:forEach var="result" items="${studyTopicArray}" varStatus="status">
						          	<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
					    	     </c:forEach>
							</form:select>
						</div>
					</div>
					<div class="study_detail_con detail_leader">
						<div class="name_title_size">스터디 장</div>
						<div class="study_detail_input_con">
							<form:input path="studyRgstusId" type="text" name="studyRgstusId" id="studyRgstusId" data-ax-path="studyRgstusId" class="textbox_style_1"/>
						</div>
					</div>	
				
				
				
					<div class="study_detail_con detail_location">
						<div class="name_title_size">스터디 지역</div>
						<div class="study_detail_input_con">
						  <div class="study_detail_input_con">
							<form:input path="studyArea" type="text" name="studyArea" id="studyArea" data-ax-path="studyArea" class="textbox_style_1"/>
						  </div>
						</div>
					</div>
					</div>
				</div>
				
				
				<div class="tc detail_desc">
					<div class="title_size type_2">스터디 소개</div>
					<div class="study_detail_input_con">
						<form:textarea path="studyDesc" id="studyDesc" name="studyDesc" data-ax-path="studyDesc" style=""/>
					</div> 
				</div>
				
			</div>
				<div class="btn_style_1_con">
					<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
				</div> 
		 </form:form>
		</div>
	</div>
<%-- 
	<div class="col-12 study_detail_pop_wrap">
		 <div class="circle_btn" onClick="closeModal()"></div>
		 <div class="col-12 study_detail_pop_title_con_con">
		 	
		 </div> 
		 
		 
		 <form:form method="POST" modelAttribute="studyInfoVO" name="studyInfoDetailForm" id="studyInfoDetailForm" >
		 <div class="study_detail_pop_wrap_con" id="detailDiv">
			
				<form:input path="studyCode" type="hidden" name="studyCode" id="studyCode" data-ax-path="studyCode" class="textbox_style_1"/>
				
				<div class="study_detail_con detail_name">
					<div class="title_size type_2">스터디 이름</div>
					<div class="study_detail_input_con">
						<form:input path="studyName" type="text" name="studyName" id="studyName" data-ax-path="studyName" class="textbox_style_1"/>
					</div>
				</div>				
				<div class="study_detail_con detail_subject">
					<div class="title_size type_2">스터디 과목</div>
					<div class="study_detail_input_con">
						<form:select path="studyTopic" name="studyTopic" id="studyTopic" data-ax-path="studyTopic" class="select_style_0" >
						    <c:forEach var="result" items="${studyTopicArray}" varStatus="status">
					          	<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
					         </c:forEach>
						</form:select>
					</div>
				</div>
				<div class="study_detail_con detail_leader">
					<div class="title_size type_2">스터디 장</div>
					<div class="study_detail_input_con">
						<form:input path="studyRgstusId" type="text" name="studyRgstusId" id="studyRgstusId" data-ax-path="studyRgstusId" class="textbox_style_1"/>
					</div>
				</div>	
				
				
				
				<div class="study_detail_con detail_location">
					<div class="title_size type_2">스터디 지역</div>
					<div class="study_detail_input_con">
					  <div class="study_detail_input_con">
						<form:input path="studyArea" type="text" name="studyArea" id="studyArea" data-ax-path="studyArea" class="textbox_style_1"/>
					  </div>
					</div>
				</div>
				
				
				<div class="detail_desc">
					<div class="title_size type_2">스터디 소개</div>
					<div class="study_detail_input_con">
						<form:textarea path="studyDesc" id="studyDesc" name="studyDesc" data-ax-path="studyDesc" style=""/>
					</div> 
				</div>
				
			</div>
				<div class="btn_style_1_con">
					<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
				</div> 
		 </form:form>
		 
		 
	</div> --%>
</body>
</html>