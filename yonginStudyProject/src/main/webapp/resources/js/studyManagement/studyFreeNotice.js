var studyFreeNoticeListGrid = new ax5.ui.grid();
var studyFreeNoticeInfoWriteModal = new ax5.ui.modal();

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
            firstIcon: '|<', 
            prevIcon: '<',
            nextIcon: '>',
            lastIcon: '>|',
            display: false,
            onChange: function () {
				_pageNo = this.page.selectPage;
				getStudyNoticeList();
                },
            },
        });
	
	
});

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
