var myParcitipateListGrid= new ax5.ui.grid();
var myMakeListGrid= new ax5.ui.grid();
var myApplicationFormGrid = new ax5.ui.grid();
var emailFixYn = "N";

$(document).ready(function () {
	
	//내가 참여한 스터디 목록
	myParcitipateListGrid.setConfig({   
    	target: $('[data-ax5grid="myParcitipateListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
			{key : "studyTopic", label: "주제", align: "center", width:"45%", sortable: true,
    			formatter:function(){
    			    return studySxnMap[this.value];
    			}
			},
        	{key : "studyName", label: "스터디 이름", align: "center", width:"45%"},
        	{key : "dropStudy", label: "탈퇴", align: "center", width:"10%"},
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 45,
                    
                    onClick: function () 	{
                    
					},
					onDataChanged: function(){
						
					},
                },
        
        page: {
            navigationItemCount: 9,
            height: 30,
            display: true,
            firstIcon: '|<', 
            prevIcon: '<',
            nextIcon: '>',
            lastIcon: '>|',
            display: false,
            onChange: function () {
                },
            },
        });

	//내가 만든 스터디 리스트 관리
	myMakeListGrid.setConfig({   
    	target: $('[data-ax5grid="myMakeListGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
			{key : "studyTopic", label: "주제", align: "center", width:"45%", sortable: true,
    			formatter:function(){
    			    return studySxnMap[this.value];
    			}
			},
        	{key : "studyName", label: "스터디 이름", align: "center", width:"45%"},
        	{key : "manageStudy", label: "관리", align: "center", width:"10%"},
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 45,
                    
                    onClick: function () 	{
                    
					},
					onDataChanged: function(){
						
					},
                },
        
        page: {
            navigationItemCount: 9,
            height: 30,
            display: true,
            firstIcon: '|<', 
            prevIcon: '<',
            nextIcon: '>',
            lastIcon: '>|',
            display: false,
            onChange: function () {
                },
            },
        });

	// 나의 스터디 신청서 내역 목록
	myApplicationFormGrid.setConfig({   
    	target: $('[data-ax5grid="myApplicationFormGrid"]'),
        showLineNumber: false,
        //showRowSelector: true,
        columns: [ 
        	{key : "title", label: "신청서 제목", align: "center", width:"40%"},
        	
			{key : "studyName", label: "스터디 이름", align: "center", width:"40%"},
        	
			{key : "status", label: "상태", align: "center", width:"10%",formatter:function(){
        		if(this.value == "10"){
        			return applicationFormStatusMap[this.value];
        		}
        		else if(this.value == "30"){
					return "<span style="+"font-weight:bold;color:green;"+">"+applicationFormStatusMap[this.value]+"</span>";
				}
        		else if(this.value == "30"){
					return "<span style="+"font-weight:bold;color:red;"+">"+applicationFormStatusMap[this.value]+"</span>";
				}
			}},
        	{key : "dropStudy", label: "취소", align: "center", width:"10%", 
       		 formatter: function (){
       			 if(this.item.status == "10"){
       				 return '<button type="button" onclick="dropStudyForm(' + this.item.applicationFormCode + ')">취소</button>';
       			 }else{
       				 return "";
       			 }
    		 }},
        ],
        header: {
        	align:"center",
        	selector: false
        },
        body: {
                    align: "left",
                    columnHeight: 45,
                    
                    onClick: function () 	{
                    
					},
					onDataChanged: function(){
						
					},
                },
        
        page: {
            navigationItemCount: 9,
            height: 30,
            display: true,
            firstIcon: '|<', 
            prevIcon: '<',
            nextIcon: '>',
            lastIcon: '>|',
            display: false,
            onChange: function () {
                },
            },
        });
	
		getStudyMadeByMeList();
		getParticipateStudyList();


		//클릭시 맨위로
		$("#Movetop").click(function(){
			$('html, body').animate({
				scrollTop : 0
			}, 400);
			return false;
		});
		
		//클릭시 이동
		$("#listMove1").on("click",function(event){
			var offset = $("#list1").offset();
			$("html body").animate({scrollTop:offset.top},400);
		});
		
		$("#listMove2").on("click",function(event){
			var offset = $("#list2").offset();
			$("html body").animate({scrollTop:offset.top},400);
		});
		
		$("#listMove3").on("click",function(event){
			var offset = $("#list3").offset();
			$("html body").animate({scrollTop:offset.top},400);
		});
		
		//전화번호 '-' 자동 붙이기
		var userPhoneNumber = document.getElementById('userPhoneNumber');

		userPhoneNumber.onkeyup = function(){
		  this.value = autoHypenPhone( this.value ) ;  
		}
		
		$("#emailCodeDiv").hide();	//	인증번호 확인 구역 숨김처리
		$("#reSendMailBtn").hide();	//  재전송 버튼 숨김처리
		$("#resetMailBtn").hide();	//  이메일 재설정 버튼 숨김처리
		
		var phoneNumber = $('#userPhoneNumber').val();
		phoneNumber = autoHypenPhone(phoneNumber);
		$('#userPhoneNumber').val(phoneNumber);
});

/*스터디 관리 호출 */
function openstudyManagementForm(){
	window.open("/studyManagement.do",'회원가입','width=700px ,height=900px ,location=no,status=no,scrollbars=no');
}

//주소 검색 함수 세팅
function addressPopup() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('userAddress').value = data.sido + " " + data.sigungu;
        }
    }).open();
}

