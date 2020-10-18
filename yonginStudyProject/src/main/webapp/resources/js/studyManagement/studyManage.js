/** 변수 설정(시작) **/  
var studyMemberManageListGrid = new ax5.ui.grid();
var studyMemberManageModal = new ax5.ui.modal();	//팝업창 띄우는 modal기능
var studyApplyCheckListGrid = new ax5.ui.grid();
var applicationFormDetailModal = new ax5.ui.modal();		//팝업창 띄우는 modal기능
var changeStudyInfoModal = new ax5.ui.modal();
var changeStudyMemberAdminModal = new ax5.ui.modal();
var cal;
var _pageNo = 0;
var _pageNo2 = 0;
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
					return '<button type="button" onclick="openStudyMemberAdminChange('+this.dindex+')" style="border:transparent; background-color:transparent;outline:none">직위 변경</button>';
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
        	
        	{key : "title", label: "신청서 제목", align: "center", width:"20%"},
        	{key : "userId", label: "신청자 ID", align: "center", width:"20%"},
        	{key : "userArea", label : "신청자 거주지", align : "center",width : "20%"},
        	{key : "rgdtDt", label : "신청 날짜", align : "center",width : "20%"},
        	{key : "", label : "승인", align : "center", width : "10%",
				formatter: function (){
					return '<button type="button" onclick="approveStudyForm('+this.dindex+')" style="border:transparent; background-color:transparent;outline:none;color:green">승인</button>';
       		 	}
        	},
        	{key : "", label : "거부", align : "center", width : "10%",
				formatter: function (){
					return '<button type="button" onclick="rejectStudyForm('+this.dindex+')" style="border:transparent; background-color:transparent;outline:none;color:red">거부</button>';
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
					onDBLClick: function(){
						openApplicationFormDetail([this.dindex]);
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
            display: true,
            onChange: function () {
            	_pageNo2 = this.page.selectPage;
            	getStudyApplicationForm();
                },
            },
        });

		getStudyMemberList();
		getStudyApplicationForm();
		
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
			//$("html body").animate({scrollTop:list2.top},400);
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
			userId:userId,
			studyCode:$("#studyCode").val(),
			studyName:$("#studyName").val()
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

// 스터디 멤버 관리 팝업 닫기
function close(){
	studyMemberManageModal.close();
}

//신청서 조회
function getStudyApplicationForm(){
	var sendData = {
			page :	_pageNo2,
			studyCode : $("#studyCode").val(),
	}
	
	$.ajax({
		type: "POST",
 		url : "/studyManagemetAdmin/selectStudyApplicationForm.json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		studyApplyCheckListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo2 || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}, 
			    		});
			    	}
			    	else{
			    		studyApplyCheckListGrid.setData([]);
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

// 신청 수락
function approveStudyForm(dindex){
	dialog.confirm({
		msg:studyApplyCheckListGrid.list[dindex].userId + " 님의 신청을 승인하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					var sendData = {
							applicationFormCode : studyApplyCheckListGrid.list[dindex].applicationFormCode,
							studyCode:studyCode,
							userCode: studyApplyCheckListGrid.list[dindex].userCode
						}
						
						$.ajax({
							type: "POST",
					 		url : "/studyManagemetAdmin/approveStudyForm.json",
							contentType: "application/json; charset=UTF-8",
							data : JSON.stringify(sendData),
							async: false,
							success : function(data, status, xhr) {
								switch(data.result){
								    case COMMON_SUCCESS:
								    	dialog.confirm({
								    		msg:data.message,
								        	btns:{
								        		yes: {
								        			label:'확인'
								        		},
								        	}
								        }, function(){
								        	if(this.key=="yes" || this.state == "close"){
								        		getStudyMemberList();
								        		getStudyApplicationForm();
								        	}
								    	});
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
			},
			no: {
				label:'아니오', onClick:function(key){
					dialog.close();
					return;
 			}
			}
		}
	}, function(){
	});
}

// 신청 거절
function rejectStudyForm(dindex){
	dialog.confirm({
		msg:studyApplyCheckListGrid.list[dindex].userId + " 님의 신청을 거부하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					var sendData = {
							applicationFormCode : studyApplyCheckListGrid.list[dindex].applicationFormCode,
							studyCode:studyCode,
							userCode: studyApplyCheckListGrid.list[dindex].userCode
						}
						
						$.ajax({
							type: "POST",
					 		url : "/studyManagemetAdmin/rejectStudyForm.json",
							contentType: "application/json; charset=UTF-8",
							data : JSON.stringify(sendData),
							async: false,
							success : function(data, status, xhr) {
								switch(data.result){
								    case COMMON_SUCCESS:
								    	dialog.confirm({
								    		msg:data.message,
								        	btns:{
								        		yes: {
								        			label:'확인'
								        		},
								        	}
								        }, function(){
								        	if(this.key=="yes" || this.state == "close"){
								        		getStudyApplicationForm();
								        	}
								    	});
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
			},
			no: {
				label:'아니오', onClick:function(key){
					dialog.close();
					return;
 			}
			}
		}
	}, function(){
	});
}

//신청서 상세 팝업 열기
function openApplicationFormDetail(dindex){
	
	var parentData={
		applicationFormCode:studyApplyCheckListGrid.list[dindex].applicationFormCode,	 		// 신청서 그리드에서 더블클릭으로 받은 applicationFormCode를 팝업으로 보낼 데이터에 넣음
		type:"studyAdminPageType",
		studyCode:studyCode,
		userCode:studyApplyCheckListGrid.list[dindex].userCode,
	}
	
	applicationFormDetailModal.open({
		width: 800,
		height: 700,
		iframe: {
			method: "post",
			url: "/study/studyApplicationFormDetailForm.do",
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


// 스터디 정보 변경
function openChangeStudyInfo(){
	
	var parentData={
		studyCode:$("#studyCode").val(),
		studyName:$("#studyName").val()
	}
	changeStudyInfoModal.open({
		width: 800,
		height: 900,
		iframe: {
			method: "post",
			url: "/studyManagemet/studyInfoChange.do",
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

// 스터디 멤버 직위 변경
function openStudyMemberAdminChange(dindex){

	var userInfo=studyMemberManageListGrid.list[dindex];
	
	var parentData = {
		userCode:userInfo.userCode,
		userName:userInfo.userName,
		studyCode:$("#studyCode").val(),
		studyName:$("#studyName").val()
	}
	
	changeStudyMemberAdminModal.open({
		width: 800,
		height: 900,
		iframe: {
			method: "post",
			url: "/studyManagement/studyMemberAdminChange.do",
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

// 신청서 상세 팝업 닫기
function closeApplcationFormModal(){
	applicationFormDetailModal.close();
}

//신청서 상세 팝업 닫기
function closeApplcationFormModalRefresh(){
	applicationFormDetailModal.close();
	getStudyMemberList();
	getStudyApplicationForm();
}

function closeStudyChangeInfoModal(){
	changeStudyInfoModal.close();
}

// 직위관리 닫고 새로고침
function studyMemberAdminChangeCloseWithRefresh(){
	changeStudyMemberAdminModal.close();
	window.location.reload();
	
}