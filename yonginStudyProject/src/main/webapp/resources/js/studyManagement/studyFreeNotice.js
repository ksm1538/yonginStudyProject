var studyFreeNoticeListGrid = new ax5.ui.grid();
var studyFreeNoticeInfoWriteModal = new ax5.ui.modal();
var _pageNo = 0;

$(document).ready(function () {
	
	//스터디 리스트 설정
	studyFreeNoticeListGrid.setConfig({   
    	target: $('[data-ax5grid="studyFreeNoticeListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
            {key : "freeNoticeNum", label: "번호", align: "center", width:"10%"},
        	{key : "freeNoticeID", label: "작성자 아이디", align: "center", width:"20%"},
        	{key : "freeNoticeMember",label : "작성자 이름", align : "center",width : "20%"},
        	{key : "freeNoticeName",label : "제목", align : "right",width : "40%"}
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 45,
                    
                    onClick: function () 	{
					},
					onDBLClick: function(){
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
				getStudyFreeNoticeList();
                },
            },
        });
	
	getStudyFreeNoticeList();
});

/* 스터디 자유게시판 리스트 조회 함수 */
function getStudyFreeNoticeList(){
	var sendData = {
			page :	_pageNo,
			searchBoardRgstusId:$('#boardRgstusId').val(),
			searchBoardTitle:$('#boardTitle').val()
	}
	
	$.ajax({
		type: "POST",
 		url : "/notice/selectStudyFreeNoticeList.json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		studyFreeNoticeListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}, 
			    		});
			    	}else{
			    		dToast.push("자유게시판 목록이 없습니다.");
			    		studyFreeNoticeListGrid.setData([]);
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

/*스터디 자유게시판 작성 호출 */
function openWriteStudyFreeNotice(){
	studyFreeNoticeInfoWriteModal.open({
		width: 800,
		height: 710,
		iframe: {
			method: "get",
			url: "/studyFreeNoticeWrite.do",
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

// 자유게시판 작성 팝업창 닫고 새로고침
function writeModalCloseWithRefresh(){
	studyFreeNoticeInfoWriteModal.close();
	window.location.reload();
	
}

// 자유게시판 작성 팝업창 닫기
function writeModalClose(){
	studyFreeNoticeInfoWriteModal.close();
}
