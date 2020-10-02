/** 변수 설정(시작) **/
var qnaBinder = new ax5.ui.binder();	// Binder 설정(데이터를 받아오면 이 Binder set 함으로써 데이터 자동으로 들어감)
var parentData = self.parent.callBack;		// 부모 페이지에서 보낸 데이터 정의
var fileIndex = 1;
var fileCodeArry = [];
var fileNameArry = [];
/** 변수 설정(끝) **/

/** 초기화(시작) **/
$(document).ready(function () {
	//summernote editor
	$('#boardDesc').summernote({           
	    height: 250,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true,
	});	
	
	qnaBinder.setModel({}, $(document["reviseQnaForm"]));
	
	reviseQna(parentData.boardCode);
	
});
/** 초기화(끝) **/

// 부모 페이지에서 받은 boardCode를 이용해 Qna 조회
function reviseQna(boardCode){
	if(boardCode == ""){
		dialog.alert("정상적인 접근이 아닙니다.");
		return;
	}
	
	$.ajax({
 		type: "POST",
 		url : "/systemQna/selectQnaDetail.json",
 		data : boardCode,
		contentType: "application/json; charset=UTF-8",
		async: true,
		success : function(data, status, xhr) {
			switch(data.result){
			    case COMMON_SUCCESS:
			    	
			    	qnaBinder.setModel(data.boardInfo);
			    	
			    	$('#boardDesc').summernote('code', data.boardInfo.boardDesc);
			    	$('#boardCode').val(boardCode);
			    	
			    	var output="";
			    	
			    	for(var i=0;i<data.fileList.length;i++){
			    		output	+= "<div id="+data.fileList[i].FILE_CODE+"><a href="+"#"+" id="+data.fileList[i].FILE_CODE+" onclick=" + "fileDownFunc(id); return false;" + ">"+data.fileList[i].ORG_FILE_NAME+"</a> ("+data.fileList[i].FILE_SIZE+"kb) <button id="+data.fileList[i].FILE_CODE+" name='"+data.fileList[i].ORG_FILE_NAME+"' onclick='fn_del(id, name, this)'; type='button'>"+"삭제"+"</button></div>"
			    	}
			    	$("#fileListDiv").html(output);
			    	
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

//Qna 수정 함수
function reviseQnaFunc(){
	
	// 폼 데이터 가져옴
    var form = $('#reviseQnaForm')[0];
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/systemQna/reviseQna",
        data: data,
        processData: false,		//필수
        contentType: false,		//필수
        async : false,
        success: function (data) {
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
				        	if(this.key=="yes" || this.state == "close"){
									closeModalWithRefresh();
				        	}
				    	});
		   		 break;
	        case COMMON_FAIL:
		   		 dialog.alert(data.message);
		   		 break;
        	}
        },
        error: function (e) {
        	 console.log('error = ' + e);
        	 console.log(e);
        }
    });
}

//팝업창 닫기
function closeModal(){
	return self.parent.closeQnaModal();		// 부모 페이지의 close함수로 리턴
}

// 팝업창 닫고 새로고침
function closeModalWithRefresh(){
	return window.history.back();
}

//파일 추가
function fn_addFile(){
	$("#fileIndex").append("<div id='fileDiv_"+fileIndex+"' style=''><input type='file' style='float:left;' name='file_"+(fileIndex)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDelBtn' onclick='fileDelFunc("+fileIndex+")'>"+"삭제"+"</button></div>");
	fileIndex++;
}

// 파일 삭제
function fileDelFunc(index){
	$('#fileDiv_'+index).remove();
}

// 첨부되어있는 파일 삭제
function fn_del(fileCode, name, thisElement){
	dialog.confirm({
		msg:name+" 파일을 삭제하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					fileCodeArry.push(fileCode);
					$("#fileCodeDel").val(fileCodeArry);
					$("#"+fileCode).fadeOut();
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

//파일 다운로드 함수
function fileDownFunc(fileCode){
	$("#FILE_CODE").attr("value", fileCode);
	var formObj = $("form[name='readForm']");
	formObj.attr("action", "/fileDown");
	formObj.submit();
}	
