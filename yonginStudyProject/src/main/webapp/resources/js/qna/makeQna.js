var qnaListGrid = new ax5.ui.grid();
var writeQnaModal = new ax5.ui.modal();

/*컬럼 이름만 바꾸고 상세 코드는 안바꿨음 물어보고 공부해서 해결하는게 나을듯 */
$(document).ready(function () {

qnaListGrid.setConfig({   
    	target: $('[data-ax5grid="qnaListGrid"]'),
        showLineNumber: false,
        showRowSelector: false,
        columns: [
        	{key : "qnaNum", label: "작성번호", align: "center", width:"10%"},
			{key : "qnaTitle", label: "제목", align: "center", width:"50%"},
			{key : "qnaId", label: "작성자 ID", align: "center", width:"20%"},
			{key : "qnaManage", label: "관리", align: "center", width:"20%"},/*얘는 로그인아이디랑 작성자 아이디랑 일치할때 수정버튼 활성화 되도록 */
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 45,

					onDBLClick: function () 	{
                    	selectStudyNoticeInfoDetail(this.list[this.dindex]["studyNoticeCode"]);
					},                    
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
				getStudyNoticeList(); // 공지사항 조회
                },
            },
        });	



});


// qna작성 팝업 열기
function writeQna(){
	writeQnaModal.open({
		width: 600,
		height: 800,
		iframe: {
			method: "get",
			url: "/writeqna.do",
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

function closewriteQnaModal(){
	writeQnaModal.close();
}



