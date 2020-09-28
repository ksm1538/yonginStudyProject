var fileIndex = 1;

$(document).ready(function () {
	//summernote editor
	$('#systemNoticeDesc').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	

});

// 공지사항 작성 함수
function makeSystemNoticeFunc(){
	
	// 폼 데이터 가져옴
    var form = $('#writeSysNoticeForm')[0];
    var data = new FormData(form);

    console.log(data);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/notice/makeSystemNotice",
        data: data,
        processData: false,		//필수
        contentType: false,		//필수
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
	self.parent.writeModalClose();
}

// 팝업창 닫고 새로고침
function closeModalWithRefresh(){
	self.parent.writeModalCloseWithRefresh();
}

// 파일 추가
function fn_addFile(){
	$("#fileIndex").append("<div id='fileDiv_"+fileIndex+"' style='margin-bottom:10%'><input type='file' style='float:left;' name='file_"+(fileIndex)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDelBtn' onclick='fileDelFunc("+fileIndex+")'>"+"삭제"+"</button></div>");
	fileIndex++;
}

// 파일 삭제
function fileDelFunc(index){
	$('#fileDiv_'+index).remove();
}