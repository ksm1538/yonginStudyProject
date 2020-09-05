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
<%-- <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/jquery/jquery.js" />" rel="stylesheet">
<script src="<c:url value="/resources/js/main.js" />"></script>
<script src="<c:url value="/resources/js/myPage.js" />"></script>

 --%>



<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/jquery-ui/jquery-ui.css" />" rel="stylesheet">
<%-- <link href="<c:url value="/resources/util/bootstrap-3.4.1-dist/css/bootstrap.min.css" />" rel="stylesheet"> --%>
<link href="<c:url value="/resources/util/ax5ui-dialog-master/dist/ax5dialog.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/ax5ui-mask-master/dist/ax5mask.css" />" rel="stylesheet">
<link href="<c:url value="/resources/util/ax5ui-grid-master/dist/ax5grid.css" />" rel="stylesheet">



<script src="<c:url value="/resources/util/jquery-ui/jquery-1.12.4.min.js" />"></script>
<%-- <script src="<c:url value="/resources/util/bootstrap-3.4.1-dist/js/bootstrap.min.js" />"></script> --%>

<!-- 그리드 -->
<script src="<c:url value="/resources/util/ax5core-master/dist/ax5core.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5ui-dialog-master/dist/ax5dialog.min.js" />"></script>
<script src="<c:url value="/resources/util/ax5ui-mask-master/dist/ax5mask.min.js" />"></script>

<script src="<c:url value="/resources/js/constant.js" />"></script>

<!-- 그리드 -->
<script src="<c:url value="/resources/util/ax5ui-grid-master/dist/ax5grid.min.js" />"></script> 

<script src="<c:url value="/resources/js/myPage.js" />"></script>

<!---- css, js 설정(끝) ---->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="col-12 point_bg0 header_wrap">
        <div class="col-12 col-center mw-1200 header_con">
                <div class="header_menu_con">
                    <ul class="header_menu">
                        <li><a>HOME</a></li>
                        <li><a>스터디목록</a></li>
                        <li><a>공지사항</a></li>
                    </ul>
                </div>
                
                <div class="header_member_con">
                    <div class="header_user"><span class="user_id">용인대학교</span>님 환영합니다.</div>
                    <div class="logout">로그아웃</div>
                    
                    <div class="user_box_con">
                       <div class="circle_btn_2"></div>
                       <div class="user_box_tri"></div>
                       <div>
                           <sul class="user_box">
                                 <li><a onclick="openMypageForm();">마이페이지</a></li>
                                <li><a>쪽지함</a></li>
                             </ul>
                       </div>
                    </div>
                </div>
        </div>
      </div>
      
      <div class="col-12 col-center mw-1200 mypage_content_wrap">
         <div class="col-3 mypage_list_con" style="height:100vh">
            <div class="mypage_list_title">마이페이지</div>
            <ul class="mypage_list">
               <li><a>개인 정보 수정</a></li>
               <li><a>내가 참여한 스터디</a></li>
               <li><a>내가 만든 스터디</a></li>
            </ul>
         </div>
         
         <div class="col-9 mypage_content_con">
            <div class="col-12 user_info_modify">
               <div class="mypage_content_title"><span>개인 정보 수정</span></div>
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
               <div class="mypage_content_title"><span>내가 참여한 스터디</span></div>
               
               	<div style="width: 100%;" >  
	  				<div data-ax5grid="studyNoticeListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
				</div>
               
            </div>
         </div>
         
         
         
             <div class="col-9 mypage_content_con">
            <div class="col-12 my_make_list">
               <div class="mypage_content_title"><span>내가 만든 스터디</span></div>
               
               <div class="tc modify_btn_con">
                  <input class="btn_style_1" type="button" value="관리하기 " onclick="openstudyManagementForm();">
               </div>
               
            </div>
         </div>
         
      
      </div>
      
      
</body>
</html>