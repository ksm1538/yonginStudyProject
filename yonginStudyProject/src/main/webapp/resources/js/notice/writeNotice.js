
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