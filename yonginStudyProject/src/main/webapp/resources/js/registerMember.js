function registerMemberFunc(){
	
	var sendData={
		userName:document.getElementById("userName").value,
		userNumber_1:document.getElementById("userNumber_1").value,
		userNumber_2:document.getElementById("userNumber_2").value,
		userPhoneNumber:document.getElementById("userPhoneNumber").value,
		userId:document.getElementById("userId").value,
		userPw:document.getElementById("userPw").value,
		userPwConfirm:document.getElementById("userPwConfirm").value
		//userPwHint:${'#userPwHint'}.val(), 비밀번호힌트
		//userPwHintAwsr:${'#userPwHintAwsr'}.val(),
	}
	
	console.log(sendData);
	  $.ajax({
	     type: "POST",
	     url : "/registerMember/registerAjax.json",
	     data: JSON.stringify(sendData),
	     dataType: "json",
	     contentType: "application/json; charset=UTF-8",
	     async: false,
	     success : function(data, status, xhr) {
	        if(data.result = "success"){
				alert(data.message);
			}
	     },
	     error: function(jqXHR, textStatus, errorThrown) {
	        alert('error = ' + jqXHR.responseText);
	     }
	  }); 
}