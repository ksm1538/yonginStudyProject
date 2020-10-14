<!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<!-- 한글 설정(끝) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!------ JSP 설정(끝) ------>

<!-- css, js 설정(시작) -->
<!--<script src="<c:url value="/resources/util/jquery.js" />"></script>-->

<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 주소 API javascript 호출(주소 사용하는 곳만 추가) -->
<!-- <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> -->

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/writeStudyNotice.js"></script>

<!-- 자기가 만든 js파일은 가장 밑으로. 순서 잘 지키고 -->
<!-- css, js 설정(끝) -->

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<div> 스터디 공지사항 test</div>
</body>
</html>