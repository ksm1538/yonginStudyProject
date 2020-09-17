<!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<!-- 한글 설정(끝) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!------ JSP 설정(끝) ------>

<!-- css, js 설정(시작) -->
<!--<script src="<c:url value="/resources/util/jquery.js" />"></script>-->

<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 주소 API javascript 호출(주소 사용하는 곳만 추가) -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/study/makeStudy.js"></script>

<!-- 자기가 만든 js파일은 가장 밑으로. 순서 잘 지키고 -->
<!-- css, js 설정(끝) -->

<!DOCTYPE html>
<html>
<head>
<title>스터디 생성</title>
</head>
<body>

<div class="col-12 col-center mw-1200 make_study_wrap">

   <div class="circle_btn" onClick="self.close()"></div> 
   
   <div class="tc make_study_title_con">
      <div class="make_study_title">스터디 생성</div>
   </div> 
   
 <form:form method="POST" modelAttribute="studyInfoVO" name="studyMakeForm" id="studyMakeForm" action="/makeStudy.json">
   <div class="col-12 col-center mw-1200 make_study_form_wrap">
      <div class="make_study_form_con">
         
         <div class="study_name make_study_form">
            <div class="title_size type_2">스터디 이름</div>
            <div class="make_study_input_con">
            	<input type="text" id="studyName" name ="studyName" class="textbox_style_1" placeholder="5~30자로 설정해주세요.">
           		<i class="fa fa-times-circle" aria-hidden="true" id="nameYnIcon" style="margin-left:1%"></i>
          		<input type="button" value="중복확인" id="checkIdBtn" class="btn_style_1" onclick="studyNameCheckFunc()">
            </div>
         </div>
         
         <div class="study_type make_study_form">
            <div class="title_size type_2">스터디 종류</div>
            <div class="make_study_input_con">
            	<select name="studyTopic" id="studyTopic" class="select_style_0" >
					<c:forEach var="result" items="${studyTopic}" varStatus="status">
						<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
					</c:forEach>
           		</select>
            </div>
         </div>
         
          <div class="study_area make_study_form">
            <div class="title_size type_2">스터디 지역</div>
            <div class="register_input_con">
				<form:input path="studyArea" type="text" name="studyArea" id="studyArea" class="textbox_style_1" readonly="true" placeholder="주소 검색을 클릭하세요."/>
			</div>
			<input type="button" value="주소 검색" class="btn_style_1" onclick="studyAddressPopup()" >
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
            <div class="make_study_input_con">
            	 <input type="text" name="studyDesc" id="studyDesc" class="textbox_style_1">
           		 <!--<textarea name="studyDesc" id="studyDesc" cols="20" rows="10" placeholder="스터디에 대한 설명을 입력해주세요."></textarea> -->
            </div>
           
         </div>      
      </div>
	</div>
	
	<div class="col-12 col-center mw-1200 make_study_btn_wrap">
   		<div class="btn_style_1_con">
      		<input type="button" value="완료" class="btn_style_1" onclick="makeStudyFunc()" >
   		</div> 
	</div> 
</form:form>
   
</div>
 
</body>
</html>