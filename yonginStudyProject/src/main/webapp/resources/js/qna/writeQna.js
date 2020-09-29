$(document).ready(function () {
	//summernote editor
	$('#writeQnaDesc').summernote({           
	    height: 350,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
});




// 팝업창 닫기
function closeModal(){
	return self.parent.closewriteQnaModal();		// 부모 페이지의 closeChangePwModal함수로 리턴
}