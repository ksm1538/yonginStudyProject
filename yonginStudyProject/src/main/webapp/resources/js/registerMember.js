function registerMemberFunc(){
	
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
	        if(data.result = "success"){
				alert(data.message);
			}
	     },
	     error: function(jqXHR, textStatus, errorThrown) {
	        alert('error = ' + jqXHR.responseText);
	     }
	  }); 
}