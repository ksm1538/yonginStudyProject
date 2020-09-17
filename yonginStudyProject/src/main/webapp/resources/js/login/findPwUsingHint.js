/** 변수 선언(시작) **/
/** 변수 선언(끝) **/

/** 초기화 (시작) **/
$(document).ready(function () {	
});
/** 초기화 (끝) **/


// 비밀번호 힌트 답 확인
function findPwUsingHint(){
	var sendData = {
		userPwHintCode : $('#userPwHintCode').val(),
		userPwHintAnswer : $('#userPwHintAnswer').val()
	}
	
	$.ajax({
	     type: "POST",
	     url : "/findPwUsingHint.json",
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
			        	if(this.key=="yes"  || this.state == "close"){
			        		window.location.replace(data.url);
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
