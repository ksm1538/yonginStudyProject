<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<!------ JSP 설정(끝) ------>

<!-- css, js 설정(시작) -->
<!--<script src="<c:url value="/resources/util/jquery.js" />"></script>-->

<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 주소 API javascript 호출(주소 사용하는 곳만 추가) -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/studyMemberAdminChange.js"></script>

<!-- css, js 설정(끝) -->


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="col-12 col-center mw-1200 make_study_wrap">

   <div class="circle_btn" onclick="closeModal()"></div> 
   
   <div class="tc make_study_title_con">
      <div class="make_study_title">직위 변경</div>
   </div> 
   
 <form:form method="POST" modelAttribute="userInStudyVO" name="studyMemberAdminChangeForm" id="studyMemberAdminChangeForm">
   <div class="col-12 col-center mw-1200 make_study_form_wrap">
      <div class="make_study_form_con">
         
         <div class="study_name make_study_form">
            <div class="title_size type_2">스터디 이름</div>
            <div class="study_detail_input_con" id="studyNameDiv">
				<form:input path="studyName" type="text" name="studyName" id="studyName" data-ax-path="studyName" class="textbox_style_1"/>
			</div>
         </div>
         
         <div class="study_name make_study_form">
            <div class="title_size type_2">멤버 이름</div>
            <div class="study_detail_input_con" id="userNameDiv">
				<form:input path="userName" type="text" name="userName" id="userName" data-ax-path="userName" class="textbox_style_1"/>
			</div>
         </div>
         
         <div class="study_name make_study_form">
            <div class="title_size type_2">멤버 현재 직위</div>
            <div class="study_detail_input_con" id="studyAuthorityDiv">
				<form:select path="studyAuthority" name="studyAuthority" id="studyAuthority" data-ax-path="studyAuthority" class="select_style_0" >
				    <c:forEach var="result" items="${studyAuthorityArray}" varStatus="status">
			          	<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
			         </c:forEach>
				</form:select>
			</div>
         </div>
         
         <div class="study_people make_study_form">
            <div class="title_size type_2">멤버 변경 직위</div>
            <div class="make_study_input_con">
            	  <select name="afterStudyAuthority" id="afterStudyAuthority" class="select_style_0" >
                	<option value="10">스터디 장</option>
                	<option value="20">스터디 운영진</option>
                	<option value="30">스터디 멤버</option>
           		  </select>
            </div>
         </div>     
      </div>
	</div>
	
	<div class="col-12 col-center mw-1200 make_study_btn_wrap">
   		<div class="btn_style_1_con">
      		<input type="button" value="변경하기" class="btn_style_1 study_apply_finsh_btn" onclick="studyMemberAdminChange()" >
      		<input type="button" value="취소" class="btn_style_1 study_apply_close_btn" onclick="closeModal()" >
   		</div> 
	</div> 
</form:form>
   
</div>
 
</body>
</html>