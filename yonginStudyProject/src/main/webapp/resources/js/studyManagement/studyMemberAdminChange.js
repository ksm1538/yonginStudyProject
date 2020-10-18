/** 변수 설정(시작) **/  
var studyMemberAdminChangeBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
/** 변수 설정(끝) **/

$(document).ready(function () {
	// DIV안에 있는 요소 비활성화
	$("#studyNameDiv *").prop("disabled", true);
	$("#userNameDiv *").prop("disabled", true);
	$("#studyAuthorityDiv *").prop("disabled", true);
	
	studyMemberAdminChangeBinder.setModel({}, $(document["studyMemberAdminChangeForm"]));
	
	studyMemberAdminoView();
});

// 부모 페이지에서 받은 userCode를 이용해 스터디 정보 조회
function studyMemberAdminoView(){
	if(parentData.userCode==null || parentData.userCode == "" || parentData.studyCode == "" || parentData.studyCode == ""){
		dialog.alert("정상적인 접근이 아닙니다.");
		return;
	}
	
	var sendData = {
		userCode:parentData.userCode,
		studyCode:parentData.studyCode
	}
	
	$.ajax({
 		type: "POST",
 		url : "/studyManagement/selectStudyMemberAdminView.json",
 		data: JSON.stringify(sendData),
	     dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	studyMemberAdminChangeBinder.setModel(data.boardInfo);
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
	return self.parent.studyMemberAdminChangeCloseWithRefresh();		// 부모 페이지의 close함수로 리턴
} 