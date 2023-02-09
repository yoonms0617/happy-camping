//퀵메뉴 추가 이벤트 시작
$(document).ready(function(){
$(window).scroll(function(){  //스크롤이 움직일때마다 이벤트 발생
      var position = $(window).scrollTop()+200;
       // 현재 스크롤바의 위치값을 반환
      $("#Quick").stop().animate({top:position+"px"}, 400); //해당 오브젝트 위치값 재설정
   });
   
});
