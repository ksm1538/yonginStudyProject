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
<jsp:include page="../studyManagement/studyHeader.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/studyMain.js"></script>


<meta charset="UTF-8">
<title>Insert title here</title> 
</head>
<body>
   
   <div class="col-12 study_main_wrap">
   		<div class="study_main_content_img"></div>
		<div class="study_main_content_dim"></div>
		
		<div class="study_main_title_con">
			<div class="tc study_main_title"><span class="study_main_name">스터디이름</span>에 오신걸 환영합니다 !</div>
			<div class="tc study_main_sub_title">스터디원과 힘을 모아 공동된 목표를 향해 나아가세요</div>
		</div>
		
		<div class="study_main_content_con">
			<div class="study_main_content">
				<div class="tc study_main_content_img_con"><img src="../resources/img/mymake.png" class="info_img"></div>
				<div class="tc study_main_content_title">스터디원 보기</div>
				<div class="tc study_main_content_sub_title">나와 함께는 스터디원을 확인하세요</div>
			</div>
			
			<div class="study_main_content">
				<div class="tc study_main_content_img_con"><img src="../resources/img/cal.png" class="cal_img"></div>
				<div class="tc study_main_content_title">스터디 일정</div>
				<div class="tc study_main_content_sub_title">스터디의 일정을 확인하세요</div>
			</div>
			
			<div class="study_main_content">
				<div class="tc study_main_content_img_con"><img src="../resources/img/chatting.png" class="chatting_img"></div>
				<div class="tc study_main_content_title">스터디 채팅</div>
				<div class="tc study_main_content_sub_title">스터디 채팅을 통해 소통하세요</div>
			</div>
		</div>
   </div>
   
   <div class="col-12 study_memeber_wrap">
   		<div class="col-12 col-center mw-1200 study_memeber_con">
   			<div class="study_memeber_title_con">
				<div class="study_memeber_title"><span>스터디원 보기</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri"></div>
						<div class="question_desc">현재 함께 스터디를 하는 동료들을 볼 수 있습니다.</div>
					</div>
				</div>
			</div>
   		</div>
   		
   		<div class="col-12 col-center mw-1200 study_member_grid_con"style="width: 100%;" >  
	  		<div data-ax5grid="studyMemberListGrid" data-ax5grid-config="{}" class="" style="height:300px; padding-top:10px; padding-right:10px"></div>  
		</div>
   		
   </div>
   
   <div class="col-12 study_plan_wrap">
   		<div class="col-12 col-center mw-1200 study_plan_con">
   			<div class="study_plan_title_con">
				<div class="study_plan_title"><span>스터디 일정</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri type_2"></div>
						<div class="question_desc type_2">스터디 일정을 보여줍니다. 일정을 확인하세요</div>
					</div>
				</div>
			</div>
			
			<div id="menu" class="calender_menu">
				<span id="menu-navi">
					  <button type="button" class="prev_btn type_2" data-action="move-prev" onclick="prev();">
					  	<i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
					  </button>
					  <span id="renderRange" class="render-range" style="font-size:2.5rem;">2020.09</span>
					  <button type="button" class="next_btn type_2" data-action="move-next" onclick="next();">
					    <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
					  </button>
				</span>
				<span id="renderRange" class="render-range"></span>
			</div>
   			<div id="study_calendar"></div>	
			
			
			
   		</div>   		  		
   </div>
	
	
	
</body>
</html> 