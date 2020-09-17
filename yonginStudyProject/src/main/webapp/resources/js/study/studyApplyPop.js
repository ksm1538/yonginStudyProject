/** 변수 설정(시작) **/
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	//summernote editor
	$('#applicationFormDesc').summernote({           
	    height: 250,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
});
/** 초기화(끝) **/

// 신청하기 버튼
function applyStudy(){
	var sendData = {
			title:$('#title').val(),
			applicationFormDesc:$('#applicationFormDesc').val(),
			studyCode:parentData.studyCode
	}
	
	$.ajax({
 		type: "POST",
 		url : "/study/applyStudy.json",
 		data : JSON.stringify(sendData),
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
			        		closeModal();
			        	}
			    	});
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

//팝업창 닫기
function closeModal(){
	return self.parent.close();		// 부모 페이지의 close함수로 리턴
}