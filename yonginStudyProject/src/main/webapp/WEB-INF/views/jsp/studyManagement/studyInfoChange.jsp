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
<script type="text/javascript" src="/resources/js/studyManagement/studyInfoChange.js"></script>

<!-- 자기가 만든 js파일은 가장 밑으로. 순서 잘 지키고 -->
<!-- css, js 설정(끝) -->


<meta charset="UTF-8">
<title>YonginStudy - <%=request.getParameter("studyName")%></title>
</head>
<body>

<div class="col-12 col-center mw-1200 make_study_wrap">

   <div class="circle_btn" onclick="closeModal()"></div> 
   
   <div class="tc make_study_title_con">
      <div class="make_study_title">스터디 내용 수정</div>
   </div> 
   
 <form:form method="POST" modelAttribute="studyInfoVO" name="studyInfoChangeForm" id="studyInfoChangeForm">
   <div class="col-12 col-center mw-1200 make_study_form_wrap">
      <div class="make_study_form_con">
         
         <div class="study_name make_study_form">
            <div class="title_size type_2">스터디 이름</div>
            <div class="study_detail_input_con" id="studyNameDiv">
				<div class="study_info_change_name_wrap"><form:input path="studyName" type="text" name="studyName" id="studyName" data-ax-path="studyName" class="name_textbox_style_0"/></div>
			</div>
         </div>
         
         <div class="study_type make_study_form">
            <div class="title_size type_2">스터디 주제</div>
            <div class="study_detail_input_con" id="studyTopicDiv">
				<form:select path="studyTopic" name="studyTopic" id="studyTopic" data-ax-path="studyTopic" class="select_style_0" >
				    <c:forEach var="result" items="${studyTopicArray}" varStatus="status">
			          	<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
			         </c:forEach>
				</form:select>
			</div>
         </div>
         
          <div class="study_area make_study_form">
            <div class="title_size type_2">스터디 지역</div>
            <div class="study_detail_input_con" id="studyAreaDiv">
				<form:input path="studyArea" type="text" name="studyArea" id="studyArea" data-ax-path="studyArea" class="textbox_style_1"/>
			</div>
         </div>
         
         <div class="study_people make_study_form">
            <div class="title_size type_2">모집 인원</div>
            <div class="make_study_input_con">
            	  <select name="studyLimit" id="studyLimit" class="select_style_0" >
                	<option value="5">5명</option>
                	<option value="10">10명</option>
                	<option value="15">15명</option>
                	<option value="20">20명</option>
                	<option value="25">25명</option>
                	<option value="30">30명</option>
           		  </select>
            </div>
         </div>
         
         <div class="study_text make_study_form">
            <div class="title_size type_2">설명</div>
            <div class="study_detail_input_con">
				<form:textarea path="studyDesc" id="studyDesc" name="studyDesc" data-ax-path="studyDesc" style=""/>
			</div>
           
         </div>      
      </div>
	</div>
	
	<div class="col-12 col-center mw-1200 make_study_btn_wrap">
   		<div class="btn_style_1_con">
      		<input type="button" value="수정하기" class="btn_style_1 study_apply_finsh_btn" onclick="studyInfoChange()" >
      		<input type="button" value="취소" class="btn_style_1 study_apply_close_btn" onclick="closeModal()" >
   		</div> 
	</div> 
</form:form>
   
</div>
 
</body>
</html>