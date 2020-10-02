var qnaListGrid = new ax5.ui.grid();
var qnaModal = new ax5.ui.modal();
var _pageNo = 0;

/*컬럼 이름만 바꾸고 상세 코드는 안바꿨음 물어보고 공부해서 해결하는게 나을듯 */
$(document).ready(function () {

qnaListGrid.setConfig({   
    	target: $('[data-ax5grid="qnaListGrid"]'),
        showLineNumber: false,
        showRowSelector: false,
        columns: [
        	{key : "qnaStatus", label: "상태", align: "center", width:"10%",
    			formatter:function(){
    				if(this.value == "10"){
    					return "<span style="+"font-weight:bold;color:#F18787;"+">"+qnaSxnMap[this.value]+"</span>";
    				}
    				else if(this.value == "20"){
    					return "<span style="+"font-weight:bold;color:green;"+">"+qnaSxnMap[this.value]+"</span>";
    				}
    				
    			}},
			{key : "boardTitle", label: "제목", align: "center", width:"41%"},
			{key : "rgstusId", label: "작성자 ID", align: "center", width:"20%"},
			{key : "rgdtDt", label: "작성일", align: "center", width:"20%"},
			{key : "", label: "", align: "center", width:"10%", 
	       		 formatter: function (){
	       			 if(this.item.qnaStatus == "20"){
	       				 return '<button type="button" onclick="openAnswerForm(' + this.dindex + ')" style="border:transparent; background-color:transparent;outline:none;font-weight:bold">답글 확인</button>';
	       			 }else{
	       				 return "";
	       			 }
	    		 }
			},
			
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 45,

					onDBLClick: function () 	{
                    	selectQnaDetail(this.list[this.dindex]["boardCode"]);
					},                    
                    onClick: function () 	{
                    
					},
					onDataChanged: function(){
						
					},
                },
        page: {
            navigationItemCount: 9,
            height: 30,
            display: true,
            firstIcon: '<i class="fa fa-step-backward" aria-hidden="true"></i>', 
            prevIcon: '<i class="fa fa-caret-left" aria-hidden="true"></i>',
            nextIcon: '<i class="fa fa-caret-right" aria-hidden="true"></i>',
            lastIcon: '<i class="fa fa-step-forward" aria-hidden="true"></i>',
            onChange: function () {
				_pageNo = this.page.selectPage;
				getQnaList(); // 공지사항 조회
                },
            },
        });	


	getQnaList();
});

/* 시스템 Qna 리스트 조회 함수 */
function getQnaList(){
	var sendData = {
			page :	_pageNo,
			searchBoardRgstusId:$('#boardRgstusId').val(),
			searchBoardTitle:$('#boardTitle').val()
	}
	
	$.ajax({
		type: "POST",
 		url : "/systemQna/selectQnaList.json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		qnaListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}, 
			    		});
			    	}else{
			    		dToast.push("QnA 목록이 없습니다.");
			    		qnaListGrid.setData([]);
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

//EnterKeyEvent
function enterKeyEvent() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	_pageNo = 0;
    	getQnaList();
    }
}

//검색 버튼용 조회 함수
function searchQnaList(){
	_pageNo = 0;
	getQnaList();
}

// qna작성 팝업 열기
function writeQna(){
	qnaModal.open({
		width: 800,
		height: 850,
		iframe: {
			method: "get",
			url: "/systemQna/writeQna.do",
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

// QnA 작성 팝업 닫기
function closeQnaModal(){
	qnaModal.close();
}

//QnA 작성 팝업 닫고 새로고침
function closeWriteQnaWithRefresh(){
	qnaModal.close();
	window.location.reload();
}

//공지사항 상세 보기 팝업 
function selectQnaDetail(boardCode){
	var parentData={
			boardCode:boardCode
	}
	
	qnaModal.open({
		width: 800,
		height: 810,
		iframe: {
			method: "get",
			url: "/systemQna/qnaDetailPopup.do",
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

// 답글 보기
function openAnswerForm(dindex){
	var boardCode = qnaListGrid.list[dindex].boardCode
	var parentData={
			boardCode:boardCode
	}
	
	qnaModal.open({
		width: 800,
		height: 810,
		iframe: {
			method: "get",
			url: "/systemQna/qnaAnswerDetailPopup.do",
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

