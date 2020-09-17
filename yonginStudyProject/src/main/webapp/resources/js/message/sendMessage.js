/** 초기화(시작) **/
$(document).ready(function () {
	//summernote editor
	$('#messageDesc').summernote({           
	    height: 150,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
});
/** 초기화(끝) **/

/**
 * 쪽지 보내기
 */
function sendMessage(){
	var sendData={
		userCodeTo:document.getElementById("userCodeTo").value,
		messageTitle:document.getElementById("messageTitle").value,
		messageDesc:document.getElementById("messageDesc").value
	}
	
	console.log(sendData); 
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