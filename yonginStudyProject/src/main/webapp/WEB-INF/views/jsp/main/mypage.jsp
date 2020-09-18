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
<jsp:include page="../common/header.jsp"></jsp:include> 

<!-- 주소 API javascript 호출(주소 사용하는 곳만 추가) -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/main/myPage.js"></script>
<script>
var currentUserEmail = '${currentUser.userEmail}';

//그리드에 출력될 스터디 신청서 상태 코드 값 설정
var applicationFormStatusList = [
    <c:forEach var="result" items="${applicationFormStatusArray}" varStatus="status">
        {codeId:"${result.codeId}", codeValue:"${result.codeValue}"}<c:if test="${!status.last}">,</c:if>
    </c:forEach> 	
    ];

var applicationFormStatusMap = {};
applicationFormStatusList.forEach(function(n){
	applicationFormStatusMap[n.codeId] = n.codeValue;
}); 

//그리드에 출력될 스터디 주제 코드 값 설정
var studySxnList = [
    <c:forEach var="result" items="${studyTopicArray}" varStatus="status">
        {codeId:"${result.codeId}", codeValue:"${result.codeValue}"}<c:if test="${!status.last}">,</c:if>
    </c:forEach> 	
    ];

var studySxnMap = {}; 
studySxnList.forEach(function(n){
	studySxnMap[n.codeId] = n.codeValue;
}); 
</script>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
      
      <div class="col-12 col-center mw-1200 mypage_content_wrap">
      	<div class="mypage_top_btn_con"><a id="Movetop"><img src="/resources/img/arrow.png"></a></div>
      	
      	
         <div class="col-3 mypage_list_con" style="height:265vh">
            <div class="mypage_fixed_list">
            	<div class="mypage_list_title">마이페이지</div>
            	<ul class="mypage_list">
               		<li><a id="listMove1">개인 정보 수정</a></li>
               		<li><a id="listMove2">내가 참여한 스터디</a></li>
              		<li><a id="listMove3">내가 만든 스터디</a></li>
               		<li><a id="listMove4">나의 스터디 신청 현황</a></li>
           		</ul>
            </div>
            
         </div>
         
         <div class="col-9 mypage_content_con" id="list1">
            <div class="col-12 user_info_modify">
               <div class="mypage_content_title" ><span>개인 정보 수정</span></div>
               
               <form:form method="POST" modelAttribute="userInfoVO" name="changeInfoForm" id="changeInfoForm">
                    <div class="col-6 modify_line_left_con">
               			<div class="modify_name modify_con first_line_right">
                     		<div class="title_size type_5">이름</div>
                    		<form:input path ="userName" type="text" id="userName" class="textbox_style_1" value='${currentUser.userName }' readonly="true"/>
                     	</div>
                      	<div class="modify_id modify_con">
                   	 		<div class="title_size type_5">아이디</div>
                   	 		<form:input path ="userId" type="text" id="userId" class="textbox_style_1" value='${currentUser.userId }' readonly="true"/>
                   	 		<input class="btn_style_1 type_2" type="button" value="비밀번호 변경" onclick="changePw()">
               			</div>
                		 <div class="modify_phonenumber modify_con">
					 		<div class="title_size type_5">이메일</div>
					 		<div class="register_input_con">
								<form:input path="userEmail" type="text" name="userEmail" id="userEmail" class="textbox_style_1" value='${currentUser.userEmail }'/>
								<input type="button" value="인증번호 전송" id="initSendMailBtn" class="btn_style_1 type_2" onclick="sendAuthCode()" >
								<input type="button" value="재전송" id="reSendMailBtn"  class="btn_style_1" onclick="sendAuthCode()" >
								<input type="button" value="이메일 변경" id="resetMailBtn"  class="btn_style_1" onclick="resetAuthCode()" >
					 		</div>
						</div>	
               	   </div>    	                        	               	
               	   <div class="col-6 modify_line_right_con">
               	  		<div class="modify_phonenumber modify_con first_line_left">
                     		<div class="title_size type_5">전화번호</div>
                     		<form:input path="userPhoneNumber" type="text" id="userPhoneNumber" class="textbox_style_1" value='${currentUser.userPhoneNumber }' maxlength="13"/>
                    	</div>
                    	<div class="modify_phonenumber modify_con">
                     		<div class="title_size type_5">주소</div>
                     		<form:input path="userAddress" type="text" name="userAddress" id="userAddress" class="textbox_style_1" readonly="true" placeholder="주소 검색을 클릭하세요." value='${currentUser.userAddress }' />
					 		<input type="button" value="주소 검색" class="btn_style_1 type_2" onclick="addressPopup()" >
               			</div>
                       
            	    </div>
            	    
            	  <div class="tc col-12 modify_btn_con">
                  	<input class="btn_style_1" type="button" value="수정하기" onclick="changeUserInfo()">
           	  	  </div>               	                            	                			
               </form:form>              
             </div>                           
         </div>
          
        
         
         
         <div class="col-9 mypage_content_con" id="list2">
            <div class="col-12 my_participate_list">
               <div class="mypage_content_title" ><span>내가 참여한 스터디</span></div>
               
               	<div class="my_participate_study"style="width: 100%;" >  
	  				<div data-ax5grid="myParcitipateListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
				</div>
               
            </div>
         </div>
         
         
         
        <div class="col-9 mypage_content_con" id="list3">
            <div class="col-12 my_make_list">
               <div class="mypage_content_title" ><span>내가 만든 스터디</span></div>
               
               	<div class="my_make_study"style="width: 100%;" >  
	  				<div data-ax5grid="myMakeListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
				</div>
               
            </div>
         </div>
         
         <div class="col-9 mypage_content_con" id="list4">
            <div class="col-12 my_apply_list">
               <div class="mypage_content_title" ><span>나의 스터디 신청 현황</span></div>
               
               	<div class="my_make_study"style="width: 100%;" >  
	  				<div data-ax5grid="myApplicationFormGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
				</div>
               
            </div>
         </div>
         
      
      </div>
      
      
</body>
</html>