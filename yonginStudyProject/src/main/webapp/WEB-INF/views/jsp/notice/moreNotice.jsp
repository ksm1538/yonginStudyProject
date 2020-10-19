<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>
    
<!DOCTYPE html>
<html>
<head>
<!---- css, js 설정(시작) ----> 
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<jsp:include page="../common/header.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/notice/moreNotice.js"></script>

<!---- css, js 설정(끝) ----> 

<meta charset="UTF-8">
<title>YonginStudy</title>
</head>
<body>

	<div class="col-12 head_notice_wrap">
		<div class="col-12 col-center mw-1200 head_notice_con">
			 <div class="head_notice_title"><span>공지사항</span></div>
			 
			 <div class="head_notice_content_con">
			 	<div class="head_notice_content_box">
			 		<div class="head_notice_search_title">작성자 ID</div>
			 		<input type="text" id="boardRgstusId" name ="boardRgstusId" class="textbox_style_1 color_bg" onkeyup="enterKeyEvent();">
			 	</div>
			 	
			 	<div class="head_notice_content_box">
			 		<div class="head_notice_search_title">제목</div>
			 		<input type="text" id="boardTitle" name="boardTitle" class="textbox_style_1 color_bg" maxlength="30" onkeyup="enterKeyEvent();">
			 	</div>
			 	
			 	 <input type="button" value="검색" class="btn_style_1" onclick="searchNoticeList()"  >
			 	
			 </div>
			 <div class="notice_list_grid_con" style="width: 100%;" >
	  				<div data-ax5grid="noticeListPlusGrid" class="color_grid" data-ax5grid-config="{}" style="height:610px; padding-top:10px; padding-right:10px"></div>  
			 </div> 
			 
			 <c:if test="${user.userIsAdmin == 'Y'}">
            	  <div class="apply_btn tc">
					<input type="button" value="글 작성하기" class="btn_style_1 type_4 notice_write_btn" onclick="openWriteNotice();">
					<input type="button" value="삭제하기" class="btn_style_1 type_4 notice_delete_btn" onclick="deleteSystemNotice();">
				  </div>
           	 </c:if>
           	 
          
			 
		</div>
	</div>
	
	


  
       
       
       
</body>
</html> 
