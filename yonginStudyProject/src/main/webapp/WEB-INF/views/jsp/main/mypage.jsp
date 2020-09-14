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
<jsp:include page="../common/header.jsp"></jsp:include> 

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/myPage.js"></script>


<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
      
      <div class="col-12 col-center mw-1200 mypage_content_wrap">
      	<div class="mypage_top_btn_con"><a id="Movetop"><img src="/resources/img/arrow.png"></a></div>
      	
      	
         <div class="col-3 mypage_list_con" style="height:200vh">
            <div class="mypage_list_title">마이페이지</div>
            <ul class="mypage_list">
               <li><a id="listMove1">개인 정보 수정</a></li>
               <li><a id="listMove2">내가 참여한 스터디</a></li>
               <li><a id="listMove3">내가 만든 스터디</a></li>
            </ul>
         </div>
         
         <div class="col-9 mypage_content_con">
            <div class="col-12 user_info_modify">
               <div class="mypage_content_title" id="list1"><span>개인 정보 수정</span></div>
               <div class="modify_line_con">
                  <div class="modify_line">
                     <div class="modify_name modify_con">
                     <div class="title_size type_5">이름</div>
                     <input type="text" id="userName" class="textbox_style_1">
                     </div>
               
                     <div class="modify_phonenumber modify_con">
                     <div class="title_size type_5">전화번호</div>
                     <input type="text" id="userPhoneNumber" class="textbox_style_1">
                     </div>
                  </div>
               
                  <div class="modify_line">
                     <div class="modify_id modify_con">
                     <div class="title_size type_5">아이디</div>
                     <input type="text" id="userId" class="textbox_style_1">
                     </div>
                  
                     <div class="modify_pw modify_con">
                     <div class="title_size type_5">비밀번호</div>
                     <input type="text" id="userPw" class="textbox_style_1">
                     </div>
                  
                  </div>
               </div>
               <div class="tc modify_btn_con">
                  <input class="btn_style_1" type="button" value="수정하기">
               </div>
            </div>
         </div>
         
         
         <div class="col-9 mypage_content_con">
            <div class="col-12 my_participate_list">
               <div class="mypage_content_title" id="list2"><span>내가 참여한 스터디</span></div>
               
               	<div class="my_participate_study"style="width: 100%;" >  
	  				<div data-ax5grid="myParcitipateListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
				</div>
               
            </div>
         </div>
         
         
         
        <div class="col-9 mypage_content_con">
            <div class="col-12 my_make_list">
               <div class="mypage_content_title" id="list3"><span>내가 만든 스터디</span></div>
               
               	<div class="my_make_study"style="width: 100%;" >  
	  				<div data-ax5grid="myMakeListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
				</div>
               
               
               <div class="tc modify_btn_con">
                  <input class="btn_style_1" type="button" value="관리하기 " onclick="openstudyManagementForm();">
               </div>
               
            </div>
         </div>
         
      
      </div>
      
      
</body>
</html>