/** 변수 설정(시작) **/
var userListGrid = new ax5.ui.grid();
var studyListGrid = new ax5.ui.grid();
var studyInfoDetailModal = new ax5.ui.modal();

var _pageNo1 = 0;	// 사용자 그리드 페이지
var _pageNo2 = 0;	// 스터디 그리드 페이지 
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	

	
	/*사이드메뉴 */
		$(".side_fixed_menu_title").click(function(){
			var sidemenu = $(".side_fixed_menu_list");
			
			if(sidemenu.is(":visible")){
				sidemenu.slideUp();
			}else{
				sidemenu.slideDown();
			}
		});
	
	//클릭시 맨위로
		$("#Movetop").click(function(){
			$('html, body').animate({
				scrollTop : 0
			}, 400);
			return false;
		});
		
		var list1 = $("#move1").offset();
		var list2 = $("#move2").offset();
	
		//클릭시 이동
		$("#side_movelist1").on("click",function(event){
			$("html body").animate({scrollTop:list1.top},400);
		});
		
		$("#side_movelist2").on("click",function(event){
			$("html body").animate({scrollTop:list2.top},400);
		});
		
	
	
	
	// 사용자 그리드 
	userListGrid.setConfig({   
    	target: $('[data-ax5grid="userListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
			{key : "userId", label: "사용자 아이디", align: "center", width:"23%"},
        	{key : "userName", label: "사용자 이름", align: "center", width:"15%"},
        	{key : "userEmail", label: "사용자 이메일", align: "center", width:"20%"},
        	{key : "userIsAdmin", label: "사용자 권한", align: "center", width:"15%", 
          		 formatter: function (){
          			 if(this.item.userIsAdmin == "Y"){
          				 return '관리자';
          			 }
          			 else{
          				 return '일반 사용자';
          			 }
        		 }},
        	{key : "kickUser", label: "추방", align: "center", width:"15%", 
          		 formatter: function (){
        			 return '<button type="button" class="grid_btn_style_0 banishment" onclick="kickUser(' + this.dindex + ')"  >추방</button>';
        		 }
        	},
        	{key : "manageAdmin", label: "관리자 임명", align: "center", width:"15%", 
         		 formatter: function (){
         			 if(this.item.userIsAdmin == 'Y'){
         				return '<button type="button" class="grid_btn_style_0" onclick="cancleAdmin(' + this.dindex + ')"  >관리자 해제</button>';
         			 }
         			 else{
         				return '<button type="button" class="grid_btn_style_0" onclick="setAdmin(' + this.dindex + ')" >관리자 설정</button>';
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
        	navigationItemCount: 10,
            height: 30,
            display: true,
            firstIcon : '<i class="fa fa-step-backward" aria-hidden="true"></i>',
			prevIcon : '<i class="fa fa-caret-left" aria-hidden="true"></i>',
			nextIcon : '<i class="fa fa-caret-right" aria-hidden="true"></i>',
			lastIcon : '<i class="fa fa-step-forward" aria-hidden="true"></i>',
            display: true,
            onChange: function () {		// 그리드 밑 페이지 번호로 이동했을 때
            	_pageNo1 = this.page.selectPage;
            	getUserList();
            	},
            },
        });
	
	
	// 스터디 그리드 
	studyListGrid.setConfig({   
    	target: $('[data-ax5grid="studyListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
        	{key : "studyTopic", label: "스터디 주제", align: "center", width:"15%",
    			formatter:function(){
    			    return studySxnMap[this.value];
    			}
        	},
        	{key : "studyName", label: "스터디 이름", align: "center", width:"30%"},
        	{key : "studyArea", label: "스터디 지역", align: "center", width:"15%"},
        	{key : "userName", label: "스터디 방장", align: "center", width:"15%"},
        	{key : "totalCount",label : "현재 인원", align : "center",width : "10%"},
        	{key : "studyLimit",label : "정원", align : "center",width : "5%"},
        	{key : "deleteStudy", label: "삭제", align: "center", width:"10%", 
          		 formatter: function (){
        			 return '<button type="button" class="grid_btn_style_0" onclick="deleteStudy(' + this.dindex + ')" >삭제</button>';
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
                    	selectStudyInfoDetail(this.list[this.dindex]["studyCode"]);
					},
					onDataChanged: function(){
						
					},
                },
        
        page: {
        	navigationItemCount: 10,
            height: 30,
            display: true,
            firstIcon : '<i class="fa fa-step-backward" aria-hidden="true"></i>',
			prevIcon : '<i class="fa fa-caret-left" aria-hidden="true"></i>',
			nextIcon : '<i class="fa fa-caret-right" aria-hidden="true"></i>',
			lastIcon : '<i class="fa fa-step-forward" aria-hidden="true"></i>',
            display: true,
            onChange: function () {		// 그리드 밑 페이지 번호로 이동했을 때
            	_pageNo2 = this.page.selectPage;
            	getStudyList();
            	},
            },
        });
	
	getUserList();	// 사용자 목록 조회
	getStudyList();	// 스터디 목록 조회
});
/** 초기화(끝) **/

/* 사용자 리스트 조회 함수 */
function getUserList(){
	
	var sendData = {
			page : _pageNo1,
			searchUserId:$('#searchUserId').val(),
			searchUserName:$('#searchUserName').val(),
			searchUserEmail:$('#searchUserEmail').val(),
			searchUserIsAdmin:$('#searchUserIsAdmin').val()
	}
	
	$.ajax({
 		type: "POST",
 		url : "/adminPage/selectUserList.json",
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(sendData),
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		userListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo1 || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}
			    		});
			    	}else{
			    		dToast.push("사용자 목록이 없습니다.");
			    		userListGrid.setData([]);
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
function enterKeyEvent1() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	_pageNo1 = 0;
    	getUserList();
    }
}