var autoHypenPhone = function(str){
    str = str.replace(/[^0-9]/g, '');
    var tmp = '';
    if( str.length < 4){
        return str;
    }else if(str.length < 7){
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3);
        return tmp;
    }else if(str.length < 11){
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 3);
        tmp += '-';
        tmp += str.substr(6);
        return tmp;
    }else{              
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 4);
        tmp += '-';
        tmp += str.substr(7);
        return tmp;
    }

    return str;
}

/* 사용자 정보 변경 */
function changeUserInfo(){
	dialog.confirm({
		msg:"수정하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					if($('#userEmail').val() != currentUserEmail){
						if(emailFixYn == "N"){
							dToast.push("이메일 인증을 진행해주세요.");
							return;
						}
					}
					
					var sendData = $('#changeInfoForm').serialize();		// validator를 이용할 경우 serialize를 이용해 데이터 수집

					  $.ajax({
					     type: "POST",
					     url : "/changeUserInfo.json",
					     data: sendData,
					     dataType: "json",
					     //contentType: "application/json; charset=UTF-8", (!중요 : validator를 이용할 경우 contentType은 주석처리)
					     async: false,
					     success : function(data, status, xhr) {
					    	 switch(data.result){
					    	 case COMMON_SUCCESS:
					    		 
					    		 sToast.push(data.message);
					    		 setTimeout(function(){
					    			 location.reload();
					    		 },1500);
					    		 break;
					    	 case COMMON_FAIL:
					    		 dialog.alert(data.message);
					    		 break;
					    	 }
					     },
					     error: function(jqXHR, textStatus, errorThrown) {
					        alert('error = ' + jqXHR.responseText);
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

//이메일 인증번호 전송 버튼
function sendAuthCode(){
	var userEmail = $('#userEmail').val();
	if(userEmail == currentUserEmail){
		dToast.push("해당 이메일은 인증이 완료되어있습니다.");
		return;
	}
	if(userEmail == ""){
		dToast.push("이메일을 입력해주세요.");
		return;
	}
	if(emailFixYn == "Y"){
		dToast.push("이미 이메일 인증이 완료되었습니다.");
		return;
	}
	
	$.ajax({
		type: "POST",
		url : "/register/sendEmailAuthCode.json",
		data: userEmail,
		dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: true,		// true 비동기통신으로 진행해 mask 사용
		beforeSend:function(){
			mask.open({
				content: '<h1><i class="fa fa-spinner fa-spin"></i> Loading...</h1>'
			});
	    },
	    complete:function(){
	    	mask.close();
	    },
		success : function(data, status, xhr) {
			switch(data.result){
		    case COMMON_SUCCESS:
		    	sToast.push(data.message);
		    	
		    	$("#userEmail").attr("readonly",true);
		    	
		    	$("#initSendMailBtn").hide();
		    	$("#emailCodeDiv").show();
		    	$("#reSendMailBtn").show();
		    	$("#resetMailBtn").show();
		    	break;
		    case COMMON_FAIL:
		    	dToast.push(data.message);
		    	break;	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('error = ' + jqXHR.responseText);
		}
	}); 
}

//이메일 인증번호 확인 버튼
function checkAuthCode(){
	var emailCode = $('#emailCode').val();
	if(emailCode == ""){
		dToast.push("인증번호를 입력해주세요.");
		return;
	}
	$.ajax({
		type: "POST",
		url : "/register/checkEmailAuthCode.json",
		data: emailCode,
		dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
		    case COMMON_SUCCESS:
		    	sToast.push(data.message);
		    	emailFixYn = "Y";
		    	
		    	$("#emailCode").attr("readonly",true);
		    	$("#authCodeBtn").val("인증완료");
		    	$("#authCodeBtn").prop("disabled", true);
		    	break;
		    case COMMON_FAIL:
		    	emailFixYn = "N";
		    	dToast.push(data.message);
		    	break;	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('error = ' + jqXHR.responseText);
		}
	}); 
}

//이메일 재입력 함수
function resetAuthCode(){
	$.ajax({
		type: "POST",
		url : "/register/resetEmail.json",
		dataType: "json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
		    case COMMON_SUCCESS:
		    	sToast.push(data.message);
		    	emailFixYn = "N";
		    	
		    	$("#userEmail").attr("readonly",false);
		    	$("#emailCode").attr("readonly",false);
		    	
		    	$("#userEmail").val("");
		    	$("#emailCode").val("");
		    	
		    	$("#initSendMailBtn").show();
		    	$("#emailCodeDiv").hide();	
		    	$("#reSendMailBtn").hide();
		    	$("#resetMailBtn").hide();
		    	
		    	$("#authCodeBtn").val("인증하기");
		    	$("#authCodeBtn").prop("disabled", false);
		    	break;
		    case COMMON_FAIL:
		    	emailFixYn = "N";
		    	dToast.push(data.message);
		    	break;	
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('error = ' + jqXHR.responseText);
		}
	}); 
}

function changePw(){
	window.open("/myPageChangePwForm.do",'비밀번호 변경','width=650px ,height=545px ,location=no,status=no,scrollbars=no');
}

/* 내가 만든 스터디 리스트 조회 함수 */
function getStudyMadeByMeList(){
	
	$.ajax({
 		type: "POST",
 		url : "/myPage/selectStudyMadeByMeList.json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	myMakeListGrid.setData(data.resultList);
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

/* 내가 가입된 스터디 리스트 조회 함수 */
function getParticipateStudyList(){
	
	$.ajax({
 		type: "POST",
 		url : "/myPage/selectParticipateStudyList.json",
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	myParcitipateListGrid.setData(data.resultList);
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