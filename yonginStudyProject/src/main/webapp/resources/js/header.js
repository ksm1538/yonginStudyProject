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

/*공지사항 더보기 호출 */
function openMoreNotice(){
	location.href = "/moreNotice.do";
}