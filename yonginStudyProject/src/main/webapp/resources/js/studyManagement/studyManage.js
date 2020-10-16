/** 변수 설정(시작) **/  
var studyMemberManageListGrid = new ax5.ui.grid();
var studyMemberManageModal = new ax5.ui.modal();	//팝업창 띄우는 modal기능
var studyApplyCheckListGrid = new ax5.ui.grid();
var cal;
var _pageNo = 0;
var studyCode;		// 현재 페이지 스터디 코드
var studyName; 		// 현재 페이지 스터디 이름
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	
	studyCode = $("#studyCode").val();
	studyName = $("#studyName").val();
	
	document.getElementById("spanStudyMainName").innerText = studyName;

	//스터디 멤버 관리
	studyMemberManageListGrid.setConfig({   
    	target: $('[data-ax5grid="studyMemberManageListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
        	
        	{key : "userId", label: "ID", align: "center", width:"26%"},
        	{key : "userName", label: "이름", align: "center", width:"15%"},
        	{key : "userAddress",label : "거주지", align : "center",width : "15%"},
        	{key : "studyAuthority",label : "직위", align : "center",width : "10%",
				formatter:function(){
    			    return studyPositionMap[this.value];
    			}
			},
			{key : "studyAuthorityChange",label : "직위 변경", align : "center",width : "25%",
				formatter: function (){
        			 //return '<button type="button" onclick="studyMemberManagefunc(' + this.list[this.dindex]["userCode"] + ')" style="border:transparent; background-color:transparent;outline:none">관리</button>';
					return '<button type="button" style="border:transparent; background-color:transparent;outline:none">직위 변경</button>';
        		 }
			},/*직위변경 */
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
						selectStudyMemberManage(this.list[this.dindex]["userCode"],this.list[this.dindex]["userId"]);
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
				getStudyMemberList();
                },
            },
        });
	
	//스터디 신청서
	studyApplyCheckListGrid.setConfig({   
    	target: $('[data-ax5grid="studyApplyCheckListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
        	
        	{key : "studyApplyName", label: "신청자 이름", align: "center", width:"25%"},
        	{key : "studyApplyId", label: "신청자 아이디", align: "center", width:"25%"},
        	{key : "studySeeApplyRank",label : "신청서 보기", align : "center",width : "25%"},
        	{key : "studyApplyCheck",label : "관리", align : "right",width : "25%"},/*수락 거절*/
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

		getStudyMemberList();
		
		
		/*클릭시 이동 */
		$("#Movetop").click(function(){
			$('html, body').animate({
				scrollTop : 0
			}, 400);
			return false;
		});
		
		var list1 = $("#movemanage").offset();
		var list2 = $("#movecal").offset();
		var list3 = $("#moveapply").offset();
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
		
		
		$("#side_movelist1").on("click",function(event){
			$("html body").animate({scrollTop:list1.top},400);
		});
		
		$("#side_movelist2").on("click",function(event){
			$("html body").animate({scrollTop:list2.top},400);
		});
		
		$("#side_movelist3").on("click",function(event){
			$("html body").animate({scrollTop:list3.top},400);
		});
	
	showRange();
	searchMyStudyCalendar();
});
/** 초기화(끝) **/

/* 스터디 멤버 조회 함수 */
function getStudyMemberList(){
	
	var sendData = {
			page :	_pageNo,
			studyCode : $("#studyCode").val(),
			searchStudyMemberId:$('#searchStudyMemberId').val(),
			searchStudyMemberName:$('#searchStudyMemberName').val(),
			searchStudyMemberAddress:$('#searchStudyMemberAddress').val(),
			searchStudyMemberIsAdmin:$('#searchStudyMemberIsAdmin').val()
	}

	$.ajax({
		type: "POST",
 		url : "/studyManagemetAdmin/selectStudyMainMemberList",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		studyMemberManageListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}, 
			    		});
			    	}else{
			    		dToast.push("스터디 인원 목록이 없습니다.");
			    		studyMemberManageListGrid.setData([]);
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

//EnterKeyEvent
function enterKeyEvent() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	_pageNo = 0;
    	getStudyMemberList();
    }
}

// 검색 버튼용 조회 함수
function searchStudyMemberList(){
	_pageNo = 0;
	getStudyMemberList();
}

// 스터디 멤버 관리 팝업 
function selectStudyMemberManage(userCode, userId){
	var parentData={
			userCode:userCode,
			userId:userId
	}
	
	studyMemberManageModal.open({
		width: 600,
		height: 600,
		iframe: {
			method: "post",
			url: "/studyManagement/studyMemberManagePopup.do",
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

// 팝업창 닫고 새로고침
function studyMemberManageCloseWithRefresh(){
	studyMemberManageModal.close();
	window.location.reload();
	
}

// 쪽지 보내기 팝업 닫기
function close(){
	studyMemberManageModal.close();
}