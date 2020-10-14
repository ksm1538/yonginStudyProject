var wsocket;
var studyCode;
var studyName;
var nickname;

$(document).ready(function(){
	studyCode = $("#studyCode").val();
	studyName = $("#studyName").val();
	nickname = $("#userId").val();
	
	
	document.getElementById("studyNameSpan").innerText = studyName + " 대화방";	
	
    connect();	// 웹 소켓에 연결
    
    /** EnterKey Event **/
	$('#message').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
            send(); 
        }
            event.stopPropagation();
    });
});

function connect() {
    
    //wsocket = new SockJS("/echo");
	wsocket = new SockJS("http://localhost:8080/studyChatWS");
    //sock = new SockJS("http://localhost:8080/study/echo");
    wsocket.onopen = onOpen;
    wsocket.onmessage = onMessage;
    wsocket.onclose = onClose;
    
}

// 나가기 버튼 클릭시 작동 함수
function disconnect() {
	wsocket.send(JSON.stringify({studyCode : studyCode, type:'LEAVE', writer:nickname}));
	wsocket.close();
	window.close();
}

//소켓이 연결되면 자동으로 발동
function onOpen(evt) {
	wsocket.send(JSON.stringify({studyCode : studyCode, type:'ENTER', writer:nickname}));
}

function onMessage(evt) {
    var data = evt.data;
    appendMessage(data)
}

function onClose(evt) {
	disconnect();
}

function send() {
    var msg = $("#message").val();
    wsocket.send(JSON.stringify({studyCode : studyCode, type:'CHAT',writer:nickname,message : msg}));
    $("#message").val("");
}

// 메시지를 화면에 표시하는 함수
function appendMessage(msg) {
    
    // 메세지 입력창에 msg를 하고 줄바꿈 처리
    $("#chatMessageArea").append(msg+"<br>");
    
    // 채팅창의 heigth를 할당
    var chatAreaHeight = $("#chatArea").height();
    
    // 쌓인 메세지의 height에서 채팅창의 height를 뺀다
    // 이를 이용해서 바로 밑에서 스크롤바의 상단여백을 설정한다
    var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
    
    /* .scrollTop(int) : Set the current vertical position of the scroll bar
                         for each of the set of matched elements.*/
    // .scrollTop(int) : 파라미터로 들어간 px 만큼 top에 공백을 둔 채
    //                   스크롤바를 위치시킨다
    $("#chatArea").scrollTop(maxScroll);
}
