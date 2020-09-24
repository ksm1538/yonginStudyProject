var noticeListPlusGrid = new ax5.ui.grid();
var _pageNo = 0;

$(document).ready(function () {
	//공지사항 목록 더보기 리스트 설정
	noticeListPlusGrid.setConfig({   
    	target: $('[data-ax5grid="noticeListPlusGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
            {key : "systemNoticeTitle", label: "제목", align: "center", width:"45%", sortable: true},
        	{key : "systemNoticeRgstusId", label: "작성자 ID", align: "center", width:"10%"},
        	{key : "systemNoticeTime", label: "날짜", align: "center", width:"10%"},
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
				getSystemNoticeList();
                },
            },
        });

	getSystemNoticeList();
});

/* 시스템 공지사항 리스트 조회 함수 */
function getSystemNoticeList(){
	var sendData = {
			page :	_pageNo
	}
	
	$.ajax({
		type: "POST",
 		url : "/notice/selectSystemNoticeList.json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		noticeListPlusGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}
			    		});
			    	}else{
			    		dToast.push("공지사항 목록이 없습니다.");
			    		noticeListPlusGrid.setData([]);
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


/*공지사항 작성 호출 */
function openWriteNotice(){
	window.open("/writeNotice.do",'공지사항 작성','width=720px ,height=1050px ,location=no,status=no,scrollbars=no');
}

/*공지사항 삭제*/
function deleteSystemNotice(){
	
	var temp = noticeListPlusGrid.getList('selected');
	var systemNoticeCodes = [];
	
	for(var i=0;i<temp.length;i++){
		systemNoticeCodes.push(temp[i].systemNoticeCode)
	}
	
	var sendData = {
		systemNoticeCodes:systemNoticeCodes
	}
	
	$.ajax({
	     type: "POST",
	     url : "/notice/deleteSystemNotice.json",
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