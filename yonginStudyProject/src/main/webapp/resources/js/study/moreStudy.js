var studyListPlusGrid = new ax5.ui.grid();
var applyStudyModal = new ax5.ui.modal();
var studyInfoDetailModal = new ax5.ui.modal();		//팝업창 띄우는 modal기능
var _pageNo = 0; 		//페이징 변수

$(document).ready(function () {
	//스터디 목록 더보기 리스트 설정
	studyListPlusGrid.setConfig({   
    	target: $('[data-ax5grid="studyListPlusGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
        	{key : "studyTopic", label: "주제", align: "center", width:"20%", sortable: true,
    			formatter:function(){
    			    return inoutSxnMap[this.value];
    			}
        	},
        	{key : "studyName", label: "제목", align: "center", width:"30%"},
        	{key : "studyArea", label: "지역", align: "center", width:"15%"},
        	{key : "userName",label : "방장", align : "center",width : "15%"},
        	{key : "totalCount",label : "현재 인원", align : "right",width : "8%"},
        	{key : "studyLimit",label : "정원", align : "right",width : "8%"},
        	{key : "applyButton", label : "", align : "center", width:"5%", 
        		 formatter: function (){
        			 return '<button type="button" onclick="applyStudyForm(' + this.dindex + ')" style="border:transparent; background-color:transparent;outline:none">신청</button>';
        		 }
        	}
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 45,
                    
                    onDBLClick: function () 	{
                    	selectStudyInfoDetail(this.list[this.dindex]["studyCode"]);
					},
					onDataChanged: function(){
						
					},
                },
        
        page: {
            navigationItemCount: 10,
            height: 30,
            display: true,
            firstIcon : '<i class="fa fa-step-backward" aria-hidden="true"></i>',
			prevIcon : '<i class="fa fa-caret-left" aria-hidden="true"></i>',
			nextIcon : '<i class="fa fa-caret-right" aria-hidden="true"></i>',
			lastIcon : '<i class="fa fa-step-forward" aria-hidden="true"></i>',
            display: true,
            onChange: function () {		// 그리드 밑 페이지 번호로 이동했을 때
            	_pageNo = this.page.selectPage;
            	getStudyList();
            	},
            },
        });

	getStudyList();	// 스터디 목록 조회
});

/* 스터디 리스트 조회 함수 */
function getStudyList(){
	
	var sendData = {
			page : _pageNo,
			searchStudyTopic:$('#studyTopic').val(),
			searchStudyName:$('#studyName').val(),
			searchStudyArea:$('#studyArea').val()
	}

	$.ajax({
 		type: "POST",
 		url : "/study/selectStudyList.json",
 		data : JSON.stringify(sendData),
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		studyListPlusGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}
			    		});
			    	}else{
			    		dToast.push("스터디 목록이 없습니다.");
			    		studyListPlusGrid.setData([]);
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

// 버튼 누르면 팝업으로 가도록 호출
function applyStudyForm(dindex){
	var studyCode = studyListPlusGrid.list[dindex].studyCode;
	
	var parentData={
			studyCode:studyCode	 		// 스터디 그리드에서 선택한 studyCode를 팝업으로 보낼 데이터에 넣음
		}
		
	applyStudyModal.open({
		width: 800,
		height: 700,
		iframe: {
			method: "post",
			url: "/studyApplyPop.do",
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



/* 스터디 만들기 팝업 호출 */
function makeStudyForm(){
	makeStudyModal.open({
		width: 800,
		height: 900,
		iframe: {
			method: "get",
			url: "/makeStudy.do",
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
	//window.open("/makeStudy.do",'스터디더보기','width=700px ,height=800px ,location=no,status=no,scrollbars=no');
}

// 스터디 만들기 팝업 닫기
function closeMakeStudyModal(){
	window.location.reload();
	makeStudyModal.close();
}


function selectStudyInfoDetail(studyCode){
	var parentData={
		studyCode:studyCode	 		// 스터디 그리드에서 선택한 studyCode를 팝업으로 보낼 데이터에 넣음
	}
	
	studyInfoDetailModal.open({
		width: 800,
		height: 710,
		iframe: {
			method: "post",
			url: "/study/studyInfoDetailPopup.do",
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

// 스터디 상세 팝업 닫기
function closeStudyInfo(){
	studyInfoDetailModal.close();
}


//스터디 신청하기 팝업 닫기
function closeApplyStudy(){
	applyStudyModal.close();
}

// EnterKeyEvent
function enterKeyEvent() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	_pageNo = 0;
    	getStudyList();
    }
}

// 검색용 버튼 함수
function searchStudyList(){
	_pageNo = 0;
	getStudyList();
}