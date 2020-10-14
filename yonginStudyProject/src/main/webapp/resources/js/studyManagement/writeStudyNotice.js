$(document).ready(function () {
	//summernote editor
	$('#noticeWriteSummernote').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
});



function closeWriteModal(){
	return self.parent.closeModal();		// 부모 페이지의 close함수로 리턴
}


// 파일 추가
function fn_addFile(){
	$("#fileIndex").append("<div id='fileDiv_"+fileIndex+"' style='display:flex; margin-bottom:10px;'><input type='file' name='file_"+(fileIndex)+"' class='file_style_0'>"+"</button>"+"<button type='button' id='fileDelBtn' onclick='fileDelFunc("+fileIndex+")'>"+"삭제"+"</button></div>");
	fileIndex++;
}

// 파일 삭제
function fileDelFunc(index){
	$('#fileDiv_'+index).remove();
}