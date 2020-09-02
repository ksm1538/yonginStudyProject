
var today = null;
var year = null;
var month = null;
var firstDay = null;
var lastDay = null;
var $tdDay = null;
var $tdSche = null;

$(document).ready(function(){
    drawCalender();
    initDate();
    drawDate();
    $("#prev_month").on("click", function(){movePrevMonth();});
    $("#next_month").on("click", function(){moveNextMonth();});
});

//날짜 초기화
function initDate(){
        $tdDay = $("td div.cal_day")
        $tdSche = $("td div.cal_schedule")
        dayCount = 0;
        today = new Date();
        year = today.getFullYear();
        month = today.getMonth()+1;
        if(month < 10){month = "0"+month;}
        firstDay = new Date(year,month-1,1);
        lastDay = new Date(year,month,0);
}



//날짜 표시 
function drawDate(){
    $("#choice_year").text(year);
    $("#choice_month").text(month);
    for(var i=firstDay.getDay();i<firstDay.getDay()+lastDay.getDate();i++){
        $tdDay.eq(i).text(++dayCount);
    }
    for(var i=0;i<42;i+=7){
        $tdDay.eq(i).css("color","red");
    }
    for(var i=6;i<42;i+=7){
        $tdDay.eq(i).css("color","blue");
    }
}

//달력 그리기
function drawCalender(){
    var setTable = "";
    setTable+='<table class="calender">';
    setTable+='<tr><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr>';
    for(var i=0;i<6;i++){
        setTable+='<tr height="70">';
        for(var j=0;j<7;j++){
            setTable +='<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap">';
            setTable +='    <div class="cal_day"></div>';
            setTable +='    <div class="cal_schedule"></div>';
            setTable +='</td>';
        }
        setTable+='</tr>';
    }
    setTable+='</table>';
    $("#calender_date").html(setTable);
}

//s


//월 이동
function movePrevMonth(){
    month--;
    if(month<=0){
        month=12;
        year--;
    }
    if(month<10){
        month=String("0"+month);
    }
    getNewInfo();
    }

function moveNextMonth(){
    month++;
    if(month>12){
        month=1;
        year++;
    }
    if(month<10){
        month=String("0"+month);
    }
    getNewInfo();
}


function getNewInfo(){
    for(var i=0;i<42;i++){
        $tdDay.eq(i).text("");
    }
    dayCount=0;
    firstDay = new Date(year,month-1,1);
    lastDay = new Date(year,month,0);
    drawDate();
}