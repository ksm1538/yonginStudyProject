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

<!-- 헤더 필요X 생략 -->

<!-- 주소 API javascript 호출(주소 사용하는 곳만 추가) -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/registerMember.js"></script>

<title>회원가입</title>
</head>
<body>

<div class="col-12 col-center mw-1200 search_memeber_title_wrap">

	<div class="circle_btn" onClick="self.close()"></div> 
	<div class="tc search_memeber_title_con">
		<div class="search_memeber_title">회원 가입</div>
	</div> 
	
	
</div>

	<div class="col-12 col-center mw-1200 search_memeber_form_wrap">
			<div class="register_member_form_con">
				<div class="user_id search_member_form_con">
					<div class="title_size type_2"> 아이디</div>
					<input type="text" name="userId" id="userId" class="textbox_style_1">
					<i class="fa fa-times-circle" aria-hidden="true" id="idYnIcon" style="margin-left:1%"></i>
					<input type="button" value="중복확인" id="checkIdBtn" class="btn_style_1" onclick="IdCheckFunc()">
				</div>
				
				<div class="user_pw search_member_form_con">
					<div class="title_size type_2">비밀번호</div>
					<input type="password" name="userPw" id="userPw" class="textbox_style_1">
				</div>
				
				<div class="user_pw_check search_member_form_con">
					<div class="title_size type_2">비밀번호 확인</div>
					<input type="password" name="userPwConfirm" id="userPwConfirm" class="textbox_style_1">
				</div>
				
				<div class="user_pw_hint search_member_form_con">
					<div class="title_size type_4">비밀번호 힌트</div>
					<select name="userPwHintCode" id="userPwHintCode" class="select_style_0" >
					    <c:forEach var="result" items="${pwHint}" varStatus="status">
				          	<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
				         </c:forEach>
					</select>
				</div>
				
				<div class="user_pw_ans search_member_form_con">
					<div class="title_size type_2">비밀번호 힌트 답</div>
					<input type="text" name="userPwHintAnswer" id="userPwHintAnswer" class="textbox_style_1">
				</div>
				
				<div class="user_name search_member_form_con">
					<div class="title_size type_2">이름</div>
					<input type="text" name="userName" id="userName" class="textbox_style_1">
				</div>
				 
				<div class="user_identity_num search_member_form_con">
					<div class="user_identity_title title_size type_2">주민번호</div>
					<input type="text" name="userNumber1" id="userNumber1" class="textbox_style_1 type_2">
					<input type="password" name="userNumber2" id="userNumber2" class="textbox_style_1 type_2">
				</div>
				
				<!-- 성별 라벨로 묶기 -->
				<div class="user_gender search_member_form_con">
					<div class="title_size type_2">성별</div>
					<div class="radio_btn_con"><input type="radio" name="userGender" value="m" class="radio_btn"><span>남성</span></div>
					<div class="radio_btn_con"><input type="radio" name="userGender" value="w" checked="checked" class="radio_btn"><span>여성</span></div>
				</div>
				
				
				<div class="user_phone search_member_form_con">
					<div class="user_phone_title title_size type_2">전화번호</div>
					<input type="text" name="userPhoneNumber" id="userPhoneNumber" class="textbox_style_1">
				</div>
				
				<div class="user_id search_member_form_con">
					<div class="title_size type_2">주소</div>
					<input type="text" name="userAddress" id="userAddress" class="textbox_style_1" readonly="true" placeholder="주소 검색을 클릭하세요.">
					<input type="button" value="주소 검색" class="btn_style_1" onclick="addressPopup()" >
				</div>
			</div>
	</div>
	
	<div class="col-12 col-center mw-1200 register_memeber_btn_wrap">
		<div class="btn_style_1_con">
			<input type="button" value="확인" class="btn_style_1" onclick="registerMemberFunc()" >
		</div> 
	</div>

  
</body>
</html>
