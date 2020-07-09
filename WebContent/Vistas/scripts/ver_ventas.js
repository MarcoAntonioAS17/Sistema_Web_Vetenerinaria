$(document).ready(function(){
	
	mostrar_registros(1,"");
	
	$("#busqueda_input").keyup(function(){
		   mostrar_registros($("#opciones").val(),$(this).val());
	   });
	
	$.post("../../Grafica",{
		accion : "grafica_ventas",
		
		},function(response){
			var resJSON = JSON.parse(response);
			
			cargar_grafica(resJSON);
	});
		

});

function mostrar_registros(opcion,busqueda){
	
	$.post("../../Ventas",{
		accion : "mostrar_ventas",
		valor : opcion,
		search: busqueda
	},
	function(responseJson){
		$("tbody").empty();
		var datos = JSON.parse(responseJson);
		
		var suma=0;
		
		const options2 = { style: 'currency', currency: 'USD' };
		const numberFormat2 = new Intl.NumberFormat('en-US', options2);
		var date;
		var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		
		for(var i in datos){
			date = new Date(datos[i].Fecha);
			date.setDate(date.getDate() + 1);
			
			$("#tbody1").append("<tr id=\"R"+datos[i].Codigo+"\" onclick=\"extender("+datos[i].Codigo+")\" class=\"odd\">" +
					"<td>"+datos[i].Codigo+"</td>" +
					"<td>"+datos[i].Nombre+"</td>" +
					"<td>"+date.toLocaleDateString("es-ES", options)+"</td>" +
					"<td>"+datos[i].Hora[0]+datos[i].Hora[1]+datos[i].Hora[2]+datos[i].Hora[3]+datos[i].Hora[4]+"</td>" +
					"<td> "+numberFormat2.format(datos[i].Total)+"</td>"+
					
					"<td>"+
					"<button type=\"button\" class=\"editar_pro but_"+datos[i].Codigo+"\"> <img width=\"25px\"  alt=\"icono-mas-info\" src=\"../img/mas-icono.svg\"></button>"+
					"<button type=\"button\" class=\"editar_pro bt-menos but_"+datos[i].Codigo+"\"> <img width=\"25px\"  alt=\"icono-menos-info\" src=\"../img/menos-icono.svg\"></button>"+
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
			
			for(j in datos[i].Ventas){
				$(codigoT).append("<tr class=\"odd\" >" +
						"<td>"+datos[i].Ventas[j][0]+"</td>" +
						"<td>"+datos[i].Ventas[j][1]+"</td>" +
						"<td>"+datos[i].Ventas[j][2]+"</td>" +
						"<td>"+numberFormat2.format(datos[i].Ventas[j][3])+"</td>" +
						"<td> "+numberFormat2.format(datos[i].Ventas[j][3]*datos[i].Ventas[j][2])+"</td>"+
						"</tr>");
			}
			
		}
		
		
		$("#tbody1").append("<tr class=\"odd\">" +
				"<td></td><td></td>" +
				"<td></td>" +
				"<td> Total </td>" +
				"<td> "+numberFormat2.format(suma)+"</td>" +
				"<td></td>"+
	            "</tr>");
		
		$("#reporte tr:not(.odd)").hide();
	    $("#reporte tr:first-child").show();
	});
	
}

function cargar_grafica(datos){
	//Grafica
	
	let miCanvas = document.getElementById("MiGrafica").getContext("2d");
	var Meses = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
	new Chart(miCanvas,{
		type: "line",
		data:{
			
				
			labels:[Meses[datos.Meses[11]-1],Meses[datos.Meses[10]-1],Meses[datos.Meses[9]-1],Meses[datos.Meses[8]-1],Meses[datos.Meses[7]-1],Meses[datos.Meses[6]-1],
					Meses[datos.Meses[5]-1],Meses[datos.Meses[4]-1],Meses[datos.Meses[3]-1],Meses[datos.Meses[2]-1],Meses[datos.Meses[1]-1],Meses[datos.Meses[0]-1]],
			datasets:[
				{
					lineTension: 0,    
					label: "Ventas Generales",
					fill: false,
					borderColor: "#f00",
					data:[datos.VentasM[11],datos.VentasM[10],datos.VentasM[9],datos.VentasM[8],datos.VentasM[7],datos.VentasM[6],
						datos.VentasM[5],datos.VentasM[4],datos.VentasM[3],datos.VentasM[2],datos.VentasM[1],datos.VentasM[0]]
				},{
					lineTension: 0,    
					label: "Ventas de Productos",
					fill: false,
					borderColor: "#0f0",
					data:[datos.V_Prod[11],datos.V_Prod[10],datos.V_Prod[9],datos.V_Prod[8],datos.V_Prod[7],datos.V_Prod[6],
						datos.V_Prod[5],datos.V_Prod[4],datos.V_Prod[3],datos.V_Prod[2],datos.V_Prod[1],datos.V_Prod[0]]
				},{
					lineTension: 0,    
					label: "Ventas en Servicios",
					fill: false,
					borderColor: "#00f",
					data:[datos.V_Ser[11],datos.V_Ser[10],datos.V_Ser[9],datos.V_Ser[8],datos.V_Ser[7],datos.V_Ser[6],
						datos.V_Ser[5],datos.V_Ser[4],datos.V_Ser[3],datos.V_Ser[2],datos.V_Ser[1],datos.V_Ser[0]]
				}
			]
		}, 
		options: {
			responsive: true,
			title: {
				fontSize: 22,
				fontColor: '#70a9d6',
				display: true,
				text: 'Ventas actuales'
			},
			tooltips: {
				mode: 'index',
				intersect: false,
			},
			hover: {
				mode: 'nearest',
				intersect: true
			},
			scales: {
				xAxes: [{
					display: true,
					scaleLabel: {
						fontSize: 14,
						display: true,
						labelString: 'Meses'
					}
				}],
				yAxes: [{
					display: true,
					scaleLabel: {
						fontSize: 14,
						display: true,
						labelString: 'Ventas'
					},
					ticks: {
						min: 0
					}
				}]
			}
		}
	});
}