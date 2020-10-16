/** 변수 선언(시작) **/
var studyCode;		// 현재 페이지 스터디 코드
var studyName; 		// 현재 페이지 스터디 이름
var userAuthority;		// 현재 접속한 사용자의 권한(10:스터디장, 20:스터디 관리자, 30:일반 스터디 원)
/** 변수 선언(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	studyCode = $("#studyCode").val();
	studyName = $("#studyName").val();
	
	getUserInfo();
	// 홈버튼 제목을 스터디 이름으로
	document.getElementById("spanStudyName").innerText = studyName;
});
/** 초기화(끝) **/

/*공지사항 호출*/
function openStudyNotice(){
	var frmPop= document.dataForm;
    var url = '/studynotice.do';
     
    
    frmPop.method="post";
    frmPop.action = url;
    frmPop.target = studyName; 
    frmPop.studyCode.value = studyCode;
    frmPop.studyName.value = studyName;
    frmPop.submit();   
    
	//이걸 쓰면 창이 새로 열리지않고 이동
	//location.href = "/studynotice.do";
	
}

/*스터디 홈*/
function openHome(){
	var frmPop= document.dataForm;
    var url = '/studyManagement/studyMain.do';
     
    
    frmPop.method="post";
    frmPop.action = url;
    frmPop.target = studyName; 
    frmPop.studyCode.value = studyCode;
    frmPop.studyName.value = studyName;
    frmPop.submit();   
    
	//이걸 쓰면 창이 새로 열리지않고 이동
	//location.href = "/studynotice.do";
	
}

/*자유게시판 호출*/
function openStudyFreeNotice(){
	var frmPop= document.dataForm;
    var url = '/studyfreenotice.do';
     
    
    frmPop.method="post";
    frmPop.action = url;
    frmPop.target = studyName; 
    frmPop.studyCode.value = studyCode;
    frmPop.studyName.value = studyName;
    frmPop.submit();
    
	//이걸 쓰면 창이 새로 열리지않고 이동
	//location.href = "/studyfreenotice.do";
	
}

/*마이페이지 호출*/
function openMypageForm(){
	//이걸 쓰면 창이 새로 열리지않고 이동
	location.href = "/myPage.do";
	
	//이걸 쓰면 창이 새로 열림
	//window.open("/myPage.do");
}

/*쪽지함 이동*/
function openMessageForm(){
	//이걸 쓰면 창이 새로 열리지않고 이동
	location.href = "/message.do";
}

/*관리자 페이지 이동*/
function openStudyAdminPage(){
	var frmPop= document.dataForm;
    var url = '/studyManagement/studyManage.do';
     
    
    frmPop.method="post";
    frmPop.action = url;
    frmPop.target = studyName; 
    frmPop.studyCode.value = studyCode;
    frmPop.studyName.value = studyName;
    frmPop.submit();
}

// 사용자 정보 가져옴
function getUserInfo(){
	
	$.ajax({
 		type: "POST",
 		url : "/studyManagement/selectStudyUserInfo.json",
 		data : studyCode,
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	userAuthority = data.userinfo;
			    	
			    	// 스터디 장 or 관리자가 아니면 관리 버튼 숨김
			    	if(!(userAuthority == 10 || userAuthority == 20)){
			    		$("#adminDiv").hide();
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