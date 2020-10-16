var calendarDetailBinder = new ax5.ui.binder();
var parentData = self.parent.callBack;

$(document).ready(function () {
	//summernote editor
	$('#scheduleWriteDesc').summernote({           
	    height: 200,        
	    codeviewFilter: true,
		codeviewIframeFilter: true,   
		disableDragAndDrop: true
	});	
	
	calendarDetailBinder.setModel({
		calendarCode : "NEW"
	}, $(document["calendarForm"]));

	// 값 넣어주는 과정
	if(self.parent.callBack.calendar != undefined){
		
		
		if(self.parent.callBack.calendar.schedule == undefined){
			inputData(self.parent.callBack.calendar);
		}
		else{
			data = self.parent.callBack.calendar.schedule;
			
			calendarDetailBinder.set("type",  data.raw);

			calendarDetailBinder.set("title", data.title.substring(8, data.title.length));
			
			calendarDetailBinder.set("content", data.body);
			
			calendarDetailBinder.set("calendarCode", data.calendarId);

			inputData(data);
		}
	} else {
		data = self.parent.callBack;
	}
	
	calendarDetailBinder.set("studyCode", parentData.studyCode);
	
	document.getElementById("userInfoSpan").innerText = parentData.studyName + " - " + parentData.userId;
	
	mouseUp();
	
	$('[data-ax5formatter]').ax5formatter();
});

/* 초기 값 세팅 함수 */
function inputData(data) {
	if(data.start == undefined || data.end == undefined){
		return;
	}
	var startMonth = '' + (data.start.getMonth()+1);
	if (startMonth.length < 2) startMonth = '0' + startMonth;
	var startDate = '' + (data.start.getDate());
	if (startDate.length < 2) startDate = '0' + startDate;
	calendarDetailBinder.set("startDt", data.start.getFullYear()+"-"+startMonth+"-"+startDate);
	
	var startHour = '' + (data.start.getHours());
	if (startHour.length < 2) startHour = '0' + startHour;
	var startMinute = '' + (data.start.getMinutes());
	if (startMinute.length < 2) startMinute = '0' + startMinute;
	calendarDetailBinder.set("startHm", startHour+':'+startMinute);
	
	var endMonth = '' + (data.end.getMonth()+1);
	if (endMonth.length < 2) endMonth = '0' + endMonth;
	var endDate = '' + (data.end.getDate());
	if (endDate.length < 2) endDate = '0' + endDate;
	calendarDetailBinder.set("endDt", data.end.getFullYear()+'-'+endMonth+'-'+endDate);
	
	var endHour = '' + (data.end.getHours());
	if (endHour.length < 2) endHour = '0' + endHour;
	var endMinute = '' + (data.end.getMinutes());
	if (endMinute.length < 2) endMinute = '0' + endMinute;
	calendarDetailBinder.set("endHm", endHour+':'+endMinute);
}

function closeWriteModal(){
	return self.parent.closeModal();		// 부모 페이지의 close함수로 리턴
}

function closeWriteModalWithRefresh(){
	return self.parent.closeModalWithRefresh();		// 부모 페이지의 close함수로 리턴
}

/* 코드에 따른 색 변화 함수 */
function mouseUp() {
	var calendarTypeVal = $('#type').val();
	$('#typeColor').css("color", typeSxnMap[calendarTypeVal][1]);
	$('#typeHeaderColor').css("background-color", typeSxnMap[calendarTypeVal][1]);
}

// 일정 저장
function saveSchedule(){
	var sendData = calendarDetailBinder.get();
	
	$.ajax({
 		type: "POST",
 		url : "/studyManagement/saveCalendar.json",
 		data : JSON.stringify(sendData),
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
			        	if(this.key=="yes" || this.state == "close"){
			        		closeWriteModalWithRefresh();
			        	}
			    	});
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