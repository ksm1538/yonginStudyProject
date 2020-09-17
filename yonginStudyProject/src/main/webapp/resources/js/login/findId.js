/** 변수 선언(시작) **/

/** 변수 선언(끝) **/

/** 초기화 (시작) **/
$(document).ready(function () {

	
});
/** 초기화 (끝) **/

// ID찾기
function findId(){
	var sendData = $('#findIdForm').serialize();		// validator를 이용할 경우 serialize를 이용해 데이터 수집

	  $.ajax({
	     type: "POST",
	     url : "/findId.json",
	     data: sendData,
	     dataType: "json",
	     //contentType: "application/json; charset=UTF-8", (validator를 이용할 경우 contentType은 주석처리)
	     async: false,
	     success : function(data, status, xhr) {
	    	 switch(data.result){
	    	 case COMMON_SUCCESS:
	    		 dialog.confirm({
			    		msg:data.message + "\n ID : " + data.resultData,
			        	btns:{
			        		yes: {
			        			label:'확인'
			        		},
			        	}
			        }, function(){
			        	if(this.key=="yes"  || this.state == "close"){
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