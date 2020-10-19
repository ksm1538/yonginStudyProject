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
<jsp:include page="../message/messageSidemenu.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/message/sendMessageList.js"></script>
	

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<div class="col-12 send_message_list_wrap" id="send_message_top">
		<div class="col-12 col-center mw-1200 send_message_list_con">
			<div class="send_message_list_title_con">
				<div class="send_message_list_title"><span>보낸 쪽지함</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri"></div>
						<div class="question_desc">누구에게 쪽지를 보냈는지 확인하세요. 삭제버튼을 통해 쪽지를 삭제할 수 있습니다.</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-12 col-center mw-1200 send_message_search_con">
				<div class="send_message_search_box get_person">
					<div class="send_message_box_title">받는사람 ID</div>
					<input type="text" id="userCodeTo" name ="userCodeTo" class="textbox_style_1" onkeyup="enterKeyEvent();">
				</div>
				<div class="send_message_search_box send_title">
					<div class="send_message_box_title">쪽지 제목</div>
					<input type="text" id="messageTitle" name="messageTitle" class="textbox_style_1" maxlength="30" onkeyup="enterKeyEvent();">
				</div>
				<div class="send_message_search_box get_search_btn">
					  <input type="button" value="검색" class="btn_style_1" onclick="searchMessageList()" >
				</div>
		</div>
		
		
		<div class="send_message_list_grid_con col-12 col-center mw-1200 " >  
	  			<div data-ax5grid="messageListGrid" data-ax5grid-config="{}" style="height:520px; padding-top:10px; padding-right:10px"></div>  
		</div>
		
		<div class="col-12 col-center mw-1200  message_btn">
				<input type="button" value="쪽지 삭제하기" class="btn_style_1 type_3" onclick="deleteSendMessage();">
		</div>
		
	</div>


        
</body>
</html> 