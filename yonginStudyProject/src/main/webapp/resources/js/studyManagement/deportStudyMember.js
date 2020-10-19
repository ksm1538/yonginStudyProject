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
	$('#memberStudyName').val(parentData.studyName);
	
	self.parent.deportSize();
});
/** 초기화(끝) **/

function deportStudyMemberFunc(){
	var sendData={
		userCodeTo:document.getElementById("userCodeTo").value,
		studyCode:parentData.studyCode,
		messageTitle:document.getElementById("messageTitle").value,
		messageDesc:document.getElementById("messageDesc").value
	}
	
	  $.ajax({
	     type: "POST",
	     url : "/studyManagement/deportStudyMemberFunc.json",
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
			        		closeModalWithRefresh();
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

// 팝업창 닫고 새로고침
function closeModalWithRefresh(){
	self.parent.studyMemberManageCloseWithRefresh();
}
