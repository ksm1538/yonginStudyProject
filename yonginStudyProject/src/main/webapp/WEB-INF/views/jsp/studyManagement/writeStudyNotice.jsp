<!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<!-- 한글 설정(끝) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!------ JSP 설정(끝) ------>


<!DOCTYPE html> 
<html>
<head>

<!-- css, js 설정(시작) -->
<!--<script src="<c:url value="/resources/util/jquery.js" />"></script>-->

<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 주소 API javascript 호출(주소 사용하는 곳만 추가) -->
<!-- <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> -->

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/writeStudyNotice.js"></script>

<!-- 자기가 만든 js파일은 가장 밑으로. 순서 잘 지키고 -->
<!-- css, js 설정(끝) -->

<title>Insert title here</title>
</head>
<body>


<div class="col-12 col-center mw-1200 make_study_wrap">

   <div class="circle_btn" onClick="closeWriteModal()"></div> 
   
   <div class="tc make_study_title_con">
      <div class="make_study_title">공지사항 작성</div>
   </div> 
   
   <form name="writeSysNoticeForm" id="writeSysNoticeForm" method="POST" enctype="multipart/form-data">
   <div class="col-12 col-center mw-1200 make_study_form_wrap">
      <div class="make_study_form_con">
      	<div class="study_text make_study_form">
           <div class="title_size type_2">제목</div>
           <div class="make_study_input_con">
           	 <div class="write_notice_study_manage_wrap"><input type="text" name="" id="" class="name_textbox_style_0"></div>
           </div>
           
        </div> 
      
       <div class="study_text make_study_form">
          <div class="title_size type_2">설명</div>
          <div class="make_study_input_con">
         		 <textarea name="noticeWriteSummernote" id="noticeWriteSummernote" placeholder="공지사항을 입력해주세요."></textarea> 
          </div>
         
       </div>    
       
       <div class="study_text">
       	  <div class="add_file_con">
       	  	<div class="title_size type_2">첨부파일</div>
          	<input type="button" value="파일 추가" class="btn_style_1" onclick="fn_addFile()">
       	  </div>
	          
          <div id="fileIndex"></div>
       </div>  
      
      </div>
	</div>
	
	<div class="col-12 col-center mw-1200 write_notice_btn_wrap">
   		<div class="btn_style_1_con">
      		<input type="button" value="작성하기" class="btn_style_1" onclick="">
      		<input type="button" value="닫기" class="btn_style_1" onclick="closeWriteModal()">
   		</div> 
	</div> 
	</form>
   
</div>

</body>
</html>