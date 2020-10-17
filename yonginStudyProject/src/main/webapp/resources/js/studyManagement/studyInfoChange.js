$(document).ready(function () {
	// ID 입력 폼의 값 변경 시, 중복체크 여부 N

	//summernote editor
	$('#studyDesc').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	

});



function studyAddressPopup() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('studyArea').value = data.sido + " " + data.sigungu;
        }
    }).open();
}

function closeModal(){
	return self.parent.closeStudyChangeInfoModal();		// 부모 페이지의 closeChangePwModal함수로 리턴
}