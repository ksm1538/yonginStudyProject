var studyNoticeListGrid = new ax5.ui.grid();
var studyNoticeInfoDetailModal = new ax5.ui.modal();
var writeNoticeModal = new ax5.ui.modal();
var _pageNo = 0;

$(document).ready(function () {
	
	//스터디 리스트 설정
	studyNoticeListGrid.setConfig({   
    	target: $('[data-ax5grid="studyNoticeListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
			{key : "noticeTitle", label: "제목", align: "center", width:"55%", sortable: true},
        	{key : "rgstusId", label: "작성자 ID", align: "center", width:"16%"},
        	{key : "rgdtDt", label: "날짜", align: "center", width:"20%"},
			{key : "noticeCount", label: "조회수", align: "center", width:"10%"}, 
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
						selectStudyNoticeInfoDetail(this.list[this.dindex]["boardCode"]);
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
            display: false,
            onChange: function () {
				_pageNo = this.page.selectPage;
				getStudyNoticeList();
                },
            },
        });
	getStudyNoticeList();
});

/* 스터디 공지사항 리스트 조회 함수 */
function getStudyNoticeList(){
	var sendData = {
			page :	_pageNo,
			searchNoticeRgstusId:$('#noticeRgstusId').val(),
			searchNoticeTitle:$('#noticeTitle').val()
	}
	
	$.ajax({
		type: "POST",
 		url : "/studyManagement/selectStudyNoticeList.json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		studyNoticeListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}, 
			    		});
			    	}else{
			    		dToast.push("공지사항 목록이 없습니다.");
			    		studyNoticeListGrid.setData([]);
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

// 공지사항 작성 열기
function openWriteStudyNotice(){
	writeNoticeModal.open({
		width: 800,
		height: 750,
		iframe: {
			method: "get",
			url: "/writeStudyNotice.do",
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

//  공지사항 작성 팝업창 닫고 새로고침
function closeModal(){
	writeNoticeModal.close();
}

function writeNoticeModalCloseWithRefresh(){
	writeNoticeModal.close();
	getStudyNoticeList();
}

/*스터디 공지사항 삭제*/ 
function deleteStudyNotice(){
	var temp = studyNoticeListGrid.getList('selected');
	
	if(temp.length == 0){
		dialog.alert("삭제할 공지사항을 선택해주세요.");
		return;
	}
	dialog.confirm({
		msg:"선택하신 공지사항을 삭제하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					var boardCodes = [];
					
					for(var i=0;i<temp.length;i++){
						boardCodes.push(temp[i].boardCode)
					}
					
					var sendData = {
							boardCodes:boardCodes
					}
					
					$.ajax({
					     type: "POST",
					     url : "/studyManagement/deleteStudyNotice.json",
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
							        	if(this.key=="yes"  || this.state == "close"){
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
			},
			no: {
				label:'아니오', onClick:function(key){
					dialog.close();
					return;
    			}
			}
		}
	}, function(){
	});
	
} 

//EnterKeyEvent
function enterKeyEvent_2() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	_pageNo = 0;
    	getStudyNoticeList();
    }
}

// 검색 버튼용 조회 함수
function searchStudyNoticeList(){
	_pageNo = 0;
	getStudyNoticeList();
}

// 스터디 공지사항 상세 보기 팝업 
function selectStudyNoticeInfoDetail(boardCode){
	var parentData={
			boardCode:boardCode
	}
	
	studyNoticeInfoDetailModal.open({
		width: 800,
		height: 810,
		iframe: {
			method: "post",
			url: "/studyManagement/studyNoticeDetailPopup.do",
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

// 스터디 공지사항 상세 팝업 닫기
function closeStudyNoticeInfo(){
	studyNoticeInfoDetailModal.close();
}
