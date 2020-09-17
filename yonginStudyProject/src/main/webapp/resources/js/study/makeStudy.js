/** 변수 선언(시작) **/
var studyNameFixYn = "N";
/** 변수 선언(끝) **/
$(document).ready(function () {
	// ID 입력 폼의 값 변경 시, 중복체크 여부 N
	$("#studyName").on("propertychange change keyup paste input", function() {
	    studyNameFixYn = "N";
    	document.getElementById("nameYnIcon").className = "fa fa-times-circle";
	});
	
	//summernote editor
	$('#studyDesc').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	

});


// 스터디 생성가입 함수
function makeStudyFunc(){
	if(studyNameFixYn == "N"){
		dToast.push("스터디 이름 중복체크를 해주세요.");
		return;
	}

	var sendData = $('#studyMakeForm').serialize();
	
	console.log(sendData); 
	  $.ajax({
	     type: "POST",
	     url : "/makeStudy.json",
	     data: sendData,
	     dataType: "json",
	     //contentType: "application/json; charset=UTF-8",
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

// 중복체크 함수
function studyNameCheckFunc(){
	var sendData = $('#studyName').val();
	
	// 스터디 이름 앞뒤 공백 제거
	sendData = sendData.trim();
	$('#studyName').val(sendData);
	
	// 스터디 이름 입력 확인
	if(sendData == ""){
		dToast.push("스터디 이름을 입력해주세요.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/make/checkExsitingStudyName.json",
		data: sendData,
		dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
		    case COMMON_SUCCESS:
		    	sToast.push(data.message);
		    	
		    	studyNameFixYn = "Y";
		    	document.getElementById("nameYnIcon").className = "fa fa-check";
		    	break;
		    case COMMON_FAIL:
		    	dToast.push(data.message);
		    	break;	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('error = ' + jqXHR.responseText);
		}
	}); 
} 

// 주소 검색 함수 세팅
function studyAddressPopup() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('studyArea').value = data.sido + " " + data.sigungu;
        }
    }).open();
}

// 팝업 닫기
function closeModal(){
	self.parent.closeMakeStudyModal();
}
