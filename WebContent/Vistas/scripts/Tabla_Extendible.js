 $(document).ready(function(){
            
    $("#reporte tr:not(.odd)").hide();
    $("#reporte tr:first-child").show();
    
   
});
 
function extender(ID){
	
	var codigo = "#R"+ID;
	$(codigo).next("tr").toggle();
	var codigoB = ".but_"+ID;
	$(codigoB).toggle();
    	
}