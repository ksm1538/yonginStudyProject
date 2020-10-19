var studyFreeNoticeListGrid = new ax5.ui.grid(); 
var studyFreeNoticeInfoWriteModal = new ax5.ui.modal();
var studyFreeNoticeInfoDetailModal = new ax5.ui.modal();		//팝업창 띄우는 modal기능
var _pageNo = 0;
var studyCode;
var studyName;

$(document).ready(function () {
	studyCode = $("#studyCode").val();
	studyName = $("#studyName").val(); 
	
	document.getElementById("spanStudyMainName").innerText = studyName;
	
	//스터디 리스트 설정
	studyFreeNoticeListGrid.setConfig({   
    	target: $('[data-ax5grid="studyFreeNoticeListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
        	{key : "boardTitle",label : "제목", align : "center",width : "46%"},
			{key : "rgstusId", label: "작성자 아이디", align: "center", width:"25%"},
			{key : "rgdtDt", label: "날짜", align: "center", width:"20%"},
			{key : "boardCount", label: "조회수", align: "center", width:"10%"},
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
						selectStudyFreeNoticeInfoDetail(this.list[this.dindex]["boardCode"]);
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
			studyCode : $("#studyCode").val(),
			searchBoardRgstusId:$('#searchBoardRgstusId').val(),
			searchBoardTitle:$('#searchBoardTitle').val()
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
	
	var parentData={
			studyCode:$("#studyCode").val()
		}
		
	studyFreeNoticeInfoWriteModal.open({
		width: 800,
		height: 750,
		iframe: {
			method: "get",
			url: "/studyFreeNoticeWrite.do",
			param: callBack = parentData,
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

// 스터디 자유게시판 상세 보기 팝업 
function selectStudyFreeNoticeInfoDetail(boardCode){
	var parentData={
			boardCode:boardCode
	}
	
	studyFreeNoticeInfoDetailModal.open({
		width: 800,
		height: 810,
		iframe: {
			method: "post",
			url: "/studyManagement/studyFreeNoticeInfoDetailPopup.do",
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

// 자유게시판 작성 팝업창 닫고 새로고침
function writeModalCloseWithRefresh(){
	studyFreeNoticeInfoWriteModal.close();
	// 원래 새로고침 있던 자리인데, 어처피 데이터 다시 불러오는 거라 데이터 가져오는 함수로 대체(이게 훨씬 빠름)
	getStudyFreeNoticeList();
	
}

// 자유게시판 작성 팝업창 닫기
function writeModalClose(){
	studyFreeNoticeInfoWriteModal.close();
}

// 자유게시판 상세보기 팝업창 닫고 새로고침
function detailPopupModalCloseWithRefresh(){
	studyFreeNoticeInfoDetailModal.close();
	getStudyFreeNoticeList();
	
}

// 자유게시판 상세 팝업창 닫기
function detailPopupModalClose(){
	studyFreeNoticeInfoDetailModal.close();
}

//EnterKeyEvent
function enterKeyEvent() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	_pageNo = 0;
    	getStudyFreeNoticeList();
    }
}

// 검색 버튼용 조회 함수
function searchNoticeList(){
	_pageNo = 0;
	getStudyFreeNoticeList();
}
