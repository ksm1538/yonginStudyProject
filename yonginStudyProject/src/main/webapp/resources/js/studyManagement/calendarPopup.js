var data = self.parent.callBack.schedule;;

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
	document.getElementById("calendarType").innerText = data.raw;
	$('#typeColor').css("background-color", data.borderColor);
})

function closePopup() {
	self.parent.closeCalenderPopup();
}