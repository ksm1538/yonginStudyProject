var studyListPlusGrid = new ax5.ui.grid();

$(document).ready(function () {
	//스터디 목록 더보기 리스트 설정
	studyListPlusGrid.setConfig({   
    	target: $('[data-ax5grid="studyListPlusGrid"]'),
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
});

function openstudyApplyPopForm(){ 
	window.open("/studyApplyPop.do",'회원가입','width=700px ,height=900px ,location=no,status=no,scrollbars=no');
}
