$(document).ready(function(){
    $('.menu li:has(ul)').click(function(e){
        e.preventDefault();
       if($(this).hasClass('active')){
           $(this).removeClass('active');
           $(this).children('ul').slideUp();
       }else{
           $('.menu li ul').slideUp();
           $('.menu li').removeClass('active');
           $(this).addClass('active');
           $(this).children('ul').slideDown();
       }
    });
    $(".menu li ul li a").click(function () {
        window.location.href = $(this).attr("href");
     });
    
    
});

$(document).on("mousedown",function(e) {
    
	var menu = $(".sidebar");
	var checkbox = $("#abrir-cerrar"); 
	
    if(!menu.is(e.target) && menu.has(e.target).length === 0 && checkbox.is(":checked")){
    	checkbox.prop("checked",false);
    }
});