var data = self.parent.callBack.schedule;
var userAuthority = self.parent.callBack.userAuthority;

$(document).ready(function() {
	var startMonth = '' + (data.start.getMonth()+1);
	if (startMonth.length < 2) startMonth = '0' + startMonth;
	var startDate = '' + (data.start.getDate());
	if (startDate.length < 2) startDate = '0' + startDate;
	
	var startHour = '' + (data.start.getHours());
	if (startHour.length < 2) startHour = '0' + startHour;
	var startMinute = '' + (data.start.getMinutes());
	if (startMinute.length < 2) startMinute = '0' + startMinute;
	
	var endMonth = '' + (data.end.getMonth()+1);
	if (endMonth.length < 2) endMonth = '0' + endMonth;
	var endDate = '' + (data.end.getDate());
	if (endDate.length < 2) endDate = '0' + endDate;
	
	var endHour = '' + (data.end.getHours());
	if (endHour.length < 2) endHour = '0' + endHour;
	var endMinute = '' + (data.end.getMinutes());
	if (endMinute.length < 2) endMinute = '0' + endMinute;
	document.getElementById("title").innerText = data.title.substring(8, data.title.length);
	document.getElementById("calendarDt").innerText = data.start.getFullYear()+'-'+startMonth+'-'+startDate 
												  + ' ' + startHour+':'+startMinute
												  + ' ~ ' + 
												  data.end.getFullYear()+'-'+endMonth+'-'+endDate
												  + ' ' + endHour+':'+endMinute;
	document.getElementById("calendarContent").innerText = data.body;
	document.getElementById("attendeesStudy").innerText = data.attendees[0] + ' - ' + data.attendees[1];
	document.getElementById("calendarType").innerText = self.parent.callBack.typeName;
	$('#typeColor').css("background-color", data.borderColor);
	
	// 스터디장 or 관리자가 아니면 달력 수정 
	if(!(userAuthority == 10 || userAuthority == 20)){
		$("#updateCalendarBtn").hide();
		$("#deleteCalendarBtn").hide();
		
		$("#closeCalendarBtn").width("82%");
		
	}
})

function closePopup() {
	self.parent.closeCalenderPopup();
}

function closePopupModalWithRefresh(){
	self.parent.closeCalenderPopupWithRefresh();
}

// 일정 수정
function updateSchedule(){
	self.parent.updateCalendar(self.parent.callBack);
}

// 일정 삭제
function deleteSchedule(){
	dialog.confirm({
		msg:"해당 일정을 삭제하시겠습니까?",
		btns:{
			yes: {
				label:'네', onClick:function(key){
					dialog.close();
					
					var sendData = {
						calendarCode : data.calendarId,
						studyCode : data.id
					}
						
					$.ajax({
				 		type: "POST",
				 		url : "/studyManagement/deleteCalendar.json",
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
							        		closePopupModalWithRefresh();
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