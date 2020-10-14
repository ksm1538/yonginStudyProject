$(document).ready(function () {
	//summernote editor
	$('#scheduleWriteDesc').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
});


function closeWriteModal(){
	return self.parent.closeModal();		// 부모 페이지의 close함수로 리턴
}