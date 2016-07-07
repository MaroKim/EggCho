// mobile
$( document ).ready(function(){
  console.log('0703/17:39');
   $('.button-collapse').sideNav();
   // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();

    // auto modal load Start
    // $('#testt').last().trigger('click');
    // auto modal load End

    $('.collapsible').collapsible({
      accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });

    $("#card2").click(function(){
            var div = $(".card2");
            var pa = div.parent();
            console.log('ooo');
            div.animate({left: '0', opacity: '0.5'}, "slow");
            console.log(div);
    });

// ImageLoad test Start
$('#ImgContainer').imagesLoaded()
  .always( function( instance ) {
    console.log('all images loaded');
  })
  .done( function( instance ) {
    console.log('all images successfully loaded');
  })
  .fail( function() {
    console.log('all images loaded, at least one is broken');
  })
  .progress( function( instance, image ) {
    var result = image.isLoaded ? 'loaded' : 'broken';
    console.log( 'image is ' + result + ' for ' + image.img.src );
  });
// ImageLoad test End





});

// modal close test Start
function customModalClose(){
  $modal = $(this);
  $modal.find(".modal-close").on('click.close', function(e) {
    $modal.closeModal(options);
  });
  $modal.css({
    display : "block",
    opacity: 0
});
}
// modal close test End


// Contents 추가 요청 시작
   var options = [
      {selector: '.page-footer', offset: 50,
      callback: function() {
         Materialize.toast("추가 로딩 호출 할꺼임 ㅋ", 1800 ); }
       },

      {selector: '.page-footer', offset: 500, callback: function()
      { Materialize.fadeIn("#ldCircle"); } } ];
// Contents 추가 요청 끝



// 이미지 파일 첨부 처리 시작
function upImg1(thisObj) {
           if(!/(\.gif|\.jpg|\.jpeg|\.png)$/i.test(thisObj.value)) {
               alert("jpg, png, gif 포맷만 가능합니다.");
               return;
           }else {
            //  $('input[type="submit"]').click(); //폼에 submit 있어야는데 이것도 나름 문제..
             console.log('포맷 검사 통과');
           }
           var maxSize  = 31457280;    //30MB
          // var maxSize  = 2;
          //  var fielSize = Math.round(thisObj.fileSize);
          // var size = document.getElementById("thisObj").files[0].size;
           console.log(thisObj.value);
           console.log(thisObj.files);
           var file = thisObj;
           var fileSize = file.files[0].size;
           console.log(fileSize);
          //  var fileSize = (thisObj.value).size;
          // var fileSize = thisObj.innerHTML;
          // var fileSize = thisObj.files[0].size;
          //  console.log(fileSize);

           if(fileSize > maxSize){
            alert("첨부파일 사이즈는 30MB 이내로 등록 가능합니다.");
            return;
        }
        console.log('파일 사이즈 30메가 제한 통과');

         }
