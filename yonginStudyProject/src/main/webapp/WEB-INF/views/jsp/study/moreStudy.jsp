<!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 한글 설정(끝) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>
    
<!DOCTYPE html>
<html>
<head>
<!---- css, js 설정(시작) ----> 
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/jquery/jquery.js" />" rel="stylesheet">
<script src="<c:url value="/resources/js/main.js" />"></script>
<script src="<c:url value="/resources/js/moreStudy.js" />"></script>
<script src="<c:url value="/resources/js/myPage.js" />"></script>
<script src="<c:url value="/resources/js/studyApplyPop.js" />"></script>

<%-- <link href="<c:url value="/resources/util/bootstrap-3.4.1-dist/css/bootstrap.min.css" />" rel="stylesheet"> --%>
<link href="<c:url value="/resources/util/ax5ui-dialog-master/dist/ax5dialog.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/ax5ui-mask-master/dist/ax5mask.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/ax5ui-toast-master/dist/ax5toast.css" />" rel="stylesheet">

<link href="<c:url value="/resources/util/ax5ui-grid-master/dist/ax5grid.css" />" rel="stylesheet">
<!-- <link href="<c:url value="/resources/util/toast-ui/tui-calendar.css" />" rel="stylesheet">  -->


<script src="<c:url value="/resources/util/jquery-ui/jquery-1.12.4.min.js" />"></script>
<%-- <script src="<c:url value="/resources/util/bootstrap-3.4.1-dist/js/bootstrap.min.js" />"></script> --%>
<script src="<c:url value="/resources/util/ax5core-master/dist/ax5core.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5ui-dialog-master/dist/ax5dialog.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5ui-mask-master/dist/ax5mask.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5ui-toast-master/dist/ax5toast.min.js" />"></script>
<script src="<c:url value="/resources/js/constant.js" />"></script>

<script src="<c:url value="/resources/util/ax5ui-grid-master/dist/ax5grid.min.js" />"></script>
<script src="<c:url value="/resources/util/toast-ui/tui-code-snippet.js" />"></script>
<!--  <script src="<c:url value="/resources/util/toast-ui/tui-calendar.min.js" />"></script> -->
<!---- css, js 설정(끝) ---->

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <div class="col-12 point_bg0 header_wrap">
        <div class="col-12 col-center mw-1200 header_con">
                <div class="header_menu_con">
                    <ul class="header_menu">
                        <li><a>HOME</a></li>
                        <li><a>스터디목록</a></li>
                        <li><a>공지사항</a></li>
                        <li><a>마이페이지</a></li>
                    </ul>
                </div>
                
                <div class="header_member_con">
                    <div class="header_user"><span class="user_id">${user.userId}</span>님 환영합니다.</div>
                    <div class="logout"><a href="/logout.json">로그아웃</a></div>
                    
                    <div class="user_box_con">
                    	<div class="circle_btn_2"></div>
                    	<div class="user_box_tri"></div>
                    	<div>
                    		 <ul class="user_box">
                       			 <li><a onclick="openMypageForm();">마이페이지</a></li>
                      			 <li><a>쪽지함</a></li>
                   			 </ul>
                    	</div>
                    </div>
                </div>
        </div>
    </div>
   
       <div class="col-12 col-center mw-1200 moreStudy_wrap">
        <div class="col-12 moreStudy_list_wrap content_wrap">
            <div class="content_title"><span>스터디 목록</span></div>
            
            <div style="width: 100%;" >  
	  			<div data-ax5grid="studyListGrid" data-ax5grid-config="{}" style="height:600px; padding-top:10px; padding-right:10px"></div>  
			</div> 
			
			<div class="apply_btn">
				<input type="button" value="스터디 신청하기" class="btn_style_1 type_3" onclick="openstudyApplyPopForm();">
			</div>
		
        </div>
       </div>
</body>
</html>
