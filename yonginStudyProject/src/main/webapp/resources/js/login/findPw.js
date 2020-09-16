/** 변수 선언(시작) **/

/** 변수 선언(끝) **/

/** 초기화 (시작) **/
$(document).ready(function () {
	$('[data-ax5formatter]').ax5formatter();
});
/** 초기화 (끝) **/

// Pw찾기
function findPw(){
	var sendData = $('#findPwForm').serialize();		// validator를 이용할 경우 serialize를 이용해 데이터 수집
	
	  $.ajax({
	     type: "POST",
	     url : "/findPw.json",
	     data: sendData,
	     dataType: "json",
	     //contentType: "charset=UTF-8", (validator를 이용할 경우 contentType은 주석처리)
	     async: false,
	     success : function(data, status, xhr) {
	    	 switch(data.result){
	    	 case COMMON_SUCCESS:
	    		 window.location.replace(data.url);
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

