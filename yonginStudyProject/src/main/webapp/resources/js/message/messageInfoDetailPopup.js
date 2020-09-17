/** 변수 설정(시작) **/
var messageInfoDetailBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
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
	
	messageInfoDetailBinder.setModel({}, $(document["messageInfoDetailForm"]));
	
	selectMessageInfoDetail(parentData.messageCode);
	
});
/** 초기화(끝) **/

// 부모 페이지에서 받은 studyCode를 이용해 스터디 정보 조회
function selectMessageInfoDetail(messageCode){
	if(messageCode == ""){
		dialog.alert("정상적인 접근이 아닙니다.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/message/selectMessageInfoDetail.json",
 		data : messageCode,
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			
			switch(data.result){
			    case COMMON_SUCCESS:
			    	messageInfoDetailBinder.setModel(data.messageInfo);
			    	$('#messageDesc').summernote('code', data.messageInfo.messageDesc);
			    	
			    	// 쪽지 보낸사람이 현재 접속한 사용자 일 때
			    	if(rgstusIdCode != data.studyInfo.messageCodeFrom){
			    		// 쪽지 보내기로 바꿔주고
			    	}
			    	// 쪽지 받은사람이 현재 접속한 사용자 일 때 
			    	else if(rgstusIdCode == data.studyInfo.messageCodeFrom){
			    		//답장하기로 바꿔주고
			    	}
			    	
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

// 팝업창 닫기
function closeModal(){
	return self.parent.close();		// 부모 페이지의 close함수로 리턴
}
