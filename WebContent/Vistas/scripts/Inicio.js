
$(document).ready(function(){
	
	$.post("../../Principal",{
		accion : "C_Datos"
	},function(responseText){
		var datos = JSON.parse(responseText);
		
		$("#Prod_Tot").text(datos.Totales);
		$("#Agotarse").text(datos.Agotarse);
		$("#caducar").text(datos.Caducar);
	});

});
