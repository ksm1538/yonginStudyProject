<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/studyManagement/studyHeader.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title> 
</head>
<body>
<div class="col-12 header_wrap">
        <div class="col-12 col-center mw-1200 header_con">
                <div class="header_menu_con">
                    <ul class="header_menu">
                        <li><a onclick="openHome();"><span id="spanStudyName"></span></a></li>
                        <li><a onclick="openStudyNotice();">공지사항</a></li>
                        <li><a onclick="openStudyFreeNotice();">자유게시판</a></li>
                    </ul>
                </div>
                
                <div class="header_member_con">
                    <div class="header_user"><span class="user_id">${user.userId}</span>님 환영합니다.</div>
                    <div class="header_mypage"><a onclick="openMypageForm();">마이페이지</a></div>
                    <div class="header_message"><a onclick="openMessageForm();">쪽지함</a></div>
                    <div class="logout"><a href="/logout.json">로그아웃</a></div>
              		<div class="admin_page">
              			<c:if test="${user.userIsAdmin == 'Y'}">
                    	  <div class="admin_page_click"><a onclick="openStudyAdminPage();">스터디 관리하기</a></div>
                   		</c:if>
              		</div>
                    
                </div>
                
                <!-- 해당 스터디 정보를 가지는 form -->
				<form id="dataForm" name="dataForm">
					<input type="hidden" id="studyCode" name="studyCode" value="<%=request.getParameter("studyCode")%>">
					<input type="hidden" id="studyName" name="studyName" value="<%=request.getParameter("studyName")%>">
				</form>
        </div>
      </div>
</body>
</html> 