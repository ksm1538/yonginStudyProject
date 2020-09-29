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
<%-- <jsp:include page="../common/header.jsp"></jsp:include> --%>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/qna/writeQna.js"></script>



<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-12 write_qna_header_wrap">
		<div class="circle_btn" onClick="closeModal();"></div> 
		<div class="tc write_qna_header_con">
			<div class="write_qna_header">Q&A 작성하기</div>
		</div>
	</div>
	
	<div class="col-12 write_qna_content_wrap">
		<div class="write_qna_content_con">
			<div class="write_qna_name_con">
				<div class="title_size type_2">제목</div>
				<div class="register_input_con">
					<input path="" type="text" name="" id="" class="textbox_style_1 type_3" placeholder="10~30자로 설정해주세요."/>
				</div>
			</div>
		</div>
		
		<div class="tc summer_note_con"><!-- 내용이라고 안넣는게 더 이쁠거같아서 뺌 -->
     		<div class="summer_note" style="">
     		   	<textarea name="writeQnaDesc" id="writeQnaDesc"></textarea>
     		</div>    		   		
     	</div>
     	
     	<div class="tc write_qna_btn_con">
     		<input type="button" value="완료" class="btn_style_1" onclick="" >
			<input type="button" value="닫기" class="btn_style_1" onclick="closeModal();" >
     	</div>
		
	</div>
</body>
</html>