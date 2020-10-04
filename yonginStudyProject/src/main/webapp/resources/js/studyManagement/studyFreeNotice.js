var studyFreeNoticeListGrid = new ax5.ui.grid();

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