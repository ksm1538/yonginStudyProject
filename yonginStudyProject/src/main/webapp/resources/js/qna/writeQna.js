var fileIndex = 1;

$(document).ready(function () {
	//summernote editor
	$('#boardDesc').summernote({           
	    height: 350,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
});

//QnA 작성 함수
function makeQnaFunc(){
	
	// 폼 데이터 가져옴
    var form = $('#writeQnaForm')[0];
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/systemQna/makeQna",
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

// 팝업창 닫기
function closeModal(){
	return self.parent.closeQnaModal();		// 부모 페이지의 close함수로 리턴
}


//팝업창 닫고 새로고침
function closeModalWithRefresh(){
	self.parent.closeWriteQnaWithRefresh();
}

//파일 추가
function fn_addFile(){
	$("#fileIndex").append("<div id='fileDiv_"+fileIndex+"' style='display:flex'><input type='file' style='margin-bottom:10px;' name='file_"+(fileIndex)+"'>"+"</button>"+"<button type='button' style='height:25px;' id='fileDelBtn' onclick='fileDelFunc("+fileIndex+")'>"+"삭제"+"</button></div>");
	fileIndex++;
}

// 파일 삭제
function fileDelFunc(index){
	$('#fileDiv_'+index).remove();
}