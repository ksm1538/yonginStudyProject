var fileIndex = 1;

$(document).ready(function () {
	//summernote editor
	$('#noticeWriteSummernote').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
});  

// 공지사항 작성 함수
function makeStudyNoticeFunc(){
	
	// 폼 데이터 가져옴
    var form = $('#writeStudyNoticeForm')[0];
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/notice/makeStudyNotice",
        data: data,
        processData: false,		//필수
        contentType: false,		//필수
        async:true,
        success: function (data) {
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
									closeModalWithRefresh();
				        	}
				    	});
		   		 break;
	        case COMMON_FAIL:
		   		 dialog.alert(data.message);
		   		 break;
        	}
        },
        error: function (e) {
        	 alert('error = ' + jqXHR.responseText);
        }
    });
}

function closeWriteModal(){
	return self.parent.closeModal();		// 부모 페이지의 close함수로 리턴
}

// 팝업창 닫고 새로고침
function closeModalWithRefresh(){
	self.parent.writeModalCloseWithRefresh();
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