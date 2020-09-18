var noticeListPlusGrid = new ax5.ui.grid();

$(document).ready(function () {
	//공지사항 목록 더보기 리스트 설정
	noticeListPlusGrid.setConfig({   
    	target: $('[data-ax5grid="noticeListPlusGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
            {key : "systemNoticeTitle", label: "제목", align: "center", width:"45%", sortable: true},
        	{key : "systemNoticeRgstusID", label: "작성자 ID", align: "center", width:"10%"},
        	{key : "systemNoticeTime", label: "날짜", align: "center", width:"10%"},
			{key : "totalCount", label: "조회수", align: "center", width:"10%"},
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
});

/* 시스템 공지사항 리스트 조회 함수 */
function getSystemNoticeList(){
	
	$.ajax({
 		type: "POST",
 		url : "/notice/selectSystemNoticeList.json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	noticeListPlusGrid.setData(data.resultList);
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


/*공지사항 작성 호출 */
function openMoreNotice(){
	window.open("/writeNotice.do",'공지사항 작성','width=720px ,height=1050px ,location=no,status=no,scrollbars=no');
}

