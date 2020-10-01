<!------ JSP 설정(시작) ------>
<!-- 한글 설정(시작) -->
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- 해당 페이지 js 호출 -->
<script type="text/javascript" src="/resources/js/common/header.js"></script>
</head>
<body> 
  <div class="col-12 header_wrap">
        <div class="col-12 col-center mw-1200 header_con">
                <div class="header_menu_con">
                    <ul class="header_menu">
                        <li><a onclick="openHome();">HOME</a></li>
                        <li><a onclick="openMoreStudyForm();">스터디목록</a></li>                      
                        <!-- <li><a onclick="makeStudyForm();">스터디만들기</a></li> -->
                        <li><a onclick="openMoreNotice();">공지사항</a></li>
                        <li><a onclick="openQna();">Q&A</a></li>
                    </ul>
                </div>
                
                <div class="header_member_con">
                    <div class="header_user"><span class="user_id">${user.userId}</span>님 환영합니다.</div>
                    <div class="header_mypage"><a onclick="openMypageForm();">마이페이지</a></div>
                    <div class="header_message"><a onclick="openMessageForm();">쪽지함</a></div>
                    <div class="logout"><a href="/logout.json">로그아웃</a></div>
              		<div class="admin_page">
              			<c:if test="${user.userIsAdmin == 'Y'}">
                    	  <div class="admin_page_click"><a onclick="openAdminPage();">관리자 페이지</a></div>
                   		</c:if>
              		</div>
                    
                
                    
                </div>
        </div>
      </div>
</body>
</html>