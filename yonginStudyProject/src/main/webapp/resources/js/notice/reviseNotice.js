/** 변수 설정(시작) **/
var systemNoticeInfoDetailBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	//summernote editor
	$('#systemNoticeDesc').summernote({           
	    height: 250,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true,
		toolbar:[]
	});	
	
	systemNoticeInfoDetailBinder.setModel({}, $(document["reviseNoticeForm"]));
	
	reviseSystemNotice(parentData.systemNoticeCode);
	
});
/** 초기화(끝) **/

// 부모 페이지에서 받은 systemNoticeCode를 이용해 공지사항 조회
function reviseSystemNotice(systemNoticeCode){
	if(systemNoticeCode == ""){
		dialog.alert("정상적인 접근이 아닙니다.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/notice/selectReviseSystemNotice.json",
 		data : systemNoticeCode,
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	systemNoticeInfoDetailBinder.setModel(data.systemNoticeInfo);
			    	$('#systemNoticeDesc').summernote('code', data.systemNoticeInfo.systemNoticeDesc);
			    	
			    	break;    
			    case COMMON_FAIL:
			    	dialog.alert(data.message); 
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log('error = ' + jqXHR.responseText + 'code = ' + errorThrown);
		}
	}); 
}

// 공지사항 수정 함수
function reviseSystemNoticeFunc(){
	var sendData = {
		systemNoticeTitle:document.getElementById("systemNoticeTitle").value,
		systemNoticeDesc:document.getElementById("systemNoticeDesc").value,
		systemNoticeCode:parentData.systemNoticeCode
	}
	
	console.log(sendData); 
	  $.ajax({
	     type: "POST",
	     url : "/notice/reviseSystemNotice.json",
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
								window.opener.location.reload();
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
	return self.parent.closeSystemNoticeInfo();		// 부모 페이지의 close함수로 리턴
}

// 팝업창 닫고 부모페이지 새로고침
function closeModalRefresh(){
	return self.parent.closeApplcationFormModalRefresh();		
}
