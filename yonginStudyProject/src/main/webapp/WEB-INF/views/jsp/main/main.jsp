    
    <!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 한글 설정(끝) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>

<!-- css, js 설정(시작) --> 

<script src="<c:url value="/resources/util/jquery/jquery.js" />"></script>
<script src="<c:url value="/resources/js/calender.js" />"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">

<!-- css, js 설정(끝) -->
    
<!-- ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ -->
    
<!DOCTYPE html>
<html>
<head>
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
                    <div class="header_user"><span class="user_id">용인대학교</span>님 환영합니다.</div>
                    <div class="logout">로그아웃</div>
                </div>
        </div>
    </div>

    <div class="col-12 col-center mw-1200 main_content_wrap">
            <!--게시판-->
        <div class="col-12 study_list_wrap content_wrap">
            <div class="content_title"><span>스터디 목록</span></div>
        </div>

        <div class="col-12 sub_content_wrap">
            <div class="col-5 left_content">
                <div class="col-12 calender_wrap content_wrap">
                    <div class="content_title"><span>시험일정</span></div>

                    <div class="calender_con">
                        <div class="col-12 tc calender_month_con">
                            <div class="left_arrow_con"><a href="#" id="prev_month"><img src="/resources/img/arrow.png" class="left_arrow"></a></div>
                            <div class="month_choice">
                                <span class="choice_year" id="choice_year"></span>
                                <span class="choice_month" id="choice_month"></span>
                            </div>
                            <div class="right_arrow_con"><a href="#" id="next_month"><img src="/resources/img/arrow.png" class="right_arrow"></a></div>
                        </div>
                    </div>

                    <div class="calender_date_con">
                        <div class="calender_date" id="calender_date"></div>
                    </div>

                </div>
            </div>
    
            <div class="col-6 right_content">
                <div class="col-12 notice_wrap content_wrap">
                   <div class="content_title"><span>공지사항</span></div>
                </div>
            </div>
        </div>

       
        
    </div>
</body>
</html>