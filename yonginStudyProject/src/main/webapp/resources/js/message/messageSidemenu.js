

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
	
});



/*받은쪽지함 */
function openMessageForm(){
	//이걸 쓰면 창이 새로 열리지않고 이동
	location.href = "/getmessage.do";
}


/*쪽지보내기 */
function openSendMessageListForm(){
	location.href ="/sendMessageList.do";
}



/*쪽지보내기 */
function openSendMessageForm(){
	location.href ="/sendMessage.do";
}


