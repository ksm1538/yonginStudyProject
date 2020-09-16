/** 변수 선언(시작) **/

/** 변수 선언(끝) **/

/** 초기화 (시작) **/
$(document).ready(function () {
	
});
/** 초기화 (끝) **/
// 이메일을 이용해 찾기
function usingEmail(){
	$.ajax({
	     type: "POST",
	     url : "/findPwUsingEmail.json",
	     dataType: "json",
	     contentType: "charset=UTF-8", 
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

// 임시비밀번호를 이용해 찾기
function usingHint(){
	window.location.replace("/findPwUsingHintForm.do");
}
