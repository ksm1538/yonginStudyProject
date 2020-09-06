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

<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<%-- <jsp:include page="../common/header.jsp"></jsp:include> --%>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/manageStudy.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="circle_btn" onClick="self.close()"></div> 
	
	
		
		<div class="manageStudy_wrap">
			<div class="manageStudy_con">
				<div class="manageStudy_title"><span>스터디원 관리</span></div>
				<div class="my_make_study"style="width: 70%;" >  
	  				<div data-ax5grid="manageStudyListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
				</div>
			</div>
		
		
			<div class="see_apply_study_con">
				<div class="manageStudy_title"><span>스터디 신청 현황</span></div>
				<div class="see_apply_study"style="width: 100%;" >  
	  				<div data-ax5grid="seeApplystudyListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
				</div>
			</div>
		
			
		</div>
		
		<div>
		 	
		</div>
</body>
</html>