/** 변수 설정(시작) **/
var studyListGrid = new ax5.ui.grid();
var studyNoticeListGrid = new ax5.ui.grid();
var cal;
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	//달력 초기 설정
	cal = new tui.Calendar('#calendar', {
	    defaultView: 'month' // monthly view option
	});
	
	$(".user_id").click(function(){
		$(".user_box_con").fadeIn(600);
	});
	
	$(".circle_btn_2").click(function(){
		$(".user_box_con").fadeOut(600);
	}); 
	
	//스터디 리스트 설정
	studyListGrid.setConfig({   
    	target: $('[data-ax5grid="studyListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
        	{key : "studyTopic", label: "주제", align: "center", width:"25%", sortable: true},
        	{key : "studyName", label: "제목", align: "center", width:"25%"},
        	{key : "studyArea", label: "지역", align: "center", width:"15%"},
        	{key : "userName",label : "방장", align : "center",width : "15%"},
        	{key : "totalCount",label : "현재 인원", align : "right",width : "10%"},
        	{key : "studyLimit",label : "정원", align : "right",width : "10%"},
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
	
	
	//공지사항 리스트 설정
	studyNoticeListGrid.setConfig({   
    	target: $('[data-ax5grid="studyNoticeListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
        	{key : "studyName", label: "스터디 이름", align: "center", width:"45%", sortable: true},
        	{key : "noticeTitle", label: "제목", align: "center", width:"45%"},
        	{key : "noticeCnt", label: "조회 수", align: "center", width:"10%"},
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
/** 초기화(끝) **/

/*스터디 더보기 호출 */
function openMoreStudyForm(){
	location.href = "/moreStudy.do";
}

/*스터드만들기 호출 */
function makeStudyForm(){
	window.open("/makeStudy.do");
}




/* 스터디 리스트 조회 함수 */
function getStudyList(){
	
	$.ajax({
 		type: "POST",
 		url : "/main/selectStudyList.json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	studyListGrid.setData(data.resultList);
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


/*공지사항 더보기 호출 */
function openMoreNotice(){
	location.href = "/moreNotice.do";
}
