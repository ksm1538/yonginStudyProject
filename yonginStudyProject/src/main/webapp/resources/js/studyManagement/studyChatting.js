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
	wsocket = new SockJS("/studyChatWS");
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
    console.log(data);
    data = data.substr(1, data.length-2);	// 처음과 끝 "" 제거

    if (data.substring(0, 5) == "CHAT:") {
        appendMessage(data.substring(5));
    }
    else if (data.substring(0, 5) == "USER:") {
    	appendUserList(data.substring(5));
    }
}

function onClose(evt) {
	disconnect();
}

function send() {
    var msg = $("#message").val();
    if(msg.length>7000){
    	dialog.alert("최대 7000 자까지 입력할 수 있습니다.");
    	return;
    }
    wsocket.send(JSON.stringify({studyCode : studyCode, type:'CHAT',writer:nickname,message : msg}));
    $("#message").val("");
}

// 메시지를 화면에 표시하는 함수
function appendMessage(msg) {
    let now = new Date();
    
    //now.toLocaleTimeString()은 현재 시각 표시하는 기능
    var message = "(" + now.toLocaleTimeString() + ")  " + msg;
    
    // msg에서 사용자 이름 따로 추출
    var name = msg.substring(0, nickname.length);
    
    // 현재 접속자 아이디와 비교해서 같으면
    if(name == nickname){
    	 $("#chatMessageArea").append("<span style='float:right'>"+message+"</span>"+"<br>");
    }
    else{
    	// 메세지 입력창에 msg를 하고 줄바꿈 처리
        $("#chatMessageArea").append("<span>"+message+"</span>"+"<br>");
    }
    
    
    
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

// 사용자 리스트를 화면에 표시하는 함수
function appendUserList(userList){
	userList = userList.substr(1, userList.length-2);	// [] 제거
	userList = userList.replace(" ", "");	//공백 제거
	
	userListArray = userList.split(",");	// String to Array
	
	$("#userListArea").empty();	// 해당 DIV의 내용 제거
	
	for(var i=0;i<userListArray.length;i++){
		$("#userListArea").append(userListArray[i]+"<br>");
		var userAreaHeight = $("#userArea").height();
		var maxScroll = $("#userListArea").height() - userAreaHeight;
		$("#userArea").scrollTop(maxScroll);
	}
	
}
