var messageListGrid = new ax5.ui.grid();
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
        	{key : "messageTitle", label: "제목", align: "center", width:"25%"},
        	{key : "messageTime", label: "시간", align: "center", width:"15%"},
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
	window.open("/sendMessage.do",'회원가입','width=500px ,height=470px ,location=no,status=no,scrollbars=no');
}

/*쪽지 삭제*/
function deleteMessage(){
	var sendData={
		studyName:document.getElementById("studyName").value,
		studyRgstusId:document.getElementById("studyRgstusId").value,
		studyTopic:$('#studyTopic option:selected').val(),
		studyArea:$('#studyArea option:selected').val(),
		studyLimit:$('#studyLimit option:selected').val(),
		studyDesc:document.getElementById("studyDesc").value
	}
	
	console.log(sendData); 
	  $.ajax({
	     type: "POST",
	     url : "/deleteMessage.json",
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
			        			window.close();
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