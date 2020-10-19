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
<script type="text/javascript" src="/resources/js/qna/qnaDetailPopup.js"></script>

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

	<div class="col-12 qna_detail_pop_wrap">
		<div class="col-12 col-center mw-1200 qna_detail_pop_con">
			<div class="circle_btn" onClick="closeModal()"></div>
			<div class="tc qna_detail_title">Q&A 상세정보</div>
		</div>
	</div>
	
	<div class="col-12 qna_detail_pop_content_wrap">
			 <form id="readForm" name="readForm" method="post" role="form">
		 		<input type="hidden" id="FILE_CODE" name="FILE_CODE" value=""> 
		 	 </form> 
		 
			 <form:form method="POST" modelAttribute="boardVO" name="qnaDetailForm" id="qnaDetailForm" >
		 		<div class="" id="detailDiv">
			
					<form:input path="boardCode" type="hidden" name="boardCode" id="boardCode" data-ax-path="boardCode" class="textbox_style_1"/>
				
					<div class="study_detail_con detail_name">
						<div class="title_size type_2">제목</div>
						<div class="study_detail_input_con">
							<form:input path="boardTitle" type="text" name="boardTitle" id="boardTitle" data-ax-path="boardTitle" class="textbox_style_1"/>
						</div>
					</div>	
				
					<div class="study_detail_con detail_name">
						<div class="title_size type_2">상태</div>
						<div class="study_detail_input_con">
							<form:input path="qnaStatus" type="text" name="qnaStatus" id="qnaStatus" data-ax-path="qnaStatus" class="textbox_style_1"/>
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
						<div class="filelist" id="fileListDiv">
						</div>
					</div>
				
				</div>
				
				
				<div class="btn_style_1_con">
            	  	<input type="button" value="수정하기" id="reviseBtn" class="btn_style_1"  onclick="openReviseQna()" >
            	  	<c:if test="${user.userIsAdmin == 'Y'}">
						<input type="button" value="답글 작성" id="answerBtn" class="btn_style_1" onclick="openWriteQnaAnswer();">
	           		</c:if>
            	  	<input type="button" value="삭제하기" id="deleteBtn" class="btn_style_1"  onclick="deleteQna()" >
					
				</div>
				
				<div class="reply_con">
					<div class="reply_numbering_con">
						<sapn class="reply_title">댓글&nbsp;</sapn><span class="reply_number" id="replyCount"></span><span class="reply_amount">개</span>
					</div>
					  
	 
	  				<div id="replyList" class="reply_list"></div>
	  
	  				<div id="inputReplyDiv" class="reply_write">
	  					<textarea name="replyText" id="replyText" class="form-control" placeholder="댓글을 입력해주세요" style="margin-top:5px; width:99%; height:60px;"></textarea>	    				
	  				</div>
	  				
	  				<input type="button" id="inputReplyCnButton" class="btn_style_1" onclick="saveReply();" style="" value="댓글 입력하기">	
	  				
	  			
				</div>
				
				<div class="btn_style_1_con_2">
					<input type="button" value="닫기" class="btn_style_1" onclick="closeModal()" >
				</div>
				 
		 	</form:form>
		</div>
	
	
	

</body>
</html>