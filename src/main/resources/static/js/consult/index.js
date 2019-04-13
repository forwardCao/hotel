window.onload=function(){
  $('footer dl').click(function(){
  	var index=$(this).index();
  	$('footer dl').removeClass('ac').eq(index).addClass('ac');
  })
}




