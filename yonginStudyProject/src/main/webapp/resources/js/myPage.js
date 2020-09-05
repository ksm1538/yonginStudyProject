var myParcitipateListGrid= new ax5.ui.grid();
var myMakeListGrid= new ax5.ui.grid();

$(document).ready(function () {
	
	//공지사항 리스트 설정
	myParcitipateListGrid.setConfig({   
    	target: $('[data-ax5grid="myParcitipateListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
        	{key : "studyName", label: "스터디 이름", align: "center", width:"45%", sortable: true},
        	{key : "noticeTitle", label: "제목", align: "center", width:"45%"},
        	{key : "dropStudy", label: "탈퇴", align: "center", width:"10%"},
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
	myMakeListGrid.setConfig({   
    	target: $('[data-ax5grid="myMakeListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
        	{key : "studyName", label: "스터디 이름", align: "center", width:"45%", sortable: true},
        	{key : "noticeTitle", label: "제목", align: "center", width:"45%"},
        	{key : "manageStudy", label: "관리", align: "center", width:"10%"},
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


		//클릭시 맨위로
		$("#Movetop").click(function(){
			$('html, body').animate({
				scrollTop : 0
			}, 400);
			return false;
		});
		
		//클릭시 이동
		$("#listMove1").on("click",function(event){
			var offset = $("#list1").offset();
			$("html body").animate({scrollTop:offset.top},400);
		});
		
		$("#listMove2").on("click",function(event){
			var offset = $("#list2").offset();
			$("html body").animate({scrollTop:offset.top},400);
		});
		
		$("#listMove3").on("click",function(event){
			var offset = $("#list3").offset();
			$("html body").animate({scrollTop:offset.top},400);
		});
		

});


	/*스터디 관리 호출 */
		function openstudyManagementForm(){
			window.open("/studyManagement.do",'회원가입','width=700px ,height=900px ,location=no,status=no,scrollbars=no');
		}