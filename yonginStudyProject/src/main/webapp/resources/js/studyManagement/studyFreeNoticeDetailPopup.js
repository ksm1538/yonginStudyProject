/** 변수 설정(시작) **/  
var studyFreeNoticeInfoDetailBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
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
	
	studyFreeNoticeInfoDetailBinder.setModel({}, $(document["noticeInfoDetailForm"]));
	
	selectStudyFreeNoticeInfoDetail(parentData.boardCode);
	
});
/** 초기화(끝) **/

// 부모 페이지에서 받은 systemNoticeCode를 이용해 공지사항 조회
function selectStudyFreeNoticeInfoDetail(boardCode){
	if(boardCode==null || boardCode == ""){
		dialog.alert("정상적인 접근이 아닙니다.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/studyManagement/selectStudyFreeNoticeInfoDetail.json",
 		data : boardCode,
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	studyFreeNoticeInfoDetailBinder.setModel(data.boardInfo);
			    	$('#boardDesc').summernote('code', data.boardInfo.boardDesc);
			    	
			    	var output="";
			    	
			    	for(var i=0;i<data.fileList.length;i++){
			    		output	+= "<a href="+"#"+" id="+data.fileList[i].FILE_CODE+" onclick=" + "fileDownFunc(id); return false;" + ">"+data.fileList[i].ORG_FILE_NAME+"</a> ("+data.fileList[i].FILE_SIZE+"kb)<br>"
			    	}
			    	$("#fileListDiv").html(output);
			    	
			    	getReplyList(boardCode);	//	댓글 조회
			    	
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

// 파일 다운로드 함수
function fileDownFunc(fileCode){
	$("#FILE_CODE").attr("value", fileCode);
	var formObj = $("form[name='readForm']");
	formObj.attr("action", "/fileDown");
	formObj.submit();
}	

// 팝업창 닫기
function closeModal(){
	return self.parent.detailPopupModalClose();		// 부모 페이지의 close함수로 리턴
}

// 팝업창 닫고 새로고침
function closeModalWithRefresh(){
	self.parent.detailPopupModalCloseWithRefresh();
}

// 수정하기 버튼 함수
function openReviseSystemNotice(){

	location.href = "/studyManagemet/studyFreeNoticeRevise.do";
	//window.open("/notice/reviseNotice.do",'공지사항 수정','width=700px ,height=800px ,location=no,status=no,scrollbars=no');
}

// 삭제하기 버튼 함수
function deleteStudyFreeNotice(){
	

    dialog.confirm({
		msg:"해당 게시물을 삭제하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					var sendData = {
							boardCode:parentData.boardCode
					}
					
					$.ajax({
					     type: "POST",
					     url : "/studyManagement/studyFreeNoticeDelete.json",
					     data: JSON.stringify(sendData),
					     dataType: "json",
					     contentType: "application/json; charset=UTF-8",
					     async: false,
					     success : function(data, status, xhr) {
					    	 switch(data.result){
					    	 case COMMON_SUCCESS:
					    		 dialog.confirm({
							    		msg:data.message,
							        	btns:{
							        		yes: {
							        			label:'확인'
							        		},
							        	}
							        }, function(){
							        	if(this.key=="yes"  || this.state == "close"){
							        		closeModalWithRefresh();
							        	}
							    	});
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


/*** 댓글 관련 함수(시작) ***/

// 댓글 저장 
function saveReply(){
	var sendData = {
			boardCode : parentData.boardCode,
			replyText : $('#replyText').val()
	}
	
	if($("#replyText").val() == ""){
		dToast.push("댓글 내용을 입력해주세요.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/reply/insertReply.json",
 		data : JSON.stringify(sendData),
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	window.location.reload();
			    	
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

// 댓글 가져오기
function getReplyList(boardCode){
	$.ajax({
 		type: "POST",
 		url : "/reply/selectReplyList.json",
 		data : boardCode,
		contentType: "application/json; charset=UTF-8",
		async: false,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	document.getElementById("replyCount").innerText = data.resultList.length;
			    	var replyList = [];
			    	var output = "";

			    	// 댓글 순서 조정(댓글, 대댓글 순서로)
					for(var i=0; i<data.resultList.length; i++) {
						if(data.resultList[i].hgrnkReplyCode == "") {
							replyList.push(data.resultList[i]);
							for(j=i+1; j<data.resultList.length; j++) {
								if(data.resultList[i].replyCode == data.resultList[j].hgrnkReplyCode && data.resultList[j].hgrnkReplyCode != "") {
									replyList.push(data.resultList[j]);
								}
							}
						}
					}

					// 댓글 UI 그리기
					for(var i=0; i<replyList.length; i++) {
						if(replyList[i].hgrnkReplyCode == "") {
							var output = output + "<li style=" + "margin-top:20px;margin-bottom:20px;border-bottom:1px;border-bottom-style:solid;border-color:#efefef;" + "></li>" +
												  "<p><strong><span id="+"replyNm"+i+"></span></strong><span id="+"replyUpdtDt"+i+" style=" + "margin-left:15px;color:grey;font-size:1.2rem;></span>" +
											  	  "<span id="+replyList[i].replyCode+" onclick="+"openReplyModal(id);"+" style="+"float:right;margin-left:10px;color:grey;font-size:1.2rem;cursor:pointer;>댓글쓰기</span>" +
											  	  "<span id="+replyList[i].replyCode+" onclick="+"deleteReply(id);"+" style="+"float:right;margin-left:5px;color:grey;font-size:1.2rem;cursor:pointer;>삭제</span>" +
											  	  "<span id="+replyList[i].replyCode+" onclick="+"openUpdateModal(id);"+" style="+"float:right;margin-left:5px;color:grey;font-size:1.2rem;cursor:pointer;>수정</span></p>" +
											  	  "<p><span id="+"replyCn"+i+"></span></p>";
						} else {
							var output = output + "<p style="+"margin-left:30px;><strong><span id="+"replyNm"+i+"></span></strong><span id="+"replyUpdtDt"+i+" style=" + "margin-left:15px;color:grey;font-size:1.2rem;></span>" +
						  	  					  "<span id="+replyList[i].replyCode+" onclick="+"deleteReply(id);"+" style="+"float:right;margin-left:5px;color:grey;font-size:1.2rem;cursor:pointer;>삭제</span>" +
						  	  					  "<span id="+replyList[i].replyCode+" onclick="+"openUpdateModal(id);"+" style="+"float:right;margin-left:5px;color:grey;font-size:1.2rem;cursor:pointer;>수정</span></p>" +
						  	  					  "<p style="+"margin-left:30px;><span id="+"replyCn"+i+"></span></p>";
						}
					}
					
					$("#replyList").html(output);
					
					for(var i=0; i<replyList.length; i++) {
						document.getElementById("replyNm"+i).innerText = replyList[i].rgstusId;
						document.getElementById("replyUpdtDt"+i).innerText = replyList[i].updtDt;
						document.getElementById("replyCn"+i).innerText = replyList[i].replyText;
					}
					
			    	
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

// 댓글 삭제
function deleteReply(id){
	dialog.confirm({
		msg:"해당 댓글을 삭제하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					$.ajax({
				 		type: "POST",
				 		url : "/reply/deleteReply.json",
				 		data : id,
						contentType: "application/json; charset=UTF-8",
						async: false,
						success : function(data, status, xhr) {
							switch(data.result){
							    case COMMON_SUCCESS:
							    	window.location.reload();
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

/* 댓글 작성 호출 */
function openReplyModal(id){
	var parentData2 = {
			replyCode : id,
			boardCode : parentData.boardCode
	}
	
	replyModal.open({
		width: 600,
		height: 310,
		iframe: {
			method: "post",
			url: "/reply/replyOnReplyForm.do",
			param: callBack = parentData2
		},
		onStateChanged: function(){
			if (this.state === "open") {
	        	mask.open();
	        }
	        else if (this.state === "close") {
	        	mask.close();
	        }
	    },
	}, function() {
	});
}

// 댓글 수정 페이지 열람
function openUpdateModal(id){
	var parentData2 = {
			replyCode : id
	}
	
	replyModal.open({
		width: 600,
		height: 310,
		iframe: {
			method: "post",
			url: "/reply/updateReplyForm.do",
			param: callBack = parentData2
		},
		onStateChanged: function(){
			if (this.state === "open") {
	        	mask.open();
	        }
	        else if (this.state === "close") {
	        	mask.close();
	        }
	    },
	}, function() {
	});
}

//댓글 작성 팝업창 닫기
function replyModalClose(){
	replyModal.close();
}

// 댓글 작성 팝업창 닫고 새로고침
function replyModalCloseWithRefresh(){
	replyModal.close();
	window.location.reload();
	
}

/*** 댓글 관련 함수(끝) ***/