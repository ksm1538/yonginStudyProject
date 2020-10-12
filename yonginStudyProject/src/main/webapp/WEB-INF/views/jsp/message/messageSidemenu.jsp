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
<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<jsp:include page="../common/header.jsp"></jsp:include>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/message/messageSidemenu.js"></script>


<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
   
    <div class="col-12 meessage_box_wrap">
		
		
		<div class="message_content_img"></div>
		<div class="message_content_dim"></div>
		
		<div class="tc message_title_con">
			<div class="message_main_title">쪽지함</div>
			<div class="message_sub_title">다른 회원들에게 쪽지를 보내보세요</div>
			<div class="message_last_title">메뉴를 클릭 후 스크롤을 내려 확인하세요</div>
		</div>
		
		<div class="col-12 message_content_con">
			<div class="col-4 message_content_box">
				<div class="tc message_content write_message" onclick="openSendMessageForm();">
					<div class="message_img_box"><img src="/resources/img/write_message.png" class="box_content_img write_img"></div>
					<div class="tc message_content_main_title">쪽지 쓰기</div>
					<div class="tc message_content_sub_title">쪽지를 작성합니다.</div>
				</div>
			</div>
			<div class="col-4 message_content_box">
				<div class="tc message_content get_message" id="listMove1" onclick="openMessageForm();">
					<div class="message_img_box"><img src="/resources/img/getmessage.png" class="box_content_img get_img"></div>
					<div class="tc message_content_main_title">받은 쪽지함</div>
					<div class="tc message_content_sub_title">받은 쪽지를 확인합니다.</div>
				</div>
			</div>
			<div class="col-4 message_content_box">
				<div class="tc message_content send_message" onclick="openSendMessageListForm();" >
					<div class="message_img_box"><img src="/resources/img/sendmessage.png" class="box_content_img send_img"></div>
					<div class="tc message_content_main_title">보낸 쪽지함</div>
					<div class="tc message_content_sub_title">보낸 쪽지를 확인합니다.</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-12 write_message_wrap">
		<div class="col-12 col-center mw-12300 write_message_con"></div>
	</div>
      
    <!--   <div class="col-12 col-center mw-1200 message_page_list_wrap">
         <div class="col-3 message_page_list_con" style="height:100vh">
            <div class="message_list_title">쪽지함</div>
            <ul class="message_list">
               <li><a id="listMove1" onclick="openSendMessageForm();">쪽지보내기</a></li>
               <li><a id="listMove3" onclick="openMessageForm();">받은쪽지함</a></li>
               <li><a id="listMove2" onclick="openSendMessageListForm();">보낸쪽지함</a></li>
                
            </ul>
         </div> 
      </div> -->
      
      
</body>
</html>