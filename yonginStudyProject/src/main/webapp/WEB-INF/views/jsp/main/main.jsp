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
<jsp:include page="../common/header.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/main.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
    <div class="col-12 col-center mw-1200 main_content_wrap">
            <!--게시판-->
        <div class="col-12 study_list_wrap content_wrap">
            <div class="content_title"><span>스터디 목록</span></div>
            
            <div class="main_study_list_con" style="width: 100%;" >  
	  			<div data-ax5grid="studyListGrid" data-ax5grid-config="{}" style="height:600px; padding-top:10px; padding-right:10px"></div>  
			</div>
			
			<div class="main_btn_con">
				<div class="list_btn">
					<input type="button" value="스터디 만들기" class="btn_style_1 type_3">
				</div>
				<div class="make_btn">
					<input type="button" value="스터디  더보기" class="btn_style_1 type_3" onclick="openMoreStudyForm();">
				</div>
			</div>
        </div>
	
	
        <div class="col-12 sub_content_wrap">
            <div class="col-5 left_content">
                <div class="col-12 calender_wrap content_wrap">
                    <div class="content_title"><span>시험일정</span></div>
					    <!-- 달력 버튼 -->
					    <div id="menu">
					      <span id="menu-navi">
					        <button type="button" class="btn btn-default btn-sm move-today" data-action="move-today">Today</button>
					        <button type="button" class="btn btn-default btn-sm move-day" data-action="move-prev">
					          <i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
					        </button>
					        <button type="button" class="btn btn-default btn-sm move-day" data-action="move-next">
					          <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
					        </button>
					      </span>
					      <span id="renderRange" class="render-range"></span>
					    </div> 
                    	<!-- 달력표시 -->
                    	<div id="calendar"></div>
                </div>
            </div>
    
            <div class="col-6 right_content">
                <div class="col-12 notice_wrap content_wrap">
                   <div class="content_title"><span>공지사항</span></div>
               		<div style="width: 100%;" >  
	  					<div data-ax5grid="studyNoticeListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
					</div>
                </div>
            </div>
        </div>
       
        
    </div>
</body>
</html>