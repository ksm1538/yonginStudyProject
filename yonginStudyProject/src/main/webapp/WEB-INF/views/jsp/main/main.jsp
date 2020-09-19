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
<script type="text/javascript" src="/resources/js/main/main.js"></script>
<script>
//진행상태 코드 값 설정
var inoutSxnList = [
    <c:forEach var="result" items="${studyTopicArray}" varStatus="status">
        {codeId:"${result.codeId}", codeValue:"${result.codeValue}"}<c:if test="${!status.last}">,</c:if>
    </c:forEach> 	
    ];

var inoutSxnMap = {};
inoutSxnList.forEach(function(n){
    inoutSxnMap[n.codeId] = n.codeValue;
}); 

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div class="col-12 main_top_desc_wrap">
		<div class="side_fixed_menu_con">
			<div class="side_fixed_menu_list_con">
				<ul class="side_fixed_menu_list">
					<li><a><img src="/resources/img/arrow.png" class="side_arrow_img" id="Movetop"></a></li>
					<li><a><img src="/resources/img/list.png" class="side_list_img" id="side_movelist1"></a></li>
					<li><a><img src="/resources/img/notice.png" class="side_notice_img" id="side_movelist2"></a></li>
					<li><a><img src="/resources/img/cal.png" class="side_cal_img" id="side_movelist3"></a></li>
				</ul>
			</div>
			<div class="tc side_fixed_menu_title">빠른 이동</div>			
		</div> 
		<div class="col-12 col-center mw-1200 main_top_desc_con">
			
			<div class="col-6 main_top_left">
				<div class="main_top_title">누가 사공이 많으면 배가 산으로<br> 간다 했나?</div>
				<div class="main_top_sub_title">목표를 위해 팀원을 모아 함께 준비하세요 !</div>
				
				<div class="main_move_btn_con">
					<div class="move_main_list main_my_study_list"><a id="movelist1">나의 스터디 목록</a></div>
					<div class="move_main_list main_my_study_list"><a id="movelist2">스터디 공지사항</a></div>
				</div>
				<div class="main_move_btn_con">
					<div class="move_main_list main_my_study_list"><a id="movelist3">스터디 일정 보기</a></div>
				</div>
			</div>
		
		
			<div class="col-6 main_top_right">
				<div class="main-content">
   					 <div class="main_banner_wrap">
      					  <div class="main_banner_list_con">      					
           						<div class="main_banner_list"> <a><img src="../resources/img/study1.jpg"></a></div>
           						<div class="main_banner_list"> <a><img src="../resources/img/study2.jpg" style="width:600px;"></a></div>
           						<div class="main_banner_list"> <a><img src="../resources/img/study3.jpg"></a></div>
           						<div class="main_banner_list"> <a><img src="../resources/img/study4.jpg"></a></div>
           						<div class="main_banner_list"> <a><img src="../resources/img/study5.jpg"></a></div>
        				  </div>
  					 </div>
				</div>	
			</div>
			
		</div>	
	</div>
	
	<div class="col-12 main_my_study_list_wrap" id="movelist">
		<div class="col-12 col-center mw-1200 main_my_study_list_con">
			
			<div class="main_my_study_list_title_con">
				<div class="main_my_study_list_title"><span>나의 스터디 목록</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri"></div>
						<div class="question_desc">나의 스터디 목록은 사용자가 참여하고 있는 스터디의 주제, 제목 , 지역 등 다양한 정보를 제공합니다. 추가로 스터디 클릭시 상세 정보를 볼 수 있습니다.</div>
					</div>
				</div>
			</div>
			
			<div class="main_study_list_con" style="width: 100%;" >  
	  			<div data-ax5grid="studyListGrid" data-ax5grid-config="{}" style="height:600px; padding-top:10px; padding-right:10px"></div>  
			</div>
		</div>
		
	</div>
	
	<div class="col-12 main_my_study_notice_wrap" id="movenotice">
		<div class="col-12 col-center mw-1200 main_my_study_notice_con">
			<div class="main_my_study_notice_title_con">
				<div class="main_my_study_notice_title"><span>스터디 공지사항</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri type_2"></div>
						<div class="question_desc type_2">스터디 공지사항은 사용자가 참여하고 있는 스터디에서 등록된 공지사항을 보여 드립니다.</div>
					</div>
				</div>
			</div>
				<div style="width: 100%;" >  
	  					<div data-ax5grid="studyNoticeListGrid" data-ax5grid-config="{}" class="color_grid" style="height:500px; padding-top:10px; padding-right:10px"></div>  
				</div>
		</div>
	</div>
	
	<div class="col-12 main_my_study_calender_wrap" id="movecal">
		<div class="col-12 col-center mw-1200 main_my_study_calender_con">
			<div class="main_my_study_calender_title_con">
				<div class="main_my_study_calender_title"><span>스터디 일정</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri"></div>
						<div class="question_desc">스터디 일정은 사용자가 참여하고 있는 스터디에서 등록된 일정을 보여 드립니다.</div>
					</div>
				</div>
			</div>
			
			
			<div id="menu" class="calender_menu">
				<span id="menu-navi">
					  <button type="button" class="today_btn" data-action="move-today">Today</button>
					  <button type="button" class="prev_btn" data-action="move-prev">
					  	<i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
					  </button>
					  <button type="button" class="next_btn" data-action="move-next">
					    <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
					  </button>
				</span>
				<span id="renderRange" class="render-range"></span>
			</div>
			<div id="calendar"></div>	
		</div>	
	</div>
	
</body>
</html>