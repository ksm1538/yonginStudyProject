var manageStudyListGrid= new ax5.ui.grid();
var seeApplystudyListGrid= new ax5.ui.grid();

$(document).ready(function () {
	
	//스터디 관리 리스트 설정
	manageStudyListGrid.setConfig({   
    	target: $('[data-ax5grid="manageStudyListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
        	{key : "ManagestudyName", label: "스터디원 이름", align: "center", width:"150px", sortable: true},
        	{key : "banishStudy", label: "추방", align: "center", width:"100px"},
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



		//스터디 신청 현황설정
	seeApplystudyListGrid.setConfig({   
    	target: $('[data-ax5grid="seeApplystudyListGrid"]'),
        showLineNumber: false,
        showRowSelector: true,
        columns: [ 
        	{key : "ApplyID", label: "신청아이디", align: "center", width:"30%", sortable: true},
        	{key : "ApplyName", label: "신청이름", align: "center", width:"30%"},
			{key : "SeeApply", label: "신청서보기", align: "center", width:"20%"},
			{key : "AcceptApply", label: "수락", align: "center", width:"10%"},
			{key : "RejectApply", label: "거절", align: "center", width:"10%"},
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


	