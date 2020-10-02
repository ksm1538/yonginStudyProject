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
<script type="text/javascript" src="/resources/js/study/moreStudy.js"></script>
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
<!---- css, js 설정(끝) ----> 

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<div class="col-12 more_study_wrap">
		<div class="col-12 col-center mw-1200 more_study_con">
			<div class="more_study_title"><span>스터디 목록</span></div>
			
			<div class="more_study_search_con">
				<div class="more_study_search_box">
					<div class="more_study_search_title">스터디 주제</div>
					<select name="studyTopic" id="studyTopic" class="select_style_0" >
            			<option value="noSelect" ><c:out value='선택 안함'/>
						<c:forEach var="result" items="${studyTopicArray}" varStatus="status">
							<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
						</c:forEach>
           			</select>
				</div>
				
				<div class="more_study_search_box">
					<div class="more_study_search_title">스터디 이름</div>
					<input type="text" id="studyName" name ="studyName" class="textbox_style_1 color_bg" onkeyup="enterKeyEvent();">
				</div>
				
				<div class="more_study_search_box">
					<div class="more_study_search_title">스터디 지역</div>
					<input type="text" name="studyArea" id="studyArea" class="textbox_style_1 color_bg" onkeyup="enterKeyEvent();">
				</div>
				
				<input type="button" value="검색" class="btn_style_1" onclick="searchStudyList()" >
			</div>
			
			<div class="study_list_grid_con"style="width: 100%;" >
	  			<div data-ax5grid="studyListPlusGrid" class="color_grid" data-ax5grid-config="{}" style="height:610px; padding-top:10px; padding-right:10px"></div>  
			</div> 
			
			<div class="make_sutdy_btn_con">
				<input type="button" value="스터디만들기" class="btn_style_1 type_2" onclick="makeStudyForm();" >
			</div>
			
			
		</div>
	</div>

    
       
</body>
</html>
