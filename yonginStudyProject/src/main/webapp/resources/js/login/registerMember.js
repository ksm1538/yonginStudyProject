/** 변수 선언(시작) **/
var idFixYn = "N";
var emailFixYn = "N";
/** 변수 선언(끝) **/

/** 초기화 (시작) **/
$(document).ready(function () {
	
	// ID 입력 폼의 값 변경 시, 중복체크 여부 N
	$("#userId").on("propertychange change keyup paste input", function() {
	    idFixYn = "N";
    	document.getElementById("idYnIcon").className = "fa fa-times-circle";
	});
	
	//전화번호 '-' 자동 붙이기
	var userPhoneNumber = document.getElementById('userPhoneNumber');

	userPhoneNumber.onkeyup = function(){
	  this.value = autoHypenPhone( this.value ) ;  
	}
	
	$("#emailCodeDiv").hide();	//	인증번호 확인 구역 숨김처리
	$("#reSendMailBtn").hide();	//  재전송 버튼 숨김처리
	$("#resetMailBtn").hide();	//  이메일 재설정 버튼 숨김처리
	
});
/** 초기화 (끝) **/

// 주소 검색 함수 세팅
function addressPopup() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('userAddress').value = data.sido + " " + data.sigungu;
        }
    }).open();
}


// 회원가입 함수
function registerMemberFunc(){
	if(idFixYn == "N"){
		dToast.push("ID 중복체크를 해주세요.");
		return;
	}
	if(emailFixYn == "N"){
		dToast.push("이메일 인증을 진행해주세요.");
		return;
	}
	
	/*var sendData={
		userName:document.getElementById("userName").value,
		userNumber1:document.getElementById("userNumber1").value,
		userNumber2:document.getElementById("userNumber2").value,
		userGender:$('input[name="userGender"]:checked').val(),
		userPhoneNumber:document.getElementById("userPhoneNumber").value,
		userId:document.getElementById("userId").value,
		userPw:document.getElementById("userPw").value,
		userPwConfirm:document.getElementById("userPwConfirm").value,
		userPwHintCode:$('#userPwHintCode option:selected').val(),
		userPwHintAnswer:$('#userPwHintAnswer').val(),
		userAddress:$('#userAddress').val()
	}*/
	var sendData = $('#registerForm').serialize();		// validator를 이용할 경우 serialize를 이용해 데이터 수집

	  $.ajax({
	     type: "POST",
	     url : "/registerMember.json",
	     data: sendData,
	     dataType: "json",
	     //contentType: "application/json; charset=UTF-8", (validator를 이용할 경우 contentType은 주석처리)
	     async: false,
	     success : function(data, status, xhr) {
	    	 switch(data.result){
	    	 case COMMON_SUCCESS:
	    		 dialog.confirm({
			    		msg:data.message,
			        	btns:{
			        		yes: {
			        			label:'확인'
			        		},
			        	}
			        }, function(){
			        	if(this.key=="yes"){
			        			window.close();
			        	}
			    	});
	    		 break;
	    	 case COMMON_FAIL:
	    		 dialog.alert(data.message);
	    		 break;
	    	 }
	     },
	     error: function(jqXHR, textStatus, errorThrown) {
	        alert('error = ' + jqXHR.responseText);
	     }
	  }); 
}

// ID 중복체크 함수
function IdCheckFunc(){
	var sendData = $('#userId').val();
	
	// 아이디 앞뒤 공백 제거
	sendData = sendData.trim();
	$('#userId').val(sendData);
	
	// ID 입력 확인
	if(sendData == ""){
		dToast.push("ID를 입력해주세요.");
		return;
	}
	
	// ID길이 확인
	if(sendData.length < 5 || sendData.length > 20){
		dToast.push("ID를 5~20자로 입력해주세요.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/register/checkExsitingId.json",
		data: sendData,
		dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
		    case COMMON_SUCCESS:
		    	sToast.push(data.message);
		    	
		    	idFixYn = "Y";
		    	document.getElementById("idYnIcon").className = "fa fa-check";
		    	break;
		    case COMMON_FAIL:
		    	dToast.push(data.message);
		    	break;	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('error = ' + jqXHR.responseText);
		}
	}); 
}

// 이메일 인증번호 전송 버튼
function sendAuthCode(){
	var userEmail = $('#userEmail').val();
	if(userEmail == ""){
		dToast.push("이메일을 입력해주세요.");
		return;
	}
	if(emailFixYn == "Y"){
		dToast.push("이미 이메일 인증이 완료되었습니다.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/register/sendEmailAuthCode.json",
		data: userEmail,
		dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: true,		// true 비동기통신으로 진행해 mask 사용
		beforeSend:function(){
			mask.open({
				content: '<h1><i class="fa fa-spinner fa-spin"></i> Loading...</h1>'
			});
	    },
	    complete:function(){
	    	mask.close();
	    },
		success : function(data, status, xhr) {
			switch(data.result){
		    case COMMON_SUCCESS:
		    	sToast.push(data.message);
		    	
		    	$("#userEmail").attr("readonly",true);
		    	
		    	$("#initSendMailBtn").hide();
		    	$("#emailCodeDiv").show();
		    	$("#reSendMailBtn").show();
		    	$("#resetMailBtn").show();
		    	break;
		    case COMMON_FAIL:
		    	dToast.push(data.message);
		    	break;	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('error = ' + jqXHR.responseText);
		}
	}); 
}

//이메일 인증번호 확인 버튼
function checkAuthCode(){
	var emailCode = $('#emailCode').val();
	if(emailCode == ""){
		dToast.push("인증번호를 입력해주세요.");
		return;
	}
	$.ajax({
 		type: "POST",
 		url : "/register/checkEmailAuthCode.json",
		data: emailCode,
		dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
		    case COMMON_SUCCESS:
		    	sToast.push(data.message);
		    	emailFixYn = "Y";
		    	
		    	$("#emailCode").attr("readonly",true);
		    	$("#authCodeBtn").val("인증완료");
		    	$("#authCodeBtn").prop("disabled", true);
		    	break;
		    case COMMON_FAIL:
		    	emailFixYn = "N";
		    	dToast.push(data.message);
		    	break;	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('error = ' + jqXHR.responseText);
		}
	}); 
}

// 이메일 재입력 함수
function resetAuthCode(){
	$.ajax({
 		type: "POST",
 		url : "/register/resetEmail.json",
		dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
		    case COMMON_SUCCESS:
		    	sToast.push(data.message);
		    	emailFixYn = "N";
		    	
		    	$("#userEmail").attr("readonly",false);
		    	$("#emailCode").attr("readonly",false);
		    	
		    	$("#userEmail").val("");
		    	
		    	$("#initSendMailBtn").show();
		    	$("#emailCodeDiv").hide();	
		    	$("#reSendMailBtn").hide();
		    	$("#resetMailBtn").hide();
		    	
		    	$("#authCodeBtn").val("인증하기");
		    	$("#authCodeBtn").prop("disabled", false);
		    	break;
		    case COMMON_FAIL:
		    	emailFixYn = "N";
		    	dToast.push(data.message);
		    	break;	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('error = ' + jqXHR.responseText);
		}
	}); 
}

var autoHypenPhone = function(str){
    str = str.replace(/[^0-9]/g, '');
    var tmp = '';
    if( str.length < 4){
        return str;
    }else if(str.length < 7){
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3);
        return tmp;
    }else if(str.length < 11){
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 3);
        tmp += '-';
        tmp += str.substr(6);
        return tmp;
    }else{              
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 4);
        tmp += '-';
        tmp += str.substr(7);
        return tmp;
    }

    return str;
}