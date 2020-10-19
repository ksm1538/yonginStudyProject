/** 변수 설정(시작) **/  
var studyMemberManageBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	
	studyMemberManageBinder.setModel({}, $(document["studyMemberManageForm"]));
	
	selectStudyMemberManagement(parentData.userCode);
	
	$("#detailDiv *").prop("disabled", true);
	
});
/** 초기화(끝) **/

// 해당 멤버 정보 조회
function selectStudyMemberManagement(userCode){
	if(userCode==null || userCode == ""){
		dialog.alert("정상적인 접근이 아닙니다.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/studyManagement/selectStudyMemberManagement.json",
 		data : userCode,
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	studyMemberManageBinder.setModel(data.boardInfo);
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

// 팝업창 닫기
function closeModal(){
	return self.parent.studyMemberManageCloseWithRefresh();		// 부모 페이지의 close함수로 리턴
} 
