/** 초기화(시작) **/
$(document).ready(function () {
	//summernote editor
	$('#messageDesc').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
	$('.message_main_title').text("쪽지쓰기");
	$('.message_sub_title').text("다른 회원들에게 쪽지를 보내보세요");
	
	var position = $("#send_message_top").offset();
    $('html, body').animate({scrollTop : position.top}, 800);
	
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
			        	if(this.key=="yes" || this.state == "close"){
			        		window.location.reload();
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