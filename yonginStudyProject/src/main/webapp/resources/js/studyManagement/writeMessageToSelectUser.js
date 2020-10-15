/** 변수 설정(시작) **/
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
var status="init";
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	//summernote editor
	$('#messageDesc').summernote({           
	    height: 250,    
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
	$('#userCodeTo').val(parentData.userId);
});
/** 초기화(끝) **/

function sendMessage(){
	var sendData={
		userCodeTo:document.getElementById("userCodeTo").value,
		messageTitle:document.getElementById("messageTitle").value,
		messageDesc:document.getElementById("messageDesc").value
	}
	
	  $.ajax({
	     type: "POST",
	     url : "/sendMessage.json",
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
			        	if(this.key=="yes" || this.state == "close"){
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
	return self.parent.close();		// 부모 페이지의 close함수로 리턴
}
