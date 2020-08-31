function openRegisterForm(){
	window.open("/login/registerForm.do",'회원가입','width=700px ,height=900px ,location=no,status=no,scrollbars=no');
}

/*function openFindIdForm(){
	'findID.jsp','아이디 찾기','width=700px ,height=500px ,location=no,status=no,scrollbars=no'
}*/

/*function openFindPwForm(){
	'findPassword.jsp','비밀번호 찾기','width=700px ,height=600px ,location=no,status=no,scrollbars=no'
}*/


function a1(){
	var sendData="1";

	  $.ajax({
	      type: "POST",
	      url : "/login/testAjax.json",
	     data: sendData,
	     dataType: "json",
	     contentType: "application/json; charset=UTF-8",
	     async: false,
	     success : function(data, status, xhr) {
	        console.log(data);
	     },
	     error: function(jqXHR, textStatus, errorThrown) {
	        alert('error = ' + jqXHR.responseText);
	     }
	  }); 
}


 function a2(){
	var sendData="2";

	  $.ajax({
	      type: "POST",
	      url : "/login/testAjax.json",
	     data: sendData,
	     dataType: "json",
	     contentType: "application/json; charset=UTF-8",
	     async: false,
	     success : function(data, status, xhr) {
	        console.log(data);
	     },
	     error: function(jqXHR, textStatus, errorThrown) {
	        alert('error = ' + jqXHR.responseText);
	     }
	  }); 
}