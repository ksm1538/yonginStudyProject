
var rolling = {

    rollingList : null //롤링될 리스트
    ,rollingLen : 0 //리스트 전체 길이 (전체 페이지수 확인위해)
    ,activeIndex : 0 //보여지는 리스트 인덱스
    ,selectedIndex : 0 //이전에 선택된 리스트 인덱스
    ,gap : 0 // absolute시 사이여백
    ,movePosX : 0
    ,finishPosX : 0

    ,leftBtn : null // 이전버튼
    ,rightBtn : null // 다음 버튼
    ,indexBtnList : null
    ,direction : "right"
    ,playInterval : null
    ,isSlide : false
    ,init : function(){

        // 정의
        this.rollingList = $(".main_banner_list_con > .main_banner_list");
        this.rollingLen = this.rollingList.length;
        this.leftBtn = $(".before_page");
        this.rightBtn = $(".next_page");

        // 포지션 세팅
        this.defaultPositionSetting();

        this.autoPlay();

        // 이벤트 등록
        this.registEvent();




    }

    ,defaultPositionSetting : function(){

        $(this.rollingList).css("opacity", 0);
        $(this.rollingList[this.activeIndex]).css("opacity", 1);

        /*	var _this = this;
         $(this.rollingList).css("position", "absolute");
         for( var i=0; i<this.rollingLen; i++ ){
         var left = ( $(this.rollingList[i]).width() + this.gap ) * i;
         if( i == this.rollingLen-1 ){
         left = ( $(this.rollingList[i]).width() + this.gap ) * -1;
         }else{
         left = ( $(this.rollingList[i]).width() + this.gap ) * i;
         }
         console.log( left );


         $(this.rollingList[i]).css("left", left + "px");

         }*/


    }


    ,registEvent : function(){
        var _this = this;

        var startPosX;



      


       



        //마우스 클릭 했을 때
        this.rollingList.mousedown(function(event){

            event.preventDefault();
            //console.log( _this.activeIndex );


            _this.dragX = event.pageX // 드래그 시작 x 좌표
            $(_this.rollingList[_this.activeIndex]).addClass('grab');
            _this.autoStop(); // 배너 클릭시 멈춤(안넘어감)

            dragStart( event );

            //마우스가 움직이는 이벤트 on
            _this.rollingList.on("mousemove", drag);


        });



        //마우스 클릭 땠을 때
        this.rollingList.mouseup(function(event){



            event.stopPropagation();

            _this.dragCondition = false; // false -> true

            $(_this.rollingList[_this.activeIndex]).removeClass('grab');

            //마우스가 움직이는 이벤트 off
            _this.rollingList.off("mousemove");

            _this.finishPosX = _this.movePosX;
            console.log("마지막 move " + _this.finishPosX);

            if(_this.finishPosX < -350 ){
                _this.slide();
                console.log("오른쪽");
            }
            else if(_this.finishPosX > 350){
                console.log("왼쪽");
                _this.slide();
            }
            else{
                console.log("원상복귀");
                $(_this.rollingList[_this.activeIndex]).css("transform", 'translateX(0px)');
                $(_this.rollingList[_this.nextIndex]).css({"transform": 'translateX('+_this.width+'px)', opacity : 1});
                $(_this.rollingList[_this.prevIndex]).css({"transform": 'translateX(-'+_this.width+'px)', opacity : 1});
            }


            _this.finishPosX = 0;
            _this.autoStop();
            _this.autoPlay();

        });




//		
    }

    ,autoPlay : function(){
        var _this = this;
        _this.playInterval = setInterval(function(){_this.slide();},2500);
    }

    ,autoStop : function(){
        if( this.playInterval != null ){
            clearInterval( this.playInterval );
            this.playInterval = null;
        }
    }






    ,slide : function(){

        var _this = this;



        //슬라이드 넘어가는순간 버튼 눌러도 넘어가지 않게 상태 확인
        if( _this.isSlide == true ){
            return;
        }

        _this.isSlide = true;
        _this.autoStop();


        //left 버튼 클릭시 left 값 들어옴 left면 인덱스 --
        if( _this.direction == "left" ){
            _this.activeIndex--;
        }else{
            _this.activeIndex++;
        }

        if(_this.activeIndex > _this.rollingLen - 1 ){
            _this.activeIndex = 0;
        }else if( _this.activeIndex < 0 ){
            _this.activeIndex = _this.rollingLen - 1;
        }

        // 보낼 비쥬얼 영역
        if( _this.direction == "left" ){
            $(_this.rollingList[_this.activeIndex]).css("transform", 'translateX(-100%)');
            TweenMax.to($(_this.rollingList[_this.selectedIndex]), 1, {transform:'translateX(100%)', opacity:1});
        }else{
            $(_this.rollingList[_this.activeIndex]).css("transform", 'translateX(100%)');
            TweenMax.to($(_this.rollingList[_this.selectedIndex]), 1, {transform:'translateX(-100%)', opacity:1});

        }
        // 가져올 비쥬얼 영역
        TweenMax.to($(_this.rollingList[_this.activeIndex]), 1, {transform:'translateX(0%)', opacity:1 , onComplete : function(){
            _this.isSlide = false;
            _this.selectedIndex = _this.activeIndex;
        }});





        $('.start_num').text(this.activeIndex + 1);

        _this.autoPlay();

//		setTimeout(function(){
//			_this.isSlide = false;
//		}, 300);

    }



    //좌우 통합
    /*if(this.activeIndex < 0) {this.activeIndex = this.rollingLen - 1;}
     else if(this.activeIndex > this.rollingLen - 1) {this.activeIndex = 0;}

     $(this.rollingList[this.activeIndex]).addClass('on');
     $(this.rollingList[this.activeIndex]).siblings().removeClass('on');

     $(this.rollingList[this.activeIndex + 1]).addClass('right');
     $(this.rollingList[this.activeIndex - 1]).addClass('left');

     $(this.rollingList[this.activeIndex + 1]).siblings().removeClass('right');
     $(this.rollingList[this.activeIndex - 1]).siblings().removeClass('left');


     if(this.activeIndex == this.rollingLen - 1){
     $(this.rollingList[0]).addClass('right');

     $(this.rollingList[0]).siblings().removeClass('right');
     }
     else if(this.activeIndex == 0){
     $(this.rollingList[this.rollingLen - 1]).addClass('left');

     $(this.rollingList[this.rollingLen - 1]).siblings().removeClass('left');
     }*/

    //좌우 분리
    /*,leftSlide : function(){
     $(this.rollingList[this.activeIndex]).addClass('on');
     $(this.rollingList[this.activeIndex]).siblings().removeClass('on');

     $(this.rollingList[this.activeIndex + 1]).addClass('right');
     $(this.rollingList[this.activeIndex - 1]).addClass('left');

     $(this.rollingList[this.activeIndex + 1]).siblings().removeClass('right');
     $(this.rollingList[this.activeIndex - 1]).siblings().removeClass('left');


     if(this.activeIndex == this.rollingLen - 1){
     $(this.rollingList[0]).addClass('right');
     $(this.rollingList[this.activeIndex - 1]).addClass('left');

     $(this.rollingList[0]).siblings().removeClass('right');
     $(this.rollingList[this.activeIndex - 1]).siblings().removeClass('left');
     }
     else if(this.activeIndex == 0){
     $(this.rollingList[this.activeIndex + 1]).addClass('right');
     $(this.rollingList[this.rollingLen - 1]).addClass('left');

     $(this.rollingList[this.activeIndex + 1]).siblings().removeClass('right');
     $(this.rollingList[this.rollingLen - 1]).siblings().removeClass('left');
     }
     else{

     }
     }

     ,rightSlide : function(){

     $(this.rollingList[this.activeIndex]).addClass('on');
     $(this.rollingList[this.activeIndex]).siblings().removeClass('on');

     $(this.rollingList[this.activeIndex + 1]).addClass('right');
     $(this.rollingList[this.activeIndex - 1]).addClass('left');

     $(this.rollingList[this.activeIndex + 1]).siblings().removeClass('right');
     $(this.rollingList[this.activeIndex - 1]).siblings().removeClass('left');


     if(this.activeIndex == this.rollingLen - 1){
     $(this.rollingList[0]).addClass('right');
     $(this.rollingList[this.activeIndex - 1]).addClass('left');

     $(this.rollingList[0]).siblings().removeClass('right');
     $(this.rollingList[this.activeIndex - 1]).siblings().removeClass('left');
     }
     else if(this.activeIndex == 0){
     $(this.rollingList[this.activeIndex + 1]).addClass('right');
     $(this.rollingList[this.rollingLen - 1]).addClass('left');

     $(this.rollingList[this.activeIndex + 1]).siblings().removeClass('right');
     $(this.rollingList[this.rollingLen - 1]).siblings().removeClass('left');
     }

     }*/
}


var lineObj = {

};




$(window).on('load', function(){
    rolling.init();
});
