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
<script type="text/javascript" src="/resources/js/main/adminPage.js"></script>
<script>
//그리드에 출력될 스터디 주제 코드 값 설정
var studySxnList = [
    <c:forEach var="result" items="${studyTopicArray}" varStatus="status">
        {codeId:"${result.codeId}", codeValue:"${result.codeValue}"}<c:if test="${!status.last}">,</c:if>
    </c:forEach> 	
    ];

var studySxnMap = {}; 
studySxnList.forEach(function(n){
	studySxnMap[n.codeId] = n.codeValue;
}); 
</script>
<meta charset="UTF-8">
<title>YonginStudy</title>
</head>
<body>

	<div class="side_fixed_menu_con">
			<div class="side_fixed_menu_list_con">
				<ul class="side_fixed_menu_list">
					<li><a><img src="/resources/img/arrow.png" class="side_arrow_img" id="Movetop"></a></li>
					<li><a><img src="/resources/img/mymake.png" class="side_mymake_img" id="side_movelist1"></a></li>
					<li><a><img src="/resources/img/mystudy.png" class="side_mystudy_img" id="side_movelist2"></a></li>
				</ul>
			</div>
			<div class="tc side_fixed_menu_title">빠른 이동</div>			
	</div> 
	<div class="col-12 admin_page_id_manage_wrap" id="move1">
		<div class="col-12 mw-1200 col-center admin_page_id_manage_con">
			<div class="col-12 admin_id_search_method_con">
				<div class="admin_main_title_con">
					<div class="admin_main_title"><span>사용자 관리</span></div>
					<div class="question_mark_con">
						<div class="tc question_mark"><span>?</span></div>
						<div class="qestion_desc_box_con">
							<div class="question_tri type_2"></div>
							<div class="question_desc type_2">사용자 관리는 관리자가 다양한 수단을 이용한 검색을 통해 현재 스터디 매칭 프로그램을 이용중인 사용자를 찾을 수 있습니다. 각 양식은 필수 입력 사항이 아닙니다.</div>
						</div>
					</div>
				</div>
				<div class="admin_id_search_method">
					<div class="admin_search id_management">
						<div class="admin_manage_title type_2">아이디</div>
						<input type="text" id="searchUserId" name ="searchUserId" class="textbox_style_1 color_bg" onkeyup="enterKeyEvent1();">
					</div>
				
					<div class="admin_search name_management">
						<div class="admin_manage_title type_3">이름</div>
						<input type="text" name="searchUserName" id="searchUserName" class="textbox_style_1 color_bg" onkeyup="enterKeyEvent1();">
					</div>
					
					<div class="admin_search email_management">
					
						<div class="admin_manage_title type_2">이메일</div>
						<input type="text" name="searchUserEmail" id="searchUserEmail" class="textbox_style_1 color_bg" onkeyup="enterKeyEvent1();">
					</div>
				
					<div class="admin_search admin_check_management">
						<div class="admin_manage_title">관리자 여부</div>
						<select name="searchUserIsAdmin" id="searchUserIsAdmin" class="select_style_0" >
  							<option class="option" value="noSelect" >선택 안함</option>
  							<option value="N" >일반 사용자</option>
  							<option value="Y" >관리자</option>
    					</select>
					</div>
					<div class="admin_search adimin_check_btn">
						<input type="button" value="검색" class="btn_style_1" onclick="searchUserList()" >
					</div>
					
				
				</div>																
			</div>
			<div class="main_study_list_con" style="width: 100%;" >  
				<div data-ax5grid="userListGrid" data-ax5grid-config="{}" class="admin_study_list color_grid" style="padding-top:10px; padding-right:10px"></div>  
			</div>
		</div>
	</div>
	
	<div class="col-12 admin_page_sdudy_manage_wrap" id="move2" >
		<div class="col-12 col-center mw-1200 admin_page_sdudy_manage_con">
			<div class="col-12 admin_study_search_method_con">
			
				<div class="admin_main_title_con">
					<div class="admin_main_title type_2"><span>스터디 관리</span></div>
					<div class="question_mark_con">
						<div class="tc question_mark"><span>?</span></div>
						<div class="qestion_desc_box_con">
							<div class="question_tri"></div>
							<div class="question_desc">스터디 관리는 관리자가 다양한 수단을 이용한 검색을 통해 스터디 매칭 시스템의 취지와  맞지 않는 스터디를 삭제할 수 있습니다.</div>
						</div>
					</div>
				</div>
				<div class="admin_id_search_method">
					<div class="admin_search name_management">
						<div class="admin_manage_title">스터디 이름</div>
						<input type="text" id="studyName" name ="studyName" class="textbox_style_1" onkeyup="enterKeyEvent2();">
					</div>
					<div class="admin_search study_subject_management">
						<div class="admin_manage_title">스터디 지역</div>
						<input type="text" name="studyArea" id="studyArea" class="textbox_style_1" onkeyup="enterKeyEvent2();">
					</div>
					<div class="admin_search study_subject_management">
						<div class="admin_manage_title">스터디 주제</div>
						<select name="studyTopic" id="studyTopic" class="select_style_0" >
  							<option value="noSelect" ><c:out value='선택 안함'/>
							<c:forEach var="result" items="${studyTopicArray}" varStatus="status">
							<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
							</c:forEach>
    					</select>
					</div>
					<div class="admin_search sutdymanage_search_btn">
						<input type="button" value="검색" class="btn_style_1" onclick="searchStudyList()" >		
					</div>	
					
				</div>
				
							
			</div>
			<div class="main_study_list_con"  style="width: 100%;" >  
				<div data-ax5grid="studyListGrid" class="admin_study_list admin_study_manage_grid" data-ax5grid-config="{}"  style="padding-top:10px; padding-right:10px"></div>  
			</div>
			
		</div>
	</div>

</body>
</html>