// 검색용 버튼 함수
function searchUserList(){
	_pageNo1 = 0;
	getUserList();
}

/* 스터디 리스트 조회 함수 */
function getStudyList(){
	
	var sendData = {
			page : _pageNo2,
			searchStudyTopic:$('#studyTopic').val(),
			searchStudyName:$('#studyName').val(),
			searchStudyArea:$('#studyArea').val()
	}

	$.ajax({
 		type: "POST",
 		url : "/study/selectStudyList.json",
 		data : JSON.stringify(sendData),
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	if(data.resultList.length>0){
			    		studyListGrid.setData({
			    			list: data.resultList,
			    		 	page: {
			    		 		currentPage: _pageNo2 || 0,
			    			 	pageSize: data.dataPerPage,
			    			 	totalElements: data.total,
			    			 	totalPages: data.totalPages
			    		 	}
			    		});
			    	}else{
			    		dToast.push("스터디 목록이 없습니다.");
			    		studyListGrid.setData([]);
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

// 스터디 정보 확인 
function selectStudyInfoDetail(studyCode){
	var parentData={
		studyCode:studyCode	 		// 스터디 그리드에서 선택한 studyCode를 팝업으로 보낼 데이터에 넣음
	}
	
	studyInfoDetailModal.open({
		width: 800,
		height: 710,
		iframe: {
			method: "post",
			url: "/study/studyInfoDetailPopup.do",
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

//EnterKeyEvent
function enterKeyEvent2() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
    	_pageNo2 = 0;
    	getStudyList();
    }
}

// 검색용 버튼 함수
function searchStudyList(){
	_pageNo2 = 0;
	getStudyList();
}

// 사용자 추방
function kickUser(dindex){
	var userCode = userListGrid.list[dindex].userCode;	//	사용자 코드
	
	if(userCode == null || userCode == undefined || userCode == "" ){
		dialog.alert("사용자 코드가 존재하지 않습니다.");
		return;
	}
	
	dialog.confirm({
		msg:userListGrid.list[dindex].userName + " 님을 추방 하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					$.ajax({
				 		type: "POST",
				 		url : "/adminPage/kickUser.json",
						contentType: "application/json; charset=UTF-8",
						data : userCode,
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
							        	console.log(this);
							        	if(this.key=="yes" || this.state == "close"){
							        		window.location.reload();
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

// 관리자 취소 함수
function cancleAdmin(dindex){
	var userCode = userListGrid.list[dindex].userCode;	//	사용자 코드
	
	if(userCode == null || userCode == undefined || userCode == "" ){
		dialog.alert("사용자 코드가 존재하지 않습니다.");
		return;
	}
	
	dialog.confirm({
		msg:userListGrid.list[dindex].userName + " 님의 관리자 권한을 해제하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					$.ajax({
				 		type: "POST",
				 		url : "/adminPage/cancleAdminUser.json",
						contentType: "application/json; charset=UTF-8",
						data : userCode,
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
							        	console.log(this);
							        	if(this.key=="yes" || this.state == "close"){
							        		window.location.reload();
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

// 관리자 설정 함수
function setAdmin(dindex){
	var userCode = userListGrid.list[dindex].userCode;	//	사용자 코드
	
	if(userCode == null || userCode == undefined || userCode == "" ){
		dialog.alert("사용자 코드가 존재하지 않습니다.");
		return;
	}
	
	dialog.confirm({
		msg:userListGrid.list[dindex].userName + " 님에게 관리자 권한을 부여하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					$.ajax({
				 		type: "POST",
				 		url : "/adminPage/setAdminUser.json",
						contentType: "application/json; charset=UTF-8",
						data : userCode,
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
							        	console.log(this);
							        	if(this.key=="yes" || this.state == "close"){
							        		window.location.reload();
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

// 스터디 삭제 함수
function deleteStudy(dindex){
	var studyCode = studyListGrid.list[dindex].studyCode;	//	사용자 코드
	
	if(studyCode == null || studyCode == undefined || studyCode == "" ){
		dialog.alert("스터디 코드가 존재하지 않습니다.");
		return;
	}
	
	dialog.confirm({
		msg: studyListGrid.list[dindex].studyName + " 스터디를 삭제하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					$.ajax({
				 		type: "POST",
				 		url : "/adminPage/deleteStudy.json",
						contentType: "application/json; charset=UTF-8",
						data : studyCode,
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
							        	console.log(this);
							        	if(this.key=="yes" || this.state == "close"){
							        		window.location.reload();
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