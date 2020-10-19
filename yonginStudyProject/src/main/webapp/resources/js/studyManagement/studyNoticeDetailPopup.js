/** 변수 설정(시작) **/
var studyNoticeDetailBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
var replyModal = new ax5.ui.modal();
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	//summernote editor
	$('#boardDesc').summernote({           
	    height: 250,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true,
		toolbar:[]
	});	
	// DIV안에 있는 요소 비활성화
	$("#detailDiv *").prop("disabled", true);
	//섬머노트 비활성화(readonly)
	$('#boardDesc').summernote('disable');
	
	systemNoticeInfoDetailBinder.setModel({}, $(document["noticeInfoDetailForm"]));
	
	selectSystemNoticeInfoDetail(parentData.boardCode);
	
});