var studyListPlusGrid = new ax5.ui.grid();
var applyStudyModal = new ax5.ui.modal();

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
        			 console.log(this);
        			 return '<button type="button" onclick="applyStudyForm(' + this.item.studyCode + ')" style="border:transparent; background-color:transparent">신청</button>';
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

	getStudyList();	// 스터디 목록 조회
});

/* 스터디 리스트 조회 함수 */
function getStudyList(){
	
	$.ajax({
 		type: "POST",
 		url : "/study/selectStudyList.json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	studyListPlusGrid.setData(data.resultList);
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
function applyStudyForm(studyCode){
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

//스터디 신청하기 팝업 닫기
function close(){
	applyStudyModal.close();
}