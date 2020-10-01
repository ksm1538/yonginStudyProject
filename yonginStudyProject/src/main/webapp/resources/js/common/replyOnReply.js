/** 변수 설정(시작) **/
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function(){
    if(parentData.replyCode == null || parentData.replyCode == undefined || parentData.replyCode == ""){
    	dToast.push("댓글이 선택되지 않았습니다.\n다시 시도해주세요.");
    }
    
});
/** 초기화(끝) **/

//댓글 달기
function replyOnReplyFunc(){
	if($("#replyText").val() == ""){
		dToast.push("댓글 내용을 입력해주세요.");
		return;
	}
	
	var sendData = {
			hgrnkReplyCode : parentData.replyCode,
			boardCode : parentData.boardCode,
			replyText : $("#replyText").val()
	}
	
	$.ajax({
 		type: "POST",
 		url : "/reply/insertReply.json",
 		data : JSON.stringify(sendData),
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
									closeModalWithRefresh();
				        	}
				    	});
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

// 현재 팝업 닫기
function closeModal(){
	self.parent.replyModalClose();
}

// 팝업 닫고 새로 고침
function closeModalWithRefresh(){
	self.parent.replyModalCloseWithRefresh();
}