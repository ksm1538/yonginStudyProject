var messageListGrid = new ax5.ui.grid();
var messageInfoModal = new ax5.ui.modal();
var cal;
var _pageNo = 0;			//페이징 변수

/** 초기화(시작) **/
$(document).ready(function () {	
	//스터디 리스트 설정
	messageListGrid.setConfig({   
    	target: $('[data-ax5grid="messageListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
        	{key : "userCodeTo", label: "받는 사람", align: "center", width:"25%", sortable: true},
        	{key : "messageTitle", label: "제목", align: "center", width:"50%"},
        	{key : "messageTime", label: "시간", align: "center", width:"25%"},
        ],
        header: {
        	align:"center",
        	//selector: false
			/*체크박스*/
			multiselect:true,
			multipleSelect: true
        },
        body: {
                    align: "left",
                    columnHeight: 45,
                    
                    onClick: function () 	{
					},
					onDBLClick: function(){
			    		selectMessageInfoDetail(this.list[this.dindex]["messageCode"]);
					},
					onDataChanged: function(){
					},
                },
        
        page: {
            navigationItemCount: 9,
            height: 30,
            display: true,
            firstIcon : '<i class="fa fa-step-backward" aria-hidden="true"></i>',
			prevIcon : '<i class="fa fa-caret-left" aria-hidden="true"></i>',
			nextIcon : '<i class="fa fa-caret-right" aria-hidden="true"></i>',
			lastIcon : '<i class="fa fa-step-forward" aria-hidden="true"></i>',
            onChange: function () {		// 그리드 밑 페이지 번호로 이동했을 때
            	_pageNo = this.page.selectPage;
            	getMessageList();
            	},
            },
        });
	
	getMessageList();	// ㅉㅗㄱㅈㅣ 목록 조회
});
/** 초기화(끝) **/



/*보낸 쪽지 삭제*/
function deleteSendMessage(){
	var temp = messageListGrid.getList('selected');
	
	//쪽지를 선택하지 않은 경우
	if(temp.length  == 0){
		dToast.push("삭제할 쪽지를 선택하세요.");
		return;
	}
	
	var messageCodes = [];
	
	for(var i=0;i<temp.length;i++){
		messageCodes.push(temp[i].messageCode)
	}
	
	var sendData = {
		messageCodes:messageCodes
	}
	
	  $.ajax({
	     type: "POST",
	     url : "/deleteSendMessage.json",
	     data: JSON.stringify(sendData),
	     dataType: "json",
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
			        		window.location.reload();
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

//EnterKeyEvent
function enterKeyEvent() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	_pageNo = 0;
    	getMessageList();
    }
}

// 검색 버튼용 조회 함수
function searchMessageList(){
	_pageNo = 0;
	getMessageList();
}

/* 쪽지 리스트 조회 함수 */
function getMessageList(){
	var sendData = {
			page : _pageNo,
			searchUserCodeTo:$('#userCodeTo').val(),
			searchMessageTitle:$('#messageTitle').val()
	}
	
	$.ajax({
 		type: "POST",
 		url : "/selectSendMessageList.json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		messageListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}
			    		});
			    	}else{
			    		dToast.push("쪽지 목록이 없습니다.");
			    		messageListGrid.setData([]);
			    	}
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


// 메시지 상세내용 정보보기
function selectMessageInfoDetail(messageCode){
	var parentData={
			messageCode:messageCode,	 		// 스터디 그리드에서 선택한 studyCode를 팝업으로 보낼 데이터에 넣음
			status:"sendMessage"
		}
		
	messageInfoModal.open({
		width: 800,
		height: 750,
		iframe: {
			method: "post",
			url: "/message/messageInfoDetailPopup.do",
			param: callBack = parentData
		},
		onStateChanged: function(){
			if (this.state === "open") {
	        	mask.open();
	        }
	        else if (this.state === "close") {
	        	mask.close();
	        }
	    },
	}, function() {
	});
}

//쪽지 상세 정보 보기 팝업 닫기
function close(){
	messageInfoModal.close();
}