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
<!---- css, js 설정(시작) ----> 
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/jquery/jquery.js" />" rel="stylesheet">
<script src="<c:url value="/resources/js/main.js" />"></script>
<script src="<c:url value="/resources/js/moreStudy.js" />"></script>
<script src="<c:url value="/resources/js/myPage.js" />"></script>
<script src="<c:url value="/resources/js/studyApplyPop.js" />"></script>

<!---- css, js 설정(끝) ---->
<meta charset="UTF-8">
<title>스터디 신청서</title>
</head>
<body>

<div class="col-12 col-center mw-1200 search_memeber_title_wrap">

	<div class="circle_btn" onClick="self.close()"></div> 
	<div class="tc search_memeber_title_con">
		<div class="search_memeber_title">스터디 신청서</div>
		
	</div> 
	
</div>

	<div class="col-12 col-center mw-1200 search_memeber_form_wrap">
			<div class="study_apply_form_con">
				<div class="study_apply_reason_form_con">
					<div class="title_size type_2 tc"><span> 신청 사유 </span></div>
					<div class="textarea_style_con tc">
						<textarea class="textarea_style" cols="100" rows="10"></textarea>
					</div>
				</div>
			</div> 
	</div>
	
	<div class="col-12 col-center mw-1200 register_memeber_btn_wrap">
		<div class="btn_style_1_con">
			<input type="button" value="확인" class="btn_style_1" onclick="()" >
		</div> 
	</div>

</body>
</html>
