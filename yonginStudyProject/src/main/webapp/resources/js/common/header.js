var makeStudyModal = new ax5.ui.modal();
$(document).ready(function (){
	
		$(".user_id").click(function(){
		$(".user_box_con").fadeIn(600);
	});
	
	$(".circle_btn_2").click(function(){
		$(".user_box_con").fadeOut(600);
	}); 
	
	
});

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

/*메인으로 이동 */
function openHome(){
	//이걸 쓰면 창이 새로 열리지않고 이동
	location.href = "/mainopen.do";
	
}

/*스터디 더보기 호출 */
function openMoreStudyForm(){
	location.href = "/moreStudy.do";
}

/*qna */
function openQna(){
	location.href = "/systemQna.do";
}

/*공지사항 더보기 호출 */
function openMoreNotice(){
	location.href = "/moreNotice.do";
}

/*스터디 공지사항*/
function openStudyNotice(){
	//이걸 쓰면 창이 새로 열리지않고 이동
	location.href = "/studynotice.do";
}

/*스터디  자유게시판*/
function openStudyFreeNotice(){
	//이걸 쓰면 창이 새로 열리지않고 이동
	location.href = "/studyfreenotice.do";
}

// 관리자 페이지 열기
function openAdminPage(){
	location.href = "/openAdminPage.do";
}
