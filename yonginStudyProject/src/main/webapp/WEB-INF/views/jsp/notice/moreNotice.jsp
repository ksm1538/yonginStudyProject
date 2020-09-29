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
<title>Insert title here</title>
</head>
<body>
       <div class="col-12 col-center mw-1200 moreStudy_wrap">
        	<div class="col-12 moreStudy_list_wrap content_wrap">
         	   <div class="content_title"><span>공지사항</span></div>
         	   
         	   작성자 ID <input type="text" id="systemNoticeRgstusId" name ="systemNoticeRgstusId" class="textbox_style_1" onkeyup="enterKeyEvent();">
                           제목 <input type="text" id="systemNoticeTitle" name="systemNoticeTitle" class="textbox_style_1" maxlength="30" onkeyup="enterKeyEvent();">
            <input type="button" value="검색" class="btn_style_1" onclick="getSystemNoticeList()"  >
            
         	   <div style="width: 100%;" >
	  				<div data-ax5grid="noticeListPlusGrid" data-ax5grid-config="{}" style="height:610px; padding-top:10px; padding-right:10px"></div>  
			   </div> 
			   
			    <c:if test="${user.userIsAdmin == 'Y'}">
            	  <div class="apply_btn tc">
					<input type="button" value="글 작성하기" class="btn_style_1 type_3" onclick="openWriteNotice();">
					<input type="button" value="삭제하기" class="btn_style_1 type_3" onclick="deleteSystemNotice();">
				  </div>
           		</c:if>
			   	
   		 	</div>
       </div>
</body>
</html> 
