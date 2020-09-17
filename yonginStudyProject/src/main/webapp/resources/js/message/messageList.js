var messageListGrid = new ax5.ui.grid();
var messageInfoModal = new ax5.ui.modal();
var cal;

/** 초기화(시작) **/
$(document).ready(function () {	
	//스터디 리스트 설정
	messageListGrid.setConfig({   
    	target: $('[data-ax5grid="messageListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
        	{key : "userCodeFrom", label: "보낸 사람", align: "center", width:"25%", sortable: true},
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
            firstIcon: '|<', 
            prevIcon: '<',
            nextIcon: '>',
            lastIcon: '>|',
            display: false,
            onChange: function () {
                },
            },
        });
	
	getMessageList();	// ㅉㅗㄱㅈㅣ 목록 조회
});
/** 초기화(끝) **/

/*쪽지 보내기 호출*/
function openSendMessageForm(){
	location.href ="/sendMessage.do";
}

/*받은 쪽지 삭제*/
function deleteMessage(){
	
	var temp = messageListGrid.getList('selected');
	var messageCodes = [];
	
	for(var i=0;i<temp.length;i++){
		messageCodes.push(temp[i].messageCode)
	}
	
	var sendData = {
		messageCodes:messageCodes
	}
	
	
	$.ajax({
	     type: "POST",
	     url : "/deleteMessageTo.json",
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
			        	if(this.key=="yes"){
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

/* ㅉㅗㄱㅈㅣ 리스트 조회 함수 */
function getMessageList(){
	
	$.ajax({
 		type: "POST",
 		url : "/selectMessageList.json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
					console.log(data);
			    	messageListGrid.setData(data.resultList);
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

//메시지 정보 팝업 보기
function selectMessageInfoDetail(messageCode){
	var parentData={
			messageCode:messageCode	 		// 스터디 그리드에서 선택한 studyCode를 팝업으로 보낼 데이터에 넣음
		}
		
	messageInfoModal.open({
		width: 800,
		height: 700,
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

// 쪽지 상세 정보 보기 팝업 닫기
function close(){
	messageInfoModal.close();
}