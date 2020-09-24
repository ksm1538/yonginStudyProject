
$(document).ready(function () {
	//summernote editor
	$('#systemNoticeDesc').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	

});

// 공지사항 작성 함수
function makeSystemNoticeFunc(){

	var sendData = {
		systemNoticeTitle:document.getElementById("systemNoticeTitle").value,
		systemNoticeDesc:document.getElementById('systemNoticeDesc').value
	}
	
	console.log(sendData); 
	  $.ajax({
	     type: "POST",
	     url : "/notice/makeSystemNotice.json",
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
								opener.location.reload();
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