<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<script type="text/javascript" src="/resources/js/qna/qnaAnswerDetailPopup.js"></script>

<script>
var rgstusIdCode = '${user.userCode}';		// 세션에 있는 현재 접속한 유저의 코드 값을 가져옴

var qnaSxnList = [
    <c:forEach var="result" items="${qnaStatusArray}" varStatus="status">
        {codeId:"${result.codeId}", codeValue:"${result.codeValue}"}<c:if test="${!status.last}">,</c:if>
    </c:forEach> 	
    ];

var qnaSxnMap = {}; 
qnaSxnList.forEach(function(n){
	qnaSxnMap[n.codeId] = n.codeValue;
}); 

</script>
<meta charset="UTF-8">
<title>YonginStudy</title>
</head>
<body>
	
	
	<div class="col-12 qna_answer_detail_pop_wrap">
		<div class="col-12 qna_answer_detail_pop_con">
			<div class="circle_btn" onClick="closeModal()"></div> 
		 	<div class="tc qna_answer_detai_title"><span>QnA 답글 상세정보</span></div>
		</div>
	</div>
	
	<div class="col-12 qna_answer_detail_content_wrap">
		 <form id="readForm" name="readForm" method="post" role="form">
		 	<input type="hidden" id="FILE_CODE" name="FILE_CODE" value=""> 
		 </form> 
		 <form:form method="POST" modelAttribute="boardVO" name="qnaAnswerDetailForm" id="qnaAnswerDetailForm" >
		 <div class="study_detail_pop_wrap_con" id="detailDiv">
			
				<form:input path="boardCode" type="hidden" name="boardCode" id="boardCode" data-ax-path="boardCode" class="textbox_style_1"/>
				
				<div class="qna_answer_detail_name_wrap">
				<div class="study_detail_con detail_name">
					<div class="name_title_size_1">제목</div>
					<div class="study_detail_input_con">
						<form:input path="boardTitle" type="text" name="boardTitle" id="boardTitle" data-ax-path="boardTitle" class="name_textbox_style_0"/>
					</div>
				</div>	
				
				<div class="study_detail_con detail_name">
					<div class="name_title_size_1">작성자</div>
					<div class="study_detail_input_con">
						<form:input path="rgstusId" type="text" name="rgstusId" id="rgstusId" data-ax-path="rgstusId" class="textbox_style_1"/>
					</div>
				</div>	
				
				<div class="study_detail_con detail_name">
					<div class="name_title_size_1">날짜</div>
					<div class="study_detail_input_con">
						<form:input path="updtDt" type="text" name="updtDt" id="updtDt" data-ax-path="updtDt" class="textbox_style_1"/>
					</div>
				</div>
				</div>
				
				<div class="detail_desc">
					<div class="study_detail_input_con">
						<form:textarea path="boardDesc" id="boardDesc" name="boardDesc" data-ax-path="boardDesc" style=""/>
					</div> 
				</div>
				
				<div class="answer_file_desc">
					<div class="title_size type_2">첨부파일</div>
					<div id="fileListDiv">
					</div>
				</div>
				
			</div>
			<div class="answer_btn_style_1_con">
            	  <input type="button" value="수정하기" id="reviseBtn" class="btn_style_1"  onclick="openReviseQnaAnswer()" >
            	  <input type="button" value="삭제하기" id="deleteBtn" class="btn_style_1"  onclick="deleteQnaAnswer()" >
				  <input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
			</div> 
		 </form:form>
		
	</div>




<%-- 
	<div class="col-12 col-center mw-1200 study_detail_pop_wrap">
		 <div class="circle_btn" onClick="closeModal()"></div> 
		 <div class="tc content_title"><span>QnA 답글 상세정보</span></div>
		 
		 <form id="readForm" name="readForm" method="post" role="form">
		 	<input type="hidden" id="FILE_CODE" name="FILE_CODE" value=""> 
		 </form> 
		 
		 <form:form method="POST" modelAttribute="boardVO" name="qnaAnswerDetailForm" id="qnaAnswerDetailForm" >
		 <div class="study_detail_pop_wrap_con" id="detailDiv">
			
				<form:input path="boardCode" type="hidden" name="boardCode" id="boardCode" data-ax-path="boardCode" class="textbox_style_1"/>
				
				<div class="study_detail_con detail_name">
					<div class="title_size type_2">제목</div>
					<div class="study_detail_input_con">
						<form:input path="boardTitle" type="text" name="boardTitle" id="boardTitle" data-ax-path="boardTitle" class="textbox_style_1"/>
					</div>
				</div>	
				
				<div class="study_detail_con detail_name">
					<div class="title_size type_2">작성자</div>
					<div class="study_detail_input_con">
						<form:input path="rgstusId" type="text" name="rgstusId" id="rgstusId" data-ax-path="rgstusId" class="textbox_style_1"/>
					</div>
				</div>	
				
				<div class="study_detail_con detail_name">
					<div class="title_size type_2">날짜</div>
					<div class="study_detail_input_con">
						<form:input path="updtDt" type="text" name="updtDt" id="updtDt" data-ax-path="updtDt" class="textbox_style_1"/>
					</div>
				</div>	
				
				<div class="detail_desc">
					<div class="study_detail_input_con">
						<form:textarea path="boardDesc" id="boardDesc" name="boardDesc" data-ax-path="boardDesc" style=""/>
					</div> 
				</div>
				
				<div class="detail_desc">
					<div class="title_size type_2">첨부파일</div>
					<div id="fileListDiv">
					</div>
				</div>
				
			</div>
				<div class="btn_style_1_con">
            	  	<input type="button" value="수정하기" id="reviseBtn" class="btn_style_1"  onclick="openReviseQnaAnswer()" >
            	  	<input type="button" value="삭제하기" id="deleteBtn" class="btn_style_1"  onclick="deleteQnaAnswer()" >
					<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
				</div> 
		 </form:form>
		 
	  <!-- 댓글 구역 (시작) -->
	  <strong>댓글 <span id="replyCount"></span></strong><strong>개</strong>
	 
	  <div id="replyList" style="margin-top:10px; max-height:200px; overflow:auto;">
	  </div>
	  
	  <div id="inputReplyDiv" style="margin-top:10px;">
	    <textarea name="replyText" id="replyText" class="form-control" placeholder="댓글을 입력해주세요" style="margin-top:5px; width:99%; height:60px;"></textarea>
	    <button id="inputReplyCnButton" onclick="saveReply();" style="background-color:#A4A4A4; border-color:#A4A4A4; float:right; margin-top:10px; margin-right:10px">댓글 입력하기</button>
	  </div>
		 
	  <!-- 댓글 구역 (끝) --> --%>
	  

</body>
</html>