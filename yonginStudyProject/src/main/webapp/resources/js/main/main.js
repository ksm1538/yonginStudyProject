/** 변수 설정(시작) **/
var studyListGrid = new ax5.ui.grid();
var studyNoticeListGrid = new ax5.ui.grid();
var calendarDetailModal = new ax5.ui.modal();
var noticeDetailModal = new ax5.ui.modal();
		//팝업창 띄우는 modal기능
var cal;
var _pageNo = 0;
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	
/*	$(".tui-full-calendar-weekday-grid-line").click(function(){
			
			window.open("/registerForm.do",'회원가입','width=720px ,height=900px ,location=no,status=no,scrollbars=no');
		});*/
	
	//달력 초기 설정
	cal = new tui.Calendar('#calendar', {
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
	
	
	
	
/*	$(".user_id").click(function(){
		$(".user_box_con").fadeIn(600);
	});
	
	$(".circle_btn_2").click(function(){
		$(".user_box_con").fadeOut(600);
	}); 
	*/
		
		
		/*클릭시 이동 */
		$("#Movetop").click(function(){
			$('html, body').animate({
				scrollTop : 0
			}, 400);
			return false;
		});
		
		var list1 = $("#movelist").offset();
		var list2 = $("#movenotice").offset();
		var list3 = $("#movecal").offset();
		//클릭시 이동
		$("#movelist1").on("click",function(event){
			$("html body").animate({scrollTop:list1.top},400);
		});
		
		$("#movelist2").on("click",function(event){
			$("html body").animate({scrollTop:list2.top},400);
		});
		
		$("#movelist3").on("click",function(event){
			$("html body").animate({scrollTop:list3.top},400);
		});
		
		/*사이드메뉴 */
		$(".side_fixed_menu_title").click(function(){
			var sidemenu = $(".side_fixed_menu_list");
			
			if(sidemenu.is(":visible")){
				sidemenu.slideUp();
			}else{
				sidemenu.slideDown();
			}
		});
		
		
		var list4 = $("#movelist").offset();
		var list5 = $("#movenotice").offset();
		var list6 = $("#movecal").offset();
		//클릭시 이동
		$("#side_movelist1").on("click",function(event){
			$("html body").animate({scrollTop:list4.top},400);
		});
		
		$("#side_movelist2").on("click",function(event){
			$("html body").animate({scrollTop:list5.top},400);
		});
		
		$("#side_movelist3").on("click",function(event){
			$("html body").animate({scrollTop:list6.top},400);
		});

	//내가 참여한 스터디 목록
		studyListGrid.setConfig({   
    	target: $('[data-ax5grid="studyListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
        	{key : "studyTopic", label: "주제", align: "center", width:"25%", sortable: true,
    			formatter:function(){
    			    return inoutSxnMap[this.value];
    			}
        	},
        	{key : "studyName", label: "제목", align: "center", width:"26%"},
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

					onDBLClick: function () 	{
                    	openStudyManagementPage(studyListGrid.list[this.dindex].studyCode, studyListGrid.list[this.dindex].studyName);
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
	
	//공지사항 리스트 설정
	studyNoticeListGrid.setConfig({   
    	target: $('[data-ax5grid="studyNoticeListGrid"]'),
        showLineNumber: false,
        showRowSelector: false,
        columns: [
        	{key : "boardTitle", label: "제목", align: "center", width:"30%"},
			{key : "studyName", label: "스터디 이름", align: "center", width:"20%"},
			{key : "rgstusId", label: "작성자 ID", align: "center", width:"24%"},
			{key : "rgdtDt", label: "날짜", align: "center", width:"17%"},
        	{key : "boardCount", label: "조회 수", align: "center", width:"10%"},
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 45,

					onDBLClick: function () 	{
                    	selectStudyNoticeInfoDetail(this.list[this.dindex]["boardCode"]);
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
	
	getStudyList();	// 스터디 목록 조회
	getStudyNoticeList(); // 공지사항 조회
	
	showRange();
	searchMyStudyCalendar();
});
/** 초기화(끝) **/

/*스터디 더보기 호출 2020 09 17 정승준 삭제*/


/*스터드만들기 호출 2020 09 18 정승준삭제 */

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

/* 시스템 공지사항 리스트 조회 함수 */
function getStudyNoticeList(){
	var sendData = {
		page : _pageNo
	}
	
	$.ajax({
		type: "POST",
 		url : "/main/selectStudyNoticeList.json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		studyNoticeListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}, 
			    		});
			    	}else{
			    		dToast.push("공지사항 목록이 없습니다.");
			    		studyNoticeListGrid.setData([]);
			    	}
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

//스케줄 검색
function searchMyStudyCalendar() {
	var today = cal.getDate();
	var month = '' + (today.getMonth()+1);
	if (month.length < 2) month = '0' + month;
	
	var sendData = {
		searchMonthFrom:today.getFullYear() + month + '01',
		searchMonthTo:today.getFullYear() + month + '31'
	}

	cal.clear();
	
	searchMyStudyCalendarAjax(sendData);
}

// 스케줄 검색 Ajax
function searchMyStudyCalendarAjax(sendData){
	$.ajax({
 		type: "POST",
 		url : "/main/searchMyStudyCalendar.json",
 		data : JSON.stringify(sendData),
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	for(var i=0; i<data.resultList.length; i++) {
						cal.createSchedules([ {
							id : data.resultList[i].calendarCode,
							title : "("+data.resultList[i].startHm+") "+data.resultList[i].title,
							body : data.resultList[i].content,
							category : 'time',
							raw:typeSxnMap[data.resultList[i].type][0],
							start : data.resultList[i].startDt + 'T' + data.resultList[i].startHm,
							end : data.resultList[i].endDt + 'T' + data.resultList[i].endHm,
							attendees : [data.resultList[i].studyName, data.resultList[i].rgstusId],
							borderColor:typeSxnMap[data.resultList[i].type][1],
						}]);
					}
			    	break;    
			    case COMMON_FAIL:
			    	dToast.push(data.message); 
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log('error = ' + jqXHR.responseText + 'code = ' + errorThrown);
		}
	}); 
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

function selectStudyNoticeInfoDetail(boardCode){
	var parentData={
			boardCode:boardCode
	}
	
	noticeDetailModal.open({
		width: 800,
		height: 810,
		iframe: {
			method: "post",
			url: "/notice/noticeInfoDetailPopup.do",
			param: callBack = parentData
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


/* 스터디 만들기 팝업 호출 */
function makeStudyForm(){
	makeStudyModal.open({
		width: 800,
		height: 900,
		iframe: {
			method: "get",
			url: "/makeStudy.do",
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
	//window.open("/makeStudy.do",'스터디더보기','width=700px ,height=800px ,location=no,status=no,scrollbars=no');
}

// 스터디 만들기 팝업 닫기
function closeMakeStudyModal(){
	window.location.reload();
	makeStudyModal.close();
}



// 공지사항 작성 팝업창 닫고 새로고침
function closeSystemNoticeInfo(){
	noticeDetailModal.close();
	window.location.reload();
	
}


//스터디 페이지 이동(POST방식 이용)
function openStudyManagementPage(studyCode, studyName){
	var frmPop= document.dataForm;
    var url = '/studyManagement/studyMain.do';
    window.open('',studyName);  
     
    frmPop.method="post";
    frmPop.action = url;
    frmPop.target = studyName; //window,open()의 두번째 인수와 같아야 하며 필수다.  
    frmPop.studyCode.value = studyCode;
    frmPop.studyName.value = studyName;
    frmPop.submit();   
}