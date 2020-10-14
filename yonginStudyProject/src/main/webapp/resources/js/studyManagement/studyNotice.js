var studyNoticeListGrid = new ax5.ui.grid();

$(document).ready(function () {
	
	//스터디 리스트 설정
	studyNoticeListGrid.setConfig({   
    	target: $('[data-ax5grid="studyNoticeListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
            {key : "noticeNum", label: "번호", align: "center", width:"10%"},
        	{key : "noticeID", label: "작성자 아이디", align: "center", width:"20%"},
        	{key : "noticeMember",label : "작성자 이름", align : "center",width : "20%"},
        	{key : "noticeName",label : "제목", align : "right",width : "40%"},
			{key : "noticeManage",label : "관리", align : "right",width : "10%"}/*권한여부로 활성화 */
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

function openWriteStudyNotice() {
	location.href = "/writeStudyNotice.do";
}
