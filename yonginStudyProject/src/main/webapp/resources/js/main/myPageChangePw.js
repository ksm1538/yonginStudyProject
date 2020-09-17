/** 변수 선언(시작) **/
/** 변수 선언(끝) **/

/** 초기화 (시작) **/
$(document).ready(function () {	
});
/** 초기화 (끝) **/


// 비밀번호 변경
function changePw(){
	var sendData = $('#changePwForm').serialize();
	
	$.ajax({
	     type: "POST",
	     url : "/myPageChangePw.json",
	     data: sendData,
	     dataType: "json",
	     //contentType: "application/json; charset=UTF-8",
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
			        		closeModal();
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

// 팝업창 닫기
function closeModal(){
	return self.parent.closeChangePwModal();		// 부모 페이지의 closeChangePwModal함수로 리턴
}
