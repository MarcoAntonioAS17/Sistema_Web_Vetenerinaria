$(document).ready(function(){
    
	mueveReloj();
	
	var now= new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;

	 $( "#Fec_C" ).val(today);
	
	 
	 
});

function mueveReloj(){
	
	var ahora= new Date();
	var h = ahora.getHours(),
    m = ahora.getMinutes();
	 if(h < 10) h = '0' + h; 
	 if(m < 10) m = '0' + m; 
	 $('#Hra_C').each(function(){ 
	   $(this).attr({'value': h + ':' + m});
	 });

    setTimeout("mueveReloj()",60000);
}