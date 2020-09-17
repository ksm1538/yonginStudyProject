/** 변수 설정(시작) **/
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
var studyApplicationFormDetailBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
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
	
	studyApplicationFormDetailBinder.setModel({}, $(document["applicationFormDetailPopupForm"]));
	
	selectStudyApplicationForm();
	
});
/** 초기화(끝) **/

// 스터디 신청서 정보 가져오기
function selectStudyApplicationForm(){
	var sendData = {
			studyCode:parentData.studyCode
	}
	
	$.ajax({
 		type: "POST",
 		url : "/study/selectStudyApplicationForm.json",
 		data : JSON.stringify(sendData),
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	studyApplicationFormDetailBinder(data.resultVO);
			    	$('#applicationFormDesc').summernote('code', data.resultVO.applicationFormDesc);
			    	
			    	if(data.resultVO.status != "10"){
			    		$("#detailDiv *").prop("disabled", true);
			    		$("#updateAFBtn").hide();
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

// 수정하기 버튼
function updateStudyApplicationForm(){
	var sendData = {
			title:$('#title').val(),
			applicationFormDesc:$('#applicationFormDesc').val(),
			applicationFormCode:studyApplicationFormDetailBinder.get("applicationFormCode")
	}
	
	$.ajax({
 		type: "POST",
 		url : "/study/updateStudyApplicationForm.json",
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
	return self.parent.closeApplicationModal();		// 부모 페이지의 close함수로 리턴
}