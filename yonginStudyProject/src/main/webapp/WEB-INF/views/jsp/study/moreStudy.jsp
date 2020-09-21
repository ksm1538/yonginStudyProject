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
   
       <div class="col-12 col-center mw-1200 moreStudy_wrap">
        	<div class="col-12 moreStudy_list_wrap content_wrap">
         	   <div class="content_title"><span>스터디 목록</span></div>
            	스터디 주제 <select name="studyTopic" id="studyTopic" class="select_style_0" >
            		<option value="noSelect" ><c:out value='선택 안함'/>
					<c:forEach var="result" items="${studyTopicArray}" varStatus="status">
						<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
					</c:forEach>
           		</select>
           		스터디 이름 <input type="text" id="studyName" name ="studyName" class="textbox_style_1" onkeyup="enterKeyEvent();">
           		스터디 지역 <input type="text" name="studyArea" id="studyArea" class="textbox_style_1" onkeyup="enterKeyEvent();">
            	<input type="button" value="검색" class="btn_style_1" onclick="searchStudyList()" >
            	
         	   <div style="width: 100%;" >
	  				<div data-ax5grid="studyListPlusGrid" data-ax5grid-config="{}" style="height:610px; padding-top:10px; padding-right:10px"></div>  
				</div> 
			
				
		
       		 </div>
       </div>
</body>
</html>
