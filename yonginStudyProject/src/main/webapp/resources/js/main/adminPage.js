/** 변수 설정(시작) **/
var userListGrid = new ax5.ui.grid();
var studyListGrid = new ax5.ui.grid();
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	
	// 사용자 그리드 
	userListGrid.setConfig({   
    	target: $('[data-ax5grid="userListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
			{key : "userId", label: "사용자 아이디", align: "center", width:"25%"},
        	{key : "userName", label: "사용자 이름", align: "center", width:"20%"},
        	{key : "userEmail", label: "사용자 이메일", align: "center", width:"30%"},
        	{key : "userIsAdmin", label: "관리자 여부", align: "center", width:"15%"},
        	{key : "kickUser", label: "추방", align: "center", width:"5%", 
          		 formatter: function (){
        			 return '<button type="button" onclick="kickUser(' + this.dindex + ')" style="border:transparent; background-color:transparent;outline:none">추방</button>';
        		 }
        	},
        	{key : "manageAdmin", label: "관리자 임명", align: "center", width:"5%", 
         		 formatter: function (){
         			 if(this.item.userIsAdmin == 'Y'){
         				return '<button type="button" onclick="cancleAdmin(' + this.dindex + ')" style="border:transparent; background-color:transparent;outline:none">관리자 해제</button>';
         			 }
         			 else{
         				return '<button type="button" onclick="makeAdmin(' + this.dindex + ')" style="border:transparent; background-color:transparent;outline:none">관리자 설정</button>';
         			 }
       		 }
       	},
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
	
	
	// 스터디 그리드 
	studyListGrid.setConfig({   
    	target: $('[data-ax5grid="studyListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
			{key : "studyName", label: "스터디 이름", align: "center", width:"30%"},
        	{key : "studyTopic", label: "스터디 주제", align: "center", width:"20%",
    			formatter:function(){
    			    return studySxnMap[this.value];
    			}},
        	{key : "studyArea", label: "스터디 지역", align: "center", width:"30%"},
        	{key : "studyRgstusId", label: "스터디 방장", align: "center", width:"15%"},
        	{key : "deleteStudy", label: "삭제", align: "center", width:"5%", 
          		 formatter: function (){
        			 return '<button type="button" onclick="deleteStudy(' + this.dindex + ')" style="border:transparent; background-color:transparent;outline:none">삭제</button>';
        		 }
        	},
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
/** 초기화(끝) **/

// 관리자 취소 함수
function cancleAdmin(dindex){
	var userCode = userListGrid.list[dindex].userCode;	//	사용자 코드
	
	alert("선택한 코드 : "+userCode);
	
}

// 관리자 설정 함수
function makeAdmin(dindex){
	var userCode = userListGrid.list[dindex].userCode;	//	사용자 코드
	
	alert("선택한 코드 : "+userCode);
}

// 스터디 삭제 함수
function deleteStudy(dindex){
	var studyCode = studyListGrid.list[dindex].studyCode;	//	사용자 코드
	
	alert("선택한 코드 : "+studyCode);
	
}