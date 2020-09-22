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
<title>Insert title here</title>
</head>
<body>
	(관리자 페이지)
	
	사용자 관리(사용자를 추방하거나 관리자로 임명할 수 있음)
	<div class="main_study_list_con" style="width: 100%;" >  
		<div data-ax5grid="userListGrid" data-ax5grid-config="{}" style="height:600px; padding-top:10px; padding-right:10px"></div>  
	</div>
	
	스터디 관리(불순한 스터디 삭제가능)
	<div class="main_study_list_con" style="width: 100%;" >  
		<div data-ax5grid="studyListGrid" data-ax5grid-config="{}" style="height:600px; padding-top:10px; padding-right:10px"></div>  
	</div>
	
</body>
</html>