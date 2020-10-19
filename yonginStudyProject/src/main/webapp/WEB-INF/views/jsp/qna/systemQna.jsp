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
<script type="text/javascript" src="/resources/js/qna/systemQna.js"></script>

<script>
//그리드에 출력될 qna 상태 코드 값 설정
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
	<div class="col-12 make_qna_wrap">
		<div class="col-12 col-center mw-1200 make_qna_con">
			<div class="make_qna_title_con">
				<div class="make_qna_title"><span>Q&A</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri type_2"></div>
						<div class="question_desc type_2">궁금한사항을 물어볼 수 있는 공간 입니다. 더블클릭하면 상세정보를 볼 수 있고 답글 확인 버튼을 누르면 답글을 확인할 수 있습니다.</div>
					</div>
				</div>
			</div>
			
			<div class="make_qna_search_con">
				<div class="make_qna_search_box write_qna_person">
					<div class="make_qna_box_title">작성자 ID</div>
					<input type="text" id=searchBoardRgstusId  class="textbox_style_1 color_bg" onkeyup="">
				</div>
				<div class="make_qna_search_box qna_name">
					<div class="make_qna_box_title">제목</div>
					<input type="text" id="searchBoardTitle" class="textbox_style_1 color_bg" onkeyup="">
				</div>
				<div class="make_qna_search_box get_search_btn">
					  <input type="button" value="검색" class="btn_style_1" onclick="" >
				</div>
				
			</div>
			
			<div style="width: 100%;" >  
	  			<div data-ax5grid="qnaListGrid" data-ax5grid-config="{}" class="qna_grid color_grid" style="height:610px; padding-top:10px; padding-right:10px"></div>  
			</div>
			
			<div class="tc make_qna_btn_con">
				<input type="button" value="질문 하기" class="btn_style_1" onclick="writeQna();">
			</div>
			
		</div>
		
	</div>
</body>
</html>


