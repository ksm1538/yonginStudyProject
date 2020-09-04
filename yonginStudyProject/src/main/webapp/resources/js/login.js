function openRegisterForm(){
	window.open("/registerForm.do",'회원가입','width=700px ,height=900px ,location=no,status=no,scrollbars=no');
}

/*function login(){
	var sendData={
		userId : document.getElementById("userId").value,
		userPw : document.getElementById("userPw").value
	}

	$.ajax({
	     type: "GET",
	     url : "/login.json",
	     data: JSON.stringify(sendData),
	     dataType: "json",
	     contentType: "application/json; charset=UTF-8",
	     async: false,
	     success : function(data, status, xhr) {
	     },
	     error: function(jqXHR, textStatus, errorThrown) {
	        alert('error = ' + jqXHR.responseText);
	     }
	}); 
}*/

/*function openFindIdForm(){
	'findID.jsp','아이디 찾기','width=700px ,height=500px ,location=no,status=no,scrollbars=no'
}*/

/*function openFindPwForm(){
	'findPassword.jsp','비밀번호 찾기','width=700px ,height=600px ,location=no,status=no,scrollbars=no'
}*/


