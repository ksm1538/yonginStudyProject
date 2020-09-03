/** 변수 설정(시작) **/
var studyListGrid = new ax5.ui.grid();
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	
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
        	{key : "studyNumber",label : "현재 인원", align : "right",width : "10%"},
        	{key : "studyLimit",label : "정원", align : "right",width : "10%"},
			
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 28,
                    
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
			    	console.log(data.resultList);
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
