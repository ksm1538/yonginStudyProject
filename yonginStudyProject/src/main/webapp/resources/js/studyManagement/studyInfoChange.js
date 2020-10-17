/** 변수 설정(시작) **/  
var studyInfoChangeBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
/** 변수 설정(끝) **/

$(document).ready(function () {
	// ID 입력 폼의 값 변경 시, 중복체크 여부 N

	//summernote editor
	$('#studyDesc').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
	// DIV안에 있는 요소 비활성화
	$("#studyTopicDiv *").prop("disabled", true);
	
	studyInfoChangeBinder.setModel({}, $(document["studyInfoChangeForm"]));
	
	selectStudyInfoView(parentData.studyCode);
});

// 부모 페이지에서 받은 studyCode를 이용해 스터디 정보 조회
function selectStudyInfoView(studyCode){
	if(studyCode==null || studyCode == ""){
		dialog.alert("정상적인 접근이 아닙니다.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/studyManagement/selectStudyInfoView.json",
 		data : studyCode,
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	studyInfoChangeBinder.setModel(data.boardInfo);
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

//스터디 정보 변경 함수
function studyInfoChange(){
	
	var sendData={
		studyCode:parentData.studyCode,
		studyLimit:$('#studyLimit').val(),
		studyDesc:$('#studyDesc').val()
	}

     $.ajax({
	     type: "POST",
	     url : "/studyManagement/studyInfoChange.json",
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



function closeModal(){
	return self.parent.closeStudyChangeInfoModal();		// 부모 페이지의 closeChangePwModal함수로 리턴
}