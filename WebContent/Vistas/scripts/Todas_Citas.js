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
		var date;
		var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		
		for(var i in datos){
			date = new Date(datos[i].Fecha); 
			date.setDate(date.getDate() + 1);
			$("tbody").append("<tr>" +
					"<td>"+datos[i].IDCita+"</td>" +
					"<td>"+datos[i].Tipo+"</td>" +
					"<td>"+date.toLocaleDateString("es-ES", options)+"</td>" +
					"<td> "+datos[i].Hora+"</td>" +
					"<td> "+datos[i].NombreC+"</td>" +
					"<td>"+datos[i].NombreM+"</td>" +
					"<td>"+datos[i].Notas+"</td>" +
					"</tr>");
		}		
	}
	);
}
