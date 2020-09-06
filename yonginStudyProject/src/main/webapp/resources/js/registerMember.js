/** 변수 선언(시작) **/
var idFixYn = "N";
/** 변수 선언(끝) **/

/** 초기화 (시작) **/
$(document).ready(function () {
	// ID 입력 폼의 값 변경 시, 중복체크 여부 N
	$("#userId").on("propertychange change keyup paste input", function() {
	    idFixYn = "N";
    	document.getElementById("idYnIcon").className = "fa fa-times-circle";
	});
	
	
});
/** 초기화 (끝) **/

// 주소 검색 함수
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
	
	var sendData={
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
	}
	
	console.log(sendData); 
	  $.ajax({
	     type: "POST",
	     url : "/registerMember.json",
	     data: JSON.stringify(sendData),
	     dataType: "json",
	     contentType: "application/json; charset=UTF-8",
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

// 중복체크 함수
function IdCheckFunc(){
	var sendData = $('#userId').val();
	
	// 아이디 앞뒤 공백 제거
	sendData = sendData.trim();
	$('#userId').val(sendData);
	
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