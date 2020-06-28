	//Guardar datos en BD
   $(document).ready(function(){
		   
		   mostrar_registros();
		   
		   
	       
   });
	   
function mostrar_registros(){
	$("tbody").empty();
	$.post("../../SCitas",{
		accion : "mostrar_todas"
	},function(responseJson){
		
		var datos = JSON.parse(responseJson);

		for(var i in datos){
			$("tbody").append("<tr>" +
					"<td>"+datos[i].IDCita+"</td>" +
					"<td>"+datos[i].Tipo+"</td>" +
					"<td>"+datos[i].Fecha+"</td>" +
					"<td> "+datos[i].Hora+"</td>" +
					"<td> "+datos[i].NombreC+"</td>" +
					"<td>"+datos[i].NombreM+"</td>" +
					"<td>"+datos[i].Notas+"</td>" +
					"</tr>");
		}		
	}
	);
}
