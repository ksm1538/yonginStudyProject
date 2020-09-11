/** 변수 선언(시작) **/

/** 변수 선언(끝) **/

/** 초기화 (시작) **/
$(document).ready(function () {
	
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
	     async: true,
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