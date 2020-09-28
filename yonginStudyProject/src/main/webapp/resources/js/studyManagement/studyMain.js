var studyMemberListGrid= new ax5.ui.grid();
var calendarDetailModal = new ax5.ui.modal();


$(document).ready(function (){
	
		
	//스터디원 이름 목록
	studyMemberListGrid.setConfig({   
    	target: $('[data-ax5grid="studyMemberListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
			{key : "studyMemberName", label: "스터디원 이름", align: "center", width:"46%", sortable: true,
    			formatter:function(){
    			    return studySxnMap[this.value];
    			}
			},
        	{key : "studyID", label: "스터디원 아이디", align: "center", width:"45%"},
        	{key : "studyRank", label: "스터디원 계급", align: "center", width:"10%", 
          		 formatter: function (){
        			 return '<button type="button" onclick="exitMyStudy(' + this.dindex + ')" style="border:transparent; background-color:transparent;outline:none">탈퇴</button>';
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

					onDBLClick: function () 	{
                    	openStudyManagementPage();
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
            firstIcon: '|<', 
            prevIcon: '<',
            nextIcon: '>',
            lastIcon: '>|',
            display: false,
            onChange: function () {
                },
            },
        });




		//달력 초기 설정
	cal = new tui.Calendar('#study_calendar', {
	    defaultView: 'month', // monthly view option
	    //useDetailPopup : true,
    	disableDblClick : true,
    	disableClick : true,
    	month : {
    		daynames : [ '일', '월', '화', '수', '목', '금', '토' ],
    		startDayOfWeek : 0,
    	}
	});
	
	cal.on({
		'clickSchedule' : function(e) {
			var schedule = e.schedule;
			lastClickSchedule = schedule;
			openCalenderPopup(e);
		}
	});
	
	
});



//이전 달로 이동
function prev() {
	cal.prev();
	showRange();
	cal.clear();
	searchMyStudyCalendar();
}

// 다음 달로 이동
function next() {
	cal.next();
	showRange();
	cal.clear();
	searchMyStudyCalendar();
}

//이전 달로 이동
function prev() {
	cal.prev();
	showRange();
	cal.clear();
	searchMyStudyCalendar();
}

// 다음 달로 이동
function next() {
	cal.next();
	showRange();
	cal.clear();
	searchMyStudyCalendar();
}

// 현재 달력 범위 보여줌
function showRange(){
	var today = cal.getDate();
	var month = '' + (today.getMonth()+1);
	if (month.length < 2) month = '0' + month;
	document.getElementById("renderRange").innerText = today.getFullYear() + "." + month;
}

function openCalenderPopup(e){
	var windowWidth = $(window).width();
	var windowHeight = $(window).height();
	var width = e.event.clientX;
	var height = e.event.clientY;
	
	calendarDetailModal.open({
		width: 350,
		height: 250,
		iframe: {
			method: "post",
			url: "/main/calendarDetailPopup.do",
			param: callBack = e
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
	
	if(width+300 > windowWidth)
		width = width-300;
	if(height+300 > windowHeight)
		height = height-300;
	calendarDetailModal.align({left:width, top:height});
}
function closeCalenderPopup(){
	calendarDetailModal.close();
}
