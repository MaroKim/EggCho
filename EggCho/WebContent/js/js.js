// mobile
$( document ).ready(function(){
   $(".button-collapse").sideNav();
   // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();
    console.log(startTest);
    $('#signup').openModal();
    $('#login').openModal();
    $('.collapsible').collapsible({
      accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });
});


// ScrollFire

  // var options = [
  //   {selector: '.class', offset: 200, callback: customCallbackFunc } },
  //   {selector: '.other-class', offset: 200, callback: function() {
  //     customCallbackFunc();
  //   } },
  // ];
  // Materialize.scrollFire(options);


   var options = [
      {selector: '.page-footer', offset: 50,
      callback: function() {
         Materialize.toast("추가 로딩 호출 할꺼임 ㅋ", 1500 ); }
       },

      {selector: '.page-footer', offset: 500, callback: function()
      { Materialize.fadeIn("#ldCircle"); } } ];
        Materialize.scrollFire(options);
