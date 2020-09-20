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

   <div class="col-12 mypage_content_wrap">
   		<div class="side_fixed_menu_con">
			<div class="side_fixed_menu_list_con">
				<ul class="side_fixed_menu_list">
					<li><a><img src="/resources/img/arrow.png" class="side_arrow_img" id="Movetop"></a></li>
					<li><a><img src="/resources/img/info.png" class="side_myinfo_img" id="side_movelist1"></a></li>
					<li><a><img src="/resources/img/mystudy.png" class="side_mystudy_img" id="side_movelist2"></a></li>
					<li><a><img src="/resources/img/mymake.png" class="side_mymake_img" id="side_movelist3"></a></li>
					<li><a><img src="/resources/img/myapply.png" class="side_myapply_img" id="side_movelist4"></a></li>
				</ul>
			</div>
			<div class="tc side_fixed_menu_title">빠른 이동</div>			
		</div> 
		<div class="mypage_content_img"></div>
		<div class="mypage_content_dim"></div>
		
		<div class="col-6 mymage_left_content_con">
			<div class="tc mypage_left_title">마이페이지</div>
			<div class="tc mypage_left_subtitle">한눈에 알아보는 나의 정보</div>
			<div class="tc mypage_left_subtitle2">우측 버튼을 클릭해서 원하는 정보를 확인하세요</div>
		</div>
		<div class="col-6 mymage_right_content_con">
			<div class="right_content_con">
				<div class="right_content" id="listMove1">
					<div class="right_content_img_con"><img src="../resources/img/info.png" class="info_img"></div>
					<div class="right_content_title">개인 정보 수정</div>
					<div class="right_content_subtitle">보안을 위해 주기적으로 바꿔요 </div>
				</div>
				<div class="right_content" id="listMove2">
					<div class="right_content_img_con"><img src="../resources/img/mystudy.png" class="mystudy_img"></div>
					<div class="right_content_title">참여한 스터디</div>
					<div class="right_content_subtitle">내가 참여한 스터디를 보여줘요 </div>
				</div>
			</div>
			<div class="right_content_con">
				<div class="right_content" id="listMove3">
					<div class="right_content_img_con"><img src="../resources/img/mymake.png" class="mymake_img"></div>
					<div class="right_content_title">만든 스터디</div>
					<div class="right_content_subtitle">내가 만든 스터디를 보여줘요 </div>
				</div>
				<div class="right_content" id="listMove4">
					<div class="right_content_img_con"><img src="../resources/img/myapply.png" class="myapply_img"></div>
					<div class="right_content_title">신청 현황</div>
					<div class="right_content_subtitle">내가 신청한 스터디를 보여줘요 </div>
				</div>
			</div>
		</div>
	</div>	
	
	<div class="col-12 mypage_myinfo_wrap" id="list1">
		<div class="col-12 col-center mw-1200 mypage_myinfo_con">
			<div class="mypage_myinfo_title_con">
				<div class="mypage_myinfo_title"><span>개인 정보 수정</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri"></div>
						<div class="question_desc">개인 정보 수정은 비밀번호와 이메일, 주소 등 개인정보를 쉽게 변경 할 수 있습니다.</div>
					</div>
				</div>
			</div>
			
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
	
	<div class="col-12 mypage_mystudy_wrap" id="list2">
		<div class="col-12 col-center mw-1200 mypage_mystudy_con">
			<div class="mypage_mystudy_title_con">
				<div class="mypage_mystudy_title"><span>내가 참여한 스터디</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri type_2"></div>
						<div class="question_desc type_2">현재 본인이 참가하고 있는 스터디의 목록입니다. 목록클릭시 상세보기가 가능합니다.</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-12 col-center mw-1200 my_participate_study"style="width: 100%;" >  
	  			<div data-ax5grid="myParcitipateListGrid" data-ax5grid-config="{}" class="color_grid" style="height:300px; padding-top:10px; padding-right:10px"></div>  
		</div>
	</div>
	
	<div class="col-12 mypage_mymake_wrap" id="list3">
		<div class="col-12 col-center mw-1200 mypage_mymake_con">
			<div class="mypage_mymake_title_con">
				<div class="mypage_mymake_title"><span>내가 만든 스터디</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri"></div>
						<div class="question_desc">현재 본인이 참가하고 있는 스터디의 목록입니다. 목록클릭시 상세보기가 가능합니다.</div>
					</div>
				</div>
			</div>
		</div>
		
		 <div class="col-12 col-center mw-1200 my_make_study"style="width: 100%;" >  
	  			<div data-ax5grid="myMakeListGrid" data-ax5grid-config="{}" style="height:300px; padding-top:10px; padding-right:10px"></div>  
		</div>
	</div>
	
	<div class="col-12 mypage_myapply_wrap" id="list4">
		<div class="col-12 col-center mw-1200 mypage_myapply_con">
			<div class="mypage_myapply_title_con">
				<div class="mypage_myapply_title"><span>나의 스터디 현황</span></div>
				<div class="question_mark_con">
					<div class="tc question_mark"><span>?</span></div>
					<div class="qestion_desc_box_con">
						<div class="question_tri type_2"></div>
						<div class="question_desc type_2">현재 본인이 신청한 스터디 목록입니다. 클릭시 신청 여부와 상세정보를 알수 있습니다.</div>
					</div>
				</div>
			</div>
		</div>
		
		 	<div class="col-12 col-center mw-1200 my_make_study"style="width: 100%;" >  
	  				<div data-ax5grid="myApplicationFormGrid" data-ax5grid-config="{}" class="color_grid" style="height:300px; padding-top:10px; padding-right:10px"></div>  
			</div>
	</div>
	
	
	
   <%-- 
      
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
      
       --%>
</body>
</html>