$(document).ready(function(){
	
	$.post("../../Compras",{
		accion : "mostrar_compras"
	},function(responseText){
		mostrar_registros(responseText);
	});

});

function mostrar_registros(responseJson){
	$("tbody").empty();
	var datos = JSON.parse(responseJson);
	var i=0;
	var suma=0+1-1;
	for(i in datos){
		$("#tbody1").append("<tr id=\"R"+datos[i].Codigo+"\" onclick=\"extender("+datos[i].Codigo+")\" class=\"odd\">" +
				"<td>"+datos[i].Codigo+"</td>" +
				"<td>"+datos[i].Nombre+"</td>" +
				"<td>"+datos[i].Fecha+"</td>" +
				"<td>"+datos[i].Hora+"</td>" +
				"<td> $"+datos[i].Total+"</td>"+
				"<td>"+
				"<button onclick=\"mas("+datos[i].Codigo+")\" type=\"button\" class=\"editar_pro\"> <img width=\"25px\"  alt=\"icono-mas-info\" src=\"../img/mas-icono.svg\"></button>"+
				"</td>"+
                "</tr>");
		suma+=parseInt(datos[i].Total,10);
		
		$("#tbody1").append("<tr> <td colspan=\"6\">" +
				"<table>"+
                			"<thead>"+
                			"<tr>"+
                		    "<th>Codigo</th>"+
                		    "<th>Nombre</th>"+
                		    "<th>Cantidad</th>"+
                		    "<th>Precio Unitario</th>"+
                		    "<th>Precio Total</th>"+
                		    "</tr>"+
                			"</thead>" +
                			"<tbody id=\"Tb"+datos[i].Codigo+"\""+
                			"</tbody>"+
                	"</table>" +
                	"</tr>");
		var codigoT="#Tb"+datos[i].Codigo;
		
		for(j in datos[i].Compras){
			$(codigoT).append("<tr >" +
					"<td>"+datos[i].Compras[j][0]+"</td>" +
					"<td>"+datos[i].Compras[j][1]+"</td>" +
					"<td>"+datos[i].Compras[j][2]+"</td>" +
					"<td>"+datos[i].Compras[j][3]+"</td>" +
					"<td> $"+datos[i].Compras[j][3]*datos[i].Compras[j][2]+"</td>"+
					"</tr>");
		}
		
	}
	
	
	$("#tbody1").append("<tr class=\"odd\">" +
			"<td></td><td></td>" +
			"<td></td>" +
			"<td> Total </td>" +
			"<td> $"+suma+"</td>" +
			"<td></td>"+
            "</tr>");
	
	$("#reporte tr:not(.odd)").hide();
    $("#reporte tr:first-child").show();
    
	
}