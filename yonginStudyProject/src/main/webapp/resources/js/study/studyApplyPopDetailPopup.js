/** 변수 설정(시작) **/
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
var studyApplicationFormDetailBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	$("#readonlyDiv").hide();
	$("#adminDiv").hide();
	
	if(parentData.type == "myPageType"){
		$("#readonlyDiv").show();
		$("#title").attr("readonly",false);
	}
	else if(parentData.type == "studyAdminPageType"){
		$("#adminDiv").show();
		$("#title").attr("readonly",true);
	}
	else{
		
	}
	
	studyApplicationFormDetailBinder.setModel({}, $(document["applicationFormDetailPopupForm"]));
	
	selectStudyApplicationForm();
	
});
/** 초기화(끝) **/

// 스터디 신청서 정보 가져오기
function selectStudyApplicationForm(){
	var sendData = {
			applicationFormCode:parentData.applicationFormCode
	}
	$.ajax({
 		type: "POST",
 		url : "/study/selectStudyApplicationForm.json",
 		data : JSON.stringify(sendData),
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	console.log(data.resultVO);
			    	// Binder에 데이터 넣어줌(자동으로 data-ax-path 랑 매치되면 알아서 대입됨)
			    	studyApplicationFormDetailBinder.setModel(data.resultVO);
			    	if(parentData.type == "studyAdminPageType"){
			    		readonly();
			    	}
			    	else if(parentData.type == "myPageType"){
			    		if(data.resultVO.checkYn == "N"){
			    			//summernote editor
				    		$('#applicationFormDesc').summernote({           
				    		    height: 250,        
				    		    codeviewFilter: true,
				    			codeviewIframeFilter: true,   
				    			disableDragAndDrop: true
				    		});	
			    		}
			    		else if(data.resultVO.checkYn == "Y"){
			    			readonly();
			    		}
			    	}
			    	
			    	//summernote는 따로 넣어줘야함 이런방식으로. Binder가 적용안됨
			    	$('#applicationFormDesc').summernote('code', data.resultVO.applicationFormDesc);
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

// 수정하기 버튼
function updateStudyApplicationForm(){
	var sendData = {
			title:$('#title').val(),
			applicationFormDesc:$('#applicationFormDesc').val(),
			applicationFormCode:studyApplicationFormDetailBinder.get("applicationFormCode")
	}
	
	$.ajax({
 		type: "POST",
 		url : "/study/updateStudyApplicationForm.json",
 		data : JSON.stringify(sendData),
		contentType: "application/json; charset=UTF-8",
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
			        		closeModalRefresh();
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

//팝업창 닫기
function closeModal(){
	return self.parent.closeApplcationFormModal();		
}

// 팝업창 닫고 부모페이지 새로고침
function closeModalRefresh(){
	return self.parent.closeApplcationFormModalRefresh();		
}

//신청 수락
function approveStudyForm(){
	dialog.confirm({
		msg: "해당 신청서를 승인하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					var sendData = {
							applicationFormCode : parentData.applicationFormCode,
							studyCode : parentData.studyCode,
							userCode: parentData.userCode
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
								        		closeModalRefresh();
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
function rejectStudyForm(){
	dialog.confirm({
		msg: "해당 신청서를 거부하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					var sendData = {
							applicationFormCode : parentData.applicationFormCode,
							studyCode : parentData.studyCode,
							userCode: parentData.userCode
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
								        		closeModalRefresh();
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

function readonly(){
	$("#detailDiv *").prop("disabled", true);
	$("#updateAFBtn").hide();
	$('#applicationFormDesc').summernote({           
	    height: 250,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true,
		toolbar:[]
	});	
	//섬머노트 비활성화(readonly)
	$('#applicationFormDesc').summernote('disable');
	$("#title").attr("readonly",true);
}
