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
<script type="text/javascript" src="/resources/js/studyManagement/studyManage.js"></script>
<script>

//그리드에 출력될 직책 코드 값 설정
var studyPositionList = [
    <c:forEach var="result" items="${studyAuthorityArray}" varStatus="status">
        {codeId:"${result.codeId}", codeValue:"${result.codeValue}"}<c:if test="${!status.last}">,</c:if>
    </c:forEach> 	
    ];
    
var studyPositionMap = {}; 
studyPositionList.forEach(function(n){
	studyPositionMap[n.codeId] = n.codeValue;
}); 

</script>

<meta charset="UTF-8">
<title>Insert title here</title> 
</head>
<body>
   
   <div class="col-12 study_main_wrap">
   		<div class="study_main_content_img"></div>
		<div class="study_main_content_dim"></div>
		
		
		<div class="side_fixed_menu_con">
			<div class="side_fixed_menu_list_con">
				<ul class="side_fixed_menu_list">
					<li><a><img src="/resources/img/arrow.png" class="side_arrow_img" id="Movetop"></a></li>
					<li><a><img src="/resources/img/mymake.png" class="side_mymake_img" id="side_movelist1"></a></li>
					<li><a><img src="/resources/img/apply.png" class="side_apply_img" id="side_movelist3"></a></li>
				</ul>
			</div>
			<div class="tc side_fixed_menu_title">빠른 이동</div>			
		</div> 
		
		<div class="study_main_title_con">
			<div class="tc study_main_title"><span class="study_main_name" id="spanStudyMainName"></span>관리 페이지에 오신걸 환영합니다 !</div>
			<div class="tc study_main_sub_title">공동된 목표를 이루기 위해 스터디를 관리하세요</div>
		</div>
		
		<div class="study_main_content_con">
			<div class="study_main_content" id="movelist1">
				<div class="tc study_main_content_img_con"><img src="../resources/img/mymake.png" class="info_img"></div>
				<div class="tc study_main_content_title">스터디원 관리</div>
				<div class="tc study_main_content_sub_title">나와 함께는 스터디원을 관리하세요</div>
			</div>
			
			<div class="study_main_content" id="movelist3">
				<div class="tc study_main_content_img_con"><img src="../resources/img/apply.png" class="apply_img"></div>
				<div class="tc study_main_content_title">신청서</div>
				<div class="tc study_main_content_sub_title">누구에게 신청이 왔는지 확인하세요</div>
			</div>
			
			<div class="study_main_content" id="movelist2" onclick="openChangeStudyInfo()">
				<div class="tc study_main_content_img_con"><img src="../resources/img/apply.png" class="edit_img"></div>
				<div class="tc study_main_content_title">스터디 정보 변경</div>
				<div class="tc study_main_content_sub_title">스터디 정보를 변경하세요</div>
			</div>
		</div>
   </div>
   
   <div class="col-12 study_memeber_wrap" id="movemanage">
   		<div class="col-12 col-center mw-1200 study_memeber_con">
   			<div class="study_memeber_title_con">
				<div class="study_memeber_title"><span>스터디원 관리</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri"></div>
						<div class="question_desc">스터디원이 공동된 목표와 적합하지 않다면 추방할 수 있습니다.</div>
					</div>
				</div>
			</div>
   		</div>
   		
   		<div class="col-12 col-center mw-1200 study_memeber_search_con">
				<div class="study_memeber_search_box get_person">
					<div class="study_memeber_box_title">ID</div>
					<input type="text" id="searchStudyMemberId" name ="searchStudyMemberId" class="textbox_style_1" onkeyup="enterKeyEvent();">
				</div>
				
				<div class="study_memeber_search_box get_person">
					<div class="study_memeber_box_title">이름</div>
					<input type="text" id="searchStudyMemberName" name ="searchStudyMemberName" class="textbox_style_1" onkeyup="enterKeyEvent();">
				</div>
				
				
				<div class="study_memeber_search_box get_person">
					<div class="study_memeber_box_title">주소</div>
					<input type="text" id="searchStudyMemberAddress" name ="searchStudyMemberAddress" class="textbox_style_1" onkeyup="enterKeyEvent();">
				</div>
				
				
				<div class="study_memeber_search_box get_person">
					<div class="study_memeber_box_title">스터디 직위</div>
					<select name="searchStudyMemberIsAdmin" id="searchStudyMemberIsAdmin" class="select_style_0" >
  							<option class="option" value="noSelect" >선택 안함</option>
  							<option value="30" >스터디원</option>
  							<option value="20" >스터디 관리자</option>
  							<option value="10" >스터디 장</option>
    					</select>
				</div>
				
				<div class="study_memeber_search_box get_search_btn">
					  <input type="button" value="검색" class="btn_style_1" onclick="searchStudyMemberList()" >
				</div>
		</div>
   		
   		<div class="col-12 col-center mw-1200 study_member_grid_con"style="width: 100%;" >  
	  		<div data-ax5grid="studyMemberManageListGrid" data-ax5grid-config="{}" class="" style="height:300px; padding-top:10px; padding-right:10px"></div>  
		</div>
   		
   </div>
   
   <div class="col-12 study_apply_check_wrap" id="moveapply">
   		<div class="col-12 col-center mw-1200 study_apply_check_con">
   			<div class="study_apply_check_title_con">
				<div class="study_apply_check_title"><span>신청서 관리</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri type_2"></div>
						<div class="question_desc type_2">어떤 분이 스터디에 참여하기 위해 신청 했는지 확인 할 수 있습니다.</div>
					</div>
				</div>
			</div>
   		
   		<div class="col-12 col-center mw-1200 "style="width: 100%;" >  
	  		<div data-ax5grid="studyApplyCheckListGrid" data-ax5grid-config="{}" class="color_grid" style="height:300px; padding-top:10px; padding-right:10px"></div>  
		</div>
   		</div>	
   </div>


	
</body>
</html> 